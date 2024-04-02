package com.sniperdev.javafleetmanager.utils;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import com.sniperdev.javafleetmanager.vehicle.Vehicle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

public class PdfGenerator {
    private static final Logger logger = LoggerFactory.getLogger(PdfGenerator.class);

    public static ByteArrayInputStream vehiclesReport(List<Vehicle> vehicles) {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Font font = new Font(Font.getFontStyleFromName("Arial"), 20, Font.BOLD);
        try{
            PdfWriter.getInstance(document, out);
            document.open();
            document.add(new Paragraph("Vehicles Report", font));
            for(Vehicle vehicle : vehicles){
                document.add(new Paragraph("Brand: " + vehicle.getBrand()));
                document.add(new Paragraph("Model: " + vehicle.getModel()));
                document.add(new Paragraph("Year: " + vehicle.getYear()));
                document.add(new Paragraph("Registration number: " + vehicle.getRegistrationNumber()));
                document.add(new Paragraph("Type: " + vehicle.getType()));
                document.add(new Paragraph("Status: " + vehicle.getStatus()));
                document.add(new Paragraph("Driver ID: " + vehicle.getDriverId()));
                document.add(new Paragraph(""));
            }
        }catch(DocumentException ex){
            logger.error("Error occurred: {0}", ex);
        }
        document.close();
        return new ByteArrayInputStream(out.toByteArray());
    }
}
