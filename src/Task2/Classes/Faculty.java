package Task2.Classes;

import Task2.Interface.IFaculty;
import Task2.Interface.IStudent;
import com.itextpdf.text.DocumentException;
import org.pdfbox.exceptions.COSVisitorException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Faculty implements IFaculty {
    private List<IStudent> students;
    private String name;
    private HashMap<String, Integer> disciplines;

    public Faculty(String name, HashMap<String, Integer> disciplines)
    {
        students = new ArrayList<>();
        this.name = name;
        this.disciplines = disciplines;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public HashMap<String, Integer> getDisciplines() {
        return disciplines;
    }

    @Override
    public void AddStudent(IStudent stud) {
        if (!students.contains(stud)) {
            students.add(stud);
        }
    }

    @Override
    public void RemoveStudent(IStudent stud) {
        students.remove(stud);
    }

}
