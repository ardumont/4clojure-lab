(ns ^{:doc "http://www.4clojure.com/problem/41#prob-title"}
  my-4clojure-lab.core-41
  (:use clojure.repl)
  (:use clojure.java.javadoc)
  (:use [midje.sweet])
  (:use [clojure.pprint :only [pp pprint]]))

;; Write a function which drops every Nth item from a sequence.

(println "--------- BEGIN 41  ----------" (java.util.Date.))

(defn my-drop "drops every Nth item from a sequence."
  [n s])

(fact
  (my-drop [1 2 3 4 5 6 7 8] 3) => [1 2 4 5 7 8]
  (my-drop [:a :b :c :d :e :f] 2) => [:a :c :e]
  (my-drop [1 2 3 4 5 6] 4) => [1 2 3 5 6])

(println "--------- END 41  ----------" (java.util.Date.))

