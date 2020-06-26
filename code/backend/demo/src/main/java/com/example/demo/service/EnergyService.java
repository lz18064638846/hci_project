package com.example.demo.service;

import com.example.demo.message.response.EnergyReport;

/**
 * @author Zhe Li
 * @date 2020/06/26
 */
public interface EnergyService {
    /**
     * user get his own energy report
     * @param userId id of user
     * @return energy report
     */
    EnergyReport getEnergyReport(Long userId);
}
