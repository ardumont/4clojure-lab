(ns ^{:doc "http://www.4clojure.com/problem/96#prob-title"}
  my-4clojure-lab.core-96
  (:use clojure.repl)
  (:use clojure.java.javadoc)
  (:use [midje.sweet]))

(println "--------- BEGIN 96  ----------" (java.util.Date.))

;; Let us define a binary tree as "symmetric" if the left half of the
;; tree is the mirror image of the right half of the tree. Write a
;; predicate to determine whether or not a given binary tree is
;; symmetric. (see To Tree, or not to Tree for a reminder on the tree
;; representation we're using).

(defn bin-tree-sym? ""
  [t]
    (or
   (nil? t)
   (and
    (coll? t)
    (= 3 (count t))
    (= (set (flatten (nth t 1))) (set (flatten (nth t 2))))
    (every? bin-tree-sym? (next t)))))

(fact
  (bin-tree-sym? '(:a (:b nil nil)
                      (:b nil nil))) => true
  (bin-tree-sym? '(:a (:b nil nil)
                      nil)) => false
  (bin-tree-sym? '(:a (:b nil nil)
                      (:c nil nil))) => false
  (bin-tree-sym? [1 [2 nil [3 [4 [5 nil nil] [6 nil nil]] nil]]
                    [2 [3 nil [4 [6 nil nil] [5 nil nil]]] nil]]) => true
  (bin-tree-sym? [1 [2 nil [3 [4 [5 nil nil] [6 nil nil]] nil]]
                  [2 [3 nil [4 [5 nil nil] [6 nil nil]]] nil]]) => false
  (bin-tree-sym? [1 [2 nil [3 [4 [5 nil nil] [6 nil nil]] nil]]
                  [2 [3 nil [4 [6 nil nil] nil]] nil]]) => false)

(println "--------- END 96  ----------" (java.util.Date.))
