(ns ^{:doc "http://www.4clojure.com/problem/85#prob-title"}
  4clojure-lab.99.core85
  (:use clojure.repl)
  (:use clojure.java.javadoc)
  (:use [midje.sweet])
  (:use [clojure.pprint :only [pprint]]))



;; Write a function which generates the power set of a given set. The
;; power set of a set x is the set of all subsets of x, including the
;; empty set and x itself.

(defn pset "Power set of a given set"
  [s]
  (reduce (fn [p e] (into p (map #(conj % e) p))) #{#{}} s))

(fact (pset #{1 :a}) => #{#{1 :a} #{:a} #{} #{1}})
(fact (pset #{}) => #{#{}})
(fact (pset #{1 2 3}) => #{#{} #{1} #{2} #{3} #{1 2} #{1 3} #{2 3} #{1 2 3}})
(fact (count (pset (into #{} (range 10)))) => 1024)
