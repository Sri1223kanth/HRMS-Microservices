package com.Workday.Kafka;

import com.Workday.dto.EmployeeCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmployeeProducer {

    private final KafkaTemplate<String, EmployeeCreatedEvent> kafkaTemplate;

    private static final String TOPIC = "employee-created";

    public void publishEmployee(EmployeeCreatedEvent event){
        kafkaTemplate.send(TOPIC,event);
    }
}
