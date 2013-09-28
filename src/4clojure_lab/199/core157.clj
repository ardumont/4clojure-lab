(ns ^{:doc "http://www.4clojure.com/problem/157#prob-title"}
  4clojure-lab.199.core157
  (:use clojure.repl)
  (:use clojure.java.javadoc)
  (:use [midje.sweet]))

;; Indexing sequence

;; Difficulty:	Easy
;; Topics:	seqs

;; Transform a sequence into a sequence of pairs containing the original elements along with their index.

(defn sp "Transform a sequence into a sequence of pairs containing the original elements along with their index."
  [s]
  (map-indexed (fn [i e] [e i]) s))

(fact
  (sp [:a :b :c]) => [[:a 0] [:b 1] [:c 2]]
  (sp [0 1 3]) => '((0 0) (1 1) (3 2))
  (sp [[:foo] {:bar :baz}]) => [[[:foo] 0] [{:bar :baz} 1]])
