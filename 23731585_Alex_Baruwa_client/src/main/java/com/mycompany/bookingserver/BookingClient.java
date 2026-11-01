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
import java.net.URL;
import java.net.URLConnection;


/**
 *
 * @author Alexb
 */
public class BookingClient {
    
        // import; https://raw.githubusercontent.com/abaruwa-dev/BookingService/refs/heads/main/booking.txt
        private static void importBookings(String address, BufferedReader in, PrintWriter out) {
            
            
                BufferedReader fileIn = null;
        try {
             URL url = new URL(address);
             URLConnection connection = url.openConnection();

             fileIn = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    
                String firstLine = fileIn.readLine(); // member=Alex
                String[] parts = firstLine.split("=", 2);
                String member = parts[1].trim();     // Alex

                String line;
                int imported = 0;
                int skipped = 0;
             
                // Send booking to server until reaches end of file
             while ((line = fileIn.readLine()) != null) {
                    if (line.trim().isEmpty()) {
                            continue;
                    }
                 
              String bookingCommand = "book; " + member + "; " + line;
              
                out.println(bookingCommand);
                 String response = in.readLine();
                  System.out.println(response);
                  
                  if (response.startsWith("ERROR:")) {
                       skipped++;
                     } else {
                       imported++;
                     }
             }
                
                  System.out.println("Imported: " + imported + "; Skipped: " + skipped);
             

                  fileIn.close();

            } catch (IOException e) {
                  System.out.println("Import error: " + e.getMessage());
                  
            }finally {
            try{
                fileIn.close();
            }catch(IOException e){
                    System.out.println("Unable to close import file.");
                    }}
        }
    
    
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
                    
                    
                        System.out.println("\n\n\n Commands: book, cancel, list. Type STOP to exit.");
                        System.out.println("Add Booking: Command; member; workshop; date; time");
                        System.out.println("Cancel: cancel; member; workshop; date; time");
                        System.out.println("List: list; member; -; -; -");
            
                    while(true){
                        
                        
                        
                        
                   
                        
                        
                       
                        command = userEntry.readLine();
                        
                        
                        
                        if(command == null){

                            break;
                        }
                        
                        
                            if (command.startsWith("import;")) {
                                
                                String[] parts = command.split(";", 2);
                                 String address = parts[1].trim();

                                importBookings(address, in, out);
                                continue;
                            }
                        
                         out.println(command);
                        
                                String response;
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

                      
                   

