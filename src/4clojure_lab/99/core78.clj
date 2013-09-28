(ns ^{:doc "http://www.4clojure.com/problem/78#prob-title"}
  4clojure-lab.99.core78
  (:use clojure.repl)
  (:use clojure.java.javadoc)
  (:use [midje.sweet])
  (:use [clojure.pprint :only [pprint]]))



;; Reimplement the function described in "Intro to Trampoline".

;; The trampoline function takes a function f and a variable number of parameters. Trampoline calls f with any
;; parameters that were supplied. If f returns a function, trampoline calls that function with no arguments. This is
;; repeated, until the return value is not a function, and then trampoline returns that non-function value. This is
;; useful for implementing mutually recursive algorithms in a way that won't consume the stack.

(defn trampo "Trampoline implementation"
  [f & x]
  (if (fn? f) (trampo (apply f x)) f))

(fact
  (letfn [(triple [x] #(sub-two (* 3 x)))
          (sub-two [x] #(stop?(- x 2)))
          (stop? [x] (if (> x 50) x #(triple x)))]
    (trampo triple 2)) => 82

    (letfn [(my-even? [x] (if (zero? x) true #(my-odd? (dec x))))
            (my-odd? [x] (if (zero? x) false #(my-even? (dec x))))]
      (map (partial trampo my-even?) (range 6))) => [true false true false true false])
