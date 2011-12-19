(ns ^{:doc "http://www.4clojure.com/problem/63#prob-title"}
  my-4clojure-lab.core-63
  (:use clojure.repl)
  (:use clojure.java.javadoc)
  (:use [midje.sweet]))

(println "--------- BEGIN 63  ----------" (java.util.Date.))

;; Given a function f and a sequence s, write a function which returns
;; a map. 
;; The keys should be the values of f applied to each item in s.
;; The value at each key should be a vector of corresponding items in
;; the order they appear in s.

(defn grp-by "Group by"
  [f v]
  (reduce (fn [m c] (assoc m (f c) (conj (m (f c) []) c))) {} v))

(fact
  (grp-by #(> % 5) #{1 3 6 8})                    => {false [1 3], true [6 8]}
  (grp-by #(apply / %) [[1 2] [2 4] [4 6] [3 6]]) => {1/2 [[1 2] [2 4] [3 6]], 2/3 [[4 6]]}
  (grp-by count [[1] [1 2] [3] [1 2 3] [2 3]])    => {1 [[1] [3]], 2 [[1 2] [2 3]], 3 [[1 2 3]]})

(println "--------- END 63  ----------" (java.util.Date.))
