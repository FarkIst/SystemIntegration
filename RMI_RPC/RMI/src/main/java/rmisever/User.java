/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmisever;

import java.io.Serializable;

/**
 *
 * @author IF
 */
public class User implements Serializable {
    
    private String name,email;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "name="+name + ", email=" + email + '\n';
    }
    
}
