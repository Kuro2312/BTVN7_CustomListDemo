package com.example.customlistdemo;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

class ViewHolder 
{
    public ImageView imageView;
    public TextView textViewName;
    public TextView textViewPhone;
    public int id;
}

public class InfoAdapter extends ArrayAdapter
{
	private ArrayList<PersonalInfo> _items;
	private Context _context;
	
	public InfoAdapter(Context context, ArrayList<PersonalInfo> data) 
	{
		 super(context, R.layout.info_item, data);
		 
		 // TODO Auto-generated constructor stub
		 this._context = context;
		 this._items = data;
	}
	
    public Object getItem(int position) 
    { 	
        return _items.get(position);
    }

    public long getItemId(int position) 
    {
        return position;
    }

    @Override
	public View getView(int position, View convertView, ViewGroup parent) 
	{
    	 ViewHolder holder;
	   	 if (convertView == null) 
	   	 {
	   		 LayoutInflater inflater = ((Activity)_context).getLayoutInflater();
			 convertView = inflater.inflate(R.layout.info_item, parent, false); 
				 
			 holder = new ViewHolder();
			 holder.imageView = (ImageView) convertView.findViewById(R.id.imageView1);
			 holder.textViewName = (TextView) convertView.findViewById(R.id.textView2);	
			 holder.textViewPhone = (TextView) convertView.findViewById(R.id.textView3);	
			
			 convertView.setTag(holder);
	   	 }
	   	 else
	   		 holder = (ViewHolder) convertView.getTag();

	   	 holder.textViewName.setId(position);
	   	 holder.textViewPhone.setId(position);
	   	 holder.imageView.setId(position);
	   	     
	   	 holder.imageView.setOnClickListener(new View.OnClickListener() {

	   		 public void onClick(View v) 
	   		 {

	   		 }
         });
	   	 
	   	 PersonalInfo info = _items.get(position);
	   	 
	   	 holder.textViewName.setText(info.getName());
	   	 holder.textViewPhone.setText(info.getPhone());
         holder.imageView.setImageBitmap(info.getProfilePicture());
         holder.id = position;
        
         return convertView;
	}
}
