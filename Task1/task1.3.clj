(defn map_ [func input]
    (apply list 
        (reduce 
            (fn [acc x] (conj acc (func x))) 
            [] 
            input
        )
    )
)

(println (map_ inc (list 1 2 3 4)))
(println (map inc (list 1 2 3 4)))


(defn filter_ [func input]
    (apply list 
        (reduce 
            (fn [acc x] (if (func x) (conj acc x) acc))
            []
            input
        )
    )
)

(println (filter_ (fn [x] (not= 0 (mod x 2))) (range 0 10)))
(println (filter (fn [x] (not= 0 (mod x 2))) (range 0 10)))