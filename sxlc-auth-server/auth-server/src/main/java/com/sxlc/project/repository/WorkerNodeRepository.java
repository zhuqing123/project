package com.sxlc.project.repository;

import com.sxlc.project.entity.WorkerNode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Author ZhuQing
 * @Date: 2019/4/14  20:48
 */
public interface WorkerNodeRepository extends JpaRepository<WorkerNode, Long>, JpaSpecificationExecutor<WorkerNode> {


}
