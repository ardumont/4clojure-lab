(ns ^{:doc "http://www.4clojure.com/problem/101#prob-title"}
  my-4clojure-lab.149.core101
  (:use [midje.sweet :only [future-fact fact contains exactly]])
  (:require [clojure.tools.trace :as t]))

;; Levenshtein Distance

;; Difficulty:	Hard
;; Topics:	seqs

;; Given two sequences x and y, calculate the Levenshtein distance of x and y, i. e. the minimum number of edits needed to transform x into y. The allowed edits are:

;; - insert a single item
;; - delete a single item
;; - replace a single item with another item

;; WARNING: Some of the test cases may timeout if you write an inefficient solution!

(defn lv
  [a b]
  (let [x (vec a)
        y (vec b)]
    (letfn
        [(L [l m]
           (let [l- (- l 1)
                 m- (- m 1)]
             (cond (= 0 l) m
                   (= 0 m) l
                   (= (x l-) (y m-)) (L l- m-)
                   :else (+ 1 (min (L l m-)
                                   (L l- m)
                                   (L l- m-))))))]
      (L (count a) (count b)))))

(fact
  (lv "kitten" "sitting")               => 3
  (lv "closure" "clojure")              => 1
  (lv "clojure" "closure")              => 1
  (lv "xyx" "xyyyx")                    => 2
  (lv "" "123456")                      => 6
  (lv "Clojure" "Clojure")              => 0
  (lv "" "")                            => 0
  (lv  [] [] )                          => 0
  (lv [1 2 3 4] [0 2 3 4 5])            => 2
  (lv '(:a :b :c :d) '(:a :d))          => 2
  (lv "ttttattttctg" "tcaaccctaccat")   => 10
  (lv "gaattctaatctc" "caaacaaaaaattt") => 9)
