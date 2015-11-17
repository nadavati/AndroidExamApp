package certwin.apps.sfdcadm201;


import certwin.apps.sfdcadm201.R;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class ADMExamPreferences extends PreferenceActivity
{
	
	public static final String LPI_101="101";
	public static final String LPI_102="102";
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences);
	}
} 
