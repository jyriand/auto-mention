(ns auto-mention.core
  (:require
   [auto-mention.handlers]
   [reagent.core :as r]
   [re-frame.core :refer [dispatch-sync
                          dispatch
                          subscribe]]))

(enable-console-print!)

(def completions {:completions
                  {:people ["Rich Hickey" "Alan Turing"]}})

(defn autocomplete-results []
  (let [results (subscribe [:results-query])]
    (fn show-results []
      [:ul {:class "completions"} "Autocomplete Results:"
       (for [completion (:people @results)]
         ^{:key completion}
         [:li completion])])))

(defn text-area []
  [:div {:contentEditable true
         :style {:width 200
                 :height 50
                 :border "1px solid black"}
         :on-input #(dispatch [:text-change %])}])

(defn container []
  [:div
   [text-area]
   [autocomplete-results]])

(defn mount-root []
  (dispatch-sync [:initialize completions])
  (r/render [container] (.getElementById js/document "app")))
