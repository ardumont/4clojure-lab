(ns ^{:doc "http://www.4clojure.com/problem/121#prob-title"}
  4clojure-lab.149.core121
  (:use clojure.repl)
  (:use clojure.java.javadoc)
  (:use [midje.sweet :only [fact]]))

;; Given a mathematical formula in prefix notation, return a function that calculates the value of the formula. The
;; formula can contain nested calculations using the four basic mathematical operators, numeric constants, and symbols
;; representing variables. The returned function has to accept a single parameter containing the map of variable names
;; to their values.

(defn formula
  [f]
  (fn [m]
    (let [n (assoc m '+ + '- - '/ / '* *)
          g (fn g [f]
              (let [[o & a]
                    (map #(if (seq? %) (g %) (n % %)) f)]
                (apply o a)))]
      (g f))))

(fact
  ((formula '(/ a b)) '{b 8 a 16}) => 2
  ((formula '(+ a b 2)) '{a 2 b 4}) => 8
  (map (formula '(* (+ 2 a) (- 10 b))) '[{a 1 b 8} {b 5 a -2} {a 2 b 11}]) => [6 0 -4]
  ((formula '(/ (+ x 2) (* 3 (+ y 1)))) '{x 4 y 1}) => 1)
