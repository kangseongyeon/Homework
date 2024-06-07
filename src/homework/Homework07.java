//package homework;
//
//import java.io.BufferedInputStream;
//import java.io.BufferedOutputStream;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.Map;
//import java.util.Scanner;
//import java.util.Set;
//
//public class Homework07 {
//	private Scanner scan = new Scanner(System.in);
//	private Map<Integer, HotelVo> hotelMap = new HashMap<Integer, HotelVo>();
//	
//	ObjectInputStream ois;
//	ObjectOutputStream oos;
//	FileInputStream fis;
//	FileOutputStream os;
//	BufferedInputStream bis;
//	BufferedOutputStream bos;
//	
//	private void main() {
//		Homework07 hw = new Homework07();
//		hw.hotelStart();
//	}
//	
//
//	public void displayMenu() {
//		System.out.println();
//		System.out.println("*******************************************");
//		System.out.println("어떤 업무를 하시겠습니까?");
//		System.out.println("1.체크인  2.체크아웃 3.객실상태 4.업무종료");
//		System.out.println("*******************************************");
//		System.out.print("메뉴선택 >> ");
//	}
//	
//	public void hotelStart() {
//		Object obj = null;
//		try {
//			ois = new ObjectInputStream(
//					new FileInputStream("d:/D_Other/homework07.txt"));
//			while ((obj = ois.readObject()) != null) { 
//				HotelVo mem = (HotelVo) obj;
//				// hotelMap.put(mem.getName(), new HotelVo(mem.getName());
//			}
//		} catch (Exception e) {
//			
//		}
//		System.out.println("**************************");
//		System.out.println("🏢 호텔 문을 열었습니다.");
//		System.out.println("**************************");
//		System.out.println();
//		
//		while (true) {
//			displayMenu();
//
//			int menuNum = scan.nextInt();
//
//			switch (menuNum) {
//			case 1:
//				checkIn();
//				break;
//			case 2:
//				checkOut();
//				break;
//			case 3:
//				condition();
//				break;
//			case 4:
//				termination();
//				return;
//			default :
//				System.out.println("😭 잘못 입력했습니다. 다시 입력해주세요.");
//			}
//		}
//	}
//
//	private void termination() {
//		System.out.println("*******************************************");
//		System.out.println("호텔 문을 닫았습니다.");
//		System.out.println("*******************************************");
//		System.out.println();
//	}
//
//	private void condition() {
//		System.out.println();
//
//		Set<Integer> keySet = hotelMap.keySet();
//		
//		if (keySet.size() == 0) {
//			System.out.println("❌ 등록된 정보가 존재하지 않습니다.");
//		} else {
//			Iterator<Integer> it = keySet.iterator();
//			
//			while (it.hasNext()) {
//				Integer roomNum = it.next();
//				HotelVo h = hotelMap.get(roomNum);
//				System.out.println("🏢 방 번호 : " + roomNum + ", 투숙객 : " + h.getName());
//			}
//		}
//		
//    }
//	
//	private void checkOut() {
//		System.out.println();
//		System.out.println("어느 방을 체크아웃 하시겠습니까?");
//		System.out.print("🏢 방 번호 입력 => ");
//		Integer roomNum = scan.nextInt();
//		
//		if (hotelMap.remove(roomNum) == null) {
//			System.out.println(roomNum + "방에는 체크인한 사람이 없습니다.");
//		} else {
//			System.out.println("🔓 체크아웃 되었습니다.");
//		}
//	}
//
//	private void checkIn() {
//		System.out.println();
//		System.out.println("어느 방에 체크인 하시겠습니까?");
//		System.out.print("🏢 방 번호 입력 => ");
//		Integer roomNum = scan.nextInt();
//		
//		System.out.println();
//		System.out.println("누구를 체크인 하시겠습니까?");
//		System.out.print("👨‍👩‍👧‍👦  이름 입력 => ");
//		String name = scan.next();
//		
//		if (hotelMap.get(roomNum) != null) {
//			System.out.println(roomNum + "방에는 이미 사람이 있습니다.");
//			return;
//		}
//		
//		hotelMap.put(roomNum, new HotelVo(name));
//		System.out.println("🔒 체크인 되었습니다.");
//	}
//	
//	
//	
//	class HotelVo {
//		private String name;
//		public HotelVo(String name) {
//			super();
//			this.name = name;
//		}
//		public String getName() {
//			return name;
//		}
//		public void setName(String name) {
//			this.name = name;
//		}
//	}
//}
