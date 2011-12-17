(ns ^{:doc "http://www.4clojure.com/problem/28#prob-title"}
  my-4clojure-lab.core-28
  (:use clojure.repl)
  (:use clojure.java.javadoc)
  (:use [midje.sweet]))

(println "--------- BEGIN 28  ----------" (java.util.Date.))

;; Write a function which flattens a sequence.

(defn my-flatten "flatten implementation"
  [s]
  (reduce
   #(if (coll? %2)
      (concat % (my-flatten %2))
      (concat % [%2]))
   []
   s))

(fact
  (my-flatten '((1 2) 3 [4 [5 6]])) => '(1 2 3 4 5 6)
  (my-flatten ["a" ["b"] "c"]) => '("a" "b" "c")
  (my-flatten '((((:a))))) => '(:a))

(println "--------- END 28  ----------" (java.util.Date.))
