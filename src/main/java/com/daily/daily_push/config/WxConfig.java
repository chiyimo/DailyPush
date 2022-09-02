package com.daily.daily_push.config;

import com.alibaba.fastjson.JSON;
import com.daily.daily_push.vo.EnterpriseVo;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.api.impl.WxCpServiceImpl;
import me.chanjar.weixin.cp.config.impl.WxCpDefaultConfigImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 推送企业消息配置
 */
public class WxConfig {


    // 配置企业微信服务
    public static WxCpService getWxCpService() {
        WxCpService wxCpService=new WxCpServiceImpl();
        WxCpDefaultConfigImpl config =new WxCpDefaultConfigImpl();
        config.setAgentId(EnterpriseVo.getAgentId());
        config.setCorpSecret(EnterpriseVo.getSecret());
        config.setCorpId(EnterpriseVo.getCorpId());
        resetTokenAndJsApi(wxCpService,config);
        return wxCpService;
    }
    // 重置token
    public static void resetTokenAndJsApi(WxCpService wxCpService,WxCpDefaultConfigImpl wxCpDefaultConfig) {
        // 配置redis
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(8);
        jedisPoolConfig.setMaxTotal(18);
        // redis启动后，默认启动的是6379端口，和我的一样即可
        Jedis jedis =new JedisPool(jedisPoolConfig,"localhost",6379,5000).getResource();

        wxCpService.setWxCpConfigStorage(wxCpDefaultConfig);
        String wxAccessToken = "wx"+EnterpriseVo.getCorpId();
        String json=jedis.get(wxAccessToken);
        if(!StringUtils.isEmpty(json)){
            wxCpDefaultConfig = JSON.parseObject(json,WxCpDefaultConfigImpl.class);
        }
        if(wxCpDefaultConfig.isAccessTokenExpired()){
            try {
                String accessToken = null;
                accessToken =wxCpService.getAccessToken(false);
                wxCpDefaultConfig.setAccessToken(accessToken);
            }catch (WxErrorException e){
                e.printStackTrace();
            }
        }
        if(wxCpDefaultConfig.isJsapiTicketExpired()){
            String jsApi = null;
            try {
                jsApi = wxCpService.getJsapiTicket();
                wxCpDefaultConfig.setJsapiTicket(jsApi);
            } catch (WxErrorException e) {
                e.printStackTrace();
            }
        }
        jedis.set(wxAccessToken,JSON.toJSONString(wxCpDefaultConfig));
        jedis.close();
    }
}
