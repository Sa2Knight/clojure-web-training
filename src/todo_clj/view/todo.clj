(ns todo-clj.view.todo
  (:require [todo-clj.view.layout :as layout]
            [hiccup.form :as hf]))

(defn todo-index-view [req todo-list]
  (->> `([:h1 "TODOの一覧だよ！"]
        [:ul
         ~@(for [{:keys [title]} todo-list]
             [:li title])])
      (layout/common req)))

(defn todo-new-view [req]
  (->> [:section.card
        [:h2 "TODO 追加"]
        (hf/form-to
         [:post "/todo/new"]
         [:input {:name :title :placeholder "TODO を入力してください"}]
         [:button.bg-blue "追加する"])]
       (layout/common req)))

(defn todo-complete-view [req]
  (->> [:section.card
        [:h2 "TODOを追加しました"]]
       (layout/common req)))

(defn todo-edit-view [req todo]
  (let [todo-id (get-in req [:params :todo-id])]
    (->> [:section.card
          [:h2 "TODO 編集"]
          (hf/form-to
            [:post (str "/todo/" todo-id "/edit")]
            [:input {:name :title :value (:title todo)
                     :placeholder "TODOを入力してください"}]
            [:button.bg-blue "更新する"])]
         (layout/common req))))
