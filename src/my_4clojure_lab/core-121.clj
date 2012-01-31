(ns ^{:doc "http://www.4clojure.com/problem/121#prob-title"}
  my-4clojure-lab.core-121
  (:use clojure.repl)
  (:use clojure.java.javadoc)
  (:use [midje.sweet]))

(println "--------- BEGIN 121  ----------" (java.util.Date.))

;; Given a mathematical formula in prefix notation, return a function that calculates the value of the formula. The
;; formula can contain nested calculations using the four basic mathematical operators, numeric constants, and symbols
;; representing variables. The returned function has to accept a single parameter containing the map of variable names
;; to their values.

(defn formula
  [f m]
  (let [g (fn g [o] (map #(if (seq? %) (g %) (m % %)) o))
        r (g f)]
    (eval r)))

(fact
  (formula '(/ a b) '{b 8 a 16}) => 2
  (formula '(/ a (+ b b)) '{b 8 a 16}) => 1
  (formula '(+ a b 2) '{a 2 b 4}) => 8
  (formula '(/ (+ x 2) (* 3 (+ y 1))) '{x 4 y 1}) => 1)

(fact
  (formula '(* (+ 2 a) (- 10 b)) '{a 1 b 8}) => 6
  (formula '(* (+ 2 a) (- 10 b)) '{b 5 a -2}) => 0
  (formula '(* (+ 2 a) (- 10 b)) '{a 2 b 11}) => -4)

(fact
  (map
   #(formula '(* (+ 2 a) (- 10 b)) %) '[{a 1 b 8} {b 5 a -2} {a 2 b 11}]) => [6 0 -4])

(println "--------- END 121  ----------" (java.util.Date.))
