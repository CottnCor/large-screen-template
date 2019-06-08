package com.largeScreen.api.service.impl;

import com.largeScreen.api.mapper.PeopleMapper;
import com.largeScreen.api.service.IPeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PeopleServiceImpl implements IPeopleService {

    @Autowired
    private PeopleMapper peopleMapper;

    @Override
    public Map getYxAccount(String userid) {
        try {
            return peopleMapper.selectYxAccount(userid);
        } catch (Exception ex) {
            return null;
        }
    }


    @Override
    public Map getUserInfo(String userid) {
        try {
            return peopleMapper.selectUserInfo(userid);
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public Map getUserState(String userid) {
        try {
            return peopleMapper.selectUserState(userid);
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public Map getUserLocation(String userid) {
        try {
            return peopleMapper.selectUserLocation(userid);
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public List<Map> getUserTree() {
        try {
            return peopleMapper.selectUserTree();
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public Boolean addMessage(String userid) {
        try {
            return peopleMapper.insertMessage(userid);
        } catch (Exception ex) {
            return null;
        }
    }
}
