package com.largeScreen.api.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface DispatchMapper {

    List<Map> selectObjectType();

    Map selectOrderDate(@Param("bizId") String bizId, @Param("date") String date);

    List<Map> selectTimeSegments(@Param("dateId") String dateId);

    List<Map> selectDispatchOverview(@Param("bizId") String bizId);

    List<Map> selectDispatchRecord(@Param("limit") Short limit, @Param("page") Short page);

    List<Map> selectDispatchList(@Param("state") List<Short> state, @Param("segmentId") List<String> segmentId, @Param("limit") Short limit, @Param("page") Short page);

    List<Map> selectDispatchCoords(@Param("id") String id);

    Map selectDispatchInfo(@Param("id") String id);

    Boolean insertDispatchRecord(@Param("record") Map record);

    Boolean updateDispatchState(@Param("id") String id, @Param("state") Short state, @Param("connState") Short connState);

    Boolean lockDispatch(@Param("id") String id);

    Boolean unlockDispatch(@Param("id") String id);
}
