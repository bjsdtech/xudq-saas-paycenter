package com.xudq.api.ons.consumer;

import com.aliyun.openservices.ons.api.Action;
import com.aliyun.openservices.ons.api.ConsumeContext;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.MessageListener;
import com.xudq.api.service.RecvableService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

/**
 * @author xudq
 * @version v1.0
 * @Title
 * @Description ons消费者
 * @date
 */
public class OnsMsgConsumer implements MessageListener {

    protected static final Logger logger = LoggerFactory.getLogger(OnsMsgConsumer.class);

     @Autowired
    private RecvableService recvableService;
    /*
     * ONS消息接收异步调用函数
     */
    @Override
    public Action consume(Message message, ConsumeContext context) {
        String bodyString = new String(message.getBody());
        int times = message.getReconsumeTimes();
        logger.info("Ons Consumer consume:  【" + message.getMsgID() + "】" + bodyString + "" + times +"次");
        //TODO 使用的全局顺序的MQ，需要注意重试次数，可能会因为一条消息没有消费成功，导致后续消息的阻塞
        if(times >= 16){
            return Action.CommitMessage;
        }
        try {
            MDC.put("request_id", UUID.randomUUID().toString());

            String tag = message.getTag();
            if (StringUtils.isEmpty(bodyString) || StringUtils.isEmpty(tag)) {
                throw new NullPointerException("message reved from ons is empty, " +
                        "bodyString = " + bodyString + " ,tag = " + tag);
            }

            // 通过tag区分消息类型
            if ("pay-core-pre-order".equals(tag)) {
                // 将流水直接入DB history，防止数据丢失
                recvableService.insertHistory(bodyString, times);
                // 进行正常的流水入库操作
                recvableService.insert(bodyString);
                return Action.CommitMessage;
            } else if ("pay-core-notify".equals(tag)) {
                // 将流水直接入DB history，防止数据丢失
                recvableService.insertHistory(bodyString, times);
                // 更新流水状态
                recvableService.updateByRecvableId(bodyString);
                return Action.CommitMessage;
            } else {
                logger.info("Ons message tag = " + tag + ", id = " + message.getMsgID());
            }
        } catch (Exception e) {
            logger.error("Consumer the message occur exception:", e);
            return Action.ReconsumeLater;
        } finally {
            MDC.clear();
        }
        return Action.ReconsumeLater;
    }

}
