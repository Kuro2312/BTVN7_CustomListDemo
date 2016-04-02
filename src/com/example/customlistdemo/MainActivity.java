package com.example.customlistdemo;

import java.util.ArrayList;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	protected TextView _textViewChoice;
	protected ListView _listViewInfo;
	protected InfoAdapter _infoAdapter;
	protected ArrayList<PersonalInfo> _data;
	
	// ảnh đại diện
	protected int[] _profiles = {R.drawable.profile0, R.drawable.profile10, R.drawable.profile2,
			R.drawable.profile3, R.drawable.profile4, R.drawable.profile5, R.drawable.profile6,
			R.drawable.profile7, R.drawable.profile8, R.drawable.profile9, R.drawable.profile1};
	
	// họ tên 
	protected String[] _names = {"Nguyễn Minh Dũng", "Nguyễn Minh Thiện", "Nguyễn Tiến Dũng", "Ngô Đình Duy Quang",
			"Lê Việt Toàn", "Lê Viết Khang", "Lê Huy Sinh", "Hành Phúc Thế", "Lê Nguyễn Ngọc Hải",
			"Phan Văn Tân", "Batman"};
	
	protected String[] _phones = {"1312094", "1312551", "1312096", "1312458", "Ready To Update", "09086743", "1312486",
			"1312544", "Non-existent", "Top Secret", "Unknown"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// Lấy các control cần thiết
		_textViewChoice = (TextView) findViewById(R.id.textView1);
		_listViewInfo = (ListView) findViewById(R.id.listView1);
		
		_listViewInfo.setOnItemClickListener(new OnItemClickListener() {
			
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                
                String item = ((ViewHolder)view.getTag()).textViewName.getText().toString();           
                
                _textViewChoice.setText("You choose: " + item);
            }
        });
		
		// Nạp dữ liệu
		loadPersonalInfoData();
		
		// Tùy biến trên ListView
		_infoAdapter = new InfoAdapter(this, _data);
		_listViewInfo.setAdapter(_infoAdapter);
	}
	
	// Phương thức để thực hiện việc lấy dữ liệu và nạp vào mảng _data
	protected void loadPersonalInfoData()
	{
		_data = new ArrayList<PersonalInfo>();
		
		for (int i = 0; i < 11; i++)
		{
			//Bitmap mBitmap = BitmapFactory.decodeResource(getResources(), _profiles[i]);
			Bitmap mBitmap = decodeSampledBitmapFromResource(getResources(), _profiles[i], 90, 90);
			_data.add(new PersonalInfo(mBitmap, _names[i], _phones[i]));
		}		
	}
	
	// Tính toán kích cỡ hợp lý với kích cỡ thể hiện
    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight)
    {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth)
        {
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight && (halfWidth / inSampleSize) > reqWidth)
            {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    // Chuyễn hóa thành bitmap có kích cỡ phù hợp
    public static Bitmap decodeSampledBitmapFromResource(Resources resource, int id, int reqWidth, int reqHeight)
    {
        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(resource, id, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(resource, id, options);
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
