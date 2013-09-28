(ns ^{:doc "http://www.4clojure.com/problem/81#prob-title"}
  4clojure-lab.99.core81
  (:use clojure.repl)
  (:use clojure.java.javadoc)
  (:use [midje.sweet]))



;; Write a function which returns the intersection of two sets. The intersection is the sub-set of items that each set has in common.

(defn my-intersect "Returns the intersection of two sets."
  [s0 s1]
  ((comp set filter) s0 s1))

(fact
  (my-intersect #{0 1 2 3} #{2 3 4 5}) => #{2 3}
  (my-intersect #{0 1 2} #{3 4 5}) => #{}
  (my-intersect #{:a :b :c :d} #{:c :e :a :f :d}) => #{:a :c :d})
