package com.example.demo.controller;

import com.example.demo.message.response.EnergyReport;
import com.example.demo.security.services.UserPrinciple;
import com.example.demo.service.EnergyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Zhe Li
 * @date 2020/05/10
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/energy")
@Api(tags = "与用户运动量管理相关的控制类")
public class EnergyController {

    private final EnergyService energyService;

    @Autowired
    public EnergyController(EnergyService energyService) {
        this.energyService = energyService;
    }

    @ApiOperation(value = "获取今日的能量报告")
    @ApiResponses({
            @ApiResponse(code = 200, message = "sign in successfully")
    })
    @GetMapping("/report")
    public EnergyReport altitudeCorrect(@AuthenticationPrincipal UserPrinciple userPrinciple) {
        return energyService.getEnergyReport(userPrinciple.getId());
    }
}
