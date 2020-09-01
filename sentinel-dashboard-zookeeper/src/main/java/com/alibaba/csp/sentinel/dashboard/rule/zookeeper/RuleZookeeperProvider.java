package com.alibaba.csp.sentinel.dashboard.rule.zookeeper;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.RuleEntity;
import com.alibaba.csp.sentinel.dashboard.rule.DynamicRuleProvider;
import com.alibaba.csp.sentinel.datasource.Converter;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public abstract class RuleZookeeperProvider<T extends RuleEntity>  implements DynamicRuleProvider<List<T>> {

    @Autowired(required = false)
    protected Converter<String, List<T>> converter;
    @Autowired
    private CuratorFramework zkClient;
    @Override
    public List<T> getRules(String appName) throws Exception {
        StringBuilder stringBuilder = new StringBuilder();
        String zkPath = ZookeeperConfigUtil.getPath(appName);
        stringBuilder.append(zkPath);
        stringBuilder.append(getDataIdPostfix());
        zkPath=stringBuilder.toString();
        Stat stat = zkClient.checkExists().forPath(zkPath);
        if(stat == null){
            return new ArrayList<>(0);
        }
        byte[] bytes = zkClient.getData().forPath(zkPath);
        if (null == bytes || bytes.length == 0) {
            return new ArrayList<>();
        }
        String s = new String(bytes);

        return converter.convert(s);
    }

    /**
     * 文件后缀 参考 com.alibaba.csp.sentinel.dashboard.rule.nacos.NacosConfigUtil.FLOW_DATA_ID_POSTFIX
     * @return
     */
    public abstract String getDataIdPostfix();
}
