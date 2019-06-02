package com.english.project.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author ZhuQing
 * @Date: 2019/4/14  20:41
 */
@Entity
@Table(name = "worker_node")
@Data
public class WorkerNode {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "host_name")
    private String hostName;

    @Column(name = "port")
    private String port;

    @Column(name = "type")
    private Integer type;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(name = "launch_date")
    private Date launchDate;

    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    @Column(name = "update_time")
    private Date updateTime;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(name = "create_time")
    private Date createTime;

}
