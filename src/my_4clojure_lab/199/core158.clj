(ns ^{:doc "http://www.4clojure.com/problem/158#prob-title"}
  my-4clojure-lab.199.core158
  (:use clojure.repl)
  (:use clojure.java.javadoc)
  (:use [midje.sweet]))

;; Decurry
 
;; Difficulty:	Medium
;; Topics:	partial-functions

;; Write a function that accepts a curried function of unknown arity n. Return an equivalent function of n arguments.
;; You may wish to read http://en.wikipedia.org/wiki/Currying

(defn decurry
  [f]
  f)

(fact
  ((decurry (fn [a]
              (fn [b]
                (fn [c]
                  (fn [d]
                    (+ a b c d)))))) 1 2 3 4) => 10

  ((decurry (fn [a]
              (fn [b]
                (fn [c]
                  (fn [d]
                    (* a b c d)))))) 1 2 3 4) => 24

  ((decurry (fn [a]
              (fn [b]
                (* a b)))) 5 5) => 25)