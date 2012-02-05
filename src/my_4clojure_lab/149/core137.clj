(ns ^{:doc "http://www.4clojure.com/problem/137#prob-title"}
  my-4clojure-lab.149.core137
  (:use clojure.repl)
  (:use clojure.java.javadoc)
  (:use [midje.sweet]))



;; Write a function which returns a sequence of digits of a non-negative number (first argument) in numerical system
;; with an arbitrary base (second argument). Digits should be represented with their integer values, e.g. 15 would be [1
;; 5] in base 10, [1 1 1 1] in base 2 and [15] in base 16.

(defn base
  [n b]
  (loop [c n a []]
    (if (< c b)
      (cons c a)
      (recur (quot c b) (cons (mod c b) a)))))

(fact
  (base 1234501 10) => [1 2 3 4 5 0 1]
  (base 0 11) => [0]
  (base 9 2) => [1 0 0 1]
  (let [n (rand-int 100000)] (base n n)) => [1 0]
  (base Integer/MAX_VALUE 42) => [16 18 5 24 15 1]) 



