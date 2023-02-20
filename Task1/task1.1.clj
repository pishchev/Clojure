(defn addToWord [baseStr alpList]
  (if (> (count alpList) 0)
    (if (= (str (last baseStr)) (first alpList))
      (addToWord baseStr (rest alpList))
      (concat (list (str baseStr (first alpList)))
              (addToWord baseStr (rest alpList))        
      )
    )
  )
)

(defn addToWordList [restAlpList alpList]
  (if (> (count restAlpList) 0)
    (concat (addToWord (first restAlpList) alpList)
            (addToWordList (rest restAlpList) alpList)
    )
  )
)

(defn combine [alpList curIterIdx]
  (if (> curIterIdx 1)
    (addToWordList (combine alpList (dec curIterIdx)) alpList)
    alpList
  )
)

(println (combine '("a" "b" "c") 3))