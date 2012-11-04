package com.supinfo.notetonsta.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.supinfo.notetonsta.dao.CampusDAO;
import com.supinfo.notetonsta.dao.SpeakerDAO;
import com.supinfo.notetonsta.entity.Campus;
import com.supinfo.notetonsta.entity.Speaker;

/**
 * Application Lifecycle Listener implementation class PersistenceManager
 *
 */
@WebListener
public class PersistenceManager implements ServletContextListener {
	
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("NoteTonSTA-PU");
	private static final String PASS_SENTENCE= "test";

	
	public static EntityManagerFactory getEntityManagerFactory(){
		return emf;
	}

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent servcon) {
    	if (CampusDAO.getInstance().getAllCampus().isEmpty())
    	{
    		CampusDAO.getInstance().create(new Campus("Guadeloupe"));
    		CampusDAO.getInstance().create(new Campus("Paris"));
    		CampusDAO.getInstance().create(new Campus("Lille"));
    		CampusDAO.getInstance().create(new Campus("Montreal"));
    		CampusDAO.getInstance().create(new Campus("London"));
    		CampusDAO.getInstance().create(new Campus("Maroc"));
    		SpeakerDAO.getInstance().create(new Speaker("TEST","Test","test@test.com","test"));
    	}
    	
    	/*Securite sec = new Securite();
    	System.out.println("crypted : " + sec.crypter(PASS_SENTENCE, "trr"));
    	System.out.println(" ts" + sec.decrypter(PASS_SENTENCE,sec.crypter(PASS_SENTENCE, "test")));
    	DesEncrypterText.getInstance().encrypt("text");
    	System.out.println(DesEncrypterText.getInstance().decrypt(DesEncrypterText.getInstance().encrypt("text")))
    	
    	File keyFile = new File("C:\\Users\\Charlie\\Documents\\test.txt");
    	SecretKey key = null;
        if(keyFile.isFile())
        {
        	try {
				key = DesEncrypter.loadKey(keyFile);
				System.out.println("load");
			} catch (IOException e) {
				System.out.println("fail1");
			}
        }
        else
        {
        	try {
        		System.out.println("c1");
				keyFile.createNewFile();
				System.out.println("c2");
				key = DesEncrypter.generateKey();
				DesEncrypter.saveKey(key, keyFile);
				System.out.println("new");
			} catch (IOException | NoSuchAlgorithmException e) {
				System.out.println("fail2");
			}
        }
        */
        
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0) {
        emf.close();
    }

	public static String getPassSentence() {
		return PASS_SENTENCE;
	}
	
}
