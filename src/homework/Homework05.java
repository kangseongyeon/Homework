package homework;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Homework05 {
	
	static int CURR_RANK=1;
	
	public static void main(String[] args) {
		List<Horse> horseList = new ArrayList<Horse>();
		horseList.add(new Horse("1번말 🐎"));
		horseList.add(new Horse("2번말 🐎"));
		horseList.add(new Horse("3번말 🐎"));
		horseList.add(new Horse("4번말 🐎"));
		horseList.add(new Horse("5번말 🐎"));
		horseList.add(new Horse("6번말 🐎"));
		horseList.add(new Horse("7번말 🐎"));
		horseList.add(new Horse("8번말 🐎"));
		horseList.add(new Horse("9번말 🐎"));
		horseList.add(new Horse("10번말 🐎"));
		
		// 말 달리는 메서드
		for (Horse h : horseList) {
			h.start();
		}
		
		while (Homework05.CURR_RANK<=10) {
			for (Horse h : horseList) {
				System.out.println(h.getStr());
			}
			System.out.println();
			System.out.println();
			System.out.println();
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		for (Horse h : horseList) {
			try {
				h.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		Collections.sort(horseList);

		System.out.println("경기 끝...");
		System.out.println("-----------------------");
		System.out.println("경기 결과");
		System.out.println();
		System.out.println("======================");
		System.out.println("순위\t:\t이름");
		System.out.println("-----------------------");
		
		for (Horse h : horseList) {
			System.out.println(h.getRank()+"\t:\t"+h.getName());
			System.out.println();
		}
	}
}

class Horse extends Thread implements Comparable<Horse>{
	private String name;
	private int rank;
	private String str;
	
	public Horse(String name) {
		super(name);
		this.name = name;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}
	

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	@Override
	public void run() {
		for (int i = 1; i <=50; i++) {
			String nameString = name+"\t";
			for (int j = 0; j < i; j++) {
				nameString+="~";
			}
			nameString+=">";
			
			for (int j = i+1; j <=50; j++) {
				nameString+="-";
			}
			setStr(nameString);
			
			try {
				Thread.sleep((int)(Math.random() * 500 + 200));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		setRank(Homework05.CURR_RANK);
		Homework05.CURR_RANK++;
	}
	
	@Override
	public int compareTo(Horse o) {

		return new Integer(this.rank).compareTo(o.getRank());
	}
	
}