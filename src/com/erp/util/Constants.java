package com.erp.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;

import com.erp.model.Log;
import com.erp.shiro.ShiroUser;

/**
* 类功能说明 TODO:常用常量定义
* 类修改者
* 修改日期
* 修改说明
* <p>Title: Constants.java</p>
* <p>Description:福产流通科技</p>
* <p>Copyright: Copyright (c) 2006</p>
* <p>Company:福产流通科技有限公司</p>
* @author lsy 756514656@qq.com
* @date 2013-5-7 下午2:50:52
* @version V1.0
*/

public class Constants
{
	public static final String LOGIN_SESSION_DATANAME="users";
	public static final String LOGIN_URL="login";
	public static final String LOGIN_SUCCESS_URL="index";
	public static final String LOGIN_LOGIN_OUT_URL="loginout";
	public static final String LOGIN_MSG="loginMsg";
	public static final String USERNAME_IS_NULL="用户名为空!";
	public static final String LOGIN_IS_EXIST="该用户已登录!";
	public static final String UNKNOWN_SESSION_EXCEPTION="异常会话!";
	public static final String UNKNOWN_ACCOUNT_EXCEPTION="账号错误!";
	public static final String INCORRECT_CREDENTIALS_EXCEPTION="密码错误!";
	public static final String LOCKED_ACCOUNT_EXCEPTION="账号已被锁定，请与系统管理员联系!";
	public static final String INCORRECT_CAPTCHA_EXCEPTION= "验证码错误!";
	public static final String AUTHENTICATION_EXCEPTION= "您没有授权!";
	public static final String UNKNOWN_EXCEPTION= "出现未知异常,请与系统管理员联系!";
	public static final String TREE_GRID_ADD_STATUS= "add";
	public static final String POST_DATA_SUCCESS= "数据更新成功!";
	public static final String POST_DATA_FAIL= "提交失败了!";
	public static final String GET_SQL_LIKE= "%";
	public static final String IS_FUNCTION= "F";
	public static final String PERSISTENCE_STATUS= "A";
	public static final String PERSISTENCE_DELETE_STATUS= "I";
	public static final String SYSTEM_ADMINISTRATOR= "admin";
	public static final String NULL_STRING= "";
	public static final String IS_DOT= ".";
	public static final String HQL_LIKE= "like";
	public static final String TEXT_TYPE_PLAIN= "text/plain";
	public static final String TEXT_TYPE_HTML= "text/html";
	public static final String FUNCTION_TYPE_O= "O";
	public static final String TREE_STATUS_OPEN= "open";
	public static final String TREE_STATUS_CLOSED= "closed";
	public static final String IS_EXT_SUBMENU= " 或可能包含菜单!";
	public static final String SHIRO_USER= "shiroUser";
	public static final String LOGS_INSERT= "insert:";
	public static final String LOGS_INSERT_TEXT= "插入:";
	public static final String LOGS_INSERT_NAME= "insertLogs";
	public static final String LOGS_UPDATE= "update:";
	public static final String LOGS_UPDATE_TEXT= "更新:";
	public static final String LOGS_UPDATE_NAME= "updateLogs";
	public static final String LOGS_DELETE= "delete:";
	public static final String LOGS_DELETE_TEXT= "删除:";
	public static final String LOGS_DELETE_NAME= "deleteLogs";
	public static final String LOGS_TB_NAME= "Log";
	public static final String FILE_SUFFIX_SQL= ".sql";
	public static final String FILE_SUFFIX_ZIP= ".zip";
	/**  
	* 函数功能说明 TODO:获取UUID生成的主键
	* Administrator修改者名字
	* 2013-5-8修改日期
	* 修改内容
	* @Title: getPrimaryKeyByUUID 
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	public static String getPrimaryKeyByUUID(){
		return UUID.randomUUID().toString();
	}  
	/**  
	* 函数功能说明 TODO:获取当前登录用户实体类
	* Administrator修改者名字
	* 2013-5-10修改日期
	* 修改内容
	* @Title: getCurrendUser 
	* @Description: TODO:
	* @param @return    设定文件 
	* @return Users    返回类型 
	* @throws 
	*/
	public static ShiroUser getCurrendUser(){
		Subject subject=SecurityUtils.getSubject();
		return (ShiroUser)subject.getSession().getAttribute(SHIRO_USER);
	}  
	
	/**  
	* 函数功能说明 TODO:高级查询hql条件拼接
	* Administrator修改者名字
	* 2013-5-30修改日期
	* 修改内容
	* @Title: getSearchConditionsHQL 
	* @Description: 
	* @param @param asName
	* @param @param searchColumnNames
	* @param @param searchAnds
	* @param @param searchConditions
	* @param @param searchVals
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	public static String getGradeSearchConditionsHQL(String asName,PageUtil pageUtil)
	{
		String searchAnds = pageUtil.getSearchAnds();
		String searchColumnNames=pageUtil.getSearchColumnNames();
		String searchConditions=pageUtil.getSearchConditions();
		String searchVals=pageUtil.getSearchVals();
		if(null!=searchColumnNames && searchColumnNames.trim().length()>0){
			StringBuffer sb=new StringBuffer();
			String[] searchColumnNameArray=searchColumnNames.split("\\,");
			String[] searchAndsArray=searchAnds.split("\\,");
			String[] searchConditionsArray=searchConditions.split("\\,");
			String[] searchValsArray=searchVals.split("\\,");
			for (int i = 0; i < searchColumnNameArray.length; i++) {
				if (searchColumnNameArray[i].trim().length() > 0 && searchConditionsArray[i].trim().length()>0) {
					/*if (i == 0) {
						sb.append(asName+"."+searchColumnNameArray[i].trim() + " " + searchConditionsArray[i].trim() + " " + searchValsArray[i].trim());
					} else {
					}*/
					String temp=searchValsArray[i].trim().replaceAll("\\'", "");
					if (HQL_LIKE.equals(searchConditionsArray[i].trim()))
					{
						sb.append(" " + searchAndsArray[i].trim() + " " + asName+IS_DOT+searchColumnNameArray[i].trim() + " " + searchConditionsArray[i].trim() + " " +"'%"+ temp+"%'");

					}else {
						sb.append(" " + searchAndsArray[i].trim() + " " + asName+IS_DOT+searchColumnNameArray[i].trim() + " " + searchConditionsArray[i].trim() + " " +"'"+ temp+"'");
					}
				}
			}
			if(sb.length()>0){
				return sb.toString();
			}
		}
		return NULL_STRING;
	}
	
	/**  
	* 函数功能说明 TODO:获得简单查询条件
	* Administrator修改者名字
	* 2013-5-30修改日期
	* 修改内容
	* @Title: getSearchConditionsHQL 
	* @Description: 
	* @param @param asName
	* @param @param param
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	public static String getSearchConditionsHQL(String asName ,Map<String, Object> param){
		StringBuffer sb=new StringBuffer();
		if (param != null && !param.isEmpty()) {
			for (String name : param.keySet())
			{
				sb.append(" and "+asName+Constants.IS_DOT+name+" like :"+name+"");
			}
		}
		return sb.toString();
	}
	
	/**  
	* 函数功能说明 TODO:获取操作日志
	* Administrator修改者名字
	* 2013-6-18修改日期
	* 修改内容
	* @Title: getLogs 
	* @Description: 
	* @param @param session
	* @param @param o
	* @param @param eventName
	* @param @param er
	* @param @param name    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	public static <T> void getLogs(Session session,T o,String eventName,String er,String name)
	{
		if (!LOGS_TB_NAME.equals(o.getClass().getSimpleName()))
		{
			String ip = getIpAddr();
			String mac=getMacAddr();
			String[] sdf = getFiledName(o);
			String id = getFieldValueByName(sdf[1], o).toString();
			Log l=new Log();
			l.setUserId(Constants.getCurrendUser().getUserId());
			l.setName(Constants.getCurrendUser().getAccount());
			l.setLogDate(new Date());
			l.setEventName(eventName+o.getClass().getSimpleName());
			l.setEventRecord(er+o.getClass().getName());
			l.setObjectId(id);
			l.setType(2);
			l.setIp(ip);
			l.setMac(mac);
			session.save(l);
		}
	}

	public static String[] getFiledName(Object o )
	{
		try
		{
			Field[] fields = o.getClass().getDeclaredFields();
			String[] fieldNames = new String[fields.length];
			for (int i = 0; i < fields.length; i++)
			{
				fieldNames[i] = fields[i].getName();
			}
			return fieldNames;
		} catch (SecurityException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public static Object getFieldValueByName(String fieldName, Object o)    
	{       
	   try    
	   {       
	       String firstLetter = fieldName.substring(0, 1).toUpperCase();       
	       String getter = "get" + firstLetter + fieldName.substring(1);       
	       Method method = o.getClass().getMethod(getter, new Class[] {});       
	       Object value = method.invoke(o, new Object[] {});       
	       return value;       
	   } catch (Exception e)    
	   {       
	       System.out.println("属性不存在");       
	       return "";       
	   }       
	}  
	
	  public static HashMap<String, Method> ConverBean(Class<?> drbean) {  
	        Class<?> stopClass = null;  
	        // 存放class信息  
	        BeanInfo drbeaninfo = null;  
	        // 存放属性信息  
	        PropertyDescriptor[] props;  
	        HashMap<String, Method> map = new HashMap<String, Method>();  
	        try {  
	            // 获取class中得属性方法信息  
	            drbeaninfo = Introspector.getBeanInfo(drbean, stopClass);  
	            // 把class中属性放入PropertyDescriptor数组中  
	            props = drbeaninfo.getPropertyDescriptors();  
	            for (int i = 0; i < props.length; i++) {  
	                // 获取属性所对应的set方法  
	                Method setMethod = props[i].getWriteMethod();  
	                // 判断属性是否有set方法 如果有放入map<属性名，set方法>中  
	                if (setMethod != null) {  
	                    String field = props[i].getName().toLowerCase();  
	                    map.put(field, setMethod);  
	                }  
	            }  
	        } catch (IntrospectionException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	        }  
	        return map;  
	    }
	
	/**  
	* 函数功能说明 TODO:获取客户端ip地址
	* Administrator修改者名字
	* 2013-6-19修改日期
	* 修改内容
	* @Title: getIpAddr 
	* @Description: 
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	public static String getIpAddr() {
		   HttpServletRequest request=ServletActionContext.getRequest();
	       String ip = request.getHeader("x-forwarded-for");
	       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	           ip = request.getHeader("Proxy-Client-IP");
	       }
	       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	           ip = request.getHeader("WL-Proxy-Client-IP");
	       }
	       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	           ip = request.getRemoteAddr();
	       }
	       return ip;
	   } 
	
	/**  
	* 函数功能说明 TODO:获取客户端mac地址
	* Administrator修改者名字
	* 2013-6-19修改日期
	* 修改内容
	* @Title: getMacAddr 
	* @Description: 
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	public static String  getMacAddr()
	{
		String smac = "";
		try
		{
			UdpGetClientMacAddr umac = new UdpGetClientMacAddr(getIpAddr());
			smac = umac.GetRemoteMacAddr();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return smac;
	}
	private static final int BUFFER_SIZE = 16 * 1024;
	 public static void copy(File src, String fullSavePath) {
	        InputStream in = null; 
	        OutputStream out = null; 
	        File newFile=new File(fullSavePath);
	        try { 
	            in = new BufferedInputStream(new FileInputStream(src), BUFFER_SIZE); 
	            out = new BufferedOutputStream(new FileOutputStream(newFile), 
	                    BUFFER_SIZE); 
	            byte[] buffer = new byte[BUFFER_SIZE]; 
	            int len = 0; 
	            while ((len = in.read(buffer)) > 0) { 
	                out.write(buffer, 0, len); 
	            } 
	            out.flush();
	        } catch (Exception e) { 
	            e.printStackTrace(); 
	        } finally { 
	            if (null != in) { 
	                try { 
	                    in.close(); 
	                } catch (IOException e) { 
	                    e.printStackTrace(); 
	                } 
	            } 
	            if (null != out) { 
	                try { 
	                    out.close(); 
	                } catch (IOException e) { 
	                    e.printStackTrace(); 
	                } 
	            } 
	        } 
	    }
	 public static String BASE_PATH =System.getProperty("erp");
	 public static String dbBackUp()
		{
			//生成临时备份文件
			SimpleDateFormat sd=new SimpleDateFormat("yyyyMMddHHmmss");
			String fineName="dbBackUp-"+sd.format(new Date());
			String sqlName=fineName+Constants.FILE_SUFFIX_SQL;
			String pathSql=BASE_PATH+"attachment"+File.separator+"dbBackUp";
			try {
				File filePathSql = new File(pathSql);
				if(!filePathSql.exists()){
					filePathSql.mkdir();
				}
				StringBuffer sbs = new StringBuffer();
				sbs.append("mysqldump ");
				sbs.append("-h 192.168.110.10 ");
				sbs.append("--user=root");
				sbs.append(" --password=fortune123");
				sbs.append(" --lock-all-tables=true ");
				sbs.append("--result-file="+pathSql+File.separator);
				sbs.append(sqlName+" ");
				sbs.append(" --default-character-set=utf8 ");
				sbs.append("ERP");
		        Runtime runtime = Runtime.getRuntime();
		        Process child = runtime.exec(sbs.toString());
		        //读取备份数据并生成临时文件
		        InputStream in = child.getInputStream();
		        OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(pathSql), "utf8");
		        BufferedReader reader = new BufferedReader(new InputStreamReader(in, "utf8"));
		        String line=reader.readLine();
		        while (line != null) {
		                writer.write(line+"\n");
		                line=reader.readLine();
		         }
		         writer.flush();
			} catch (Exception e) {
				
			}
			return sqlName;
		}
}
