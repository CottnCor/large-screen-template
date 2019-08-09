package com.largeScreen.api.service.impl;

import com.alibaba.fastjson.JSON;
import com.largeScreen.api.annotations.DataSource;
import com.largeScreen.api.aop.DataSourceEnum;
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
    @DataSource(DataSourceEnum.ZXJZ)
    public List<Map> getRegionByLevel(Short level) {
        try {
            return commonMapper.selectRegionByLevel(level);
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    @DataSource(DataSourceEnum.ZXJZ)
    public List<Map> getRegionByParent(String xzqdm) {
        try {
            return commonMapper.selectRegionByParent(xzqdm);
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    @DataSource(DataSourceEnum.ZXJZ)
    public List<Map> getRegionByBounds(Short level, String wkt) {
        try {
            return commonMapper.selectRegionByBounds(level, wkt);
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    @DataSource(DataSourceEnum.ZXJZ)
    public Map getRegionByCoord(Double lng, Double lat) {
        try {
            return commonMapper.selectRegionByCoord(lng, lat);
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    @DataSource(DataSourceEnum.ZXJZ)
    public Map getRegionByXzqdm(String xzqdm) {
        try {
            return commonMapper.selectRegionByXzqdm(xzqdm);
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    @DataSource(DataSourceEnum.ZXJZ)
    public Map getRegionWktByXzqdm(String xzqdm) {
        try {
            return commonMapper.selectRegionWktByXzqdm(xzqdm);
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    @DataSource(DataSourceEnum.ZXJZ)
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
    @DataSource(DataSourceEnum.ZXJZ)
    public Map getOssConfig() {
        try {
            return commonMapper.selectOssConfig();
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    @DataSource(DataSourceEnum.ZXJZ)
    public Map getStorageAddress(String id) {
        try {
            return commonMapper.selectStorageAddress(id);
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    @DataSource(DataSourceEnum.ZXJZ)
    public List<Map> getConfigDic(String layerId, String type, Integer pid, Integer subtype) {
        try {
            return commonMapper.selectConfigDic(layerId, type, pid, subtype);
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    @DataSource(DataSourceEnum.ZXJZ)
    public List<Map> getEnumeratorDic(String key) {
        try {
            return commonMapper.selectEnumeratorDic(key);
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    @DataSource(DataSourceEnum.ZXJZ)
    public List<Map> getSupervisedInfoDic(Integer level, String layerId) {
        try {
            List<Map> fields = commonMapper.selectConfigDic(layerId, "4", level == 1 ? 1001 : 1002, level == 1 ? 5 : 6);
            for (Map field : fields) {
                if (field.containsKey("casevalue")) {
                    Map casevalue = JSON.parseObject(field.get("casevalue").toString());
                    if (casevalue.containsKey("DicCode")) {
                        String dicCode = casevalue.get("DicCode").toString();
                        List<Map> options = this.getEnumeratorDic(dicCode);
                        field.put("options", options);
                    }
                }
            }
            return fields;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    @DataSource(DataSourceEnum.ZXJZ)
    public List<Map> getJzInfoDic(String layerId) {
        try {
            List<Map> fields = commonMapper.selectConfigDic(layerId, "4", 1001, 3);
            for (Map field : fields) {
                if(field.containsKey("casevalue")){
                    Map casevalue = JSON.parseObject(field.get("casevalue").toString());
                    if(casevalue.containsKey("DicCode")){
                        String dicCode = casevalue.get("DicCode").toString();
                        List<Map> options = this.getEnumeratorDic(dicCode);
                        field.put("options", options);
                    }
                }
            }
            return fields;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    @DataSource(DataSourceEnum.ZXJZ)
    public List<Map> getJctbInfoDic(String layerId) {
        try {
            List<Map> fields = commonMapper.selectConfigDic(layerId, "4", 1001, 1);
            for (Map field : fields) {
                if(field.containsKey("casevalue")){
                    String translate = field.get("casevalue").toString();
                    if(translate != null && translate != "" && translate.contains("translate")){
                        field.put("translate", translate);
                    }
                }
            }
            return fields;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    @DataSource(DataSourceEnum.ZXJZ)
    public Map getJctbInfo(String layerId, String tbbh, String xzqdm, List<String> fields) {
        try {
            return commonMapper.selectJctbInfo(fields, layerId, tbbh, xzqdm);
        } catch (Exception ex) {
            return null;
        }
    }


    @Override
    @DataSource(DataSourceEnum.ZXJZ)
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
    @DataSource(DataSourceEnum.ZXJZ)
    public List<Map> getJctbAffix(String layerId, String tbbh, String xzqdm) {
        try {
            return commonMapper.selectJctbAffix(layerId, tbbh, xzqdm);
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    @DataSource(DataSourceEnum.ZXJZ)
    public void addJctbAffix(String layerId, String tbbh, String xzqdm, List<String> fields, List<String> values) throws Exception {
//        commonMapper.insertJctbAffix(record);
    }

    @Override
    @DataSource(DataSourceEnum.ZXJZ)
    public void editJctbInfo(String layerId, String tbbh, String xzqdm, List<String> fields, List<String> values) throws Exception {
//        commonMapper.updateJctbInfo(record);
    }

    @Override
    @DataSource(DataSourceEnum.ZXJZ)
    public Map invokeMethod(String method) {
        try {
            return commonMapper.invokeMethod(method);
        } catch (Exception ex) {
            return null;
        }
    }
}
