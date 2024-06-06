package homework;

public class Homework04 {
	public enum Planet {
		수성(2439), 금성(6052), 지구(6371), 화성(3390), 목성(69911), 토성(58332),천왕성(25362), 해왕성(24622);
		
		private double radius;
		
		private Planet (double radius) {
			this.radius = radius;
		}
		
		public Double getPlanet() {
			return radius;
		}
	}
	
	public static void main(String[] args) {
		System.out.println("문제) 태양계 행성을 나타내는 enum Plant을 이용하여 구하시오");
		System.out.println("(단, enum 객체 생성 시 반지름을 이용하도록 정의하시오.)");
		System.out.println();
		System.out.println("예) 행성의 반지름 (KM)");
		
		Planet[] planetArr = Planet.values();
		
		for (Planet p : planetArr) {
			System.out.println(p.name()+"의 면적 : " + p.getPlanet() * p.getPlanet() * 4 * Math.PI + " Km^2");
		}
	}
}
