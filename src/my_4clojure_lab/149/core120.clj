(ns ^{:doc "http://www.4clojure.com/problem/120#prob-title"}
  my-4clojure-lab.149.core120
  (:use clojure.repl)
  (:use clojure.java.javadoc)
  (:use [midje.sweet]))



;; Write a function which takes a collection of integers as an
;; argument. Return the count of how many elements are smaller than
;; the sum of their squared component digits. For example: 10 is
;; larger than 1 squared plus 0 squared; whereas 15 is smaller than 1
;; squared plus 5 squared.

(defn f
  [s]
  (count
   (for [x s
         :let [d (map (comp read-string str) (str x))]
         :when (< x (reduce + (map #(* % %) d)))]
     s)))

(fact
  (f (range 10)) => 8
  (f (range 30)) => 19
  (f (range 100)) => 50
  (f (range 1000)) => 50)


