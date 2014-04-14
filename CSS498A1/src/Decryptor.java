

/*
 * This class reads a stream and decrypts it with the specified algorithm and key into another stream
 */

import java.io.*;
import javax.crypto.*;

public class Decryptor {
	
	
	public Decryptor(InputStream in, OutputStream out) {
		Cipher c = new Cipher();
		CipherInputStream de = new CipherInputStream(in);
	}
}
