package life.majiang.community2.controller;

import life.majiang.community2.mapper.UserMapper;
import life.majiang.community2.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
    @Autowired
    private UserMapper userMapper;
    @GetMapping("/") //the root fold, default represent
    public String index(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        try {
            for (Cookie cookie:cookies){
                if (cookie.getName().equals("token")){
                    String token = cookie.getValue();
                    User user  =  userMapper.findByToken(token);
                    if (user!=null){
                        request.getSession().setAttribute("user", user);

                    }
                    break;
                }
            }
        }catch (java.lang.NullPointerException e){
            // do nothing
        }finally {
            return "index";
        }
    }
}
