package com.daily.daily_push.vo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EnterpriseVo {
    //应用ID
    private static Integer agentId;

    //应用Secret
    private static String secret;
    //企业ID
    private static String corpId;

    public static Integer getAgentId() {
        return agentId;
    }

    public static String getSecret() {
        return secret;
    }

    @Value("${enterprise.secret}")
    public void setSecret(String secret) {
        EnterpriseVo.secret = secret;
    }

    public static String getCorpId() {
        return corpId;
    }

    @Value("${enterprise.corpId}")
    public void setCorpId(String corpId) {
        EnterpriseVo.corpId = corpId;
    }

    @Value("${enterprise.agentId}")
    public void setAgentId(Integer agentId) {
        EnterpriseVo.agentId = agentId;
    }


}
