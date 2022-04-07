//Maven Kafka Package
package test;

//Java imports declaration
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

public class deepProducerTest {
    //Testing that the Kafka Producer is being created to send String messages
    @Test
    void givenKeyValue_whenSend_thenVerifyHistory() {

        MockProducer mockProducer = new MockProducer<>(true, new StringSerializer(), new StringSerializer());

        kafkaProducer = new KafkaProducer(mockProducer);
        Future<RecordMetadata> recordMetadataFuture = kafkaProducer.send("soccer", 
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

        kafkaProducer = new KafkaProducer(mockProducer);
        Future<RecordMetadata> recordMetadataFuture = kafkaProducer.send("partition", 
        "{\"site\" : \"baeldung\"}");

        assertTrue(recordMetadataFuture.get().partition() == 1);
    }

//Testing that the Producer has some value that is not null or empty before sending.
    @Test
    void givenKeyValue_whenSend_thenReturnException() {
        MockProducer<String, String> mockProducer = new MockProducer<>(false, 
        new StringSerializer(), new StringSerializer());

        kafkaProducer = new KafkaProducer(mockProducer);
        Future<RecordMetadata> record = kafkaProducer.send("site", "{\"site\" : \"baeldung\"}");
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
