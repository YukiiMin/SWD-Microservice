using Confluent.Kafka;
using System;
using System.Threading;

public class KafkaConsumerService
{
    public void ConsumeUserProfile()
    {
        var config = new ConsumerConfig
        {
            BootstrapServers = "localhost:9092",
            GroupId = "user-group",
            AutoOffsetReset = AutoOffsetReset.Earliest
        };

        using var consumer = new ConsumerBuilder<Null, string>(config).Build();
        consumer.Subscribe("user-profile-topic");

        while (true)
        {
            var result = consumer.Consume(CancellationToken.None);
            Console.WriteLine($"C# Consumer Received: {result.Message.Value}");
        }
    }
}