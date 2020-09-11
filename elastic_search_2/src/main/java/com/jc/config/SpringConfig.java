package com.jc.config;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Configuration
@ComponentScan("com.jc")
public class SpringConfig {

    @Bean(name = "elasticsearchTemplate")
    public ElasticsearchTemplate createES(Client client){
        return new ElasticsearchTemplate(client);
    }


    @Bean(name = "transportClient")
    public Client createClient() throws UnknownHostException {
        Settings settings = Settings.builder()
                .put("cluster.name", "my-application")
                .build();
        TransportClient client = new PreBuiltTransportClient(settings);
        client.addTransportAddresses(new InetSocketTransportAddress(InetAddress.getByName("192.168.48.120"),9300),
                new InetSocketTransportAddress(InetAddress.getByName("192.168.48.121"),9300),
                new InetSocketTransportAddress(InetAddress.getByName("192.168.48.122"),9300));
        return client;
    }
}
