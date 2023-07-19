package org.spiderflow.core.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 爬虫持久化实体类
 */
@TableName("sp_flow")
@Data
public class SpiderFlow {

    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 定时任务表达式
     */
    private String cron;

    private String name;

    /**
     * xml流程图
     */
    private String xml;

    private String enabled;

    private Date createDate;

    private Date lastExecuteTime;

    private Date nextExecuteTime;

    /**
     * 定时执行的执行次数
     */
    private Integer executeCount;

    @TableField(exist = false)
    private Integer running;


    public SpiderFlow() {
    }

    public SpiderFlow(String id, String name) {
        this.id = id;
        this.name = name;
    }

}
