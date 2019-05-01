package com.hc.kugou.service.impl;

import com.hc.commons.MvUtils;
import com.hc.kugou.bean.Mv;
import com.hc.kugou.mapper.MvMapper;
import com.hc.kugou.service.MvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:
 * @Date:2019/5/1
 * @Description:com.hc.kugou.service.impl
 * @Version:1.0
 */
@Service("mvService")
public class MvServiceImpl implements MvService {
    @Autowired
    private MvMapper mvMapper;
    /**
     * 根据名字模糊查找mv
     * 先在数据库查询  如果没有就调用python脚本查询
     *
     * @param mvName mv名
     * @return Mv对象
     */
    @Override
    public Mv findByName(String mvName) {
        List<Mv> mvList = mvMapper.findMvByName(mvName);
        Mv mv = null;
        if(mvList == null || mvList.size() == 0){
            //数据库中没有 调用python
            mv = MvUtils.getMv(mvName);
            final Mv insertMv = mv;
            //将这条mv存入数据库
            new Thread(){
                @Override
                public void run() {
                    synchronized (MvServiceImpl.class){
                        mvMapper.insert(insertMv);
                    }
                }
            }.start();

        }else{
            //数据库中有  返回第一条
            mv = mvList.get(0);
        }
        return mv;
    }
}
