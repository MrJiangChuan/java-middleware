package com.jc.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * PUT 192.168.48.123:9200/index1
 * {
 *     "mappings": {
 *             "properties": {
 *                 "id": {
 *                 	"type": "long",
 *                     "store": true,
 *                     "index": false
 *                 },
 *                 "title": {
 *                 	"type": "text",
 *                     "store": true,
 *                     "index": true,
 *                     "analyzer":"ik_max_word",
 *                     "search_analyzer": "ik_max_word"
 *                 },
 *                 "content": {
 *                 	"type": "text",
 *                     "store": true,
 *                     "index": true,
 *                     "analyzer":"ik_max_word",
 *                     "search_analyzer": "ik_max_word"
 *                 }
 *             }
 *         },
 *     "settings":{
 *             "number_of_shards": 5,
 *             "number_of_replicas": 1
 *         }
 * }
 */
@Document(indexName = "index1", shards = 5, replicas = 1)
public class Article implements Serializable {
    @Id
    @Field(type = FieldType.Long, index = false, store = true)
    private Long id;
    @Field(type = FieldType.Text, index = true, store = true, analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private String title;
    @Field(type = FieldType.Text, index = true, store = true, analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private String content;

    public Article() {
    }

    public Article(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
