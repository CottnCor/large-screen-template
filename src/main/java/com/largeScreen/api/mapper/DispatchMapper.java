package com.largeScreen.api.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface DispatchMapper {

    List<Map> selectObjectType();

    Map selectOrderDate(@Param("bizId") String bizId, @Param("date") String date);

    List<Map> selectTimeSegments(@Param("orderDateId") String orderDateId);

    Map selectDispatchOverview(@Param("bizId") String bizId);

    List<Map> selectDispatchMap(@Param("state") Short state, @Param("segmentId") String segmentId, @Param("limit") Short limit, @Param("page") Short page);

    List<Map> selectDispatchList(@Param("state") Short state, @Param("segmentId") String segmentId, @Param("limit") Short limit, @Param("page") Short page);

    Map selectDispatchInfo(@Param("id") String id);

    Map selectDispatchLocation(@Param("id") String id);

    Boolean insertDispatchRecord();

    Boolean updateDispatchState(@Param("id") String id, @Param("state") Short state, @Param("connState") Short connState);

    Boolean lockDispatch(@Param("id") String id);

    Boolean unlockDispatch(@Param("id") String id);

    List<Map> statisticDispatch(@Param("start") String start, @Param("end") String end);
}
