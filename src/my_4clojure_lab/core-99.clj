(ns ^{:doc "http://www.4clojure.com/problem/99#prob-title"}
  my-4clojure-lab.core-99
  (:use clojure.repl)
  (:use clojure.java.javadoc)
  (:use [midje.sweet]))

(println "--------- BEGIN 99  ----------" (java.util.Date.))

;; Write a function which multiplies two numbers and returns the result as a sequence of its digits.

(defn pdt "Function which multiplies two numbers and returns the result as a sequence of its digits."
  [a b]
  (let [z (zipmap "0123456789" (range 10))]
    (map z (reduce conj [] (str (* a b))))))

(fact
  (pdt 1 1) => [1]
  (pdt 99 9) => [8 9 1]
  (pdt 999 99) => [9 8 9 0 1])

(println "--------- END 99  ----------" (java.util.Date.))
