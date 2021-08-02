package com.example.ticketvalidator;

public class TicketEvent {
    private String id;
    private String name;
    private String date;
    private String total;
    private String tickets;


    public TicketEvent(){

    }


    public TicketEvent(String id, String name, String date, String total, String tickets) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.total = total;
        this.tickets = tickets;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTickets() {
        return tickets;
    }

    public void setTickets(String tickets) {
        this.tickets = tickets;
    }





}
