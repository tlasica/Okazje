package pl.tlasica.okazje;

import java.util.Calendar;
import java.util.Locale;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

	private static final String LANGCODE = "langCode";
	private TextView	mCurrDateTextView;
	private TextView	mOccasionTextView;
	private Calendar	currDate;
	private Occasions	occasionsDict;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);
        
        mCurrDateTextView = (TextView) findViewById( R.id.textview_current_date);
        mOccasionTextView = (TextView) findViewById( R.id.textview_occasion);
        
        String langCode = getLangCodeFromSettings();
        if (langCode == null) langCode = getLangCodeFromLocale();
        occasionsDict = new Occasions( createDataProviderByLangCode( langCode ) );
    }

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);		
        return true;
    }

	@Override
	protected void onStart() {
		super.onStart();
		updateContent();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		adjustDisplay();
	}

	private void updateContent() {
		updateCurrentDate();
		updateOccasion();
	}

	private void adjustDisplay() {
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
        Log.d("DISPLAY", metrics.toString() );
        int xSize = Math.min(metrics.heightPixels, metrics.widthPixels);
        int ySize = Math.max(metrics.heightPixels, metrics.widthPixels);
        Log.d("DISPLAY", "xSize=" + xSize);
        Log.d("DISPLAY", "ySize=" + ySize);

        // adjust card size to 50% of min(width,height)
        LinearLayout layout = (LinearLayout) findViewById(R.id.layout_card);
        LinearLayout.LayoutParams p = (LinearLayout.LayoutParams) layout.getLayoutParams();
        int cardHeight = Math.round( ySize * 0.56f ); 
        p.height = cardHeight;
        layout.requestLayout();
        
        //adjust font size
        TextView textLabel = (TextView) findViewById(R.id.textview_label);
        TextView textOccasion = (TextView) findViewById( R.id.textview_occasion);

        float fontSize = ySize/24;

        Log.d("DISPLAY", "fontSize=" + fontSize);
    	textLabel.setTextSize(TypedValue.COMPLEX_UNIT_PX, fontSize);
    	textOccasion.setTextSize(TypedValue.COMPLEX_UNIT_PX, fontSize);                		
	}
	
	private void updateCurrentDate() {
		currDate = Calendar.getInstance();
		String dateStr = DateFormat.getDateFormat(getApplicationContext()).format( currDate.getTime());
		mCurrDateTextView.setText( dateStr );		
	}

	private void updateOccasion() {
		String occ = occasionsDict.getRandomOccasion( currDate );
		mOccasionTextView.setText( occ );		
		
	}

	public void onClickOccasion(View view) {
		updateOccasion();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch( item.getItemId() ) {
			case R.id.menu_english:
				setLanguage("en");
				return true;
			case R.id.menu_polish:
				setLanguage("pl");
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
	
	private void setLanguage(String langCode) {
		saveLangCodeToSettings("en");
        occasionsDict = new Occasions( createDataProviderByLangCode( langCode ) );
        this.updateContent();		
	}

	private String getLangCodeFromLocale() {
		Locale locale = Locale.getDefault();
		String lang = locale.getLanguage();
		return lang;		
	}
	
	private OccasionsData createDataProviderByLangCode(String lang) {
		if (lang.equalsIgnoreCase("pl")) {
			return new OccasionsDataPL();
		}
		if (lang.equalsIgnoreCase("en")) {
			return new OccasionsDataEN();			
		}
		return new OccasionsDataEN();	// by default
	}
	
	private void saveLangCodeToSettings(String langCode) {
		SharedPreferences settings = this.getPreferences(MODE_PRIVATE);
		SharedPreferences.Editor editor = settings.edit();
		editor.putString(LANGCODE, langCode);
		editor.commit();	
	}
	
	private String getLangCodeFromSettings() {
		SharedPreferences settings = this.getPreferences(MODE_PRIVATE);
		return settings.getString(LANGCODE, null);
	}
}
