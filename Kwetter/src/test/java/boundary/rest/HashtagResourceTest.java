package boundary.rest;

import domain.Hashtag;
import domain.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import service.HashtagService;
import service.UserService;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.get;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.*;

public class HashtagResourceTest {

    @Inject
    HashtagService service;

    String baseURL = "hashtags/";
    @Before
    public void setUp() throws Exception {
        service.addHashtag(new Hashtag("TestHashtag"));
        service.addHashtag(new Hashtag("TestHashtag1"));
        service.addHashtag(new Hashtag("TestHashtag2"));
        service.addHashtag(new Hashtag("Test1"));
        service.addHashtag(new Hashtag("Test2"));
        service.addHashtag(new Hashtag("Test3"));
    }

    @Test
    public void getAll() {
        get(baseURL).then().body("hashtag", hasItems(
                "TestHashtag",
                "TestHashtag1",
                "TestHashtag2",
                "Test1",
                "Test2",
                "Test3"));
    }

    @Test
    public void findHashtag() {
        get(baseURL+ "TestHashtag").then().body("hashtag", equalTo("TestHashtag"));
        get(baseURL+ "TestHashtag1").then().body("hashtag", equalTo("TestHashtag1"));
        get(baseURL+ "Test2").then().body("hashtag", equalTo("Test2"));
    }

    @Test
    public void searchHashtags() {
        get(baseURL + "search/Test").then().body("hashtag", hasItems(
                "TestHashtag",
                "TestHashtag1",
                "TestHashtag2",
                "Test1",
                "Test2",
                "Test3"));

        get(baseURL + "search/Hashtag").then().body("hashtag", hasItems(
                "TestHashtag",
                "TestHashtag1",
                "TestHashtag2"));
    }
}