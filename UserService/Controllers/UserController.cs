using Microsoft.AspNetCore.Mvc;
using System.Threading.Tasks;
using UserService.Model;
using UserService.Service;

namespace UserService.Controllers
{
    [ApiController]
    [Route("api/user-csharp")]
    public class UserController : ControllerBase
    {
        private readonly KafkaProducerService _producerService;
        private readonly MongoService _mongoService;

        public UserController(MongoService mongoService, KafkaProducerService producerService)
        {
            _mongoService = mongoService;
            _producerService = producerService;
        }

        [HttpGet("profile")]
        public async Task<IActionResult> GetProfile()
        {
            // var user = new { username = "minh", email = "minhC-Sharp@gmail.com" };
            var user = new User { Id = System.Guid.NewGuid().ToString(), Username = "minh", Email = "minhCsharp@gmail.com" };
            await _mongoService.CreateUser(user);
            await _producerService.SendUserProfile(user.Username, user.Email);
            return Ok(user);
        }


        // [HttpGet("profile")]
        // public IActionResult GetProfile()
        // {
        //     return Ok(new
        //     {
        //         username = "minh",
        //         email = "minhhtse184760@fpt.edu.vn"
        //     });
        // }
    }
}