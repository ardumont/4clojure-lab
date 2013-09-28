(ns ^{:doc "http://www.4clojure.com/problem/38#prob-title"}
  clojure4-lab.49.core38
  (:use clojure.repl)
  (:use clojure.java.javadoc)
  (:use [midje.sweet]))

;; Write a function which returns the Nth element from a sequence.



(defn my-max "max"
  [& xv]
  (reduce (fn [a b] (if (< a b) b a)) xv))

;.;. The next function taunts you still. Will you rise to the challenge? --
;.;. anonymous
(fact
  (my-max 1 8 3 4) => 8
  (my-max 30 20) => 30
  (my-max 45 67 11) => 67
  (my-max 45 44 110) => 110)
