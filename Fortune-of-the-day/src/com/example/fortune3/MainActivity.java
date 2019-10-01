package com.example.fortune3;

import android.app.Activity;
import android.os.Bundle;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;
import java.io.*;
import java.util.*;
import android.graphics.*;

public class MainActivity extends Activity implements OnClickListener{
	
	public int Layout_num = 100;
	
	private TextView text = null;
	
	private int rnd_num[] = new int[360];
	private String line = null;
	private String mLine[] = new String[360];
	
	private Typeface face;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        setLayout(Layout_num);
        
        face = Typeface.createFromAsset(getResources().getAssets(),
        		"hmkmami.ttf");

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private void setLayout(int _num)
    {
		switch(_num)
    	{
    	case 100:
    		setContentView(R.layout.activity_main);
    		break;
    		
    	case 200:
    		setContentView(R.layout.result);
    		text = (TextView)findViewById(R.id.result_text);
    		text.setVisibility(0);
    		text.setTextSize((float) 25.0);
    		text.setTypeface(face);
            Rand();
    		getStrings(line);
    		break;
    	}
    }
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(Layout_num)
		{
    	case 100:
    		switch(v.getId())
        	{
        	case R.id.click_bt:
        		Layout_num = 200;
        		Toast toast = Toast.makeText(this, "운세는 하루에 한번씩만 보세요!\n두번이상 보는 것은 의미가 없습니다.", Toast.LENGTH_SHORT);
				toast.show();
        		setLayout(Layout_num);
        		break;
        	} 
    	case 200:	
			switch(v.getId())
			{
			case R.id.result:
				Layout_num = 100;
				setLayout(Layout_num);
				break;
			}
			break;
		}
	}
	
	private void getStrings(String _line)
	{
		try
		{
			BufferedReader _brRead = new BufferedReader( new InputStreamReader( getResources().openRawResource(R.raw.say)));
			StringBuilder buffer = new StringBuilder();
			
			int line_count = 0;
			while((line = _brRead.readLine()) != null)
			{
				buffer.append(line);
				mLine[line_count] = line;
				line_count++;
			}
			_brRead.close();
			switch(Layout_num)
			{
			case 200:
				text.setText(mLine[rnd_num[0]]);
				break;
			}
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	
	private void Rand() //----- 랜덤 값의 생성 ---
	{
		Random rand = new Random();
		int num, num1, num2;
		int _max = 360;
		 
		for (int i = 0; i < _max; i++) {
			rnd_num[i] =i;
		}

		/// 패 섞기.////////////////
		for (int i = 0; i < _max; i++) {
		 num1 = rand.nextInt(359);
		 num2 = rand.nextInt(359);
		 num = rnd_num[num1];
		 rnd_num[num1] = rnd_num[num2];
		 rnd_num[num2] = num;
		}
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
