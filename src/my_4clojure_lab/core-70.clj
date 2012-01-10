(ns ^{:doc "http://www.4clojure.com/problem/70#prob-title"}
  my-4clojure-lab.core-70
  (:use clojure.repl)
  (:use clojure.java.javadoc)
  (:use [midje.sweet])
  (:use [clojure.pprint :only [pprint]]))

(println "--------- BEGIN 70  ----------" (java.util.Date.))

;; Write a function that splits a sequence up into a sorted list of
;; words. Capitalization should not affect sort order and punctuation
;; should be ignored.

(defn wsort "Splits a sequence up into a sorted list of words."
  [s]
  (sort
   #(compare (.toLowerCase %) (.toLowerCase %2))
   (filter #(not= "" %) (re-seq #"[a-zA-Z]*" s))))

(fact
  (wsort "Have a nice day.") => ["a" "day" "Have" "nice"]
  (wsort "Clojure is a fun language!") => ["a" "Clojure" "fun" "is" "language"]
  (wsort "Fools fall for foolish follies.") => ["fall" "follies" "foolish" "Fools" "for"])

(println "--------- END 70  ----------" (java.util.Date.))