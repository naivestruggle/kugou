package com.hc.kugou.service;

import com.hc.kugou.bean.IndexViewBean;

/**
 * @Author:
 * @Date:2019/4/30
 * @Description:com.hc.kugou.service
 * @Version:1.0
 */
public interface IndexService {

    /**
     * 得到所有的主页要显示的信息
     * @return  信息对象
     */
    IndexViewBean showService();
}
