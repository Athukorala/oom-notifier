package com.swlc.bolton.notifier;

import com.swlc.bolton.notifier.controller.ControllerFactory;
import com.swlc.bolton.notifier.controller.UserController;
import com.swlc.bolton.notifier.dto.UserDTO;
import com.swlc.bolton.notifier.enums.ControllerTypes;
import com.swlc.bolton.notifier.json.CommonResponse;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {
    public final long TEST_ID = 1;
    public final String TEST_EMAIL = "tharinduathukorala1@gmail.com";
    public final String TEST_PASSWORD = "Test@OOM@123";
    public final String TEST_NAME = "Dananjaya";

    private UserController userController;

    @BeforeEach
    public void init() {
        userController = (UserController) ControllerFactory.getInstance().getController(ControllerTypes.USER);
    }

    @Test
    @DisplayName("Test user login with invalid credentials")
    public void testUserLoginWithInvalidCredentials() {
        CommonResponse loginResponse = userController.loginHandler(new UserDTO(TEST_EMAIL, "invalid@passwod"));
        assertFalse(loginResponse.isSuccess());
    }

    @Test
    @DisplayName("Test user login with valid credentials")
    public void testUserLoginWithValidCredentials() {
        CommonResponse loginResponse = userController.loginHandler(new UserDTO(TEST_EMAIL, TEST_PASSWORD));
        assertTrue(loginResponse.isSuccess());
    }

    @Test
    @DisplayName("Test user remove account")
    public void testUserRemoveAccount() {
        UserDTO loggedUserObj = new UserDTO(TEST_ID, TEST_NAME, TEST_EMAIL, TEST_PASSWORD);
        CommonResponse removeResp = userController.removeAccountHandler(loggedUserObj);
        assertTrue(removeResp.isSuccess());
    }
}
