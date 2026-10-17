/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.bookingserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Alexb
 */
public class BookingServer {
    
    private static ServerSocket serverSocket;
    private static final int PORT = 5003;
    private static int clientConnections = 0;
    
    public static void main(String[] args) {
        
        System.out.println("Opening port...");

        BookingManager manager = new BookingManager();
        
        try {
            while (true) {
        // Wait for the next client.
        Socket link = serverSocket.accept();
        clientConnections++;

        // Give each client own BookingHandler using the same manager.
        BookingHandler job = new BookingHandler(link, manager);
        Thread worker = new Thread(job);
        worker.start();
        
        clientConnections++;
    }
            
        }catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
            return;
        }
        
    }
}
