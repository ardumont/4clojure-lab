(ns ^{:doc "http://www.4clojure.com/problem/132#prob-title"}
  my-4clojure-lab.core-132
  (:use clojure.repl)
  (:use clojure.java.javadoc)
  (:use [midje.sweet]))

(println "--------- BEGIN 132  ----------" (java.util.Date.))

;; Write a function that takes a two-argument predicate, a value, and a collection; and returns a new collection where
;; the value is inserted between every two items that satisfy the predicate.

(defn insert
  [p s [f & r]]
  (if (nil? f)
    []
    (reduce (fn [l x]
              (if (p (last l) x) (conj l s x) (conj l x))) [f] r)))

;.;. Any intelligent fool can make things bigger, more complex, and more violent. It takes a touch of genius -- and a lot of
;.;. courage -- to move in the opposite direction. -- Schumacher
(fact 
  (insert < :less [1 6 7 4 3]) => '(1 :less 6 :less 7 4 3)
  (insert > :more [2]) => '(2)
  (insert #(and (pos? %) (< % %2)) :x (range 5)) => [0 1 :x 2 :x 3 :x 4]
  (empty? (insert > :more ())) => truthy)

(future-fact
    (take 12 (->> [0 1]
                (iterate (fn [[a b]] [b (+ a b)]))
                (map first)             ; fibonacci numbers
                (insert (fn [a b]        ; both even or both odd
                          (= (mod a 2) (mod b 2)))
                        :same))) => [0 1 :same 1 2 3 :same 5 8 13 :same 21])

(println "--------- END 132  ----------" (java.util.Date.))
