package org.edu.member.service;

import lombok.SneakyThrows;
import org.edu.member.dao.MemberDaoImpl;
import org.edu.member.vo.Member;

import java.sql.SQLOutput;
import java.util.Scanner;

public class MemberService {
    private Scanner sc = new Scanner(System.in);
    private MemberDaoImpl dao = new MemberDaoImpl();

    public void displayMenu() {

        int menu = 0; // 메뉴 선택용 변수

        do {
            try {
                System.out.println("[메인 메뉴]");
                System.out.println("1. 회원 등록");
                System.out.println("2. 회원 목록 조회");
                System.out.println("3. 회원 정보 조회");
                System.out.println("4. 회원 수정");
                System.out.println("5. 회원 삭제");
                System.out.println("0. 종료");
                System.out.print("메뉴 선택 >> ");

                menu = sc.nextInt();
                sc.nextLine(); // 입력 버퍼 개행문자 제거
                System.out.println(); // 줄바꿈

                switch (menu) {
                    case 1:
                        create();
                        break;
                    case 2:
//                        getList();
                        break;
                    case 3:
//                        get();
                        break;
                    case 4:
                        update();
                        break;
                    case 5:
//                        delete();
                        break;

                    case 0:
                        System.out.println("[프로그램 종료]");
                        break;
                    default:
                        System.out.println("잘못 입력하셨습니다. 메뉴를 다시 선택해주세요.");
                }

//            }catch (SQLException e){
//                System.out.println("DB 작업중 에러 발생");
//                e.printStackTrace();

            } catch (Exception e) {
                sc.nextLine(); // 잘못된 입력 제거
                e.printStackTrace();
            }
        }while(menu != 0);
    }
    // 회원 등록
    @SneakyThrows
    private void create() {
        System.out.println("=== 회원 등록 ===");

        // 아이디, 비밀번호, 이름, 권한을 입력받아 각각 변수에 저장
        System.out.print("아이디 :");
        String memberId = sc.nextLine();

        System.out.print("비밀번호 : ");
        String memberPw = sc.nextLine();

        System.out.print("이름 : ");
        String memberName = sc.nextLine();

        System.out.print("권한 : ");
        String memberRole = sc.nextLine();

//        int result = dao.create(memberId, memberPw, memberName, memberRole);

        // Memeber 객체 생성 후 전달
        Member member = new Member();
        member.setMemberId(memberId);
        member.setMemberPw(memberPw);
        member.setMemberName(memberName);
        member.setMemberRole(memberRole);

        int result = dao.create(member);

        // 회원 등록 성공 시 : "000님의 가입을 환영합니다."
        //         실패 시 : "회원 등록 실패"

        if(result==1) {
            System.out.print(memberName + "님의 가입을 환영합니다.");
        }else {
            System.out.println("회원 등록 실패");
        }
    }

    // 회원 정보 수정
    private void update() {
        System.out.println("=== 회원 정보 수정 ===");

        // 회원 번호를 입력 받아 일치하는 회원의 이름, 권한 수정
        System.out.print("수정할 회원 번호 : ");
        int no = sc.nextInt();

        System.out.println("수정할 이름 : ");
        String name = sc.next();

        System.out.println("수정할 권한 : ");
        String role = sc.next();

        // 성공 시 : "회원 정보 수정 성공"
        // 실패 시 : "회원 정보 수정 실패"


    }

}
