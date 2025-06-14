using Confluent.Kafka;
using System.Text.Json;
using System.Threading.Tasks;

public class KafkaProducerService
{
    private readonly IProducer<Null, string> _producer;

    public KafkaProducerService()
    {
        var config = new ProducerConfig { BootstrapServers = "localhost:9092" };
        _producer = new ProducerBuilder<Null, string>(config).Build();
    }

    public async Task SendUserProfile(string username, string email)
    {
        var message = JsonSerializer.Serialize(new { username, email });
        await _producer.ProduceAsync("user-profile-topic", new Message<Null, string> { Value = message });
    }
}