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
  (let [f (fn [[u d]] (filter (fn [[x y]] (= d x)) s))
        d (f v)
        all (concat d (mapcat f d))]
    (set (map (fn [[x y]] [a y]) all))))

(fact "tc-simple"
  (tc-simple [:a :b] #{[:a :b]
                       [:b :c]
                       [:b :f]
                       [:d :e]}) => #{[:a :c] [:a :f]})

(fact
  (tc-simple [:a :b] #{[:a :b] [:b :c] [:c :e]}) => #{[:a :c] [:a :e]})

(defn tc "Transitive closure"
  [s]
  (into s (mapcat #(tc-simple % s) s)))

(fact "tc"
  (tc #{:a :b}) => #{:a :b :c :d}
  (provided
    (tc-simple :a #{:a :b}) => #{:c}
    (tc-simple :b #{:a :b}) => #{:d}))

(fact "tc"
  (tc #{[8 4] [9 3] [4 2] [27 9]}) => #{[4 2] [8 4] [8 2] [9 3] [27 9] [27 3]}

  (tc #{["cat" "man"] ["man" "snake"] ["spider" "cat"]}) => #{["cat" "man"] ["cat" "snake"] ["man" "snake"]
                                                              ["spider" "cat"] ["spider" "man"] ["spider" "snake"]}

  (tc #{["father" "son"] ["uncle" "cousin"] ["son" "grandson"]}) => #{["father" "son"] ["father" "grandson"]
                                                                      ["uncle" "cousin"] ["son" "grandson"]})

;; 4clojure

(defn tc "Transitive closure"
  [s]
  (letfn [(t [[a b :as v] s]
            (let [f (fn [[u d]] (filter (fn [[x y]] (= d x)) s))
                  d (f v)
                  all (concat d (mapcat f d))]
              (set (map (fn [[x y]] [a y]) all))))]
    (into s (mapcat #(t % s) s))))

(fact "tc"
  (tc #{[8 4] [9 3] [4 2] [27 9]}) => #{[4 2] [8 4] [8 2] [9 3] [27 9] [27 3]}

  (tc #{["cat" "man"] ["man" "snake"] ["spider" "cat"]}) => #{["cat" "man"] ["cat" "snake"] ["man" "snake"]
                                                              ["spider" "cat"] ["spider" "man"] ["spider" "snake"]}

  (tc #{["father" "son"] ["uncle" "cousin"] ["son" "grandson"]}) => #{["father" "son"] ["father" "grandson"]
                                                                      ["uncle" "cousin"] ["son" "grandson"]})