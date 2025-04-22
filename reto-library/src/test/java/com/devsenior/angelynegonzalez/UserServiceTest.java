package com.devsenior.angelynegonzalez;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.devsenior.angelynegonzalez.exception.NotFoundException;
import com.devsenior.angelynegonzalez.service.UserService;

public class UserServiceTest {

    private UserService service;

    @BeforeEach
    void setup() {
        service = new UserService();
    }

    @Test
    void testAddUserWithoutRegisterDate() throws NotFoundException {
        // GIVEN
        var id = "user123";
        var name = "Alice";
        var email = "alice@example.com";

        // WHEN
        service.addUser(id, name, email);

        // THEN
        var user = service.getUserById(id);
        assertNotNull(user);
        assertEquals(id, user.getId());
        assertEquals(name, user.getName());
        assertEquals(email, user.getEmail());
        assertNotNull(user.getRegisterDate()); // Should be initialized to LocalDate.now()
    }

    @Test
    void testAddUserWithRegisterDate() throws NotFoundException {
        // GIVEN
        var id = "user456";
        var name = "Bob";
        var email = "bob@example.com";
        var registerDate = LocalDate.of(2025, 4, 20);

        // WHEN
        service.addUser(id, name, email, registerDate);

        // THEN
        var user = service.getUserById(id);
        assertNotNull(user);
        assertEquals(id, user.getId());
        assertEquals(name, user.getName());
        assertEquals(email, user.getEmail());
        assertEquals(registerDate, user.getRegisterDate());
    }

    @Test
    void testGetUserByIdExistingUser() throws NotFoundException {
        // GIVEN
        var id = "user789";
        var name = "Charlie";
        var email = "charlie@example.com";
        service.addUser(id, name, email);

        // WHEN
        var user = service.getUserById(id);

        // THEN
        assertNotNull(user);
        assertEquals(id, user.getId());
        assertEquals(name, user.getName());
        assertEquals(email, user.getEmail());
    }

    @Test
    void testGetUserByIdNonExistingUser() {
        // GIVEN
        var id = "nonexistent";

        // WHEN - THEN
        assertThrows(NotFoundException.class, () -> service.getUserById(id));
    }

    @Test
    void testUpdateUserEmailExistingUser() throws NotFoundException {
        // GIVEN
        var id = "user101";
        var name = "Diana";
        var email = "diana@oldemail.com";
        service.addUser(id, name, email);
        var newEmail = "diana@newemail.com";

        // WHEN
        service.updateUserEmail(id, newEmail);

        // THEN
        var updatedUser = service.getUserById(id);
        assertEquals(newEmail, updatedUser.getEmail());
    }

    @Test
    void testUpdateUserEmailNonExistingUser() {
        // GIVEN
        var id = "nonexistent";
        var newEmail = "test@example.com";

        // WHEN - THEN
        assertThrows(NotFoundException.class, () -> service.updateUserEmail(id, newEmail));
    }

    @Test
    void testUpdateUserNameExistingUser() throws NotFoundException {
        // GIVEN
        var id = "user112";
        var name = "Eve";
        var email = "eve@example.com";
        service.addUser(id, name, email);
        var newName = "Evelyn";

        // WHEN
        service.updateUserName(id, newName);

        // THEN
        var updatedUser = service.getUserById(id);
        assertEquals(newName, updatedUser.getName());
    }

    @Test
    void testUpdateUserNameNonExistingUser() {
        // GIVEN
        var id = "nonexistent";
        var newName = "Test User";

        // WHEN - THEN
        assertThrows(NotFoundException.class, () -> service.updateUserName(id, newName));
    }

    @Test
    void testDeleteExistingUser() throws NotFoundException {
        // GIVEN
        var id = "user131";
        var name = "Frank";
        var email = "frank@example.com";
        service.addUser(id, name, email);

        // WHEN
        service.deleteUser(id);

        // THEN
        assertThrows(NotFoundException.class, () -> service.getUserById(id));
        assertEquals(0, service.getAllUsers().size()); // Assuming only this user was added
    }

    @Test
    void testDeleteNonExistingUser() {
        // GIVEN
        var id = "nonexistent";

        // WHEN - THEN
        assertThrows(NotFoundException.class, () -> service.deleteUser(id));
    }

    @Test
    void testGetAllUsersEmptyList() {
        // GIVEN - No users added

        // WHEN
        var users = service.getAllUsers();

        // THEN
        assertNotNull(users);
        assertTrue(users.isEmpty());
    }

    @Test
    void testGetAllUsersWithMultipleUsers() {
        // GIVEN
        service.addUser("user201", "Grace", "grace@example.com");
        service.addUser("user202", "Henry", "henry@example.com");

        // WHEN
        var users = service.getAllUsers();

        // THEN
        assertNotNull(users);
        assertEquals(2, users.size());
        assertTrue(users.stream().anyMatch(user -> user.getId().equals("user201")));
        assertTrue(users.stream().anyMatch(user -> user.getId().equals("user202")));
    }
}