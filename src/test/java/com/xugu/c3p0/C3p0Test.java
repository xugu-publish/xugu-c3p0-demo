package com.xugu.c3p0;

import org.junit.Test;
import java.sql.*;



public class C3p0Test {
	
	//@Test
	public void testCreate()
	{
		Connection con = null; 
		Statement stm = null;
		ConnectionPool pool = ConnectionPool.getInstance();  
        con = pool.getConnection();
        try {
        	stm = con.createStatement();
			stm.execute("create table userTable(id int identity(1,1),name char(20),password varchar(20))");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	//@Test
	public void testInsert()
	{
		Connection con = null; 
		PreparedStatement pstm = null;
		ConnectionPool pool = ConnectionPool.getInstance();  
        con = pool.getConnection();  
        try {
			pstm = con.prepareStatement("insert into userTable values(?,?,?)");
			pstm.setInt(1, 1);
			pstm.setString(2, "ccc");
			pstm.setString(3, "123");
			pstm.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//@Test
	public void testBatchInser()
	{
		Connection con = null; 
		PreparedStatement pstm = null;
		ConnectionPool pool = ConnectionPool.getInstance();  
        con = pool.getConnection();  
        try {
			pstm = con.prepareStatement("insert into userTable values(default,?,?)");
			//一下语句服务器要报违反唯一值错误
			//pstm = con.prepareStatement("insert into userTable(name,password) values(?,?)");
			for(int i=10;i<1000;i++)
			{
				pstm.setString(1, "ccc");
				pstm.setString(2, "123");
				pstm.addBatch();
				
			}
			pstm.executeBatch();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//@Test
	public void testUpdate()
	{
		Connection con = null; 
		PreparedStatement pstm = null;
		ConnectionPool pool = ConnectionPool.getInstance();  
        con = pool.getConnection();  
        try {
			pstm = con.prepareStatement("update userTable set name=? where id=?");
			pstm.setString(1, "hhh");
			pstm.setInt(2, 1);
			pstm.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//@Test
	public void testSelect()
	{
		Connection con = null; 
		PreparedStatement pstm = null;
		ConnectionPool pool = ConnectionPool.getInstance();  
        con = pool.getConnection();  
        try {
			pstm = con.prepareStatement("select * from userTable where id in(?,?)");
			pstm.setInt(1, 10);
			pstm.setInt(2, 100);
			ResultSet rs = pstm.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			while(rs.next())
			{
				for(int i=1;i<=rsmd.getColumnCount();i++)
				{
					System.out.println(rs.getObject(i));
					
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
        
	}
	
	@Test
	public void testDelete()
	{
		Connection con = null; 
		PreparedStatement pstm = null;
		ConnectionPool pool = ConnectionPool.getInstance();  
        con = pool.getConnection();  
        try {
			pstm = con.prepareStatement("delete from userTable where id=?");
			pstm.setInt(1, 1);
			pstm.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
