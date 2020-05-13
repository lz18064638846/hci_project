package com.example.demo.serviceimpl;

import com.example.demo.message.request.AltitudeForm;
import com.example.demo.service.AltitudeService;
import com.example.demo.util.Point;
import org.springframework.stereotype.Service;

/**
 * @author Zhe Li
 * @date 2020/05/10
 */
@Service
public class AltitudeServiceImpl implements AltitudeService {

    private static double abs(double x) {
        return  (x > 0) ? x : (-x);
    }

    private static final double WIDTH_COEFFICIENT = 0.2;

    private static final double SLOPE_COEFFICIENT = 0.2;

    @Override
    public String altitudeCorrect(AltitudeForm altitudeForm) {
        String ret = "";
        double handWidth = abs(altitudeForm.getLeftHand().getX() - altitudeForm.getRightHand().getX());
        double elbowWidth = abs(altitudeForm.getLeftElbow().getX() - altitudeForm.getRightElbow().getX());
        double shoulderWidth = abs(altitudeForm.getLeftShoulder().getX() - altitudeForm.getRightShoulder().getX());
        double handSlope = Point.getSlope(altitudeForm.getLeftHand(), altitudeForm.getRightHand());
        double shoulderSlope = Point.getSlope(altitudeForm.getLeftShoulder(), altitudeForm.getRightShoulder());

        if(handWidth - shoulderWidth > WIDTH_COEFFICIENT * shoulderWidth) {
            ret = ret.concat("双手距离过宽!");
        }

        if(handWidth - shoulderWidth < -WIDTH_COEFFICIENT * shoulderWidth) {
            ret = ret.concat("双手距离过窄！");
        }

        if(elbowWidth - handWidth > WIDTH_COEFFICIENT * handWidth) {
            ret = ret.concat("肘部外翻过多!");
        }

        if( abs(handSlope/shoulderSlope - 1) > SLOPE_COEFFICIENT) {
            ret = ret.concat("手部应该与肩部平行！");
        }

        if("".equals(ret)) {
            ret = "动作标准";
        }

        return ret;
    }
}
