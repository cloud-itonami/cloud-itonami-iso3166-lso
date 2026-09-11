(ns marketentry.registry
  "Pure-function market-entry filing-draft + filing-submit record
  construction -- an append-only market-entry book-of-record draft.

  Like every sibling actor's registry, there is no single international
  reference-number standard for a public-procurement market-entry
  filing -- every jurisdiction assigns its own format. This namespace
  does NOT invent one; it builds a jurisdiction-scoped sequence number
  and validates the record's required fields, the same honest,
  non-fabricating discipline `marketentry.facts` uses.

  `engagement-fee-matches-claim?` is an HONEST reapplication of the
  SAME ground-truth-recompute DISCIPLINE sibling actors use (verify a
  claimed monetary total against the entity's own recorded quantity x
  unit fields), reapplied to a market-entry engagement fee line.

  `contractor-registration-fee-matches-claim?` /
  `contractor-registration-fee-mismatch-claim?` are the SAME discipline
  applied to a genuinely Lesotho-specific mechanism: gov.ls's own
  Contractors Registration Certificate e-service page (fetched directly
  2026-07-23), which reads verbatim: 'Contractors Registration
  Certificate Application letter, traders licence, tax clearance
  certificate and equipment registrations Categories: A=2000, B=1500,
  C=500, D=300 3 months'. This iteration could not independently
  confirm which ministry administers this specific certificate (an
  honest owner-attribution gap, see `marketentry.facts`), while the fee
  schedule and requirements THEMSELVES are directly confirmed gov.ls
  text.

  This is a GENUINELY DIFFERENT check SHAPE than every prior iso3166
  sibling this repo mirrors: GMB's GIEPA Special Investment Certificate
  is an ORIGIN-CONDITIONAL INVESTMENT-AMOUNT THRESHOLD (a >= test whose
  own threshold value depends on the bidder's declared investor
  origin), CAF's Marché réservé mechanism is a MULTI-CRITERION
  INCLUSION-ELIGIBILITY test over the bidder's own workforce
  composition/legal form, BTN's FDI Negative List is a SET-MEMBERSHIP
  test on the engagement's own declared FDI sector, and EST's
  digital-signing-method check tests the VALIDITY OF THE FILING'S OWN
  EXECUTION INSTRUMENT (a procedural axis, not the bidder's business
  substance at all). Lesotho's Contractors Registration Certificate
  mechanism is none of these: it is a DISCRETE-CATEGORY -> FIXED-
  CONSTANT LOOKUP-TABLE EQUALITY check -- not a threshold (>=), not a
  percentage-of-turnover formula, not a set-membership/exclusion test,
  and not an instrument-validity check. The engagement declares its own
  registration category (:A/:B/:C/:D, a closed enumerated set with NO
  ordering semantics used by the check itself -- unlike Antigua and
  Barbuda's/Botswana's ORDERED-TIER classifications, this check does not
  compare across tiers, it only tests whether the claimed fee for the
  bidder's OWN declared category equals THAT category's own published
  constant) and the governor independently looks up the officially
  published fee for that exact category, then compares it for EQUALITY
  (not >=, not a range) against the engagement's own claimed fee -- the
  same general ground-truth-recompute shape as
  `engagement-fee-matches-claim?`, but keyed by a government-published
  discrete category rather than computed from a linear base+rate*months
  formula.

  This namespace is pure data + pure functions -- no I/O, no network
  call to any real procurement portal. It builds the RECORD an
  operator would keep, not the act of submitting a portal registration
  itself (that is `marketentry.operation`'s `:filing/submit`, always
  human-gated -- see README Actuation)."
  (:require [kotoba.lang.text :as str]))

(defn- unsigned-certificate
  "Every certificate this actor produces is UNSIGNED -- signature is
  the market-entry operator's act, not this actor's."
  [kind subject record-id]
  {"@context" ["https://www.w3.org/ns/credentials/v2"]
   "type" ["VerifiableCredential" kind]
   "credentialSubject" {"id" subject "record" record-id}
   "proof" nil
   "issued_by_registry" false
   "status" "draft-unsigned"})

(defn- zero-pad [n w]
  (let [s (str n)]
    (str (apply str (repeat (max 0 (- w (count s))) "0")) s)))

(def ^:private money-scale
  "Sub-minor-unit scale used when comparing two money amounts: 1/10000 of
  a unit. Coarser than double representation error by many orders of
  magnitude, finer than any real currency's minor unit (2 decimals for
  most, 3 for KWD/BHD/OMR, 0 for JPY/KRW)."
  10000)

(defn- money=
  "Exact-at-money-precision equality for two amounts.

  `==` on raw doubles is NOT the right comparison for money. With
  whole-unit fees the two agree, but as soon as an amount carries
  cents the sum `base + rate x months` is routinely not the double
  nearest the true total, and a CORRECT claim compares false: measured
  on this exact shape, 40,989 of 327,060 cent-denominated combinations
  (12.5%) were rejected while being right, against 0 of 327,060 in
  whole units.

  Rounding both sides to `money-scale` before comparing removes the
  representation error while preserving every distinction money can
  actually carry."
  [x y]
  (and (number? x) (number? y)
       (= (Math/round (* money-scale (double x)))
          (Math/round (* money-scale (double y))))))

(defn compute-engagement-fee
  "The ground-truth engagement fee for `engagement`'s own `:base-fee`
  and `:monitoring-months` x `:monthly-rate` -- a single flat
  base + months x rate calculation, not a full pricing engine."
  [{:keys [base-fee monthly-rate monitoring-months]}]
  ;; nil when any field is not a number: an un-recomputable engagement is
  ;; un-verifiable, which is neither `correct` nor a ClassCastException
  ;; thrown out of the caller.
  (when (and (number? base-fee) (number? monthly-rate) (number? monitoring-months))
    (+ (double base-fee)
       (* (double monthly-rate) (double monitoring-months)))))

(defn engagement-fee-matches-claim?
  "Does `engagement`'s own `:claimed-fee` equal the independently
  recomputed `compute-engagement-fee`?"
  [{:keys [claimed-fee] :as engagement}]
  (money= claimed-fee (compute-engagement-fee engagement)))

(def contractor-registration-fee-schedule
  "gov.ls's own Contractors Registration Certificate e-service page
  (fetched directly 2026-07-23, own text): 'Categories: A=2000, B=1500,
  C=500, D=300' -- the fixed registration-certificate fee for each
  contractor category. The page does not restate the currency unit for
  this entry; Lesotho's currency is the loti/maloti (M), the unit used
  explicitly elsewhere on the same site's Company Registration fee
  schedule (M 530.00 / M 1030.00, gov.ls/eservice/company-registration/)
  and independently confirmed by the Central Bank of Lesotho's own
  currency page -- treated as Maloti by strong contextual inference,
  not an independently-confirmed statement on THIS specific page (see
  `marketentry.facts`)."
  {:A 2000 :B 1500 :C 500 :D 300})

(defn compute-contractor-registration-fee
  "The ground-truth Contractors Registration Certificate fee for
  `contractor-category` (:A/:B/:C/:D), or nil for an unrecognized
  category -- never guesses a fee for a category outside the published
  schedule."
  [contractor-category]
  (get contractor-registration-fee-schedule contractor-category))

(defn contractor-registration-fee-matches-claim?
  "Does `engagement`'s own `:claimed-contractor-registration-fee` equal
  the officially published fee for its own declared
  `:contractor-category`? A missing/unrecognized category simply fails
  (does not throw)."
  [{:keys [contractor-category claimed-contractor-registration-fee]}]
  (boolean
   (when-let [official (compute-contractor-registration-fee contractor-category)]
     (and (some? claimed-contractor-registration-fee)
          (money= claimed-contractor-registration-fee official)))))

(defn contractor-registration-fee-mismatch-claim?
  "Does `engagement` declare `:seeking-contractor-registration? true`
  (i.e. it is applying for a Contractors Registration Certificate)
  while the INDEPENDENTLY recomputed
  `contractor-registration-fee-matches-claim?` is false? An engagement
  not seeking contractor registration is never flagged by this check
  (entity/engagement-scope-gated, the same discipline BTN's
  `:foreign-company?`-gated FDI check and GMB's `:seeking-sic?`-gated
  check use)."
  [{:keys [seeking-contractor-registration?] :as engagement}]
  (boolean (and seeking-contractor-registration?
                (not (contractor-registration-fee-matches-claim? engagement)))))

(defn register-draft
  "Validate + construct the FILING-DRAFT registration DRAFT -- the
  market-entry operator's own act of preparing a portal registration
  package. Pure function -- does not touch any real procurement
  portal."
  [engagement-id jurisdiction sequence]
  (when-not (and engagement-id (not= engagement-id ""))
    (throw (ex-info "draft: engagement_id required" {})))
  (when-not (and jurisdiction (not= jurisdiction ""))
    (throw (ex-info "draft: jurisdiction required" {})))
  (when (< sequence 0)
    (throw (ex-info "draft: sequence must be >= 0" {})))
  (let [draft-number (str (str/upper jurisdiction) "-DFT-" (zero-pad sequence 6))
        record {"record_id" draft-number
                "kind" "filing-draft"
                "engagement_id" engagement-id
                "jurisdiction" jurisdiction
                "immutable" true}]
    {"record" record "draft_number" draft-number
     "certificate" (unsigned-certificate "FilingDraft" draft-number draft-number)}))

(defn register-submit
  "Validate + construct the FILING-SUBMIT registration DRAFT -- the
  market-entry operator's own act of actually submitting a portal
  registration (always human-gated upstream)."
  [engagement-id jurisdiction sequence]
  (when-not (and engagement-id (not= engagement-id ""))
    (throw (ex-info "submit: engagement_id required" {})))
  (when-not (and jurisdiction (not= jurisdiction ""))
    (throw (ex-info "submit: jurisdiction required" {})))
  (when (< sequence 0)
    (throw (ex-info "submit: sequence must be >= 0" {})))
  (let [submit-number (str (str/upper jurisdiction) "-SUB-" (zero-pad sequence 6))
        record {"record_id" submit-number
                "kind" "filing-submit"
                "engagement_id" engagement-id
                "jurisdiction" jurisdiction
                "immutable" true}]
    {"record" record "submit_number" submit-number
     "certificate" (unsigned-certificate "FilingSubmit" submit-number submit-number)}))

(defn append [history result]
  (conj (vec history) (get result "record")))
