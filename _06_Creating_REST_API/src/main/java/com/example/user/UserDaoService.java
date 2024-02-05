package com.example.user;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDaoService {
    private static List<User> users = new ArrayList<>();
    private static int usersCount = 0;

    static {
        users.add(new User(++usersCount, "Adam", LocalDate.of(1990, 1, 1)));
        users.add(new User(++usersCount, "Eve", LocalDate.of(1985, 11, 23)));
        users.add(new User(++usersCount, "Jack", LocalDate.of(1995, 5, 4)));
    }

    public List<User> findAll() {
        return users;
    }

    public User findById(int id) throws UserNotFoundException {
        return users.stream()
            .filter(user -> user.getId().equals(id))
            .findFirst()
            .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    public User save(User user) {
        if (user.getId() != null) {
            return null;
        }
        user.setId(++usersCount);
        users.add(user);
        return user;
    }

    public void deleteById(int id) throws UserNotFoundException {
        User user = findById(id);
        users.remove(user);
    }
}
