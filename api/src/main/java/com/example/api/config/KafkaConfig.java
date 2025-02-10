package com.example.api.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.serializer.JsonSerializer;
import reactor.core.scheduler.Schedulers;
import reactor.kafka.receiver.ReceiverOptions;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderOptions;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@Configuration
public class KafkaConfig {
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;

    @Value("${spring.kafka.consumer.auto-offset-reset}")
    private String autoOffsetResetConf;

    @Value("${spring.kafka.producer.acks}")
    private String acks;

    @Bean("kafkaSender")
    public KafkaSender<String, Object> kafkaSender() {
        SenderOptions<String, Object> senderOptions = SenderOptions.create(getProducerProps());
        senderOptions.scheduler(Schedulers.parallel());
        senderOptions.closeTimeout(Duration.ofSeconds(5));
        return KafkaSender.create(senderOptions);
    }

    @Bean
    public ReceiverOptions<String, Object> receiverOptions() {
        return ReceiverOptions.<String, Object>create(getConsumerProps())
                .subscription(
                        new ArrayList<>(){{
//                            add(TopicConst.Consumer.EVENT_MONITORING_STATUS);
//                            add(TopicConst.Consumer.LOG_EVENT_MONITORING_STATUS);
//                             add(TopicConst.Consumer.VISTA_TO_STREAM_API);
                              add("vista-to-stream-daemon-metric-search");
                        }})
                .commitBatchSize(0)
                .commitInterval(Duration.ofSeconds(1))

            ;
    }

    private Map<String, Object> getProducerProps() {
        return new HashMap<>() {{
            put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
            put(ProducerConfig.MAX_BLOCK_MS_CONFIG, 2000);
            put(ProducerConfig.ACKS_CONFIG, acks);
            put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
            put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        }};
    }

    private Map<String, Object> getConsumerProps() {
        return new HashMap<>() {{
            put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
            put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
            put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, autoOffsetResetConf);
            put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
            put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        }};
    }

}
