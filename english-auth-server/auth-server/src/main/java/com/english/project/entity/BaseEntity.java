package com.english.project.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author ZhuQing
 * @Date: 2019/4/14  16:41
 */

@MappedSuperclass
@Data
public class BaseEntity implements Serializable {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "uid-generator")
    @GenericGenerator(name = "uid-generator", strategy = "com.english.project.utils.SnowflakeId")
    @Column(name = "id")
    private Long id;

    /**
     * 是否删除0未删除，1已经删除
     */
    @Column(name = "del_flag",columnDefinition = "tinyint default 0")
    private Byte delFlag=0;

    /**
     *创建人id
     */
    @Column(name = "founder_id")
    private Long founderId=1L;

    /**
     * 创建时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 修改人id
     */
    @Column(name = "modify_id")
    private Long modifyId=1L;

    /**
     * 修改时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    @Column(name = "update_time")
    private Date updateTime;
}
