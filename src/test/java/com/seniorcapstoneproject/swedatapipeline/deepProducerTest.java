//Spring Kafka Package
package com.seniorcapstoneproject.swedatapipeline;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

//Java imports declaration
import org.apache.kafka.clients.admin;
import org.apache.kafka.clients.producer;
import org.apache.kafka.clients.producer.MockProducer;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import java.util.Properties;
import java.beans.Transient;
import java.util.Collections;

//import SpringBoot Framework to containerize API
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootTest
public class deepProducerTest {
    
    @Test
    void contextLoads(){
        
    }
    //Testing that the Kafka Producer is being created to send String messages
    @Test
    void givenKeyValue_whenSend_thenVerifyHistory() {

        MockProducer mockProducer = new MockProducer<>(true, new StringSerializer(), new StringSerializer());

        mockProducer = new KafkaProducer(mockProducer);
        Future<RecordMetadata> recordMetadataFuture = mockProducer.send("soccer", 
        "{\"site\" : \"baeldung\"}");

        assertTrue(mockProducer.history().size() == 1);
    }

//Testing if the Producer is able to write into multiple partitions
    @Test
    void givenKeyValue_whenSendWithPartitioning_thenVerifyPartitionNumber() 
    throws ExecutionException, InterruptedException {
        PartitionInfo partitionInfo0 = new PartitionInfo(TOPIC_NAME, 0, null, null, null);
        PartitionInfo partitionInfo1 = new PartitionInfo(TOPIC_NAME, 1, null, null, null);
        List<PartitionInfo> list = new ArrayList<>();
        list.add(partitionInfo0);
        list.add(partitionInfo1);

        Cluster cluster = new Cluster("kafkab", new ArrayList<Node>(), list, emptySet(), emptySet());
        this.mockProducer = new MockProducer<>(cluster, true, new EvenOddPartitioner(), 
        new StringSerializer(), new StringSerializer());

        mockProducer = new KafkaProducer(mockProducer);
        Future<RecordMetadata> recordMetadataFuture = mockProducer.send("partition", 
        "{\"site\" : \"baeldung\"}");

        assertTrue(recordMetadataFuture.get().partition() == 1);
    }

//Testing that the Producer has some value that is not null or empty before sending.
    @Test
    void givenKeyValue_whenSend_thenReturnException() {
        MockProducer<String, String> mockProducer = new MockProducer<>(false, 
        new StringSerializer(), new StringSerializer());

        mockProducer = new KafkaProducer(mockProducer);
        Future<RecordMetadata> record = mockProducer.send("site", "{\"site\" : \"baeldung\"}");
        RuntimeException e = new RuntimeException();
        mockProducer.errorNext(e);

        try {
            record.get();
        } catch (ExecutionException | InterruptedException ex) {
        assertEquals(e, ex.getCause());
        }
        assertTrue(record.isDone());
    }
}
