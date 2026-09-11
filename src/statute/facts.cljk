(ns statute.facts
  "General-law compliance catalog for Lesotho (LSO) -- extends this
  repo's existing `marketentry.facts` (public-procurement market-entry
  only, narrow scope) with a second, orthogonal catalog of statutes a
  company operating in this jurisdiction must generally track for
  compliance. Mirrors cloud-itonami-iso3166-jpn/-deu/-bgr/-aze/-alb/
  -arm/-atg/-ben/-btn/-bwa/-caf/-est/-gmb's `statute.facts`
  (ADR-2607141700, cloud-itonami-compliance-fact-federation).

  Every entry cites an OFFICIAL government-adjacent URL -- never
  fabricated.

  All FOUR entries below were confirmed the SAME way: this iteration
  fetched the Lesotho Legal Information Institute's (LesLII,
  `lesotholii.org` -- 'set up in 2012 ... supported by the Judiciary of
  Lesotho, the Law Office, the Law Society and other justice sector
  stakeholders', its own About text) own legislation-LISTING pages
  directly (`lesotholii.org/legislation/` and `?page=2`, plain
  server-rendered HTML, HTTP 200, NOT behind any challenge) and read
  the exact anchor text + Akoma-Ntoso citation URL for each Act's row
  in the table. EVERY attempt this iteration made to fetch the
  individual DOCUMENT page for any of these four Acts' own detailed
  section text returned Cloudflare's own 'Just a moment...'
  JS-challenge shell (HTTP 403, confirmed by inspecting the raw
  response body) to both `curl` (including a warmed-up cookie session
  with a Referer header) and `WebFetch` -- an HONEST, explicitly-
  flagged ACCESS gap (a bot-challenge on LesLII's individual-document
  route specifically, not the listing route), not a claim that these
  Acts' text does not exist. Confidence is therefore MODERATE on each
  citation's exact title/year (independently read from LesLII's own
  listing table) and LOW on any section-level claim -- accordingly, NO
  section numbers are cited below, only the Act's own title/year/URL.

  - Company/commercial-entity law: 'Companies Act, 2011'
    (`lesotholii.org/akn/ls/act/2011/18/eng@2011-09-02`). This is a
    stronger citation than this family's own GMB catalog was able to
    confirm for The Gambia (GMB's catalog explicitly carries NO
    Companies Act entry, an honest gap) -- Lesotho's own legal-
    information institute names this Act directly by title and year.
  - Labour law: 'Labour Act, 2024'
    (`lesotholii.org/akn/ls/act/2024/3/eng@2024-04-02`). LesLII's SAME
    legislation-listing page ALSO separately lists 'Labour Code Order,
    1992' (`lesotholii.org/akn/ls/act/ord/1992/24/eng@1992-12-31') --
    this iteration could NOT independently confirm (full text
    inaccessible, see above) whether the 2024 Act supersedes, amends,
    or coexists alongside the 1992 Order; this catalog cites the MORE
    RECENT 'Labour Act, 2024' as the primary current labour-law entry
    and reports the earlier Order's existence here as an honestly-
    flagged, relationship-unconfirmed related citation, rather than
    guessing which one governs today.
  - Tax law: TWO separate tax Acts are named directly by LesLII's own
    listing (unlike this family's GMB catalog, which found a single
    combined Income-and-VAT Act for The Gambia): 'Income Tax Order,
    1993' (`lesotholii.org/akn/ls/act/ord/1993/9/eng@1993-04-01`) and
    'Value Added Tax Act, 2001'
    (`lesotholii.org/akn/ls/act/2001/9/eng@2001-12-31`). Both are
    listed here under topic `:tax`.

  A law not in this table has NO spec-basis, full stop; extend
  `catalog`, do not invent an id/url.")

(def catalog
  "iso3 -> vector of statute entries. `:statute/url` + `:statute/law-number`
  are the citation the governor requires before any compliance-fact
  proposal referencing this law can commit. LSO's catalog has 4
  entries -- richer than some siblings' (GMB has 2) because LesLII's
  own listing directly named all four Acts by title/year, even though
  (like every sibling in this family with an access gap) the detailed
  section TEXT of each could not be independently confirmed this
  iteration (see namespace docstring)."
  {"LSO"
   [{:statute/id "lso.companies-act-2011"
     :statute/title "Companies Act, 2011"
     :statute/jurisdiction "LSO"
     :statute/kind :law
     :statute/law-number "Companies Act, 2011 (title + year confirmed directly from the Lesotho Legal Information Institute's (LesLII) own legislation-listing table, lesotholii.org/legislation/, plain server-rendered HTML fetched directly; the Act's own detailed section text sits behind a Cloudflare JS-challenge this iteration could not read around -- see namespace docstring for the full access-gap discipline)"
     :statute/url "https://www.lesotholii.org/akn/ls/act/2011/18/eng@2011-09-02"
     :statute/url-provenance :official-lesotholii-org
     :statute/enacted-date "2011"
     :statute/retrieved-at "2026-07-23"
     :statute/topic #{:corporate-governance}}
    {:statute/id "lso.labour-act-2024"
     :statute/title "Labour Act, 2024"
     :statute/jurisdiction "LSO"
     :statute/kind :law
     :statute/law-number "Labour Act, 2024 (title + year confirmed directly from LesLII's own legislation-listing table, lesotholii.org/legislation/, fetched directly. LesLII's SAME listing page ALSO separately names an earlier 'Labour Code Order, 1992' -- this iteration could NOT independently confirm whether the 2024 Act supersedes, amends, or coexists with the 1992 Order (detailed text access-gapped, see namespace docstring); this entry cites the more recent Act as the primary current citation and reports the earlier Order's existence here as an honest, relationship-unconfirmed related finding, not resolved by guessing)"
     :statute/url "https://www.lesotholii.org/akn/ls/act/2024/3/eng@2024-04-02"
     :statute/url-provenance :official-lesotholii-org
     :statute/enacted-date "2024"
     :statute/retrieved-at "2026-07-23"
     :statute/topic #{:labor}}
    {:statute/id "lso.income-tax-order-1993"
     :statute/title "Income Tax Order, 1993"
     :statute/jurisdiction "LSO"
     :statute/kind :law
     :statute/law-number "Income Tax Order, 1993 (title + year confirmed directly from LesLII's own legislation-listing table, lesotholii.org/legislation/, fetched directly. This iteration did not independently fetch the Order's own primary statutory text, only LesLII's own citation of its title/year, so exact section numbers are not claimed here)"
     :statute/url "https://www.lesotholii.org/akn/ls/act/ord/1993/9/eng@1993-04-01"
     :statute/url-provenance :official-lesotholii-org
     :statute/enacted-date "1993"
     :statute/retrieved-at "2026-07-23"
     :statute/topic #{:tax}}
    {:statute/id "lso.value-added-tax-act-2001"
     :statute/title "Value Added Tax Act, 2001"
     :statute/jurisdiction "LSO"
     :statute/kind :law
     :statute/law-number "Value Added Tax Act, 2001 (title + year confirmed directly from LesLII's own legislation-listing table, lesotholii.org/legislation/?page=2, fetched directly; a SEPARATE Act from the Income Tax Order, 1993 above -- unlike this family's GMB catalog, which found a single combined Income-and-VAT Act for The Gambia, Lesotho's own legal-information institute lists these as two distinct Acts. This iteration did not independently fetch the Act's own primary statutory text, only LesLII's own citation of its title/year)"
     :statute/url "https://www.lesotholii.org/akn/ls/act/2001/9/eng@2001-12-31"
     :statute/url-provenance :official-lesotholii-org
     :statute/enacted-date "2001"
     :statute/retrieved-at "2026-07-23"
     :statute/topic #{:tax}}]})

(defn spec-basis
  "The jurisdiction's statute vector, or nil -- nil means NO spec-basis
  for that jurisdiction yet."
  [iso3]
  (get catalog iso3))

(defn coverage
  "Honest coverage report, same shape/discipline as `marketentry.facts/coverage`:
  never report a missing jurisdiction as covered."
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s)
         missing (remove catalog iso3s)]
     {:requested (count iso3s)
      :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note (str "cloud-itonami-iso3166-lso statute.facts Wave 0 (ADR-2607141700): "
                 (count (get catalog "LSO")) " LSO statute(s) seeded with an "
                 "official title/year/URL citation (section-level text access-"
                 "gapped by a Cloudflare JS-challenge on LesLII's individual "
                 "document pages -- an honest gap, see namespace docstring). "
                 "Extend `statute.facts/catalog`, never fabricate a law-id or "
                 "URL.")})))

(defn by-topic
  "Statutes for `iso3` tagged with `topic` (e.g. :labor, :tax)."
  [iso3 topic]
  (filterv #(contains? (:statute/topic %) topic) (spec-basis iso3)))
