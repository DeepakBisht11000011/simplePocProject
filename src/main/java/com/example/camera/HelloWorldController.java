package com.example.camera;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.camera.entity.StudentEntity;

@Controller
public class HelloWorldController {

    @GetMapping(value="/")
    public String getDefaultPage(){return "redirect:/hello-world";}
    @GetMapping(value = "/hello-world")
    public String getHelloWorldPage(){
        return "helloWorld";
    }
    
    @PostMapping(value="/newEntity")
    @ResponseBody
    public String uploadStudent(
    		@RequestBody StudentEntity entity, @RequestParam(required = false) String id) {
    	System.out.println(id);
    	return "";
    }
    
    @PostMapping(value="/newEntity/v1")
    @ResponseBody
    public String uploadStudentv1(
    		@RequestParam final String name, @RequestParam final String profession, 
    		@RequestParam String id
    		) {
    	System.out.println(name+","+id+","+profession);
    	return "";
    }
}
