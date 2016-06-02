(ns auto-mention.handlers
  (:require [auto-mention.helpers :as h]
            [auto-mention.parser :as parser]
            [re-frame.core :refer [register-handler dispatch]]
            [clojure.string :as s]))

(register-handler
 :initialize
 (fn [db [_ value]]
   (merge db value)))

(register-handler
 :text-change
 (fn [db [_ value]]
   (let [text (h/inner-text value)
         new-db (assoc-in db [:text-area :content] text)]
     (dispatch [:parse-text text])
     new-db)))

(register-handler
 :mark-completed
 (fn [db _]
   (let [mentions (get-in db [:completions :mentions])]
     (if (not (empty? mentions))
       (assoc-in db [:text-area :completed] (vec mentions))
       db))))

(register-handler
 :parse-text
 (fn [db [_ value]]
   (let [trigger-words (parser/trigger-words \@ value)]
     (if (seq trigger-words)
       (assoc db :completions {:people ["Rich Hickey" "Alan Turing"]
                               :mentions trigger-words})
       (assoc db :completions nil)))))
