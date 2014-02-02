package pl.tlasica.okazje;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

/**
 * Aktualizuje dane wczytując je z URL dostepnego w okazjedowypicia.pl
 * Parametrów nie ma, Progress co 1 m-c, Result True: udało się / False: nie udało się
 * @author tomek
 *
 */
public class DataUpdater extends AsyncTask<Void, Integer, Boolean > {

	private String 		baseUrl;
	private Context		context;
	
	public DataUpdater(String aBaseUrl, Context aContext) {
		baseUrl = aBaseUrl;
		context = aContext;
	}
	
	@Override
	protected Boolean doInBackground(Void... arg0) {
		return updateAll();
	}

	private void log(String tag, String msg) {
		Log.d(tag, msg);
	}
	
	public boolean updateAll() {
		boolean anyUpdated = false;
		for(int month=1; month<=12; month++) {
	        log("UPDATE", "Updating month " + month );
	        boolean updated = updateMonth(month);
	        log("UPDATE", "Updating month " + month + (updated?" succeeded":" failed") );
			anyUpdated |= updated;
		}
		return anyUpdated;
	}

	public  boolean updateMonth(int month) {
		String url = urlForChecksum(month);
		log("UPDATE", url);
		try {			
			// get md5 from update site
			String md5 = getChecksumFromURL(urlForChecksum(month));
			// check if update is needed
			DatabaseHelper db = DatabaseHelper.getInstance(context);
			if (db.isUpdateNeeded(month, md5)) {
				log("UPDATE", "update for month " + month + " is needed");
				// get content from web
				List<String> data = geTextFileFromURL(urlForData(month));
				// update content in the database
				updateMonthDbFromFile(data);
				// store new checksum
				db.storeMD5(month, md5);
				return true;
			}			
			return false;
		} catch (Exception e){
			log("UPDATE", "Exception:" + e);
			return false;
		}		
	}

	//TODO: trzeba tę operację zoptymalizować, bo ...	
	private void updateMonthDbFromFile(List<String> data) {
		Map<Integer, List<String>> update = occasionsFileToMap(data);
		DatabaseHelper db = DatabaseHelper.getInstance(context);
		for(Integer daynum: update.keySet()) {
			db.cleanDay(daynum);
			for(String text: update.get(daynum)) {
				OneOccasion in = new OneOccasion();
				in.dayNum = daynum;
				in.text = text;
				db.addOccasion(in);
			}
		}
	}

	private String urlForData(int month) {
		return String.format("%s%02d.txt", baseUrl, month);
	}

	private String urlForChecksum(int month) {
		return String.format("%s%02d.txt.md5", baseUrl, month);
	}

	private List<String> geTextFileFromURL(String url) throws Exception {
		log("UPDATE", "url=" + url);
		URL web = new URL(url);
		URLConnection connection = web.openConnection();	
        BufferedReader in = new BufferedReader( new InputStreamReader(connection.getInputStream()));        
        List<String> res = new ArrayList<String>();
        String line;
        while ((line = in.readLine()) != null)
        	res.add(line);
        in.close();
        return res;		
	}

	private String getChecksumFromURL(String url) throws Exception {
		List<String> content = geTextFileFromURL(url);
		String md5 = content.get(0).split(" ")[0];
		return md5;
	}
	
	private Map<Integer, List<String> > occasionsFileToMap(List<String> file) {
		Map<Integer, List<String> > map = new HashMap<Integer, List<String>>();
		List<String> curr = null;
		for(String line: file) {
			String s = line.trim();
			if (s.length()>=3 && s.length()<=4 && android.text.TextUtils.isDigitsOnly(s)) {
				Integer day = Integer.valueOf(s);
				curr = new ArrayList<String>();
				map.put(day, curr);
			}
			else {
				if (!s.isEmpty()) curr.add(s);
			}
		}
		return map;
	}

}
