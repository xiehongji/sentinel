package com.alibaba.csp.sentinel.dashboard.rule.zookeeper;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.RuleEntity;
import com.alibaba.csp.sentinel.dashboard.rule.DynamicRulePublisher;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.csp.sentinel.util.AssertUtil;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.List;

public abstract class RuleZookeeperPublisher<T extends RuleEntity> implements DynamicRulePublisher<List<T>> {


    @Autowired(required = false)
    protected Converter<List<T>, String> converter;
    @Autowired
    private CuratorFramework zkClient;
    @Override
    public void publish(String app, List<T> rules) throws Exception {
        AssertUtil.notEmpty(app, "app name cannot be empty");
        StringBuilder stringBuilder = new StringBuilder();
        String path = ZookeeperConfigUtil.getPath(app);
        stringBuilder.append(path);
        stringBuilder.append(getDataIdPostfix());
        path=stringBuilder.toString();
        Stat stat = zkClient.checkExists().forPath(path);
        if (stat == null) {
            zkClient.create().creatingParentContainersIfNeeded().withMode(CreateMode.PERSISTENT).forPath(path, null);
        }
        byte[] data = CollectionUtils.isEmpty(rules) ? "[]".getBytes() : converter.convert(rules).getBytes();
        zkClient.setData().forPath(path, data);
    }
    /**
     * 文件后缀 参考 com.alibaba.csp.sentinel.dashboard.rule.nacos.NacosConfigUtil.FLOW_DATA_ID_POSTFIX
     * @return
     */
    public abstract String getDataIdPostfix();
}
