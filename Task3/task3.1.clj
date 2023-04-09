; модуль
(ns integration (:gen-class))

(def prec 0.1)
(defn f_sleep [x] (do (Thread/sleep 1) x))
(defn fx_x3 [x] (/(* (* x x) x) 3))
(defn fx_x2 [x] (* x x))

(defn trapezoid [f x0 x1]
  (* (* (+ (f x0) (f x1)) (- x1 x0)) 0.5)
)

(defn calculate_interval [f interval]
  (trapezoid f (* interval prec) (+ (* interval prec) prec))
)
(def calculate_interval_meme (memoize calculate_interval))

(defn integrate 
  ([f x0 interval acc]
    (if (<= (* (inc interval) prec) x0)
      (recur f x0 (inc interval) (+ acc (calculate_interval f interval)))
      (+ acc (trapezoid f (* interval prec) x0))
    )
  )
  ([f]
    (fn [x0]
      (integrate f x0 0 0)
    )
  )
)

(defn integrate_meme 
  ([f x0 interval acc]
    (if (<= (* (inc interval) prec) x0)
      (recur f x0 (inc interval) (+ acc (calculate_interval_meme f interval)))
      (+ acc (trapezoid f (* interval prec) x0))
    )
  )
  ([f]
    (fn [x0]
      (integrate_meme f x0 0 0)
    )
  )
)

; тестирование
(ns Task.2.1 (:use clojure.test) (:use integration))
(defn abs [n] (max n (- n)))
(defn is_close [x0 x1] ( < (abs(- x0 x1)) prec))

(deftest functions-test
  (is (is_close ((integrate fx_x3) 3) 6.7575))
  (is (is_close ((integrate fx_x2) 5) 41.675))
)
(run-tests 'Task.2.1)

(println "No meme")
(time ((integrate f_sleep) 11.3))
(time ((integrate f_sleep) 2.1))
(time ((integrate f_sleep) 20.003))
(time ((integrate f_sleep) 13.1))

(println "Meme")
(time ((integrate_meme f_sleep) 11.8))
(time ((integrate_meme f_sleep) 2.15))
(time ((integrate_meme f_sleep) 20.03))
(time ((integrate_meme f_sleep) 13))