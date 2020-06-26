package com.example.demo.serviceimpl;

import static java.lang.Math.*;
import java.sql.Timestamp;
import com.example.demo.message.request.AltitudeForm;
import com.example.demo.model.Record;
import com.example.demo.model.RecordPrimaryKey;
import com.example.demo.model.SportType;
import com.example.demo.repository.RecordRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AltitudeService;
import com.example.demo.util.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Zhe Li
 * @date 2020/05/10
 */
@Service
public class AltitudeServiceImpl implements AltitudeService {

    private static final double WIDTH_COEFFICIENT = 0.2;

    private static final double SLOPE_COEFFICIENT = 0.2;

    private static final double ELBOW_ANGLE = PI / 6;

    private final RecordRepository recordRepository;
    private final UserRepository userRepository;

    @Autowired
    public AltitudeServiceImpl(RecordRepository recordRepository, UserRepository userRepository) {
        this.recordRepository = recordRepository;
        this.userRepository = userRepository;
    }


    @Override
    public String altitudeCorrect(AltitudeForm altitudeForm, Long userId) {
        String ret = "";
        double score = 1.0;
        double handWidth = abs(altitudeForm.getLeftHand().getX() - altitudeForm.getRightHand().getX());
        double shoulderWidth = abs(altitudeForm.getLeftShoulder().getX() - altitudeForm.getRightShoulder().getX());
        double handSlope = Point.getSlope(altitudeForm.getLeftHand(), altitudeForm.getRightHand());
        double shoulderSlope = Point.getSlope(altitudeForm.getLeftShoulder(), altitudeForm.getRightShoulder());
        double leftElbow = Point.getSlope(altitudeForm.getLeftElbow(), altitudeForm.getLeftHand());
        double rightElbow = Point.getSlope(altitudeForm.getRightElbow(), altitudeForm.getRightHand());


        if(handWidth - shoulderWidth > WIDTH_COEFFICIENT * shoulderWidth) {
            ret = ret.concat("双手距离过宽!\n");
        }

        if(handWidth - shoulderWidth < -WIDTH_COEFFICIENT * shoulderWidth) {
            ret = ret.concat("双手距离过窄!\n");
        }

        score /= (abs((handWidth - shoulderWidth) / shoulderWidth) + 1);

        if(leftElbow > - tan(ELBOW_ANGLE)) {
            ret = ret.concat("左手肘外翻过多!\n");
        }

        if(rightElbow < tan(ELBOW_ANGLE)) {
            ret = ret.concat("右手肘外翻过多!\n");
        }

        if( abs(handSlope/shoulderSlope - 1) > SLOPE_COEFFICIENT) {
            ret = ret.concat("手部应该与肩部平行!\n");
        }

        if("".equals(ret)) {
            ret = "动作标准\n";
        }

        Record record = new Record(new RecordPrimaryKey(userRepository.getOne(userId),
                new Timestamp(System.currentTimeMillis())), score, SportType.PUSH_UP);

        recordRepository.save(record);

        return ret;
    }
}
