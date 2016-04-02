package com.example.customlistdemo;

import android.graphics.Bitmap;

public class PersonalInfo 
{
	protected Bitmap _profilePicture;
	protected String _name;
	protected String _phone;
	
	public Bitmap getProfilePicture()
	{
		return _profilePicture;
	}
	
	public String getName()
	{
		return _name;
	}
	
	public String getPhone()
	{
		return _phone;
	}
	
	public PersonalInfo(Bitmap picture, String name, String phone)
	{
		_profilePicture = picture;
		_name = name;
		_phone = phone;
	}
}
