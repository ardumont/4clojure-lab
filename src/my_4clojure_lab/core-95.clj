(ns ^{:doc "http://www.4clojure.com/problem/95#prob-title"}
  my-4clojure-lab.core-95
  (:use clojure.repl)
  (:use clojure.java.javadoc)
  (:use [midje.sweet]))

(println "--------- BEGIN 95  ----------" (java.util.Date.))

;; Write a predicate which checks whether or not a given sequence
;; represents a binary tree. Each node in the tree must have a value,
;; a left child, and a right child.

(defn bin-tree? "Is this tree a binary one?"
  [t]
  (or
   (nil? t)
   (and
    (coll? t)
    (= 3 (count t))
    (every? bin-tree? (next t)))))

(fact 
  (bin-tree? '(:a (:b nil nil) nil)) => true
  (bin-tree? '(:a (:b nil nil))) => false
  (bin-tree? [1 nil [2 [3 nil nil] [4 nil nil]]]) => true
  (bin-tree? [1 [2 nil nil] [3 nil nil] [4 nil nil]]) => false
  (bin-tree? [1 [2 [3 [4 nil nil] nil] nil] nil]) => true
  (bin-tree? [1 [2 [3 [4 false nil] nil] nil] nil]) => false
  (bin-tree? '(:a nil ())) => false)

(println "--------- END 95  ----------" (java.util.Date.))
