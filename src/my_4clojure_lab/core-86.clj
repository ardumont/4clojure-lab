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

(defn happy-number? "Determines if a number is happy or not - Max loop is 100 - so a number is considered not happy if we go beyond 100 checks."
  [i]
  (loop [n i c 100]
    (if (= 0 c)
       false
       (let [s (apply + (map (zipmap "0123456789" [0 1 4 9 16 25 36 49 64 81]) (str n)))]
         (if (= 1 s)
           true
           (recur s (dec c)))))))

(fact 
  (happy-number? 7) => true
  (happy-number? 986543210) => true
  (happy-number? 2) => false
  (happy-number? 3) => false)

(println "--------- END 86  ----------" (java.util.Date.))
