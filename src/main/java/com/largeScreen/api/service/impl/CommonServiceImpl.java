package com.largeScreen.api.service.impl;

import com.largeScreen.api.mapper.CommonMapper;
import com.largeScreen.api.service.ICommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommonServiceImpl implements ICommonService {

    @Autowired
    private CommonMapper commonMapper;

    @Override
    public List<Map> getRegionByLevel(Short level) {
        try {
            return commonMapper.selectRegionByLevel(level);
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public List<Map> getRegionByParent(String xzqdm) {
        try {
            return commonMapper.selectRegionByParent(xzqdm);
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public List<Map> getRegionByBounds(Short level, String wkt) {
        try {
            return commonMapper.selectRegionByBounds(level, wkt);
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public Map getRegionByXzqdm(String xzqdm) {
        try {
            return commonMapper.selectRegionByXzqdm(xzqdm);
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public Map getRegionWktByXzqdm(String xzqdm) {
        try {
            return commonMapper.selectRegionWktByXzqdm(xzqdm);
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public Map getRegionTagByXzqdm(String xzqdm) {
        try {
            return commonMapper.selectRegionTagByXzqdm(xzqdm);
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public List<Map> getRegionGroups() {
        List<Map> data = new ArrayList<>();
        try {
            List<Map> items;
            List<Map> parents = this.getRegionByParent("-1");
            for (Map record : parents) {
                items = this.getRegionByParent(record.get("xzqdm").toString());
                Map group = new HashMap();
                group.putAll(record);
                group.put("regions", items);
                data.add(group);
            }
            return data;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public Map getStorageAddress(String id) {
        try {
            return commonMapper.selectStorageAddress(id);
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public Map getJctbInfo(String layerId, String jctbId) {
        try {
            return commonMapper.selectJctbInfo(layerId, jctbId);
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public List<Map> getVisibleJctb(Double minx, Double miny, Double maxx, Double maxy) {
        try {
            List<Map> visibleJctb = new ArrayList<>();
            List<Map> ybjzInfos = commonMapper.selectVisibleJctb(minx, miny, maxx, maxy, "1014");
            List<Map> ccjzInfos = commonMapper.selectVisibleJctb(minx, miny, maxx, maxy, "1015");
            List<Map> bcjzInfos = commonMapper.selectVisibleJctb(minx, miny, maxx, maxy, "1016");
            List<Map> zxjzInfos = commonMapper.selectVisibleJctb(minx, miny, maxx, maxy, "1017");
            visibleJctb.addAll(ybjzInfos);
            visibleJctb.addAll(ccjzInfos);
            visibleJctb.addAll(bcjzInfos);
            visibleJctb.addAll(zxjzInfos);
            return visibleJctb;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public List<Map> getJctbAffix(String layerId, String jctbId) {
        try {
            return commonMapper.selectJctbAffix(layerId, jctbId);
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public void editJctbInfo(Map record) {
        try {
            commonMapper.updateJctbInfo(record);
        } catch (Exception ex) {
        }
    }
}
