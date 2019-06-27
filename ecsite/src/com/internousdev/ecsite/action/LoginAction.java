package com.internousdev.ecsite.action;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.ecsite.dao.BuyItemDAO;
import com.internousdev.ecsite.dao.LoginDAO;
import com.internousdev.ecsite.dto.BuyItemDTO;
import com.internousdev.ecsite.dto.LoginDTO;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport implements SessionAware {
	private String loginUserId;
	private String loginPassword;
	public Map<String,Object> session;
	private LoginDAO ldao=new LoginDAO();
	private LoginDTO ldto=new LoginDTO();
	private BuyItemDAO bdao=new BuyItemDAO();

	public String execute(){
		String result=ERROR;
		ldto=ldao.getLoginUserInfo(loginUserId, loginPassword);
		session.put("loginUser",ldto);

		if(((LoginDTO)session.get("loginUser")).getLoginFlg()){
			result=SUCCESS;
			BuyItemDTO bdto=bdao.getBuyIteminfo();
			session.put("login_user_id",ldto.getLoginId());//（"key(名前)",値）
			session.put("id", bdto.getId());
			session.put("buyItem_name",bdto.getItemName());
			session.put("buyItem_price",bdto.getItemPrice());

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
	@Override
	public void setSession(Map<String,Object> session){
		this.session=session;
	}

}
