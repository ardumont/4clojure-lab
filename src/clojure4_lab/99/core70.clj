(ns ^{:doc "http://www.4clojure.com/problem/70#prob-title"}
  clojure4-lab.99.core70
  (:use clojure.repl)
  (:use clojure.java.javadoc)
  (:use [midje.sweet])
  (:use [clojure.pprint :only [pprint]]))



;; Write a function that splits a sequence up into a sorted list of
;; words. Capitalization should not affect sort order and punctuation
;; should be ignored.

(defn wsort "Splits a sequence up into a sorted list of words."
  [s]
  (sort-by #(.toLowerCase %) (re-seq #"\w+" s)))

(fact
  (wsort "Have a nice day.") => ["a" "day" "Have" "nice"]
  (wsort "Clojure is a fun language!") => ["a" "Clojure" "fun" "is" "language"]
  (wsort "Fools fall for foolish follies.") => ["fall" "follies" "foolish" "Fools" "for"])
