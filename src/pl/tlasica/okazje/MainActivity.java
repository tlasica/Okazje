package pl.tlasica.okazje;

import java.util.Calendar;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.View;
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

	private void updateContent() {
		updateCurrentDate();
		updateOccasion();
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
