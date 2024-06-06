package homework;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Homework01 {
	public static void main(String[] args) {
		List<Student> stuList = new ArrayList<Student>();
		
		stuList.add(new Student("20210772", "강성연", 90, 80, 100));
		stuList.add(new Student("20210771", "금서윤", 90, 83, 68));
		stuList.add(new Student("20210775", "이채민", 80, 90, 100));
		stuList.add(new Student("20210773", "강민서", 90, 64, 50));
		stuList.add(new Student("20210774", "조미주", 70, 65, 80));
		
		System.out.println("<정렬 전>");
		for (Student stu : stuList) {
			System.out.println(stu);
		}
		System.out.println("========================================");
		
		Collections.sort(stuList);
		
		System.out.println("<학번의 오름차순으로 정렬>");
		int i = 1;
		for (Student stu : stuList) {
		    stu.setRank(i);
		    i++;
		    System.out.println(stu);
		}
		System.out.println("========================================");

		
		Collections.sort(stuList, new SortScoreDesc());
		System.out.println("<총점의 역순 정렬>");
		int j = 1;
		for (Student stu : stuList) {
			stu.setRank(j);
			j++;
			System.out.println(stu);
		}
		System.out.println("========================================");
	}
}

class SortScoreDesc implements Comparator <Student> {

	@Override
	public int compare(Student stu1, Student stu2) {
		if (stu1.getSum() > stu2.getSum()) {
			return -1;
		} else if (stu1.getSum() < stu2.getSum()) {
			return 1;
		} else {
			if (stu1.getStuNum().compareTo(stu2.getStuNum()) == -1) {
				return 1;
			} else {
				return -1;
			}
		}
	}
	
}

class Student implements Comparable <Student> {
	private String stuNum;
	private String name;
	private int kor;
	private int eng;
	private int math;
	private int sum;
	private int rank;
	
	
	public Student(String stuNum, String name, int kor, int eng, int math) {
		super();
		this.stuNum = stuNum;
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.math = math;
		this.sum = kor + eng + math;
	}

	public String getStuNum() {
		return stuNum;
	}

	public void setStuNum(String stuNum) {
		this.stuNum = stuNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getKor() {
		return kor;
	}

	public void setKor(int kor) {
		this.kor = kor;
	}

	public int getEng() {
		return eng;
	}

	public void setEng(int eng) {
		this.eng = eng;
	}

	public int getMath() {
		return math;
	}

	public void setMath(int math) {
		this.math = math;
	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}
	


	@Override
	public String toString() {
		return "Student [stuNum=" + stuNum + ", name=" + name + ", kor=" + kor + ", eng=" + eng + ", math=" + math
				+ ", sum=" + sum + ", rank=" + rank + "]";
	}

	@Override
	public int compareTo(Student stu) {
		return this.getStuNum().compareTo(stu.getStuNum());
	}
}