(ns ^{:doc "http://www.4clojure.com/problem/132#prob-title"}
  my-4clojure-lab.149.core132
  (:use clojure.repl)
  (:use clojure.java.javadoc)
  (:use [midje.sweet]))



;; Write a function that takes a two-argument predicate, a value, and a collection; and returns a new collection where
;; the value is inserted between every two items that satisfy the predicate.

;;  (map first (partition-by identity (mapcat (fn [[a b]] (if (p a b) [a n b] [a b])) (partition 2 1 s))))

(defn insert
  [p n [f & r :as s]]
  (if (seq s)
    (concat [f] (mapcat #(if (p % %2) [n %2] [%2]) s r))))

;.;. Any intelligent fool can make things bigger, more complex, and more violent. It takes a touch of genius -- and a lot of
;.;. courage -- to move in the opposite direction. -- Schumacher
(fact 
  (insert < :less [1 6 7 4 3]) => '(1 :less 6 :less 7 4 3)
  (insert > :more [2]) => '(2)
  (insert #(and (pos? %) (< % %2)) :x (range 5)) => [0 1 :x 2 :x 3 :x 4]
  (empty? (insert > :more ())) => truthy
  (take 12 (->> [0 1]
                (iterate (fn [[a b]] [b (+ a b)]))
                (map first)             ; fibonacci numbers
                (insert (fn [a b]        ; both even or both odd
                          (= (mod a 2) (mod b 2)))
                        :same))) => [0 1 :same 1 2 3 :same 5 8 13 :same 21])


