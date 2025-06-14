package org.minhht.loginfunction.api;


import org.minhht.loginfunction.model.Session;
import org.minhht.loginfunction.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
public class LoginController {
    @Autowired
    private SessionRepository sessionRepository;

    @PostMapping
    public Session creatSession(@RequestBody Session session) {
        // Save the session to the database
        return sessionRepository.save(session);
    }

    @GetMapping("/{id}")
    public Session getSession(@PathVariable String id) {
        return sessionRepository.findById(id).orElse(null);
    }


    // @GetMapping("/displayMessage")
    // public ResponseEntity<String> showMessage() {;
    //     return ResponseEntity.ok("Login Function is working!");
    // }

}
