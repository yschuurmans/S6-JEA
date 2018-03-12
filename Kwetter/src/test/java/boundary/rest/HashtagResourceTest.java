package boundary.rest;

import domain.Hashtag;
import domain.Tweet;
import domain.User;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import service.HashtagService;
import service.UserService;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.*;

public class HashtagResourceTest {

    @Inject
    HashtagService service;

    String baseURL = "Kwetter/hashtags/";
    @BeforeClass
    public static void setUp() throws Exception {
        given().body ("{ \"id\": 0, \"tweetContent\": \"##TestHashtag it work? is interessant\" }").when ().contentType (ContentType.JSON).put("Kwetter/users/Youri/tweets").then().statusCode(200);
        given().body ("{ \"id\": 0, \"tweetContent\": \"##TestHashtag1 it work? is interessant\" }").when ().contentType (ContentType.JSON).put("Kwetter/users/Youri/tweets").then().statusCode(200);
        given().body ("{ \"id\": 0, \"tweetContent\": \"##TestHashtag2 it work? is interessant\" }").when ().contentType (ContentType.JSON).put("Kwetter/users/Youri/tweets").then().statusCode(200);
        given().body ("{ \"id\": 0, \"tweetContent\": \"#Test1 it work? is interessant\" }").when ().contentType (ContentType.JSON).put("Kwetter/users/Youri/tweets").then().statusCode(200);
        given().body ("{ \"id\": 0, \"tweetContent\": \"#Test2 it work? is interessant\" }").when ().contentType (ContentType.JSON).put("Kwetter/users/Youri/tweets").then().statusCode(200);
        given().body ("{ \"id\": 0, \"tweetContent\": \"#Test3 it work? is interessant\" }").when ().contentType (ContentType.JSON).put("Kwetter/users/Youri/tweets").then().statusCode(200);

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

        get(baseURL + "search/TestHashtag").then().body("hashtag", hasItems(
                "TestHashtag",
                "TestHashtag1",
                "TestHashtag2"));
    }
}