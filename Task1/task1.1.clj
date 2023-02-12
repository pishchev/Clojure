(defn getLastStrChar [resStr] 
  (str(nth resStr (- (count resStr) 1)))
)

(defn isLastCharEqual [resStr symList idx]
  (= (getLastStrChar resStr) (nth symList idx))
)

(defn combinations [resStr curPos n symList]
  (if (= n (count resStr))
      (println resStr)
      (
        if (< curPos (count symList))
           (
              if (not (isLastCharEqual resStr symList curPos))
                 (println "Way1")
                 (println "Way2")
           )
           ()
      )
  )
)


(println ( = (getLastStrChar "absfgh") "f"))

(combinations "a" 0 2 `("a" "b" "c"))