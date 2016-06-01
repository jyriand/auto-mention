(ns auto-mention.parser
  (:require [clojure.string :as s]))

(defn split-text [text]
  (s/split text #" "))

(defn trigger-word? [trigger  word]
  (and (= (first word) trigger)
       (>= (count word) 2)))

(defn trigger-words [trigger text]
  (let [words (split-text text)]
    (filter #(trigger-word? trigger %) words)))
