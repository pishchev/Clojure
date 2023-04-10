; модуль
(ns integration (:gen-class))

(def prec 0.1)
(defn f_sleep [x] (do (Thread/sleep 1) x))
(defn fx_x3 [x] (/(* (* x x) x) 3))
(defn fx_x2 [x] (* x x))

(defn partial-sum-seq [f]
  (iterate
    (fn [[prev-sum ind f_a]]
        (list
          (+ prev-sum (-> (+ f_a (f (* prec (inc ind)))) (/ 2) (* prec)))
          (inc ind)
          (f (* prec (inc ind)))))
          
    (list 0 0 (f 0))))

(defn integrate [f]
    (fn [t]
      (let [ind (int (/ t prec))
            cur-block (nth (partial-sum-seq f) ind)
            trapeze-sum (first cur-block)
            f-ind (nth cur-block 2)
            local-prec (- t (* ind prec))]
        (-> (f t) (+ f-ind) (/ 2) (* local-prec) (+ trapeze-sum)))))

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