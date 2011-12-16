(ns ^{:doc "http://www.4clojure.com/problem/23#prob-title"}
  my-4clojure-lab.core-23
  (:use clojure.repl)
  (:use clojure.java.javadoc)
  (:use [midje.sweet])
  (:use [clojure.pprint :only [pp pprint]]))

;; Write a function which reverses a sequence.

(println "--------- BEGIN 23  ----------" (java.util.Date.))

test not run	
(= (__ [1 2 3 4 5]) [5 4 3 2 1])
test not run	
(= (__ (sorted-set 5 7 2 7)) '(7 5 2))
test not run	
(= (__ [[1 2][3 4][5 6]]) [[5 6][3 4][1 2]])

(println "--------- END 23  ----------" (java.util.Date.))
