package com.alibaba.csp.sentinel.dashboard.rule.zookeeper.authority;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.AuthorityRuleEntity;
import com.alibaba.csp.sentinel.dashboard.rule.ConfigUtil;
import com.alibaba.csp.sentinel.dashboard.rule.zookeeper.RuleZookeeperPublisher;
import org.springframework.stereotype.Component;

@Component("authorityRuleZookeeperPublisher")
public class AuthorityRuleZookeeperPublisher extends RuleZookeeperPublisher<AuthorityRuleEntity> {

    @Override
    public String getDataIdPostfix() {
        return ConfigUtil.AUTHORITY_DATA_ID_POSTFIX;
    }
}