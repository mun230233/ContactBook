package example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.io.File;

import java.util.Scanner;

public class Contact_book {
	
	private ArrayList<Information> info;
	
	public Contact_book() {
		info = new ArrayList<>();
		
		try (BufferedReader fr = new BufferedReader(new FileReader("info.csv"))) {
			String line;
			
			while ((line = fr.readLine()) != null) {
				String[] fields = line.split(",");
				info.add(new Information(fields[0],fields[1], fields[2]));
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void save() {
		for (int n = 0; n < info.size(); ++n) {
			info.get(n).save(n);
		}
	}
	
	public void manage() {
		Scanner scanner = new Scanner(System.in);
		int choice = 0;
		
		while (choice != 4) {
			System.out.println("----------------------連絡帳---------------------");
			System.out.println("1.追加         2.削除         3.表示         4.終了");
			choice = scanner.nextInt();
			
			if (choice == 1) {
				add();
			} else if (choice == 2) {
				delete();
			} else if (choice == 3) {
				print_info();
			} else if (choice == 4) {
				save();
			}
		}
		
		System.out.println("終了");
	}
	
	public void add() {
		String[] data = new String[3];
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("名前を入力してください: ");
		data[0] = scanner.nextLine();
		System.out.print("電話番号を入力してください: ");
		data[1] = scanner.nextLine();
		System.out.print("メールを入力してください: ");
		data[2] = scanner.nextLine();
		
		info.add(new Information(data[0], data[1], data[2]));
		
		System.out.println();
		System.out.println("データが追加されました。");
	}
	
	public void delete() {
		Scanner scanner = new Scanner(System.in);
		int check = 0;
		
		while (check != 1 && check != 2) {
			System.out.print("全てのデータを消しますか？(YES:1,  NO:2) : ");
			check = scanner.nextInt();
			System.out.println();
		}
		
		if (check == 1) {
			info.clear();
			
			try (BufferedWriter fw = new BufferedWriter(new FileWriter("info.csv"))) {
				System.out.println("全てのデータが削除されました。");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void print_info() {
		
		System.out.println("\n|       名前       |       電話番号       |       メール       |\n");
		Information ex;
		
		for (int n = 0; n < info.size(); ++n) {
			ex = info.get(n);
			
			System.out.printf(" %-17s %-21s %-20s\n", ex.get_name(), ex.get_num(), ex.get_mail());
		}
		
		System.out.println();
	}
}
