package com.nttdata.credits.Controllers.Kafka;

import com.nttdata.credits.Controllers.Producer.KafkaStringProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/kafka")
public class KafkaController {

    private final KafkaStringProducer kafkaStringProducer;

    @Autowired
    KafkaController(KafkaStringProducer kafkaStringProducer) {
        this.kafkaStringProducer = kafkaStringProducer;
    }

    @PostMapping(value = "/publish")
    public Mono<String> sendMessageToKafkaTopic(@RequestParam("message") String message) {
        this.kafkaStringProducer.sendMessage(message);
        return Mono.just(message);
    }
}