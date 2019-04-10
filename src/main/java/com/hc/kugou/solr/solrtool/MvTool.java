package com.hc.kugou.solr.solrtool;

/**
 * @Author:
 * @Date:2019/4/5
 * @Description:com.hc.kugou.solr
 * @Version:1.0
 */
public class MvTool {
    public static final String MV_ID_FIELD = "mv_id";
    public static final String MV_LISTENER_COUNT_FIELD = "mv_listener_count";
    public static final String MV_CLASS_NAME_FIELD = "mv_class_name";
    public static final String MV_NAME_FIELD = "mv_name";
    public static final String[] MV_POINT_FIELDS_ALL = {
            "id",
            "mv_id",
            "mv_hash",
            "mv_name",
            "mv_hc_url",
            "mv_hd_url",
            "mv_bd_url",
            "mv_head_image",
            "mv_update_time",
            "mv_class_name",
            "mv_listener_count"
    };
}
