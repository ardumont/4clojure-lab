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
    (fn [[m v] e]
      ;; if the previous key (dec e) exists then e became the new key and the
      ;; value is the seq we read until now with e as the last
      ;; element, else we reinit the map with e as key and the
      ;; sequence we read is just e
      (let [n {e (if (m (dec e))
                   (conj (m (dec e)) e)
                   [e])}]
        [n
         ;; if we now have a sequence with a greater length, we take
         ;; this one, that is we retrieve the first value of vals from
         ;; the map and copy it inside the vector of the longest sequence
         (let [f (first (vals n))]
           (if (and (<= 2 (count f)) (< (count v) (count f)))
             f
             v))]))
    [{} []]
    s)))

(fact
  (longest [1 0 1 2 3 0 4 5]) => [0 1 2 3]
  (longest [5 6 1 3 2 7]) => [5 6]
  (longest [2 3 3 4 5]) => [3 4 5]
  (longest [7 6 5 4]) => [])

(println "--------- END 53  ----------" (java.util.Date.))
