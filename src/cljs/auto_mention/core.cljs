(ns auto-mention.core
  (:require [reagent.core :as r]))

(defn text-area []
  [:div {:contentEditable true
         :style {:width 100
                 :height 100
                 :border "1px solid black"}}])

(defn mount-root []
  (r/render [text-area] (.getElementById js/document "app")))
