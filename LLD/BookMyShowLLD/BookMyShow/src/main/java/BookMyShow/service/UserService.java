package BookMyShow.service;

import BookMyShow.exception.InvalidEmailException;
import BookMyShow.exception.InvalidPasswordException;
import BookMyShow.exception.InvalidUserException;
import BookMyShow.exception.UserDuplicateEmailException;
import BookMyShow.model.*;
import BookMyShow.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void addTicket(Ticket ticket, int userId) throws Exception{
        Optional<User> savedUserOpt = userRepository.findById(userId);
        if (savedUserOpt.isPresent()) {
            User savedUser = savedUserOpt.get();
            List<Ticket> tickets = savedUser.getTickets();
            tickets.add(ticket);
            savedUser.setTickets(tickets);
            userRepository.save(savedUser);
        }
        else throw new InvalidUserException("Invalid user");
    }

    public User login(String email, String password) throws Exception {
        User savedUser = userRepository.findUserByEmail(email);

        if (savedUser == null) {
            throw new InvalidEmailException("email not valid");
        }
        BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
        if (encode.matches(password, savedUser.getPassword())) {
            return savedUser;
        }
        throw new InvalidPasswordException("Invalid password");
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public User signUp(String name, String email, String password) throws Exception {
        User savedUser = userRepository.findUserByEmail(email);
        if(savedUser != null){
            throw new UserDuplicateEmailException("User with same email exists");
        }
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
        user.setPassword(encode.encode(password));
        user.setTickets(new ArrayList<>());
        return userRepository.save(user);
    }
}
