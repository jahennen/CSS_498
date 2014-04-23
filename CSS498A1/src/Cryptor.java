import java.security.*;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import javax.crypto.*;


public class Cryptor {
	
	private static Scanner inScan;
	private Key defaultAESKey;
	private Key defaultTDESKey;
	private Key defaultRSA;
	
	public void encrypt() {
		
	}
	
	public void decrypt() {
		
	}
	
	public void genKey() {
		
	}
	
	public static String nextLine() {
		String retval = inScan.nextLine();
		if(inScan.equals("q") || inScan.equals("quit"))
			System.exit(0);
		return retval;
	}
	
	public static void main(String[] args) {
		Map<String,Cipher> ciphers = new TreeMap<String,Cipher>();		
		while(true) {
			System.out.println("Choose an option (q or quit to exit):");
			System.out.println("1. - Encrypt");
			System.out.println("2. - Decrypt");
			System.out.println("3. - Generate Key");
			inScan = new Scanner(System.in);
			String option = nextLine();
			if (option.length()!=1 && !"123".contains(option)) {
				System.out.println("Invalid input\n");
				continue;
			}
			int o = Integer.parseInt(option);
			switch (o) {
			case 1:
				encrypt();
			case 2:
			case 3:
			}
				
		}
	}
	
}
