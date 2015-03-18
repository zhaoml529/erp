package com.erp.serviceImpl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.dao.PublicDao;
import com.erp.model.Parameter;
import com.erp.service.SystemParameterService;
import com.erp.shiro.ShiroUser;
import com.erp.util.Constants;
import com.erp.viewModel.CheckBoxModel;
import com.erp.viewModel.Options;
import com.erp.viewModel.ParameterModel;


@Service("systemParameterService")
public class SystemParameterServiceImpl implements SystemParameterService
{
	private PublicDao<Parameter> publicDao;
	@Autowired
	public void setPublicDao(PublicDao<Parameter> publicDao )
	{
		this.publicDao = publicDao;
	}
	
	public  List<ParameterModel> findParameterList(String type)
	{
		String hql="from Parameter t where t.status='A'";
		 List<Parameter> temp = publicDao.find(hql);
		 List<ParameterModel> list2=new ArrayList<ParameterModel>();
		 for (Parameter p : temp)
		{
			ParameterModel pm=new ParameterModel();
			try
			{
				BeanUtils.copyProperties(pm, p);
				if ("checkbox".equals(p.getEditorType()))
				{
					CheckBoxModel cm=new CheckBoxModel();
					cm.setType("checkbox");
					cm.setOptions(new Options());
					pm.setEditor(cm);
				}else {
					pm.setEditor(p.getEditorType());
				}
				list2.add(pm);
			} catch (IllegalAccessException e)
			{
				e.printStackTrace();
			} catch (InvocationTargetException e)
			{
				e.printStackTrace();
			}
		}
		return list2;
	}
	public boolean persistenceParameter(Map<String, List<Parameter>> map) 
	{
		this.addParameter(map.get("addList"));
		this.updParameter(map.get("updList"));
		this.delParameter(map.get("delList"));
		return true;
	}
	
	public boolean addParameter(List<Parameter> addlist) 
	{
		
		if (addlist!=null&&addlist.size()!=0) {
			ShiroUser users = Constants.getCurrendUser();
			for (Parameter companyInfo : addlist) {
				companyInfo.setCreated(new Date());
				companyInfo.setLastmod(new Date());
				companyInfo.setStatus("A");
				if (users!=null)
				{
					companyInfo.setCreater(users.getUserId());
					companyInfo.setModifyer(users.getUserId());
				}
				publicDao.save(companyInfo);
			}
		}
		return true;
	}
	
	public boolean updParameter(List<Parameter>  updlist) 
	{
		if (updlist!=null&&updlist.size()!=0) {
			ShiroUser users = Constants.getCurrendUser();
			for (Parameter companyInfo : updlist) {
				
				companyInfo.setLastmod(new Date());
				companyInfo.setModifyer(users.getUserId());
				publicDao.update(companyInfo);
			}
		}
		return true;
	}
	
	public boolean delParameter(List<Parameter>  dellist)
	{
		if (dellist!=null&&dellist.size()!=0) {
			for (Parameter companyInfo : dellist) {
				companyInfo.setStatus("I");
				companyInfo.setLastmod(new Date());
				publicDao.deleteToUpdate(companyInfo);
			}
		}
		return true;
	}
}
