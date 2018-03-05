package service;

import dao.UserDAO;
import domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    UserService service;

    @Mock
    UserDAO dao;

    User user;
    User user2;

    @Before
    public void setUp() throws Exception {
        service = new UserService();
        service.setdao(dao);
        user = new User("Youri", "Bio", "Password");
        user2 = new User("Ken", "Bio", "Password");

        List<User> followers = new ArrayList<>();
        followers.add(user2);

        user.getFollowers().add(user2);
        user.getFollowing().add(user2);
    }

    @Test
    public void addUser() {
        service.addUser(user);
        verify(dao, times(1)).addUser(user);
    }

    @Test
    public void removeUser() {
        service.removeUser(user);
        verify(dao, times(1)).removeUser(user);
    }

    @Test
    public void removeUserByName() {
        when(dao.findUserByName(user2.getUsername())).thenReturn(user2);
        service.removeUser(user2.getUsername());
        verify(dao, times(1)).removeUser(user2);
    }

    @Test
    public void findByName() {
        when(dao.findUserByName("Youri")).thenReturn(user);
        assertEquals(service.findByName("Youri"), user);
        verify(dao, times(1)).findUserByName("Youri");

    }

    @Test
    public void getAllUsers() {
        List<User> allUsers = new ArrayList<>();
        allUsers.add(user);
        allUsers.add(user2);

        when(dao.getAllUsers()).thenReturn(allUsers);

        assertEquals(service.getAllUsers().size(), 2);

        verify(dao, times(1)).getAllUsers();
    }

    @Test
    public void getUser() {
        when(dao.getUser(0)).thenReturn(user);
        assertEquals(service.getUser(0), user);
        verify(dao, times(1)).getUser(0);
    }

    @Test
    public void addFollower() {
        when(dao.findUserByName(user.getUsername())).thenReturn(user);
        when(dao.findUserByName(user2.getUsername())).thenReturn(user2);
        service.addFollower(user2.getUsername(), user.getUsername());
        verify(dao,times(1)).editUser(user);
        verify(dao,times(1)).editUser(user2);
    }

    @Test
    public void removeFollower() {
        when(dao.findUserByName(user.getUsername())).thenReturn(user);
        when(dao.findUserByName(user2.getUsername())).thenReturn(user2);
        service.removeFollower(user2.getUsername(), user.getUsername());
        verify(dao,times(1)).editUser(user);
        verify(dao,times(1)).editUser(user2);
    }

    @Test
    public void getUserFollowers() {


        when(dao.findUserByName(user.getUsername())).thenReturn(user);

        assertEquals(service.getUserFollowers(user.getUsername()).size(), 1);

        verify(dao, times(1)).findUserByName(user.getUsername());
    }

    @Test
    public void getUserFollowing() {
        when(dao.findUserByName(user.getUsername())).thenReturn(user);

        assertEquals(service.getUserFollowing(user.getUsername()).size(), 1);

        verify(dao, times(1)).findUserByName(user.getUsername());
    }

    @Test
    public void editUser() {
        when(dao.editUser(user)).thenReturn(true);
        service.editUser(user);
        verify(dao,times(1)).editUser(user);
    }
}