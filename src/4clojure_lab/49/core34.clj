(ns ^{:doc "http://www.4clojure.com/problem/34#prob-title"}
  4clojure-lab.49.core34
  (:use clojure.repl)
  (:use clojure.java.javadoc)
  (:use [midje.sweet]))

;; Write a function that creates a link of all integers in a certain
;; range.

(defn my-range "My range function implementation - start inclusive, end exclusive"
  [s e]
  (take (- e s) (iterate inc s)))

(fact
  (my-range 1 4) => '(1 2 3)
  (my-range -2 2) => '(-2 -1 0 1)
  (my-range 5 8) => '(5 6 7))
