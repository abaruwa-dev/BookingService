/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookingserver;

import java.net.Socket;
import java.io.*;

public class BookingHandler implements Runnable {

    private Socket connection;
    BookingManager manager;
    PrintWriter out = null;

    public BookingHandler(Socket clientSocket, BookingManager manager) {
        connection = clientSocket;
        this.manager = manager;
    } // End constructor

    @Override
    public void run() {

        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));

            out = new PrintWriter(connection.getOutputStream(), true);

            String command;
            
            
            // while the command line is not empty it keeps running
            while ((command = in.readLine()) != null) {

                if (command.trim().equalsIgnoreCase("STOP")) {
                    out.println("TERMINATE");
                    break;
                } 

                try {
                    if (command.trim().isEmpty()) {
                        throw new BookingManager.InvalidCommandException("Enter a command");
                    }

                    String[] parts = command.split(";", -1);

                    if (parts.length != 5) {
                        throw new BookingManager.InvalidCommandException(
                                "Expected: action; member; workshop; date; time");
                    }
                    
                    
                    // set values
                    String action = parts[0].trim();
                    String member = parts[1].trim();
                    String workshop = parts[2].trim();
                    String date = parts[3].trim();
                    String time = parts[4].trim();
                    
                    
                    // if any is empty throw exception
                    if (member.isEmpty() || workshop.isEmpty() ||
                        date.isEmpty() || time.isEmpty() || action.isEmpty()) {

                        throw new BookingManager.InvalidCommandException(
                                "All five fields must be filled.");
                    }

                    String response;
                    
                    
                    // if they chose book create booking and response 
                    if (action.equalsIgnoreCase("book")) {
                        Booking b = new Booking(member, workshop, date, time);
                        response = manager.addBooking(b);
                        
                        
                        // if theyu choose cancel , then cancel booking and respond
                    } else if (action.equalsIgnoreCase("cancel")) {
                        response = manager.cancelBooking(
                                member, workshop, date, time);
                        
                        
                        // display list
                    } else if (action.equalsIgnoreCase("list")) {
                        
                        
                        // if they didnt fill in fields with - - - then wont work becausse the array list must have 5 fields filled 
                        if (!workshop.equals("-") ||
                            !date.equals("-") ||
                            !time.equals("-")) {

                            throw new BookingManager.InvalidCommandException(
                                    "Expected: list; member; -; -; -");
                        }

                        response = manager.listBookings(member);

                    } else {
                        throw new BookingManager.InvalidCommandException(
                                "Unknown command. Use book, cancel or list.");
                    } 

                    out.println(response);

                } catch (BookingManager.InvalidCommandException e) {
                    out.println("ERROR: " + e.getMessage());
                } 

            } 

        } catch (IOException e) {
            System.out.println("Connection error: " + e.getMessage());
            
            
            // close connection lastly 
        } finally {
            try {
                connection.close();
            } catch (IOException e) {
                System.out.println(
                        "Unable to close connection: " + e.getMessage());
            }
        } 

    } 

} 

