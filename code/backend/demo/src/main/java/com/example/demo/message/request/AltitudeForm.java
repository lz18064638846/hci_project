package com.example.demo.message.request;

import java.util.List;
import com.example.demo.util.Point;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author Zhe Li
 * @date 2020/05/10
 */
@ApiModel(value = "用户六个关键部位的位置数据")
public class AltitudeForm {

    @ApiModelProperty(value = "左肩", required = true)
    private Point leftShoulder;

    @ApiModelProperty(value = "右肩", required = true)
    private Point rightShoulder;

    @ApiModelProperty(value = "左肘", required = true)
    private Point leftElbow;

    @ApiModelProperty(value = "右肘", required = true)
    private Point rightElbow;

    @ApiModelProperty(value = "左手", required = true)
    private Point leftHand;

    @ApiModelProperty(value = "右手", required = true)
    private Point rightHand;


    public AltitudeForm() {
    }

    public AltitudeForm(Point leftShoulder, Point rightShoulder, Point leftElbow, Point rightElbow, Point leftHand, Point rightHand) {
        this.leftShoulder = leftShoulder;
        this.rightShoulder = rightShoulder;
        this.leftElbow = leftElbow;
        this.rightElbow = rightElbow;
        this.leftHand = leftHand;
        this.rightHand = rightHand;
    }

    public Point getLeftShoulder() {
        return leftShoulder;
    }

    public void setLeftShoulder(Point leftShoulder) {
        this.leftShoulder = leftShoulder;
    }

    public Point getRightShoulder() {
        return rightShoulder;
    }

    public void setRightShoulder(Point rightShoulder) {
        this.rightShoulder = rightShoulder;
    }

    public Point getLeftElbow() {
        return leftElbow;
    }

    public void setLeftElbow(Point leftElbow) {
        this.leftElbow = leftElbow;
    }

    public Point getRightElbow() {
        return rightElbow;
    }

    public void setRightElbow(Point rightElbow) {
        this.rightElbow = rightElbow;
    }

    public Point getLeftHand() {
        return leftHand;
    }

    public void setLeftHand(Point leftHand) {
        this.leftHand = leftHand;
    }

    public Point getRightHand() {
        return rightHand;
    }

    public void setRightHand(Point rightHand) {
        this.rightHand = rightHand;
    }
}
