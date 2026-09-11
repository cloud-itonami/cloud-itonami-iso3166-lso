(ns marketentry.facts-test
  (:require [clojure.test :refer [deftest is testing]]
            [marketentry.facts :as facts]))

(deftest lso-has-spec-basis
  (let [sb (facts/spec-basis "LSO")]
    (is (some? sb))
    (is (string? (:provenance sb)))
    (is (seq (:required-evidence sb)))
    (is (some? (facts/contractor-registration-spec-basis "LSO")))))

(deftest lso-rep-spec-basis-is-honestly-absent
  (testing "the Public Procurement Regulations 2007's own text sits behind a Cloudflare challenge-gate this iteration could not read around -- deliberately not claimed"
    (is (nil? (facts/rep-spec-basis "LSO")))))

(deftest lso-corporate-number-spec-basis-is-honestly-absent
  (testing "TIN registration (Form 1) is confirmed to be filed as part of company registration, but this iteration could not independently confirm a separate Lesotho tax/revenue-collection authority -- deliberately not claimed"
    (is (nil? (facts/corporate-number-spec-basis "LSO")))))

(deftest unknown-jurisdiction-has-no-spec-basis
  (is (nil? (facts/spec-basis "ATL")))
  (is (nil? (facts/spec-basis "ZZZ"))))

(deftest required-evidence-satisfied
  (let [sb (facts/spec-basis "LSO")
        all (:required-evidence sb)]
    (is (true? (facts/required-evidence-satisfied? "LSO" all)))
    (is (not (facts/required-evidence-satisfied? "LSO" (take 1 all))))
    (is (nil? (facts/required-evidence-satisfied? "ATL" all)))))

(deftest coverage-is-honest
  (let [c (facts/coverage ["LSO" "USA" "ATL"])]
    (is (= 3 (:requested c)))
    (is (= 2 (:covered c)))
    (is (= ["ATL"] (:missing-jurisdictions c)))))

(deftest contractor-registration-spec-basis-fee-schedule
  (let [cr (facts/contractor-registration-spec-basis "LSO")]
    (is (= 2000 (get-in cr [:contractor-registration-fee-schedule :A])))
    (is (= 1500 (get-in cr [:contractor-registration-fee-schedule :B])))
    (is (= 500 (get-in cr [:contractor-registration-fee-schedule :C])))
    (is (= 300 (get-in cr [:contractor-registration-fee-schedule :D])))))
