package certwin.apps.sfdcadm201;

import org.codechimp.apprater.AppRater;

import certwin.apps.sfdcadm201.R;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ADMAbout extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);
		Button okAboutButton = (Button) findViewById(R.id.okButton);
		;
		okAboutButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Intent intent = new Intent();
				setResult(RESULT_OK, intent);
				finish();
			}
		});

		Button buttonTest1 = (Button) findViewById(R.id.button2);

		buttonTest1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri
						.parse("http://www.certwin.com/contact/"));
				startActivity(browserIntent);
			}
		});

		Button buttonTest = (Button) findViewById(R.id.button1);

		buttonTest.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				// This forces display of the rate prompt.
				// It should only be used for testing purposes
				AppRater.showRateDialog(v.getContext());
			}
		});

		// This will keep a track of when the app was first used and whether to
		// show a prompt
		// It should be the default implementation of AppRater
		AppRater.app_launched(this);
	}
}
