package com.nt.cntrl;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.nt.Dto.patients.PatientResponseDto;
import com.nt.Dto.patients.SanmtiPatr;
import com.nt.service.AdmitPatientService;
import com.nt.service.PatientService;

@Controller
@RequestMapping("/samtiPatra")
public class SamtiPatrController {
	
	@Autowired
	private PatientService patientService;
	
	@GetMapping("/sanmtiptra-form/{id}")
	public String AdmitForm(@PathVariable Long id ,Model model) {
		PatientResponseDto result = patientService.getPatientById( id );
		model.addAttribute( "patient", result );
		return"patients/Sanmtiptr";
	}

	public static final String Mfont = "src\\main\\resources\\static\\Fonts\\Krishna Normal.ttf";
	public static final String Mfont2 = "src\\main\\resources\\static\\Fonts\\Kruti_Dev_010.ttf";
	public static final String Mfont3 = "src\\main\\resources\\static\\Fonts\\Mangal 400.ttf";
	
	
	@PostMapping( "/reportGenerate/{id}" )
	public void generatePdf( @Valid @PathVariable Long id ,@ModelAttribute SanmtiPatr sanmtiPatr, HttpServletResponse response, HttpSession session ) throws DocumentException, IOException {
		
		PatientResponseDto patient = patientService.getPatientById( id );

		response.setContentType( "application/pdf" );
		response.setHeader( "Content-Disposition", "inline; filename=example.pdf" );
		Font bf28anjali = new Font( FontFamily.COURIER, 23, Font.BOLD, BaseColor.DARK_GRAY );
		Font bf30 = new Font( FontFamily.UNDEFINED, 30 );
		Font bf32 = new Font( FontFamily.COURIER, 28, Font.BOLD, BaseColor.DARK_GRAY );
		Font MedicienFont = new Font( FontFamily.HELVETICA, 18, Font.BOLDITALIC, BaseColor.DARK_GRAY );
		Font MedicienTableFont = new Font( FontFamily.HELVETICA, 13, Font.BOLD, BaseColor.WHITE );
		Font MedicienTableDataFont = new Font( FontFamily.HELVETICA, 10, Font.BOLD, BaseColor.DARK_GRAY );
		Font EndBorderFont = new Font( FontFamily.HELVETICA, 1, Font.ITALIC, BaseColor.DARK_GRAY );
		Font bf16 = new Font( FontFamily.COURIER, 11, Font.NORMAL, BaseColor.BLACK );
		Font bf14 = new Font( FontFamily.TIMES_ROMAN, 13, Font.NORMAL, BaseColor.GRAY );
		Font bf14Contact = new Font( FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.GRAY );
		

		BaseFont baseFont = BaseFont.createFont(Mfont, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
		float desiredFontSize = 17; // Set your desired font size here
		Font marathiFont = new Font(baseFont, desiredFontSize, Font.NORMAL);
		
		BaseFont baseFont2 = BaseFont.createFont(Mfont, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
		float desiredFontSize2 = 18; // Set your desired font size here
		Font marathiFont2 = new Font(baseFont2, desiredFontSize2, Font.BOLD);
		
		BaseFont baseFont3 = BaseFont.createFont(Mfont, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
		float desiredFontSize3 = 12; // Set your desired font size here
		Font marathiFont3 = new Font(baseFont3, desiredFontSize3, Font.BOLDITALIC);
		
		BaseFont baseFont4 = BaseFont.createFont(Mfont, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
		float desiredFontSize4 = 14; // Set your desired font size here
		Font marathiFont4 = new Font(baseFont4, desiredFontSize4, Font.NORMAL);
		
		BaseFont baseFont5 = BaseFont.createFont(Mfont, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
		float desiredFontSize5 = 17; // Set your desired font size here
		Font marathiFont5 = new Font(baseFont5, desiredFontSize5, Font.BOLD);
		
		BaseFont baseFont7 = BaseFont.createFont(Mfont2, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
		float desiredFontSize7 = 14; // Set your desired font size here
		Font marathiFont7 = new Font(baseFont7, desiredFontSize7, Font.NORMAL);
		
		
		
		float leftMargin5f = 5f;
		float leftMargin10f = 10f;
		float leftMargin15f = 15f;
		float leftMargin20f = 20f;
		float leftMargin25f = 25f;
		float leftMargin50f = 50f;
		float leftMargin70f = 70f;
		float leftMargin90f = 90f;
		float leftMargin140f = 140f;
		float leftMargin550f = 500f;
		float opacity = 0.5f;
		Document document = new Document();
		PdfWriter.getInstance( document, response.getOutputStream() );
		document.open();
		LocalDateTime currentDateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern( "yyyy-MM-dd HH:mm:ss" );
		String formattedDateTime = currentDateTime.format( formatter );
		
		 
		PdfPTable Medicine = new PdfPTable( 3 );

		Medicine.setWidthPercentage( 100 );
		Medicine.setSpacingBefore( 5f );
		Medicine.setWidths( new float[] { 2.4f, 2.4f, 3.0f} );

		PdfPCell c1 = new PdfPCell( new Phrase( "Medicine Type", bf16 ) );
		c1.setBorderWidth( 1 );
		Phrase additionalParagraph1 = new Phrase(" Anjali ICU ", bf28anjali);
		Phrase additionalParagraph2 = new Phrase("\n And Multi speciality Health Care", MedicienTableDataFont);
		c1.addElement(additionalParagraph1);
		c1.addElement(additionalParagraph2);
		Medicine.addCell(c1);

		PdfPCell c2 = new PdfPCell( new Phrase( "Medicine Name", bf16 ) );
		c2.setBorderWidth( 1 );
		Phrase additionalParagraph21 = new Phrase(" Chalisgaon Road , Bhadgaon ", bf14);
		Phrase additionalParagraph23 = new Phrase("            Dist-Jalgaon ", bf14);
		Phrase additionalParagraph24 = new Phrase("Contact :02596-297777-295777 \n ", bf14Contact);
		Phrase additionalParagraph22 = new Phrase("anjalihospital7179@gmail.com", MedicienTableDataFont);
		c2.addElement(additionalParagraph21);
		c2.addElement(additionalParagraph23);
		c2.addElement(additionalParagraph24);
		c2.addElement(additionalParagraph22);
		
		Medicine.addCell( c2 );

		PdfPCell c3 = new PdfPCell(new Phrase("Dosage", bf16));
		c3.setBorderWidth(1);
		Phrase additionalParagraph = new Phrase("Document No: \nPatien Name: \nGender: \nMRN No:         Bed No:     \nWard No:        IPD No:     \nConsultant:        Age:     ", bf16);
		c3.addElement(additionalParagraph);

		Medicine.addCell(c3);

		document.add( Medicine );
		
		
		Paragraph indentedParagraph1 = new Paragraph( "laerh i= ", marathiFont );
		indentedParagraph1.setIndentationLeft( 200 );
		document.add( indentedParagraph1 );

		Paragraph para2 = new Paragraph( "vatyh vfrn{krk foHkkx ]HkMxko",marathiFont2 );
		para2.setIndentationLeft( 130 );
		document.add( para2 );

		

		
		PdfPTable table4 = new PdfPTable( 6 );
		table4.setSpacingBefore( 25f );
		table4.setWidthPercentage( 100 );
		table4.setWidths( new float[] { 1.0f, 4.0f,0.0f, 0.0f,0.7f, 1.0f } );

		table4.addCell( createCell( "#X.kkps uko &  ", 0 ) );
		table4.addCell( createCelll( ""+patient.getFirstName()+" "+patient.getLastName()+"", 0 ) );
		table4.addCell( createCell( "-----------", 0 ) );
		table4.addCell( createCell( "", 0 ) );
		table4.addCell( createCell( "o; &", 0 ) );
		table4.addCell( createCelll( ""+patient.getAge()+"", 0 ) );
		table4.addCell( createCell( "eh &  ", 0 ) );
		table4.addCell( createCelll( ""+sanmtiPatr.getResponsible_Person()+"", 0 ) );
		table4.addCell( createCell( "-------------", 0 ) );
		table4.addCell( createCell( "", 0 ) );
		table4.addCell( createCell( "jkg.kkj &", 0 ) );
		table4.addCell( createCelll( ""+patient.getAddress()+"", 0 ) );

		document.add( table4 );
		
		Paragraph para3 = new Paragraph("              eyk Lor%P;k  ek>s ukrsokbZdkaP;k vatyh vfrn{krk foHkkx] HkMxko e/;s mipkjklkBh Hkjrh dj.;kl laerh nsr vkgs - byktklkBh vko';d R;k rikl.;k o  vko';d R;k 'kL=fØ;k dj.;kph ek>h laerh vkgs -eh ek>h ek>s ukrsokbZdkaph oS|dh; dkxni=s ;ksX; R;k O;ähl xjt vlY;kl nk[ko.;kph ijokuxh nsr vkgs - eyk gk‚fLiVy   fu;ekaph tk.kho vlwu R;k çek.ks eh okx.;kph tckcnkjh ?ksrs #X.kkcjkscj vk.kysys iSls ]nkfxus o tksf[kespk oLrw eh rC;r ?ksrY;k vlwu R;kcíy g‚fLiVy ph dkgh tckcnkjh jkg.kkj ukgh - vuisf{kr çlaxh o T;k M‚DVjkapk bykt pkyw vkgs rs tj dkgh vifjgk;Z dkj.kkeqGs ;sÅ u 'kdY;kl frP;k,soth ;ksX; R;k nqljh M‚DVjkauk bykt d: ns.;kl O;oLFkkiukl ijokuxh nsr vkgs - g‚fLiVy P;k Qhl o brj pktsZl cíy eyk dYiuk fnyh vlwu] R;kçek.ks eh iSls Hkj.;kph tckcnkjh ?ksrks - dkgh vuisf{kr o ekuoh ç;Rukapk ckgsjP;k ?kVuk #X.kkckcrhr ?kMw 'kdrkr ;kph eyk dYiuk vkgs o R;kcíy eh g‚fLiVy yk tckcnkj /kj.kkj ukgh - \n        ojhy loZ etdwj eh ]dkGthiwoZd okpyk vlwu R;kuarjp eh lgh dsyh vkgs -" , marathiFont4 );
		document.add( para3 );
		// add Line
		Paragraph lineParagraph2 = new Paragraph();

	
		
		
		PdfPTable table7 = new PdfPTable( 6 );
		table7.setSpacingBefore( 25f );
		table7.setWidthPercentage( 100 );
		table7.setWidths( new float[] { 0.5f, 2.0f,1.0f, 0.0f,0.5f, 1.5f } );

		table7.addCell( createCell( "fnukad &   ", 0 ) );
		table7.addCell( createCelll( ""+formattedDateTime+"", 0 ) );
		table7.addCell( createCell( "", 0 ) );
		table7.addCell( createCell( "", 0 ) );
		table7.addCell( createCell( "uko &", 0 ) );
		table7.addCell( createCelll( ""+sanmtiPatr.getResponsible_Person()+"", 0 ) );
		table7.addCell( createCell( "", 0 ) );
		table7.addCell( createCell( "", 0 ) );
		table7.addCell( createCell( "", 0 ) );
		table7.addCell( createCell( "", 0 ) );
		table7.addCell( createCell( "lgh &", 0 ) );
		table7.addCell( createCelll( " ---------------------------------", 0 ) );

		document.add( table7 );
		
		LineSeparator line2 = new LineSeparator();
		line2.setLineWidth( 1 );
		line2.setLineColor( BaseColor.GRAY );
		lineParagraph2.add( line2 );
		document.add( lineParagraph2 );
		
		

		PdfPTable SanmtiPatr = new PdfPTable( 3 );
		SanmtiPatr.setWidths( new float[] { 1.5f, 2.5f, 1.5f} );
		SanmtiPatr.setSpacingBefore(18f);
		PdfPCell sp = new PdfPCell( new Phrase( "", MedicienTableFont ) );
		sp.setBorderWidth( 0 );
		SanmtiPatr.addCell( sp );

		PdfPCell sp1 = new PdfPCell( new Phrase( "#X.k vR;oLFk vlY;kph ekfgrh", marathiFont5 ) );
		sp1.setBorderWidth( 0 );
		SanmtiPatr.addCell( sp1 );

		PdfPCell sp2 = new PdfPCell( new Phrase( "", MedicienTableFont ) );
		sp2.setBorderWidth( 0 );
		SanmtiPatr.addCell( sp2 );
		
		document.add( SanmtiPatr );
		
		PdfPTable table8 = new PdfPTable( 3 );
		table8.setSpacingBefore( 10f );
		table8.setWidthPercentage( 100 );
		table8.setWidths( new float[] { 3.5f, 3.0f,4.0f } );

		table8.addCell( createCell( "	       	 eyk ek>s ukrsokbZd", 0 ) );
		table8.addCell( createCelll( ""+patient.getFirstName()+""+patient.getLastName()+"", 0 ) );
		table8.addCell( createCell( ";kaph  rC;sr vÙoLFk ¼fparktud ½ ", 0 ) );
		
		document.add( table8 );

		
		Paragraph para4 = new Paragraph("vlY;kph iwoZ dYiuk  nsÅu ]M‚DVjkauh letkowu lkafxrya vkgs - rjh R;kaP;koj vko';d rs loZ mipkj o lacaf/kr rikl.;k dj.;kl M‚DVjkauk o O;oLFkkiukl laerh nsr vkgs -" , marathiFont4 );
		document.add( para4 );																			
		
		PdfPTable table6= new PdfPTable( 6 );
		table6.setSpacingBefore( 25f );
		table6.setWidthPercentage( 100 );
		table6.setWidths( new float[] { 0.5f, 2.0f,1.0f, 0.0f,0.5f, 1.5f } );

		table6.addCell( createCell( "fnukad &   ", 0 ) );
		table6.addCell( createCelll(""+formattedDateTime+"", 0 ) );
		table6.addCell( createCell( "", 0 ) );
		table6.addCell( createCell( "", 0 ) );
		table6.addCell( createCell( "uko &", 0 ) );
		table6.addCell( createCelll( ""+sanmtiPatr.getResponsible_Person()+"", 0 ) );
		table6.addCell( createCell( "", 0 ) );
		table6.addCell( createCell( "", 0 ) );
		table6.addCell( createCell( "", 0 ) );
		table6.addCell( createCell( "", 0 ) );
		table6.addCell( createCell( "lgh &", 0 ) );
		table6.addCell( createCell( "------------------------------------", 0 ) );

		document.add( table6 );

		Rectangle rect = new Rectangle( 557, 805, 28, 35 ); // you can resize rectangle
		rect.enableBorderSide( 1 );
		rect.setBorderColor( BaseColor.BLACK );
		rect.setBorderWidth( 3 );
		document.add( rect );

		Rectangle rect1 = new Rectangle( 577, 825, 18, 15 ); // you can resize rectangle
		rect1.enableBorderSide( 1 );
		rect1.enableBorderSide( 2 );
		rect1.enableBorderSide( 4 );
		rect1.enableBorderSide( 8 );
		rect1.setBorderColor( BaseColor.BLACK );
		rect1.setBorderWidth( 1 );
		document.add( rect1 );

		document.close();
	}

	private static PdfPCell createCell( String text, float borderWidth ) throws DocumentException, IOException {
		
		BaseFont baseFont4 = BaseFont.createFont(Mfont, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
		float desiredFontSize4 = 14; // Set your desired font size here
		Font marathiFont4 = new Font(baseFont4, desiredFontSize4, Font.NORMAL);

         // Create a Font object with Kruti Dev font
		
		PdfPCell cell = new PdfPCell();
		cell.setBorderWidth( borderWidth );
		cell.addElement( new com.itextpdf.text.Paragraph( text, marathiFont4) );
		return cell;
	}

	private static PdfPCell createCelll( String text, float borderWidth ) throws DocumentException, IOException {
		
	

        Font marFont = FontFactory.getFont("arial unicode ms",BaseFont.IDENTITY_H,true);

		
		PdfPCell cell = new PdfPCell();
		cell.setBorderWidth( borderWidth );
		cell.addElement( new com.itextpdf.text.Paragraph( text, marFont) );
		return cell;
	}

	
}
