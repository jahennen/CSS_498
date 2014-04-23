import java.security.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import javax.crypto.*;


public class Cryptor {
	
	private static Scanner inScan;
	private Map<String,SecretKey> defaultKeys;
	
	public Cryptor() {
		defaultKeys = new HashMap<String,SecretKey>();
	}
	
	public void encrypt() {
		
	}
	
	public void decrypt() {
		
	}
	
	public void genKey(String type) {
		System.out.println("Keyfile name (blank for default):");
		String kf = inScan.nextLine();
		if (kf.isEmpty()) {
			kf = "def"+type+"key.kf";
		}
		KeyGenerator kgen = null;
		if (type.equals("AES")) {
			try {
				kgen = KeyGenerator.getInstance("AES");
				kgen.init(128);
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (kgen != null) {
				defaultKeys.put("AES", kgen.generateKey());
				// TODO: write key to output file
			}
		} else if (type.equals("TDES")) {
			try {
				kgen = KeyGenerator.getInstance("DESede");
				kgen.init(128);
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (kgen != null)
				defaultKeys.put("AES", kgen.generateKey());
		}
	}
	
	public static String nextLine() {
		String retval = inScan.nextLine();
		if(inScan.equals("q") || inScan.equals("quit"))
			System.exit(0);
		return retval;
	}
	
	public static boolean validOpt(String option) {
		if (option.length()!=1 && !"123".contains(option)) {
			System.out.println("Invalid input\n");
			return false;
		}
		return true;
	}
	
	public static void main(String[] args) {
		Cryptor cryptor = new Cryptor();
		while(true) {
			System.out.println("Choose an option (q or quit to exit at any point):");
			System.out.println("1. - Encrypt");
			System.out.println("2. - Decrypt");
			System.out.println("3. - Generate Key");
			inScan = new Scanner(System.in);
			String option = nextLine();
			if (!validOpt(option)) {
				continue;
			}
			int o = Integer.parseInt(option);
			switch (o) {
			case 1:
				cryptor.encrypt();
				break;
			case 2:
				break;
			case 3:
				System.out.println("Choose a key type (q or quit to exit):");
				System.out.println("1. - AES");
				System.out.println("2. - Triple-DES");
				System.out.println("3. - RSA Public/Private");
				option = nextLine();
				if (!validOpt(option)) {
					continue;
				}
				String[] types = {"AES", "TDES", "RSA"};
				cryptor.genKey(types[Integer.parseInt(option)]);
				break;
			}
				
		}
	}
	
}
