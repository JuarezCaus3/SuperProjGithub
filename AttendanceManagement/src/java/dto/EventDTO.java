/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.util.Date;

/**
 *
 * @author David
 */
public class EventDTO {
    
    private int id;
    
    private String name;
    private String room;
    private Date date;
    private int hour;
    private int week;

    public EventDTO() {
    }

    public EventDTO(int id, String name, String room, Date date, int hour, int week) {
        this.id = id;
        this.name = name;
        this.room = room;
        this.date = date;
        this.hour = hour;
        this.week = week;
    }
    
    public void reset() {
        setId(1);
        setName(null);
        setRoom(null);
        setDate(null);
        setHour(-1);
        setWeek(-1);
    }   
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }
}