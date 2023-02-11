(defn combinations 
  ([num]
    (println "No args"))
  ([num x]
    (println x))
  ([num x & rest]
    (println x)
    (apply combinations num rest))
)
    
  
(combinations 2 "f" "d" "s")