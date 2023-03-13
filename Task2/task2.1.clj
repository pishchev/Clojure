;проверка на некратность
(defn notMultiple 
  ([denum, num, factor]
    (if (= num (* denum factor))
      false
      (if (< num (* denum factor))
        true
        (recur denum num (inc factor))
      )
    )
  )
  ([denum, num]
    (notMultiple denum num 1)
  )
)

(println "Test1----------")
(println (notMultiple 2 9))
(println (notMultiple 3 9))
(println (notMultiple 4 9))
(println (notMultiple 5 9))
(println (notMultiple 9 9))
(println "----------Test1\n")

;фильтруем числа кратные знаменателю
(defn filterMultiplesOfDenom [denum, nums]
  (filter (partial notMultiple denum) nums) 
)

(println "Test2----------")
(println (filterMultiplesOfDenom 2 (range 1 10)))
(println (filterMultiplesOfDenom 3 (range 1 10)))
(println (filterMultiplesOfDenom 4 (range 1 10)))
(println (filterMultiplesOfDenom 5 (range 1 10)))
(println "----------Test2\n")

(defn filterMultiplesOfDenoms [denums, nums]
  (reduce (fn [acc, num] (filterMultiplesOfDenom num acc)) nums denums)
)

(println "Test3----------")
(println (filterMultiplesOfDenoms (range 2 3) (range 1 20)))
(println (filterMultiplesOfDenoms (range 3 4) (range 1 20)))
(println (filterMultiplesOfDenoms (range 5 7) (range 1 20)))
(println "----------Test3\n")

(defn extend_acc [from, to, acc]
  (concat 
    acc
    (filterMultiplesOfDenoms acc (range from to))
  )
)

(println (extend_acc 10 30 `(2, 3)))


(def step 5)
(def initial `(2,3,5))

(defn get_n_simple_numbers 
  ([n, acc, from, to]
    (if (< n (count acc))
      (nth acc n)
      (recur n (extend_acc from to acc) (+ from step) (+ to step))
    )
  )
  ([n]
    (get_n_simple_numbers (dec n) `(2,3,5) step (* 2 step))
  )
)

(println (get_n_simple_numbers 9))