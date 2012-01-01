(ns ^{:doc "http://www.4clojure.com/problem/135#prob-title"}
  my-4clojure-lab.core-135
  (:use clojure.repl)
  (:use clojure.java.javadoc)
  (:use [midje.sweet])
  (:use [clojure.pprint :only [pprint]]))

(println "--------- BEGIN 135  ----------" (java.util.Date.))

;; Your friend Joe is always whining about Lisps using the prefix
;; notation for math. Show him how you could easily write a function
;; that does math using the infix notation. Is your favorite language
;; that flexible, Joe?
;; Write a function that accepts a variable length mathematical
;; expression consisting of numbers and the operations +, -, *, and /.
;; Assume a simple calculator that does not do precedence and instead
;; just calculates left to right.

(defn compute "Compute"
  [& xv]
  (first (reduce #(if (= 2 (count %)) [((last %) (first %) %2)] (conj % %2)) [] xv)))

(fact 
  (compute 2 + 5) => 7
  (compute 38 + 48 - 2 / 2) => 42
  (compute 10 / 2 - 1 * 2) => 8
  (compute 20 / 2 + 2 + 4 + 8 - 6 - 10 * 9) => 72)

(println "--------- END 135  ----------" (java.util.Date.))
