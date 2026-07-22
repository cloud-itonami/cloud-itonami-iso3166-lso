(ns statute.facts-test
  (:require [clojure.string :as str]
            [clojure.test :refer [deftest is]]
            [statute.facts :as facts]))

(deftest lso-has-spec-basis
  (let [sb (facts/spec-basis "LSO")]
    (is (= 4 (count sb)))
    (is (every? #(str/starts-with? (:statute/url %) "https://") sb))
    (is (every? :statute/law-number sb))))

(deftest unknown-jurisdiction-has-no-spec-basis
  (is (nil? (facts/spec-basis "ATL")))
  (is (nil? (facts/spec-basis "ZZZ"))))

(deftest coverage-is-honest
  (let [c (facts/coverage ["LSO" "JPN" "ATL"])]
    (is (= 3 (:requested c)))
    (is (= 1 (:covered c)))
    (is (= ["ATL" "JPN"] (:missing-jurisdictions c)))))

(deftest by-topic-filters
  (is (= ["lso.labour-act-2024"]
         (mapv :statute/id (facts/by-topic "LSO" :labor))))
  (is (= #{"lso.income-tax-order-1993" "lso.value-added-tax-act-2001"}
         (set (mapv :statute/id (facts/by-topic "LSO" :tax)))))
  (is (= ["lso.companies-act-2011"]
         (mapv :statute/id (facts/by-topic "LSO" :corporate-governance))))
  (is (empty? (facts/by-topic "ATL" :labor))))
