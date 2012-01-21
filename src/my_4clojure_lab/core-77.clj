(ns ^{:doc "http://www.4clojure.com/problem/77#prob-title"}
  my-4clojure-lab.core-77
  (:use clojure.repl)
  (:use clojure.java.javadoc)
  (:use [midje.sweet])
  (:use [clojure.pprint :only [pprint]]))

(println "--------- BEGIN 77  ----------" (java.util.Date.))

;; Write a function which finds all the anagrams in a vector of words. A word x is an anagram of word y if all the
;; letters in x can be rearranged in a different order to form y. Your function should return a set of sets, where each
;; sub-set is a group of words which are anagrams of each other.

;; Each sub-set should have at least two words. Words without any anagrams should not be included in the result.

(defn anagram "Anagram finder"
  [v]
  (->> v (group-by set) vals (map set) (filter next) set))

(fact
  (anagram ["meat" "mat" "team" "mate" "eat"]) => #{#{"meat" "team" "mate"}})

;.;. The highest reward for a man's toil is not what he gets for it but what he becomes by it. -- Ruskin
(fact
  (anagram ["veer" "lake" "item" "kale" "mite" "ever"]) => #{#{"veer" "ever"} #{"lake" "kale"} #{"mite" "item"}})

(println "--------- END 77  ----------" (java.util.Date.))
