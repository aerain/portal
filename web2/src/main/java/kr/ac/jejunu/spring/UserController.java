package kr.ac.jejunu.spring;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;

@Controller
@RequestMapping("/user")
@SessionAttributes("user")
public class UserController {

    @RequestMapping(value = "/get")
    public ModelAndView get(){
        User user = User.builder().id(1).name("hulk").password("hello").build();
        ModelAndView modelAndView = new ModelAndView("user");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @GetMapping
    public User sattr() {
        User user = User.builder().id(1).name("바보").password("11111").build();
        return user;
    }

//    @RequestMapping("/gmod")
//    public String model(@ModelAttribute User user){
//        user.setName("ha");
//        user.setId(4);
//
//        return "user";
//    }

    @RequestMapping("/user")
    public String model(User user) { return "user"; }

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

    @GetMapping("/session")
    public String session(HttpSession session) {
        User user = User.builder().id(10).name("session").password("1234123").build();
        session.setAttribute("user", user);
        return "redirect:/user/getSession";
    }

    @GetMapping("/getSession")
    public ModelAndView getSession(HttpSession session) {
        User user = (User) session.getAttribute("user");
        ModelAndView modelAndView = new ModelAndView("user");
        modelAndView.addObject("user", user);
        return modelAndView;
    }
}