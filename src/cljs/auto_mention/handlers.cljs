(ns auto-mention.handlers
  (:require-macros [reagent.ratom :refer [reaction]])
  (:require [re-frame.core :refer [register-handler
                                   register-sub
                                   dispatch]]))

(defn get-inner-text [e]
  (-> e .-target .-textContent))

(register-sub
 :results-query
 (fn [db _]
   (reaction (:completions @db))))

(register-sub
 :board-query
 (fn [db _]
   (reaction (:board @db))))


(register-handler
 :initialize
 (fn [db [_ value]]
   (merge db value)))

(register-handler
 :text-change
 (fn [db [_ value]]
   (js/console.log (get-inner-text value))
   db))