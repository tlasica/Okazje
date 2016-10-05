package pl.tlasica.okazje;

import java.util.Calendar;
import java.util.Locale;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.*;
import android.content.pm.ActivityInfo;
import android.media.Image;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ShareActionProvider;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	//private static final String LINK_FACEBOOK = "http://facebook.com/okazjeapp";
	
	private TextView	mCurrDateTextView;
	private TextView	mOccasionTextView;
	private Calendar	currDate;
	private String		currOccasion;
	private Occasions	occasionsDict;
	private ShareActionProvider mShareActionProvider;

    private long lastUpdateMillis = 0;

    final String    APP_URL = "http://bit.ly/okazjeapp";
    final String    PIC_URL = "https://raw.githubusercontent.com/tlasica/Okazje/master/icon-128.png";
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);

        mCurrDateTextView = (TextView) findViewById( R.id.textview_current_date);
        mOccasionTextView = (TextView) findViewById( R.id.textview_occasion);
                    
        occasionsDict = new Occasions( new OccasionsDataFromDb(getApplicationContext()) );

        AppRater rater = new AppRater(this);
        rater.appLaunched();
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
        registerReceiver(mConnReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
	}

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }


    @Override
    public void onPause() {
        super.onPause();
        unregisterReceiver(mConnReceiver);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    public void today(View view) {
    	today();
    }
    
	private void today() {
		updateCurrentDate(Calendar.getInstance());
		updateOccasion();
	}

	public void prevDay(View view) {
		Calendar prev = (Calendar)currDate.clone();
		prev.add(Calendar.DAY_OF_YEAR, -1);			
		updateCurrentDate(prev);
		updateOccasion();		
	}
	
	public void nextDay(View view) {
		Calendar next = (Calendar)currDate.clone();
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

        ViewGroup layout = (ViewGroup) findViewById(R.id.layout_card);
        ViewGroup.LayoutParams p = layout.getLayoutParams();
        int cardHeight = Math.round( ySize * 0.66f );
        p.height = cardHeight;
        layout.requestLayout();

        // adjust icon size
        ImageView icon = (ImageView) findViewById(R.id.icon);
        ViewGroup.LayoutParams ilp = icon.getLayoutParams();
        ilp.height = Math.round(xSize / 6.0f);
        ilp.width = Math.round(xSize / 6.0f);
        layout.requestLayout();

        //adjust font size
        float fontSize = ySize/24;
        Log.d("DISPLAY", "fontSize=" + fontSize);
    	findTextView(R.id.textview_occasion).setTextSize(TypedValue.COMPLEX_UNIT_PX, fontSize);
        findTextView(R.id.textview_current_date).setTextSize(TypedValue.COMPLEX_UNIT_PX, fontSize * 0.8f);
        findTextView(R.id.textview_prev_day).setTextSize(TypedValue.COMPLEX_UNIT_PX, fontSize * 0.6f);
        findTextView(R.id.textview_next_day).setTextSize(TypedValue.COMPLEX_UNIT_PX, fontSize * 0.6f);
	}

    private TextView findTextView(int resId) {
        return (TextView) findViewById(resId);
    }

    private String formatDate(Calendar cal) {
        return DateFormat.format(getString(R.string.date_format), cal).toString();
        //return DateFormat.getDateFormat(getApplicationContext()).format(cal.getTime());
    }

    private String currentDateStrNoYear() {
        return DateFormat.format("E d.M",currDate).toString();

    }

	private void updateCurrentDate(Calendar day) {
		currDate = day;
		mCurrDateTextView.setText( formatDate(currDate) );
        // update prev day
        Calendar prev = (Calendar)currDate.clone();
        prev.add(Calendar.DAY_OF_YEAR, -1);
        ((TextView) findViewById( R.id.textview_prev_day)).setText(formatDate(prev));
        // update next day
        Calendar next = (Calendar)currDate.clone();
        next.add(Calendar.DAY_OF_YEAR, +1);
        ((TextView) findViewById( R.id.textview_next_day)).setText(formatDate(next));
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

	private String createShareContentText(String occ) {
		String content = String.format("Okazja na %s:\n%s\n\n" +
				"Okazje Android App: %s\n", formatDate(currDate), occ, APP_URL);
		return content;
	}


	// Call to update the share intent
	private void updateShareIntent(String occ) {
		String subject = formatDate(currDate);
		String content = createShareContentText(occ);
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

    public void addNewOccasion(MenuItem item) {
        Log.d("MENU", "addNewOccation()");
        // open dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.add_occasion_text));
        builder.setCancelable(true);
        builder.setPositiveButton(getString(R.string.add_occasion_bttn_yes), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Log.d("ADD", "yes!");
                // Przygotowanie tekstu emaila
                String text = "Data: " + formatDate(currDate) + "\n" + "Text:";
                // open to send email
                Intent email = new Intent(Intent.ACTION_VIEW);
                email.setData(Uri.parse("mailto:"));
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{"tomek@3kawki.pl"});
                email.putExtra(Intent.EXTRA_SUBJECT, "[OKAZJE] Propozycja okazji");
                email.putExtra(Intent.EXTRA_TEXT, text);
                startActivity(email);
            }
        });
        builder.setNegativeButton(getString(R.string.add_occasion_bttn_no), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Log.d("ADD", "cancelled");
                dialog.cancel();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();



    }

    private BroadcastReceiver mConnReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo netInfo = connManager.getActiveNetworkInfo();
            if (netInfo!=null && netInfo.isConnected()) {
                Log.d("NETWORK", "network is connected");

                long curr = System.currentTimeMillis();
                if (curr - lastUpdateMillis > 5 * 60 * 1000) {
                    Log.d("NETWORK", "starting data updater");
                    lastUpdateMillis = curr;
                    String msg = getString(R.string.updater_msg_inprogress);
                    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                    new DataUpdater(updateSite(), getApplicationContext(), occasionsDict).execute();
                }
            }
            else {
                Log.d("NETWORK", "network is disconnected");
            }

        }
    };


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }//onActivityResult


    protected String updateSite() {
        Locale locale = Locale.getDefault();
        String language = locale.getLanguage();
        String site = "http://okazjedowypicia.herokuapp.com/assets/data";
        if (language.equals("en")) return site + "/en";
        if (language.equals("pl")) return site;
        else return site;
    }

}
