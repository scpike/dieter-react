(ns dieter.test.asset.react
  (:use dieter.asset.react)
  (:use dieter.settings)
  (:use clojure.test)
  (:require [clojure.java.io :as io]))

(use-fixtures :each
  (fn [f]
    "Clear out the js engines between tests (pool caches the wrong js engine)"
    (dosync (ref-set pool #{}))
    (f)))

(deftest test-preprocess-jsx-rhino
  (binding [*settings* (merge *settings* {:engine :rhino})]
    (is (= (preprocess-jsx
            (io/file "test/fixtures/assets/javascripts/without-pragma.jsx"))
           "React.createComponent(<p>Hello!</p>);"))))

(deftest test-no-change-without-pragma
  (binding [*settings* (merge *settings* {:engine :v8})]
    (is (= (preprocess-jsx (io/file "test/fixtures/assets/javascripts/without-pragma.jsx"))
           "React.createComponent(<p>Hello!</p>);"))))

(deftest compiles-with-pragma
  (binding [*settings* (merge *settings* {:engine :v8})]
    (is (= (preprocess-jsx (io/file "test/fixtures/assets/javascripts/with-pragma.jsx"))
           "/** @jsx React.DOM */\nvar app = Nav( {color:\"blue\"} );"))))
