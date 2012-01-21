(ns ^{:doc "http://www.4clojure.com/problem/98#prob-title"}
  my-4clojure-lab.core-98
  (:use clojure.repl)
  (:use clojure.java.javadoc)
  (:use [midje.sweet])
  (:use [clojure.pprint :only [pprint]]))

(println "--------- BEGIN 98  ----------" (java.util.Date.))

;; A function f defined on a domain D induces an equivalence relation on D, as follows: a is equivalent to b with
;; respect to f if and only if (f a) is equal to (f b). Write a function with arguments f and D that computes the equivalence classes of D with respect to f.

(defn equi "Equivalence relation"
  [f s]
  (set (map set (vals (group-by f s)))))

(fact 
  (equi #(* % %) #{-2 -1 0 1 2}) => #{#{0} #{1 -1} #{2 -2}}
  (equi #(rem % 3) #{0 1 2 3 4 5 }) => #{#{0 3} #{1 4} #{2 5}}
  (equi identity #{0 1 2 3 4}) => #{#{0} #{1} #{2} #{3} #{4}}
  (equi (constantly true) #{0 1 2 3 4}) => #{#{0 1 2 3 4}})

(println "--------- END 98  ----------" (java.util.Date.))
