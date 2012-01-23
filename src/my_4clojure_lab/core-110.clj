(ns ^{:doc "http://www.4clojure.com/problem/110#prob-title"}
  my-4clojure-lab.core-110
  (:use clojure.repl
        clojure.java.javadoc
        [midje.sweet]
        [clojure.pprint :only [pprint]]))

(println "--------- BEGIN 110  ----------" (java.util.Date.))

;; Write a function that returns a lazy sequence of "pronunciations" of a sequence of numbers. A pronunciation of each
;; element in the sequence consists of the number of repeating identical numbers and the number itself. For example, [1
;; 1] is pronounced as [2 1] ("two ones"), which in turn is pronounced as [1 2 1 1] ("one two, one one").

;; Your function should accept an initial sequence of numbers, and return an infinite lazy sequence of pronunciations,
;; each element being a pronunciation of the previous element.
(defn pronunciations
  [s]
  (letfn [(g [[f & r]]
            (let [m (reduce (fn [{:keys [c v l]} e]
                              (if (= v e)
                                {:c (+ 1 c) :v v :l l}
                                {:c 1 :v e :l (conj l c v)}))
                            {:c 1 :v f :l []}
                            r)]
              (conj (m :l) (m :c) (m :v))))]
    (iterate g (g s))))

(fact (take 3 (pronunciations [1])) => [[1 1] [2 1] [1 2 1 1]])

(fact (first (pronunciations [1 1 1 4 4])) => [3 1 2 4])

(fact (nth (pronunciations [1]) 6) => [1 1 1 3 2 1 3 2 1 1])

(fact (count (nth (pronunciations [3 2]) 15)) => 338)

(println "--------- END 110  ----------" (java.util.Date.))
