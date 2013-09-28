(ns ^{:doc "http://www.4clojure.com/problem/62#prob-title"}
  4clojure-lab.99.core62
  (:use clojure.repl)
  (:use clojure.java.javadoc)
  (:use [midje.sweet]))



;; Given a side-effect free function f and an initial value x
;; write a function which returns an infinite lazy sequence of x, (f
;; x), (f (f x)), (f (f (f x))), etc.

(defn my-ite "Iterate implementation"
  [f x]
  (cons x (lazy-seq x (my-ite f (f x)))))

(fact
  (take 5 (my-ite #(* 2 %) 1)) => [1 2 4 8 16]
  (take 100 (my-ite inc 0)) => (take 100 (range))
  (take 9 (my-ite #(inc (mod % 3)) 1)) => (take 9 (cycle [1 2 3])))
