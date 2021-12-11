package com.wang.server.common.utils;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class Tools {

	/**
	 * 随机生成六位数验证码
	 * @return
	 */
	public static int getRandomNum(){
		 Random r = new Random();
		 return r.nextInt(900000)+100000;//(Math.random()*(999999-100000)+100000)
	}
	/**
	 * 随机生成四位数验证码
	 * @return
	 */
	public static int getRandomNum4(){
		Random r = new Random();
		return r.nextInt(9000)+1000;
	}
	/**
	 * 检测字符串是否不为空(null,"","null")
	 * @param s
	 * @return 不为空则返回true，否则返回false
	 */
	public static boolean notEmpty(String s){
		return s!=null && !"".equals(s) && !"null".equals(s)&&!"".equals(s.trim())&&!"null".equals(s);
	}

	/**
	 * 检测字符串是否为空(null,"","null")
	 * @param s
	 * @return 为空则返回true，不否则返回false
	 */
	public static boolean isEmpty(String s){
		return s==null || "".equals(s) || "null".equals(s);
	}

	/**
	 * 检测对象是否为空(null,"","null")
	 * @param
	 * @return 为空则返回true，不否则返回false
	 */
	public static boolean isNullOrEmpty(Object obj) {
		if (obj == null)
			return true;
		if (obj instanceof CharSequence)
			return ((CharSequence) obj).length() == 0;
		if (obj instanceof Collection)
			return ((Collection) obj).isEmpty();
		if (obj instanceof Map)
			return ((Map) obj).isEmpty();
		if (obj instanceof Object[]) {
			Object[] object = (Object[]) obj;
			if (object.length == 0) {
				return true;
			}
			boolean empty = true;
			for (int i = 0; i < object.length; i++) {
				if (!isNullOrEmpty(object[i])) {
					empty = false;
					break;
				}
			}
			return empty;
		}
		return false;
	}
	/**
	 * 字符串转换为字符串数组
	 * @param str 字符串
	 * @param splitRegex 分隔符
	 * @return
	 */
	public static String[] str2StrArray(String str,String splitRegex){
		if(isEmpty(str)){
			return null;
		}
		return str.split(splitRegex);
	}

	/**
	 * 用默认的分隔符(,)将字符串转换为字符串数组
	 * @param str	字符串
	 * @return
	 */
	public static String[] str2StrArray(String str){
		return str2StrArray(str,",\\s*");
	}

	/**
	 * 按照yyyy-MM-dd HH:mm:ss的格式，日期转字符串
	 * @param date
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String date2Str(Date date){
		return date2Str(date,"yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 按照yyyy-MM-dd HH:mm:ss的格式，字符串转日期
	 * @param date
	 * @return
	 */
	public static Date str2Date(String date){
		if(notEmpty(date)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				return sdf.parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return new Date();
		}else{
			return null;
		}
	}

	/**
	 * 按照参数format的格式，日期转字符串
	 * @param date
	 * @param format
	 * @return
	 */
	public static String date2Str(Date date,String format){
		if(date!=null){
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.format(date);
		}else{
			return "";
		}
	}



	/**
	 * 写txt里的单行内容
	 * @param
	 * @param content  写入的内容
	 */
	public static void writeFile(String fileP,String content){
		String filePath = String.valueOf(Thread.currentThread().getContextClassLoader().getResource(""))+"../../";	//项目路径
		filePath = (filePath.trim() + fileP.trim()).substring(6).trim();
		if(filePath.indexOf(":") != 1){
			filePath = File.separator + filePath;
		}
		try {
	        OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(filePath),"utf-8");
	        BufferedWriter writer=new BufferedWriter(write);
	        writer.write(content);
	        writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	  * 验证整数
	  * @param
	  * @return
	  */
	 public static boolean isInt(String number){
	  boolean flag = false;
	  try{
	    String check = "^[0-9]*$";
	    Pattern regex = Pattern.compile(check);
	    Matcher matcher = regex.matcher(number);
	    flag = matcher.matches();
	   }catch(Exception e){
	    flag = false;
	   }
	  return flag;
	 }

	/**
	  * 验证邮箱
	  * @param email
	  * @return
	  */
	 public static boolean checkEmail(String email){
	  boolean flag = false;
	  try{
	    String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
	    Pattern regex = Pattern.compile(check);
	    Matcher matcher = regex.matcher(email);
	    flag = matcher.matches();
	   }catch(Exception e){
	    flag = false;
	   }
	  return flag;
	 }

	 /**
	  * 验证手机号码
	  * @param
	  * @return
	  */

	 public static boolean checkMobileNumber(String mobileNumber){
	  boolean flag = false;
	  try{
	    Pattern regex = Pattern.compile("^(((13[0-9])|(14[5|7|9])|(15([0-3]|[5-9]))|(17[3,5-8])|(18[0-9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$");
	    Matcher matcher = regex.matcher(mobileNumber);
	    flag = matcher.matches();
	   }catch(Exception e){
	    flag = false;
	   }
	  return flag;
	 }



	/**
	 * 读取txt里的单行内容
	 * @param
	 */
	public static String readTxtFile(String fileP) {
		try {
			String filePath = String.valueOf(Thread.currentThread().getContextClassLoader().getResource(""))+"../../";	//项目路径
			filePath = filePath.replaceAll("file:/", "");
			filePath = filePath.replaceAll("%20", " ");
			filePath = filePath.trim() + fileP.trim();
			if(filePath.indexOf(":") != 1){
				filePath = File.separator + filePath;
			}
			String encoding = "utf-8";
			File file = new File(filePath);
			if (file.isFile() && file.exists()) { 		// 判断文件是否存在
				InputStreamReader read = new InputStreamReader(
				new FileInputStream(file), encoding);	// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					return lineTxt;
				}
				read.close();
			}else{
				log.info("找不到指定的文件,查看此路径是否正确:{}",filePath);

			}
		} catch (Exception e) {
			log.info("读取文件内容出错");
		}
		return "";
	}
	/**
	 * @param checkType 校验类型：0校验手机号码，1校验座机号码，2两者都校验满足其一就可
	 * @param phoneNum
	 * */
	public static boolean validPhoneNum(String checkType,String phoneNum){
		boolean flag=false;
		Pattern p1 = null;
		Pattern p2 = null;
		Matcher m = null;
		p1 = Pattern.compile("^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1}))+\\d{8})?$");
		p2 = Pattern.compile("^(0[0-9]{2,3}\\-)?([1-9][0-9]{6,7})$");
		if("0".equals(checkType)){
			if(phoneNum.length()!=11){
				return false;
			}else{
				m = p1.matcher(phoneNum);
				flag = m.matches();
			}
		}else if("1".equals(checkType)){
			if(phoneNum.length()<11||phoneNum.length()>=16){
				return false;
			}else{
				m = p2.matcher(phoneNum);
				flag = m.matches();
			}
		}else if("2".equals(checkType)){
			if(!((phoneNum.length() == 11 && p1.matcher(phoneNum).matches())||(phoneNum.length()<16&&p2.matcher(phoneNum).matches()))){
				return false;
			}else{
				flag = true;
			}
		}
		return flag;
	}


	public static boolean cheakOrgCode(String str) {
	    final String[] codeNo = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B",
	            "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "O", "P", "Q", "R", "S",
	            "T", "U", "V", "W", "X", "Y", "Z" };
	    final String[] staVal = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11",
	            "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24",
	            "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35" };
	    Map map = new HashMap();
	    for (int i = 0; i < codeNo.length; i++) {
	        map.put(codeNo[i], staVal[i]);
	    }
	    final int[] wi = { 3, 7, 9, 10, 5, 8, 4, 2 };
	    Pattern pat = Pattern.compile("^[0-9A-Z]{8}-[0-9X]$");
	    Matcher matcher = pat.matcher(str);
	    if (!matcher.matches()) {
			log.info("你的表达式非法");
	        return false;
	    }
	    String[] all = str.split("-");
	    final char[] values = all[0].toCharArray();
	    int parity = 0;
	    for (int i = 0; i < values.length; i++) {
	        final String val = Character.toString(values[i]);
	        parity += wi[i] * Integer.parseInt(map.get(val).toString());
	    }
	    String cheak = (11 - parity % 11) == 10 ? "X" : Integer.toString((11 - parity % 11));
	    return cheak.equals(all[1]);
	}

	/**
	 * 将字符串转成ASCII
	 * @return
	 */
	public static String str2Asc(String str){
		StringBuilder sb = new StringBuilder();
		String regex = "^[a-zA-Z]+$";
		char[] ch = str.toCharArray();
		for (int i = 0; i < ch.length; i++) {
			if(String.valueOf(ch[i]).matches(regex)){
				sb.append(Integer.valueOf(ch[i]).intValue());
			}else{
				sb.append(ch[i]);
			}

		}
		return sb.toString();
	}

	/**
	 * 获取当前时间
	 */
	public static LocalDateTime nowDateTime(){
		return LocalDateTime.now();
	}

	/**
	 * 获取当前时间
	 */
	public static LocalDate nowDate(){
		return LocalDate.now();
	}

	/**
	 * 获取当前时间
	 */
	public static LocalTime nowTime(){
		return LocalTime.now();
	}

	public static void addJson(HttpServletResponse httpServletResponse, String code , String errorMsg) throws Exception{
		try {
			httpServletResponse.setCharacterEncoding("utf-8");
			httpServletResponse.setContentType("application/json; charset=utf-8");
			httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
			httpServletResponse.setHeader("Cache-Control", "no-cache");
			PrintWriter out = httpServletResponse.getWriter();
			JSONObject res = new JSONObject();
			res.put("code",code);
			res.put("msg",errorMsg);
			out.write(res.toString());
			out.flush();
			out.close();
		}catch (Exception e){
			e.printStackTrace();
		}

	}

}
