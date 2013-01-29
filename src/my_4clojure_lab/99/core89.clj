(ns my-4clojure-lab.149.core89
  "http://www.4clojure.com/problem/89#prob-title"
  (:use [midje.sweet :only [future-fact fact contains exactly]])
  (:require [clojure.tools.trace :as t]))
;; Graph Tour

;; Difficulty:	Hard
;; Topics:	graph-theory


;; Starting with a graph you must write a function that returns true if it is possible to make a tour of the graph in which every edge is visited exactly once.

;; The graph is represented by a vector of tuples, where each tuple represents a single edge.

;; The rules are:

;; - You can start at any node.
;; - You must visit each edge exactly once.
;; - All edges are undirected.

(defn graph
  [])

(fact (graph [[:a :b]])                                                                                 => true)

(fact (graph [[:a :a] [:b :b]])                                                                         => false)

(fact (graph [[:a :b] [:a :b] [:a :c] [:c :a] [:a :d] [:b :d] [:c :d]])                                 => false)

(fact (graph [[1 2] [2 3] [3 4] [4 1]])                                                                 => true)

(fact (graph [[:a :b] [:a :c] [:c :b] [:a :e] [:b :e] [:a :d] [:b :d] [:c :e] [:d :e] [:c :f] [:d :f]]) => true)

(fact (graph [[1 2] [2 3] [2 4] [2 5]])                                                                 => false)
