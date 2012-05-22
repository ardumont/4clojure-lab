(ns ^{:doc "http://www.4clojure.com/problem/50#prob-title"}
  my-4clojure-lab.99.core50
  (:use clojure.repl)
  (:use clojure.java.javadoc)
  (:use [midje.sweet])
  (:use [clojure.pprint :only [pprint]]))

;; Transitive Closure
 
;; Difficulty:	Hard
;; Topics:	set-theory

;; Write a function which generates the transitive closure of a binary
;; relation. The relation will be represented as a set of 2 item
;; vectors.

(defn tc-simple "Transitive closure for one element in the set"
  [[a b :as v] s]
  (let [f (fn [b] (filter (fn [[x y]] (= b x)) s))
        all (map #(f (second %)) (f b))]
    (set (map (fn [[x y]] [a y]) all))))

(defn tc-simple "Transitive closure for one element in the set"
  [[a b :as v] s]
  (set (map (fn [[x y]] [a y]) (filter (fn [[x y]] (= b x)) s))))

(fact "tc-simple"
  (tc-simple [:a :b] #{[:b :c] [:d :e] [:a :b] [:b :f]}) => #{[:a :c] [:a :f]}
  (tc-simple [:a :b] #{[:a :b] [:b :c] [:c :e]}) => #{[:a :c] [:a :e]})

(defn tc "Transitive closure"
  [s]
  (set (concat s (mapcat #(tc-simple % s) s))))

(fact "tc"
  (tc #{[8 4] [9 3] [4 2] [27 9]}) => #{[4 2] [8 4] [8 2] [9 3] [27 9] [27 3]}

  (tc #{["cat" "man"] ["man" "snake"] ["spider" "cat"]}) => #{["cat" "man"] ["cat" "snake"] ["man" "snake"]
                                                              ["spider" "cat"] ["spider" "man"] ["spider" "snake"]}

  (tc #{["father" "son"] ["uncle" "cousin"] ["son" "grandson"]}) => #{["father" "son"] ["father" "grandson"]
                                                                      ["uncle" "cousin"] ["son" "grandson"]})