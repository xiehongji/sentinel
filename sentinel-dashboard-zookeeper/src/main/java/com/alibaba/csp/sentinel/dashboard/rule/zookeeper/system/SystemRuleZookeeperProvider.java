package com.alibaba.csp.sentinel.dashboard.rule.zookeeper.system;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.SystemRuleEntity;
import com.alibaba.csp.sentinel.dashboard.rule.ConfigUtil;
import com.alibaba.csp.sentinel.dashboard.rule.zookeeper.RuleZookeeperProvider;
import org.springframework.stereotype.Component;

@Component("systemRuleZookeeperProvider")
public class SystemRuleZookeeperProvider extends RuleZookeeperProvider<SystemRuleEntity> {
    @Override
    public String getDataIdPostfix() {
        return ConfigUtil.SYSTEM_DATA_ID_POSTFIX;
    }
}
