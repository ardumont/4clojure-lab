(ns ^{:doc "http://www.4clojure.com/problem/150#prob-title"}
  clojure4-lab.199.core150
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

(defn gen-pal "Naive implementation"
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

(defn nxp "Next palindrome or the palindrome itself"
  [n] (let [c concat           ;; alias the concat function
            r reverse          ;; so is the reverse function
            I (d n)            ;; I the potential palindrome in a number sequence
            l (count I)        ;; length of the palindrome
            h (quot l 2)       ;; the half of the sequence
            F (take h I)       ;; First half of the sequence
            f (-> F r e)       ;; first half number reversed
            N (-> F e inc d)   ;; Next first half sequence
            S (take-last h I)  ;; the Second half of the sequence
            s (e S)            ;; the seconf half number
            m (I h)            ;; the middle number
            e? (even? l)       ;; is the length of the palindrome even?
            o? (not e?)]       ;; opposite
        (e
         (cond
          (and e? (< f s))   (c N (r N))            ;; 1882  -> (< 81 82)  -> (+ 1 18) -> 1991
          e?                 (c F (r F))            ;; 1880  -> (> 81 80)  -> 1881
          (and o? (>= f s))  (c F [m] (r F))        ;; 13321 -> (>= 31 21) -> 13331
          (and o? (< m 9))   (c F [(+ 1 m)] (r F))  ;; 13332 -> (< 31 32)  -> 13431
          :else              (c N [0] (r N))))))    ;; 13932 -> (and (< 31 32) (= 9 m)) -> 14041

;.;. If this isn't nice, I don't know what is. -- Vonnegut
(fact
  (nxp 1882) => 1991
  (nxp 1880) => 1881
  (nxp 13321) => 13331
  (nxp 13332) => 13431
  (nxp 13932) => 14041)

(fact
  (nxp 9888) => 9889
  (nxp 9889) => 9889
  (nxp 9789) => 9889
  (nxp 1882) => 1991)

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
                    I (d n)
                    l (count I)
                    h (quot l 2)
                    F (take h I)
                    f (-> F r e)
                    N (-> F e inc d)
                    S (take-last h I)
                    s (e S)
                    m (I h)
                    e? (even? l)
                    o? (not e?)]
                (e
                 (cond
                  (and e? (>= f s))  (c F (r F))
                  e?                 (c N (r N))
                  (and o? (>= f s))  (c F [m] (r F))
                  (and o? (< m 9))   (c F [(+ 1 m)] (r F))
                  :else              (c N [0] (r N))))))]
    (iterate (comp p inc) (p n))))

(fact
  (take 12 (gen-pal 8997)) => [8998 9009 9119 9229 9339 9449 9559 9669 9779 9889 9999 10001])

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
  (= (set (take 199 (gen-pal 0)))
     (set (map #(first (gen-pal %)) (range 0 10000)))) => true)

(fact
  (apply < (take 6666 (gen-pal 9999999))) => true)

(fact
  (nth (gen-pal 0) 10101) => 9102019)
