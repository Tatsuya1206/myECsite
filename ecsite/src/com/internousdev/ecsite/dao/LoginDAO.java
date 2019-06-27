package com.internousdev.ecsite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.internousdev.ecsite.dto.LoginDTO;
import com.internousdev.ecsite.util.DBConnector;

public class LoginDAO {

	private DBConnector db=new DBConnector();
	private Connection con=db.getConnection();
	private LoginDTO dto=new LoginDTO();

	public LoginDTO getLoginUserInfo(String loginUserId,String loginPassword){

		String sql="SELECT * FROM login_user_transaction where login_id = ? AND login_pass = ?";
		/*
		 * login_user_tranzactionテーブルから全部の列（login_id,login_pass,user_name）を持ってこい。
		 * */
		try{
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, loginUserId);//「1」はsql文の左から数えて設置する「？」の場所のこと。
			ps.setString(2, loginPassword);

			ResultSet rs=ps.executeQuery();

			/*
			 * ------rs(器)------
			 * loginUserId/loginPasswrod/user_name
			 *  (カーソルは0行目に存在する。正確には１行目の上。)
			 *       taro /    123      / taro-maru
			 *       jiro /    456      / jiroEmonn
			 *
			 * */

			if(rs.next()){
				dto.setLoginId(rs.getString("login_id"));//(rsからgetしてくる)内のモノをDTOにsetする
				dto.setLoginPassword(rs.getString("login_pass"));
				dto.setUserName(rs.getString("user_name"));

				if(rs.getString("login_id") !=null){//正常値だったら以下の処理。
					dto.setLoginFlg(true);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		try{
			con.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return dto;

	}
	public LoginDTO getLoginDTO(){
		return dto;
	}

}
