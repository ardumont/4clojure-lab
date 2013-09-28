(ns ^{:doc "http://www.4clojure.com/problem/112#prob-title"}
  clojure4-lab.149.core112
  (:use clojure.repl)
  (:use clojure.java.javadoc)
  (:use [midje.sweet]))



;; Create a function which takes an integer and a nested collection of integers as arguments. Analyze the elements of
;; the input collection and return a sequence which maintains the nested structure, and which includes all elements
;; starting from the head whose sum is less than or equal to the input integer.

(defn filter-seq
  [n s]
  (nth ((fn g [n s]
           (loop [v [] t n r s]
             (if (empty? r)
               [v t]
               (let [e (first r)]
                 (if (coll? e)
                   (let [l (g t e)
                         sv (nth l 0)]
                     (recur (if (not (empty? sv)) (conj v sv) v) (nth l 1) (rest r)))
                   (if (<= 0 (- t e))
                     (recur (conj v e) (- t e) (rest r))
                     [v t]))))))
        n s) 0))

(fact "OK"
  (filter-seq 10 [1 2 [3 [4 5] 6] 7]) => '(1 2 (3 (4)))
  (filter-seq 0 [0 0 [0 [0]]]) => '(0 0 (0 (0)))
  (filter-seq 1 [[[[[1]]]]]) => '(((((1)))))
  (filter-seq 30 [1 2 [3 [4 [5 [6 [7 8]] 9]] 10] 11]) => '(1 2 (3 (4 (5 (6 (7))))))
  (filter-seq 1 [-10 [1 [2 3 [4 5 [6 7 [8]]]]]]) => '(-10 (1 (2 3 (4))))
  (filter-seq 0 [1 2 [3 [4 5] 6] 7]) => '()
  (filter-seq 9 (range)) => '(0 1 2 3))
