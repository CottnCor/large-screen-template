package com.largeScreen.api.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PeopleMapper {

    Map selectYxAccount(@Param("userid") String userid);

    Map selectUserInfo(@Param("userid") String userid);

    Map selectUserState(@Param("userid") String userid);

    Map selectUserLocation(@Param("userid") String userid);

    List<Map> selectUserTree();

    Boolean insertMessage(@Param("userid") String userid);
}
