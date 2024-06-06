package homework;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Homework06 {
	public static void main(String[] args) {
		FileInputStream fis = null;
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		
		try {
			fis = new FileInputStream("d:/D_Other/Tulips.jpg");
			fos = new FileOutputStream("d:/D_Other/복사본_Tulips.jpg");
			bos = new BufferedOutputStream(fos, 5);
			int data = 0;
			while ((data = fis.read()) != -1) {
				bos.write(data);
			}
			System.out.println("파일 복사 작업 완료...");
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				bos.flush();
				bos.close();
				fis.close();
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
