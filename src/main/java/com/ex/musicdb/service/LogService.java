package com.ex.musicdb.service;

import com.ex.musicdb.model.servise.LogServiceModel;

import java.util.List;

public interface LogService {
    void createLog(String action, Long albumId);

    List<LogServiceModel> findAllLogs();
}
