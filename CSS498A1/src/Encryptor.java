import java.io.*;
import java.security.InvalidParameterException;

import javax.crypto.Cipher;


public class Encryptor {
	enum CI_TYPES{AES128,AES256, DES, RSA;}
	Cipher cipher;
	
	public Encryptor(InputStream message, String password, String type) {
		
	}
	
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
