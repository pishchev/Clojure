; модуль
(ns integration (:gen-class))

(defn fx_x [x] x)
(defn fx_x2 [x] (* x x))
(defn fx_x3_5x_3 [x] (+ (* x x x) (* 5 x) 3))

(defn trapezoid [f a b]
    (* (* (+ (f a) (f b)) (- b a)) 0.5)
)

(defn integrate [f a b h]
    (if (< a b)
        (+ 
            (trapezoid f a (+ a h))
            (integrate f (+ a h) b h)
        )
        0 
    ) 
)

(def integrate-meme (memoize integrate))

; тестирование
(ns Task.2.1 (:use clojure.test) (:use integration))
(deftest functions-test
    (testing "Test f(x)=x")
    (is (= (fx_x 1) 1))
    (is (= (fx_x 5) 5))
    (is (= (fx_x 10) 10))
    (testing "Test f(x)=x^2")
    (is (= (fx_x2 1) 1))
    (is (= (fx_x2 2) 4))
    (is (= (fx_x2 -3) 9))
    (testing "Test f(x)=x^3+5x+3")
    (is (= (fx_x3_5x_3 0) 3))
    (is (= (fx_x3_5x_3 2) 21))
    (is (= (fx_x3_5x_3 -1) -3))
)

(deftest trapezoid-test
    (testing "Trapezoid f(x)=x")
    (is (= (trapezoid fx_x 0 5) 12.5))
    (testing "Trapezoid f(x)=x^2")
    (is (= (trapezoid fx_x2 2 5) 43.5))
    (testing "Trapezoid f(x)=x^3+5x+3")
    (is (= (trapezoid fx_x3_5x_3 -2 3) 75.0))
)

(deftest integration-test
    (testing "Integration f(x)=x")
    (is (= (integrate fx_x 0 10 1) 50.0))
    (testing "Integration f(x)=x^2")
    (is (= (integrate fx_x2 -1 6 0.5) 72.625))
    (testing "Integration f(x)=x^3+5x+3")
    (is (= (integrate fx_x3_5x_3 -3 -1 0.1) -34.02))
)

(deftest integration-meme-test
    (testing "Integration f(x)=x")
    (is (= (integrate-meme fx_x 0 10 1) 50.0))
    (testing "Integration f(x)=x^2")
    (is (= (integrate-meme fx_x2 -1 6 0.5) 72.625))
    (testing "Integration f(x)=x^3+5x+3")
    (is (= (integrate-meme fx_x3_5x_3 -3 -1 0.1) -34.02))
)

(run-tests 'Task.2.1)

(println "No meme")
(time (integrate fx_x3_5x_3 0 50 0.05))
(time (integrate fx_x3_5x_3 0 50 0.05))
(time (integrate fx_x3_5x_3 0 50 0.05))
(time (integrate fx_x3_5x_3 0 50 0.05))
(time (integrate fx_x3_5x_3 0 50 0.05))

(println "Meme")
(time (integrate-meme fx_x3_5x_3 50 100 0.05))
(time (integrate-meme fx_x3_5x_3 50 100 0.05))
(time (integrate-meme fx_x3_5x_3 50 100 0.05))
(time (integrate-meme fx_x3_5x_3 50 100 0.05))
(time (integrate-meme fx_x3_5x_3 50 100 0.05))
