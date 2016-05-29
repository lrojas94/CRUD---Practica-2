/**
 * Created by luis on 5/29/16.
 */
import freemarker.core.FreeMarkerTree;

import  freemarker.core.*;
import static spark.Spark.get;
import static spark.Spark.staticFiles;

import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.HashMap;
import java.util.Map;


public class Main {
    public static void main(String[] args) {
        staticFiles.location("./public");

        get("/", (request,response)->{
            Map<String, Object> attributes = new HashMap<String, Object>();
            attributes.put("message", "Hello World!");

            System.out.println("Called this");
            return new ModelAndView(attributes,"index.ftl");
        }, new FreeMarkerEngine());
    }
}
