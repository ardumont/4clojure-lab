(ns ^{:doc "http://www.4clojure.com/problem/96#prob-title"}
  clojure4-lab.99.core96
  (:use clojure.repl)
  (:use clojure.java.javadoc)
  (:use [midje.sweet])
  (:use [clojure.pprint :only [pprint]]))



;; Let us define a binary tree as "symmetric" if the left half of the
;; tree is the mirror image of the right half of the tree. Write a
;; predicate to determine whether or not a given binary tree is
;; symmetric. (see To Tree, or not to Tree for a reminder on the tree
;; representation we're using).

(defn mirror "Create a mirror tree"
  [t]
  (if (coll? t)
    (let [[a b c] t]
      [a (mirror c) (mirror b)])
    t))

(fact
  (mirror [:b :a :c]) => [:b :c :a]
  (mirror [:a [:b :c :d] [:e :f :g]]) => [:a [:e :g :f] [:b :d :c]])

(defn bin-tree-sym? ""
  [t]
  (= t ((fn m [[a b c :as r]] (if r [a (m c) (m b)] r)) t)))

(fact
  (bin-tree-sym? '(:a (:b nil nil)
                      (:b nil nil))) => true
  (bin-tree-sym? '(:a (:b nil nil)
                      nil)) => false
  (bin-tree-sym? '(:a (:b nil nil)
                      (:c nil nil))) => false
  (bin-tree-sym? [1 [2 nil [3 [4 [5 nil nil] [6 nil nil]] nil]]
                  [2 [3 nil [4 [5 nil nil] [6 nil nil]]] nil]]) => false
  (bin-tree-sym? [1 [2 nil [3 [4 [5 nil nil] [6 nil nil]] nil]]
                  [2 [3 nil [4 [6 nil nil] nil]] nil]]) => false
  (bin-tree-sym? [1 [2 nil [3 [4 [5 nil nil] [6 nil nil]] nil]]
                  [2 [3 nil [4 [6 nil nil] [5 nil nil]]] nil]])) => true
