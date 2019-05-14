package kr.ac.jejunu.spring;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/annotation")
public class AnnotationController {
    @RequestMapping
    public ModelAndView hello() {
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("title", "인덱스");
        mav.addObject("hello", "안녕하세요");
        return mav;
    }
}
