package ee4216.backend.controllers;

import ee4216.backend.classes.User;
import java.io.IOException;
import java.security.Principal;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.CookieValue;
 
@Controller
public class WebController {
    
    @Autowired
    JdbcTemplate jdbcTemplate;
   
    @RequestMapping(value="/")
    public String home(Principal principal, HttpServletResponse response){
        
        System.out.println(principal.getName());
        response.addCookie(new Cookie("username", principal.getName()));
        
        return "home";
    }
   
    @RequestMapping(value="/user")
    public String user(){
        return "user";
    }
   
    @RequestMapping(value="/login")
    public String login(){
        return "login";
    }
   
    @RequestMapping(value="/403")
    public String Error403(){
        return "403";
    }
    
    /*
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public void login(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            HttpServletResponse response) throws IOException {

        User user = new User();

        user = jdbcTemplate.queryForObject(
            "SELECT username, password, enabled FROM users WHERE username = '" + username + "'",
            (rs, rowNum) -> new User(rs.getString("username"), rs.getString("password"), rs.getBoolean("enabled")));

        System.out.println("User: " + user.toString());

        if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
            //return true;
            response.sendRedirect("/home");
        } else {
            //return false;
            response.sendRedirect("/login");
        }
    }
*/
    
}
