package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    //맵어플리케이션에서 /hello라고 들어오면 이 메소드를 호출해준다
    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data", "hello!!");
        //templates폴더에 있는 hello.html을 찾아서 동작
        // 즉 view 이름을 반환한것
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value="name") String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }
    
    @GetMapping("hello-string")
    //html에 나오는 바디태그가 아닌 
    //http의 바디부에 이 데이터를 직접 넣어주겠다 => name이 spring이라면 "hello spring"이 간다
    @ResponseBody
    public String helloString(@RequestParam("name") String name){
        return "hello " + name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    //Hello라는 객체
    public Hello helloApi(@RequestParam("name") String name){
        //ResponseBody했을때 그냥 문자에서는 문자를 바로 보내주면 됐지만
        //객체가 오게 되면
        // 기본적으로 근야 json방식으로 데이터를 만들어서 http응답에 반환 하겠다.
        // 넘어온 Hello객체를 보고 컨버터에서 객체를 json의 스타일로 변경하여서 요청한 곳으로 응답답        Hello hello = new Hello();
        Hello hello = new Hello();

        hello.setName(name);
        return hello;

    }

    //static클래스로 만들면 HelloController클래스 안에서 static클래스를 또 사용가능
    static class Hello{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
