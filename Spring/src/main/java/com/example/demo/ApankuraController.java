package com.example.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.net.NetworkInterface.getNetworkInterfaces;

@RestController()
@RequestMapping("/apankura")
public class ApankuraController {

    @Autowired
    private Environment env;

    @RequestMapping("/info")
    public Map<String, String> Information() throws SocketException {
        HashMap<String, String> map = new HashMap<String,String>();
        Enumeration e = getNetworkInterfaces();
        StringBuilder builder = new StringBuilder();
        while(e.hasMoreElements())
        {
            NetworkInterface n = (NetworkInterface) e.nextElement();
            Enumeration ee = n.getInetAddresses();
            while (ee.hasMoreElements())
            {
                InetAddress i = (InetAddress) ee.nextElement();
                builder.append(i.getHostAddress()).append(";");
            }
        }
        map.put( "netowrk", builder.toString());
        return map;
    }

    @RequestMapping("/liveness")
    public Map<String, String> Liveness() throws SocketException {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        HashMap<String, String> map = new HashMap<>();
        map.put("date", formatter.format(date));
        return map;
    }
    /*
    Must return 200 if the api is ready to service request
    */
    @RequestMapping("/readiness")
    public ResponseEntity<String> Readiness() throws SocketException {
        String jdbcClassName = this.env.getProperty("spring.datasource.driverClassName");
        String url = this.env.getProperty("spring.datasource.url");
        String user = this.env.getProperty("spring.datasource.username");
        String password = this.env.getProperty("spring.datasource.password");
        Connection connection = null;
        try {
            Class.forName(jdbcClassName);
            connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } finally{
            if(connection!=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        }
        return  new ResponseEntity<>(jdbcClassName, HttpStatus.OK);
    }

    

    @RequestMapping("/session/all")
    public Map<String, String> AllSession(HttpSession session) {
        HashMap<String, String> map = new HashMap<>();
        for (String key: Collections.list( session.getAttributeNames() ) ){
            map.put(key, (String)session.getAttribute(key));
        }
        return map;
    }

    @RequestMapping("/session")
    public Map<String, String> GetSession(HttpSession session,
                                          @RequestParam String key) {
        HashMap<String, String> map = new HashMap<>();
        map.put(key, (String)session.getAttribute(key));
        return map;
    }

    @PostMapping("/session")
    public Map<String, String> PostSession(HttpSession session,
                                           @RequestParam String key,
                                           @RequestParam String data) {
        session.setAttribute(key, data);
        HashMap<String, String> map = new HashMap<>();
        map.put(key, data);
        return map;
    }

    @PutMapping("/session")
    public Map<String, String> PutSession(HttpSession session,
                                           @RequestParam String key,
                                           @RequestParam String data) {
        session.setAttribute(key, data);
        HashMap<String, String> map = new HashMap<>();
        map.put(key, data);
        return map;
    }

    @DeleteMapping("/session")
    public Map<String, String> DeleteSession(HttpSession session,
                                          @RequestParam String key) {
        session.removeAttribute(key);
        HashMap<String, String> map = new HashMap<>();
        return map;
    }

    @DeleteMapping("/session/all")
    public Map<String, String> DeleteAllSession(HttpSession session) {
        for (String key: Collections.list( session.getAttributeNames() ) ){
            session.removeAttribute(key);
        }
        HashMap<String, String> map = new HashMap<>();
        return map;
    }
    @DeleteMapping("/session/invalidate")
    public Map<String, String> Invalidate(HttpSession session) {
        session.invalidate();
        HashMap<String, String> map = new HashMap<>();
        return map;
    }
}
