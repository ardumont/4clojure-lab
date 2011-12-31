(ns ^{:doc "http://www.4clojure.com/problem/66#prob-title"}
  my-4clojure-lab.core-66
  (:use clojure.repl)
  (:use clojure.java.javadoc)
  (:use [midje.sweet]))

(println "--------- BEGIN 66  ----------" (java.util.Date.))

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

(println "--------- END 66  ----------" (java.util.Date.))
