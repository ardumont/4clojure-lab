(ns ^{:doc "http://www.4clojure.com/problem/66#prob-title"}
  4clojure-lab.99.core66
  (:use clojure.repl)
  (:use clojure.java.javadoc)
  (:use [midje.sweet]))



;; Given two integers, write a function which returns the greatest
;; common divisor.

(defn gcd "Greatest common divisor"
  [a b]
  (if (= 0 b) a
      (gcd b (mod a b))))

(fact
  (gcd 2 4) => 2
  (gcd 10 5) => 5
  (gcd 5 7) => 1
  (gcd 1023 858) => 33)
