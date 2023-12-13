package com.svi.group5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.svi.group5.service.impl.TimetableServiceImpl;

@Controller
public class DevAppointmentController {
    
    @Autowired
    TimetableServiceImpl ttsi;
    
    @PostMapping("/ensureschedule")
    public ResponseEntity<String> ensureSchedule() {
        ttsi.ensureThisWeekSchedule();
        return ResponseEntity.ok("successfully created appointments for this week");
    }
}
