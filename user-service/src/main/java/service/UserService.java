package service;

import lombok.AllArgsConstructor;
import org.apache.tomcat.jni.Library;
import org.springframework.stereotype.Service;
import repository.UserRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    public List<Library> getbooks() {
        return userRepository.findAll();
    }
    public  Library createLibrary(Library newLibrary) {
        return userRepository.save(newLibrary);
    }
}
