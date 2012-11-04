package com.supinfo.notetonsta.util;

import java.security.Key; 
import javax.crypto.Cipher; 
import javax.crypto.spec.SecretKeySpec; 
 
public class Securite {
  private String algo = "Blowfish";
 
  public String crypter(String password, String text) {
    try {
      byte[] passwordInBytes = password.getBytes("UTF-8"); 
      Key clef = new SecretKeySpec(passwordInBytes, algo); 
      Cipher cipher = Cipher.getInstance(algo);
      cipher.init(Cipher.ENCRYPT_MODE, clef);

      byte[] cryptedText = cipher.doFinal(text.getBytes());
      return cryptedText.toString();
    }
    catch (Exception e) {
      System.out.println("Erreur lors de l'encryptage des donnees");
      return "";
    }
  }
 
  public String decrypter(String password, String text) {
    try {
      byte[] passwordInBytes = password.getBytes("UTF-8"); 
      Key clef = new SecretKeySpec(passwordInBytes, algo); 
      Cipher cipher = Cipher.getInstance(algo);
      cipher.init(Cipher.DECRYPT_MODE, clef);
      
      byte[] clearText = cipher.doFinal(new sun.misc.BASE64Decoder().decodeBuffer(text));
      return new String(clearText,"UTF-8");
    }
    catch (Exception e) {
      System.out.println(e.toString()); 
      return "";
    }
  }

}
