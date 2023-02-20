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

(defn combine 
  ([alpList curIterIdx acc]
    (if (> curIterIdx 1)
      (recur alpList 
             (dec curIterIdx)
             (addToWordList alpList acc)
      )
      acc
    )
  )
  ([alpList curIterIdx]
    (combine alpList curIterIdx alpList)
  )
)

(println (combine '("a" "b" "c") 1))
(println (combine '("a" "b" "c") 2))
(println (combine '("a" "b" "c") 3))