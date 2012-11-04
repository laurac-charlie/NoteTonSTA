package com.supinfo.notetonsta.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class DesEncrypter {
    private  Cipher ecipher;
    private  Cipher dcipher;

    public DesEncrypter(SecretKey key) {
    	try {
        	ecipher = Cipher.getInstance("DES");
            dcipher = Cipher.getInstance("DES");
            ecipher.init(Cipher.ENCRYPT_MODE, key);
            dcipher.init(Cipher.DECRYPT_MODE, key);

        } catch (javax.crypto.NoSuchPaddingException e) {
        } catch (java.security.NoSuchAlgorithmException e) {
        } catch (java.security.InvalidKeyException e) {
        }
    }
    
    

    public String encrypt(String str) {
        try {
            // Encode the string into bytes using utf-8
            byte[] utf8 = str.getBytes("UTF8");

            // Encrypt
            byte[] enc = ecipher.doFinal(utf8);

            // Encode bytes to base64 to get a string
            return new sun.misc.BASE64Encoder().encode(enc);
        } catch (javax.crypto.BadPaddingException e) {
        } catch (IllegalBlockSizeException e) {
        } catch (UnsupportedEncodingException e) {
        } 
        return null;
    }

    public String decrypt(String str) {
        try {
            // Decode base64 to get bytes
            byte[] dec = new sun.misc.BASE64Decoder().decodeBuffer(str);

            // Decrypt
            byte[] utf8 = dcipher.doFinal(dec);

            // Decode using utf-8
            return new String(utf8, "UTF8");
        } catch (javax.crypto.BadPaddingException e) {
        } catch (IllegalBlockSizeException e) {
        } catch (UnsupportedEncodingException e) {
        } catch (java.io.IOException e) {
        }
        return null;
    }
    

	public static SecretKey generateKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
        keyGenerator.init(56); //128 default; 192 and 256 also possible
        SecretKey key = keyGenerator.generateKey();
        return key; 
    }    
	
    public static void saveKey(SecretKey key, File file) throws IOException  {
        byte[] encoded = key.getEncoded();
        String data = new BigInteger(1, encoded).toString(16);
        writeStringToFile(file, data);
    }
    
	public static SecretKey loadKey(File file) throws IOException {
        String hex = new String(readFileToByteArray(file));
        byte[] encoded = new BigInteger(hex, 16).toByteArray();
        SecretKey key = new SecretKeySpec(encoded, "DES");
        return key;
    }

	private static void writeStringToFile(File file, String data) {
		FileOutputStream fileStream;
		try 
		{
			fileStream = new FileOutputStream(file);
			fileStream.write(data.getBytes());
			fileStream.close();
		} 
		catch (IOException e){
		}
	}

	private static String readFileToByteArray(File file) {
		BufferedReader br;
		try 
		{
			br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			return br.readLine();
		} 
		catch (IOException e) 
		{
			return null;
		}
	}
}