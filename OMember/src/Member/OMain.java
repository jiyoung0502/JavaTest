package Member;

import java.util.Scanner;

public class OMain {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		OSQL osql = new OSQL(); 	// OSQL�� ����ϱ� ���� ����
		OMember omem = new OMember();
		
		boolean run = true;
		int menu = 0;
		
		do {
			System.out.println("===============================");
			System.out.println("1.DB����     2.DB��������     3.ȸ������");
			System.out.println("4.ȸ����ȸ   5.ȸ����������   6.ȸ������");
			System.out.println("7.����");
			System.out.println("===============================");
			System.out.print("�޴� ���� >> ");
			
			menu = sc.nextInt();
			
			switch(menu) {
			case 1:		// DB����
				osql.connect();
				break;
			case 2:		// DB��������
				osql.conClose();
				break;
			case 3:		// ȸ������
				System.out.println("ȸ�������� �Է����ּ���!");	
				
				System.out.print("�л���ȣ >> ");
				int stuno = sc.nextInt();
				
				System.out.print("�л��̸� >> ");
				String oname = sc.next();
				
				System.out.print("������� >> ");
				String obirth = sc.next();
				
				omem.setStuno(stuno);
				omem.setOname(oname);
				omem.setObirth(obirth);
				
				osql.insert(omem); // insert�ȿ� omem�� ����
				
				break;
			case 4:		// ȸ����ȸ
				osql.select(); // select�ȿ� �ƹ� ������ ����
				break;
			case 5:		// ȸ����������
				// (1) �л���ȣ, �̸� üũ
				System.out.println("�л� ���� üũ");
				
				System.out.print("�л���ȣ >> ");
				stuno = sc.nextInt();
				
				System.out.print("�л��̸� >> ");
				oname = sc.next();
				
				boolean check = osql.checkStu(stuno, oname);
				
				
				// (2) ȸ����������
				if(check) {
					System.out.println(stuno+"�� �л��� ������ �����մϴ�.");
					System.out.print("�л��̸� >> ");
					oname = sc.next();
					
					System.out.print("�������(ex. 20210723) >> ");
					obirth = sc.next();
					
					omem.setStuno(stuno);
					omem.setOname(oname);
					omem.setObirth(obirth);
					
					osql.modify(omem);
					
				} else {
					System.out.println("�л������� �������� �ʽ��ϴ�.");
				}
				
				
				break;
			case 6:		// ȸ������
				// (1) �л���ȣ, �̸� üũ
				
				// ȸ������ sql�� : DELETE OMB WHERE STUNO=?
				System.out.println("�л���ȣ �̸� üũ");
				
				System.out.print("�л���ȣ >> ");
				stuno = sc.nextInt();
				
				System.out.print("�л��̸� >> ");
				oname = sc.next();
				
				check = osql.checkStu(stuno,oname);
				
				// (2) ȸ����������
				if(check) {	// �л���ȣ �̸� ��ġo
					osql.delete(stuno);
				} else {		//��ġ���� ���� ��
					System.out.println("�л���ȣ�� �̸��� ��ġ���� �ʽ��ϴ�.");
				}
				
				
				break;
			case 7:
				System.out.println("�ý����� �����մϴ�.");
				run = false;
				break;
			default :
				System.out.println("�߸� �Է��߽��ϴ�. �ٽ� �Է����ּ���!");
				break;
			}
		} while(run);
		
	}

}
