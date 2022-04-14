/*
*@authors 
* Lillivia T
* Nishik P
* Alejandro D
* Cole H
* Gabe M
*
*
* This is an application that produces messages
* at one end of the data pipeline.
*
* This application will use stream processing, i.e.
* the visible boundary doesn't exist, for optimal data flow.
*
* The data pipeline is often referred to as a Topic in Kafka
* , and will be referred to as a topic for the remainder of this API.
*
*
* This Producer API will create its own data to be
* stored into S3. When implemented, it will pull data
* from specified data stores into Kafka near-real time.
*/

//Maven Kafka Package
package org.apache.kafka.clients;

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
//Create the Java class for the Kafka Topic Producer API
public class deepProducer {
    //@RequestMapping("/") 
    //Create Producer
    final Producer<String, String> producer = createProducer();    
    
    //Producer Constructor
    public deepProducer(Producer<String, String> producer){
        this.producer = producer;
    }

    //Kafka send Metadata
    public Future<RecordMEtadata> send(String key, String content){
        ProducerRecord record = new ProducerRecord("swedatapipeline", key, content);
        return producer.send(record);
    }

    public void flush() {
        producer.flush();
    }
 
    public void beginTransaction() {
        producer.beginTransaction();
    }
    
    public void initTransaction() {
        producer.initTransaction();
    }

    public void commitTransaction() {
        producer.commitTransaction();
    }
    
    //Send Kafka Message
    //Exception handling for Kafka messages.
}
