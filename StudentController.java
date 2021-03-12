package com.oracle.controller;

import com.oracle.domain.Student;
import com.oracle.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Resource
    private StudentService service;

//    注册学生
    @RequestMapping("/addStudent.do")
    public ModelAndView addStudent(Student student){
        String tips = "注册失败";
        ModelAndView mv = new ModelAndView();
//      调用service处理student
        int i = service.addStudent(student);
        if (i>0){
            //注册成功
            tips = "学生【"+student.getName()+"】注册成功";
        }
        mv.addObject("tips",tips);
        mv.setViewName("result");
        return mv;
    }


//    处理查询，响应ajax
//    1.是否有jackson的依赖
//    2.springmvc加注解驱动
//    3.方法
    @RequestMapping("/querystudent.do")
    @ResponseBody
    public List<Student> queryStudent(){
        List<Student> students = service.findStudents();
        for (Student student : students) {
            System.out.println(student);

        }
        return students;
    }



}
