# InsightBih
## Android aplikacija za prikazivanje podataka sa iddeea.gov

Aplikacija prikazuje podatke sa zvanične stranice IDDEEA BiH putem javnog API-ja. Koristi četiri različita skupa podataka:

    - PersonsByRecordDate - broj osoba prema datumu upisa,
    - IssuedIDCards - broj izdatih ličnih dokumenata,
    - DiedByRequestDate - broj umrlih prema datumu prijave,
    - RegisteredVehiclesNumbers - broj registrovanih vozila.

Na početnom ekranu korisnik vidi listu dostupnih datasetova. Klikom na bilo koji dataset otvara se novi ekran gdje se mogu unijeti filteri (npr. mjesec, godina, entitet) i izvršiti pretraga. Nakon toga se prikazuju rezultati u vidu liste. Svaki rezultat se može otvoriti za prikaz više informacija.
Aplikacija omogućava pretragu, filtriranje po parametrima, i pregled detalja za svaki zapis. Također postoji mogućnost spremanja favorite rezultata, koji se kasnije mogu vidjeti u posebnoj sekciji "Favoriti".
Aplikacija je responzivna, što znači da se automatski prilagođava veličini ekrana. Također podržava rotaciju ekrana – ako korisnik rotira uređaj, stanje ostaje sačuvano (npr. tekst u polju za pretragu ne nestaje). To je omogućeno korištenjem SavedStateHandle u ViewModelima.
Aplikacija koristi Jetpack Compose, pa se korisnički interfejs automatski ažurira kada se promijeni stanje (npr. kad dođu novi podaci ili korisnik unese filter). Podaci se dobavljaju putem Retrofit biblioteke, a lokalno se čuvaju u Room bazi ako API poziv ne uspije (npr. nema interneta). Na taj način aplikacija može prikazati podatke čak i kada je offline.
Za upravljanje podacima koristi se arhitektura MVVM, pa je kod podijeljen na View, ViewModel i Repository. 

