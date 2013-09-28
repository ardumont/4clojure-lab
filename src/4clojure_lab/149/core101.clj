(ns ^{:doc "http://www.4clojure.com/problem/101#prob-title"}
  4clojure-lab.149.core101
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

(comment ;; first implementation using the reverse string
  (defn lv
    [a b]
    (letfn
        [(L [[f & r :as x] [h & t :as y]]
           (let [l (count x)
                 m (count y)]
             (cond (= 0 l) m
                   (= 0 m) l
                   (= f h) (L r t)
                   :else (+ 1 (min (L x t)
                                   (L r y)
                                   (L r t))))))]
      (L (-> a vec rseq) (-> b vec rseq)))))

(comment ;; improve on the first implementation to compute the count only once
  (defn lv
    [a b]
    (letfn
        [(L [[f & r :as x] l [h & t :as y] m]
           (let [l- (- l 1)
                 m- (- m 1)]
             (cond (= 0 l) m
                   (= 0 m) l
                   (= f h) (L r l- t m-)
                   :else (+ 1 (min (L x l t m-)
                                   (L r l- y m)
                                   (L r l- t m-))))))]
      (L (-> a vec rseq) (count a) (-> b vec rseq) (count b)))))

(defn lv
  "Compute the levenshtein distance"
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
      (L (count x) (count y)))))

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
  (lv '(:a :b :c :d) '(:a :d))          => 2)

(fact
 (lv "ttttattttctg" "tcaaccctaccat")   => 10
 (lv "gaattctaatctc" "caaacaaaaaattt") => 9)
