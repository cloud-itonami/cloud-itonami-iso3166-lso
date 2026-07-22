(ns marketentry.registry-test
  (:require [clojure.test :refer [deftest is testing]]
            [marketentry.registry :as registry]))

(deftest engagement-fee-recompute
  (let [e {:base-fee 500000 :monthly-rate 30000 :monitoring-months 12 :claimed-fee 860000.0}]
    (is (== 860000.0 (registry/compute-engagement-fee e)))
    (is (true? (registry/engagement-fee-matches-claim? e))))
  (let [bad {:base-fee 500000 :monthly-rate 30000 :monitoring-months 12 :claimed-fee 999000.0}]
    (is (false? (registry/engagement-fee-matches-claim? bad)))))

(deftest register-draft-and-submit
  (let [d (registry/register-draft "eng-1" "LSO" 0)
        s (registry/register-submit "eng-1" "LSO" 0)]
    (is (= "LSO-DFT-000000" (get d "draft_number")))
    (is (= "LSO-SUB-000000" (get s "submit_number")))
    (is (nil? (get-in d ["certificate" "proof"])))
    (is (= "draft-unsigned" (get-in s ["certificate" "status"])))))

(deftest register-requires-ids
  (is (thrown? Exception (registry/register-draft "" "LSO" 0)))
  (is (thrown? Exception (registry/register-submit "eng-1" "" 0))))

(deftest contractor-registration-fee-schedule-lookup
  (testing "each of the four published categories resolves to its own exact fee"
    (is (= 2000 (registry/compute-contractor-registration-fee :A)))
    (is (= 1500 (registry/compute-contractor-registration-fee :B)))
    (is (= 500 (registry/compute-contractor-registration-fee :C)))
    (is (= 300 (registry/compute-contractor-registration-fee :D)))
    (is (nil? (registry/compute-contractor-registration-fee :Z))
        "an unrecognized category has no fee -- never guessed")))

(deftest contractor-registration-fee-matches-claim
  (testing "a claimed fee that EXACTLY equals the published category fee matches"
    (is (true? (registry/contractor-registration-fee-matches-claim?
                {:contractor-category :A :claimed-contractor-registration-fee 2000.0})))
    (is (true? (registry/contractor-registration-fee-matches-claim?
                {:contractor-category :D :claimed-contractor-registration-fee 300.0}))))
  (testing "a claimed fee for the WRONG category's amount does not match its own declared category"
    (is (false? (registry/contractor-registration-fee-matches-claim?
                 {:contractor-category :D :claimed-contractor-registration-fee 2000.0}))
        "D's own published fee is 300, not A's 2000 -- categories are not interchangeable"))
  (testing "missing or unrecognized category fails closed"
    (is (false? (registry/contractor-registration-fee-matches-claim?
                 {:claimed-contractor-registration-fee 2000.0})))
    (is (false? (registry/contractor-registration-fee-matches-claim?
                 {:contractor-category :Z :claimed-contractor-registration-fee 2000.0})))
    (is (false? (registry/contractor-registration-fee-matches-claim? {})))))

(deftest contractor-registration-fee-mismatch-claim-is-entity-scope-gated
  (testing "an engagement NOT declared :seeking-contractor-registration? is never flagged, even if the claimed fee is wrong"
    (is (false? (registry/contractor-registration-fee-mismatch-claim?
                 {:seeking-contractor-registration? false
                  :contractor-category :D :claimed-contractor-registration-fee 999999.0}))))
  (testing "a registration-seeking engagement whose claimed fee does NOT match its own category's published fee -> mismatch"
    (is (true? (registry/contractor-registration-fee-mismatch-claim?
                {:seeking-contractor-registration? true
                 :contractor-category :D :claimed-contractor-registration-fee 1000.0}))))
  (testing "a registration-seeking engagement whose claimed fee DOES match its own category's published fee -> not flagged"
    (is (false? (registry/contractor-registration-fee-mismatch-claim?
                 {:seeking-contractor-registration? true
                  :contractor-category :C :claimed-contractor-registration-fee 500.0})))))
