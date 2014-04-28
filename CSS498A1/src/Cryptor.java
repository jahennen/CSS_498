import java.io.*;
import java.security.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;


public class Cryptor {
	
	private static Scanner inScan;
	private Map<String,SecretKey> defaultKeys;
	
	public Cryptor() {
		defaultKeys = new HashMap<String,SecretKey>();
	}
	
	public void encrypt(String type, File keyFile, File in, File out) throws IOException{
		FileInputStream keyStream = new FileInputStream(keyFile);
		Key k = null;
		byte[] encodedKey = new byte[keyStream.available()]; 
		keyStream.read(encodedKey);
		System.err.println(Arrays.toString(encodedKey));
		k = new SecretKeySpec(encodedKey, 0, encodedKey.length, type);
		if (k != null) {
			Cipher c = null;
			try {
				FileInputStream inStream = new FileInputStream(in);
				c = Cipher.getInstance(type);
				c.init(Cipher.ENCRYPT_MODE,k);
				System.out.println("c initalized");
				System.out.println(c.toString());
				CipherOutputStream outCipher = new CipherOutputStream(new FileOutputStream(out), c);
				byte[] buf = new byte[1024];
				int len;
				while((len = inStream.read(buf))!=-1) {
					outCipher.write(buf, 0, len);
				}
				outCipher.close();
				inStream.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		keyStream.close();
	}
	
	public void decrypt(String type, File keyFile, File in, File out) throws IOException {
		FileInputStream keyStream = new FileInputStream(keyFile);
		Key k = null;
		byte[] encodedKey = new byte[keyStream.available()]; 
		keyStream.read(encodedKey);
		System.err.println(Arrays.toString(encodedKey));
		k = new SecretKeySpec(encodedKey, 0, encodedKey.length, type);
		if (k != null) {
			Cipher c = null;
			try {
				FileOutputStream outStream = new FileOutputStream(out);
				c = Cipher.getInstance(type);
				c.init(Cipher.DECRYPT_MODE,k);
				System.out.println("c initalized");
				System.out.println(c.toString());
				CipherInputStream inCipher = new CipherInputStream(new FileInputStream(in), c);
				byte[] buf = new byte[1024];
				int len;
				while((len = inCipher.read(buf)) != -1) {
					System.err.println(Arrays.toString(buf));
					outStream.write(buf, 0, len);
				}
				inCipher.close();
				outStream.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		keyStream.close();
	}
	
	public void genKey(String type, File keyFile) throws IOException {
		FileOutputStream out = new FileOutputStream(keyFile);
		if (type.equals("AES")) {
			Key k = genDefaultKey(type);
			// TODO: write key to output file
			if (k != null) {
				out.write(k.getEncoded());
			}
		}
		if (type.equals("DESede")) {
			Key k = genDefaultKey(type);
			// TODO: write key to output file
			if (k != null) {
				out.write(k.getEncoded());
			}
		}
		if (type.equals("RSA")) {
			
		}
		out.close();
	}
	
	private SecretKey genDefaultKey(String type) {
		KeyGenerator kgen = null;
		try {
			kgen = KeyGenerator.getInstance(type);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SecretKey k = null;
		if (kgen != null) {
			k = kgen.generateKey();
		}
		return k;
	}
	
	public static String nextLine() {
		String retval = inScan.nextLine();
		if(retval.equals("q") || retval.equals("quit"))
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
	
	public static File getKeyFile(String type){
		System.out.println("Keyfile name (blank for default):");
		String kf = inScan.nextLine();
		if (kf.isEmpty()) {
			kf = "def"+type+"key.kf";
		}
		
		return new File(kf);
	}
	
	public static File getInputFile(){
		System.out.println("Input file name:");
		String infile = inScan.nextLine();
		File ret;
		if (infile.isEmpty() || !(ret = new File(infile)).isFile()) {
			return null; 
		}
		return ret;
	}
	
	public static File getOutputFile() {
		System.out.println("Output file name:");
		String outfile = inScan.nextLine();
		if (outfile.isEmpty()) {
			return null;
		}
		return new File(outfile);
	}
	
	public static String getAlgType() {
		System.out.println("1. - AES");
		System.out.println("2. - Triple-DES");
		System.out.println("3. - RSA Public/Private");
		System.out.print("> ");
		String option = nextLine();
		System.out.println();
		if (!validOpt(option)) {
			return "invalid";
		}
		String[] types = {"AES", "DESede", "RSA"};
		return types[Integer.parseInt(option) - 1];
	}
	
	public static void main(String[] args) {
		Cryptor cryptor = new Cryptor();
		// Main menu
		inScan = new Scanner(System.in);
		while(true) {
			System.out.println("Choose an option (q or quit to exit at any point):");
			System.out.println("1. - Encrypt");
			System.out.println("2. - Decrypt");
			System.out.println("3. - Generate Key");
			System.out.print("> ");
			String option = nextLine();
			System.out.println();
			if (!validOpt(option)) {
				continue;
			}
			int o = Integer.parseInt(option);
			String type;
			switch (o) {
			// Encrypt
			case 1:
				System.out.println("Choose algorithm type:");
				type = getAlgType();
				try {
					File infile, outfile = null;
					if((infile = getInputFile()) == null) {
						System.err.println("Missing input file/filename");
						continue;
					}
					
					if ((outfile = getOutputFile()) == null) {
						System.err.println("Missing output filename");
						continue;
					}
					cryptor.encrypt(type, getKeyFile(type) ,infile,outfile);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			// Decrypt
			case 2:
				System.out.println("Choose algorithm type:");
				type = getAlgType();
				try {
					File infile, outfile = null;
					if((infile = getInputFile()) == null) {
						System.err.println("Missing input file/filename");
						continue;
					}
					
					if ((outfile = getOutputFile()) == null) {
						System.err.println("Missing output filename");
						continue;
					}
					cryptor.decrypt(type, getKeyFile(type) ,infile,outfile);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			// Generate Key
			case 3:
				System.out.println("Choose a key type:");
				type = getAlgType();
				if (type.equals("invalid")) {
					continue;
				}
				try {
					cryptor.genKey(type, getKeyFile(type));
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
				
		}
	}
	
}
