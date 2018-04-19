import java.security.MessageDigest;
import java.util.*;
import java.util.Scanner;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


public class App {

	public static void main(String[] args) {

		System.out.println("Enter message to be sent:");
		Scanner Input = new Scanner(System.in);
		String str = Input.nextLine();

		System.out.println("Enter your key: ");
		int PK = Input.nextInt();
        	RandomNumber R1 = new RandomNumber(PK);
        	RandomNumber R2 = new RandomNumber(PK);

        try
        {
	        byte[] key = Integer.toString(R1.generate()).getBytes("UTF-8");
	        MessageDigest SHA = MessageDigest.getInstance("SHA-1");
	        key = SHA.digest(key);
	        key = Arrays.copyOf(key, 16);
	        SecretKeySpec SK = new SecretKeySpec(key,"AES");
	        // build the initialisation vector.  This example is all zeros, but it
	        // could be any value or generated using a random number generator.
	        byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	        IvParameterSpec IVspec = new IvParameterSpec(iv);

	        Cipher Encrypt;
	        Encrypt = Cipher.getInstance("AES/CBC/PKCS5Padding");
	        Encrypt.init(Cipher.ENCRYPT_MODE, SK, IVspec);
	        byte[] encrypted = Encrypt.doFinal(str.getBytes());
	        System.out.println("Encrypted string : "+ Arrays.toString(encrypted));


	        byte[] key1 = Integer.toString(R2.generate()).getBytes("UTF-8");
	        MessageDigest SHA1 = MessageDigest.getInstance("SHA-1");
	        key1 = SHA1.digest(key1);
	        key1 = Arrays.copyOf(key1, 16);
	        SecretKeySpec SK1 = new SecretKeySpec(key1,"AES");

	        Cipher Decrypt;
	        Decrypt = Cipher.getInstance("AES/CBC/PKCS5Padding");
	        Decrypt.init(Cipher.DECRYPT_MODE, SK1, IVspec);
	        byte[] decrypted = Decrypt.doFinal(encrypted);
	        System.out.println("Decrypted string : "+ new String(decrypted, "UTF-8"));

        }
        catch(Exception e)
        {
        	e.printStackTrace();
        	Input.close();
        }
        Input.close();
	}
}
