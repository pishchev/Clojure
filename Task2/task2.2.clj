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

(defn filterMultiplesOfDenom [denum, nums]
  (filter (partial notMultiple denum) nums) 
)

(def step 5) 
(def init `(2 3 5))
(defn sector [sectorIdx] 
  (range (* step sectorIdx) (* step (inc sectorIdx))) 
)

(defn filterMultiplesOfDenoms [denums, sectorIdx]
  (reduce 
    (fn [acc, num] (filterMultiplesOfDenom num acc)) 
    (sector sectorIdx) 
    denums
  )
)

(defn extendPrimes 
  ([primes sectorIdx]
    (concat 
      (concat (filterMultiplesOfDenoms primes sectorIdx))
      (lazy-seq(extendPrimes (concat primes (filterMultiplesOfDenoms primes sectorIdx)) (inc sectorIdx)))
    )
  )
  ([]
    (concat init (extendPrimes init 1))
  )
)

(println "Test----------")
(println (take 1 (extendPrimes)))
(println (take 2 (extendPrimes)))
(println (take 3 (extendPrimes)))
(println (take 4 (extendPrimes)))
(println (take 5 (extendPrimes)))
(println (take 6 (extendPrimes)))
(println (take 20 (extendPrimes)))
(println (take 25 (extendPrimes)))
(println "----------Test\n")
