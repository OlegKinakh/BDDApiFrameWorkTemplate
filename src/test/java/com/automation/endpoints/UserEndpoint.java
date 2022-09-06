package com.automation.endpoints;

import com.automation.pojos.User;
import com.automation.utils.DbResponse;
import com.automation.utils.Serializers;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;

import static org.junit.Assert.assertEquals;


public class UserEndpoint extends Base {
    Serializers<User> serialize = new Serializers<>();

    public void createUser() {
        //create User object
        user.setEmail(faker.name().firstName().replace(" ", "") + "@gmail.com");
        user.setGender("male");
        user.setName(faker.funnyName().name());
        user.setStatus("active");
    }


    public void postUser() {
        //Create json Body from pojo
        String jsonBody = serialize.serialize(user);

        Response response = DbResponse.postResponse(jsonBody, "/users");


        //Deserialize
        user = serialize.deserialize(user, response);
        System.out.println(response.asString());

        Assertions.assertEquals(201, response.getStatusCode());

    }

    public void verifyUserExists() {
        Response response = DbResponse.getResponse(user.getId());
        Assertions.assertEquals(200, response.getStatusCode());
    }

    public void updateUserInfo() {
        String changedName = "Changed";
        user.setName(changedName);
        String jsonBody = serialize.serialize(user);
        Response response = DbResponse.putResponse(jsonBody, "/users/" + user.getId());
        Assertions.assertEquals(200, response.getStatusCode());
    }

    public void verifyUserUpdate() {
        User userToVerify = new User();
        Response response = DbResponse.getResponse(user.getId());
        userToVerify = serialize.deserialize(userToVerify, response);

        User finalUserToVerify = userToVerify;
        Assertions.assertAll(
                () -> assertEquals(200, response.getStatusCode()),
                () -> assertEquals(user.getName(), finalUserToVerify.getName())
        );


    }

    public void deleteUser() {
        Response response = DbResponse.deleteResponse(user.getId());
        Assertions.assertAll(
                () -> assertEquals(204, response.getStatusCode())
        );
    }

    public void verifyUserDeleted() {
        Response response = DbResponse.getResponse(user.getId());
        Assertions.assertEquals(404, response.getStatusCode());

    }
}
