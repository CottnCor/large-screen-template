package com.largeScreen.api.service;

import java.util.List;
import java.util.Map;

public interface IPeopleService {

    Map getYxAccount(String userid);

    Map getUserInfo(String userid);

    Map getUserState(String userid);

    Map getUserLocation(String userid);

    List<Map> getUserTree();

    Boolean addMessage(String userid);
}
