(println "--------- END 30  ----------" (java.util.Date.))
(ns ^{:doc "http://www.4clojure.com/problem/30#prob-title"}
  my-4clojure-lab.core-30
  (:use clojure.repl)
  (:use clojure.java.javadoc)
  (:use [midje.sweet]))

(println "--------- BEGIN 30  ----------" (java.util.Date.))

;; Write a function which removes consecutive duplicates from a sequence.

(defn rem-dup "Remove duplicate entries from a sequence"
  [s]
  (reduce (fn [l x] (if (not= x (last l)) (concat l [x]) l)) [] s))

(fact 
  (apply str (rem-dup "Leeeeeerrroyyy")) => "Leroy"
  (rem-dup [1 1 2 3 3 2 2 3]) => '(1 2 3 2 3)
  (rem-dup [[1 2] [1 2] [3 4] [1 2]]) => '([1 2] [3 4] [1 2]))

(println "--------- END 30  ----------" (java.util.Date.))
