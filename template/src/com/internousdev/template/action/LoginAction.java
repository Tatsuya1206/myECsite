package com.internousdev.template.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.template.dao.BuyItemDAO;
import com.internousdev.template.dao.LoginDAO;
import com.internousdev.template.dto.BuyItemDTO;
import com.internousdev.template.dto.LoginDTO;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport implements SessionAware {

	private String loginUserId;
	private String loginPassword;
	private String result;
	private Map<String,Object> session;

	public String execute(){
		LoginDAO dao=new LoginDAO();
		LoginDTO dto=new LoginDTO();
		BuyItemDAO buydao=new BuyItemDAO();

		result=ERROR;
		dto=dao.getLoginUserInfo(loginUserId, loginPassword);
		session.put("loginUser", dto);

		if(((LoginDTO)session.get("loginUser")).getLoginFlg()){
			result=SUCCESS;
			BuyItemDTO buydto=buydao.getBuyItemInfo();
			session.put("login_user_id", dto.getLoginId());
			session.put("id", buydto.getId());
			session.put("buyItem_name", buydto.getItemName());
			session.put("buyItem_price",buydto.getItemPrice());

			return result;
		}
		return result;
	}
	public String getLoginUserId(){
		return loginUserId;
	}
	public void setLoginUserId(String loginUserId){
		this.loginUserId=loginUserId;
	}
	public String getLoginPassword(){
		return loginPassword;
	}
	public void setLoginPassword(String loginPassword){
		this.loginPassword=loginPassword;
	}
	public Map<String,Object> getSession(){
		return session;
	}
	@Override
	public void setSession(Map<String,Object> session){
		this.session=session;
	}

}
