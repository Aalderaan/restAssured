package com.example.restassured.tests;
import com.example.restassured.tests.steps.UsersInfoSteps;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static com.example.restassured.tests.data.Schema.USERS_SCHEMA;
import static io.restassured.internal.matcher.xml.XmlXsdMatcher.matchesXsdInClasspath;

public class TestApi {
    /**
     * First here we create an instance of UsersInoSteps class, to be able after to call its methods
     */

    UsersInfoSteps usersInfoSteps = new UsersInfoSteps();

    /**
     * In the 'checkIfUsersInfoCorrespondsToSchema' method we take to String arguments, witch we extract
     * from testSuits.xml file, then with 'getUsersId' method we extract user's id, and with this id, and other
     * passed arguments in the 'getUsersInfo' we get Response of the User's info and with assert we check if it
     * corresponds to the UserSchema.xsd file
     */

    @Test
    @Parameters({"userName", "password"})
    void checkIfUsersInfoCorrespondsToSchema(String userName, String password) {
        String usersId = usersInfoSteps.getUsersId(userName, password);

        usersInfoSteps.getUsersInfo(userName, password, usersId)
                .then()
                .assertThat().body(matchesXsdInClasspath(USERS_SCHEMA));
    }

    /**
     * In the 'checkPossibilityToSignInWithValidCredentials' method we take the  String arguments, witch we extract
     * from testSuits.xml file, then with 'signIn' method and getStatusCode() we extract status code, and with assert
     * we check if it corresponds to the expected one.
     */

    @Test
    @Parameters({"userName", "password", "errorMessage"})
    void checkPossibilityToSignInWithValidCredentials(String userName, String password, String errorMessage) {
        int statusCode = usersInfoSteps.signIn(userName, password).getStatusCode();

        Assert.assertEquals(statusCode, 200, errorMessage);
    }
}
