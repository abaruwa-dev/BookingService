/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.bookingserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;


/**
 *
 * @author Alexb
 */
public class BookingClient {
    
    
    private static final String HOST = "localhost";
    private static final int PORT = 5003;
  
    public static void main(String[] args){
        
             Socket link = null;
            
             
             try{
                 
             link = new Socket(HOST, PORT);
        
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(link.getInputStream()));

            PrintWriter out = new PrintWriter(
                    link.getOutputStream(), true);
    
            
            // Set up a separate reader for keyboard input.
            BufferedReader userEntry = new BufferedReader(
                    new InputStreamReader(System.in));
            
                    String command = null;
            
                    while(true){
                        
                        
                        System.out.println("\n\n\n Commands: book, cancel, list. Type STOP to exit.");
                        System.out.println("Add Booking: Command; member; workshop; date; time");
                        System.out.println("Cancel: cancel; member; workshop; date; time");
                        System.out.println("List: list; member; -; -; -");
                        command = userEntry.readLine();
                        
                        
                        
                        if(command == null){

                            break;
                        }
                        
                        out.println(command);
                        
                        String response = null;
                        response = in.readLine();
                        
                        if(response == null){
                            
                            break; 
                        }
                        
                        
                        System.out.println(response);
                        
                        if (response.equals("TERMINATE")) {
                              break;
                        }  }
                    
                    
                 }catch (IOException e) {
                         System.out.println("System error: " + e.getMessage());

                 } finally {
                      if (link != null) {
                 try {
                        link.close();
                 } catch (IOException e) {
                        System.out.println("Unable to close: " + e.getMessage());
                 }
              }
            } 
        } }

                      
                   

