package pl.tlasica.okazje;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import android.util.Log;
import android.util.SparseArray;

public class Occasions {

	private static final String TAG = "OCCASIONS";
	private int 							currDayNum;		//MMDD (bo lata przestępne)
	private SparseArray<List<String>>	 	occasions;
	List<String>							currDayOccasions;
	Random									random;
	List<String>							NO_OCCASIONS = new ArrayList<String>();
	private int 							lastOccasionIndex = 0;
	
	public Occasions() {
		occasions = new SparseArray< List<String> >();
		random = new Random( Calendar.getInstance().getTimeInMillis() );
		initData();
		NO_OCCASIONS.add("Za update aplikacji!");
		currDayNum = 0;
	}
	
	public String getRandomOccasion(Calendar cal) {
		int num = Occasions.dateToDayNum(cal);
		if (currDayNum != num) {
			prepareDayForBrowsing( num );
			lastOccasionIndex = 0;
		}
		String occ = currDayOccasions.get( lastOccasionIndex );
		nextOccasion();
		return occ;
	}
	
	private void prepareDayForBrowsing(int num) {
		Log.d(TAG, "prepare random for " + String.valueOf( num ));
		currDayNum = num;
		currDayOccasions = occasions.get( num );
		if (currDayOccasions == null) {
			currDayOccasions = NO_OCCASIONS;
		}
		java.util.Collections.shuffle( currDayOccasions );			
	}
	
	private void nextOccasion() {
		lastOccasionIndex++;
		if (lastOccasionIndex>=currDayOccasions.size()) {
			lastOccasionIndex = 0;
		}
	}
	
	
	private void initData() {
		Log.i("OCCASIONS INIT", "Start");
		
		setDay(703);
		add("Imieniny Heliodora");
		add("Imieniny Tryfona");
		add("Imieniny Tomasza");
		add("Imieniny Anatola");
		add("Imieniny Jacka i Józefa");
		add("Dzień Niepodległości Białorusi");
		add("W roku 1519 Karniszyn uzyskał prawa miejskie");
		add("W 1921 w Lublinie odbył się ślub Bolesława Bieruta");
		add("W 1961 urodził się 30-milionowy obywatel Polski");
		add("W 1995 Marian Dziurowicz został prezesem PZPN");
		add("W 1996 odbyła się premiera filmu \"Dzień Niepodległości\"");
		add("W 1757 urodził się Jan Paweł Woronicz");
		add("W 1883 urodził się Franz Kafka");
		add("W 1928 urodził się jan Machulski");
		
		setDay(704);
		add("Imieniny Aggeusza i Anatazego");
		add("Imieniny Hiacynta i Innocentego");
		add("Imieniny Jacka i Jacentego");
		add("Imieniny Malwiny");
		add("Imieniny Andrzeja");
		add("Imieniny Elżbiety");
		add("Imieniny Piotra");
		add("Dzień Wolności Rwandy");
		add("Dzień Niepodległości Stanów Zjednoczonych Ameryki");
		add("Dzień Przyjaźni Filipińsko-Amerykańskiej");
		add("W 1364 Barczewo uzyskało prawa miejskie");
		add("W 1633 zwycięstwo wojsk polskich w bitwie pod Sasowym Rogiem");
		add("W 1945 założono klub sportowy Pogoń Lębork");
		add("W 1054 w gwiazdozbiorze Byka wybuchła supernowa SN 1054");
		add("W 1776 uchwalono Deklarację Niepodległości Stanów Zjednoczonych");
		add("W 1865 ukazała się powieść \"Alicja W Krainie Czarów \"");
		add("W 1950 rozpoczęło nadawanie Radio Wolna Europa");
		add("W 1982 po remisie 0:0 z ZSSR reprezentacja Polski awansowała do półfinału mundialu w Hiszpanii");
		add("W 2012 Iwane Merabiszwili został premierem Gruzji");
		add("W 1917 urodził się Manolete, hiszpański matador");
		add("W 1974 urodził się Denis Pankratow, rosyjski pływak");
		
		setDay(705);
		add("Imieniny Przybywoja i Trofima");
		add("Imieniny Antoniego");
		add("Imieniny Bartłomieja");
		add("Imieniny Cyryla");
		add("Święto Niepodległości Algierii");
		add("Święto Niepodległości Wenezueli");
		add("Święto Niepodległości Republiki Zielonego Przylądka");
		add("Dzień słowiańskich misjonarzy Cyryla i Metodego");
		add("Dzień Jedności w Zambii");
		add("W 1600 wydano akt fundacyjny Akademii Zamojskiej");
		add("W 1848 w Cieszynie ukazało się czasopismo \"Nowiny dla Ludu Wiejskiego\"");
		add("W 1921 zakończyło się III Powstanie Śląskie");
		add("W 1931 na lotnisku w Gliwicach wylądował sterowiec Graf Zeppelin");
		add("W 1993 wszedł w życie podatek VAT");
		add("W 907 Węgrzy pokonali wojska bawarskie pod Bratysławą");
		add("W 1687 Isaac Newto opublikował swoje wiekopomne dzieło");
		add("W 1946 w Paryżu zaprezentowano publicznie kostium bikini");
		add("W 1996 w Szkocji urodziła się owca Dolly");
		
		setDay(706);
		add("Imieniny Zuzanny");
		add("Imieniny Teresy");
		add("Imieniny Dominika i Chociebora");
		add("Dzień Jana Husa w Czechach");
		add("Światowy Dzień Pocałunku");
		add("Święto Niepodległości Malawi");
		add("W 1921 założono klub piłkarski Tur Turek");
		add("W 1883 w Chropaczowie uruchomiono KWK Śląsk");
		add("W 1950 utworzono województwo Opolskie");
		add("W 1189 Ryszard I Lwie Serce został królem Anglii");
		add("W 1785 Dolar został przyjęty jako waluta narodowa USA");
		add("W 1974 Polska zajęła 3. miejsce w mistrzostwach świata w RFN");
		add("W 1944 odbyła się premiera flimu \"Forrest Gump\"");
		add("W 1946 urodził się Sylwester Stallone");
		add("W 1954 urodził się Grzegorz Skawiński");

		setDay(707);
		add("Imieniny Edelburga i Gościwita");
		add("Imieniny Ilidii i Ilidiusza");
		add("Imieniny Wilibalda");
		add("Dzień Przemysłu w Tanzanii");
		add("Święto Niepodległości Wysp Salomona");
		add("W Japonii obchodzone jest święto Tanabata");
		add("W 1136 wydano Bullę Gnieźnieńską");
		add("W 2000 wystartowała MTV Polska");
		add("W 1456 Joanna d'Arc została pośmiertnie oczyszczona z zarzutu herezji");
		add("W 1874 urodził się Władysław Grabski");
		add("W 1983 urodził się Krzysztof Lijewski");
	
		setDay(708);
		add("Imieniny Chwalimira i Falibora");
		add("Imieniny Hadriany i Hadriana");
		add("Imieniny Prokopa");
		add("W 1280 Przemków uzyskał prawa miejskie");
		add("W 1357 Łeba uzyskała prawa miejskie");
		add("W 1659 zwycięstwo wojsk polsko-kozacko-tatarskich nad rosyjskimi w bitwie pod Konotopem");
		add("W 975 Edward Męczennik został królem Anglii.");
		add("W 1497 Vasco da Gama wyruszył w poszukiwaniu drogi morskiej do Indii.");
		add("W 1714 brytyjski parlament powołał Komisję Długości Geograficznej.");
		add("W 1889 wkazało się pierwsze wydanie The Wall Street Journal.");
		add("W 1963 założono klub piłkarski PAS Teheran.");
		add("W 1979 została odkryta Adrastea, księżyc Jowisza.");
		add("W 1997 Węgry, Czechy i Polska zostały zaproszone do NATO.");
		add("W 2007 w Seattle został zaprezentowany Boeing 787 Dreamliner.");
		add("W 1621 urodził się Jean de La Fontaine, francuski pisarz");
		add("1838 urodził się Ferdinand von Zeppelin, niemiecki konstruktor sterowców");
		add("1847 urodził się František Křižík, czeski wynalazca");
		add("W 1892 urodził się Nikołaj Polikarpow, rosyjski konstruktor lotniczy");
		add("W 1978 urodził się Robert Burneika, litewski kulturysta");

		setDay(709);
		add("Imieniny Barbary");
		add("Imieniny Wszebąda");
		add("Imieniny Zenony i Zenona");
		add("Imieniny Brykcjusza");
		add("Święto Niepodległości Argentyny");
		add("Dzień Partenitu na Ukrainie i Krymie");
		add("W 1521 na Wawelu został zawieszony dzwon Zygmunt");
		add("W 1807 utworzono Wolne Miasto Gdańsk");
		add("W 1922 otwarto schronisko PTTK na Stożku");
		add("W 1923 założono klub piłkarski Szczakowianka Jaworzno");
		add("W 2007 Andrzej Lepper został zdymisjonowany po aferze gruntowej");
		add("W 455 Awitus został cesarzem rzymskim");
		add("W 1877 rozpoczął się 1. turniej tenisowy na Wimbledonie");
		add("W 1971 przyjęto flagę Kataru");
		add("W 2007 po 89 latach w Buenos Aires spadł śnieg");

		setDay(710);
		add("Imieniny Alma i Amalberga");
		add("Imieniny Racimira, Rufina i Rzędziwoja");
		add("Imieniny Sekundy");
		add("Imieniny Witalisa i Zacheusza");
		add("Święto Niepodległości Wysp Bahama");
		add("Święto Sił Zbrojnych na Mauritiusie");
		add("W 1649 rozpoczęła się obrona Zbaraża");
		add("W 1863 zwycięstwo powstańców w bitwie pod Ossą");
		add("W 1865 założono we Wrocławiu ogród zoologiczny");
		add("W 2008 na Politechnice Wrocławskiej uruchomiono polski superkomputer Nova");
		add("W 1922 Muhammad VI został bejem Tunisu");
		add("W 1994 Aleksandar Lukaszenka został prezydentem Białorusi");
		add("W 1509 urodził się Jan Kalwin");
		add("W 1856 urodził się Nikola Tesla, serbski fizyk");
		add("W 1925 urodził się Edmund Niziurski");
		add("W 2013 ukazała się wersja 1.0 aplikacji Okazje");
		
		setDay(711);
		add("Imieniny Pelagii i Pelagiusza");
		add("Imieniny Sieprawa");
		add("Imieniny Wyczesława");
		add("Imieniny Zybarta, Zyberta, Zybrachta i Zygberta");
		add("Dzień Gospelu w Kiribati");
		add("Święto Niepodległości Mongolii");
		add("Światowy Dzień Ludności");
		add("W 1928 zaprezentowano pierwszą partię 52 ciężarówek Ursus");
		add("W 1988 rozpoczęła się wizyta przywódcy ZSRR Michaiła Gorbaczowa");
		add("W 1997 założono klub piłkarski Podbeskidzie Bielsko-Biała");
		add("W 1776 James Cook wyruszył w swoją trzecią wyprawę");
		add("W 1893 japończyk Kōkichi Mikimoto uzyskał pierwszą perłę hodowlaną");
		add("W 1899 założono włoską firmę motoryzacyjną FIAT");
		add("W 1941 rozpoczęto seryjną produkcję cywilnej wersji VW Garbusa");
		add("W 1996 Andrzej Gołota stoczył 1. walkę z Riddickiem Bowe");
		add("W 2006 Microsoft zakończył wsparcie dla Windows 98");
		add("W 1942 urodził się Tomasz Stańko, polski trębacz jazzowy");
		
		setDay(712);
		add("Imieniny Bruna i Brunona");
		add("Imieniny Himisława i Imisława");
		add("Imieniny Tatomira");
		add("Imieniny Marcjanny");
		add("Dzień Oranżystów w Irlandii Północnej");
		add("Święto Niepodległości Kiribati");
		add("Święto Niepodległości - Wyspy Św. Tomasza i Książeca");
		add("W 1580 ukazało się pierwsze wydanie Biblii w języku słowiańskim");
		add("W 1894 ukazał się pierwszy numer pisma socjalistycznego \"Robotnik\"");
		add("W 1925 otwarto schronisko \"Murowaniec\" w Tatrach");
		add("W 1589 założono Carycyn (obecnie Wołgograd)");
		add("W 1870 opatentowano celuloid");
		add("W 1962 w Londynie odbył się pierwszy koncert The Rolling Stones");
		add("W 2011 Neptun zakończył pierwszy pełny o bieg wokół Słońca od czasu odkrycia w 1846");
		add("W 100 pne urodził się Juliusz Cezar");
		add("W 1774 urodził się Jozef Dekret-Matejovie, słowacki leśnik");
		add("W 1867 urodził się Karoly Kaan, węgierski leśnik");
		add("W 1971 urodził się Liroy, polski raper i producent");
		
		setDay(713);
		add("Imieniny Joela");
		add("Imieniny Świerada i Trofima");
		add("Święto Narodowe Czarnogóry");
		add("W 1429 Mława otrzymała prawa miejskie");
		add("W 1521 Kraków po raz pierwszy usłyszał głos dzwonu Zygmunt");
		add("W 1930 banderę z żaglowca STS Lwów przekazano na Dar Pomorza");
		add("W 1945 założono klub sportowy Huragan Morąg");
		add("W 1968 w Legnicy zlikwidowano komunikację tramwajową");
		add("W 1990 sformowano jednostkę do działań specjalnych GROM");
		add("W 1260 Żmudzini pokonali krzyżaków w bitwie pod Durben");
		add("W 1772 James Cook wypłynął z Plymouth w drugą podróż dookoła świata");
		add("W 1832 odkryto źródła Missisipi");
		add("W 1923 powstał napis HOLLYWOODLAND na wzgórzach niedaleko Los Angeles");
		add("W 1925 w Czechosłowacji odnaleziono figurkę Wenus z Dolních Věstonic");
		add("W 1969 została wystrzelona radziecka sonda księżycowa Łuna-15.");
		add("W 1973 ukazał się debiutancki album brytyjskiej grupy Queen");
		add("W 1944 urodził się Ernő Rubik, węgierski matematyk");
		add("W 1951 urodził się Marek Piekarczyk, polski wokalista zespołu TSA");
		add("W 1986 urodził się Ganbatyn Dżarglanczuluun, mongolski łyżwiarz szybki");
		
		setDay(714);
		add("Imieniny Bonawentury");
		add("Imieniny Donalda");
		add("Imieniny Heraklesa");
		add("Dzień Zdobycia Bastylii we Francji");
		add("Swięto Narodowe Iraku");
		add("W 1893 we Wrocławiu uruchomiono pierwszą linię tramwaju elektrycznego");
		add("W 1894 we Lwowie odbył się pierwszy mecz piłkarski na ziemiach polskich");
		add("W 1795 Marsylianka została ogłoszona hymnem Francji");
		add("W 1789 zdobycie i zburzenie Bastylii rozpoczęło Rewolucję Francuską");
		add("W 1832 w USA zwolniono handel opium z podatków federalnych.");
		add("W 1867 Alfred Nobel po raz pierwszy publicznie zaprezentował dynamit");
		add("W 1881 rewolwerowiec Billy Kid został zastrzelony przez Pata Garretta");
		add("W 1976 zakończono budowę linii kolejowej łączącej Tanzanię z Zambią");
		add("W 1862 urodził się Gustav Klimt");
		add("W 1971 urodził się Howard Webb, angielski sędzia piłkarski");
		
		setDay(715);
		add("Imieniny Antiocha i Cyriaka");
		add("Imieniny Pompiliusza");
		add("Imieniny Henryka");
		add("Imieniny Anny");
		add("Światowy Dzień bez Telefonu Komórkowego");
		add("W 1410 armia polsko-litewska zbiła okrutnie krzyżaków pod Grunwaldem");
		add("W 1899 Józef Piłsudski ożenił się z Marią Juszkiewicz");
		add("W 1924 liczna mieszkańców Katowic przekroczyła 100 tys.");
		add("W 1960 odbyła się premiera filmu \"Krzyżacy\"");
		add("W 1969 ogłoszono amnestię");
		add("W 1995 rozpoczął się pierwszy Przystanek Woodstock");
		add("W 1783 we Francji został wypróbowany pierwszy statek parowy");
		add("W 1873 został założony w Glasgow klub piłkarski Rangers FC");
		add("W 1969 Tu-144 jako pierwszy komercyjny samolot przekroczył prędkość 2 machów");
		add("W 1988 premiera filmu \"Szklana Pułapka\"");
		add("W 1606 urodził się Rembrandt Harmenszoon van Rijn");
		add("W 1993 urodził się Jaka Hvala, słoweński skoczek narciarski");

		setDay(716);
		add("Imieniny Atenogenesa");
		add("Imieniny Carmen");
		add("Imieniny Dzierżysława i Dzirżysława");
		add("Imieniny Fausta i Stefana");
		add("W 1903 rozpoczęto budowę szlaku \"Orla Perć\"");
		add("W 1922 podspiano akt przyłączenia Górnego Śląska do Polski");
		add("W 622 rozpoczął bieg kalendarz muzułmański");
		add("W 1909 poowstała firma Audi");
		add("W 1935 w Oklahoma City zainstalowano pierwsze na świecie parkometry");
		add("W 1990 ukraiński parlament ogłosił deklarację suwerenności");
		add("W 1872 urodził się Roald Amundsen");
		add("W 1928 urodził się Andrzej Zawada, polski himalaista");
		add("W 1957 urodził się Włodzimierz Smolarek, polski piłkarz");
		
		setDay(717);
		add("Imieniny Akwilina");
		add("Imieniny Dzierżykraja");
		add("Imieniny Januarii");
		add("Imieniny Sekundy]");
		add("Imieniny Teodozego i Teodozjusza");
		add("Dzień Konstytucji w Korei Południowej");
		add("Urodziny Króla Lesotho");
		add("W 1952 do Gdańska wjechał pierwszy pociąg");
		add("W 1986 ostatnia w historii Polski amnestia więźniów politycznych");
		add("W 1695 założono Bank of Scotland");
		add("W 1821 Hiszpania sprzedała Florydę Stanom Zjednoczonym");
		add("W 1955 w Anaheim otwarto pierwszy Disneyland");
		add("W 1995 uruchomiono system GPS");
		add("W 1937 urodził się Stanisław Tym");
		add("W 1958 urodził się Helmut Krieger, polski kulomiot");

		setDay(718);
		add("Imieniny Dziwigora");
		add("Imieniny Prymitywa");
		add("Imieniny Symforoza");
		add("Imieniny Drogomiła i Drogomira");
		add("Międzynarodowy Dzień Nelsona Mandeli");
		add("Święto Proklamowania Pierwszej Konstytucji w Urugwaju");
		add("W 1362 Koło otrzymało prawa miejskie");
		add("W 1897 otwarto schronisko PTTK na Szyndzielni");
		add("W 2003 wyłączono na zawsze ostatni pracujący komputer Odra 1305");
		add("W 1325 władca Azteków Tenoch założył stolicę Tenochtitlan");
		add("W 1898 Maria Skłodowska-Curie z mężem poinformowali o odkryciu Polonu");
		add("W 1968 powstała korporacja Intel");
		add("W 1872 urodził się Julius Fucik, czeski kompozytor");
		add("W 1958 urodziła się Renata Beger");
		
		setDay(719);
		add("Mjanna - Dzień Męczenników");
		add("Imieniny Epafrasa");
		add("Imieniny Symmacha");
		add("Imieniny Zdziesuła");
		add("W 1569 książę pruski Albrecht złożył hołd lenny królowi Polski");
		add("W 64 zakończył się wielki pożar Rzymu");
		add("W 1900 w Paryżu otwarto pierwszą linię metra");
		add("W 1908 założono Feyenoord Rotterdam");
		add("W 1949 Laos uzyskał niepodległość");
		add("W 1954 ukazał się debiutancki singiel Elvisa Presleya");
		add("W 1922 urodził się Tuanku Jaafar, malezyjski dyplomata i król Malezji");
		add("W 1934 urodził się Janusz Christa, rysownik i autor komiksów ");
		
		setDay(720);
		add("Imieniny Ansegiza");
		add("Imieniny Modesta");
		add("Imieniny Heliasza, Eliasza i Flawiana");
		add("Imieniny Sewera i Stosława");
		add("Dzień Przyjaźni w Argentynie");
		add("Święto Niepodległości w Kolumbi");
		add("Starosłowiańskie Święto Peruna");
		add("W 1949 w Krakowie otwarto Powszechny Dom Towarowy");
		add("W 1968 oddano do użytku zbiornik wodny w Solinie");
		add("W 1985 uruchomiono komunikację trolejbusową w Słupsku");
		add("W 514 Św. Hormizdas został papieżem");
		add("W 802 do Akwizgranu przyprowadzono słonia albinosa");
		add("W 1969 lądownik Apollo 11 wylądował na Księżycu");
		add("W 1304 urodził się Francesco Petrarka");

		setDay(721);
		add("Imieniny Arbogasta");
		add("Imieniny Praksedy");
		add("Imieniny Zotyka");
		add("Święto Niepodległości w Belgii");
		add("Święto Wyzwolenia w Guam");
		add("W 1807 proklamowano Wolne Miasto Gdańsk");
		add("W 1954 ruszyła pierwsza turbina EC Żerań");
		add("W 1973 pierwsza wolna sobota");
		add("W 1873 Jesse James dokonał swojego pierwszego napadu na pociąg");
		add("W 1964 jwiat moreli japońskiej został kwiatem państwowym Tajwanu");
		add("W 1990 odbył się konkurs The Wall Live In Berlin");
		add("Bo ciepło. Ewentualnie dlatego że leje.");

		setDay(722);
		add("Imieniny Benona i Benony");
		add("Imieniny Menelausa i Nicefora");
		add("Imieniny Platona");
		add("W Europie Dzień aproksymacji Pi");
		add("Święto Rewolucji w Gambii");
		add("W 1897 w Warszawie rozpoczęło pracę pogotowie ratunkowe");
		add("W 1949 w Warszawie oddano do użytku trasę W-Z");
		add("W 1944 we Lwowie AK rozpoczęło akcję \"Burza\"");
		add("W 1955 oddano do użytku Pałac Kultury i Nauki w Warszawie");
		add("W 1973 uruchomiono linię produkcyjną Fiata 126p");
		add("W 1983 rozwiązano WRON i zniesiono stan wojenny");
		add("W 1927 założono klub piłkarski AS Roma");
		add("W 2005 Jelena Isinbajewa osiągnęła 5m w skoku o tyczce");
		add("W 1878 urodził się Janusz Korczak, polski pedagog");
		add("W 1930 urodził się Leoncjusz Ciuciura, polski kompozytor");

		setDay(723);
		add("W starożytnym Rzymie obchodzono Neptunalia");
		add("Święto Rewolucji Lipcowej w Egipcie");
		add("Święto Konstytucji w Fidżi");
		add("Święto Rewolucji w Libii");
		add("Dzień Włóczykija w Polsce");
		add("Imieniny Olimpiusza");
		add("W 1973 wydano pierwszą pocztówkę o tematyce górskiej");
		add("W 1945 założono klub sportowy Polonia Świdnica");
		add("W 1902 odkryto kometę 26P/Grigg-Skjellerup");
		add("W 1903 w USA sprzedano pierwszego Forda");
		add("W 1985 zaprezentowano komputer Amiga 1000");
		add("W 2010 powstał boysbend \"One Direction\"");
		add("W 1989 urodził się Daniel Radcliffe, brytyjski aktor");

		setDay(724);
		add("Imieniny Gleba");
		add("Imieniny Segolena");
		add("Święto Policji w Polsce");
		add("W 1400 odbyły sie pierwsze wykłady w Collegium Maius UJ");
		add("W 1625 mianowano pierwszego Naczelnego Pocztmistrza Wrocławia");
		add("W 1978 premiera filmu \"Wodzirej\"");
		add("W 1993 ukazało się ostatnie wydanie Świata Młodych");
		add("W 1701 założono Detroit w Michigan");
		add("W 1837 na Słowacji spadł meteoryt Divina");
		add("W 1888 Jonh Boyd Dunlop opatentował oponę");
		add("W 1944 urodził się Krzysztof Pośpiech, polski dyrygent");
		
		setDay(725);
		add("Imieniny Olimpii");
		add("Dzień Św.Jakuba w Hiszpanii");
		add("Rocznica Aneksji Guanacaste na Kostaryce");
		add("Dzień Bezpiecznego Kierowcy w Polsce");
		add("Święto Republiki w Tunezji");
		add("W 1510 Florian Ungier założył pierwszą drukarnię w Polsce");
		add("W 1966 otwarto Muzemu Budownictwa Ludowego w Sanoku");
		add("W 1567 Hiszpanie zalożyli Caracas");
		add("W 1893 w Grecji otwarto Kanał Koryncki");
		add("W 1917 powstał japoński koncern Nikon");
		add("W 1980 został wydany album \"Back in Black\" AC/DC");
		add("W 1952 urodził się Effi Eitam, izraelski polityk");
		
		setDay(726);
		add("Imieniny Sancji i Symeona");
		add("Święto Rewolucji na Kubie");
		add("Nowy Rok w kalendarzu Majów");
		add("Święto Niepodległości w Liberii i na Malediwach");
		add("W 1974 premiera 1. odcinka serialu \"Janosik\"");
		add("W 811 Bułgarzy pokonali wojska Bizancjum w bitwie na przełęczy Wyrbica");
		add("W 1973 premiera filmu \"Wejście Smoka\"");
		add("W 1846 urodził się Texas Jack Omohundro, amerykański kowboj");
		add("W 1925 urodził się Zdenek Smetana, czeski twórca filmów animowanych");

		setDay(727);
		add("Imieniny Antuza");
		add("Imieniny Liliozy");
		add("Imieniny Wszebora");
		add("Dzień Zwycięstwa w Korei Północnej");
		add("2. Dzień Święta Rewolucji na Kubie");
		add("W 1692 Białystok uzyskał prawa miejskie");
		add("W 1939 podniesienie bandery na \"MS Chrobry\"");
		add("W 1054 król Szkocji Makbet został pokonany przez Siwarda z Northumbrii");
		add("W 1694 powstał Bank of england");
		add("W 1940 miał miejsce debiut ekranowy Królika Bugsa");
		add("W 1983 Madonna wydała debiutancki album");
		
		setDay(728);
		add("Imieniny Achacjusza i Achacego");
		add("Światowy Dzień Wirusowego Zapalenia Wątroby (wypada nie pić)");
		add("Święto Niepodległości w Peru");
		add("Rocznica Upadku Faszyzmu w San Marino");
		add("W 1243 powstała diecezja chełmińska");
		add("W 1971 powstał klub piłkarski Miedź Legnica");
		add("W 1950 uruchomiono komunikację trolejbusową w Krasnodarze");
		add("W 1993 Andora została przyjęta do ONZ");

		setDay(729);
		add("Imieniny Beatrycze");
		add("Imieniny Cirzpiboga i Cirzpisława");
		add("Imieniny Prospera");
		add("Imieniny Simplycego i Symplicjusza");
		add("Święto narodowe Wysp Owczych");
		add("W 1423 Łódź otrzymała prawa miejskie, w tym prawo targów w środy");
		add("W 1683 Jan III Sobieski wyruszył pod Wiedeń");
		add("W 1921 w Prószkowie pod Opolem zanotowano +40,2c");
		add("W 1973 premiera filmu \"Wniebowzięci\"");
		add("W 1095 Koloman I Uczony został królem Węgier");
		add("W 1958 w Waszyngtonie utworzono NASA");
		
		setDay(730);
		add("Imieniny Ingeborgi");
		add("Imieniny Swojsława i Ubysława");
		add("Święto Tronu w Maroko");
		add("Międzynarodowy Dzień Koleżeństwa");
		add("Święto Niepodległości w Vanuatu");
		add("W 1535 wojska polskie rozpoczęły oblężenie Staroduba");
		add("W 1882 otwarto Dworzec Tatrzański w Zakopanem");
		add("W 762 Kalif Al-Mansur założył Bagdad");
		add("W 1619 w Jamestown wybuchł \"strajk polskich rzemieślników\", 1. strajk w Ameryce");
		add("W 1733 w Bostonie powstała pierwsza amerykańska loża masońska");
		add("W 1976 polscy siatkarze pokonali ZSSR 3:2 w finale turnieju olimpijskiego w Montrealu");
		add("W 1980 Władysław Kozakiewicz wykonał słynny gest");
		add("W 2003 w Meksyku wyprodukowano ostatniego VV Garbusa");
		add("W 1963 urodził się Arnold Schwarzenegger");
		add("W 1974 urodził się Jacek Dukaj");
		
		setDay(731);
		add("Imieniny Beata (tak, tak)");
		add("Dzień Flagi na Hawajach");
		add("Dzień Skarbowości w Polsce");
		add("U Celtów wigilia Lughnasadh");
		add("W 1932 w Gdyni po raz pierwszy obchodzono Święto Morza");
		add("W 1959 wydano zarządzenie, wg którego poniedziałek stał się \"dniem bezmięsnym\"");
		add("W 1993 założono Polski Związek Golfa");
		add("W 1928 Halina Konopacka zdobyła 1. w historii medal olimpijski dla Polski");
		add("W 1992 Gruzja została przyjęta do ONZ");
		add("W 2006 Fidel Castro Ruz przekazał tymczasowo władzę bratu");
		add("W 1704 urodził się Gabriel Cramer, szwajcarski matematyk");
		add("W 1973 urodził się Ebenezer Dorkutso Dougbatey, ghanijski piłkarz");
		
		addAugust();
		addSeptember();
		
		Log.i("OCCASIONS INIT", "Stop");

	}
	
	private void addAugust() {
		setDay(801);
		add("Imieniny Eleazara i Etewolda");
		add("Imieniny Nadziei");
		add("Imieniny Nemezego i Nemezjusza");
		add("Imieniny Wiercisława");
		add("Dzień Wyzwolenia na Barbadosie");
		add("Święto Armii w Chińskiej Republice Ludowej");
		add("Dzień Rodziców w Demokratycznej Republice Konga");
		add("Narodowy Dzień Pamięci Powstania Warszawskiego");
		add("W 1293 założono miasto Twardogóra");
		add("W 1827 uruchomiono latarnię morską na Helu");
		add("W 1834 w Krakowie zakazano w wypasu bydła na Plantach");
		add("W 1989 zniesiono kartki na mięso");
		add("W 1498 Krzysztof Kolumb dotarł do Wenezueli");
		add("W 1861 w The Times opublikowano pierwszą na świecie prognozę pogody");
		add("W 1793 we Francji wprowadzono system metryczny");
		add("W 1987 wystartowała MTV Europe");
		add("W 1972 urodził się Matumona Lundala, angolski piłkarz");
		
		setDay(802);
		add("Imieniny Rutyliusza");
		add("Święto Dziewicy Los Angeles, patronki Kostaryki");
		add("Święto Niepodległości Republiki Macedońskiej");
		add("W 1299 Białogard otrzymał prawa miejskie");
		add("W 1610 Henry Hudson odkrył Zatokę Hudsona");
		add("W 1940 sformowano w Anglii Dywizjon 303");
		add("Marnie z okazjami, warto wypić aby coś się ciekawego wydarzyło");

		setDay(803);
		add("Imieniny Cyry");
		add("Imieniny Dalmacego i Eufroniusza");
		add("Święto Sił Zbrojnych w Gwinei Równikowej");
		add("Dzień Flagi w Wenezueli");
		add("W 1873 założono Galicyjskie Towarzystwo Tatrzańskie");
		add("W 2010 krzyżowcy stawili opór siłom zła pod Pałacem Prezydenckim");
		add("W 1778 otwarto teatr La Scala w Mediolanie");
		add("W 1947 uruchomiono komunikację tramwajową w Irkucku");
		add("W 1941 urodził się Grzegorz Rosiński, polski rysownik");
		add("W 1974 urodził się Iwan Wyrypajew, rosyjski aktor");
		
		setDay(804);
		add("Imieniny Mironiega");
		add("Imieniny Pęcisława i Pękosława");
		add("Imieniny Tertuliana i Tertuliany");
		add("Święto Rewolucji w Burkina Faso");
		add("1. dzień festiwalu San Salwador");
		add("W 1260 Bulla papieska przyznała prawo organizowania jarmarków św. Dominika");
		add("W 2003 powstał Krajowy Rejestr Długów");
		add("W 1818 Antoni Malczewski jako pierwszy polak zdobył Mont Blanc");
		add("W 1881 w Sewilli odnotowano 50 °C");
		add("W 2009 Boggy Pick na Karaibach został przemianowany na Mount Obama");
		add("W 1904 urodził się Witold Gombrowicz");
		
		setDay(805);
		add("imieniny Cyriaka");
		add("Imieniny Memiusza i Nonny");
		add("Imieniny Wenantego");
		add("Dzień Niepodległości w Burkina Faso");
		add("Dzień Zwycięstwa i Dumy Państwowej w Chorwacji");
		add("Międzynarodowy Dzień Piwa (i Piwowara)");
		add("W 1358 Będzin uzyskał prawa miejskie");
		add("W 1923 założono klub piłkarski Concordia Knurów");
		add("W 1976 powstało Grudziądzkie Towarzystwo Kultury");
		add("W 1861 w USA zniesiono karę chłosty");
		add("W 1914 w Cleveland uruchomiono pierwszą elektryczną sygnalizację świetlną");
		add("W 1957 w Tanzanii spadł meteoryt Ufana");
		add("W 1461 urodził się Aleksander Jagiellończyk, król Polski");
		add("W 1921 urodził się Roman Bratny, polski pisarz, ponoć twardy");

		setDay(806);
		add("Imieniny Felicysyma");
		add("Imieniny Justa");
		add("Święto Niepodległości na Jamajce");
		add("Święto Niepodległości w Boliwii");
		add("W 1847 na stacje w Katowicach wjechał pierwszy pociąg osobowy");
		add("W 1919 upadła Węgierska Republika Rad");
		add("W 1997 Microsoft kupił wart 150mln $ pakiet akcji Apple Computer");

		setDay(807);
		add("Imieniny Agantangela");
		add("Imieniny Andromedy");
		add("Imieniny Dobiemiara");
		add("Święto Republiki w Wybrzeżu Kości Słoniowej");
		add("W 1927 otwarto Stadion Miejski w Kaliszu");
		add("W 1856 do Australii przybyli pierwsi polscy osadnicy");
		add("W 2006 Apple zakończył stosowanie procesorów PowerPC");
		add("W 1958 urodził się Bruce Dickinson");
		add("W 1972 urodziła się Dorota Deląg, siostra Pawła Deląga");
		
		setDay(808);
		add("Imieniny Largusa");
		add("Imieniny Niezamysła");
		add("Imieniny Smaragda i Szmaragda");
		add("Dzień Pokoju w Iraku");
		add("Dzień Rolników w Tanzanii");
		add("W 1951 Radzionków uzyskał prawa miejskie");
		add("W 969 Dżawhar as-Sikilli założył Kair");
		add("W 1946 w Bułgarii zniesiono monarchię");
		add("W 1863 urodził się Antoni Kocyan, polski leśnik");
		
		setDay(809);
		add("Imieniny Doroteusza");
		add("Imieniny Miłorada");
		add("Narodowy Dzień Kobiet w RPA");
		add("Święto Niepodległości Singapuru");
		add("W 1762 Rosjanie opuścili twierdzę Kołobrzeg");
		add("W 1136 Pomorzanie pod wodzą księcia Racibora zdobyli norweskie miasto Konungahela");
		add("W 1962 przyjęto flagę Gabonu i hymn Mali");
		add("W 1669 urodziła się Eudoksja Łopuchina, caryca Rosji");
		add("W 1939 urodziła się Maria Czubaszek");

		setDay(810);
		add("Imieniny Prohora");
		add("Dzień Przewodnika i Ratownika Górskiego w Polsce");
		add("Święto Niepodległości Ekwadoru");
		add("W 1848 pierwszy pociąg odjechał z dworca w Poznaniu");
		add("W 1500 Diogo Diaz odkrył Madagaskar");
		add("W 2010 urodziła się Urszula Łasica");
		add("W 1793 otwarto muzeum w paryskim Luwrze");
		add("W 1899 założono klub piłkarski Viking FK (ciekawe gdzie)");
		add("W 1965 urodził sie Namdżilyn Bajarsajchan, mongolski bokser");

		setDay(811);
		add("Imieniny Tyburvji i Tyburcego");
		add("Imieniny Włosciwoja");
		add("Święto Niepodległości w Czadzie");
		add("W 1932 utworzono Białowieski Park Narodowy");
		add("W 1920 Rada Obrony Państwa ustanowiła Krzyż Walecznych");
		add("W 3114 pne wg kalendarza Majów rozpoczęła się 5. epoka dziejów ludzkości");
		add("W 1975 polska wyprawa po raz pierwszy zdobyła Gaszerbrum III (7952 m npm)");
		add("W 1892 urodził się Władysław Anders");
		add("W 1950 urodził się Steve Wozniak, współzałożyciel Apple Computer");
		add("W 1953 urodził się Hulk Hogan");
		
		setDay(812);
		add("Imieniny Bądzsława");
		add("Imieniny Eunomii");
		add("Międzynarodowy Dzień Młodzieży");
		add("Urodziny Królowej Sirikit w Tajlandii");
		add("Święto Obrony w Zambii");
		add("Święto Armii w Zimbabwe");
		add("W 1308 Lidzbark Warmiński uzyskał prawa miejskie");
		add("W 1896 w Gdańsku wyruszył na trasę pierwszy tramwaj elektryczny");
		add("W 1943 żołnierze Kedywu AK uprowadzili w Warszawie samochód bankowy ze 105 mln zł");
		add("W 1833 Chicago uzyskało prawa miasteczka, licząc 350 mieszkańców");
		add("W 1979 IBM wprowadził na rynek pierwszy model komputera osobistego");
		
		setDay(813);
		add("Imieniny Benilda");
		add("Imieniny Kasjana i Konkordii");
		add("Imieniny Sekundyna");
		add("Święto Wolnego Laosu");
		add("Międzynarodowy Dzień Osób Leworęcznych");
		add("Dzień Kobiet w Tunezji");
		add("W 1923 do portu w Gdyni zawinął pierwszy statek oceaniczny, francuski SS Kentucky");
		add("W 1976 wprowadzono kartki na cukier");
		add("W 1923 żaglowiec STS Lwów jako pierwszy pod polską banderą przepłynął równik");
		add("W 1997 wyemitowano premierowy odcinek serialu South Park");
		add("W 2008 Boliwia i Libia nawiązały stosunki dyplomatyczne");
		add("W 1873 urodził się Józef Haller, polski generał i polityk");

		setDay(814);
		add("Imieniny Dobrowoja");
		add("Imieniny Machabeusza");
		add("Święto Niepodległości w Pakistanie");
		add("Święto Przyłączenia Prowincji Wadi Eddahab w Maroko");
		add("Dzień Energetyka");
		add("W 1344 Działdowo otrzymało prawa miejskie");
		add("W 1919 założono klub piłkarski Ruch Radzionków");
		add("W 1980 rozpoczęły się strajki na Wybrzeżu");
		add("W 1040 Makbet zabił króla Szkocji Duncana I i zajął jego miejsce");
		add("W 1370 Karlowe Wary otrzymały prawa miejskie");
		add("W 1992 w Ugandzie spadł meteoryt Mbale");
		add("W 1961 urodził się Maciej Maleńczuk");
		
		setDay(815);
		add("Imieniny Alipiusza");
		add("Imieniny Trzebiemira");
		add("Imieniny Napoleona");
		add("Dzień Niepodległości w Kongo");
		add("Dzień Wyzwolenia w Korei Południowej i Północnej");
		add("Święto Wojska Polskiego");
		add("W 1843 do Szczecina wjechał pierwszy pociąg z Berlina");
		add("W 1920 Cud nad Wisłą, polska armia zatrzymała bolszewików");
		add("W 2000 oficjalnie wystartował komunikator Gadu-Gadu");
		add("W 1914 otwarto Kanał Panamski");
		add("W 1969 rozpoczął się festiwal Woodstock");
		add("W 1769 urodził się Napoleon Bonaparte");
		add("W 1938 urodził się Janusz A. Zajdel");
		add("W 1976 urodził się Agent Tomek");
		
		setDay(816);
		add("Imieniny Anioła i Arsacjusza");
		add("Imieniny Diomedesa i Eleuterii");
		add("Dzień Odrodzenia na Dominikanie");
		add("W 1794 wprowadzono do obiegu pierwsze polskie bankoty");
		add("W 1980 w Gdańsku powstał Międzyzakładowy Komitet Strajkowy");
		add("W 2010 rozpoczęła się budowa II linii metra w Warszawie");
		add("W 1809 założono Uniwersytet Humboldta w Berlinie");
		add("W 2005 Pedro Pablo Kuczyński został premierem Peru");
		add("W 1958 urodziła się Madonna");

		setDay(817);
		add("Imieniny Jaczewoja");
		add("Imieniny Liberata");
		add("Imieniny Rustyka i Rustyki");
		add("Święto Niepodległości w Gabonie");
		add("Święto Niepodległości w Indonezji");
		add("Urodziny Marcusa Garveya, założyciela Ruchu Rastafari");
		add("W 1988 Polska po raz pierwszy uzyskała połączenie internetowe ze światem");
		add("W 1991 narodził się polski Internet");
		add("W 1982 w fabryce Philipsa odbyła się prezentacja płyty kompaktowej");
		add("W 2008 podczas IO w Pekinie Michael Phelps zdobył 8. złoty medal");
		add("W 1601 urodził się Pierre de Fermat, francuski matematyk");
		add("W 1923 urodził się Chaleo Yoovidhya, twórca napoju Red Bull");
		
		setDay(818);
		add("Imieniny Agapita");
		add("Imieniny Firmina");
		add("Imieniny Tworzysława i Żyrosława");
		add("W 1587 Saul Wahl został rzekomo wybrany na króla Polski");
		add("W 1925 spółka Polskie Radio otrzymała monopol na radiofonię w Polsce");
		add("W 1587 urodziło się pierwsze w Nowym Świecie dziecko angielskich rodziców");
		add("W 1786 Reykjavik otrzymał prawa miejskie");
		add("W 1894 w Ostrawie otwarto pierwszą linię tramwaju parowego");
		add("W 1993 w Trondheim otwarto jedyny na świecie wyciąg rowerowy");
		add("W 1685 urodził się Brook Taylor, jeden z szeregu angielskich matematyków");
		add("W 1937 urodził się Edward Stachura, polski pisarz");
		add("W 1933 urodził się Roman Polański");
		add("W 1981 urodził się Lucas Vonlanthen, szwajcarski kombinator norweski");
		add("");

		setDay(819);
		add("Imieniny Ezechiela");
		add("Światowy Dzień Pomocy Humanitarnej ONZ");
		add("W 1557 Polska wypowiedziała wojnę zakonowi kawalerów mieczowych");
		add("W 1901 poświęcono Krzyż na Giewoncie");
		add("W 1908 powstał Łódzki Klub Sportowy");
		add("W 1956 Legia Warszawa pokonała Wisłę Kraków 12:0");
		add("Przyjmuje się że w tym właśnie dniu wynaleziono fotografię");
		add("W 1743 urodziła się Maria Joanna du Barry, francuska kurtyzana");
		add("W 1883 urodziła się Coco Chanel");
		
		setDay(820);
		add("Imieniny Filiberta");
		add("Imieniny Leowigilda");
		add("Imieniny Świeluba");
		add("Święto Rewolucji Króla i Ludu w Maroku");
		add("Dzień św. Stefana na Węgrzech");
		add("W 1416 Grabów nad Prosną otrzymał prawa miejskie");
		add("W 1963 w Wojskowej Akademii Technicznej uruchomiono pierwszy polski laser");
		add("W 1980 Reinhold Messner dokonał pierwszego solowego wejścia na Mount Everest");
		add("W 1991 Estonia ogłosiła niepodległość od ZSRR");
		add("W 1086 urodził się Bolesław III Krzywousty");
		add("W 1847 urodził się Bolesław Prus");
		add("W 1890 urodził się Howard P. Lovecraft, ten od macek");
		
		setDay(821);
		add("Imieniny Agapiusza");
		add("Imieniny Męcimira");
		add("W 1811 w Reszlu została spalona na stosie ostatnia europejska czarownica");
		add("W 1842 Hobart na Tasmanii otrzymało prawa miejskie");
		add("W 1972 produkcję Syreny przeniesiono z FSO do FSM");
		add("W 1789 urodził się Augustin Louis Cauchy, mający ciąg do matematyki");
		add("W 1891 urodził się Emiliano Mercado del Toro, który zmarł w 2007");
		add("W 1988 urodził się Robert Lewandowski, polski piłkarz");
		add("W 1986 urodził się Usain Bolt, jamajski sprinter");

		setDay(822);
		add("Imieniny Agatonika");
		add("Imieniny Dalegora i Fabrycjana");
		add("W 1945 powstało Polskie Stronnictwo Ludowe");
		add("W 2006 Lech Wałęsa wystąpił z NSZZ Solidarność");
		add("W 1942 Brazylia wypowiedziała wojnę Niemcom i Włochom");
		add("W 1997 miała miejsce premiera filmu Sztos");
		add("W 1932 BBC rozpoczęła eksperymentalną emisję programu telewizyjnego");

		setDay(823);
		add("Imieniny Archelausa");
		add("Imieniny Sydonii");
		add("Imieniny Teonillii");
		add("W starożytnym Rzymie czczono Vulcanalia");
		add("Dzień Wyzwolenia w Rumunii");
		add("Dzień Flagi na Ukrainie");
		add("W 1879 w Szczecinie uruchomiono komunikację tramwajową");
		add("W 1924 miała miejsce wielka opozycja Marsa");
		add("W 686 urodzi się Karol Młot");
		add("W 1843 urodził się Viktor Lorenc, pierwszy czeski taternik");
		add("W 1923 urodził się Edgar Frank Codd, normalny informatyk");

		setDay(824);
		add("Imieniny Audoena");
		add("Imieniny Cieszymira");
		add("Imieniny Natanaela");
		add("Święto Flagi w Liberii");
		add("W 1675 Jan III Sobieski pokonał tatarów pod Lesienicami");
		add("W 2001 miała miejsce premiera filmu \"Poranek kojota\"");
		add("W 410 Goci zdobyli Rzym (pijemy na smutno, to w końcu barbarzyńcy)");
		add("W 1456 zakończono pierwszy druk Biblii Gutenberga");
		add("W 1963 w RFN rozpoczął się pierwszy sezon Bundesligi");
		add("W 1995 oficjalna premiera Windows 95");
		add("W 1948 urodził się Jean-Michael Jarre");

		setDay(825);
		add("Imieniny Gaudencjusza i Gaudentego");
		add("Imieniny Kalasantego");
		add("Imieniny Peregryna");
		add("Święto Niepodległości Urugwaju");
		add("W 1599 wydano drukiem Biblię w tłumaczeniu ks. Jakuba Wujka");
		add("W 1851 rozpoczął działalność Zakład Karny Racibórz");
		add("W 1961 rozpoczął się I Festiwal Piosenki w Sopocie");
		add("W 1994 pod Warszawą spadł meteoryt Baszkówka");
		add("W 2000 padł pierwszy klaps na planie serialu \"M jak miłość\"");
		add("W 458 w Indiach w traktacie \"Lokavibhaaga\" pojawił się symbol 0 (zero)");
		add("W 1609 Galileusz zaprezentował Wenecjanom swój pierwszy teleskop");
		add("W 1718 założono Nowy Orlean w stanie Luizjana");
		add("W 1995 odbyła się premiera filmu \"Desperado\"");


		setDay(826);
		add("Imieniny Symplicjusza i Symplicego (żeby nie było łatwo)");
		add("Imieniny Wiktorianny");
		add("Dzień Bohaterów w Namibii i na Filipinach");
		add("W 1923 ukazał się pierwszy numer tygodnika \"Życie Robotnicze\"");
		add("W 2006 Przegląd Sportowy opublikował Listę Fryzjera");
		add("W 55 p.n.e Juliusz Cezar wylądował w Brytanii");
		add("W 1910 urodziła się Matka Teresa z Kalkuty");
		add("W 1792 Tadeusz Kościuszko otrzymał honorowe obywatelstwo francuskie");


		setDay(827);

		add("Imieniny Armadei");
		add("Imieniny Gebharda i Honorata");
		add("Imieniny Serapiona");
		add("W 1492 Jan Olbrach został wybrany na króla Polski");
		add("W 1595 wojska polskie pod wodzą hetmana Jana Zamoyskiego zdobyły Chocim");
		add("W 1946 założono Aeroklub Warmińsko-Mazurski");
		add("W 1971 odbyła się premiera komedii \"Nie lubie poniedziałku\"");
		add("W 479 p.n.e. Grecy pokonali Persów w bitwie pod Platejami");
		add("W 1664 powstała Francuska Kompania Wschodnioindyjska");
		add("W 1820 Josef Naus zdobył Zugspitze (2962m) - najwyższy szczyt w Niemczech");
		add("W 1859 w Pensylwanii po raz pierwszy wydobyto ropę naftową przy pomocy odwiertu");
		add("W 1955 wydano po raz pierwszy Ksiegę Rekordów Guinnessa");
		add("W 2003 Robert Korzeniowski zdobył złoty medal mistrzostw świata w chodzie na 50km");
		add("W 1471 urodził się Jerzy Brodaty, książę Saksonii");
		add("W 1858 urodził się Giuseppe Peano, włoski matematyk i logik");
		add("W 1972 urodził się Dalip Singh Rana, indyjski wrestler");

		setDay(828);

		add("Imieniny Bibiana");
		add("Imieniny Pelagiusza");
		add("Dzień Wyzwolenia w Hąkągu");
		add("Święto Polskiego Lotnictwa");
		add("W 1927 w Spale odbyły się pierwsze ogólnopolskie dożynki");
		add("W 1610 polski królewicz Władysław IV Waza został carem Rosji");
		add("W 1845 ukazało się pierwsze wydanie miesięcznika popularnonaukowego Scientific American");
		add("W 1903 założono Harley-Davidson Motor Company");
		add("W 1937 założono Toyota Motor Corporation");
		add("W 1968 Chińskie Radio Międzynarodowe nadało pierwszą audycję w języku polskim");
		add("W 1749 urodził się Johann Wolfgang von Goethe, niemiecki poeta");

		setDay(829);

		add("Imieniny Eutymiusza");
		add("Imieniny Sebbusa");
		add("Dzień Straży Gminnej");
		add("Dzień Strażnika Miejskiego");
		add("W 1833 w Wielkiej Brytanii uchwalono ustawę znoszącą niewolnictwo");
		add("W 1885 Gottlieb Daimler uzyskał patent na pierwszy motocykl");
		add("W 1997 Sejm RP przyjął ustawę o ochronie danych osobowych");
		add("W 1632 urodził się John Locke, angielski filozof ");
		add("W 1957 urodził się Grzegorz Ciechowski, polski muzyk i wokalista");
		add("W 1958 urodził się Michael Jackson");

		setDay(830);

		add("Imieniny Adaukta");
		add("Imieniny Częstowoja");
		add("Imieniny Damroka");
		add("Dzień Zwycięstwa w Turcji");
		add("W 1959 w Tatrach Zachodnich została odkryta jaskinia Śnieżna Studnia");
		add("W 1835 założono Melbourne");
		add("W 1992 Michael Schumacher wygrał swój pierwszy wyścig w F1 (Grand Prix Belgii)");
		add("W 2009 Ali Bongo Ondimba wygrał wybory prezydenckie w Gabonie");
		add("W 1875 urodził się Otokar Chlup, czeski pedagog");
		add("W 1971 urodziła się Katarzyna Nosowska, polska piosenkarka że Hey!");

		setDay(831);

		add("Imieniny Optata i Prymiana");
		add("Imieniny Solidariusza");
		add("Blog Day!");
		add("Dzień Solidarności i Wolności w Polsce");
		add("W 1820 podpisano w Stoczni Gdańskiej porozumienia sierpniowe");
		add("W 1935 radziecki górnik Aleksiej Stachanow podczas jednej zmiany wykonał 1475% normy");
		add("W 1993 ostatni rosyjscy żołnierze opuścili Litwę");
		add("W 1987 ukazał się album Bad Michaela Jacksona");		
	}

	private void addSeptember() {
		setDay(901);
		add("Imieniny Miłodziada");
		add("Imieniny Witalisa");
		add("Święto Nauczyciela w Singapurze");
		add("W 1364 Urban V wydał bullę powołującą do życia Akademię Krakowską");
		add("W 1964 utworzono Instytut Warzywnictwa im. Emila Chroboczka w Skierniewicach");
		add("W 2009 Poczta Polska w wyniku komercjalizacji została przekształcona w spółkę akcyjną");
		add("W 1886 założono szwajcarski klub sportowy Grasshoppers Zurych");
		add("W 1944 w Rio de Janeiro odsłonięto pomnik Fryderyka Chopina");
		add("W 1985 odnaleziono wrak Titanica");
		add("W 1923 urodził się Rocky Marciano, amerykański bokser");

		setDay(902);
		add("Imieniny Absalona");
		add("Imieniny Elpidii i Elpidiusza");
		add("Święto Pracy w Boliwii");
		add("W 1960 odbyła się premiera filmu historycznego \"Krzyżacy\"");
		add("W 1974 miała miejsce premiera filmu historycznego \"Potop\"");
		add("W 1859 rozpoczęła się najsilniejsza w historii burza magnetyczna");
		add("W 1900 w pruskich szkołach publicznych wprowadzono przedmiot wychowanie seksualne");
		add("W 1965 urodził się Lennox Lewis, brytyjski bokser");
		add("W 1966 urodziła się Salma Hayek, meksykańska aktorka");

		setDay(903);
		add("Imieniny Bazylisa");
		add("Imieniny Natalisa");
		add("Dzień Flagi w Australii");
		add("W 1856 na kanały mazurskie wypłynął pierwszy parowiec – Masovia");
		add("W 1910 w wielkopolskiej wsi Grzępy spadł meteoryt Grzempach");
		add("W 1935 w porcie w Gdyni żaglowiec Dar Pomorza zakończył rejs dookoła świata");
		add("W 1935 wyszedł pierwszy numer tygodnika naukowo-technicznego \"Młody Zawodowiec\"");
		add("W 301 założono San Marino");
		add("W 1999 założono Ukraiński Uniwersytet Islamski w Doniecku");
		add("W 1875 urodził się Ferdinand Porsche, niemiecki konstruktor samochodów");
		add("W 1941 urodził się Stan Borys, polski wokalista");

		setDay(904);
		add("Imieniny Felicyty");
		add("Imieniny Hermiony");
		add("W 1962 w Katowicach otwarto Dom Handlowy Zenit");
		add("W 1963 Polska pokonała w Szczecinie Norwegię 9:0");
		add("W 1997 na antenie TVP1 wyemitowano pierwszy odcinek teleturnieju \"Jaka to melodia?\"");
		add("W 1781 hiszpańscy osadnicy założyli Los Angeles");
		add("W 1956 IBM skonstruował pierwszy dysk twardy");
		add("W 1809 urodzi się Juliusz Słowacki, polski poeta");
		add("W 1984 urodził się Oscar Boniek García Ramírez, honduraski piłkarz");



		setDay(905);
		add("Imieniny Budziboja i Przyboja");
		add("Imieniny Fereola");
		add("Imieniny Stronisława i Rozwita");
		add("Dzień Nauczyciela w Indiach");
		add("W 1994 na antenie TVP1 wyemitowano pierwszy odcinek serialu \"Moda na sukces\"");
		add("W 2007 w Tychach zjechał z taśmy milionowy egzemplarz Fiata Panda");
		add("W 1698 car Piotr I Wielki wprowadził podatek od posiadania brody");
		add("W 1977 NASA wystrzeliła sondę kosmiczną Voyager 1");
		add("W 2008 Nikaragua uznała niepodległość Abchazji i Osetii Południowej");
		add("W 1946 urodził się Freddie Mercury, brytyjski wokalista zespołu Queen");

		setDay(906);
		add("Imieniny Amoniusza i Bolemira");
		add("Imieniny Fausta i Mansweta");
		add("Imieniny Onezyfora");
		add("Dzień Zjednoczenia w Bułgarii");
		add("W 1933 założono Okocimski Klub Sportowy Brzesko");
		add("W 775 p.n.e. chińscy astronomowie odnotowali najstarsze w historii zaćmienie Słońca");
		add("W 1632 powstała diecezja bagdadzka");
		add("W 1946 radziecka ekspedycja zdobyła Szczyt Karola Marxa (6726 m n.p.m.)");
		add("W 1991 Leningrad został przemianowany na Petersburg");

		setDay(907);
		add("Imieniny Dobrobąda i Domasuła");
		add("Imieniny Eupsychii");
		add("Imieniny Gratusa i Pamfila");
		add("Imieniny Sozonta");
		add("Dzień Zwycięstwa w Mozambiku");
		add("W 1896 we Wrocławiu odbył się pierwszy w mieście pokaz filmowy");
		add("W 1928 rozpoczęła się pierwsza edycja Tour de Pologne");
		add("W 1989 rozwiązano Zmotoryzowane Odwody Milicji Obywatelskiej (ZOMO)");
		add("W 1848 w Cesarstwie Austriackim zniesiono poddaństwo");
		add("W 1939 radio BBC nadało pierwszą audycję w języku polskim");
		add("W 1983 urodził się Piri Weepu, nowozelandzki rugbysta");

		setDay(908);
		add("Imieniny Bratumiła");
		add("Międzynarodowy Dzień Alfabetyzacji (pod patronatem UNESCO)");
		add("Dzień Dobrej Wiadomości w Polsce");
		add("W 1977 zostało wyemitowane premierowe wydanie programu popularnonaukowego Sonda");
		add("W 1100 dokonano wyboru antypapieża Teodoryka");
		add("W 1930 firma 3M wprowadziła do sprzedaży przezroczystą taśmę klejącą");
		add("W 1976 urodziła się Anna Barańska, polska himalaistka");
	
	}
	
	
	public static int dateToDayNum(Calendar cal) {		
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int month = cal.get(Calendar.MONTH);
		return 100 * (month+1) + day;
	}
	
	
	private void setDay(int dateNum) {
		currDayNum = dateNum;
		currDayOccasions = occasions.get( currDayNum );
		if (currDayOccasions==null) {
			currDayOccasions = new ArrayList<String>();
			occasions.put( currDayNum, currDayOccasions );
			Log.i(TAG, "day set to " + String.valueOf( dateNum ));
		}
	}
		
	
	private void add(String occasion) {
		if ( currDayOccasions != null ) {
			currDayOccasions.add( occasion );
		} else {
			Log.e(TAG, "date not set (currDateOccasions is null" );
		}
	}
	
}
