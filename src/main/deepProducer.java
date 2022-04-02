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
import java.util.*;
import org.apache.kafka.clients.admin;
import org.apache.kafka.clients.producer;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import java.util.Properties;

//Create the Java class for the Kafka Topic Producer API
public class deepProducer {

    //Construct standard Properties file
    Properties config = new Properties();
    config.put("client.id", InetAddress.getLocalHost().getHostName());
    config.put("bootstrap.servers", "host1:9092,host2:9092");
    config.put("acks", "all");
    new KafkaProducer<K,V>(config);
    //Create the Kafka Topic Producer to send records
    //private static Producer<Long, String> createProducer(){
    //}
    //Main method to run the Producer API
    static void runProducer(final int sendMessageCount) throws Exception {
      final Producer<Long, String> producer = createProducer();
      long time = System.currentTimeMillis();

      try {
          for (long index = time; index < time + sendMessageCount; index++) {
              final ProducerRecord<Long, String> record =
                      new ProducerRecord<>(TOPIC, index,
                                  "Message Produced" + index);

              RecordMetadata metadata = producer.send(record).get();

              long elapsedTime = System.currentTimeMillis() - time;
              System.out.printf("sent record(key=%s value=%s) " +
                              "meta(partition=%d, offset=%d) time=%d\n",
                      record.key(), record.value(), metadata.partition(),
                      metadata.offset(), elapsedTime);

          }
      } finally {
          producer.flush();
          producer.close();
      }
  }
    //Send Kafka Message
    final ProducerRecord<K, V> record = new ProducerRecord<>(topic, key, value);
    Future<RecordMEtadata> future = producer.send(record);
    //Exception handling for Kafka messages.
}
