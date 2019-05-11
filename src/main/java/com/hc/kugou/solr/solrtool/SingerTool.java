package com.hc.kugou.solr.solrtool;

/**
 * @Author:
 * @Date:2019/4/5
 * @Description:com.hc.kugou.solr
 * @Version:1.0
 */
public class SingerTool {
    public static String SINGER_LISTENER_COUNT_FIELD = "singer_listener_count";
    public static String SINGER_CLASS_NAME_FIELD = "singer_class_name";
    public static String SINGER_FLAG_FIELD = "singer_flag";
    public static String[] SINGER_POINTER_FIELD = {
            "id",
            "singer_id",
            "singer_class_name",
            "singer_sindex",
            "singer_singername",
            "singer_singerid",
            "singer_imgurl",
            "singer_flag",
            "singer_listener_count"
    };

    private SingerTool(){}

}
