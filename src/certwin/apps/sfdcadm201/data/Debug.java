package certwin.apps.sfdcadm201.data;

import android.util.Log;

public final class Debug
{
    private Debug ()
    {
    	
    }

    public static void out (Object msg)
    {
    	//Log.i ("info", "#############################################");
        Log.i ("info", msg.toString ());
    	//Log.i ("info", "#############################################");
    }
}

