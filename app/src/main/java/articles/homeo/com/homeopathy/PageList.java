package articles.homeo.com.homeopathy;

import android.app.ListActivity;
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

public class PageList extends ListActivity {
	JSONParser jsonParser = new JSONParser();

	ArrayList<HashMap<String, String>> pageList;
	JSONArray pages = null;
	private ShareActionProvider mShareActionProvider;

	// ALL JSON node names
	private static final String TAG_ID = "id";
	private static final String TAG_NAME = "page";

	private String json = "";
	private int mPosition;

	public String loadJSONFromAsset(String fileName) {
		String json = null;
		try {
			InputStream is = this.getAssets().open(fileName + ".json");
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
		setContentView(R.layout.page_list);
		pageList = new ArrayList<HashMap<String, String>>();
		ListView lv = getListView();
		Intent i = getIntent();
		String book_id = i.getStringExtra("book_id");

		try {
			json = loadJSONFromAsset(book_id);
			pages = new JSONArray(json);

			if (pages != null) {

				for (int intLoop = 0; intLoop < pages.length(); intLoop++) {
					JSONObject c = pages.getJSONObject(intLoop);

					String id = c.getString(TAG_ID);
					String name = c.getString(TAG_NAME);

					HashMap<String, String> map = new HashMap<String, String>();
					map.put(TAG_ID, id);
					map.put(TAG_NAME, name);

					pageList.add(map);
				}
			}

			SimpleAdapter adapter = new SimpleAdapter(PageList.this, pageList,
					R.layout.list_item_pages,
					new String[] { TAG_ID, TAG_NAME }, new int[] {
							R.id.page_id, R.id.page_name }) {
				@Override
				public View getView(int pos, View convertView, ViewGroup parent) {
					View v = convertView;
					ImageView thumbnail;
					DataSource mDataSource = new DataSource();
					if (v == null) {
						LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
						v = vi.inflate(R.layout.list_item_pages, parent, false);
					}
					thumbnail = (ImageView) v.findViewById(R.id.thumb);
					thumbnail.setImageResource(mDataSource.getmPhotoList().get(
							0));

					TextView tv = (TextView) v.findViewById(R.id.page_id);
					tv.setText(pageList.get(pos).get("id"));

					TextView tv2 = (TextView) v.findViewById(R.id.page_name);
					tv2.setText(pageList.get(pos).get("page"));
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
					String page_id = ((TextView) view
							.findViewById(R.id.page_id)).getText()
							.toString();
					String page_name = ((TextView) view
							.findViewById(R.id.page_name)).getText()
							.toString();
					Intent i = new Intent(getParent(), PageDetail.class);
					i.putExtra("page_id", page_id);
					i.putExtra("page_name", page_name);
					//TabGroupActivity parentActivity = (TabGroupActivity) getParent();
					//parentActivity.startChildActivity("PambanDetail", i);
					startActivity(i);
				}
			});			
			
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}

}
