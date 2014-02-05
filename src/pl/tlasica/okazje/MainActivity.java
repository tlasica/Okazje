package pl.tlasica.okazje;

import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.text.format.DateFormat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ShareActionProvider;
import android.widget.TextView;

public class MainActivity extends Activity {

	//private static final String LINK_PLAYSTORE = "https://play.google.com/store/apps/details?id=pl.tlasica.okazje";
	private static final String LINK_BITLY="http://bit.ly/19b02vz";
	//private static final String LINK_FACEBOOK = "http://facebook.com/okazjeapp"; 
	
	private TextView	mCurrDateTextView;
	private TextView	mOccasionTextView;
	private Calendar	currDate;
	private String		currOccasion;
	private Occasions	occasionsDict;
	private ShareActionProvider mShareActionProvider;
	private GestureDetectorCompat 	mDetector;

	private static final String UPDATE_SITE = "http://okazjedowypicia.herokuapp.com/assets/data/";	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);
        
		mDetector = new GestureDetectorCompat(this, new MyGestureListener() );
        
        mCurrDateTextView = (TextView) findViewById( R.id.textview_current_date);
        mOccasionTextView = (TextView) findViewById( R.id.textview_occasion);
                    
        //occasionsDict = new Occasions( new OccasionsDataPL() );
        occasionsDict = new Occasions( new OccasionsDataFromDb(getApplicationContext()) );
    
        //TODO: aktualizacja w przypadku wczytania => zapisać na event
        new DataUpdater(UPDATE_SITE, getApplicationContext(), occasionsDict).execute();
    }

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
		int currentapiVersion = android.os.Build.VERSION.SDK_INT;
		if (currentapiVersion >= android.os.Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
			getMenuInflater().inflate(R.menu.activity_main, menu);
			// Locate MenuItem with ShareActionProvider
		    MenuItem item = menu.findItem(R.id.menu_item_share);
		    // Fetch and store ShareActionProvider
		    mShareActionProvider = (ShareActionProvider) item.getActionProvider();
		    // Update Share Intent
		    if (currOccasion != null ) {
		    	updateShareIntent( currOccasion );
		    }    
		    // Return true to display menu
		    return super.onCreateOptionsMenu(menu);
		}
		else {
			return true;
		}
    }

	@Override
	protected void onStart() {
		super.onStart();
		today();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		adjustDisplay();
	}

    @Override 
    public boolean onTouchEvent(MotionEvent event){ 
        this.mDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }	
	
    public void today(View view) {
    	today();
    }
    
	private void today() {
		updateCurrentDate(Calendar.getInstance());
		updateOccasion();
	}

	private void prevDay() {
		Calendar prev = currDate;		
		prev.add(Calendar.DAY_OF_YEAR, -1);			
		updateCurrentDate(prev);
		updateOccasion();		
	}
	
	private void nextDay() {
		Calendar next = currDate;		
		next.add(Calendar.DAY_OF_YEAR, +1);			
		updateCurrentDate(next);
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
        ViewGroup layout = (ViewGroup) findViewById(R.id.layout_card);
        ViewGroup.LayoutParams p = (ViewGroup.LayoutParams) layout.getLayoutParams();
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
	
	private void updateCurrentDate(Calendar day) {
		currDate = day;
		String dateStr = DateFormat.getDateFormat(getApplicationContext()).format( currDate.getTime());
		mCurrDateTextView.setText( dateStr );		
	}

	void updateOccasion() {
		currOccasion = occasionsDict.getRandomOccasion( currDate );
		mOccasionTextView.setText( currOccasion );
		updateShareIntent( currOccasion );
	}

	public void onClickOccasion(View view) {
		updateOccasion();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return super.onOptionsItemSelected(item);
	}

	private String createShareContentText(String occ, String dateStr) {
		String content = String.format("Dobra okazja na %s:\n%s\n\n" +
				"Okazje Android App: %s\n", dateStr, occ, LINK_BITLY);
		return content;
	}
		
	// Call to update the share intent
	private void updateShareIntent(String occ) {
		String dateStr = DateFormat.getDateFormat(getApplicationContext()).format( currDate.getTime());				
				
		String subject = "Dobra okazja na " + dateStr;
		String content = createShareContentText(occ, dateStr);
		Log.d("SHARE", content);
						
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("text/plain");
		intent.putExtra(Intent.EXTRA_SUBJECT, subject);
		intent.putExtra(Intent.EXTRA_TEXT, content);
		
	    //intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);		
	    if (mShareActionProvider != null) {
	        mShareActionProvider.setShareIntent(intent);
	    }
	}
	
	class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
	       
        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2, 
                float velocityX, float velocityY) {
        	float x1 = event1.getX();
        	float x2 = event2.getX();
        	
        	if (x2 - x1 > 150.0) {
        		prevDay();
        	}
        	if (x2 - x1 < -150.0) {
        		nextDay();
        	}
            return true;
        }
    }	
}
