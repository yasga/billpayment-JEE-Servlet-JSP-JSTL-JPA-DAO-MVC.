package com.servlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfWriter;


@WebServlet("/PdfServlet")
public class PdfServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public PdfServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		Font blueFont = FontFactory.getFont(FontFactory.COURIER, 14, Font.BOLD, new CMYKColor(255, 0, 0, 0));
    	Font blueFontgras = FontFactory.getFont(FontFactory.COURIER, 30, Font.BOLD, new CMYKColor(255, 0, 0, 0));
    	Font redFont = FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD, new CMYKColor(0, 255, 0, 0));
    	Font yellowFont = FontFactory.getFont(FontFactory.COURIER, 14, Font.BOLD, new CMYKColor(0, 0, 255, 0));
		
        String nomu = request.getParameter("nom");
        String prenomu = request.getParameter("prenom");
        String telu = request.getParameter("tel");
        String montantAbonnementu = request.getParameter("montantAbonnement");
		
		try{
	        Document document = new Document();
	        // step 2
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        
	        PdfWriter.getInstance(document, baos);
	        
	        document.open();
	        document.addTitle("Facture PDF");
	        document.addSubject("Facture email PDF");
	        document.addKeywords("iText, email");
	        document.addAuthor("yassine");
	        document.addCreator("yassine");

	        Paragraph chapterTitle = new Paragraph("Facture payée", blueFont);
	        Chapter chapter1 = new Chapter(chapterTitle, 1);
	        chapter1.setNumberDepth(0);
	     
	        Paragraph sectionTitle = new Paragraph("Détails de la facture :", yellowFont);
	        Section section1 = chapter1.addSection(sectionTitle);
	        
	        Paragraph sectionContenta = new Paragraph("1 - Détails personnelles de l'abonné :", redFont);        
	        Paragraph sectionContent = new Paragraph("Nom : "+nomu);
	        Paragraph sectionContent2 = new Paragraph("Prénom : "+prenomu);
	        Paragraph sectionContent3 = new Paragraph("Numéro de téléphone : 0"+telu);
	        Paragraph sectionContent4 = new Paragraph("...");
	        Paragraph sectionContentb = new Paragraph("2 - Détails de facturation :", redFont);        
	        Paragraph sectionContent1 = new Paragraph("Montant de la facture : "+montantAbonnementu+" DHS");
	        Paragraph sectionContent22 = new Paragraph("Etat de la facture : Payée");
	        Paragraph sectionContent33 = new Paragraph("Moyen de paiement : Carte bancaire Visa");     
	        Paragraph sectionContent55 = new Paragraph("...");
	        Paragraph sectionContent66 = new Paragraph("Merci pour votre confiance, à bientôt !", blueFontgras);
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
	        
	        response.setHeader("Expires", "0");
	        response.setHeader("Cache-Control",
	                "must-revalidate, post-check=0, pre-check=0");
	        response.setHeader("Pragma", "public");
	        response.setContentType("application/pdf");
	        response.setContentLength(baos.size());

	        ServletOutputStream os = response.getOutputStream();
	        
	        baos.writeTo(os);
	        os.flush();
	        os.close();
	        
	        
	        
	    }
	    catch(DocumentException e) {
	        throw new IOException(e.getMessage());
	    }
	}

}