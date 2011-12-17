(ns ^{:doc "http://www.4clojure.com/problem/42#prob-title"}
  my-4clojure-lab.core-42
  (:use clojure.repl)
  (:use clojure.java.javadoc)
  (:use [midje.sweet]))

(println "--------- BEGIN 42  ----------" (java.util.Date.))

;; Write a function which calculates factorials.

(defn facto "Factorial"
  [n]
  (if (<= n 0) 1 (* n (facto (dec n)))))

(fact 
  (facto 1) => 1
  (facto 3) => 6
  (facto 5) => 120
  (facto 8) => 40320)

(println "--------- END 42  ----------" (java.util.Date.))
