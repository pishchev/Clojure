(defn combinations 
  ([]
    (println "No args"))
  ([x]
    (println x))
  ([x & rest]
    (println x)
    (apply combinations rest))
)
    
  
(combinations)