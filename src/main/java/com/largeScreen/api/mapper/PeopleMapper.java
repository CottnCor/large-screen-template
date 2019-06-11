package com.largeScreen.api.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PeopleMapper {

    Boolean insertMsg(@Param("userid")Long userid, @Param("msg")String msg);

    Map selectYxInfo(@Param("userid")Long userid);

    Map selectUserInfo(@Param("userid")Long userid);

    Map selectUserState(@Param("userid")Long userid);

    List<Map> selectPeopleState();

    Map selectUserCoords(@Param("userid")Long userid);

    List<Map> selectPeopleCoords(@Param("wkt")String wkt);

    List<Map> selectPeopleCluster(@Param("level")Short level, @Param("filter")String filter);

    List<Map> selectPeopleCounts(@Param("type")String type, @Param("level")Short level, @Param("filter")String filter);

    List<Map> selectLevelCounts(@Param("type")String type);

    Map selectPersonTime(@Param("startTime") String startTime, @Param("endTime") String endTime);

    List<Map> selectPersonTimeCounts(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("level") Short level, @Param("xzqdm") String xzqdm);

    List<Map> selectActivePerson(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("limit") Short limit,  @Param("page") Short page);
}
