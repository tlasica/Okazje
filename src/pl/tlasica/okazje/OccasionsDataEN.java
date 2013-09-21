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
			break;
			
			default:
				res.add("For the application update!");
				break;
		}
		
		return res;
	}

}
