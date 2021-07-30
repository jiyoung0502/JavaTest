package Member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OSQL {

	// [1] 접속변수
	Connection con = null;
	
	// [2] 데이터를 보내는 변수
	Statement stmt = null;
	PreparedStatement pstmt = null; // ? 가 '문자'로 인식
	
	// [3] 데이터를 받아오는 변수
	ResultSet rs = null;
	
	
	// 1. DB접속 메소드
	public void connect() {
		con = DBC.DBConnect();
	}

	// 2. DB접속해제 메소드
	public void conClose() {
		try {
			con.close();
			System.out.println("DB접속해제!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void insert(OMember omem) {
		String sql = "INSERT INTO OMB VALUES(?,?,TO_DATE(?))";
		
		// sql문에 ?가 있는지 확인
		// 있으면 pstmt 사용(꼭 사용!)
		// 없으면 stmt 사용 (pstmt 사용가능)
		
		try {
			
			pstmt = con.prepareStatement(sql);
			// sql문을 DB에 전달하기 위한 준비상태
			
			pstmt.setInt(1, omem.getStuno());
			pstmt.setString(2, omem.getOname());
			pstmt.setString(3, omem.getObirth());
			// INSERT INTO OMB VALUES(1501,'김경환',TO_DATE('20210722'));
			// ?안에 값을 넣어서 타이핑만 한 상태
			
			int result = pstmt.executeUpdate();
			// DB실행(Ctrl+Enter) => 결과 : n행이 삽입됐습니다. 에서 n을
			// result에 담음
			
			System.out.println("result값 : " + result);
			
			if(result > 0) {
				System.out.println("회원등록 성공!");
			} else {
				System.out.println("회원등록 실패!");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 4. 회원조회 메소드
	public void select() {
		String sql = "SELECT * FROM OMB";
		// sql문에 ?가 있는지 확인
		// 있으면 pstmt 사용(꼭 사용!)
		// 없으면 stmt 사용 (pstmt 사용가능)
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {	// rs.next() : 데이터의 갯수만큼 true값, 지나면 false
								// 레코드가 더이상 존재하지 않으면 false값 => 멈춘다.
				System.out.println();
				//System.out.println("학생번호 : " + rs.getInt(1)); 		// (n)에서 n은 컬럼 숫자
				//System.out.println("학생이름 : " + rs.getString(2));
				//System.out.println("생년월일 : " + rs.getString(3));
				
				System.out.println("학생번호 : " + rs.getInt("STUNO")); 		// 컬럼숫자 대신 컬럼이름 넣어도 됨
				System.out.println("학생이름 : " + rs.getString("ONAME"));
				System.out.println("생년월일 : " + rs.getString("OBIRTH"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	// 5-1, 6-1 학생정보체크
	public boolean checkStu(int stuno, String oname) {
		
		boolean result = false;
		
		String sql = "SELECT STUNO FROM OMB WHERE STUNO=? AND ONAME=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, stuno);
			pstmt.setString(2, oname);
			
			// INSERT, UPDATE, DELETE문의 짝꿍 int result
			// SELECT문의 짝꿍 ResultSet rs
			rs = pstmt.executeQuery();
			
			// while, if
			if(rs.next()) {		// SELECT문의 결과가 존재한다면
				result = true;
			} else {			// 존재하지 않는다면
				result = false;
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public void modify(OMember omem) {
		String sql = "UPDATE OMB SET ONAME=?, OBIRTH=? WHERE STUNO=?";
		try { 										// ? 순서대로 작성
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, omem.getOname());
			pstmt.setString(2, omem.getObirth());
			pstmt.setInt(3, omem.getStuno());
			
			int result = pstmt.executeUpdate();
			
			if(result > 0) {
				System.out.println("회원정보 수정 성공!");
			} else {
				System.out.println("회원정보 수정 실패!");
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
				System.out.println("회원정보 삭제 성공!");
			} else {
				System.out.println("회원정보 삭제 실패!");
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
