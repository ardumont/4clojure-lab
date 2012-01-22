(ns ^{:doc "http://www.4clojure.com/problem/116#prob-title"}
  my-4clojure-lab.core-116
  (:use clojure.repl)
  (:use clojure.java.javadoc)
  (:use [midje.sweet])
  (:use [clojure.pprint :only [pprint]]))

(println "--------- BEGIN 116  ----------" (java.util.Date.))

;; A balanced prime is a prime number which is also the mean of the primes directly before and after it in the sequence
;; of valid primes. Create a function which takes an integer n, and returns true iff it is a balanced prime.

(defn balanced-prime? "Determine if a number is a prime."
  [n]
  (letfn [(p? [n] (not-any? #(= 0 (rem n %)) (take (int (Math/sqrt n)) (range 2 n))))
          (s [n] (first (for [x (range (inc n) (* 2 n)) :when (p? x)] x)))
          (p [n] (first (for [x (reverse (range 2 n)) :when (p? x)] x)))]
    (and (< 2 n) (p? n) (= n (/ (+ (s n) (p n)) 2)))))

(fact 
  (balanced-prime? 4) => false
  (balanced-prime? 563) => true
  (nth (filter balanced-prime? (range)) 15) => 1103)

(println "--------- END 116  ----------" (java.util.Date.))

