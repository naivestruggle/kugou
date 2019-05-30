package com.hc.kugou.service.impl;

import com.hc.kugou.bean.custombean.CustomMusic;
import com.hc.kugou.mapper.ListMapper;
import com.hc.kugou.service.ListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

/**
 * @author ck
 * @create 2019-05-27 16:17
 */
@Service("listService")
@Transactional(rollbackFor = {SQLException.class})
public class ListServiceImpl implements ListService {

    @Autowired
    private ListMapper listMapper;

    @Override
    public List<CustomMusic> querySoarList() {
        return listMapper.querySoarList();
    }

    @Override
    public List<CustomMusic> queryTop500() {
        return listMapper.queryTop500();
    }

    @Override
    public List<CustomMusic> queryNewCNList(Integer className) {

        //判断语种


        return null;
    }
}
