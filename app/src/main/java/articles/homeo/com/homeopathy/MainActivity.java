package articles.homeo.com.homeopathy;
import java.util.Map;

import android.R.string;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.TabActivity;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ShareActionProvider;
import android.widget.TabHost;
import android.widget.Toast;
import android.widget.TabHost.TabSpec;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
/**
 * Created by Srinivasan on 24-12-2016.
 */
public class MainActivity extends TabActivity{
    TabHost tabHost;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        tabHost = getTabHost();

        TabSpec bookSpec = tabHost.newTabSpec("Books");
        bookSpec.setIndicator("Books");
        Intent bookIntent = new Intent(this, BookActivity.class);
        bookSpec.setContent(bookIntent);

        TabSpec contactSpec = tabHost.newTabSpec("Contact");
        contactSpec.setIndicator("Contact");
        Intent contactIntent = new Intent(this, PageDetail.class);
        contactIntent.putExtra("page_id", "0");
        contactIntent.putExtra("page_name", "Contact");
        contactSpec.setContent(contactIntent);

        tabHost.addTab(bookSpec);
        tabHost.addTab(contactSpec);
    }
}
