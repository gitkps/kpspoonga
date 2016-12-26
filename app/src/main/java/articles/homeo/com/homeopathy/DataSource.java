package articles.homeo.com.homeopathy;

import java.util.ArrayList;

public class DataSource {
    private ArrayList<Integer> mPhotoList;
    private ArrayList<Integer> mList;


    public ArrayList<Integer> getmPhotoList() {
        return mPhotoList;
    }

    public ArrayList<Integer> getmList() {
        return mList;
    }

    private void setupPhotoList() {
    	 mPhotoList.add(R.drawable.homeo);
    }

    private void setupList() {

    }
    
    public int getDataSourceLength() {
        return mList.size();
    }
    
    public DataSource() {
        mPhotoList = new ArrayList();
        mList = new ArrayList();
        setupPhotoList();
        setupList();
    }
}
