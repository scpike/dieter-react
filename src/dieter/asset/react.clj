(ns dieter.asset.react
  (:require
   [dieter.asset :refer [register]]
   dieter.asset.javascript
   [dieter.pools :refer [make-pool]]
   [dieter.jsengine :refer [run-compiler]]))

(def pool (make-pool))

(defn preprocess-jsx [file]
  (run-compiler pool
                ["react-wrapper.js" "JSXTransformer.js"] "compileJSX"
                file))

(defrecord ReactJSX [file]
  dieter.asset.Asset
  (read-asset [this]
    (dieter.asset.javascript.Js. (:file this) (preprocess-jsx (:file this)))))

(register "jsx" map->ReactJSX)
