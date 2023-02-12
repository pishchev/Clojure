(defn getLastStrChar [resStr] 
  (str(nth resStr (- (count resStr) 1)))
)

(defn isLastCharEqual [resStr symList idx]
  (= (getLastStrChar resStr) (nth symList idx))
)

(defn isEmpty [resStr] 
  (= (count resStr) 0)
)

(defn combinations [resStr curPos n symList]
  (if (= n (count resStr))
      (println resStr)
      (
        if (< curPos (count symList))
           (
              if (or (isEmpty resStr) (not (isLastCharEqual resStr symList curPos)))
                 (println "Way1")
                 (println "Way2")
           )
           ()
      )
  )
)

(combinations "a" 0 2 `("a" "b" "c"))