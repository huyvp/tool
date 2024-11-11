package com.tool.controller;

import com.tool.dto.TabReq;
import com.tool.service.tab.TabService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/config/tab")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TabController {
    TabService tabService;

    @PostMapping
    public ResponseEntity<?> tab(@RequestBody @Valid TabReq tab) {
        return ResponseEntity.ok(
                tabService.createTab(tab)
        );
    }

    @GetMapping
    public ResponseEntity<?> getTabs() {
        return ResponseEntity.ok(
                tabService.getTabs()
        );
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<?> updateTab(@RequestBody TabReq tabReq, @PathVariable String id) {
        return ResponseEntity.ok(
                tabService.updateTab(tabReq, id)
        );
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<?> deleteTab(@PathVariable String id) {
        tabService.deleteTab(id);
        return ResponseEntity.ok("Delete success");
    }
}
