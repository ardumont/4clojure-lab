(ns ^{:doc "http://www.4clojure.com/problem/54#prob-title"}
  clojure4-lab.99.core54
  (:use clojure.repl)
  (:use clojure.java.javadoc)
  (:use [midje.sweet])
  (:use [clojure.pprint :only [pprint]]))



;; Write a function which returns a sequence of lists of x items each.
;; Lists of less than x items should not be returned.

(defn mpart "Returns a sequence of lists of x items each"
  [x s]
  (if (<= x (count s))
    (cons (take x s) (mpart x (drop x s)))))

(fact
  (mpart 3 (range 9)) => '((0 1 2) (3 4 5) (6 7 8))
  (mpart 2 (range 8)) => '((0 1) (2 3) (4 5) (6 7))
  (mpart 3 (range 8)) => '((0 1 2) (3 4 5)))
