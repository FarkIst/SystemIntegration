/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmisever;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.stream.FileImageInputStream;

/**
 *
 * @author IF
 */
public class TextRMIService extends UnicastRemoteObject implements RMIInterface {
    
    @Override
    public List<User> getUsers() throws RemoteException {
        try {
            BufferedReader bf = new BufferedReader(new FileReader("users.txt"));
            String str;
            List<User> users = new ArrayList();
            while((str = bf.readLine()) != null) {
                String[] parts = str.split(",");
                users.add(new User(parts[0],parts[1]));
                
            }
            return users;
        } catch (IOException ex) {
            Logger.getLogger(TextRMIService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    
}
