/*
*@authors 
* Lillivia T
* Nishik P
* Alejandro D
* Cole H
* Gabe M
*
*/

package com.seniorcapstoneproject.swedatapipeline;

//import for kafka 
//import org.apache.kafka.clients.deepFlowConsumer;
//import org.apache.kafka.clients.deepProducer;
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

//import java utilities
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

//import SpringBoot Framework to containerize API
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class SwedatapipelineApplication {
    public static void main(String[] args) {

      //ApplicationContext apc = SpringApplication.run(SwedatapipelineApplication.class, args);
      SpringApplication.run(SwedatapipelineApplication.class, args);
    }
@Bean
        CommandLineRunner commandLineRunner(KafkaTemplate<String, String> kafkaTemplate){
            return args ->{
                kafkaTemplate.send(topic="swedatapipeline", data="StormEvents.csv");
            };
        }
      /*deepFlowConsumer consume = new deepFlowConsumer(); //Utilize Dependency Injection; Instead of hardcoding each object.
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
            }*/
    }

