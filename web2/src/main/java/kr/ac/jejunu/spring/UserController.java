package kr.ac.jejunu.spring;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.*;

@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/get")
    public ModelAndView get(){
        User user = User.builder().id(1).name("hulk").password("hello").build();
        ModelAndView modelAndView = new ModelAndView("user");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @RequestMapping("/gmod")
    public String model(@ModelAttribute User user){
        user.setName("ha");
        user.setId(4);

        return "user";
    }


    @RequestMapping(value="/upload", method= RequestMethod.GET)
    public String uploadPage() {
       return "upload";
    }


    @RequestMapping(value="/upload", method= RequestMethod.POST)
    public ModelAndView uploadPage(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        File path = new File(request.getServletContext().getRealPath("/") + "/WEB-INF/static/" + file.getOriginalFilename());

        FileOutputStream outputStream = new FileOutputStream(path);
        // 루프방지를 위해 버퍼에서 넣어놓은뒤 실행
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
        bufferedOutputStream.write(file.getBytes());
        bufferedOutputStream.close();
        ModelAndView modelAndView = new ModelAndView("upload");
        modelAndView.addObject("url", "/images/" + file.getOriginalFilename());
        return modelAndView;
    }
    @RequestMapping("/error")
    public void error(){
        throw new RuntimeException("runtie exception");
    };

    @ExceptionHandler(Exception.class)
    public ModelAndView errorPage(Exception e ) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("e", "e");

        return modelAndView;
    }


}