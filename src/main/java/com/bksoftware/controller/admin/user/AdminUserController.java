package com.bksoftware.controller.admin.user;

import com.bksoftware.entities.Record;
import com.bksoftware.entities.user.AppUser;
import com.bksoftware.service.RecordService;
import com.bksoftware.service.user.AppUserService;
import com.bksoftware.service_impl.user.AppUserService_Impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("api/v1/admin/user")
public class AdminUserController {

    @Autowired
    private AppUserService_Impl appUserService;

    @Autowired
    private RecordService recordService;

    @GetMapping
    public ResponseEntity<List<AppUser>> findAllUser(
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size,
            HttpServletResponse response
    ) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        if (page < 1) page = 1;
        if (size < 0) size = 10;
        Pageable pageable = PageRequest.of(page - 1, size);
        List<AppUser> users = appUserService.findAllPage(pageable);
        if (users != null) return new ResponseEntity<>(users, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/size")
    public ResponseEntity<Double> findAllUserSize(
            HttpServletResponse response
    ) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        double pageNumber = recordService.findByName("user").getSize();
        double result = Math.ceil(pageNumber / 10);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/delete")
    public ResponseEntity<Object> deleteUser(@RequestParam("id") int id,
                                             HttpServletResponse response
    ) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        AppUser appUser = appUserService.findById(id);
        if (appUser == null) return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        Record record = recordService.findByName("user");
        record.setSize(record.getSize() - 1);
        boolean result = appUserService.deleteAppUser(appUser);
        if (result) {
            recordService.saveRecord(record);
            return new ResponseEntity<>("delete user fail", HttpStatus.OK);
        }
        return new ResponseEntity<>("delete user fail", HttpStatus.BAD_REQUEST);
    }
}
