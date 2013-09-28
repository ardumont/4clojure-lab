(ns ^{:doc "http://www.4clojure.com/problem/105#prob-title"}
  clojure4-lab.149.core105
  (:use clojure.repl)
  (:use clojure.java.javadoc)
  (:use [midje.sweet])
  (:use [clojure.pprint :only [pprint]]))



;; Given an input sequence of keywords and numbers, create a map such
;; that each key in the map is a keyword, and the value is a sequence
;; of all the numbers (if any) between it and the next keyword in the sequence.

(defn f "Identify keys and values"
  [v]
  (dissoc
   (reduce
    (fn [m e]
      (if (keyword? e)
        (assoc m e [] :last e)
        (assoc m (m :last) (conj (m (m :last)) e))))
    (sorted-map)
    v) :last))

(fact
  (f [])  => {}
  (f [:a 1]) => {:a [1]}
  (f [:a 1, :b 2]) => {:a [1], :b [2]}
  (f [:a 1 2 3 :b :c 4]) => {:a [1 2 3], :b [], :c [4]})
