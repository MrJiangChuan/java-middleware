package com.jc;

import com.jc.entity.Article;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.support.IndicesOptions;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.document.Document;
import org.springframework.data.elasticsearch.core.query.*;

import java.time.Duration;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class ElasticSearch3ApplicationTests {

	@Autowired
	private ElasticsearchRestTemplate elasticsearchRestTemplate;

	@Test
	void createIndex(){
		elasticsearchRestTemplate.indexOps(Article.class).create();
		Document mapping = elasticsearchRestTemplate.indexOps(Article.class).createMapping(Article.class);
		System.out.println(mapping);
	}

	@Test
	void deleteIndex(){
		boolean delete = elasticsearchRestTemplate.indexOps(Article.class).delete();
		System.out.println(delete);
	}

	@Test
	void insert() {
		Article save = null;
		for (long i = 5; i < 10; i++) {
			save = elasticsearchRestTemplate.save(new Article(i, "从共享病毒信息到支援海外抗疫，中国一直在行动", "2020年的第二天，中国疾病预防控制中心病毒病预防控制所大门口，该所党委书记武桂珍焦急地等待着。中午12时，湖北省疾病预防控制中心送检的4例标本终于抵达，并迅速被送进了生物安全三级实验室。"));
		}
		System.out.println(save);
	}

	@Test
	void delteById(){
		String delete = elasticsearchRestTemplate.delete("29", Article.class);
		System.out.println(delete);
	}

	@Test
	void search(){

		Query query  = new NativeSearchQueryBuilder()
				.withQuery(new QueryStringQueryBuilder("我想知道病毒是什么东西smart扶贫jp工作非常重要？sdsgg\\*").defaultField("title").analyzer("ik_max_word"))
				.withPageable(PageRequest.of(0,20))
				.build();
		SearchHits<Article> search = elasticsearchRestTemplate.search(query, Article.class);
		search.forEach(articleSearchHit -> System.out.println(articleSearchHit.getContent()));
	}



}
