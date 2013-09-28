(ns ^{:doc "http://www.4clojure.com/problem/83#prob-title"}
  4clojure-lab.99.core83
  (:use clojure.repl)
  (:use clojure.java.javadoc)
  (:use [midje.sweet]))



;; Write a function which takes a variable number of booleans.
;; Your function should return true if some of the parameters are
;; true, but not all of the parameters are true.
;; Otherwise your function should return false.

(defn half-truth "Takes a variable number of booleans. Returns true if some of the parameters are true, else return false."
  [& xv]
  (= 2 (count (group-by true? xv))))

(fact
  (half-truth false false) => false
  (half-truth true false) => true
  (half-truth true) => false
  (half-truth false true false) => true
  (half-truth true true true) => false
  (half-truth true true true false) => true)
