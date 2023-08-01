package com.xudq.api.service;

import com.alibaba.fastjson.JSONObject;
import com.xudq.api.utils.HttpReturn;
import com.xudq.api.utils.HttpUtil;
import com.xudq.api.utils.OctoUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author xudq
 * @version v1.0
 * @Title
 * @Description 调用渠道服务
 * @date
 */
@Service
public class ChannelService {

    private static final Logger logger = LoggerFactory.getLogger(ChannelService.class);

    /**
     * 降级渠道账号，可以不用
     */
    private static final String FALLBACK_CHANNEL_ID = "";

    @Value("${octo.host}")
    private String octoHost;

    @Value("${octo.url.jiesuan-web.queryChannleAccountIdByMerId}")
    private String queryChannleAccountIdByMerId;


    public String queryAccountId(String merId) {
        try {
            if (StringUtils.isBlank(merId)) {
                logger.error("调用渠道管理查询渠道账号错误, channelMerId为空");
                throw new RuntimeException("调用渠道管理查询渠道账号错误");
            }
            String url = "http://" + octoHost + queryChannleAccountIdByMerId + "/" + merId + "?_octo=" + OctoUtils.generateJwtToken();
            logger.info("调用渠道管理查询渠道账号 url: {}", url);
            HttpReturn response = HttpUtil.get(url);
            if (response == null || response.getStatus() != 200
                    || StringUtils.isBlank(response.getText())) {
                logger.error("调用渠道管理查询渠道账号错误, 使用默认渠道: {}, {}", FALLBACK_CHANNEL_ID, JSONObject.toJSONString(response));
                throw new RuntimeException("调用渠道管理查询渠道账号错误");
            }
            String text = response.getText();
            logger.info("调用渠道管理查询渠道账号返回内容: {}", text);
            JSONObject body = JSONObject.parseObject(text);
            int code = body.getIntValue("status");
            if (code != 0) {
                logger.error("调用渠道管理查询渠道账号错误, 使用默认渠道: {}, {}", FALLBACK_CHANNEL_ID, text);
                throw new RuntimeException("调用渠道管理查询渠道账号错误");
            }
            String data = body.getString("data");
            if (StringUtils.isBlank(data)) {
                logger.error("调用渠道管理查询渠道账号错误, 使用默认渠道: {}, {}", FALLBACK_CHANNEL_ID, text);
                throw new RuntimeException("调用渠道管理查询渠道账号错误");
            }
            logger.info("调用渠道管理查询渠道账号成功, merId: {}, accountId: {}",  merId, data);
            return data;
        } catch (Exception e) {
            logger.error("调用渠道管理查询渠道账号异常, 使用默认渠道: {}, {}", FALLBACK_CHANNEL_ID, e);
            throw new RuntimeException("调用渠道管理查询渠道账号错误");
        }
    }

}
