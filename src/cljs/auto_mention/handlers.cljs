(ns auto-mention.handlers
  (:require-macros [reagent.ratom :refer [reaction]])
  (:require [re-frame.core :refer [register-handler
                                   register-sub
                                   dispatch]]
            [clojure.string :as s]))

(defn get-inner-text [e]
  (-> e .-target .-textContent))

(register-sub
 :results-query
 (fn [db _]
   (reaction (:completions @db))))

(register-sub
 :text-query
 (fn [db _]
   (reaction (:text @db))))

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
   (assoc db :text (get-inner-text value))
   (dispatch [:parse-text (get-inner-text value)])
   db))

(register-handler
 :parse-text
 (fn [db [_ value]]
   (if (s/includes? value "@")
     (js/console.log "Match")
     (js/console.log "No match"))
   db))
