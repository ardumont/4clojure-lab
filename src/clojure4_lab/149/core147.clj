(ns ^{:doc "http://www.4clojure.com/problem/147#prob-title"}
  clojure4-lab.149.core147
  (:use clojure.repl)
  (:use clojure.java.javadoc)
  (:use [midje.sweet])
  (:use [clojure.pprint :only [pprint]]))



;; Write a function that, for any given input vector of numbers,
;; returns an infinite lazy sequence of vectors, where each next one
;; is constructed from the previous following the rules used in
;; Pascal's Triangle.
;; For example, for [3 1 2], the next row is [3 4 3 2].

(defn trapezoid "Trapezoid"
  [s]
  (iterate
   (fn [[f & r :as v]] (concat [f] (map + v r) [(last v)]))
   s))

(fact
  (second (trapezoid [2 3 2])) => [2 5 5 2]
  (take 5 (trapezoid [1])) => [[1] [1 1] [1 2 1] [1 3 3 1] [1 4 6 4 1]]
  (take 2 (trapezoid [3 1 2])) => [[3 1 2] [3 4 3 2]]
  (take 10 (trapezoid [2 4 2])) => (rest (take 11 (trapezoid [2 2]))))
