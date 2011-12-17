(ns ^{:doc "http://www.4clojure.com/problem/33#prob-title"}
  my-4clojure-lab.core-33
  (:use clojure.repl)
  (:use clojure.java.javadoc)
  (:use [midje.sweet]))

(println "--------- BEGIN 33  ----------" (java.util.Date.))

;; Write a function which replicates each element of a sequence a
;; certain amount of time

(defn dupn "Duplicate each element of the sequence with a certain repetition"
  [s n]
  (for [x s, y (repeat n x)] y))

(fact
  (dupn [1 2 3] 2) => '(1 1 2 2 3 3)
  (dupn [:a :b] 4) => '(:a :a :a :a :b :b :b :b)
  (dupn [4 5 6] 1) => '(4 5 6)
  (dupn [[1 2] [3 4]] 2) => '([1 2] [1 2] [3 4] [3 4])
  (dupn [44 33] 2) => [44 44 33 33])

(println "--------- END 33  ----------" (java.util.Date.))
