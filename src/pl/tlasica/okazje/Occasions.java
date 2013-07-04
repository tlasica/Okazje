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
	
	public Occasions() {
		occasions = new SparseArray< List<String> >();
		random = new Random( Calendar.getInstance().getTimeInMillis() );
		initData();
	}
	
	public String getRandomOccasion(Calendar cal) {
		int num = Occasions.dateToDayNum(cal);
		Log.d(TAG, "get random for" + String.valueOf( num ));
		List<String> chooseFrom = occasions.get( num );
		if ( chooseFrom != null ) {
			int pos = random.nextInt( chooseFrom.size() );
			return chooseFrom.get( pos );
		}
		else {
			return "Nie ma dobrej okazji... Może by tak nie pić?";
		}
	}
	
	private void initData() {
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
		add("Święno Niepodległości Argentyny");
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
		add("Święto Sił Zbronjnych na Mauritiusie");
		add("W 1649 rozpoczęła się obrona Zbaraża");
		add("W 1863 zwycięstwo powstańców w bitwie pod Ossą");
		add("W 1865 założono we Wrocławiu ogród zoologiczny");
		add("W 2008 na Politechnice Wrocławskiej uruchomiono polski superkomputer Nova");
		add("W 1922 Muhammad VI został bejem Tunisu");
		add("W 1994 Aleksandar Lukaszenka został prezydentem Białorusi");
		add("W 1509 urodził się Jan Kalwin");
		add("W 1856 urodził się Nikola Tesla, serbski fizyk");
		add("W 1925 urodził się Edmund Niziurski");
		
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
