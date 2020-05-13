package com.example.demo.service;

import com.example.demo.message.request.AltitudeForm;

/**
 * @author Zhe Li
 * @date 2020/05/10
 */
public interface AltitudeService {
    /**
     * This service handle user's altitude data and return hint which could help user do better.
     *
     * @param altitudeForm Form that post position of six critical part of human body.
     * @return Hint that help user do better.
     */
    String altitudeCorrect(AltitudeForm altitudeForm);
}
