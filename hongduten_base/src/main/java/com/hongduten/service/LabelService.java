package com.hongduten.service;

import com.hongduten.dao.LabelDao;
import com.hongduten.pojo.Label;
import com.hongduten.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created by dudu on 2019/9/14.
 */
@Service
public class LabelService {

    @Autowired
    private LabelDao labelDao;

    @Autowired
    private IdWorker idWorker;

    /*** 查询全部标签 * @return */
    public List<Label> findAll() {
        return labelDao.findAll();
    }

    /**
     * 创建条件
     * @param serachMap
     * @return
     */
    private Specification<Label> createSpecification(final Map serachMap) {
        return new Specification() {
            /**
             *
             * @param root 根
             * @param criteriaQuery 条件
             * @param cb 拼接
             * @return
             */
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if(serachMap.get("labelname") != null && !"".equals(serachMap.get("labelname"))) {
                    Predicate pe = cb.like(root.get("labelname").as(String.class), "%" + (String)serachMap.get("labelname") + "%");
                    predicates.add(pe);
                }
                if(serachMap.get("state") != null && !"".equals(serachMap.get("state"))) {
                    Predicate pe = cb.equal(root.get("state").as(String.class), (String)serachMap.get("state"));
                    predicates.add(pe);
                }
                if(serachMap.get("recommend") != null && !"".equals(serachMap.get("recommend"))) {
                    Predicate pe = cb.equal(root.get("recommend").as(String.class), (String)serachMap.get("recommend"));
                    predicates.add(pe);
                }
                //对象数组
                Predicate[] predicates1 = new Predicate[predicates.size()];
                Predicate[] predicates2 = predicates.toArray(predicates1);
                Predicate retPredicate = cb.and(predicates2);
                return retPredicate;
            }
        };
    }

    /**
     * 条件查询
     * @param searchMap
     * @return
     */
    public List<Label> findSearch(Map searchMap) {
        Specification specification = createSpecification(searchMap);
        return labelDao.findAll(specification);
    }

    /**
     * 带分页的条件查询
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    public Page<Label> findSearch(Map searchMap, int page, int size) {
        Specification specification = createSpecification(searchMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return labelDao.findAll(specification, pageRequest);
    }

    /*** 根据ID查询标签 * @return */
    public Label findById(String id) {
        Optional<Label> getLabel = labelDao.findById(id);
        if(getLabel != null && getLabel.isPresent()) {
            return getLabel.get();
        }
        return null;
    }

    /*** 增加标签 * @param label */
    public void add(Label label) {
        //使用了分布式id
        label.setId(idWorker.nextId() + "");//设置ID
        labelDao.save(label);
    }

    // /*** 修改标签 * @param label */
    public void update(Label label) {
        labelDao.save(label);
    }

    /*** 删除标签 * @param id */

    public void deleteById(String id) {
        labelDao.deleteById(id);
    }
}
