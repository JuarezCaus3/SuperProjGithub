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
    private int hora;
    private int week;
    private int subject_code;
    private long manager_code;
    private boolean status;

    public EventDTO() {
    }

    public EventDTO(int id, String name, String room, Date date, int hora, int week, int subject_code, long manager_code, boolean status) {
        this.id = id;
        this.name = name;
        this.room = room;
        this.date = date;
        this.hora = hora;
        this.week = week;
        this.subject_code = subject_code;
        this.manager_code = manager_code;
        this.status = status;
    }
    
    public void reset() {
        setId(1);
        setName(null);
        setRoom(null);
        setDate(null);
        setHora(1);
        setWeek(1);
        setStatus(false);
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

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int getSubject() {
        return subject_code;
    }

    public void setSubject(int subject_code) {
        this.subject_code = subject_code;
    }

    public long getManager() {
        return manager_code;
    }

    public void setManager(long manager_code) {
        this.manager_code = manager_code;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
  
}