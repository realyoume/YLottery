package com.yumi.lottery.service.jobhandler;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import com.yumi.lottery.mapper.*;
import com.yumi.lottery.model.entity.*;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @author: xk
 * @description 活动任务处理器
 * @date: 2023/8/6 14:13
 */

@Component
public class ActivityJobHandler {
    @Autowired
    private ActivityPublishMessageMapper activityPublishMessageMapper;

    @Autowired
    private ActivityPublishMessageHistoryMapper activityPublishMessageHistoryMapper;

    @Autowired
    private ActivityPublishMapper activityPublishMapper;

    @Autowired
    private ActivityMapper activityMapper;

    @Autowired
    private StrategyMapper strategyMapper;

    @Autowired
    private StrategyDetailMapper strategyDetailMapper;

    @Autowired
    Configuration configuration;


    @XxlJob("activityPublishHandler1")
    public ReturnT<String> activityPublishHandler1(String param) throws Exception{
        LambdaQueryWrapper<ActivityPublishMessage> queryWrapper = new LambdaQueryWrapper<>();
        List<ActivityPublishMessage> activityPublishMessages = activityPublishMessageMapper.selectList(queryWrapper);

        for (ActivityPublishMessage activityPublishMessage : activityPublishMessages) {
            if (activityPublishMessage.getState1() == 1 && activityPublishMessage.getState2() == 1
                    && activityPublishMessage.getState3() == 1){
                // 消息已完成，加入历史表
                ActivityPublishMessageHistory activityPublishMessageHistory = new ActivityPublishMessageHistory();
                BeanUtils.copyProperties(activityPublishMessage, activityPublishMessageHistory);
                activityPublishMessageHistoryMapper.insert(activityPublishMessageHistory);

                activityPublishMessageMapper.deleteById(activityPublishMessage.getId());
            }

            if (activityPublishMessage.getState1() == 0){
                ActivityPublish activityPublish = activityPublishMapper.selectById(activityPublishMessage.getId());

                Activity activity = new Activity();
                BeanUtils.copyProperties(activityPublish, activity);

                activityMapper.insert(activity);

                String activityStrategyJsonStr = activityPublish.getActivityStrategyInfo();

                Strategy strategy = JSON.parseObject(activityStrategyJsonStr, Strategy.class);
                strategyMapper.insert(strategy);

                String activityDetailsJsonStr = activityPublish.getActivityDetailsInfo();
                List<StrategyDetail> strategyDetails = JSON.parseArray(activityDetailsJsonStr, StrategyDetail.class);

                for (StrategyDetail strategyDetail : strategyDetails) {
                    strategyDetailMapper.insert(strategyDetail);
                }

                activityPublishMessage.setState1(1);
                activityPublishMessageMapper.updateById(activityPublishMessage);
            }
        }

        return ReturnT.SUCCESS;
    }

    @XxlJob("activityPublishHandler2")
    public ReturnT<String> activityPublishHandler2(String param) throws Exception{
        LambdaQueryWrapper<ActivityPublishMessage> queryWrapper = new LambdaQueryWrapper<>();
        List<ActivityPublishMessage> activityPublishMessages = activityPublishMessageMapper.selectList(queryWrapper);

        for (ActivityPublishMessage activityPublishMessage : activityPublishMessages) {
            if (activityPublishMessage.getState2() == 0){
                ActivityPublish activityPublish = activityPublishMapper.selectById(activityPublishMessage.getId());

                Activity activity = new Activity();
                BeanUtils.copyProperties(activityPublish, activity);

                Map<String, Object> params = new HashMap<>();
                params.put("activity", activity);

                Template template = null;
                String html;
                try {
                    template = configuration.getTemplate("src/main/resources/templates/activity.ftl");
                    html = FreeMarkerTemplateUtils.processTemplateIntoString(template, params);
                } catch (Exception e) {

                }

                // 使用Feign调用minio服务上传文件

                activityPublishMessage.setState2(1);
                activityPublishMessageMapper.updateById(activityPublishMessage);

            }
        }
        return ReturnT.SUCCESS;
    }
}


    