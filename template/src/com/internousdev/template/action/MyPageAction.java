package com.internousdev.template.action;

import java.sql.SQLException;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.template.dao.MyPageDAO;
import com.internousdev.template.dto.MyPageDTO;
import com.opensymphony.xwork2.ActionSupport;

public class MyPageAction extends ActionSupport implements SessionAware {
	public Map<String,Object> session;
	public String deleteFlg;
	private String result;
	public String execute() throws SQLException{
		MyPageDAO dao=new MyPageDAO();
		MyPageDTO dto=new MyPageDTO();

		if(deleteFlg==null){
			String item_transaction_id=session.get("id").toString();
			String user_master_id=session.get("login_user_id").toString();

			dto=dao.getMyPageUserInfo(item_transaction_id, user_master_id);
			session.put("buyItem_name", dto.getItemName());
			session.put("total_price", dto.getTotalPrice());
			session.put("total_count", dto.getTotalCount());
			session.put("total_payment", dto.getPayment());
			session.put("message", "");

		}else if(deleteFlg.equals("1")){
			delete();
		}
		result = SUCCESS;
		return result;
	}

	public void delete() throws SQLException{
		MyPageDAO dao=new MyPageDAO();

		String item_transaction_id=session.get("id").toString();
		String user_master_id=session.get("login_user_id").toString();

		int res=dao.buyItemHistoryDelete(item_transaction_id, user_master_id);

		if(res>0){
			session.put("message", "商品を正しく削除しました。");
		}else if(res==0){
			session.put("message","商品情報の削除に失敗しました。");

		}

	}

	public String getDeleteFlg(){
		return deleteFlg;
	}
	public void setDeleteFlg(String deleteFlg){
		this.deleteFlg=deleteFlg;
	}
	@Override
	public void setSession(Map<String,Object> loginSessionMap){
		this.session=loginSessionMap;
	}

}
