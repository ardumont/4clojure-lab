(ns ^{:doc "http://www.4clojure.com/problem/86#prob-title"}
  my-4clojure-lab.99.core86
  (:use clojure.repl)
  (:use clojure.java.javadoc)
  (:use [midje.sweet])
  (:use [clojure.pprint :only [pprint]]))



;; Happy numbers are positive integers that follow a particular
;; formula: take each individual digit, square it, and then sum the
;; squares to get a new number. Repeat with the new number and
;; eventually, you might get to a number whose squared sum is 1. This
;; is a happy number. An unhappy number (or sad number) is one that
;; loops endlessly. Write a function that determines if a number is
;; happy or not.

;; info: http://en.wikipedia.org/wiki/Happy_number
;; tips: when a number is not an happy one, there is a cycle of repetition
;; of the same number.

(defn happy? "Determines if a number is happy or not."
  [i]
  ((fn f [s n]
     (or
      (= n 1)
      (if (s n)
        false
        (f (conj s n) (apply + (map (zipmap "0123456789" [0 1 4 9 16 25 36 49 64 81]) (str n)))))))
   #{} i))

(fact 
  (happy? 7) => true
  (happy? 986543210) => true
  (happy? 2) => false
  (happy? 3) => false)


