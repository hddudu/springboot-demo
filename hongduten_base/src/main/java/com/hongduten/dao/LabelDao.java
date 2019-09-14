package com.hongduten.dao;

import com.hongduten.pojo.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 *  通用实现接口： 基于Hibernate自动实现
 *  save（） 新增 + 修改
 *  deleteById、
 *  findAll
 *  findAll(specification条件)
 * Created by dudu on 2019/9/14.
 */
public interface LabelDao extends JpaRepository<Label, String>, JpaSpecificationExecutor<Label> {

}
