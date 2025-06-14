const { Kafka } = require('kafkajs');

const kafka = new Kafka({
    clientId: 'user-service-node',
    brokers: ['localhost:9092']
});

const producer = kafka.producer();
const consumer = kafka.consumer({ groupId: 'user-group' });

async function sendUserProfile(username, email) {
    await producer.connect();
    await producer.send({
        topic: 'user-profile-topic',
        messages: [{ value: JSON.stringify({ username, email }) }]
    });
    await producer.disconnect();
}

async function consumeUserProfile() {
    await consumer.connect();
    await consumer.subscribe({ topic: 'user-profile-topic', fromBeginning: true });
    await consumer.run({
        eachMessage: async ({ topic, partition, message }) => {
            console.log(`Node.js Consumer Received: ${message.value.toString()}`);
        }
    });
}

module.exports = { sendUserProfile, consumeUserProfile };