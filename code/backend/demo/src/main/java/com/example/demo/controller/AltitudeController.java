package com.example.demo.controller;

import javax.validation.Valid;
import com.example.demo.message.request.AltitudeForm;
import com.example.demo.service.AltitudeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Zhe Li
 * @date 2020/05/10
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/altitude")
@Api(tags = "与用户姿态识别相关的控制类")
public class AltitudeController {

    @Autowired
    AltitudeService altitudeService;

    @ApiOperation(value = "俯卧撑的姿态识别与纠正", notes = "前端提供六个关键点的坐标")
    @ApiResponses({
            @ApiResponse(code = 200, message = "sign in successfully")
    })
    @PostMapping("/correct")
    public String altitudeCorrect(@RequestBody AltitudeForm altitudeForm) {
        return altitudeService.altitudeCorrect(altitudeForm);
    }
}
