(ns ^{:doc "http://www.4clojure.com/problem/103#prob-title"}
  clojure4-lab.149.core103
  (:use clojure.repl
        clojure.java.javadoc
        [midje.sweet]
        [clojure.pprint :only [pprint]]))



;; Given a sequence S consisting of n elements generate all k-combinations of S, i. e. generate all possible sets
;; consisting of k distinct elements taken from S. The number of k-combinations for a sequence is equal to the binomial
;; coefficient.

(unfinished)

;; from 4clojure.com/problem/85
(defn pset "Power set of a given set"
  [s]
  (reduce (fn [p e] (into p (map #(conj % e) p))) #{#{}} s))

(fact
  (pset #{1 :a}) => #{#{1 :a} #{:a} #{} #{1}}
  (pset #{}) => #{#{}}
  (pset #{1 2 3}) => #{#{} #{1} #{2} #{3} #{1 2} #{1 3} #{2 3} #{1 2 3}}
  (count (pset (into #{} (range 10)))) => 1024)

(defn k-combi "Generate a n-combinations of the seq s"
  [n s]
  (set (filter #(= n (count %)) (pset s))))

(fact "IT test"
  (k-combi 1 #{4 5 6}) => #{#{4} #{5} #{6}}
  (k-combi 10 #{4 5 6}) => #{}
  (k-combi 2 #{0 1 2}) => #{#{0 1} #{0 2} #{1 2}}
  (k-combi 3 #{0 1 2 3 4}) => #{#{0 1 2} #{0 1 3} #{0 1 4} #{0 2 3} #{0 2 4}
                                #{0 3 4} #{1 2 3} #{1 2 4} #{1 3 4} #{2 3 4}}
  (k-combi 4 #{[1 2 3] :a "abc" "efg"}) => #{#{[1 2 3] :a "abc" "efg"}}
  (k-combi 2 #{[1 2 3] :a "abc" "efg"}) => #{#{[1 2 3] :a} #{[1 2 3] "abc"} #{[1 2 3] "efg"}
                                             #{:a "abc"} #{:a "efg"} #{"abc" "efg"}})
