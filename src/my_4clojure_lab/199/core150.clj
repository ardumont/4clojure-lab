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

(defn nxp "Next palindrome"
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
        ish (-> sh r e)
        m (s h)
        e? (even? l)
        o? (not e?)]
    (e
     (cond
      (and e? (< ifh ish))   (c nfh (r nfh))
      e?                     (c fh (r fh))
      (and o? (>= ifh ish))  (c fh [m] (r fh))
      (and o? (< m 9))       (c fh [(+ 1 m)] (r fh))
      :else                  (c nfh [0] (r nfh))))))

(fact "some limit cases that explicit my errors"
  (nxp 1234500000) => 1234554321
  (nxp 1234554321) => 1234554321
  (nxp 1234554322) => 1234664321)

(fact
  (nxp 99) => 99
  (nxp 100) => 101
  (nxp 9999) => 9999
  (nxp 10000) => 10001)

(fact "odd length - edge cases - switch to even length"
  (nxp 9) => 9
  (nxp 999) => 999)

(fact "odd length - 9"
  (nxp 193) => 202
  (nxp 191) => 191
  (nxp 290) => 292
  (nxp 295) => 303)

(fact "odd length normal cases"
  (nxp 121) => 121
  (nxp 120) => 121
  (nxp 161) => 161
  (nxp 162) => 171
  (nxp 13630) => 13631
  (nxp 13631) => 13631
  (nxp 13632) => 13731)

(fact "nxp"
  (nxp 0) => 0
  (nxp 1) => 1
  (nxp 11) => 11
  (nxp 1111) => 1111
  (nxp 23) => 33
  (nxp 35) => 44
  (nxp 10) => 11)

(defn gen-pal "Generate palindromes."
  [n]
  (letfn
      [(d [n] (if (< n 10) [n] (conj (d (quot n 10)) (rem n 10))))
       (e [s] (reduce #(+ (* 10 %) %2) 0 s))
       (p [n] (let [c concat
                    r reverse
                    s (d n)
                    l (count s)
                    h (quot l 2)
                    F (take h s)
                    ifh (e F)
                    nfh (-> F e inc d)
                    sh (take-last h s)
                    ish (-> sh r e)
                    m (s h)
                    e? (even? l)
                    o? (not e?)]
                (e
                 (cond
                  (and e? (< ifh ish))   (c nfh (r nfh))
                  e?                     (c F (r F))
                  (and o? (>= ifh ish))  (c F [m] (r F))
                  (and o? (< m 9))       (c F [(+ 1 m)] (r F))
                  :else                  (c nfh [0] (r nfh))))))]
    (iterate (comp p inc) (p n))))

(fact
  (take 26 (gen-pal 0)) => [0 1 2 3 4 5 6 7 8 9
                            11 22 33 44 55 66 77 88 99
                            101 111 121 131 141 151 161])

(fact
  (take 16 (gen-pal 162)) => [171 181 191 202
                              212 222 232 242
                              252 262 272 282
                              292 303 313 323])

(fact
  (take 6 (gen-pal 1234550000)) => [1234554321 1234664321 1234774321
                                    1234884321 1234994321 1235005321])

(fact
  (first (gen-pal (* 111111111 111111111))) => (* 111111111 111111111))

(fact
  (=  (set (take 199 (gen-pal 0))) (set (map #(first (gen-pal %)) (range 0 10000)))) => true)

(fact
  (apply < (take 6666 (gen-pal 9999999))) => true)

(fact
  (nth (gen-pal 0) 10101) => 9102019)