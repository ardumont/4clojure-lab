(ns ^{:doc "http://www.4clojure.com/problem/114#prob-title"}
  clojure4-lab.149.core114
  (:use clojure.repl
        clojure.java.javadoc
        [midje.sweet]
        [clojure.pprint :only [pprint]]))



;; take-while is great for filtering sequences, but it is limited: you can only examine a single item of the sequence at a
;; time. What if you need to keep track of some state as you go over the sequence?
;; Write a function which accepts an integer n, a predicate p, and a sequence. It should return a lazy
;; sequence of items in the list up to, but not including, the nth item that satisfies the predicate.

(defn mtake-while "Take-while improved implementation"
  [n p s]
  ((reduce (fn [{:keys [c l] :as m} e]
              (let [nc (if (p e) (inc c) c)
                    nl (conj l e)]
                (assoc m :c nc :l nl nc nl))) {:c 1 :l []} s) n))

(fact
  (mtake-while 4 #(= 2 (mod % 3)) [2 3 5 7 11 13 17 19 23]) => [2 3 5 7 11 13]
  (mtake-while 3 #(some #{\i} %) ["this" "is" "a" "sentence" "i" "wrote"]) => ["this" "is" "a" "sentence"]
  (mtake-while 1 #{"a"} ["this" "is" "a" "sentence" "i" "wrote"]) => ["this" "is"])
