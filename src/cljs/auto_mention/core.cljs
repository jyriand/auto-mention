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

(defn test-part []
  (let [text (subscribe [:text-query])]
    (fn text-bla []
      [:div @text])))

(defn container []
  [:div
   [text-area]
   [autocomplete]
   [test-part]])

(defn mount-root []
  (dispatch-sync [:initialize completions])
  (r/render [container] (.getElementById js/document "app")))
