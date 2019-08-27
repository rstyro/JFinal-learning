package top.lrshuai.jfinal.controller;

import com.jfinal.core.Controller;

public class HelloController extends Controller {

    public void hi(){
        renderText("Hello JFinal");
    }

    public void index(){
        try {
            System.out.println("??????");
            render("index.html");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
