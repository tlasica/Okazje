package pl.tlasica.okazje;

import java.util.LinkedList;
import java.util.List;

public class OccasionsDataEN implements OccasionsData {

	@Override
	public List<String> forDay(int dateNum) {
		List<String> res = new LinkedList<String>(); 

		switch (dateNum) {

		case 928:
			res.add("In 1924 first round-the-world flight was completed");
			res.add("In 1928 sir Alexander Fleming notices a bacteria-killing mold growing in his laboratory (penicillin)");
			res.add("In 2008 SpaceX launches the first private spacecraft, the Falcon 1 into orbit");
			res.add("Seymour Cray birthday (1925), American computer scientist");
			res.add("Czech Statehood Day");
			res.add("Ask a Stupid Question Day (United States)");
			res.add("Fedor Emelianenko birthday (1976)");
			break;

		case 929:
			res.add("1650 – Henry Robinson opens his Office of Addresses and Encounters in London");
			res.add("1885 – The first practical public electric tramway in the world is opened in England");
			res.add("1966 – The Chevrolet Camaro, originally named Panther, is introduced");
			res.add("Miguel de Cervantes birthday (1547), Spanish author");
			res.add("Caravaggio birthday (1571), Italian painter");
			res.add("Horatio Nelson birthday (1758), English admiral");
			res.add("International Coffee Day");
			res.add("Inventors' Day (Argentina)");
			res.add("The University of Padua was founded in 1222");
			res.add("Chevrolet Camaro went on sale in 1966");
			res.add("MacGyver action-adventure television series started in 1985");
			break;
			
		case 930:
			res.add("1791 – The first performance of The Magic Flute, the last opera by Mozart");
			res.add("1860 – Britain's first tram service begins in Birkenhead");
			res.add("1927 – Babe Ruth becomes the first baseball player to hit 60 home runs in a season");
			res.add("1968 – The Boeing 747 is rolled out and shown to the public for the first time");
			res.add("1980 – Ethernet specifications are published by Xerox working with Intel and DEC");
			res.add("2004 – The first images of a live giant squid in its natural habitat are taken near Tokyo");
			res.add("International Translation Day");
			res.add("Hubert Cecil Booth patented a vacuum cleaner in 1901" );
			break;
			
		case 1001:
			res.add("1890 – Yosemite National Park is established by the U.S. Congress");
			res.add("1946 – Mensa International is founded in the United Kingdom");
			res.add("1969 – Concorde breaks the sound barrier for the first time");
			res.add("1964 – Japanese Shinkansen begin high-speed rail service from Tokyo to Osaka");
			res.add("1992 – Cartoon Network begins broadcasting");
			res.add("World Vegetarian Day"); 
			res.add("International Day of Older Persons");
			break;
			
		case 1002:
			res.add("1925 – John Logie Baird performs the first test of a working television system");
			res.add("1951 – Sting brithday, English singer-songwriter, bass player, and actor (The Police)");
			res.add("1535 – Jacques Cartier discovers the area where Montreal, Quebec is located.");
			res.add("Batik Day (Indonesia)");
			res.add("International Day of Non-Violence");			
			break;
			
		case 1003:
			res.add("1789 – George Washington makes the first Thanksgiving Day"); 
			res.add("1955 – The Mickey Mouse Club debuts on ABC");
			res.add("1990 – Re-unification of Germany");
			res.add("1951 – The \"Shot Heard 'Round the World\"");
			res.add("1985 – The Space Shuttle Atlantis makes its maiden flight");
			break;
			
		case 1004:
			res.add("1582 – Pope Gregory XIII implements the Gregorian Calendar");
			res.add("1883 – First run of the Orient Express");
			res.add("1927 – Gutzon Borglum begins sculpting Mount Rushmore");
			res.add("1957 – Space Race: Launch of Sputnik I, the first artificial satellite to orbit the Earth");
			res.add("1985 – Free Software Foundation is founded in Massachusetts, United States");
			res.add("1947 – Max Planck, German physicist, Nobel Prize laureate (b. 1858)");
			res.add("World Animal Day");
			break;
			
			default:
				res.add("For the application update!");
				break;
		}
		
		return res;
	}

}
