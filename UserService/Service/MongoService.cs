using Microsoft.VisualBasic;
using MongoDB.Driver;
using System.Threading.Tasks;
using UserService.Model;

namespace UserService.Service
{
    public class MongoService
    {
        private readonly IMongoCollection<User> _users;

        public MongoService()
        {
            var client = new MongoClient("mongodb://localhost:27020/userdbcsharp");
            var database = client.GetDatabase("userdbcsharp");
            _users = database.GetCollection<User>("users");
        }

        public async Task CreateUser(User user)
        {
            await _users.InsertOneAsync(user);
        }

        public async Task<User> GetUser(String id)
        {
            return await _users.Find(u => u.Id == id).FirstOrDefaultAsync();
        }
    }
}