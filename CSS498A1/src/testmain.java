import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import javax.crypto.*;


public class testmain {
	public static void main(String[] args) {
		
		Map<String,Cipher> ciphers = new TreeMap<String,Cipher>();		
		
		System.out.print("Ciphertext: ");
		Scanner s = new Scanner(System.in);
		String ctext = s.nextLine();
		System.out.print("");
		
		
		System.out.println("Ciphertext was: \"" + ctext + "\"");
		
	}
	
}
