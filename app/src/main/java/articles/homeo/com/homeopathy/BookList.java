package articles.homeo.com.homeopathy;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ShareActionProvider;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class BookList extends TabGroupActivity {
	JSONParser jsonParser = new JSONParser();

	ArrayList<HashMap<String, String>> bookList;
	JSONArray books = null;
	private ShareActionProvider mShareActionProvider;

	// ALL JSON node names
	private static final String TAG_ID = "id";
	private static final String TAG_NAME = "book";

	private String json = "";

	public String loadJSONFromAsset() {
		String json = null;
		try {
			InputStream is = this.getAssets().open("books.json");
			int size = is.available();
			byte[] buffer = new byte[size];
			is.read(buffer);
			is.close();
			json = new String(buffer, "UTF-8");
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}
		return json;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		bookList = new ArrayList<HashMap<String, String>>();
		ListView lv = (ListView) findViewById(R.id.lstBooks);

		try {
			json = loadJSONFromAsset();
			books = new JSONArray(json);

			if (books != null) {
				// looping through All albums
				for (int i = 0; i < books.length(); i++) {
					JSONObject c = books.getJSONObject(i);

					// Storing each json item values in variable
					String id = c.getString(TAG_ID);
					String name = c.getString(TAG_NAME);

					// creating new HashMap
					HashMap<String, String> map = new HashMap<String, String>();

					// adding each child node to HashMap key => value
					map.put(TAG_ID, id);
					map.put(TAG_NAME, name);

					// adding HashList to ArrayList
					bookList.add(map);
				}
			}

			SimpleAdapter adapter = new SimpleAdapter(BookList.this,
					bookList, R.layout.list_item_books, new String[] {
							TAG_ID, TAG_NAME }, new int[] { R.id.book_id,
							R.id.book_name }) {
				@Override
				public View getView(int pos, View convertView, ViewGroup parent) {
					View v = convertView;
					ImageView thumbnail;
					DataSource mDataSource = new DataSource();
					if (v == null) {
						LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
						v = vi.inflate(R.layout.list_item_books, parent,
								false);
					}
					thumbnail = (ImageView) v.findViewById(R.id.thumb);
					thumbnail.setImageResource(mDataSource.getmPhotoList().get(
							0));

					TextView tv = (TextView) v.findViewById(R.id.book_id);
					tv.setText(bookList.get(pos).get("id"));

					TextView tv2 = (TextView) v
							.findViewById(R.id.book_name);
					tv2.setText(bookList.get(pos).get("book"));
					tv2.setTextSize(18);
					return v;
				}
			};
			// updating listview
			lv.setAdapter(adapter);
			lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> arg0, View view,
						int arg2, long arg3) {
					String book_id = ((TextView) view
							.findViewById(R.id.book_id)).getText()
							.toString();
					String book_name = ((TextView) view
							.findViewById(R.id.book_name)).getText()
							.toString();

					Intent i = new Intent(getParent(), PageList.class);
					i.putExtra("book_id", book_id);
					i.putExtra("book_name", book_name);

					TabGroupActivity parentActivity = (TabGroupActivity) getParent();
					parentActivity.startChildActivity("PageList", i);

				}
			});

		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onBackPressed() {
		TabGroupActivity parentActivity = (TabGroupActivity) getParent();
		parentActivity.onBackPressed();
	}

	/*
	 * @Override public boolean onKeyDown(int keyCode, KeyEvent event) {
	 * 
	 * AlertDialog.Builder alertDlg = new AlertDialog.Builder(this);
	 * alertDlg.setMessage("Are you sure you want to exit?");
	 * alertDlg.setCancelable(false); alertDlg.setPositiveButton("Yes", new
	 * DialogInterface.OnClickListener() { public void onClick(DialogInterface
	 * dialog, int id) { MandalamList.super.onBackPressed(); } } );
	 * alertDlg.setNegativeButton("No", new DialogInterface.OnClickListener() {
	 * 
	 * @Override public void onClick(DialogInterface dialog, int which) {
	 * 
	 * } });
	 * 
	 * alertDlg.create().show(); return true; }
	 */
}
