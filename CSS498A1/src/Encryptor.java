import java.io.*;
import java.security.InvalidKeyException;
import java.security.InvalidParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.*;
import javax.crypto.spec.*;


public class Encryptor {
	Cipher cipher;
	CipherOutputStream enc_out;
	
	/**
	 * Produces an Encryptor that uses a random key 
	 * @param out
	 * @param type
	 */
	public Encryptor(OutputStream out, String type) {
		
	}
	
	/**
	 * Produces an Encryptor that uses a password as seed for the key
	 * @param out
	 * @param password
	 * @param type
	 */
	public Encryptor(OutputStream out, String password, String type) {
		cipherinit(type, password);
//		try {
//			cipher.init(Cipher.ENCRYPT_MODE, generateKeyFromPass(password));
//		} catch (InvalidKeyException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}
	
//	private SecretKey generateKeyFromPass(String password){
//		SecretKey key = null;
//		try {
//        char[] chars = password.toCharArray();
//         
//        PBEKeySpec spec = new PBEKeySpec(chars);
//        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
//        key = skf.generateSecret(spec);
//		} catch(Exception e) {
//			System.err.println(e.getMessage());
//			System.exit(1);
//		}
//		return key;
//    }
	
	/**
	 * Initializes the cipher to be used. TODO: password stuff?
	 * @param type
	 */
	public void cipherinit(String type, String pass) {
		try{
			switch(CI_TYPES.valueOf(type)){
			case AES:
				cipher = Cipher.getInstance("AES/CCB/PKCS5Padding");
				break;
			case DES:
				cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
				break;
			case RSA:
				cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
				break;
			default:
				throw new InvalidParameterException();
			}
		} catch(Exception e) {
			System.err.println(e.getMessage());
			System.exit(1);
		}
	}
}
