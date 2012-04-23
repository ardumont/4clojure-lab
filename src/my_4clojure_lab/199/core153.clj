(ns ^{:doc "http://www.4clojure.com/problem/150#prob-title"}
  my-4clojure-lab.199.core153
  (:use clojure.repl)
  (:use clojure.java.javadoc)
  (:use [midje.sweet]))

;; Pairwise Disjoint Sets
 
;; Difficulty:	Easy
;; Topics:	set-theory

;; Given a set of sets, create a function which returns true if no two of those sets have any elements in common (such
;; sets are usually called pairwise disjoint or mutually disjoint) and false otherwise. Some of the test cases are a
;; bit tricky, so pay a little more attention to them.

(defn pds? "pairwise disjoint sets?"
  [s]
  (let [a (reduce into [] s)]
    (= (count a) (count (set a)))))

(fact 
  (pds? #{#{\U} #{\s} #{\e \R \E} #{\P \L} #{\.}}) => true)

(fact
  (pds? #{#{:a :b :c :d :e}
          #{:a :b :c :d}
          #{:a :b :c}
          #{:a :b}
          #{:a}}) => false)
(fact
  (pds? #{#{[1 2 3] [4 5]}
          #{[1 2] [3 4 5]}
          #{[1] [2] 3 4 5}
          #{1 2 [3 4] [5]}}) => true)
(fact
  (pds? #{#{'a 'b}
          #{'c 'd 'e}
          #{'f 'g 'h 'i}
          #{''a ''c ''f}}) => true)
(fact
  (pds? #{#{'(:x :y :z) '(:x :y) '(:z) '()}
          #{#{:x :y :z} #{:x :y} #{:z} #{}}
          #{'[:x :y :z] [:x :y] [:z] [] {}}}) => false)
(fact
  (pds? #{#{(= "true") false}
          #{:yes :no}
          #{(class 1) 0}
          #{(symbol "true") 'false}
          #{(keyword "yes") ::no}
          #{(class '1) (int \0)}}) => false)
(fact
  (pds? #{#{distinct?}
          #{#(-> %) #(-> %)}
          #{#(-> %) #(-> %) #(-> %)}
          #{#(-> %) #(-> %) #(-> %)}}) => true)
(fact
  (pds? #{#{(#(-> *)) + (quote mapcat) #_ nil}
          #{'+ '* mapcat (comment mapcat)}
          #{(do) set contains? nil?}
          #{, , , #_, , empty?}}) => false)