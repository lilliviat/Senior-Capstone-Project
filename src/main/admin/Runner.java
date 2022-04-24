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

//import java utilities
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

//import SpringBoot Framework to containerize API
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
@RestController
public class Runner {
    public static void main(String[] args) {
      //Spring framework interface used for Enterprise Data-driven development.
      ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
      Sweapi obj = (Sweapi)context.getBean("consumer");

      deepFlowConsumer consume = new deepFlowConsumer(); //Utilize Dependency Injection; Instead of hardcoding each object.
      deepProducer produce = new deepProducer(); //Consumer and Producer classes implement as an interface
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
