package com.tool.controller;

import com.tool.service.weekly.GenWeeklyData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(value = "/report")
public class ReportController {

    private final GenWeeklyData genWeeklyData;

    @Autowired
    public ReportController(GenWeeklyData genWeeklyData) {
        this.genWeeklyData = genWeeklyData;
    }

    @GetMapping("/weekly/{id}")
    public String exportWeeklyReport(@PathVariable String id) throws IOException {
        return genWeeklyData.execute(id);
    }
}
