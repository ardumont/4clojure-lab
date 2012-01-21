(ns ^{:doc "http://www.4clojure.com/problem/75#prob-title"}
  my-4clojure-lab.core-75
  (:use clojure.repl)
  (:use clojure.java.javadoc)
  (:use [midje.sweet])
  (:use [clojure.pprint :only [pprint]]))

(println "--------- BEGIN 75  ----------" (java.util.Date.))

;; Two numbers are coprime if their greatest common divisor equals 1. Euler's totient function f(x) is defined as the
;; number of positive integers less than x which are coprime to x. The special case f(1) equals 1. Write a function
;; which calculates Euler's totient function.

(defn euler "Euler's totient function"
  [n]
  (if (= 1 n)
    1
    (letfn [(g [a b] (if (= 0 b) a (g b (mod a b))))]
      (count (filter #(= 1 (g n %)) (range 1 n))))))

(fact 
  (euler 1) => 1
  (euler 10) => (count '(1 3 7 9))
  (euler 40) => 16
  (euler 99) => 60)

(println "--------- END 75  ----------" (java.util.Date.))
