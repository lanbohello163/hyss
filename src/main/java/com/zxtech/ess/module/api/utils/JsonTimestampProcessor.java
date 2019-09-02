package com.zxtech.ess.module.api.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;  

public class JsonTimestampProcessor implements JsonValueProcessor{  
	  
	//自定义时间格式化的样式  
    private String format = "yyyy-MM-dd hh:mm:ss";
    
    public JsonTimestampProcessor() {  
        super();  
        // TODO Auto-generated constructor stub  
    }  
      
    public JsonTimestampProcessor(String format) {  
        this.format = format;  
    }  
  
    public Object processArrayValue(Object arg0, JsonConfig arg1) {  
        // TODO Auto-generated method stub  
        return arg0;  
    }  
      
    /** 
     * 处理对象的值 
     *  str 这个参数是当前需要处理的属性名 
     */  
    public Object processObjectValue(String str, Object obj, JsonConfig arg2) {  
        // TODO Auto-generated method stub  
        String ret = "";  
        if(obj instanceof java.sql.Timestamp){  
            SimpleDateFormat sdf = new SimpleDateFormat(format);  
            ret = sdf.format(((Date) obj).getTime());  
        } 
        return ret == null ? "" : ret.toString();  
    }  
}  
