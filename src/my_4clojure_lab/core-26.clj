(ns ^{:doc "http://www.4clojure.com/problem/26#prob-title"}
  my-4clojure-lab.core-26
  (:use clojure.repl)
  (:use clojure.java.javadoc)
  (:use [midje.sweet]))

(println "--------- BEGIN 26  ----------" (java.util.Date.))

;; Write a function which returns the nth element from a sequence.

(defn fib "Fibonacci"
  [n] (take n (map first (iterate (fn [[a b]] [b (+ a b)]) [1 1]))))

(fact
  (fib 3) => '(1 1 2)
  (fib 6) => '(1 1 2 3 5 8)
  (fib 8) => '(1 1 2 3 5 8 13 21))

(println "--------- END 26  ----------" (java.util.Date.))
