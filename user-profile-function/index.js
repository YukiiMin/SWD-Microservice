const express = require('express');
const { MongoClinet } = require('mongodb');
const { sendUserProfile, consumeUserProfile } = require('./kafka');
const app = express();
const port = 8084;

app.use(express.json());
// MongoDB connection
const url = 'mongodb://localhost:27019/userdbnode';
const clinet = new MongoClinet(url);

async function connectToMongo() {
    try {
        await clinet.connect();
        console.log('Connected to MongoDB');
    } catch (error) {
        console.error('Error connecting to MongoDB:', error);
    }
}

connectToMongo();

app.get('api/user/profile', async (req, res) => {
    const user = {username: 'minh', email: 'minhht@gmail.com' };
    const db = clinet.db('userdbnode');
    await db.collection('users').insertOne(user);
    await sendUserProfile(user);
    res.json(user);
});

app.listen(port, () => {
    console.log(`User service listening at http://localhost:${port}`);
    consumeUserProfile();
});



// app.get('/api/user/profile', (req, res) => {
//     res.json({
//         username: 'minh',
//         email: 'minh@example.com'
//     });
// });

// app.listen(port, () => {
//     console.log(`User service listening at http://localhost:${port}`);
// });
