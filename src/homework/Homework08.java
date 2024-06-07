package homework;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Scanner;

import kr.or.ddit.util.JDBCUtil2;

/*
위의 테이블을 작성하고 게시판을 관리하는
다음 기능들을 구현하시오.

기능 구현하기 ==> 전체 목록 출력, 새글작성, 수정, 삭제, 검색 
 
------------------------------------------------------------

게시판 테이블 구조 및 시퀀스

create table jdbc_board(
    board_no number not null,  -- 번호(자동증가)
    board_title varchar2(100) not null, -- 제목
    board_writer varchar2(50) not null, -- 작성자
    board_date date not null,   -- 작성날짜
    board_content clob,     -- 내용
    constraint pk_jdbc_board primary key (board_no)
);
create sequence board_seq
    start with 1   -- 시작번호
    increment by 1; -- 증가값
		
----------------------------------------------------------

// 시퀀스의 다음 값 구하기
//  시퀀스이름.nextVal
*/

public class Homework08 {
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		Homework08 hw = new Homework08();
		hw.start();
	}

	public void displayMenu() {
		System.out.println();
		System.out.println("----------------------------------------------");
		System.out.println(" ======= 메뉴 선택 ======= ");
		System.out.println(" 0. 작업 끝");
		System.out.println(" 1. 글 작성");
		System.out.println(" 2. 글 수정");
		System.out.println(" 3. 글 삭제");
		System.out.println(" 4. 글 검색");
		System.out.println(" 5. 전체 목록 출력");
		System.out.println("----------------------------------------------");
		System.out.print("메뉴 선택 >> ");
	}

	public void start() {
		int choice;
		do {
			displayMenu();
			choice = scan.nextInt();
			switch (choice) {
			case 0:
				System.out.println("작업을 마칩니다.");
				break;
			case 1:
				insertBoard();
				break;
			case 2:
				updateBoard();
				break;
			case 3:
				deleteBoard();
				break;
			case 4:
				searchBoard();
				break;
			case 5:
				printbBard();
				break;
			default:
				break;
			}
		} while (choice != 0);
	}

	private void printbBard() {
		System.out.println();
		System.out.println("----------------------------------------------");
		System.out.println("번호 \t 제목 \t 작성자 \t 작성일 \t\t 내용");
		System.out.println("----------------------------------------------");
		
		try {
			conn = JDBCUtil2.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(" SELECT * FROM JDBC_BOARD ");
			
			while (rs.next()) {
				int boardNo = rs.getInt("BOARD_NO");
				String boardTitle = rs.getString("BOARD_TITLE");
				String boardWriter = rs.getString("BOARD_WRITER");
				String boardContent = rs.getString("BOARD_CONTENT");
				
				LocalDate boardDt = rs.getTimestamp("BOARD_DATE").toLocalDateTime().toLocalDate();
				System.out.println(boardNo + "\t" + boardTitle + "\t" + boardWriter + "\t" + boardDt + "\t" + boardContent);
			}
			System.out.println("----------------------------------------------");
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			JDBCUtil2.close(conn, stmt, pstmt, rs);
		}
	}

	private void searchBoard() {
		System.out.println();
		System.out.println("검색할 게시글 번호를 입력하세요.");
		System.out.print("게시글 번호 >> ");
		int boardNo = scan.nextInt();
		
		try {
			conn = JDBCUtil2.getConnection();
			
			String sql = " SELECT * FROM JDBC_BOARD WHERE BOARD_NO = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			rs = pstmt.executeQuery();
			
			System.out.println();
			
			System.out.println("----------------------------------------------");
			System.out.println("번호 \t 제목 \t 작성자 \t 작성일 \t\t 내용");
			System.out.println("----------------------------------------------");
			
			rs.next();
			boardNo = rs.getInt("BOARD_NO");
			String boardTitle = rs.getString("BOARD_TITLE");
			String boardWriter = rs.getString("BOARD_WRITER");
			String boardContent = rs.getString("BOARD_CONTENT");
			
			LocalDate boardDt = rs.getTimestamp("BOARD_DATE").toLocalDateTime().toLocalDate();
			System.out.println(boardNo + "\t" + boardTitle + "\t" + boardWriter + "\t" + boardDt + "\t" + boardContent);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			JDBCUtil2.close(conn, stmt, pstmt, rs);
		}
	}

	private void deleteBoard() {
		System.out.println();
		System.out.println("삭제할 게시글 번호를 입력하세요.");
		System.out.print("게시글 번호 >> ");
		int boardNo = scan.nextInt();
		
		try {
			conn = JDBCUtil2.getConnection();
			
			String sql = " DELETE\r\n " + 
						 " FROM JDBC_BOARD\r\n " + 
						 " WHERE BOARD_NO = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			int cnt = pstmt.executeUpdate();
			
			if (cnt > 0) {
				System.out.println("게시글 번호 " + boardNo + " 삭제 성공!!");
			} else {
				System.out.println("게시글 번호 " + boardNo + " 삭제 실패!!");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			JDBCUtil2.close(conn, stmt, pstmt, rs);
			printbBard();
		}
	}

	private void updateBoard() {
		boolean isExist = false;
		int boardNo = 0;
		
		do {
			System.out.println();
			System.out.println("수정할 게시글 번호를 입력하세요.");
			System.out.print("게시글 번호 >> ");
			boardNo = scan.nextInt();
			
			isExist = checkBoard(boardNo);
			
			if (!isExist) {
				System.out.println("게시글 번호가 " + boardNo + "인 게시물은 존재하지 않습니다.");
				System.out.println("다시 입력해주세요.");
			}
		} while (!isExist);
		
		System.out.println();
		
		System.out.print("게시글 제목 >> ");
		String boardTitle = scan.next();
		
		System.out.print("작성자 >> ");
		String boardWriter = scan.next();
		scan.nextLine();

		System.out.print("내용 >> ");
		String boardContent = scan.nextLine();
		
		try {
			conn = JDBCUtil2.getConnection();
			String sql = " UPDATE JDBC_BOARD\r\n" + 
						 " SET BOARD_TITLE = ?, BOARD_WRITER = ?, BOARD_CONTENT = ?\r\n " + 
						 " WHERE BOARD_NO = ? ";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, boardTitle);
			pstmt.setString(2, boardWriter);
			pstmt.setString(3, boardContent);
			pstmt.setInt(4, boardNo);
			
			int cnt = pstmt.executeUpdate();
			
			if (cnt > 0) {
				System.out.println("게시글 " + boardNo + "번 수정 성공!!");
			} else {
				System.out.println("게시글 " + boardNo + "번 수정 실패!!");
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			JDBCUtil2.close(conn, stmt, pstmt, rs);
			printbBard();
		}
	}

	private void insertBoard() {
		System.out.println();
		System.out.println("게시글을 등록합니다.");
		System.out.print("게시글 제목 >> ");
		String boardTitle = scan.next();

		System.out.print("작성자 >> ");
		String boardWriter = scan.next();
		scan.nextLine();

		System.out.print("내용 >> ");
		String boardContent = scan.nextLine();

		try {
			conn = JDBCUtil2.getConnection();

			String sql = " INSERT INTO JDBC_BOARD (BOARD_NO, BOARD_TITLE, BOARD_WRITER, BOARD_DATE, BOARD_CONTENT)\r\n " + 
						 " VALUES (BOARD_SEQ.NEXTVAL, ? , ? , SYSDATE, ? ) ";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, boardTitle);
			pstmt.setString(2, boardWriter);
			pstmt.setString(3, boardContent);

			int cnt = pstmt.executeUpdate();

			if (cnt > 0) {
				System.out.println("게시글  " + boardTitle + " 등록이 완료되었습니다.");
			} else {
				System.out.println("게시글 " + boardTitle + " 등록을 실패했습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil2.close(conn, stmt, pstmt, rs);
			printbBard();
		}

	}

	private boolean checkBoard(int boardNo) {
		boolean isExist = false;
		try {
			conn = JDBCUtil2.getConnection();

			String sql = " SELECT COUNT(*) AS CNT\r\n " + 
						 " FROM JDBC_BOARD\r\n " + 
						 " WHERE BOARD_NO = ? ";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, boardNo);
			rs = pstmt.executeQuery();
			int cnt = 0;
			while (rs.next()) {
				cnt = rs.getInt(1);
				if (cnt > 0) {
					isExist = true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil2.close(conn, stmt, pstmt, rs);
		}
		return isExist;
	}
}
