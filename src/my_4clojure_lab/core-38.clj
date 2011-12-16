(ns ^{:doc "http://www.4clojure.com/problem/38#prob-title"}
  my-4clojure-lab.core-38
  (:use clojure.repl)
  (:use clojure.java.javadoc)
  (:use [midje.sweet]))

;; Write a function which returns the Nth element from a sequence.

(println "--------- BEGIN 38  ----------" (java.util.Date.))

(defn my-max "max"
  [& xv]
  (reduce (fn [a b] (if (< a b) b a)) xv))

(fact
  (my-max 1 8 3 4) => 8
  (my-max 30 20) => 30
  (my-max 45 67 11) => 67)

(println "--------- END 38  ----------" (java.util.Date.))
