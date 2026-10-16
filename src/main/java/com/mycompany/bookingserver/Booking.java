/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookingserver;

/**
 *
 * @author Alexb
 */
public class Booking {
        
     private String member;
     private String workshop;
     private String date ;
     private String time ;
    
    
    
    
    public Booking(String member,String workshop,String date,String time){
        this.member = member;
        this.workshop = workshop;
        this.date = date;
        this.time = time;
      
        }
        
            public String getMember(){
               return member;
  
            }
            
            public String getWorkshop(){
               return workshop;
  
            }
            
            public String getTime(){
               return time;
  
            }
            
            public String getDate(){
               return date;
  
            }
            
            public String getDetails(){
                return "MEMBER = " + member
                      + ", WORKSHOP = " + workshop
                      + ", DATE = " + date
                      + ", TIME = " + time;
            }
}
