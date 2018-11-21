package ee4216.backend.controllers;

import com.google.gson.JsonObject;
import ee4216.backend.classes.Route;
import ee4216.backend.classes.User;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;
import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author alvaro
 */
@RestController
@RequestMapping("/api")
public class RestApiController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/users")
    @ResponseBody
    public List getUsers() {

        List users = new ArrayList(); 

        jdbcTemplate.query(
                "SELECT username, password, enabled FROM users",
                (rs, rowNum) -> new User(rs.getString("username"), rs.getString("password"), rs.getBoolean("enabled"))
        ).forEach(user -> users.add(user));

        return users;
    }

    @GetMapping("/getPhoto")
    @ResponseBody
    public String getPhoto(
            @RequestParam(value = "latitude") String latitude,
            @RequestParam(value = "longitude") String longitude
    ) throws MalformedURLException, IOException {

        HttpURLConnection connection = null;
        String inputLine;
        StringBuffer response = new StringBuffer();
       
        try {
            URL url = new URL("https://api.flickr.com/services/rest/?method=flickr.photos.search"
                    + "&api_key=8856b3f057a21cb464b3a34f65e0cee8"
                    + "&accuracy=14"
                    + "&content_type=1"
                    + "&lat=" + latitude
                    + "&lon=" + longitude
                    + "&radius=3"
                    + "&extras=url_m"
                    + "&per_page=1"
                    + "&page=1"
                    + "&format=json"
                    + "&nojsoncallback=1");
            
            // Make connection
            connection = (HttpURLConnection) url.openConnection();
            // Set request type as HTTP GET
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setUseCaches(false);
            connection.setDoOutput(true);
            if (connection.getResponseCode() == 200) {
                // get response stream
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(connection.getInputStream()));
                // feed response into the StringBuilder
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                // Start parsing
                JSONObject obj = new JSONObject(response.toString());
                System.out.println("Obj: " + obj);
                
                // get Array type
                JSONObject results = obj.getJSONObject(("photos"));
                
                // under results, get string type
                JSONArray array = results.getJSONArray("photo");
                
                String urlPhoto =  array.getJSONObject(0).getString("url_m");
                System.out.println("Photo url: " + urlPhoto);
                return urlPhoto;
            } else {
                System.out.println("Can't get data");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return "";
    }
    
    @GetMapping("/getRoute")
    @ResponseBody
    public List getBestRouteName(@RequestParam(value = "username") String username) {

        List routes = new ArrayList(); 
        
        jdbcTemplate.query(
                "SELECT origin, originAddress, nameAddress1, address1,"
                        + "nameAddress2, address2,"
                        + "nameAddress3, address3,"
                        + "nameAddress4, address4"
                        + " FROM route WHERE username= ?",
                (rs, rowNum) -> new Route(rs.getString("origin"), rs.getString("originAddress"), 
                rs.getString("nameAddress1"), rs.getString("address1"),
                rs.getString("nameAddress2"), rs.getString("address2"),
                rs.getString("nameAddress3"), rs.getString("address3"),
                rs.getString("nameAddress4"), rs.getString("address4")),
                username
        ).forEach(route -> routes.add(route));
        
        return routes;
    }
    
    @PutMapping("/saveRoute")
    @ResponseBody
    public int getBestRouteName(
            @RequestParam(value = "username") String username,
            @RequestParam(value = "originAddress") String originAddress,
            @RequestParam(value = "address1") String address1,
            @RequestParam(value = "address2") String address2,
            @RequestParam(value = "address3") String address3,
            @RequestParam(value = "address4") String address4,
            @RequestParam(value = "origin") String origin,
            @RequestParam(value = "nameAddress1") String nameAddress1,
            @RequestParam(value = "nameAddress2") String nameAddress2,
            @RequestParam(value = "nameAddress3") String nameAddress3,
            @RequestParam(value = "nameAddress4") String nameAddress4){

        int completed = -1;
        
        completed = jdbcTemplate.update("INSERT INTO route "
                + "(username, origin, originAddress, "
                + "nameAddress1, address1, "
                + "nameAddress2, address2,"
                + "nameAddress3, address3, "
                + "nameAddress4, address4)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                username,
                origin,
                originAddress,
                nameAddress1,
                address1,
                nameAddress2,
                address2,
                nameAddress3,
                address3,
                nameAddress4,
                address4);
        
        if(completed==1){
            System.out.println("Route saved on database"); 
        } else if(completed==0) {
            System.out.println("Couldn't save route");
        }
        return completed;
    }
    
    @DeleteMapping("/deleteRoute")
    @ResponseBody
    public int deleteRoute(
            @RequestParam(value = "username") String username,
            @RequestParam(value = "origin") String origin,
            @RequestParam(value = "nameAddress1") String nameAddress1,
            @RequestParam(value = "nameAddress2") String nameAddress2,
            @RequestParam(value = "nameAddress3") String nameAddress3,
            @RequestParam(value = "nameAddress4") String nameAddress4){

        int completed = -1;
        
        completed = jdbcTemplate.update("DELETE FROM route "
                + "WHERE username=? AND origin=? AND nameAddress1 = ? AND nameAddress2 = ?"
                + "AND nameAddress3 = ? "
                + "AND nameAddress4 = ? ",
                username,
                origin,
                nameAddress1,
                nameAddress2,
                nameAddress3,
                nameAddress4);
        
        if(completed==1){
            System.out.println("DELETED route"); 
        } else if(completed==0) {
            System.out.println("Couldn't delete route");
        }
        return completed;
    }
    
}
