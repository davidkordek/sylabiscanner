package com.example.demo3;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.text.PDFTextStripper;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    private PDDocument doc;

    public Parser(String filePath)throws IOException{
        File file = new File(filePath);

    }

    public void parseFile(File file, HttpServletResponse response)throws IOException{
        doc = PDDocument.load(file);
        //converts the PDF into text
        java.io.PrintWriter out = response.getWriter( );
        PDFTextStripper stripper = new PDFTextStripper();

        // StringBuilder to store the extracted text
        StringBuilder sb = new StringBuilder();

        // Add text to the StringBuilder from the PDF
        sb.append(stripper.getText(doc));

        //Pattern used to find text in file
        //This pattern finds the pattern (DD/MM) in the syllabus
        Pattern p = Pattern.compile("(\\d\\d/\\d\\d)");
        Matcher m = p.matcher(sb);
        while (m.find()){//prints out the matches
            out.println(m.group());
            out.println("</br>");
        }
        if (doc != null) {
            doc.close();
        }

    }
}