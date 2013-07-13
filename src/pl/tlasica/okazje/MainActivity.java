package pl.tlasica.okazje;

import java.util.Calendar;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

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
                           
        occasionsDict = new Occasions();
    }

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
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

        // adjust card size to 50% of min(width,height)
        LinearLayout layout = (LinearLayout) findViewById(R.id.layout_card);
        LinearLayout.LayoutParams p = (LinearLayout.LayoutParams) layout.getLayoutParams();
        p.height = ySize / 2;
        layout.requestLayout();
        
        //adjust font size
        TextView textLabel = (TextView) findViewById(R.id.textview_label);
        TextView textOccasion = (TextView) findViewById( R.id.textview_occasion);

        float fontSize = 22f;
    	if (xSize > 700) {
    		fontSize = 40f;
    	}
    	else if (xSize > 500 ) {
    		fontSize = 30f;
    	}
    	textLabel.setTextSize(TypedValue.COMPLEX_UNIT_DIP, fontSize);
    	textOccasion.setTextSize(TypedValue.COMPLEX_UNIT_DIP, fontSize);                		
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
	
}
