package com.atom.jobseeker.rent.controller;

import com.atom.jobseeker.rent.pojo.House;
import com.atom.jobseeker.rent.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author SunLei
 */
@RestController
@RequestMapping("/rent/house")
public class HouseController {
    @Autowired
    HouseService houseService;


}
