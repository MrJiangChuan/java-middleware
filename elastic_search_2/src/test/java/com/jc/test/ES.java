package com.jc.test;

import com.jc.config.SpringConfig;
import com.jc.entity.Article;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
public class ES {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Test
    public void createIndex(){
        boolean index = elasticsearchTemplate.createIndex(Article.class);
        System.out.println(index);
    }

    @Test
    public void insert(){
        Map<String, Object> params = new HashMap<>();
        params.put("id", 1L);
        params.put("title","美方取消逾千名中国学生和研究人员签证 外交部回应");
        params.put("content","9月10日，外交部发言人赵立坚主持例行记者会。有记者就近期美方取消了超过1000名中国学生或者研究人员签证一事提问。赵立坚表示，一段时间以来，美方在中美人文交流领域采取了一系列消极言行，与美方自我标榜的开放自由理念背道而驰，与两国民意背道而驰，与两国开展国际人才交流背道而驰，给中美正常人员交流和人员往来带来严重消极影响，严重损害两国关系社会基础。美国政府公然采取损害中国在美留学人员合法权益的措施，是赤裸裸的政治迫害和种族歧视，严重侵犯了中国留学人员人权，美方应立即停止利用各种借口对在美中国留学生的无端限制和打压，我们支持中国留学人员依法维护他们合法正当权益，中方保留对此事作出进一步反应的权利。");
        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.doc(params);
        UpdateQuery query = new UpdateQueryBuilder()
                .withId("1")
                .withUpdateRequest(updateRequest)
                .withClass(Article.class)
                .withDoUpsert(true)
                .build();
        UpdateResponse update = elasticsearchTemplate.update(query);
        System.out.println(update);
    }

    @Test
    public void findAll(){
        SearchQuery query = new NativeSearchQueryBuilder()
                .withQuery(new QueryStringQueryBuilder("中国学生").defaultField("title").analyzer("ik_smart"))
                .withPageable(PageRequest.of(0,10))
                .build();
        List<Article> articles = elasticsearchTemplate.queryForList(query, Article.class);
        for (Article article : articles) {
            System.out.println(article);
        }
    }
}
