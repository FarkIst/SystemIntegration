/*
 * To change this license header, choose License Headers in Project Properties.
package rmiclient;

/**
 * This is a simple client program that uses methods as a service from another,
 * server program.
 *
 * There are two services provided by the server and consumed by this client: -
 * calculation - date/time info
 *
 * The client gets input from the console: two operands/numbers and the
 * operator/character Then the client builds a stub, which searches for a
 * service and connects with the server for getting it Finally, the client
 * prints out the results on the console
 *
 * @author Dora Di
 */
package rmiClient;
import rmiserver.RMIInterface;
import java.rmi.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import rmiserver.*;

public class RMIClient {

    

    public static void getService(String param) throws Exception {
        // name =  rmi:// + ServerIP +  /EngineName;
        String remoteEngine = "rmi://localhost/"+param;

        // Create local stub, lookup in the registry searching for the remote engine - the interface with the methods we want to use remotely
        RMIInterface obj = (RMIInterface) Naming.lookup(remoteEngine);

        // Send requests to the remote services the usual way, as if they are local
        List<User> result = obj.getUsers();
        
        printout(result);
    }

    public static void printout(List<User> users) {
        // Print the results on the Client console
        for(User u : users){
            System.out.println(u.toString());
        }
    }

    public static void main(String[] args) {
        try {
            getService("Text");
            getService("DB");
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }
}
