package com.internousdev.ecsite.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public String getDate(){
		Date dt=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd/HH:mm:ss");
		return sdf.format(dt);

	}

}
