(ns clojure4-lab.149.core113
  "http://www.4clojure.com/problem/113#prob-title"
  (:use [clojure.repl]
        [clojure.java.javadoc])
  (:require [midje.sweet :as m]))

(defn tmp
  [& s]
  (reify clojure.lang.Seqable
    (seq [this] (-> s distinct seq))
    (toString [this] (->> s sort (clojure.string/join ", ")))))

(m/fact
  (str (tmp 2 1 3)) => "1, 2, 3"

  (seq (tmp 2 1 3)) => '(2 1 3)

  (seq (tmp 2 1 3 3 1 2)) => '(2 1 3)

  (seq (apply tmp (repeat 5 1))) => '(1)

  (str (apply tmp (repeat 5 1))) => "1, 1, 1, 1, 1"

  (seq (tmp)) => nil

  (str (tmp)) => "")
