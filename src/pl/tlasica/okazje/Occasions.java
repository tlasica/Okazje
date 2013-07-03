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
