(ns ^{:doc "http://www.4clojure.com/problem/65#prob-title"}
  my-4clojure-lab.core-65
  (:use clojure.repl)
  (:use clojure.java.javadoc)
  (:use [midje.sweet])
  (:use [clojure.pprint :only [pprint]]))

(println "--------- BEGIN 65  ----------" (java.util.Date.))

;; Clojure has many sequence types, which act in subtly different ways.
;; The core functions typically convert them into a uniform "sequence" type
;; and work with them that way, but it can be important to understand the behavioral
;; and performance differences so that you know which kind is appropriate for your application.

;; Write a function which takes a collection and returns one of :map, :set, :list, or :vector
;; - describing the type of collection it was given.
;; You won't be allowed to inspect their class or use the built-in predicates like list?
;; - the point is to poke at them and understand their behavior.

(defn which-seq
  [s]
  (let [l {:c :d}
        k {:a :b}
        t (conj s l k)
        ft (first t)
        lt (last t)
        fs (first s)
        ls (last s)]
    (cond
     (or (= s #{}) (and (= lt ls)
                        (= ft fs))) :set
     (or (= t [l k]) (and (= fs ft)
                          (= k lt))) :vector
     (or (= t (conj () l k)) (and (= k ft)
                                  (= ls lt))) :list
     :else :map)))

(fact "Tests ok"
  (which-seq [1 2 3 4 5 6]) => :vector
  (which-seq (range (rand-int 20))) => :list
  (which-seq {:a 1, :b 2})  =>  :map
  (which-seq #{10 (rand-int 5)}) => :set)

;.;. {:a :b, :c :d}
;.;. #{{:c :d} {:a :b}}
;.;. [{:c :d} {:a :b}]
;.;. ({:a :b} {:c :d})
;.;. 
;.;. [31mFAIL[0m at (NO_SOURCE_FILE:1)
;.;.     Expected: [:map :set :vector :list]
;.;.       Actual: (:map :set :vector :map)
(fact "Test in passing"
     (map which-seq [{} #{} [] ()])   =>  [:map :set :vector :list])

(println "--------- END 65  ----------" (java.util.Date.))
