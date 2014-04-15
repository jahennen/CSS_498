import java.io.*;
import java.security.InvalidParameterException;

import javax.crypto.Cipher;


public class Encryptor {
	enum CI_TYPES{AES128,AES256, DES, RSA;}
	Cipher cipher;
	OutputStream enc_out;
	
	/**
	 * Produces an Encryptor that 
	 * @param out
	 * @param type
	 */
	public Encryptor(OutputStream out, String type) {
		
	}
	
	public Encryptor(OutputStream out, String password, String type) {
		cipherinit(type);
		
	}
	
	/**
	 * Initializes the cipher to be used. TODO: password stuff?
	 * @param type
	 */
	public void cipherinit(String type) {
		try{
			switch(CI_TYPES.valueOf(type)){
			case AES128:
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
