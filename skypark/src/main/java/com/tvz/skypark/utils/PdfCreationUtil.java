package com.tvz.skypark.utils;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.encoding.WinAnsiEncoding;
import org.springframework.stereotype.Component;

import com.tvz.skypark.dto.ReservationDetailsDto;
import com.tvz.skypark.model.User;

@Component
public class PdfCreationUtil {
	
	public void generatePDF(ReservationDetailsDto resDetailsDto, String fileCode, User user) throws IOException {
		
		// Create a document and add a page to it
		PDDocument document = new PDDocument();
		PDPage page = new PDPage();
		document.addPage( page );

		// Start a new content stream which will "hold" the to be created content
		PDPageContentStream cs = new PDPageContentStream(document, page);

		// Define a text content stream using the selected font, moving the cursor and drawing the text "Hello World"
		cs.beginText();
		cs.setFont(PDType1Font.HELVETICA, 20);
		cs.newLineAtOffset(250, 750);
		cs.showText( "Potvrda" );
		cs.endText();
		
		cs.beginText();
		cs.setFont(PDType1Font.HELVETICA, 14);
		cs.newLineAtOffset(60, 680);
		cs.showText( "Kod: " + fileCode );
		cs.newLine();
		cs.newLine();
		cs.endText();
		
		//Detalji o korisniku

		cs.beginText();
		cs.setFont(PDType1Font.HELVETICA, 14);
		cs.setLeading(20f);
		cs.newLineAtOffset(60, 610);
		cs.showText("Ime: " + user.getFirstName().replace("ć", "c").replace("č", "c"));
		cs.newLine();
		cs.newLine();
		cs.showText("Prezime: " + user.getLastName().replace("ć", "c").replace("č", "c") );
		cs.newLine();
		cs.newLine();
		cs.showText("Tel.: " + user.getPhoneNumber());
		cs.newLine();
		cs.newLine();
		cs.showText("Email: " + user.getEmail());
		cs.newLine();
		cs.newLine();
		cs.setFont(PDType1Font.HELVETICA_BOLD, 16);
		cs.showText("Cijena: " + String.valueOf(resDetailsDto.getPrice()).replace(".0", ",00") + " HRK" );
		cs.endText();

		// zatvaranje streama
		cs.close();
		
		// save na ftp server
		document.save( "C:/Users/Ivan/Server/" + fileCode + ".pdf" );
		document.close();
		
	}
	
    public String remove(String test) {
        StringBuilder b = new StringBuilder();
        for (int i = 0; i < test.length(); i++) {
            if (WinAnsiEncoding.INSTANCE.contains(test.charAt(i))) {
                b.append(test.charAt(i));
            }
        }
        return b.toString();
    }

}
