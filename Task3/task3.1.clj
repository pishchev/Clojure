(defn fx_x [x] x)
(defn fx_x2 [x] (* x x))
(defn fx_x3_5x_3 [x] (+ (* x x x) (* 5 x) 3))

(defn trapezoid [f a b]
    (* (* (+ (f a) (f b)) (- b a)) 0.5)
)

(defn integral [f a b h]
    (if (< a b)
        (+ 
            (trapezoid f a (+ a h))
            (integral f (+ a h) b h)
        )
        0 
    ) 
)

(def integral-mem (memoize integral))

(println (integral fx_x 0 10 1))
