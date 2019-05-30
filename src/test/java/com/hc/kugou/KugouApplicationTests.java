package com.hc.kugou;

import com.hc.commons.Code;
import com.hc.commons.MailUtils;
import com.hc.commons.StringUtils;
import com.hc.kugou.bean.Music;
import com.hc.kugou.bean.Mv;
import com.hc.kugou.bean.custombean.CustomMusic;
import com.hc.kugou.mapper.*;
import com.hc.kugou.service.IndexService;
import com.hc.kugou.solr.MusicSolr;
import org.apache.commons.io.input.ReaderInputStream;
import org.apache.solr.client.solrj.SolrClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KugouApplicationTests {
    @Test
    public void fun1(){
        System.out.println(1);
    }
}
