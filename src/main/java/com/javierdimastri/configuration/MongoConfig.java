package com.javierdimastri.configuration;

import com.mongodb.client.MongoClients;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.ImmutableMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfig;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.util.SocketUtils;

@Configuration
public class MongoConfig {

    @Value("${service.host_url}")
    private String HOST;

//    @Value("${spring.data.mongodb.uri}")
//    private String CONNECTION_STRING;
    private static final String CONNECTION_STRING = "mongodb://%s:%d";
    @Bean
    public MongoTemplate mongoTemplate() throws Exception {

        int randomPort = SocketUtils.findAvailableTcpPort();

        ImmutableMongodConfig mongoDbConfig = MongodConfig.builder()
                .version(Version.Main.PRODUCTION)
                .net(new Net(HOST, randomPort, Network.localhostIsIPv6()))
                .build();

        MongodStarter starter = MongodStarter.getDefaultInstance();
        MongodExecutable mongodExecutable = starter.prepare(mongoDbConfig);
        mongodExecutable.start();
        return new MongoTemplate(MongoClients.create(String.format(CONNECTION_STRING, HOST, randomPort)), "mongo_auth");
    }
}
