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
import org.apache.kafka.clients.deepFlowConsumer;
import org.apache.kafka.clients.deepProducer;
import org.apache.kafka.clients.admin.Admin;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.CreateTopicsOptions;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.KafkaFuture;
import org.apache.kafka.common.config.TopicConfig;

//import java streaming for files
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

//import JSON utilities
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

//import jaav utilities
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

//import SpringBoot Framework to containerize API
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class Runner {
    public static void main(String[] args) {
      deepFlowConsumer consume = new deepFlowConsumer();
      deepProducer produce = new deepProducer();
        try {
            File myObj = new File("StormsEvents.csv");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
              String data = myReader.nextLine();
              System.out.println(data);
            }
            myReader.close();
          } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }

        deepProducer deepproducer = new deepProducer(producer);
            if (args.length == 0) {
                runProducer(5);
            } else {
                runProducer(Integer.parseInt(args[0]));
            }
    }
}
