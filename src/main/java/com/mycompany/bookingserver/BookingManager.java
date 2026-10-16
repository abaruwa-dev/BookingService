/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookingserver;
import java.util.ArrayList;
/**
 *
 * @author Alexb
 */
public class BookingManager {
    
    
    // custom exception 
    public static class InvalidCommandException extends Exception {
        
    public InvalidCommandException(String message) {
        super(message);
    }
    
    }
    
    //store bookings
    private ArrayList<Booking> bookings = new ArrayList<>();
    
    
    // Create a Booking with details
    public synchronized String addBooking(Booking b){
        
        for (Booking existing : bookings) {
        if (existing.getMember().equals(b.getMember()) &&
            existing.getWorkshop().equals(b.getWorkshop()) &&
            existing.getDate().equals(b.getDate()) &&
            existing.getTime().equals(b.getTime())) {

            return "Duplicate booking found.";
        }
    }
        
        bookings.add(b);
        return "Booking Added Was Successful " + listBookings(b.getMember());
        
    }
    
    // Cancel a Booking with Member
    public synchronized String cancelBooking(String member, String workshop, String date, String time){
        for(Booking b : bookings){
            if(b.getMember().equals(member) )
                if(b.getWorkshop().equals(workshop))
                     if(b.getDate().equals(date))
                         if(b.getTime().equals(time)){
                   
                             bookings.remove(b);
                             return "Booking Cancled Successfuly " + listBookings(b.getMember());
        }
            }
                   return "Booking not found" ;
        }
    
    
    // List All Bookings for Members 
    public synchronized String listBookings(String member){
           
       String listBookings = "";
        
       for(Booking b : bookings){  
           if(b.getMember().equals(member)){
                 listBookings += b.getDetails()+" ; ";  
           }
            
            }
                // if theres no results return no Bookings
                if (listBookings.isEmpty()) {
                    return member + " has no bookings.";
            }
           
       

           return listBookings;
    }
    

    
    
    
    
    
    
    
}
