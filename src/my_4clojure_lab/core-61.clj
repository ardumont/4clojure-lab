(ns ^{:doc "http://www.4clojure.com/problem/61#prob-title"}
  my-4clojure-lab.core-61
  (:use clojure.repl)
  (:use clojure.java.javadoc)
  (:use [midje.sweet]))

(println "--------- BEGIN 61  ----------" (java.util.Date.))

;; Write a function which takes a vector of keys and a vector of values and constructs a map from them.

(defn my-zipmap "takes a vector of keys and a vector of values and constructs a map from them."
  [s l]
  (reduce (fn [m [k v]] (assoc m k v)) {} (map (fn [k v] [k v]) s l)))

(fact 
  (my-zipmap [:a :b :c] [1 2 3]) => {:a 1, :b 2, :c 3}
  (my-zipmap [1 2 3 4] ["one" "two" "three"]) => {1 "one", 2 "two", 3 "three"}
  (my-zipmap [:foo :bar] ["foo" "bar" "baz"]) => {:foo "foo", :bar "bar"})

(println "--------- END 61  ----------" (java.util.Date.))