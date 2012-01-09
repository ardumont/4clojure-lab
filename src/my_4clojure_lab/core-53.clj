(ns ^{:doc "http://www.4clojure.com/problem/53#prob-title"}
  my-4clojure-lab.core-53
  (:use clojure.repl)
  (:use clojure.java.javadoc)
  (:use [midje.sweet])
  (:use [clojure.pprint :only [pprint]]))

(println "--------- BEGIN 53  ----------" (java.util.Date.))

;; Given a vector of integers, find the longest consecutive
;; sub-sequence of increasing numbers. If two sub-sequences have
;; the same length, use the one that occurs first. An increasing
;; sub-sequence must have a length of 2 or greater to qualify.

(defn longest "Find the longest consecutive sub-sequence of increasing numbers."
  [s]
  (second
   (reduce
    (fn [[m v :as r] e]
      (if (m (dec e))
        [{e (conj (m (dec e)) e)} v]
        (if (< (count v) (count (nth (vals m) 0)))
          [{e #{e}} (nth (vals m) 0)]
          [{e #{e}} (into [] v)])))
    [{} []]
    s)))

(fact
  (longest [1 0 1 2 3 0 4 5]) => [0 1 2 3]
  (longest [5 6 1 3 2 7]) => [5 6]
  (longest [2 3 3 4 5]) => [3 4 5]
  (longest [7 6 5 4]) => [])

(println "--------- END 53  ----------" (java.util.Date.))
