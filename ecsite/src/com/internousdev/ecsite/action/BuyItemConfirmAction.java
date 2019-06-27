package com.internousdev.ecsite.action;

import java.sql.SQLException;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.ecsite.dao.BuyItemCompleteDAO;
import com.opensymphony.xwork2.ActionSupport;

public class BuyItemConfirmAction extends ActionSupport implements SessionAware {
	public Map<String,Object> session;
	private BuyItemCompleteDAO dao=new BuyItemCompleteDAO();

	public String execute() throws SQLException{
		//【trows SQLException】は例外処理（try～catch文）を呼び出し元に委ね、コンパイルエラーを無くすもの。
		dao.buyItemInfo(session.get("id").toString(),
				session.get("total_price").toString(),
				session.get("count").toString(),
				session.get("login_user_id").toString(),
				session.get("pay").toString());
		String result=SUCCESS;
		return result;
	}
	@Override
	public void setSession(Map<String,Object> session){
		this.session=session;
	}

}
