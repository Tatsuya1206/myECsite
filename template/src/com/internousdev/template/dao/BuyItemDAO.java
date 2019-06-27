package com.internousdev.template.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.internousdev.template.dto.BuyItemDTO;
import com.internousdev.template.util.DBConnector;

public class BuyItemDAO {

	public BuyItemDTO getBuyItemInfo(){
		DBConnector db=new DBConnector();
		Connection con=db.getConnection();
		BuyItemDTO dto=new BuyItemDTO();

		String sql="SELECT id,item_name,item_price FROM item_info_transaction";

		try{
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();

			if(rs.next()){
			dto.setId(rs.getInt("id"));
			dto.setItemName(rs.getString("item_name"));
			dto.setItemPrice(rs.getString("item_price"));
			}

		}catch(Exception e){
			e.printStackTrace();
		}
		try{
			con.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return dto;
	}

}
