(ns qlkit-material-ui.core
  (:require [cljsjs.material-ui :as my]
            [material-ui-icons :as mi]
            [qlkit.core :as ql]
            [react :refer [createElement]]
            [clojure.string :as st]
            [goog.object :refer [get]]))

(defn- to-kebab-case
  "Convert a mixed-case string to a Clojure-style kebab-case keyword with an optional prefix.
   E.g.
     (to-kebab-case \"AbcDefG\")                => :abc-def-g
     (to-kebab-case \"AbcDefG\" \"Icon\" \".\") => :icon.abc-def-g"
  ([s prefix separator]
   (let [skewer (fn [s] (->> (st/split s #"(?=[A-Z])")
                             (map st/lower-case)
                             (interpose "-")
                             (apply str)))]
     (keyword (str (if prefix
                     (str (skewer prefix) separator)
                     "")
                   (skewer s)))))
  ([s] (to-kebab-case s nil nil)))

(defn- obj->clj
  [obj]
  (-> (fn [result key]
        (let [v (goog.object/get obj key)]
          (if (= "function" (goog/typeOf v))
            result
            (assoc result key v))))
      (reduce {} (.getKeys goog/object obj))))

(defn- to-kebab-case-map
  "Convert a Javascript Object to a Clojure map, with the keys converted from Javascript-style names
   to idiomatic Clojure-style keywords.  If prefix is given, it's added to the front, separated by a '.'."
  ([js-obj prefix separator] (->> js-obj
                                  obj->clj
                                  (map (fn [[nm obj]]
                                         {(to-kebab-case nm prefix separator)
                                          obj}))
                                  (reduce into {})))
  ([js-obj] (to-kebab-case-map js-obj nil nil)))

(def ^:private components
  "Map of kebab-case component name to the component object."
  (merge (dissoc (to-kebab-case-map js/MaterialUI)
                 :colors
                 :create-mui-theme
                 :jss-preset
                 :with-mobile-dialog
                 :with-styles
                 :with-theme
                 :with-width)
         (to-kebab-case-map js/MaterialUIIcons "Icon" "/"))) 

(defn enable-material-ui! []
  "Configure Qlkit for Material-UI React components.  Call this before calling `qlkit.qlkit/mount."
  (doseq [[k v] components]
    (ql/register-component k v)))

(def available-components
  (sort (keys (js->clj js/MaterialUI))))

(def available-icons
  (sort (keys (js->clj js/MaterialUIIcons))))
