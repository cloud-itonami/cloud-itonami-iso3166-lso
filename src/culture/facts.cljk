(ns culture.facts
  "Country-level regional-culture catalog for Lesotho (LSO) -- national
  dishes, protected products, beverages, crafts, festivals and heritage
  sites, per ADR-2607171400 addendum 2 (cloud-itonami-municipality-
  culture-catalog Wave 1, in com-junkawasaki/root). Sibling namespace to
  `marketentry.facts` / `statute.facts` (ADR-2607141700); city-level
  counterparts live in the cloud-itonami-municipality-* repos.

  Catalog is keyed by UPPERCASE ISO3 (mirrors `statute.facts`); entries
  carry no :culture/municipality (that attribute is city-level only).

  Every entry cites a source URL that was actually fetched and read on
  :culture/retrieved-at -- never fabricated. Summaries state only what the
  cited source confirms. An item not in this table has NO spec-basis, full
  stop; extend `catalog`, do not invent an id/url.")

(def catalog
  "iso3 -> vector of culture entries."
  {"LSO"
   [{:culture/id "lso.dish.papa"
     :culture/name "Papa"
     :culture/country "LSO"
     :culture/kind :dish
     :culture/summary "Corn-based staple dish of Basotho cuisine, comparable to ugali/polenta, typically served with accompaniments such as vegetables, beans or meat."
     :culture/url "https://en.wikipedia.org/wiki/Cuisine_of_Lesotho"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "lso.dish.likhobe"
     :culture/name "Likhobe"
     :culture/country "LSO"
     :culture/kind :dish
     :culture/summary "Basotho stew made with beans, berries and sorghum."
     :culture/url "https://en.wikipedia.org/wiki/Cuisine_of_Lesotho"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "lso.dish.oxtail-stew"
     :culture/name "Oxtail stew"
     :culture/country "LSO"
     :culture/kind :dish
     :culture/summary "Listed among the traditional foods of Basotho cuisine alongside curries and kebabs."
     :culture/url "https://en.wikipedia.org/wiki/Cuisine_of_Lesotho"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "lso.beverage.motoho"
     :culture/name "Motoho"
     :culture/country "LSO"
     :culture/kind :beverage
     :culture/summary "Fermented sorghum porridge drink of Basotho cuisine, listed among the country's traditional beverages."
     :culture/url "https://en.wikipedia.org/wiki/Cuisine_of_Lesotho"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "lso.craft.mokorotlo"
     :culture/name "Mokorotlo"
     :culture/country "LSO"
     :culture/kind :craft
     :culture/summary "Straw hat widely used for traditional Sotho clothing; the national symbol of Lesotho and the Basotho people."
     :culture/url "https://en.wikipedia.org/wiki/Mokorotlo"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "lso.craft.basotho-blanket"
     :culture/name "Basotho blanket"
     :culture/country "LSO"
     :culture/kind :craft
     :culture/summary "Distinctive woven blanket garment commonly worn by Sotho people in Lesotho and South Africa, evolved from traditional animal-skin cloaks and carrying significant ceremonial importance."
     :culture/url "https://en.wikipedia.org/wiki/Basotho_blanket"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "lso.product.mohair"
     :culture/name "Mohair"
     :culture/country "LSO"
     :culture/kind :product
     :culture/summary "Fibre from the Angora goat; Lesotho accounted for about 16% of world mohair production in 2022."
     :culture/url "https://en.wikipedia.org/wiki/Mohair"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "lso.festival.morija-arts-cultural-festival"
     :culture/name "Morija Arts & Cultural Festival"
     :culture/country "LSO"
     :culture/kind :festival
     :culture/summary "Annual event held in Morija, a village in the Maseru District of Lesotho."
     :culture/url "https://en.wikipedia.org/wiki/Morija_Arts_%26_Cultural_Festival"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "lso.heritage.sehlabathebe-national-park"
     :culture/name "Sehlabathebe National Park"
     :culture/country "LSO"
     :culture/kind :heritage
     :culture/summary "Lesotho national park added to the UNESCO World Heritage Site Tentative List in 2008 (Mixed cultural + natural category), and part of the transboundary Maloti-Drakensberg World Heritage Site."
     :culture/url "https://en.wikipedia.org/wiki/Sehlabathebe_National_Park"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}]})

(defn spec-basis [iso3] (get catalog iso3))

(defn coverage
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s)
         missing (remove catalog iso3s)]
     {:requested (count iso3s)
      :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note (str "cloud-itonami-iso3166-lso culture catalog "
                 "(ADR-2607171400 addendum 2, Wave 1): " (count (get catalog "LSO"))
                 " LSO entries, each with a fetched-and-read citation. "
                 "Extend `culture.facts/catalog`, never fabricate an id/url.")})))

(defn by-kind [iso3 kind]
  (filterv #(= (:culture/kind %) kind) (spec-basis iso3)))
