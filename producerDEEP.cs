using KafkaNet;
using KafkaNet.Model;
using KafkaNet.Protocol;
using Confluent.Kafka;

static string ReadAllText(string path)
{
    return File.ReadAllText(path);
}

static void SendToKafka(string stream)
{
    string brokerList = "127.0.0.1:9092";
    string topicName = "test.topic";

    var config = new ProducerConfig {BootstrapServers = brokerList };

    using (var producer = new ProducerBuilder<string, string>(config).Build())
    {
        try
        {
            var deliveryReport = producer.ProduceAsync(topicName, new Message<string, string> { Key = "test", Value = stream });
            producer.Flush();                                               
         }
         catch (ProduceException<string, string> e)
         {
             Console.WriteLine($"failed to deliver message: {e.Message} [{e.Error.Code}]");
         }
     }
}