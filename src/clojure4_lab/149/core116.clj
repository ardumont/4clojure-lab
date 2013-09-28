(ns ^{:doc "http://www.4clojure.com/problem/116#prob-title"}
  clojure4-lab.149.core116
  (:use clojure.repl)
  (:use clojure.java.javadoc)
  (:use [midje.sweet])
  (:use [clojure.pprint :only [pprint]]))



;; A balanced prime is a prime number which is also the mean of the primes directly before and after it in the sequence
;; of valid primes. Create a function which takes an integer n, and returns true iff it is a balanced prime.

(defn balanced-prime? "Determine if a number is a prime."
  [n]
  (let  [p? (fn [n] (not-any? #(= 0 (rem n %)) (range 2 n)))
         s #(nth (for [x (range (inc %) (* 2 %)) :when (p? x)] x) 0)
         p #(nth (for [x (reverse (range 2 %)) :when (p? x)] x) 0)]
    (and (< 2 n) (p? n) (= n (/ (+ (s n) (p n)) 2)))))

(fact
  (balanced-prime? 4) => false
  (balanced-prime? 563) => true
  (nth (filter balanced-prime? (range)) 15) => 1103)
