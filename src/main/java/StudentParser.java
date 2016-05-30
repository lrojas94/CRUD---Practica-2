import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by luis on 5/29/16.
 */
public class StudentParser {
    public static String url = "jdbc:h2:tcp://localhost/~/students";

    public StudentParser(){
        try{
            Class.forName("org.h2.Driver");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


    public ArrayList<Student> getStudents(String mat){

        ArrayList<Student> students = new ArrayList<Student>();

        try {
            Connection con = DriverManager.getConnection(url,"sa","");
            String query = "SELECT * FROM STUDENTS.PUBLIC.ESTUDIANTES";
            if(mat != ""){
                query += " WHERE MATRICULA = " + mat;
            }
            PreparedStatement statement = con.prepareStatement(query);
            ResultSet set = statement.executeQuery();
            int i = 0;
            while(set.next()){
                Student student = new Student();
                student.setMatricula(Integer.parseInt(set.getString("matricula")));
                student.setNombre(set.getString("nombre"));
                student.setApellido(set.getString("apellidos"));
                student.setTelefono(set.getString("telefono"));
                students.add(student);
            }

            return students;

        }catch (Exception e){
            System.out.println("T.T ERROR");
            System.out.println(e.getMessage());
        } finally {
            return students;
        }
    }

    public boolean addStudent(Student student){
        try {
            int matricula = student.getMatricula();
            if(getStudents(Integer.toString(matricula)).size() != 0){
                return false; //Cant add student
            }
            Connection con = DriverManager.getConnection(url, "sa", "");
            String query = String.format("INSERT INTO STUDENTS.PUBLIC.ESTUDIANTES (MATRICULA, NOMBRE, APELLIDOS, TELEFONO) " +
                    "VALUES ('%s','%s','%s','%s')",student.getMatricula(),student.getNombre(),student.getApellido(),student.getTelefono());

            con.createStatement().execute(query);
            return true;
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean saveStudent(Student student,String matricula){
        try{

            ArrayList<Student> studentsFound = getStudents(matricula);
            if(studentsFound.size() > 1 ||
                    !(studentsFound.size() == 1 && Integer.toString(studentsFound.get(0).getMatricula()).equals(matricula))){
                return false; //Cant update student
            }

            Connection con = DriverManager.getConnection(url, "sa", "");
            String query = String.format("UPDATE STUDENTS.PUBLIC.ESTUDIANTES SET" +
                    " MATRICULA = '%s', NOMBRE='%s', APELLIDOS='%s', TELEFONO='%s'" +
                    " WHERE MATRICULA = '%s'"
                    ,student.getMatricula(),student.getNombre(),student.getApellido(),student.getTelefono(),matricula);

            con.createStatement().execute(query);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return false;
    }

    public boolean delete(String matricula){
        try{
            ArrayList<Student> studentsFound = getStudents(matricula);
            if(studentsFound.size() == 0){
                return false; //Cant delete student
            }
            Connection con = DriverManager.getConnection(url, "sa", "");
            String query = String.format("DELETE FROM STUDENTS.PUBLIC.ESTUDIANTES" +
                            " WHERE MATRICULA = '%s'",matricula);

            con.createStatement().execute(query);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return false;
    }
}
