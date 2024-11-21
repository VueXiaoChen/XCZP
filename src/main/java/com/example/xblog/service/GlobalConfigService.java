package com.example.xblog.service;

import com.example.xblog.config.GlobalConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GlobalConfigService {
    @Autowired
    private GlobalConfig globalConfig;

    public void SaveName(String UserName){
        globalConfig.setUserName(UserName);
    }

    public String GetName(){
        return globalConfig.getUserName();
    }
}
