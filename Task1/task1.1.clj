(defn getLastStrChar [resStr] 
  (str(nth resStr (- (count resStr) 1)))
)

(defn isLastCharEqual [resStr symList idx]
  (= (getLastStrChar resStr) (nth symList idx))
)

(defn isEmpty [resStr] 
  (= (count resStr) 0)
)

(defn rec_combinations [resStr curPos n symList]
  (if (= n (count resStr))
      (println resStr)
      (
        if (< curPos (count symList))
           (
              if (or (isEmpty resStr) (not (isLastCharEqual resStr symList curPos)))
                 (
                    do 
                      (rec_combinations (str resStr (nth symList curPos)) 0 n symList)
                      (rec_combinations resStr (+ curPos 1) n symList)
                 )
                 (rec_combinations resStr (+ curPos 1) n symList)
           )
           ()
      )
  )
)

(defn combinations [num symList]
  (rec_combinations "" 0 num symList)
)

(combinations 2 `("a" "b" "c"))