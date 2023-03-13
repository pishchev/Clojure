(defn notMultiple 
  ([denum, num, factor]
    (if (= num (* denum factor)) false
      (if (< num (* denum factor)) true
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

(def step 5) 
(defn extendPrimes [primes sectorIdx]
  (concat primes
    (filterMultiplesOfDenoms primes 
      (range (* step sectorIdx) (* step (inc sectorIdx))))
  )
)

(println "Test4----------")
(println (extendPrimes `(2, 3) 1))
(println (extendPrimes `(2, 3) 2))
(println (extendPrimes `(2, 3) 3))
(println "----------Test4\n")

(defn nthPrime 
  ([n, primes, sectorIdx]
    (if (< n (count primes))
      (nth primes n)
      (recur n (extendPrimes primes sectorIdx) (inc sectorIdx))
    )
  )
  ([n]
    (nthPrime (dec n) `(2,3,5) 1)
  )
)

(println "Test5----------")
(println (nthPrime 1))
(println (nthPrime 2))
(println (nthPrime 3))
(println (nthPrime 4))
(println (nthPrime 5))
(println (nthPrime 10))
(println (nthPrime 15))
(println (nthPrime 20))
(println (nthPrime 40))
(println "----------Test5\n")
