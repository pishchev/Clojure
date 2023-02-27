(defn addToWord [baseStr alpList]
    (map
        (fn [sym] (str baseStr (first sym)))
        (filter (fn [sym] (not= (str(last baseStr)) sym)) alpList )
    )
)

(defn addToWordList[restAlpList alpList]
    (apply concat
        (map (fn [sym] (addToWord sym alpList)) restAlpList)
    )
)

(defn combine [alpList curIterIdx]
  (if (> curIterIdx 1)
    (addToWordList (combine alpList (dec curIterIdx)) alpList)
    alpList
  )
)

(println (combine `("a" "b" "c") 1))
(println (combine `("a" "b" "c") 2))
(println (combine `("a" "b" "c") 3))
