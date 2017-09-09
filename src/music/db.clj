(ns music.db
  (:require [yesql.core :refer [defqueries]]
            [environ.core :refer [env]]))

(defqueries "music/sql/queries.sql" {:connection (env :database-uri)})

(defn search-by-composer [composer]
  (search-music-by-composer {:composer (str "%" composer "%")}))
