(ns todo-clj.db
  (:require [clojure.java.jdbc :as jdbc]))

(def db-spec
  {:dbtype "postgresql" :dbname "todo_clj_dev" :host "localhost" :port 5432 :user "postgres" :password "1q2w3e4r"})

(defn migrate []
  (jdbc/db-do-commands
    db-spec
    (jdbc/create-table-ddl :todo [:id :serial] [:title :varchar])))
