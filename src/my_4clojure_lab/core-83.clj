(ns ^{:doc "http://www.4clojure.com/problem/83#prob-title"}
  my-4clojure-lab.core-83
  (:use clojure.repl)
  (:use clojure.java.javadoc)
  (:use [midje.sweet]))

(println "--------- BEGIN 83  ----------" (java.util.Date.))

;; Write a function which takes a variable number of booleans.
;; Your function should return true if some of the parameters are
;; true, but not all of the parameters are true.
;; Otherwise your function should return false.

(defn half-truth "Takes a variable number of booleans. "
  [& xv]
  (let [n (count (filter truthy xv))]
    (cond (zero? n) false
          (= (count xv) n) false
          :else true)))

(fact 
  (half-truth false false) => false
  (half-truth true false) => true
  (half-truth true) => false
  (half-truth false true false) => true
  (half-truth true true true) => false
  (half-truth true true true false) => true)

(println "--------- END 83  ----------" (java.util.Date.))