package articles.homeo.com.homeopathy;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

public class BookActivity extends TabGroupActivity{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startChildActivity("MandalamActivity", new Intent(this,BookList.class));
    }
    
    @Override
    public void onBackPressed() {
    	AlertDialog.Builder alertDlg = new AlertDialog.Builder(this);
		alertDlg.setMessage("Do you really want to exit?");
		alertDlg.setCancelable(false);
		alertDlg.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {	 	
				System.exit(0);
			}	 
		}	 
				);	 
		alertDlg.setNegativeButton("No", new DialogInterface.OnClickListener() {
			@Override	 
			public void onClick(DialogInterface dialog, int which) {

			}
		});

		alertDlg.create().show();
    	/*TabGroupActivity parentActivity = (TabGroupActivity) getParent();
        parentActivity.onBackPressed();*/    	
    }

}
