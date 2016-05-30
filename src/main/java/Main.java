/**
 * Created by luis on 5/29/16.
 */
import freemarker.core.FreeMarkerTree;

import  freemarker.core.*;

import spark.ModelAndView;
import spark.Request;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;


public class Main {
    private static Student createStudentFromForm(Request request){
        Student student = new Student();
        student.setNombre(request.queryParams("name"));
        student.setApellido(request.queryParams("lastName"));
        student.setMatricula(Integer.parseInt(request.queryParams("mat")));
        student.setTelefono(request.queryParams("tel"));

        return student;
    }

    public static void main(String[] args) {
        staticFiles.location("/public");
        StudentManager studentManager = new StudentManager();

        get("/", (request,response)->{
            Map<String, Object> attributes = new HashMap<String, Object>();
            attributes.put("students", studentManager.getStudents(""));

            if(request.queryParams("delete") != null){
                attributes.put("delete",request.queryParams("delete"));
            }

            return new ModelAndView(attributes,"students.ftl");
        }, new FreeMarkerEngine());

        get("/view/:matricula",((request, response) -> {
            Map<String, Object> attributes = new HashMap<String, Object>();
            String mat = request.params("matricula");
            ArrayList<Student> student = studentManager.getStudents(mat);
            if(student.size() != 0){
                //Student found
                attributes.put("student",student.get(0));
            }
            else{
                attributes.put("student",null);
            }
            return new ModelAndView(attributes,"view_student.ftl");
        }),new FreeMarkerEngine());

        get("/add", (request, response) -> {
            //here there's nothing to do...just print the form
            return new ModelAndView(null,"add_edit_student.ftl");
        },new FreeMarkerEngine());

        post("/add",(request, response) -> {
            Map<String, Object> attributes = new HashMap<String, Object>();
            Student student = createStudentFromForm(request);

            if(studentManager.addStudent(student)){
                response.redirect("/");
            }
            else{
                attributes.put("student",student);
                attributes.put("message","Error agregando estudiante. Probablemente la matricula ya existe.");
            }

            return new ModelAndView(attributes,"add_edit_student.ftl");

        },new FreeMarkerEngine());

        get("/edit/:matricula",(request, response) -> {
            Map<String, Object> attributes = new HashMap<String, Object>();
            String matricula = request.params("matricula");
            ArrayList<Student> students = studentManager.getStudents(matricula);
            if(students.size() != 0) {
                attributes.put("student",students.get(0));
            }
            else if (students.size() == 0){
                response.redirect("/");
            }

            attributes.put("is_edit",true);
            return new ModelAndView(attributes,"add_edit_student.ftl");
        }, new FreeMarkerEngine());

        post("/edit/:matricula",(request, response) -> {
            Map<String, Object> attributes = new HashMap<String, Object>();
            Student student = createStudentFromForm(request);
            if(studentManager.saveStudent(student,request.params("matricula"))){
                response.redirect("/");
            }

            attributes.put("student",student);
            attributes.put("message","Error agregando estudiante. Probablemente la matricula ya existe.");
            attributes.put("is_edit",true);
            return new ModelAndView(attributes,"add_edit_student.ftl");
        },new FreeMarkerEngine());

        get("/delete/:matricula",(request, response) -> {
            String mat = request.params("matricula");
            if(studentManager.delete(mat)){
                response.redirect("/?delete=success");
            }
            else{
                response.redirect("/?delete=failure");
            }
            return new ModelAndView(null,"students.ftl");
        }, new FreeMarkerEngine());
    }
}
