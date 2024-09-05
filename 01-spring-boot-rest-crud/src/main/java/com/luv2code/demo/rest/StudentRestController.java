package com.luv2code.demo.rest;

import com.luv2code.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {
    private List<Student>thestudents;
    //define postconstruct to load the student data only once
    @PostConstruct
    public void LoadData(){

        thestudents = new ArrayList<>();
        thestudents.add(new Student("Ahmed","Ibrahim"));
        thestudents.add(new Student("Ali","Ibrahim"));
        thestudents.add(new Student("Mohamed","Ibrahim"));





    }

    //define endpoint for return list of students
    @GetMapping("/students")
    public List<Student>  getStudent() {

        return thestudents;
    }
    //endpoint return single student
    @GetMapping("/student/{studentid}")
    public Student getStudent(@PathVariable int studentid) {
        //check the studen against lis size
        if (studentid < 0 || studentid >= thestudents.size()){
            throw new StudentNotFoundException("Studen id Not found : "+studentid);
        }
        return thestudents.get(studentid);

    }
    // Add an Exception Handelr Using @ExcepyionHandelr
    @ExceptionHandler()
    public ResponseEntity<StudentErrorClass> handleException(StudentNotFoundException ex){
        //Create StudentErrorResponse
        StudentErrorClass error = new StudentErrorClass();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(ex.getMessage());
        error.setTimestamp((int) System.currentTimeMillis());
        //return ResponseEntity
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);


    }
    //add another exception handelr to catch all exception
    @ExceptionHandler
    public ResponseEntity<StudentErrorClass> handleException(Exception ex){
        //Create StudentErrorResponse
        StudentErrorClass error = new StudentErrorClass();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage("Bad request");
        error.setTimestamp((int) System.currentTimeMillis());
        //return ResponseEntity
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);

    }


}
