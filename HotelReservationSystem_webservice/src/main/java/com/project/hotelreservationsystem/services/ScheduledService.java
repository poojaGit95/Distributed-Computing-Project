package com.project.hotelreservationsystem.services;

import java.util.TimerTask;

public class ScheduledService extends TimerTask {

    @Override
    public void run() {
        System.out.println("Task is running");
    }
}


