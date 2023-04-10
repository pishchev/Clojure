; модуль
(ns integration (:gen-class))

(def prec 0.1)
(defn f_sleep [x] (do (Thread/sleep 1) x))
(defn fx_x3 [x] (/(* (* x x) x) 3))
(defn fx_x2 [x] (* x x))

(defn trapezoid [fx0 fx1 step]
  (* (* (+ fx0 fx1) step) 0.5)
)

(defn sum_seq [f]
  (iterate
    (fn [[sum fx0 x0]]
      (let [x1 (+ x0 prec)
            fx1 (f x1)]
        (list
          (+ sum (trapezoid fx0 fx1 prec)) fx1 x1
        )
      )
    )
    (list 0 (f 0) 0)
  )
)

(defn integrate [f]
  (fn [x1]
    (let [sum_seq_ (sum_seq f)
          last_block (nth sum_seq_ (int (/ x1 prec)))
          sum (first last_block)
          x0 (nth last_block 2)
          fx0 (nth last_block 1)
          fx1 (f x1)]
      (+ sum (trapezoid fx0 fx1 (- x1 x0)))
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