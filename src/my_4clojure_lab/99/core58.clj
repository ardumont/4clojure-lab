(ns ^{:doc "http://www.4clojure.com/problem/58#prob-title"}
  my-4clojure-lab.99.core58
  (:use clojure.repl)
  (:use clojure.java.javadoc)
  (:use [midje.sweet])
  (:use [clojure.pprint :only [pprint]]))



;; Write a function which allows you to create function compositions.
;; The parameter list should take a variable number of functions, and
;; create a function applies them from right-to-left.

(defn mcomp "Comp implementation."
  ([f g] (fn [v] (-> v g f)))
  ([f g h] (fn [& v] (f (g (apply h v))))))

(fact "Function with vector as argument"
  ((mcomp rest reverse) [1 2 3 4]) => [3 2 1]
  ((mcomp (partial + 3) second) [1 2 3 4]) => 5)

(fact "Function with a list of arguments"
  ((mcomp zero? #(mod % 8) +) 3 5 7 9) => truthy
  ((mcomp #(.toUpperCase %) #(apply str %) take) 5 "hello world") => "HELLO")

