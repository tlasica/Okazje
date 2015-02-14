package pl.tlasica.okazje;

import java.util.Calendar;
import java.util.Locale;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.*;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
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
import android.widget.Toast;
import com.facebook.*;
import com.facebook.widget.FacebookDialog;
import com.facebook.widget.WebDialog;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class MainActivity extends Activity {

	//private static final String LINK_FACEBOOK = "http://facebook.com/okazjeapp";
	
	private TextView	mCurrDateTextView;
	private TextView	mOccasionTextView;
	private Calendar	currDate;
	private String		currOccasion;
	private Occasions	occasionsDict;
	private ShareActionProvider mShareActionProvider;
	private GestureDetectorCompat 	mDetector;

    private AdView      adView;

    private UiLifecycleHelper uiHelper;

    private long lastUpdateMillis = 0;

    final String    APP_URL = "http://bit.ly/okazjeapp";
    final String    PIC_URL = "https://raw.githubusercontent.com/tlasica/Okazje/master/icon-128.png";
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);
        
		mDetector = new GestureDetectorCompat(this, new MyGestureListener() );
        
        mCurrDateTextView = (TextView) findViewById( R.id.textview_current_date);
        mOccasionTextView = (TextView) findViewById( R.id.textview_occasion);
                    
        occasionsDict = new Occasions( new OccasionsDataFromDb(getApplicationContext()) );

        // register to see connectivity changes
        registerReceiver(mConnReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

        // configure facebook
        uiHelper = new UiLifecycleHelper(this, null);
        uiHelper.onCreate(savedInstanceState);

        // configure google admob
        adView = (AdView)this.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("A9A32839D5C3A3E567C5D00C21437288")
                .build();
        adView.loadAd(adRequest);


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
        uiHelper.onResume();
		adjustDisplay();
        adView.resume();
	}

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        uiHelper.onSaveInstanceState(outState);
    }


    @Override
    public void onPause() {
        adView.pause();
        super.onPause();
        uiHelper.onPause();
    }

    @Override
    public void onDestroy() {
        adView.destroy();
        super.onDestroy();
        uiHelper.onDestroy();
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
        ViewGroup.LayoutParams p = layout.getLayoutParams();
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

    private String currentDateStr() {
        return DateFormat.getDateFormat(getApplicationContext()).format( currDate.getTime());

    }

    private String currentDateStrNoYear() {
        return DateFormat.format("E d.M",currDate).toString();

    }

	private void updateCurrentDate(Calendar day) {
		currDate = day;
		mCurrDateTextView.setText( currentDateStr() );
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
				"Okazje Android App: %s\n", currentDateStr(), occ, APP_URL);
		return content;
	}


	// Call to update the share intent
	private void updateShareIntent(String occ) {
		String subject = "Okazja na " + currentDateStr();
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
                String text = "Data: " + currentDateStr() + "\n" + "Tekst okazji:";
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

                // show fb share button
                findViewById(R.id.facebookShareButton).setVisibility(View.VISIBLE);
            }
            else {
                Log.d("NETWORK", "network is disconnected");
                // hide fb share button
                findViewById(R.id.facebookShareButton).setVisibility(View.INVISIBLE);
            }

        }
    };


    public void facebookLoginAndShare(View view) {
        if (Session.getActiveSession() != null && Session.getActiveSession().isOpened()) {
            facebookShareWithFeedDialog();
        }
        else {
            Session.openActiveSession(this, true, new Session.StatusCallback() {

                // callback when session changes state
                @Override
                public void call(Session session, SessionState state, Exception exception) {
                    if (state == SessionState.OPENED) {
                        facebookShareWithFeedDialog();
                    }

                }
            });
        }

    }


    public void facebookShareWithFeedDialog() {
        String msg = String.format("%s: %s", currentDateStrNoYear(), currOccasion);

        Bundle params = new Bundle();
        params.putString("description", getString(R.string.fb_share_description));
        params.putString("link", APP_URL);
        params.putString("picture", PIC_URL);
        params.putString("name", msg);

        WebDialog feedDialog = (
                new WebDialog.FeedDialogBuilder(this, Session.getActiveSession(), params))
                .setOnCompleteListener(new WebDialog.OnCompleteListener() {

                    @Override
                    public void onComplete(Bundle values, FacebookException error) {
                        if (error == null) {
                            final String postId = values.getString("post_id");
                            if (postId != null) Log.d("FB", "posted");
                        } else if (error instanceof FacebookOperationCanceledException) {
                            Log.i("FB", "Publish cancelled");
                        } else {
                            Log.w("FB", "Error posting story");
                        }
                    }

                })
                .build();
        feedDialog.show();
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Session.getActiveSession().onActivityResult(this, requestCode, resultCode, data);
        uiHelper.onActivityResult(requestCode, resultCode, data, new FacebookDialog.Callback() {
            @Override
            public void onError(FacebookDialog.PendingCall pendingCall, Exception error, Bundle data) {
                Log.e("Activity", String.format("Error: %s", error.toString()));
            }

            @Override
            public void onComplete(FacebookDialog.PendingCall pendingCall, Bundle data) {
                Log.i("Activity", "Success!");
            }
        });
    }//onActivityResult


    protected String updateSite() {
        Locale locale = Locale.getDefault();
        String language = locale.getLanguage();
        String site = "http://okazjedowypicia.herokuapp.com/assets/data/";
        if (language.equals("en")) return site + "en/";
        if (language.equals("pl")) return site;
        else return site;
    }

}
