package com.sfsb.studentsSFSB.students.model;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfTable;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;

import java.awt.*;
import java.io.IOException;
import java.util.List;

public class GenerateReportPDF {

    private List<Students> listStudents;

    public GenerateReportPDF(List<Students> listStudents) {
        this.listStudents = listStudents;
    }

    private void writeTableHeader(PdfPTable table){
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(new Color(135,206,235));
        cell.setPadding(2);
        Font font = new Font();
        font.setStyle(Font.BOLD);
        //font.setColor(Color.white);

        cell.setPhrase(new Phrase("Date",font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Login",font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Break Duration",font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Logout", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Working Hours/day", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Task",font));
        table.addCell(cell);

    }

    private void writeTableData(PdfPTable table){
        for(Students student: listStudents){
            Font font = new Font();
            font.setSize(11);
            table.addCell(new Phrase(student.getLoginDate().toString(),font));
            table.addCell(new Phrase(student.getLoginTime().toString(),font));
            table.addCell(new Phrase(student.getBreakPeriod().toString(),font));
            table.addCell(new Phrase(student.getLogoutTime().toString(),font));
            table.addCell(new Phrase(student.getWorkHoursPerDay().toString(),font));
            table.addCell(new Phrase(student.getTask(),font));

        }

    }

    public void export(HttpServletResponse response) throws IOException, DocumentException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();
        Font font = new Font();
        font.setSize(14);
        font.setStyle(Font.BOLD);
        Paragraph paragraph = new Paragraph("SUMMARY OF WORKING DAYS AND HOURS",font);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        document.add(paragraph);


        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100);
        table.setSpacingBefore(10);
        table.setWidths(new float[]{1.5f, 1f, 1f, 1f,1.5f,3f});
        //table.getDefaultCell().setPadding(2);
        writeTableHeader(table);
        writeTableData(table);
        document.add(table);
        document.close();


    }
}
