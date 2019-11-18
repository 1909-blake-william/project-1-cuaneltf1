package com.fdesign.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fdesign.models.User;
import com.fdesign.util.ConnectionUtil;

public class UserDaoSQL implements UserDao {
	
	User extractUser(ResultSet rs) throws SQLException {
		int id = rs.getInt("user_id");
		String rsUsername = rs.getString("username");
		String rsPassword = rs.getString("password");
		String rsFirstName = rs.getString("first_name");
		String rsLastName = rs.getString("last_name");
		String rsUserEmail = rs.getString("user_email");
		int role = rs.getInt("user_role_id");
		return new User(id, rsUsername, rsPassword, rsFirstName, rsLastName, rsUserEmail, role);
	}

	@Override
	public int save(User u) {
		try(Connection c = ConnectionUtil.getConnection()){
			String sql = "INSERT INTO expense_users (user_id, username, password, first_name, last_name, user_email "
					+ " VALUES (expense_users_id_seq.nextval,?,?,?,?,?,?)";
			
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, u.getUsername());
			ps.setString(2, u.getPassword());
			ps.setString(3, u.getFirst_name());
			ps.setString(4, u.getLast_name());
			ps.setString(5, u.getUser_email());
			ps.setInt(6, u.getUser_role_id());
			
			return ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return 0;
		}
	}

	@Override
	public List<User> findAll() {
		try(Connection c = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM expense_users";
			
			PreparedStatement ps = c.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			List<User> users = new ArrayList<User>();
			while (rs.next()) {
				users.add(extractUser(rs));
			}

			return users;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			return null;
		}
	}

//	@Override
//	public User findById() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
	@Override
	public User findByUsernameAndPassword(String username, String password) {
		try (Connection c = ConnectionUtil.getConnection()){
			
			String sql = "SELECT * FROM expense_users " + "WHERE username = ? AND password = ?";
			
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				return extractUser(rs);
			} else {
				return null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			return null;
		}
	}
//
//	@Override
//	public User findByUsername(String username) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
}
