package test.hecj.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class SqlUtil {

	public static String withSplit(List ids100, String sp) {
		String str="";
		for (Object id : ids100) {
			str+="'"+id+"'"+sp+"";
		}
		if (ids100!=null&&ids100.size()>0) {
			str=str.substring(0,str.length()-1);
		}
		return str;
	}
	public static String onlyWithSplit(List ids100, String sp) {
		String str="";
		for (Object id : ids100) {
			str+=id+sp;
		}
		if (ids100!=null&&ids100.size()>0) {
			str=str.substring(0,str.length()-1);
		}
		return str;
	}
	public static String filterEmj(String source){
//
//		     Pattern emoji = Pattern . compile (   "[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]" ,
//
//		         Pattern . UNICODE_CASE | Pattern . CASE_INSENSITIVE ) ;

		if (StringUtils.isNotEmpty(source)) {
			return source.replaceAll("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]", "");
		}
		return "";
 
		     
	}
	public static void main(String[] args) {
//		 System.out.println(filterEmj("FP_ViVi༄༣"));
		List<Long> list = new ArrayList<Long>();
		list.add(1l);
		list.add(2l);
		System.out.println(list.contains(21l));
				
	}
	 public static String html(String content) {
		 if(content==null) return "";        
		     String html = content;
		     
		     html = html.replace( "\"", "");
		     html = html.replace( "'", "&apos;");
		     html = html.replaceAll( "&", "&amp;");
		     html = html.replace( "\"", "&quot;");  //"
		     html = html.replace( "\t", "&nbsp;&nbsp;");// 替换跳格
		     html = html.replace( " ", "&nbsp;");// 替换空格
		     html = html.replace("<", "&lt;");
		 
		     html = html.replaceAll( ">", "&gt;");
		   
		     return html;
		 }

}
