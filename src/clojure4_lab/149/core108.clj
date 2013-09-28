(ns ^{:doc "http://www.4clojure.com/problem/108#prob-title"}
  clojure4-lab.149.core108
  (:use clojure.repl)
  (:use clojure.java.javadoc)
  (:use [midje.sweet]))



;; Given any number of sequences, each sorted from smallest to largest, find the smallest number which appears in each
;; sequence. The sequences may be infinite, so be careful to search lazily.

(defn sm "Find the smallest number which appears in each sequence."
  [v & xv]
  (first
   (drop-while nil?
               (map
                (fn [e]
                  (if  (= (count xv)
                          (count
                           (filter #(= e %)
                                   (map
                                    (fn [l]
                                      (if (= e (first (drop-while #(> e %) l)))
                                        e))
                                    xv))))
                    e))
                v))))

(fact
  (sm [3 4 5]) => 3
  (sm [1 2 3 4 5 6 7] [0.5 3/2 4 19 25 67 149] [1 3 4 19] [4 17]) => 4)

(fact
 (sm (range) (range 0 100 7/6) [2 3 5 7 11 13]) => 7)

(fact
  (sm (map #(* % % %) (range))
      (filter #(zero? (bit-and % (dec %))) (range))
      (iterate inc 20)) => 64)
