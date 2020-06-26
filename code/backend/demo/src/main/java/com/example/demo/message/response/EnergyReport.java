package com.example.demo.message.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author Zhe Li
 * @date 2020/06/26
 */
@ApiModel(value = "返回用户运动情况的表格", description = "包括了今日运动消耗能量以及饮食指导")
public class EnergyReport {

    private static final double KCAL_TO_RICE = 1.16;

    private static final double KCAL_TO_CORN = 0.86;

    private static final double KCAL_TO_SWEETPOTATO = 0.86;

    @ApiModelProperty(value = "消耗热量，单位为千卡")
    private double calorie;

    @ApiModelProperty(value = "相当于多少克米饭")
    private double rice;

    @ApiModelProperty(value = "相当于多少克玉米")
    private double corn;

    @ApiModelProperty(value = "相当于多少克红薯")
    private double sweetPotato;

    public EnergyReport(double calorie) {
        this.calorie = calorie;
        this.corn = calorie / KCAL_TO_CORN;
        this.rice = calorie / KCAL_TO_RICE;
        this.sweetPotato = calorie / KCAL_TO_SWEETPOTATO;
    }

    public double getCalorie() {
        return calorie;
    }

    public void setCalorie(double calorie) {
        this.calorie = calorie;
    }

    public double getRice() {
        return rice;
    }

    public void setRice(double rice) {
        this.rice = rice;
    }

    public double getCorn() {
        return corn;
    }

    public void setCorn(double corn) {
        this.corn = corn;
    }

    public double getSweetPotato() {
        return sweetPotato;
    }

    public void setSweetPotato(double sweetPotato) {
        this.sweetPotato = sweetPotato;
    }
}
