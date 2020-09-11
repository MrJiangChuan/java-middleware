package com.jc.test;

import com.jc.entity.Article;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class ElasticTest {


    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Test
    public void createIndex(){
        boolean index = elasticsearchTemplate.createIndex(Article.class);
        System.out.println(index);
    }

    @Test
    public void criteriaQuery(){
        CriteriaQuery criteriaQuery = new CriteriaQuery(new Criteria("title").contains("中国").and("content").contains("中国"));
        List<Article> articles = elasticsearchTemplate.queryForList(criteriaQuery, Article.class);
        for (Article article : articles) {
            System.out.println(article);
        }

    }

    @Test
    public void searchQuery(){

        SearchQuery stringQuery = new NativeSearchQueryBuilder()
//                .withQuery(new QueryStringQueryBuilder("还有你和我").defaultField("title").analyzer("standard"))
                .withQuery(new QueryStringQueryBuilder("还有你和我").defaultField("title").analyzer("ik_smart"))
                .withQuery(new QueryStringQueryBuilder("中国").defaultField("content").analyzer("ik_smart"))
//                .withQuery(new TermQueryBuilder("title","我爱中国"))
//                .withQuery(new TermQueryBuilder("content","中国"))
                .withPageable(PageRequest.of(0, 20)).build();
        List<Article> articles = elasticsearchTemplate.queryForList(stringQuery, Article.class);
        for (Article article : articles) {
            System.out.println(article);
        }

    }

    /**
     * 多条件查询 must == AND notmust == NOT should must(should A, should B) == A OR B
     */
    @Test
    public void boolQuery(){

        SearchQuery stringQuery = new NativeSearchQueryBuilder()

//                .withQuery(new BoolQueryBuilder().should( new QueryStringQueryBuilder("女人").defaultField("title").analyzer("ik_smart"))
//                .must(new QueryStringQueryBuilder("女人").defaultField("content").analyzer("ik_smart")))

                .withQuery(new BoolQueryBuilder().must(
                        new BoolQueryBuilder().should(new QueryStringQueryBuilder("中国").defaultField("title").analyzer("ik_smart"))
                        .should(new QueryStringQueryBuilder("中国").defaultField("content").analyzer("ik_smart"))
                ))

//                .withQuery(new BoolQueryBuilder().must( new TermQueryBuilder("title","特朗普"))
//                        .must(new TermQueryBuilder("content","特朗普")))

                .withPageable(PageRequest.of(0, 20)).build();

        List<Article> articles = elasticsearchTemplate.queryForList(stringQuery, Article.class);
        for (Article article : articles) {
            System.out.println(article);
        }

    }
}
