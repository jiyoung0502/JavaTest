package Member;

import java.util.Scanner;

public class OMain {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		OSQL osql = new OSQL(); 	// OSQL을 사용하기 위해 만듬
		OMember omem = new OMember();
		
		boolean run = true;
		int menu = 0;
		
		do {
			System.out.println("===============================");
			System.out.println("1.DB접속     2.DB접속해제     3.회원가입");
			System.out.println("4.회원조회   5.회원정보수정   6.회원삭제");
			System.out.println("7.종료");
			System.out.println("===============================");
			System.out.print("메뉴 선택 >> ");
			
			menu = sc.nextInt();
			
			switch(menu) {
			case 1:		// DB접속
				osql.connect();
				break;
			case 2:		// DB접속해제
				osql.conClose();
				break;
			case 3:		// 회원가입
				System.out.println("회원정보를 입력해주세요!");	
				
				System.out.print("학생번호 >> ");
				int stuno = sc.nextInt();
				
				System.out.print("학생이름 >> ");
				String oname = sc.next();
				
				System.out.print("생년월일 >> ");
				String obirth = sc.next();
				
				omem.setStuno(stuno);
				omem.setOname(oname);
				omem.setObirth(obirth);
				
				osql.insert(omem); // insert안에 omem의 정보
				
				break;
			case 4:		// 회원조회
				osql.select(); // select안에 아무 정보도 없음
				break;
			case 5:		// 회원정보수정
				// (1) 학생번호, 이름 체크
				System.out.println("학생 정보 체크");
				
				System.out.print("학생번호 >> ");
				stuno = sc.nextInt();
				
				System.out.print("학생이름 >> ");
				oname = sc.next();
				
				boolean check = osql.checkStu(stuno, oname);
				
				
				// (2) 회원정보수정
				if(check) {
					System.out.println(stuno+"번 학생의 정보를 수정합니다.");
					System.out.print("학생이름 >> ");
					oname = sc.next();
					
					System.out.print("생년월일(ex. 20210723) >> ");
					obirth = sc.next();
					
					omem.setStuno(stuno);
					omem.setOname(oname);
					omem.setObirth(obirth);
					
					osql.modify(omem);
					
				} else {
					System.out.println("학생정보가 존재하지 않습니다.");
				}
				
				
				break;
			case 6:		// 회원삭제
				// (1) 학생번호, 이름 체크
				
				// 회원삭제 sql문 : DELETE OMB WHERE STUNO=?
				System.out.println("학생번호 이름 체크");
				
				System.out.print("학생번호 >> ");
				stuno = sc.nextInt();
				
				System.out.print("학생이름 >> ");
				oname = sc.next();
				
				check = osql.checkStu(stuno,oname);
				
				// (2) 회원정보삭제
				if(check) {	// 학생번호 이름 일치o
					osql.delete(stuno);
				} else {		//일치하지 않을 때
					System.out.println("학생번호와 이름이 일치하지 않습니다.");
				}
				
				
				break;
			case 7:
				System.out.println("시스템을 종료합니다.");
				run = false;
				break;
			default :
				System.out.println("잘못 입력했습니다. 다시 입력해주세요!");
				break;
			}
		} while(run);
		
	}

}
