/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ee4216.backend.classes;

/**
 *
 * @author alvaro
 */
public class User {
    
    private final String username;
    private final String password;
    private final Boolean enabled;

    public User() {
        this.username = "";
        this.password = "";
        this.enabled = null;
    }

    public User(String username, String password, Boolean enabled) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    @Override
    public String toString() {
        return "User{" + "username=" + username + ", password=" + password + ", enabled=" + enabled + '}';
    }
    
}
