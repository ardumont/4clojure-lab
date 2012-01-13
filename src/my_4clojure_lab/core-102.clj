(ns ^{:doc "http://www.4clojure.com/problem/102#prob-title"}
  my-4clojure-lab.core-102
  (:use clojure.repl)
  (:use clojure.java.javadoc)
  (:use [midje.sweet])
  (:use [clojure.pprint :only [pprint]]))

(println "--------- BEGIN 102  ----------" (java.util.Date.))

;; When working with java, you often need to create an object with
;; fieldsLikeThis, but you'd rather work with a hashmap that has
;; :keys-like-this until it's time to convert. Write a function which
;; takes lower-case hyphen-separated strings and converts them to
;; camel-case strings.

(defn camel-case "Takes lower-case hyphen-separated strings and converts them to camel-case strings."
  [s]
  (let [[f & r] (re-seq #"\w+" s)
        v (map clojure.string/capitalize r)]
    (apply str (concat f v))))

(fact 
  (camel-case "something") => "something"
  (camel-case "multi-word-key") => "multiWordKey"
  (camel-case "leaveMeAlone") => "leaveMeAlone")

(println "--------- END 102  ----------" (java.util.Date.))
