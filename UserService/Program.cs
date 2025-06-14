using Microsoft.AspNetCore.Builder;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Hosting;

var builder = WebApplication.CreateBuilder(args);

// Add services to the container.
builder.Services.AddControllers();
builder.Services.AddSingleton<KafkaProducerService>();
builder.Services.AddSingleton<KafkaConsumerService>();
builder.Services.AddHostedService<Worker>();
builder.WebHost.ConfigureKestrel(options =>
{
    options.ListenAnyIP(8085); // Configure to listen on port 8085
});

var app = builder.Build();

// Configure the HTTP request pipeline.
app.UseHttpsRedirection();
app.UseAuthorization();
app.MapControllers();

app.Run();

public class Worker : BackgroundService
{
    private readonly KafkaConsumerService _consumerService;

    public Worker(KafkaConsumerService consumerService)
    {
        _consumerService = consumerService;
    }

    protected override Task ExecuteAsync(CancellationToken stoppingToken)
    {
        _consumerService.ConsumeUserProfile();
        return Task.CompletedTask;
    }
}