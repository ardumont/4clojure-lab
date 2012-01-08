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

(defn consecutive-seq "Given a sequence, return all the consecutive sequences inside the master sequence."
  [s]
  (let [r (map #(when (= (inc %) %2) [% %2]) s (rest s))]
    r))

(fact
  (consecutive-seq [1 2 3 0 1 4 7 3 1 2 3 4 5 6 7 8]) => [[1 2 3] [0 1] [1 2 3 4 5 6 7 8]])

(defn longest "Find the longest consecutive sub-sequence of increasing numbers."
  [s]
  (let [l (reduce
               (fn [m e]
                 (if (nil? (m (count e))) (assoc m (count e) e) m))
               {}
               (consecutive-seq s))]
    (l (apply max (keys l)))))

(fact
 (longest :seq) => [3 4 5]
 (provided
   (consecutive-seq :seq) => [[1 2] [3 4 5] [6 7]]))

(future-fact
  (longest [1 0 1 2 3 0 4 5]) => [0 1 2 3]
  (longest [5 6 1 3 2 7]) => [5 6]
  (longest [2 3 3 4 5]) => [3 4 5]
  (longest [7 6 5 4]) => [])

(println "--------- END 53  ----------" (java.util.Date.))
