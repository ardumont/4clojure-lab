(ns ^{:doc "http://www.4clojure.com/problem/67#prob-title"}
  4clojure-lab.99.core67
  (:use clojure.repl)
  (:use clojure.java.javadoc)
  (:use [midje.sweet])
  (:use [clojure.pprint :only [pprint]]))



;; Write a function which returns the first x number of prime numbers.

(defn primes "A lazy seq of primes, using the 'n is prime if not divisible by an int > sqrt n' optimization"
  [n]
  (loop [cd 2 c n p []]
    (if (= 0 c)
      p
      (if (some #(= 0 (rem cd %)) p)
        (recur (inc cd) c p)
        (recur (inc cd) (dec c) (conj p cd))))))

(fact
  (primes 2) => [2 3]
  (primes 5) => [2 3 5 7 11]
  (last (primes 100)) => 541)
