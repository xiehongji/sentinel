package com.alibaba.csp.sentinel.dashboard.rule.zookeeper.authority;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.AuthorityRuleEntity;
import com.alibaba.csp.sentinel.dashboard.rule.ConfigUtil;
import com.alibaba.csp.sentinel.dashboard.rule.zookeeper.RuleZookeeperProvider;
import org.springframework.stereotype.Component;

@Component("authorityRuleZookeeperProvider")
public class AuthorityRuleZookeeperProvider extends RuleZookeeperProvider<AuthorityRuleEntity> {
    @Override
    public String getDataIdPostfix() {
        return ConfigUtil.AUTHORITY_DATA_ID_POSTFIX;
    }
}
