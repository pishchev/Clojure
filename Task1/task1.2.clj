(defn addToWord 
  ([baseStr alpList acc]
    (if (> (count alpList) 0)
      (if (= (str (last baseStr)) (first alpList))
        (recur baseStr (rest alpList) acc)
        (recur baseStr
               (rest alpList)
               (concat acc (list (str baseStr (first alpList))))
        )       
      )
      acc
    )
  )
  ([baseStr alpList]
    (addToWord baseStr alpList `())
  )
)

(defn addToWordList 
  ([restAlpList alpList acc]
    (if (> (count restAlpList) 0)
      (recur (rest restAlpList)
             alpList
             (concat acc (addToWord (first restAlpList) alpList))
      )
      acc
    )
  )
  ([restAlpList alpList]
    (addToWordList restAlpList alpList `())
  )
)

(defn combine 
  ([alpList curIterIdx acc]
    (if (> curIterIdx 1)
      (recur alpList 
             (dec curIterIdx)
             (addToWordList acc alpList)
      )
      acc
    )
  )
  ([alpList curIterIdx]
    (combine alpList curIterIdx alpList)
  )
)

(println (combine `("a" "b" "c") 2))