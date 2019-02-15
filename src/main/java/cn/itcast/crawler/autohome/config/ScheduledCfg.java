package cn.itcast.crawler.autohome.config;

import cn.itcast.crawler.autohome.job.CloseConnectJob;
import cn.itcast.crawler.autohome.job.CrawlerAutohomeJob;
import org.quartz.CronTrigger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
public class ScheduledCfg {

    //定义关闭无效连接任务
    @Bean("closeConnectJobBean")
    public JobDetailFactoryBean closeConnectJobBean(){
        JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();

        jobDetailFactoryBean.setApplicationContextJobDataKey("context");
        jobDetailFactoryBean.setJobClass(CloseConnectJob.class);
        jobDetailFactoryBean.setDurability(true);
        return jobDetailFactoryBean;
    }

    //定义关闭无效连接触发器
    @Bean("closeConnectJobTrigger")
    public CronTriggerFactoryBean closeConnectJobTrigger(@Qualifier(value = "closeConnectJobBean") JobDetailFactoryBean jobDetailFactoryBean){
        CronTriggerFactoryBean trigger = new CronTriggerFactoryBean();
        trigger.setJobDetail(jobDetailFactoryBean.getObject());
        trigger.setCronExpression("0/5 * * * * ? ");
        return trigger;
    }

    //定义调度器
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(CronTrigger[] cronTriggerImpl){
        SchedulerFactoryBean bean = new SchedulerFactoryBean();
        bean.setTriggers(cronTriggerImpl);
        return bean;
    }

    // 定义爬取汽车之家任务
    @Bean("crawlerAutohomeJobBean")
    public JobDetailFactoryBean crawlerAutohomeJobBean() {
        JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
        jobDetailFactoryBean.setApplicationContextJobDataKey("context");
        jobDetailFactoryBean.setJobClass(CrawlerAutohomeJob.class);
        jobDetailFactoryBean.setDurability(true);

        return jobDetailFactoryBean;
    }

    // 定义爬取汽车之家触发器
    @Bean("crawlerAutohomeJobTrigger")
    public CronTriggerFactoryBean crawlerAutohomeJobTrigger(
            @Qualifier(value = "crawlerAutohomeJobBean") JobDetailFactoryBean itemJobBean) {
        CronTriggerFactoryBean tigger = new CronTriggerFactoryBean();
        tigger.setJobDetail(itemJobBean.getObject());
        tigger.setCronExpression("0/5 * * * * ? ");
        return tigger;
    }




}
