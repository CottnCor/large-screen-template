package com.largeScreen.api.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.largeScreen.api.annotations.DataSource;
import com.largeScreen.api.aop.DataSourceEnum;
import com.largeScreen.api.mapper.CommonMapper;
import com.largeScreen.api.mapper.PeopleMapper;
import com.largeScreen.api.service.IPeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PeopleServiceImpl implements IPeopleService {

    @Autowired
    private PeopleMapper peopleMapper;

    @Autowired
    private CommonMapper commonMapper;

    @Override
    public Map getPeopleOverview(String type) {
        try {
            Map overview = new HashMap();
            overview.put("total", 0);
            overview.put("online", 0);
            List<Map> peopleCounts = new ArrayList<>();
            if ("sdhc".equals(type)) {
                peopleCounts = this.getSdhcPeopleCounts(null);
            } else if ("znfw".equals(type)) {
                peopleCounts = this.getZnfwPeopleCounts(null);
            }
            for (Map counts : peopleCounts) {
                Integer old = Integer.valueOf(overview.get("total").toString());
                Integer update = Integer.valueOf(counts.get("offline").toString()) + Integer.valueOf(counts.get("online").toString());
                overview.put("total", old + update);

                old = Integer.valueOf(overview.get("online").toString());
                update = Integer.valueOf(counts.get("online").toString());
                overview.put("online", old + update);
            }
            return overview;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public List<Map> getLevelCounts(String type) {
        try {
            return peopleMapper.selectLevelCounts(type);
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public List<Map> getPeopleCounts(String type, String xzqdm) {
        try {
            List<Map> peopleCounts = new ArrayList<>();
            if ("sdhc".equals(type)) {
                peopleCounts = this.getSdhcPeopleCounts(xzqdm);
            } else if ("znfw".equals(type)) {
                peopleCounts = this.getZnfwPeopleCounts(xzqdm);
            }
            return peopleCounts;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public List<Map> getUserTree() {
        try {
            Short level = 1;
            List<Map> users = peopleMapper.selectPeopleState();
            List<Map> userTree = new ArrayList<>();
            List<Map> sjs = commonMapper.selectRegionByLevel(level);
            List<Map> sxjs = new ArrayList<>();
            List<Map> xjs = new ArrayList<>();
            for (final Map sj : sjs) {
                if (sj.get("xzqdm") == null || sj.get("xzqdm").toString().isEmpty()
                        || "710000".equals(sj.get("xzqdm").toString())
                        || "810000".equals(sj.get("xzqdm").toString())
                        || "820000".equals(sj.get("xzqdm").toString())) {
                    continue;
                }
                Map sjTree = new HashMap();
                List<Map> sxjTreeItems = new ArrayList<>();
                final List<Map> sjUsers = users.stream().filter(item -> item.get("xzqdm").toString().substring(0, 2).equals(sj.get("xzqdm").toString().substring(0, 2))).collect(Collectors.toList());
                final List<Map> sjOnlineUsers = sjUsers.stream().filter(item -> "1".equals(item.get("state").toString())).collect(Collectors.toList());
                sjTree.put("level", 1);
                sjTree.put("id", sj.get("xzqdm").toString());
                sjTree.put("totalCount", sjUsers.size());
                sjTree.put("onlineCount", sjOnlineUsers.size());
                sjTree.put("xzqdm", sj.get("xzqdm").toString());
                sjTree.put("name", sj.get("xzqmc").toString());
                sxjs = commonMapper.selectRegionByParent(sj.get("xzqdm").toString());
                for (final Map sxj : sxjs) {
                    Map sxjTree = new HashMap();
                    List<Map> xjTreeItems = new ArrayList<>();
                    final List<Map> sxjUsers = sjUsers.stream().filter(item -> item.get("xzqdm").toString().substring(0, 4).equals(sxj.get("xzqdm").toString().substring(0, 4))).collect(Collectors.toList());
                    final List<Map> sxjOnlineUsers = sxjUsers.stream().filter(item -> "1".equals(item.get("state").toString())).collect(Collectors.toList());
                    sxjTree.put("level", 2);
                    sxjTree.put("id", sxj.get("xzqdm").toString());
                    sxjTree.put("totalCount", sxjUsers.size());
                    sxjTree.put("onlineCount", sxjOnlineUsers.size());
                    sxjTree.put("xzqdm", sxj.get("xzqdm").toString());
                    sxjTree.put("name", sxj.get("xzqmc").toString());
                    xjs = commonMapper.selectRegionByParent(sxj.get("xzqdm").toString());
                    for (final Map xj : xjs) {
                        Map xjTree = new HashMap();
                        xjTree.put("level", 3);
                        xjTree.put("id", xj.get("xzqdm").toString());
                        xjTree.put("xzqdm", xj.get("xzqdm").toString());
                        xjTree.put("name", xj.get("xzqmc").toString());
                        final List<Map> xjUsers = sxjUsers.stream().filter(item -> item.get("xzqdm").toString().equals(xj.get("xzqdm").toString())).collect(Collectors.toList());
                        final List<Map> xjOnlineUsers = xjUsers.stream().filter(item -> "1".equals(item.get("state").toString())).collect(Collectors.toList());
                        xjTree.put("totalCount", xjUsers.size());
                        xjTree.put("onlineCount", xjOnlineUsers.size());
                        List<Map> userItems = new ArrayList<>();
                        for (Map user : xjUsers) {
                            Map userNode = new HashMap();
                            userNode.put("level", 4);
                            userNode.put("id", user.get("userid").toString());
                            userNode.put("name", user.get("username").toString());
                            userNode.put("userid", user.get("userid").toString());
                            userNode.put("state", Integer.parseInt(user.get("state").toString()));
                            userItems.add(userNode);
                        }
                        xjTree.put("children", userItems);
                        xjTreeItems.add(xjTree);
                    }
                    sxjTree.put("children", xjTreeItems);
                    sxjTreeItems.add(sxjTree);
                }
                sjTree.put("children", sxjTreeItems);
                userTree.add(sjTree);
            }
            return userTree;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public List<Map> getPeopleCoords(Short level, String bounds) {
        try {
            List<Map> result = new ArrayList<>();
            List<Map> temp = new ArrayList<>();
            if (1 == level) {
                temp = peopleMapper.selectPeopleCluster(level, null);
            } else {
                JSONObject obj = JSONObject.parseObject(bounds);
                String minLng = obj.getString("minLng");
                String minLat = obj.getString("minLat");
                String maxLng = obj.getString("maxLng");
                String maxLat = obj.getString("maxLat");
                String wktStr = String.format("POLYGON((%s %s,%s %s,%s %s,%s %s,%s %s))"
                        , minLng, minLat
                        , minLng, maxLat
                        , maxLng, maxLat
                        , maxLng, minLat
                        , minLng, minLat);
                List<Map> regions = commonMapper.selectRegionByBounds(level, wktStr);
                if (2 == level) {
                    String filter = "";
                    for (Map region : regions) {
                        String xzqdm = region.get("xzqdm").toString().substring(0, 2);
                        if (xzqdm != null && xzqdm != "") {
                            filter = String.format("%s OR f_xzqdm LIKE '%s%%'", filter, xzqdm);
                        }
                    }
                    filter = filter.substring(4, filter.length());
                    temp = peopleMapper.selectPeopleCluster(level, filter);
                } else if (3 == level) {
                    List<Map> users = new ArrayList<>();
                    if (regions.size() > 0) {
                        for (Map region : regions) {
                            Map wkt = commonMapper.selectRegionWktByXzqdm(region.get("xzqdm").toString());
                            if (wkt != null) {
                                users.addAll(peopleMapper.selectPeopleCoords(wkt.get("wkt").toString()));
                            }
                        }
                    } else {
                        users.addAll(peopleMapper.selectPeopleCoords(wktStr));
                    }
                    return users;
                }
            }
            for (Map item : temp) {
                if (Integer.parseInt(item.get("count").toString()) == 1) {
                    Map<String, Object> verifyed = verifyUserLacation(item.get("xzqdm").toString());
                    if (verifyed != null) {
                        verifyed.put("count", 1);
                        verifyed.put("xzqdm", item.get("xzqdm").toString());
                        verifyed.put("xzqmc", item.get("xzqmc").toString());
                        result.add(verifyed);
                    }
                } else {
                    result.add(item);
                }
            }
            return result;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public Map getYxInfo(Long userid) {
        try {
            return peopleMapper.selectYxInfo(userid);
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public Map getUserInfo(Long userid) {
        try {
            return peopleMapper.selectUserInfo(userid);
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public Map getUserState(Long userid) {
        try {
            return peopleMapper.selectUserState(userid);
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public Map getUserCoords(Long userid) {
        try {
            return peopleMapper.selectUserCoords(userid);
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public Boolean sendMsg(Long userid, String msg) {
        try {
            return peopleMapper.insertMsg(userid, msg);
        } catch (Exception ex) {
            return null;
        }
    }

    private Map verifyUserLacation(String xzqdm) {
        try {
            Map<String, Object> verifyed = new HashMap<String, Object>();
            List<Map> users = new ArrayList<>();
            Map wkt = commonMapper.selectRegionWktByXzqdm(xzqdm);
            if (wkt != null) {
                String filter = wkt.get("wkt").toString();
                users.addAll(peopleMapper.selectPeopleCoords(filter));
                if (users.size() > 0) {
                    verifyed.put("lng", users.get(0).get("lng"));
                    verifyed.put("lat", users.get(0).get("lat"));
                    return verifyed;
                }
            }
            return null;
        } catch (Exception ex) {
            return null;
        }
    }

    @DataSource(DataSourceEnum.ZNFW)
    public List<Map> getZnfwPeopleCounts(String xzqdm) {
        try {
            Short level = 1;
            String filter = "";
            if (xzqdm == null || xzqdm.isEmpty()) {
                level = 1;
            } else if ("0000".equals(xzqdm.substring(xzqdm.length() - 4))) {
                level = 2;
                filter = xzqdm.substring(0, 2) + "%%%%";
            } else if ("00".equals(xzqdm.substring(xzqdm.length() - 2))) {
                level = 3;
                filter = xzqdm.substring(0, 4) + "%%";
            } else {
                return new ArrayList<>();
            }
            return peopleMapper.selectPeopleCounts("znfw", level, filter);
        } catch (Exception ex) {
            return null;
        }
    }

    private List<Map> getSdhcPeopleCounts(String xzqdm) {
        try {
            Short level = 1;
            String filter = "";
            if (xzqdm == null || xzqdm.isEmpty()) {
                level = 1;
            } else if ("0000".equals(xzqdm.substring(xzqdm.length() - 4))) {
                level = 2;
                filter = xzqdm.substring(0, 2) + "%%%%";
            } else if ("00".equals(xzqdm.substring(xzqdm.length() - 2))) {
                level = 3;
                filter = xzqdm.substring(0, 4) + "%%";
            } else {
                return new ArrayList<>();
            }
            return peopleMapper.selectPeopleCounts("sdhc", level, filter);
        } catch (Exception ex) {
            return null;
        }
    }
}
