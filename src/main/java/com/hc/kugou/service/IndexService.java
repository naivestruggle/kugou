package com.hc.kugou.service;

import com.hc.kugou.bean.custombean.IndexViewBean;

/**
 * @Author:
 * @Date:2019/4/30
 * @Description:com.hc.kugou.service    主页业务类
 * @Version:1.0
 */
public interface IndexService {

    /**
     * 得到所有的主页要显示的信息
     * @return  信息对象
     */
    IndexViewBean showService();
}
