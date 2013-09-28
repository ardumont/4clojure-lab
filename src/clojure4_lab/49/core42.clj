(ns ^{:doc "http://www.4clojure.com/problem/42#prob-title"}
  clojure4-lab.49.core42
  (:use clojure.repl)
  (:use clojure.java.javadoc)
  (:use [midje.sweet]))



;; Write a function which calculates factorials.

(defn facto "Factorial"
  [n]
  (apply * n (range 2 n)))

(fact
  (facto 1) => 1
  (facto 3) => 6
  (facto 5) => 120
  (facto 8) => 40320)
