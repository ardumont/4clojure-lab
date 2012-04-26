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

(defn pal? "Naive implemenation - Is the sequence a palindrome?"
  [s]
  (= (seq s) (reverse (seq s))))

(fact "pal?"
  (pal? [1 2 3]) => falsey
  (pal? [1 2 1]) => truthy
  (pal? "racecar") => truthy
  (pal? [:foo :bar :foo]) => truthy
  (pal? '(1 1 3 3 1 1))  => truthy
  (pal? '(:a :b :c)) => falsey)

(defn pal? "Less naive implementation - Is the sequence a palindrome?"
  [s]
  (or (nil? s) (and (= (first s) (last s)) (pal? (butlast (rest s))))))

(fact "pal?"
  (pal? [1 2 3]) => falsey
  (pal? [1 2 1]) => truthy
  (pal? "racecar") => truthy
  (pal? [:foo :bar :foo]) => truthy
  (pal? '(1 1 3 3 1 1))  => truthy
  (pal? '(:a :b :c)) => falsey)

(defn f "first implementation"
  ([n] (f n []))
  ([n s]
     (let [p (fn p [s] (= (seq s) (reverse s)))
           l (lazy-seq (f (+ 1 n)))]
       (if (p (str n)) (cons n l) l))))

(defn d "decode"
  [n] (if (< n 10) [n] (conj (d (quot n 10)) (rem n 10))))

(fact "d"
  (d 100) => [1 0 0]
  (d 132456677) => [1 3 2 4 5 6 6 7 7])

(defn e "encode"
  [s]
  (reduce #(+ (* 10 %) %2) 0 s))

(fact "encode"
  (e [1 2 3]) => 123)

(defn p "pal?"
  [s]
  (let [n (count s)
        h (quot n 2)
        l (for [x (range h)
                :while (= (s x) (s (- n x 1)))]
            x)]
    (= h (count l))))

(fact "p"
  (p [1]) => true
  (p [1 1 4 1 2]) => false
  (p [1 1 1 1 1]) => true
  (p [1 1 1 1 1 1]) => true
  (p [1 2 3 4 6 6 4 3 2 1]) => true
  (p [1 2 3 4 5 5 0 0 0 1]) => false)

(defn f
  ([n] (f n []))
  ([n s]
     (letfn [(d [n] (if (< n 10) [n] (conj (d (quot n 10)) (rem n 10))))
             (p [s]
               (let [n (count s)
                     h (quot n 2)
                     l (for [x (range h)
                             :while (= (s x) (s (- n x 1)))]
                         x)]
                 (= h (count l))))
             (g [n s]
               (let [l (lazy-seq (g (+ 1 n) s))]
                 (if (p (d n)) (cons n l) l)))]
       (g n s))))

(defn nxpal "Next palindrome"
  [n]
  (let [c concat
        r reverse
        s (d n)
        l (count s)
        h (quot l 2)
        fh (take h s)
        ifh (e fh)
        nfh (-> fh e inc d)
        sh (take-last h s)
        ish (e sh)
        m (s h)
        e? (even? l)
        o? (odd? l)]
    (e
     (cond
      (and e? (>= ifh ish))  (c fh (r fh))
      e?                     (c nfh (r nfh))
      (and o? (>= ifh ish))   (c fh [m] (r fh))
      (and o? (< m 9))       (c fh [(inc m)] (r fh))
      :else                  (c nfh [0] (r nfh))))))

(fact
  (nxpal 1234550000) => 1234664321)

(fact
  (nxpal 99) => 99
  (nxpal 100) => 101
  (nxpal 9999) => 9999
  (nxpal 10000) => 10001)

(fact "odd length - edge cases - switch to even length"
  (nxpal 9) => 9
  (nxpal 999) => 999)

(fact "odd length - 9"
  (nxpal 193) => 202
  (nxpal 191) => 191
  (nxpal 290) => 292
  (nxpal 295) => 303)

(fact "odd length normal cases"
  (nxpal 121) => 121
  (nxpal 120) => 121
  (nxpal 161) => 161
  (nxpal 162) => 171
  (nxpal 13631) => 13731)

(fact "nxpal"
  (nxpal 0) => 0
  (nxpal 1) => 1
  (nxpal 11) => 11
  (nxpal 1111) => 1111
  (nxpal 23) => 33
  (nxpal 35) => 44
  (nxpal 10) => 11)

(defn gen-pal "Generate palindromes."
  [n]
  (iterate nxpal (if (pal? (d n)) n (nxpal n))))

(future-fact
  (take 26 (gen-pal 0)) => [0 1 2 3 4 5 6 7 8 9
                            11 22 33 44 55 66 77 88 99
                            101 111 121 131 141 151 161])

(future-fact
  (take 16 (gen-pal 162)) => [171 181 191 202
                              212 222 232 242
                              252 262 272 282
                              292 303 313 323])
(future-fact
  (take 6 (gen-pal 1234550000)) => [1234554321 1234664321 1234774321
                                    1234884321 1234994321 1235005321])

(future-fact
  (first (gen-pal (* 111111111 111111111))) => (* 111111111 111111111))

(future-fact
  (=  (set (take 199 (gen-pal 0))) (set (map #(first (gen-pal %)) (range 0 10000)))) => true)

(future-fact
  (apply < (take 6666 (gen-pal 9999999))) => true)

(future-fact
  (nth (gen-pal 0) 10101) => 9102019)