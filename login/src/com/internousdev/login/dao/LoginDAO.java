package com.internousdev.login.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.internousdev.login.dto.LoginDTO;
import com.internousdev.login.util.DBConnector;

public class LoginDAO {
	//DAOはデータベースと話すのであって、actionからの値は関係ない。
	//データベースの表ごと持ってくる。
	public LoginDTO select(String name,String password) throws SQLException{
		LoginDTO dto=new LoginDTO();
		DBConnector db=new DBConnector();
		Connection con=db.getConnection();

		String sql="select * from user where user_name=? and password=?";
		/*上記のｓｑｌ文を実行したいのでPreparedStatementが実行準備をしてくれる。
		 * またｓｑｌ文で？をパラメータの値にしておくと
		 * 後で好きに(psメソッドで)投入できる。*/

		try{
			PreparedStatement ps=con.prepareStatement(sql);
			/*ConnectionメソッドがPreparedStatementを生成している。*/

			ps.setString(1,name);
			/*ps.set+型();はPreparedStatementが持つメソッド*/
			ps.setString(2,password);

			ResultSet rs=ps.executeQuery();
			//器を作って、実行。
			/*実行結果をｒｓに入れる。
			 * -----------
			 * user_name,Password
			 * taro,123
			 * hanako,456
			 * -----------
			 * 上記のような表で引っ張りだしてくる。
			 * */

			if(rs.next()){
				dto.setName(rs.getString("user_name"));
				//DTOに値をset引数は器（ｒｓ）が取得したものをString型に変更してset。
				dto.setPassword(rs.getString("password"));
			}
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				con.close();
			}
			return dto;

		}
	}


