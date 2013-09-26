package ch.kausoft.tool;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.LineNumberInputStream;

public class CsvReader {



	public static void AsciiRead() {

		String name = "C:\\Arbeiten\\Adressen\\google\\google alle 100414-1555.csv";
		File f = new File(name);
		String zeile;
		FileInputStream fis;
		InputStreamReader isr;
		BufferedReader br;

		try {
			fis = new FileInputStream(f);
			isr = new InputStreamReader(fis);
			br = new BufferedReader(isr);

			zeile = br.readLine();
			while (zeile != null) {
				System.out.println(zeile);
				zeile = br.readLine();

			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void UnicodeRead( ) {

		String name = "C:\\eclipse2013\\Workspaces\\Kostenmiete\\Kostenmiete\\src\\ch\\kausoft\\wbg\\daten\\InputDaten.csv";
		File f = new File(name);
		String zeile;
		FileInputStream fis;
		InputStreamReader isr;
		BufferedReader br;

		try {
			fis = new FileInputStream(f);
			isr = new InputStreamReader(fis);
			br = new BufferedReader(isr);

			zeile = br.readLine();
			while (zeile != null) {
				System.out.println(zeile);
				zeile = br.readLine();

			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	

}
