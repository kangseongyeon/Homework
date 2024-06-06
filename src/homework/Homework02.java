package homework;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Homework02 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("==========================");
			System.out.println("Lotto 프로그램");
			System.out.println("--------------------------");
			System.out.println("1. Lotto 구입");
			System.out.println("2. 프로그램 종료");
			System.out.println("==========================");

			System.out.print("메뉴 선택 : ");
			int sel = sc.nextInt();

			if (sel == 1) {
				System.out.println();
				System.out.println("🎰 Lotto 구입 시작");
				System.out.println();
				System.out.println("(1000원에 로또 번호 하나입니다.)");
				System.out.print("금액 입력 : ");
				int money = sc.nextInt();
				System.out.println();

				int lottos = money / 1000;
				int change = money % 1000;

				System.out.println("행운의 로또 번호는 아래와 같습니다.");
				for (int i = 1; i <= lottos; i++) {
					Set<Integer> intRnd = new HashSet<Integer>();
					while (intRnd.size() < 6) {
						int num = (int) (Math.random() * 45 + 1);
						intRnd.add(num);
					}
					
					List<Integer> intRndList = new ArrayList<Integer>(intRnd);
					System.out.print("로또번호" + i +"\t");
					for (Integer num : intRndList) {
						System.out.print(num + " ");
					}
					System.out.println();
				}

				System.out.println("💴 받은 금액은 " + money + "원이고 🪙 거스름돈은 " + change + "원 입니다.");
				System.out.println();
			} else if (sel == 2) {
				System.out.println("😁감사합니다.");
				break;
			} else {
				System.out.println("✏️ 1 또는 2를 입력해주세요");
				System.out.println();
			}
		}
	}
}
