# cloud-itonami-iso3166-lso

**LSO**: Lesotho.

- Public Procurement Regulations, 2007 (Lesotho Legal Information
  Institute (LesLII), lesotholii.org) / Ministry of Finance and
  Development Planning -- supplier registration + procurement-plan
  publication
- Companies Register (Ministry of Trade & Industry, companies.org.ls) /
  One-Stop Business Facilitation Centre (OBFC) business/company
  registration, TIN registration filed as part of company registration
  (Form 1)
- gov.ls Contractors Registration Certificate category-fee schedule
  (A=2000/B=1500/C=500/D=300) gate for engagements seeking contractor
  registration

AGPL-3.0-or-later.

## Market-entry / statute catalogs

Governed public-sector market-entry compliance actor, same architecture
as every `cloud-itonami-iso3166-*` sibling in this fleet:

- `src/marketentry/{facts,governor,phase,sim,operation,registry,store,
  marketentryllm}.cljc` -- the actor. `facts.cljc` cites the Public
  Procurement Regulations, 2007 (named directly by LesLII's own
  legislation-listing page), the Ministry of Finance and Development
  Planning (own text: 'Procurement – Registration of suppliers and
  creation of database'), the Companies Register (Ministry of Trade &
  Industry, companies.org.ls, own text) and its published fee schedule,
  and gov.ls's own Contractors Registration Certificate e-service page.
  `governor.cljc`'s flagship check independently recomputes whether an
  engagement's own claimed Contractors Registration Certificate fee
  matches gov.ls's own published category-fee schedule for its own
  declared category (A/B/C/D) -- a discrete-category-to-fixed-constant
  lookup-table equality test, a check shape genuinely different from
  every other iso3166 sibling's (see the namespace docstrings for the
  full research trail and honestly-narrowed scope, including facts this
  iteration could NOT verify, such as the detailed section text of any
  cited Act -- all sit behind a Cloudflare bot-challenge on LesLII's
  individual document pages -- and the identity of a separate Lesotho
  tax/revenue-collection authority).
- `src/statute/facts.cljc` -- general-law catalog: the Companies Act,
  2011; the Labour Act, 2024 (LesLII's own listing also separately
  names an earlier Labour Code Order, 1992, relationship to the 2024
  Act unconfirmed); the Income Tax Order, 1993; and the Value Added Tax
  Act, 2001. Titles/years/URLs are confirmed directly from LesLII's own
  legislation-listing pages; detailed section text could not be
  independently confirmed this iteration (see the namespace docstring).

Every citation is curl/WebFetch-verified against an official source
(lesotholii.org, gov.ls, companies.org.ls, centralbank.org.ls,
sacu.int). LesLII's own legislation-LISTING pages loaded as ordinary
server-rendered HTML, but EVERY individual document page (where an
Act's own detailed section text would be) returned a Cloudflare
'Just a moment...' JS-challenge (HTTP 403) to both `curl` and
`WebFetch` this iteration -- an honestly-flagged ACCESS gap, not a
claim of non-existence, see `marketentry.facts`'s and `statute.facts`'s
docstrings. This iteration also found and disclosed a genuine
inconsistency across Lesotho's own official sources: companies.org.ls's
own text names 'Director Consent forms (Form 9)' while gov.ls's own
company-registration e-service page names 'Form 8 (for Directors)' --
both are reported as found, not resolved by guessing.

Lesotho is a member of the Southern African Customs Union (SACU,
confirmed directly from sacu.int's own member-state listing) and its
currency, the loti (plural maloti, symbol M), circulates alongside the
South African rand (confirmed directly from the Central Bank of
Lesotho's own currency page: 'Issuing Currency: To issue Maloti and
Rand to commercial banks to satisfy public demand for currency') --
reported as a dual-circulation fact, not an independently re-derived
Common Monetary Area peg mechanism.

## Culture catalog

Alongside the market-entry / statute catalogs, this repo carries a
**country-level regional-culture catalog** (ADR-2607171400 addendum 2,
`cloud-itonami-municipality-culture-catalog` Wave 1, in
`com-junkawasaki/root`) — national dishes, protected products, beverages,
crafts, festivals and heritage sites for Lesotho:

- `src/culture/facts.cljc` — the catalog, source of truth (keyed by
  uppercase ISO3, mirroring `statute.facts`).
- `schema/culture.edn` — DataScript schema.
- `data/culture-tx.edn` — derived DataScript tx-data (regenerated from
  the catalog, never hand-edited).

City-level counterparts live in the `cloud-itonami-municipality-*` repos.
Same provenance discipline as the compliance catalogs: every entry cites a
source URL that was actually fetched and read on `:culture/retrieved-at`;
summaries state only what the cited source confirms. An item not in
`culture.facts/catalog` has no spec-basis — never fabricate one.
