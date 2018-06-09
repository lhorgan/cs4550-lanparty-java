package webdev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import webdev.repositories.ReviewRepository;
import webdev.repositories.UserRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ReviewService {

    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    UserRepository userRepository;


}
