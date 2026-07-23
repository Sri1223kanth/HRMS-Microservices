package com.eazybytes.Kafka;

import com.eazybytes.Dto.EmployeeCreatedDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j

public class PayRollConsumer {

    @KafkaListener(
            topics = "employee-created",
            groupId = "payroll-group"
    )
    public void  consume(EmployeeCreatedDto event){

        log.info("Employee received by Kafka :{}",event);

    }
}
