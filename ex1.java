import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import javax.script.AbstractScriptEngine;

public class ex1 {

	public static void main(String[] args) {
		
//		������Ÿ��[] ������ = new ������Ÿ��[] {���1, ���2, ....}
//		
//		������Ÿ��[] ������ = {���1, ���2, ....}
		// ���� ����
//		String[] stringArray;
//		// �迭�� ���� �Ҵ�
//		stringArray = new String[10];
//		
//		System.out.println(Arrays.toString(stringArray));
//		
//		// ���� ���� �� �迭 ����
//		int[] intArray = new int[] {1, 2, 3};
//		
//		// ���� ���� �� �迭 ����
//		double[] doubleArray = {3.159, 1.753, 7.456, 8.426};
//		
//		System.out.println(Arrays.toString(intArray));
//		System.out.println(Arrays.toString(doubleArray));
//		
		
		
		
		
//		String[] bookTitle = {"�ȳ�", "����", "����"};
//		
//		String[] bookPublisher = {"����", "�ϴ�", "����"};
//		
//		int[] bookPrice = {3000, 4000, 5000};
//		
//		boolean[] bookLone = {false, false, false};
//		
//		String[] personName = {"ȫ�浿", "������", "�д�"};
//		
//		String[] personTelnum = {"010-1111-2222", "010-2222-3333" };
//		
//		String[] personLoanBookList = new String[3];
//
//		
//		
//		for (int i = 0; i < bookTitle.length; i++) {
//			System.out.println("���� : " + bookTitle[i]);
//			System.out.println("���ǻ� : " + bookPublisher[i]);
//			System.out.println("���� : " + bookPrice[i]);
//			System.out.println("�������⿩�� : " + bookLone[i]);
//		}
//		
//		for (int i = 0; i < personName.length; i++) {
//			System.out.println("�̸� : " + personName[i]);
//			System.out.println("����ó : " + personTelnum[i]);
//			System.out.println("���� ���� : " + personLoanBookList[i]);
//		}
//		
//		for (int i = 0; i < bookTitle.length; i++) {
//			System.out.println(bookTitle[i]);
//			
//		}
		
//		System.out.println("�� ���� ���ڿ��� �����Ͻðڽ��ϱ�? ");
//		
//		Scanner scanf = new Scanner(System.in);
//		int arrayLength = scanf.nextInt();
//		
//		scanf.close();
//		
//		String[] stringArray = new String[arrayLength];
////		for (int i = 0; i < stringArray.length; i++) {
////			stringArray[i] = i + "";
////			// stringArray[i] = String.valueOf(i);
////		}
//		
//		for (int i = 0; i < stringArray.length; i++) {
////			System.out.println(stringArray[i] + ", ");
//			stringArray[i] = i + "";
//			
//			if (arrayLength - 1 != i) {
//				System.out.println(stringArray[i] + ", ");
//			} else {
//				System.out.println(stringArray[i]);
//			}
//			
//		}
		
		// foreach
		
//		int[] intArray = new int[] {1, 2, 3};
//		
//		intArray[1] = 4;
//		intArray[2] = 2;
//		
//		for(int el : intArray) {    
//		System.out.println(el);
//		}
//		
//		
//		
//		double[] doubleArray = {3.456, 2.456, 1.453, 8.426};
//		for(double el : doubleArray) {
//			
//			double tmp = doubleArray[1];
//			doubleArray[1] = doubleArray[3];
//			doubleArray[3] = tmp;
//			
//			System.out.println(el);
//		}
		
		
		
		
		
		
		
		
//		String[] koreanFoodMenuList = new String[5];
//		
//		koreanFoodMenuList[0] = "���";
//		koreanFoodMenuList[1] = "��ġ�";
//		koreanFoodMenuList[2] = "���κ�";
//		koreanFoodMenuList[3] = "��ġ";
//		koreanFoodMenuList[4] = "����";
//		
//		for (String menu : koreanFoodMenuList) {
//			System.out.println(menu);
//		}
		
		
		
		
//		
//		// ������ �迭
//		// ������ Ÿ��[][] ������;
//		String[][] bookInfo = new String[3][5];
//		
//		String[][] personInfo = new String[3][3];
//		
		
		
		
		
		// Random Ŭ������ �����ϴ� nextInt, nextDouble ���� �޼��带 ���
//		Random random = new Random();
//		
//		System.out.println(Integer.MIN_VALUE + " ~ " + Integer.MAX_VALUE);
//		System.out.println(random.nextInt()); 
//		System.out.println(random.nextInt(10)); // 0 ~ 9
//		System.out.println(random.nextBoolean()); // true false
//		System.out.println(Long.MIN_VALUE + " ~ " + Long.MAX_VALUE);
//		System.out.println(random.nextLong());
//		System.out.println(random.nextFloat());
//		System.out.println(random.nextDouble());
		
		
		
		
		
//		Scanner scanf = new Scanner(System.in);
//		
//		int[] userInput = new int[5];
//		
//		for (int i = 0; i < userInput.length; i++) {
//			System.out.println("0 ~ 100 ������ ���� �Է��ϼ��� : " +( i + 1) + " / 5");
//			userInput[i] = scanf.nextInt();
//			
//			if (userInput[i] < 0 || userInput[i] > 100) {
//				System.out.println("0 ~ 100 ������ ���� �����մϴ�.");
//				i--;
//				
//			}
//			
//			// System.out.println(Arrays.toString(userInput));
//			
//			// �ִ밪 ���ذ� 101
//			int max = -1;
//			// �ּҰ� ���ذ� -1
//			int min = 101;
//			
//			for(int element : userInput) {
//				if (element < min) min = element;
//				if (element > max) max = element;
//			}
//			
//			System.out.println("min = " + min);
//			System.out.println("max = " + max);
//		}
//		
//		scanf.close();
		
		
		
		
		
		
//		Scanner scanf = new Scanner(System.in);
//		
//		double[] userInput = new double[5];
//		double min = 11;
//		double max = -1;
//		
//		for (int i = 0; i < 5; i++) {
//			System.out.println("0 ~ 10 ������ ��");
//			userInput[i] = scanf.nextDouble();
//			
//			if(userInput[i] < 0 || userInput[i] > 100) {
//				System.out.println("0 ~ 100������ ���� �����մϴ�.");
//			}
//			
//			
//			
//			
//			if(userInput[i] < min) min = userInput[i];
//			
//			if(userInput[i] > max) max = userInput[i];
//		} // end for 
//		
//		
//		int amount = 0;
//		double sum = 0;
//		
//		for (int i = 0; i < 5; i++) {
//			if(userInput[i] != min && userInput[i] != max) {
//			
//			sum += userInput[i];
//			
//			amount++;
//		}
//		
//	}
//		
//		System.out.println("�������� = " + (sum / amount));
//		
//		scanf.close();
//		
		
		
		
		Random random = new Random();
		
		int[] lottoNumberArray = new int[6];
		
		for (int i = 0; i < 6; i++) {
			int lottoNumber = random.nextInt(45) + 1;
			
			if(lottoNumber == lottoNumberArray[i]) {
				i--;
			} else {
				lottoNumberArray[i] = lottoNumber;
			}
			
			for (int j = 0; j < i; j++) {
				if (lottoNumberArray[i] == lottoNumberArray[j])
					i--;
			}
		}// end for
		
		System.out.println(Arrays.toString(lottoNumberArray));
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
