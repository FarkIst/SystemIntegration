/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.farki.tcpmessageencoder;

import javafx.concurrent.Task;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 *
 * @author IF
 */
public class TCPServer {
    public static final int PORT = 6666;
    public static ServerSocket serverSocket = null; // Server gets found
    public static Socket openSocket = null;         // Server communicates with the client

    public static ServerSocket configureServer() throws UnknownHostException, IOException {
        // get server's own IP address
        String serverIP = InetAddress.getLocalHost().getHostAddress();
        System.out.println("Server ip: " + serverIP);

        // create a socket at the predefined port
        serverSocket = new ServerSocket(PORT);

        return serverSocket;
    }


    public static void main(String[] args) throws IOException {
        // ONLY configure the server once, so that you wont try to bind an occupied Address
        ServerSocket ss = configureServer();
        ExecutorService pool =  Executors.newFixedThreadPool(2);
        while(true) {
            pool.execute(new ServerClientHandler(ss.accept())); //Accept the socket, when a thread is being executed(for connection establishment)
        }
    }



static class ServerClientHandler implements Runnable {
        private static Socket openSocket;
    public ServerClientHandler(Socket openSocket) {
        this.openSocket = openSocket;
    }

    public void connectClient() throws IOException {
        String request, response;

        // two I/O streams attached to the server socket:
        Scanner in;         // Scanner is the incoming stream (requests from a client)
        PrintWriter out;    // PrintWriter is the outcoming stream (the response of the server)
        in = new Scanner(openSocket.getInputStream());
        out = new PrintWriter(openSocket.getOutputStream(), true);
        // Parameter true ensures automatic flushing of the output buffer

        // Server keeps listening for request and reading data from the Client,
        // until the Client sends "stop" requests
        while (in.hasNextLine()) {
            System.out.println("inside connectClient");
            request = in.nextLine();

            if (request.equals("stop")) {
                out.println("Good bye, client!");
                System.out.println("Log: " + request + " client!");
                break;
            } else {
                // server responses
                response = new StringBuilder(request).reverse().toString();
                out.println(response);
                // Log response on the server's console, too
                System.out.println("Log: " + response);
            }
        }
    }
    @Override
    public void run() {
        try {
            connectClient();
        } catch (Exception e) {
            System.out.println(" Connection fails: " + e);
        } finally {
            try {
                openSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Connection to client closed");
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Server port closed");
        }
    }
}
}
