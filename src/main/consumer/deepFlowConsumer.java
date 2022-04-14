/*
*@authors 
* Lillivia T
* Nishik P
* Alejandro D
* Cole H
* Gabe M
*
*
* This application consumes messages at one
* end of a data pipeline.
* This application will use stream processing, i.e.
* the visible boundary doesn't exist, for optimal data flow.
*
* The data pipeline is often referred to as a Topic in Kafka
* , and will be referred to as a topic for the remainder of this API.
*
*
* This Consumer API will send the data from the Producer to be
* stored into S3. When implemented, it will send data
* from specified data stores into Kafka near-real time.
*/

//Maven Kafka Package
package org.apache.kafka.clients;

//Java import statements
import org.apache.kafka.clients.admin;
import org.apache.kafka.clients.producer;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.Collections;

//import SpringBoot Framework to containerize API
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//SpringBoot Java Application to containerize API
//@SpringBootApplication
//@RestController

public class deepFlowConsumer {
      // Create the consumer using props.
      final Consumer<String, String> consumer =
                                  new KafkaConsumer<>(props);

      // Subscribe to the topic.
      consumer.subscribe(Collections.singletonList(TOPIC));
        return consumer;
    }

    static void runConsumer() throws InterruptedException {
        final Consumer<String, String> consumer = createConsumer();

        final int giveUp = 100;   int noRecordsCount = 0;

        while (true) {
            final ConsumerRecords<Long, String> consumerRecords =
                    consumer.poll(1000);

            if (consumerRecords.count()==0) {
                noRecordsCount++;
                if (noRecordsCount > giveUp) break;
                else continue;
            }

            consumerRecords.forEach(record -> {
                System.out.printf("Consumer Record:(%d, %s, %d, %d)\n",
                        record.key(), record.value(),
                        record.partition(), record.offset());
            });

            consumer.commitAsync();
        }
        consumer.close();
        System.out.println("DONE");
    }
}  
