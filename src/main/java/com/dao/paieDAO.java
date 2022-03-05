package com.dao;


import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfWriter;
import com.model.CarteModel;
import com.util.JPAutil;

public class paieDAO {
	
	private EntityManager entityManager=JPAutil.getEntityManager("test2jdbc");

    public CarteModel check(String numCarteu, String cryptogrammeu) {
    	
        CarteModel m;
        Query query=entityManager.createQuery("select m from CarteModel m where m.numCarte= :numCarteu "
        		+ "and m.cryptogramme= :cryptogrammeu");
		query.setParameter("numCarteu", numCarteu);
		//query.setParameter("dateExpu", dateExpu);
		query.setParameter("cryptogrammeu", cryptogrammeu);		
		m= (CarteModel) query.getSingleResult();
        
           if(m.getDateExp() != null) {
        	   
                String numCarte = m.getNumCarte();
                Date dateExp = m.getDateExp();
                String cryptogramme = m.getCryptogramme();

                m.setNumCarte(numCarte);
                m.setDateExp(dateExp);
                m.setCryptogramme(cryptogramme);
                
           }
           
        return m;
    }
    
    public static void sendEmail(String host, String port,
            final String userName, final String password, String toAddress,
            String subject, String message, String nom, String prenom, String tel, String montantAbonnement) throws AddressException,
            MessagingException {
 
       
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
 
        
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        };
 
        Session session = Session.getInstance(properties, auth);
 
      
        ByteArrayOutputStream outputStream = null;
        
        try {           
            
            MimeBodyPart textBodyPart = new MimeBodyPart();
            textBodyPart.setText(message);
             
            
            outputStream = new ByteArrayOutputStream();
            writePdf(outputStream,nom,prenom,tel,montantAbonnement);
            byte[] bytes = outputStream.toByteArray();
             
            
            DataSource dataSource = new ByteArrayDataSource(bytes, "application/pdf");
            MimeBodyPart pdfBodyPart = new MimeBodyPart();
            pdfBodyPart.setDataHandler(new DataHandler(dataSource));
            pdfBodyPart.setFileName("facture.pdf");
                         
            
            MimeMultipart mimeMultipart = new MimeMultipart();
            mimeMultipart.addBodyPart(textBodyPart);
            mimeMultipart.addBodyPart(pdfBodyPart);
       
          
        Message msg = new MimeMessage(session);
 
        
        msg.setFrom(new InternetAddress(userName));
        InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.setSubject(subject);
        msg.setText(message);
        msg.setContent(mimeMultipart);
        
        Transport.send(msg);
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            if(null != outputStream) {
                try { outputStream.close(); outputStream = null; }
                catch(Exception ex) { }
            }
        }
    }
 	
    public static void writePdf(OutputStream outputStream, String nom, String prenom, String tel, String montantAbonnement) throws Exception {
    	
    	Font blueFont = FontFactory.getFont(FontFactory.COURIER, 14, Font.BOLD, new CMYKColor(255, 0, 0, 0));
    	Font blueFontgras = FontFactory.getFont(FontFactory.COURIER, 30, Font.BOLD, new CMYKColor(255, 0, 0, 0));
    	Font redFont = FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD, new CMYKColor(0, 255, 0, 0));
    	Font yellowFont = FontFactory.getFont(FontFactory.COURIER, 14, Font.BOLD, new CMYKColor(0, 0, 255, 0));
    	
        Document document = new Document();
        PdfWriter.getInstance(document, outputStream);
         
        document.open();
         
        document.addTitle("Facture PDF");
        document.addSubject("Facture email PDF");
        document.addKeywords("iText, email");
        document.addAuthor("yas");
        document.addCreator("yas");

        Paragraph chapterTitle = new Paragraph("Facture pay�e", blueFont);
        Chapter chapter1 = new Chapter(chapterTitle, 1);
        chapter1.setNumberDepth(0);
     
        Paragraph sectionTitle = new Paragraph("D�tails de la facture :", yellowFont);
        Section section1 = chapter1.addSection(sectionTitle);
        
        Paragraph sectionContenta = new Paragraph("1 - D�tails personnelles de l'abonn� :", redFont);        
        Paragraph sectionContent = new Paragraph("Nom : "+nom);
        Paragraph sectionContent2 = new Paragraph("Pr�nom : "+prenom);
        Paragraph sectionContent3 = new Paragraph("Num�ro de t�l�phone : 0"+tel);
        Paragraph sectionContent4 = new Paragraph("...");
        Paragraph sectionContentb = new Paragraph("2 - D�tails de facturation :", redFont);        
        Paragraph sectionContent1 = new Paragraph("Montant de la facture : "+montantAbonnement+" DHS");
        Paragraph sectionContent22 = new Paragraph("Etat de la facture : Pay�e");
        Paragraph sectionContent33 = new Paragraph("Moyen de paiement : Carte bancaire Visa");
        Paragraph sectionContent55 = new Paragraph("...");
        Paragraph sectionContent66 = new Paragraph("Merci pour votre confiance, � bient�t !", blueFontgras);
        section1.add(sectionContenta);        
        section1.add(sectionContent);
        section1.add(sectionContent2);
        section1.add(sectionContent3);
        section1.add(sectionContent4);
        section1.add(sectionContentb);
        section1.add(sectionContent1);
        section1.add(sectionContent22);
        section1.add(sectionContent33);
        section1.add(sectionContent55);
        section1.add(sectionContent66);
        
        document.add(chapter1);
        document.close();
    }
    public static void sendEmailseul(String host, String port,
            final String userName, final String password, String toAddress,
            String subject, String message) throws AddressException,
            MessagingException {
 
        // sets SMTP server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        // creates a new session with an authenticator
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        };
        Session session = Session.getInstance(properties, auth);
 
        // creates a new e-mail message
        Message msg = new MimeMessage(session);
 
        msg.setFrom(new InternetAddress(userName));
        InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.setSubject(subject);
       
        msg.setText(message);
 
        // sends the e-mail
        Transport.send(msg);
 
    }

}
