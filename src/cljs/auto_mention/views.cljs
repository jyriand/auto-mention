(ns auto-mention.views
  (:require [re-frame.core :refer [subscribe
                                   dispatch]]))

(defn autocomplete-results [results]
  [:ul {:class "completions"} "Autocomplete Results:"
   (for [completion (:people results)]
     ^{:key completion}
     [:li completion])])

(defn autocomplete []
  (let [results (subscribe [:results-query])]
    (fn show-results []
      (if (nil? @results)
        [:div "No results"]
        [autocomplete-results @results]))))

(defn text-area []
  [:div {:contentEditable true
         :style {:width 200
                 :height 50
                 :border "1px solid black"}
         :on-input #(dispatch [:text-change %])}])

(defn container []
  [:div
   [text-area]
   [autocomplete]])
