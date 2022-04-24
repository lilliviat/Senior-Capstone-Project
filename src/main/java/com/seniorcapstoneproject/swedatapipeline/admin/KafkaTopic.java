/*
*@authors 
* Lillivia T
* Nishik P
* Alejandro D
* Cole H
* Gabe M
*
*/

package main.admin;

//import for kafka 
import org.apache.kafka.clients.deepProducer;
import org.apache.kafka.clients.admin.Admin;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.CreateTopicsOptions;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.KafkaFuture;
import org.apache.kafka.common.config.TopicConfig;

//import java utilities
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class KafkaTopic {
    
    //Properties constructor to set this.properties = properties
    public void KafkaTopic(){
        this.properties = properties;
    }

    //Create Kafka Topic function on run
    public void createTopic(String topicName) throws Exception {
        try (Admin admin = Admin.create(properties)) {
            int partitions = 1;
            short replicationFactor = 1;
            NewTopic newTopic = new NewTopic(topicName, partitions, replicationFactor);

            CreateTopicsResult result = admin.createTopics(Collections.singleton(newTopic));

            //Get the async result for the new topic creation
            KafkaFuture<Void> future = result.values()
                .get(topicName);

            //Call get() to block until topic creation has completed or failed
            future.get();
        }
    }

}

