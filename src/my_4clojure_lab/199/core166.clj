(ns ^{:doc "http://www.4clojure.com/problem/166#prob-title"}
  my-4clojure-lab.199.core166
  (:use clojure.repl)
  (:use clojure.java.javadoc)
  (:use [midje.sweet]))

;; Difficulty:	Elementary
;; Topics:	

;; For any orderable data type it's possible to derive all of the basic comparison operations (<, ≤, =, ≠, ≥, and >)
;; from a single operation (any operator but = or ≠ will work).

;; Write a function that takes three arguments, a less than operator for the data and two items to compare. The function
;; should return a keyword describing the relationship between the two items. The keywords for the relationship between
;; x and y are as follows:

;;     x = y → :eq
;;     x > y → :gt
;;     x < y → :lt

(defn op
  [f x y]
  (cond (f x y) :lt
        (f y x) :gt
        :else :eq))

(fact
  (op < 5 1) => :gt
  (op > 0 2) => :gt
  (op (fn [x y] (< (count x) (count y))) "pear" "plum") => :eq
  (op (fn [x y] (< (mod x 5) (mod y 5))) 21 3) => :lt)
