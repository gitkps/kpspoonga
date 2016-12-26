package articles.homeo.com.homeopathy;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

public class PageDetail extends Activity {

	private WebView webView;
	private String pageid;
	private String pageName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.page_detail);

		webView = (WebView) findViewById(R.id.webViewDetail);
		webView.getSettings().setSupportZoom(true);
		webView.getSettings().setBuiltInZoomControls(true);
		webView.getSettings().setDisplayZoomControls(false);
		webView.getSettings().setRenderPriority(RenderPriority.HIGH);
		webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
		Intent i = getIntent();
		pageid = i.getStringExtra("page_id");
		pageName = i.getStringExtra("page_name");

		try {
			this.setTitle(pageName);
			webView.loadUrl("file:///android_asset/" + pageid + ".html");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}