(ns ^{:doc "http://www.4clojure.com/problem/80#prob-title"}
  my-4clojure-lab.99.core80
  (:use clojure.repl)
  (:use clojure.java.javadoc)
  (:use [midje.sweet]))



;; A number is "perfect" if the sum of its divisors equal the number
;; itself. 6 is a perfect number because 1+2+3=6. Write a function
;; which returns true for perfect numbers and false otherwise.

(defn perfect? "Is the number perfect?"
  [n]
  (= n (apply + (for [x (range 1 n) :when (= 0 (rem n x))] x))))

(fact "IT test"
  (perfect? 6) => true
  (perfect? 7) => false
  (perfect? 496) => true
  (perfect? 500) => false
  (perfect? 8128) => true)


