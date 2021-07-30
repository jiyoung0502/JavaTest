package Member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OSQL {

	// [1] ���Ӻ���
	Connection con = null;
	
	// [2] �����͸� ������ ����
	Statement stmt = null;
	PreparedStatement pstmt = null; // ? �� '����'�� �ν�
	
	// [3] �����͸� �޾ƿ��� ����
	ResultSet rs = null;
	
	
	// 1. DB���� �޼ҵ�
	public void connect() {
		con = DBC.DBConnect();
	}

	// 2. DB�������� �޼ҵ�
	public void conClose() {
		try {
			con.close();
			System.out.println("DB��������!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void insert(OMember omem) {
		String sql = "INSERT INTO OMB VALUES(?,?,TO_DATE(?))";
		
		// sql���� ?�� �ִ��� Ȯ��
		// ������ pstmt ���(�� ���!)
		// ������ stmt ��� (pstmt ��밡��)
		
		try {
			
			pstmt = con.prepareStatement(sql);
			// sql���� DB�� �����ϱ� ���� �غ����
			
			pstmt.setInt(1, omem.getStuno());
			pstmt.setString(2, omem.getOname());
			pstmt.setString(3, omem.getObirth());
			// INSERT INTO OMB VALUES(1501,'���ȯ',TO_DATE('20210722'));
			// ?�ȿ� ���� �־ Ÿ���θ� �� ����
			
			int result = pstmt.executeUpdate();
			// DB����(Ctrl+Enter) => ��� : n���� ���Եƽ��ϴ�. ���� n��
			// result�� ����
			
			System.out.println("result�� : " + result);
			
			if(result > 0) {
				System.out.println("ȸ����� ����!");
			} else {
				System.out.println("ȸ����� ����!");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 4. ȸ����ȸ �޼ҵ�
	public void select() {
		String sql = "SELECT * FROM OMB";
		// sql���� ?�� �ִ��� Ȯ��
		// ������ pstmt ���(�� ���!)
		// ������ stmt ��� (pstmt ��밡��)
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {	// rs.next() : �������� ������ŭ true��, ������ false
								// ���ڵ尡 ���̻� �������� ������ false�� => �����.
				System.out.println();
				//System.out.println("�л���ȣ : " + rs.getInt(1)); 		// (n)���� n�� �÷� ����
				//System.out.println("�л��̸� : " + rs.getString(2));
				//System.out.println("������� : " + rs.getString(3));
				
				System.out.println("�л���ȣ : " + rs.getInt("STUNO")); 		// �÷����� ��� �÷��̸� �־ ��
				System.out.println("�л��̸� : " + rs.getString("ONAME"));
				System.out.println("������� : " + rs.getString("OBIRTH"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	// 5-1, 6-1 �л�����üũ
	public boolean checkStu(int stuno, String oname) {
		
		boolean result = false;
		
		String sql = "SELECT STUNO FROM OMB WHERE STUNO=? AND ONAME=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, stuno);
			pstmt.setString(2, oname);
			
			// INSERT, UPDATE, DELETE���� ¦�� int result
			// SELECT���� ¦�� ResultSet rs
			rs = pstmt.executeQuery();
			
			// while, if
			if(rs.next()) {		// SELECT���� ����� �����Ѵٸ�
				result = true;
			} else {			// �������� �ʴ´ٸ�
				result = false;
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public void modify(OMember omem) {
		String sql = "UPDATE OMB SET ONAME=?, OBIRTH=? WHERE STUNO=?";
		try { 										// ? ������� �ۼ�
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, omem.getOname());
			pstmt.setString(2, omem.getObirth());
			pstmt.setInt(3, omem.getStuno());
			
			int result = pstmt.executeUpdate();
			
			if(result > 0) {
				System.out.println("ȸ������ ���� ����!");
			} else {
				System.out.println("ȸ������ ���� ����!");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public void delete(int stuno) {
		String sql = "DELETE OMB WHERE STUNO=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, stuno);
			
			int result = pstmt.executeUpdate();
			
			if(result>0) {
				System.out.println("ȸ������ ���� ����!");
			} else {
				System.out.println("ȸ������ ���� ����!");
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
