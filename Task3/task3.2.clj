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

(defn parallel-integral [f intervals]
    (->>(range 0 intervals)
        (map #(calculate_interval  f %)) 
        (doall)
        (apply +)
    )
)

(defn integrate 
  ([f]
    (fn [x0]
      ( + (parallel-integral f (int (/ x0 prec)))
          (trapezoid f (* (int (/ x0 prec)) prec) x0)
      )
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

(time ((integrate f_sleep) 11.3))
(time ((integrate f_sleep) 2.1))
(time ((integrate f_sleep) 20.003))
(time ((integrate f_sleep) 13.1))