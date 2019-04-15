package com.hc.kugou.bean.custombean;

import com.hc.kugou.bean.Mv;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author:
 * @Date:2019/4/7
 * @Description:com.hc.kugou.bean.custombean
 * @Version:1.0
 */
@Data
public class CustomMv extends Mv implements Serializable {
    /**
     * 高亮
     */
    private String highlight;

    /**
     * mv高亮作者名
     */
    private String mvHighlightAuthorName;
    public String getMvHighlightAuthorName(){
        return highlight.split("-")[0];
    }

    /**
     * 高亮mv名
     */
    private String mvHighlightNamne;
    public String getMvHighlightNamne(){
        return highlight.split("-")[1];
    }

}
