package Task2.Classes;

import Task2.Interface.IStudent;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Student implements IStudent {
    private String name;
    private HashMap<String, Integer> disciplines; /*какие дисциплины сданы и баллы по ним*/
    private List<String> faculties; /*на какие факультеты поступил*/

    public Student(String name, HashMap<String, Integer> disciplines){
        this.name = name;
        this.disciplines = disciplines;
        faculties = new ArrayList<>();
    }

    @Override
    public void AddFaculties(String fac)
    {
        if(!faculties.contains(fac)) {
            faculties.add(fac);
        }
    }

    @Override
    public HashMap<String, Integer> getDisciplines() {
        return disciplines;
    }

    @Override
    public void ToNotify() throws IOException {
        String[] FIO = name.split("_");
        String path = FIO[0]+FIO[1]+FIO[2]+".txt";
        String letter = "";
        FileWriter fileWriter = new FileWriter(path);
        if (faculties.size() != 0) {
            letter = "Здравствуйте, " + FIO[0] + " " + FIO[1] + " " + FIO[2] +
                    ", вы поступили на следующие направления:\n";
            int i = 1;
            for (String nameFac : faculties) { /*пишем факультеты с нумерацией и без _*/
                letter += i + ") ";
                for (String s : nameFac.split("_")) {
                    letter += s + " ";
                }
                letter += "\n";
                i++;
            }
        }
        else {
            letter = "Здравствуйте, " + FIO[0] + " " + FIO[1] + " " + FIO[2] +
                    ", к сожалению, вы никуда не поступили.\n";
        }
        fileWriter.write(letter);
        fileWriter.flush();
    }

    @Override
    public void ToNotifyPDF() throws IOException, DocumentException {
        String[] FIO = name.split("_");
        String path = FIO[0]+FIO[1]+FIO[2]+".pdf";
        FileWriter fileWriter = new FileWriter(path);
        String letter = "";
        if (faculties.size() != 0) {
            letter = "Здравствуйте, " + FIO[0] + " " + FIO[1] + " " + FIO[2] +
                    ", вы поступили на следующие направления:\n";
            int i = 1;
            for (String nameFac : faculties) { /*пишем факультеты с нумерацией и без _*/
                letter += i + ") ";
                for (String s : nameFac.split("_")) {
                    letter += s + " ";
                }
                letter += "\n";
                i++;
            }
        }
        else {
            letter = "Здравствуйте, " + FIO[0] + " " + FIO[1] + " " + FIO[2] +
                    ", к сожалению, вы никуда не поступили.\n";
        }
        fileWriter.write(letter);
        fileWriter.flush();

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(path));

        document.open();
        String FONT = "C:\\Windows\\Fonts\\Arial.ttf";
        BaseFont bf=BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font font=new Font(bf,15,Font.NORMAL);
        Paragraph chunk = new Paragraph(letter, font);

        document.add(chunk);
        document.close();
    }
}
