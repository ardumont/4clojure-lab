(ns ^{:doc "http://www.4clojure.com/problem/86#prob-title"}
  my-4clojure-lab.core-86
  (:use clojure.repl)
  (:use clojure.java.javadoc)
  (:use [midje.sweet])
  (:use [clojure.pprint :only [pprint]]))

(println "--------- BEGIN 86  ----------" (java.util.Date.))

;; Happy numbers are positive integers that follow a particular
;; formula: take each individual digit, square it, and then sum the
;; squares to get a new number. Repeat with the new number and
;; eventually, you might get to a number whose squared sum is 1. This
;; is a happy number. An unhappy number (or sad number) is one that
;; loops endlessly. Write a function that determines if a number is
;; happy or not.

(defn happy-number ""
  [i]
  (if (<= i 4)
    false
    (let [s (apply + (map #(let [i (read-string %)] (* i i)) (re-seq #"\d" (str i))))]
      (if (= 1 s)
        true
        (happy-number s)))))

(fact 
  (happy-number 7) => true
  (happy-number 986543210) => true
  (happy-number 2) => false
  (happy-number 3) => false)

(println "--------- END 86  ----------" (java.util.Date.))
