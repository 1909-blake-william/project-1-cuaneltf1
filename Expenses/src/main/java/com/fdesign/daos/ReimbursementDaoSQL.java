package com.fdesign.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.fdesign.models.Reimbursement;
import com.fdesign.models.User;
import com.fdesign.util.ConnectionUtil;

public class ReimbursementDaoSQL implements ReimbursementDao {
	
	Reimbursement extractReimbursement(ResultSet rs) throws SQLException {
		int id = rs.getInt("reimbursement_id");
		int amount = rs.getInt("reimbursement_amount");
		Timestamp submitted = rs.getTimestamp("reimbursement_submitted");
		Timestamp resolved = rs.getTimestamp("reimbursement_resolved");
		String rsDescription = rs.getString("reimbursement_description");
		String rsResolution = rs.getString("reimbursement_resolution");
		int author = rs.getInt("reimbursement_author");
		int resolver = rs.getInt("reimbursement_resolver");
		int statusId = rs.getInt("reimbursement_status_id");
		int typeId = rs.getInt("reimbursement_type_id");
		return new Reimbursement(id, amount, submitted, resolved, rsDescription, rsResolution, author, resolver, statusId, typeId);
	}

	@Override
	public void save(Reimbursement r) {
		try(Connection c = ConnectionUtil.getConnection()){
			String sql1 = "INSERT INTO reimbursement (reimbursement_id, reimbursement_amount, reimbursement_submitted, reimbursement_description, reimbursement_author, reimbursement_type_id) "
					+ " VALUES (reimbursement_id_seq.nextval, ?, TO_TIMESTAMP(LOCALTIMESTAMP, 'DD-MON-RR HH.MI.SSXFF PM'),?,?,?)";
			
			PreparedStatement ps1 = c.prepareStatement(sql1);
			ps1.setInt(1, r.getReimbursement_amount());
			ps1.setString(2, r.getReimbursement_description());
			ps1.setInt(3, r.getReimbursement_author());
			ps1.setInt(4, r.getReimbursement_type_id());
			ps1.executeUpdate();
			
			return;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return;
		}
	}

	@Override
	public List<Reimbursement> findAll() {
		try(Connection c = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM reimbursement ORDER BY reimbursement_id";
			
			PreparedStatement ps = c.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			List<Reimbursement> reimbursement = new ArrayList<Reimbursement>();
			while(rs.next()) {
				reimbursement.add(extractReimbursement(rs));
			}
			return reimbursement;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}

	@Override
	public void updateReimbursement(String rsResolution, int resolver, int statusId, int id) {
		try(Connection c = ConnectionUtil.getConnection()){
			String sql = "UPDATE reimbursement SET reimbursement_resolved = TO_TIMESTAMP(LOCALTIMESTAMP, 'DD-MON-RR HH.MI.SSXFF PM'), reimbursement_resolution = ?, reimbursement_resolver = ?, reimbursement_status_id = ? WHERE reimbursement_id =?";
			
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, rsResolution);
			ps.setInt(2, resolver);
			ps.setInt(3, statusId);
			ps.setInt(4, id);
			ps.executeUpdate();
			
			return;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return;
		}
	}

	@Override
	public List<Reimbursement> findByAuthorAndStatus(int author, int statusId) {
		try(Connection c = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM reimbursement" + " WHERE reimbursement_author = ? AND reimbursement_status_id = ? ORDER BY reimbursement_id";
			
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, author);
			ps.setInt(2, statusId);
			
			ResultSet rs = ps.executeQuery();
			List<Reimbursement> reimbursement = new ArrayList<Reimbursement>();
			while(rs.next()) {
				reimbursement.add(extractReimbursement(rs));
			}
			return reimbursement;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}
	
	@Override
	public List<Reimbursement> findReimbursementByUserAuthor(int author){
		try(Connection c = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM reimbursement WHERE reimbursement_author = ? ORDER BY reimbursement_id";
			
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, author);
			
			ResultSet rs = ps.executeQuery();
			List<Reimbursement> reimbursement = new ArrayList<Reimbursement>();
			while(rs.next()) {
				reimbursement.add(extractReimbursement(rs));
			}
			return reimbursement;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}

	@Override
	public List<Reimbursement> findReimbursementByUserStatus(int statusId) {
		try(Connection c = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM reimbursement WHERE reimbursement_status_id = ? ORDER BY reimbursement_id";
			
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, statusId);
			
			ResultSet rs = ps.executeQuery();
			List<Reimbursement> reimbursement = new ArrayList<Reimbursement>();
			while(rs.next()) {
				reimbursement.add(extractReimbursement(rs));
			}
			return reimbursement;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}

}
