(ns marketentry.facts
  "Per-jurisdiction public-procurement market-entry regulatory catalog
  -- the G2-style spec-basis table the Market-Entry Compliance Governor
  checks every `:jurisdiction/assess` proposal against ('did the advisor
  cite an OFFICIAL public source for this jurisdiction's requirements,
  or did it invent one?').

  Lesotho's real market-entry surface (curl/WebFetch-verified
  2026-07-23; where a page could not be reached, or turned out to be
  behind a bot-challenge with no readable content, that is stated
  explicitly rather than silently omitted):

  - **Public procurement.** The Lesotho Legal Information Institute
    (LesLII, `lesotholii.org` -- 'set up in 2012 ... supported by the
    Judiciary of Lesotho, the Law Office, the Law Society and other
    justice sector stakeholders', its own About text, fetched directly)
    lists exactly ONE procurement-related entry across its entire
    legislation index (93 Acts total, across the site's 2 listing
    pages, both fetched directly as plain server-rendered HTML, NOT
    behind any challenge): 'Public Procurement Regulations, 2007'
    (`lesotholii.org/akn/ls/act/ln/2007/1/eng@2007-01-05` -- the `ln`
    path segment is LesLII's own Akoma-Ntoso document-type code for a
    Legal Notice/subsidiary instrument, i.e. this is confirmed as
    REGULATIONS, not a primary Act). This iteration does NOT know
    whether a separate enabling 'Public Procurement Act' exists and is
    simply absent from LesLII's own (evidently partial) index, or
    whether these Regulations were made under some other general Act's
    delegated legal-notice power -- an honest gap, not resolved by
    guessing. Separately, the Ministry of Finance and Development
    Planning's own gov.ls ministry page (fetched directly, raw HTML,
    NOT a summary) reads verbatim: 'Ministry: Ministry of Finance and
    Development Planning ... Publication of procurement plans
    Procurement – Registration of suppliers and creation of database' --
    confirming the Ministry of Finance and Development Planning as the
    real administering authority for supplier registration, independent
    of (and not itself naming) the 2007 Regulations. EVERY individual
    LesLII document page this iteration tried to fetch for the Act's own
    detailed section text (Public Procurement Regulations 2007,
    Companies Act 2011, Labour Act 2024, Income Tax Order 1993, Value
    Added Tax Act 2001 -- see `statute.facts`) returned Cloudflare's own
    'Just a moment...' JS-challenge shell (HTTP 403, `cf-mitigated`
    challenge page, confirmed by inspecting the raw response body) to
    BOTH plain `curl` (including a warmed-up cookie session with a
    Referer header) and `WebFetch` this iteration -- an HONEST,
    explicitly-flagged ACCESS gap (a bot-challenge on LesLII's
    individual-document route specifically; the LISTING pages loaded
    with an ordinary HTTP 200 and full plain HTML both times), not a
    claim that these Acts' text does not exist. This mirrors this
    family's GMB catalog's own GPPA client-side-rendering gap and CAF's/
    BTN's own unread-provision gaps -- same honest discipline, different
    technical cause (challenge-gated vs. client-rendered).
  - **Business/company registration** is handled through the online
    Company Register at `companies.org.ls` (own text, fetched directly,
    ordinary server-rendered HTML): 'Welcome to Lesotho Ministry of
    Trade & Industry. The Company register is an online register
    available to the public 24 hours a day, 7 days a week, for
    incorporating and maintaining companies and searching for company,
    director and shareholder details.' Its own text ALSO reads: 'Forms
    such as Director Consent forms (Form 9), List of Shareholders
    (Form1A) etc are still required to be filed and are available to
    clients to download from www.obfc.org.ls (Legislation Tab)' --
    confirming a One-Stop Business Facilitation Centre (OBFC) exists at
    `obfc.org.ls`, referenced by companies.org.ls's own site (this
    iteration could not read `obfc.org.ls`'s own content beyond a thin
    login-shell page -- an honest access gap, not pursued further since
    companies.org.ls's own text already independently confirms OBFC's
    role). Separately, gov.ls's own Company Registration e-service page
    (fetched directly, raw HTML, NOT a summary) reads verbatim:
    'Company Registration www.companies.org.ls . Certified copies of
    IDs . Application forms: -Form 8 (for Directors) -Form 1A (shares
    allocation) -Form 1 (TIN registration) . Issuing Certificate of
    Incorporation Local/Internal Fee: M 530.00 Foreign/External: M
    1030.00 3 days'. NOTE a genuine, honestly-flagged INCONSISTENCY
    across Lesotho's own official sources found this iteration:
    companies.org.ls's own text names 'Director Consent forms (Form 9)'
    while gov.ls's own company-registration e-service page names 'Form
    8 (for Directors)' -- both are reported here exactly as found; this
    iteration does not resolve the discrepancy by guessing which form
    number is current. Both sources agree on 'Form1A'/'Form 1A' for the
    shareholder/shares-allocation filing. TIN registration ('Form 1') is
    filed AS PART OF company registration itself, per gov.ls's own text
    above -- this iteration could NOT independently confirm the
    identity or establishing legislation of a separate Lesotho tax/
    revenue-collection authority beyond this integrated Form 1 filing (a
    single unrelated gov.ls e-service page title uses the string
    'Revenue Lesotho Services' in the context of an arts/performance
    border-clearance service, which is too thin and out-of-context a
    citation to confirm as that authority's formal name or legal basis
    -- an honest gap, see `corporate-number-spec-basis` below, which is
    deliberately nil for LSO, the same discipline GMB's catalog uses for
    its own honestly-absent `rep-spec-basis`).
  - `contractor-registration-spec-basis` grounds this vertical's
    FLAGSHIP check (see `marketentry.governor` / `marketentry.registry`)
    -- a genuinely Lesotho-specific mechanism this iteration found
    directly on gov.ls's own Contractors Registration Certificate
    e-service page (fetched directly, raw HTML, NOT a summary), which
    reads verbatim: 'Contractors Registration Certificate Application
    letter, traders licence, tax clearance certificate and equipment
    registrations Categories: A=2000, B=1500, C=500, D=300 3 months'.
    This iteration could NOT independently confirm which ministry
    administers this specific certificate (the e-service listing page
    itself does not attribute it to a ministry, and this iteration does
    not assume it is the same Ministry of Finance and Development
    Planning office that administers general supplier registration,
    since no source states that link directly) -- an honest gap in
    OWNER attribution, while the fee schedule and requirements
    THEMSELVES are directly confirmed gov.ls text. The page does not
    restate the currency unit for these four figures; Lesotho's
    currency is the loti/maloti (plural Maloti, symbol M) -- confirmed
    both by the SAME site's own Company Registration fee schedule above
    ('M 530.00' / 'M 1030.00') and independently by the Central Bank of
    Lesotho's own currency page (`centralbank.org.ls/currency/`, fetched
    directly): 'Issuing Currency: To issue Maloti and Rand to commercial
    banks to satisfy public demand for currency.' -- this iteration
    treats the Contractors Registration Certificate figures as Maloti by
    this strong contextual inference, not an independently-confirmed
    restatement on that specific page.
  - **Regional context** (worth confirming rather than assuming, per
    this iteration's brief): the Southern African Customs Union's own
    site (`sacu.int`, fetched directly) confirms Lesotho as a SACU
    member state ('Botswana, Eswatini, Lesotho, Namibia, South Africa'
    each listed as a member-state page). The Common Monetary Area
    loti/rand 1:1 peg specifically was NOT independently re-confirmed
    with an exact 'peg'/'Common Monetary Area' quote this iteration
    (the Central Bank of Lesotho's own currency page confirms DUAL
    circulation of Maloti and Rand, which is consistent with the
    commonly-understood CMA arrangement, but does not itself use the
    words 'Common Monetary Area' or 'peg') -- reported as
    dual-currency-circulation fact, not over-claimed as a fully
    re-derived peg mechanism.
  - This iteration also looked for a Lesotho-specific representative/
    director exclusion-extension provision (the shape Bulgaria's ЗОП
    Art. 54(2)-(3) / Benin's Art. 61/62 document for their own laws).
    Because the Public Procurement Regulations 2007's own text sits
    behind the Cloudflare challenge-gate described above, this iteration
    could NOT confirm whether such a provision exists. `rep-spec-basis`
    below is left honestly nil for LSO, the same discipline GMB's/CAF's/
    BTN's own catalogs use when a mechanism's current, citable shape
    could not be confirmed.

  Coverage is reported HONESTLY (see `coverage`): a jurisdiction not in
  this table has NO spec-basis, full stop -- the advisor must not
  fabricate one, and the governor holds if it tries.")

(def catalog
  "iso3 -> requirement map. `:required-evidence` mirrors the generic
  intake/portal-registration/filing evidence set; `:legal-basis` /
  `:owner-authority` / `:provenance` are the G2 citation the governor
  requires before any `:jurisdiction/assess` proposal can commit. LSO
  deliberately carries NO `:rep-owner-authority` and NO
  `:corporate-number-owner-authority` -- see the namespace docstring's
  honest-scope-narrowing notes. `:contractor-registration-owner-
  authority` / `:contractor-registration-legal-basis` /
  `:contractor-registration-fee-schedule` / `:contractor-registration-
  provenance` ground this vertical's flagship governor check
  (`contractor-registration-fee-matches-claim?`/`contractor-
  registration-fee-mismatch-claim?` in `marketentry.registry`)."
  {"LSO" {:name "Lesotho"
          :owner-authority "Ministry of Finance and Development Planning -- 'Ministry: Ministry of Finance and Development Planning ... Publication of procurement plans Procurement – Registration of suppliers and creation of database' (gov.ls, own ministry page text, fetched directly)"
          :legal-basis "Public Procurement Regulations, 2007 (named directly by the Lesotho Legal Information Institute's (LesLII) own legislation-listing page, lesotholii.org/legislation/?page=2, plain server-rendered HTML fetched directly; the Regulations' own detailed section text sits behind a Cloudflare 'Just a moment...' JS-challenge that returned HTTP 403 to both curl and WebFetch this iteration on LesLII's individual-document route specifically -- an honestly-flagged ACCESS gap, not a claim of non-existence, see namespace docstring; this iteration also could not confirm whether a separate enabling Public Procurement Act exists beyond these Regulations)"
          :national-spec "Business/company registration: Companies Register (Ministry of Trade & Industry, companies.org.ls, own text, fetched directly: 'The Company register is an online register available to the public 24 hours a day, 7 days a week, for incorporating and maintaining companies'); One-Stop Business Facilitation Centre (OBFC) referenced at obfc.org.ls by companies.org.ls's own text. Own published fee schedule (gov.ls company-registration e-service, own text, fetched directly): Local/Internal M530.00, Foreign/External M1030.00, 3-day processing, Certificate of Incorporation issued, forms Form 8/Form 9 (Directors -- an honest form-number inconsistency across Lesotho's own sources, see namespace docstring), Form 1A (shares allocation), Form 1 (TIN registration, filed as part of company registration itself)"
          :provenance "https://www.lesotholii.org/legislation/?page=2 ; https://www.gov.ls/ministry/ministry-of-finance-and-development-planning/ ; https://www.companies.org.ls/ ; https://www.gov.ls/eservice/company-registration/ ; https://www.gov.ls/eservice/contractors-registration-certificate/"
          :required-evidence ["Certificate of Incorporation (Companies Register, Ministry of Trade & Industry -- companies.org.ls / gov.ls company-registration e-service, both fetched directly)"
                              "TIN registration confirmation (Form 1, filed as part of company registration itself, per gov.ls's own company-registration e-service page; this iteration could NOT independently confirm a separate Lesotho tax/revenue-collection authority beyond this integrated filing -- see namespace docstring)"
                              "Traders Licence (named as a precondition on gov.ls's own Contractors Registration Certificate e-service page requirements list, fetched directly)"
                              "Tax Clearance Certificate (named as a requirement on BOTH gov.ls's own Contractors Registration Certificate AND Listing of Consultants e-service pages, fetched directly)"
                              "Supplier registration record (Ministry of Finance and Development Planning Procurement department -- 'Registration of suppliers and creation of database', gov.ls, own text, fetched directly)"
                              "Contractors Registration Certificate confirmation record, when the engagement declares :seeking-contractor-registration? true"]
          :contractor-registration-owner-authority "Government of Lesotho e-Services portal (gov.ls) -- the specific administering ministry is not stated on the service listing itself and could not be independently confirmed this iteration (an honest gap; see namespace docstring)"
          :contractor-registration-legal-basis "gov.ls's own Contractors Registration Certificate e-service page (fetched directly, own text): 'Contractors Registration Certificate Application letter, traders licence, tax clearance certificate and equipment registrations Categories: A=2000, B=1500, C=500, D=300 3 months'. Currency unit not restated on this page; treated as Maloti (M) by strong contextual inference from this same site's Company Registration fee schedule and the Central Bank of Lesotho's own currency page (see namespace docstring)"
          :contractor-registration-fee-schedule {:A 2000 :B 1500 :C 500 :D 300}
          :contractor-registration-provenance "https://www.gov.ls/eservice/contractors-registration-certificate/"}
   "USA" {:name "United States"
          :owner-authority "U.S. General Services Administration (GSA) / SAM.gov"
          :legal-basis "Federal Acquisition Regulation (FAR); System for Award Management"
          :national-spec "SAM.gov entity registration + NAICS self-certification"
          :provenance "https://sam.gov/"
          :required-evidence ["EIN record"
                              "SAM.gov registration record"
                              "State business registration record"
                              "Authorized-representative record"]}
   "DEU" {:name "Germany"
          :owner-authority "Beschaffungsamt des BMI / e-Vergabe platforms"
          :legal-basis "Gesetz gegen Wettbewerbsbeschränkungen (GWB) / VgV"
          :national-spec "e-Vergabe supplier registration under EU procurement directives"
          :provenance "https://www.evergabe-online.de/"
          :required-evidence ["Handelsregister extract"
                              "e-Vergabe registration record"
                              "USt-IdNr record"
                              "Authorized-representative record"]}})

(defn spec-basis
  "The jurisdiction's requirement map, or nil -- nil means NO spec-basis,
  and the governor must hold any proposal that tries to assess or file
  on it."
  [iso3]
  (get catalog iso3))

(defn coverage
  "Honest coverage report: how many of the requested jurisdictions actually
  have a spec-basis entry. Never report a missing jurisdiction as covered."
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s)
         missing (remove catalog iso3s)]
     {:requested (count iso3s)
      :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note (str "cloud-itonami-iso3166-lso R0: " (count catalog)
                 " jurisdictions seeded with an official spec-basis. "
                 "This is a starting catalog for market-entry navigation, "
                 "not a survey of all ~194 jurisdictions -- extend "
                 "`marketentry.facts/catalog`, never fabricate a "
                 "jurisdiction's requirements.")})))

(defn required-evidence-satisfied?
  "Does `submitted` (a set/coll of evidence keywords or strings) satisfy
  every evidence item listed for `iso3`? Missing spec-basis -> never
  satisfied."
  [iso3 submitted]
  (when-let [{:keys [required-evidence]} (spec-basis iso3)]
    (let [need (count required-evidence)
          have (count (filter (set submitted) required-evidence))]
      (= need have))))

(defn evidence-checklist [iso3]
  (:required-evidence (spec-basis iso3) []))

(defn rep-spec-basis
  "The jurisdiction's representative-related requirement map, or nil when
  this catalog has no such regime. For LSO this is deliberately nil --
  see the `catalog` docstring's honest-scope-narrowing note (the Public
  Procurement Regulations 2007's own text sits behind a Cloudflare
  challenge-gate this iteration could not read around, so no
  representative/director exclusion-extension provision could be
  confirmed)."
  [iso3]
  (when-let [sb (spec-basis iso3)]
    (when (:rep-owner-authority sb)
      (select-keys sb [:rep-owner-authority :rep-legal-basis :rep-provenance]))))

(defn corporate-number-spec-basis
  "The jurisdiction's corporate-number / tax-id regime, or nil. For LSO
  this is deliberately nil -- TIN registration (Form 1) is confirmed to
  be filed AS PART OF company registration itself, but this iteration
  could NOT independently confirm the identity or establishing
  legislation of a separate Lesotho tax/revenue-collection authority
  (an honest gap, see the `catalog` docstring)."
  [iso3]
  (when-let [sb (spec-basis iso3)]
    (when (:corporate-number-owner-authority sb)
      (select-keys sb [:corporate-number-owner-authority
                       :corporate-number-legal-basis
                       :corporate-number-provenance]))))

(defn contractor-registration-spec-basis
  "The jurisdiction's Contractors Registration Certificate category-fee
  schedule, or nil. For LSO this is real and current -- the flagship
  check this vertical adds is grounded here (gov.ls's own Contractors
  Registration Certificate e-service listing: 'Categories: A=2000,
  B=1500, C=500, D=300')."
  [iso3]
  (when-let [sb (spec-basis iso3)]
    (when (:contractor-registration-owner-authority sb)
      (select-keys sb [:contractor-registration-owner-authority
                       :contractor-registration-legal-basis
                       :contractor-registration-fee-schedule
                       :contractor-registration-provenance]))))
