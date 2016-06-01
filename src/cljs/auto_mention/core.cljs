(ns auto-mention.core
  (:require
   [auto-mention.handlers]
   [reagent.core :as r]
   [re-frame.core :refer [dispatch-sync
                          dispatch]]))

(defn text-area []
  [:div {:contentEditable true
         :style {:width 100
                 :height 100
                 :border "1px solid black"}
         :on-input #(dispatch [:text-change %])}])

(defn mount-root []
  (dispatch-sync [:initialize])
  (r/render [text-area] (.getElementById js/document "app")))
