(ns ^{:doc "http://www.4clojure.com/problem/150#prob-title"}
  my-4clojure-lab.199.core150
  (:use clojure.repl)
  (:use clojure.java.javadoc)
  (:use [midje.sweet]))



;; A palindromic number is a number that is the same when written forwards or backwards (e.g., 3, 99, 14341).

;; Write a function which takes an integer n, as its only argument, and returns an increasing lazy sequence of all
;; palindromic numbers that are not less than n.

;; The most simple solution will exceed the time limit!

;; from 4clojure.com/27 exercise
(defn pal? "Is the sequence a palindrome?"
  [s]
  (or (nil? s) (and (= (first s) (last s)) (pal? (butlast (rest s))))))

(fact
  (pal? [1 2 3]) => falsey
  (pal? [1 2 1]) => truthy
  (pal? "racecar") => truthy
  (pal? [:foo :bar :foo]) => truthy
  (pal? '(1 1 3 3 1 1))  => truthy
  (pal? '(:a :b :c)) => falsey)

(defn g
  ([n] (g n []))
  ([n s]
     (let [p (fn p [s] (= (seq s) (reverse s)))
           l (lazy-seq (g (inc n)))]
       (if (p (str n)) (cons n l) l))))

(defn f
  ([n] (f n []))
  ([n s]
     (let [p (fn p [s] (or (nil? s) (and (= (first s) (last s)) (p (butlast (rest s))))))
           l (lazy-seq (f (inc n)))]
       (if (p (str n)) (cons n l) l))))

(fact
  (take 26 (f 0)) => [0 1 2 3 4 5 6 7 8 9
                      11 22 33 44 55 66 77 88 99
                      101 111 121 131 141 151 161])

(fact
  (take 16 (f 162)) => [171 181 191 202
                        212 222 232 242
                        252 262 272 282
                        292 303 313 323])

(fact
  (take 6 (f 1234550000)) => [1234554321 1234664321 1234774321
                              1234884321 1234994321 1235005321])

(fact
  (first (f (* 111111111 111111111))) => (* 111111111 111111111))

(fact
    (set (take 199 (f 0))) => (set (map #(first (f %)) (range 0 10000))))

(future
  (apply < (take 6666 (f 9999999))) => true)

(future
  (nth (f 0) 10101) => 9102019)


