package com.swlc.bolton.notifier;

import com.swlc.bolton.notifier.config.email.EmailConfigUtility;
import com.swlc.bolton.notifier.config.email.EmailUtility;
import com.swlc.bolton.notifier.controller.ControllerFactory;
import com.swlc.bolton.notifier.controller.UserController;
import com.swlc.bolton.notifier.dto.UserDTO;
import com.swlc.bolton.notifier.enums.ControllerTypes;
import com.swlc.bolton.notifier.json.CommonResponse;
import org.junit.jupiter.api.*;

import java.util.Properties;

import static com.swlc.bolton.notifier.constants.ApplicationConstant.EMAIL_REG_BODY;
import static com.swlc.bolton.notifier.constants.ApplicationConstant.EMAIL_REG_SUBJECT;
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
    public void testUserLoginWithInvalidCredentials() {
        CommonResponse loginResponse = userController.loginHandler(new UserDTO(TEST_EMAIL, TEST_PASSWORD));
        assertFalse(loginResponse.isSuccess());
    }

    @Test
    public void testRegisterNewUserWithValidConditions() {
        CommonResponse regResponse = userController.registerHandler(new UserDTO(TEST_ID, TEST_NAME, TEST_EMAIL, TEST_PASSWORD));
        assertTrue(regResponse.isSuccess());
    }

    @Test
    public void testSendEmailToTestUser() {
        try {
            Properties smtpProperties = new EmailConfigUtility().loadProperties();
            boolean isSend = EmailUtility.sendEmail(smtpProperties, TEST_EMAIL, EMAIL_REG_SUBJECT, String.format(EMAIL_REG_BODY, TEST_NAME), null);
            assertTrue(isSend);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void testRegisterNewUserWithAlreadyRegisteredEmail() {
        CommonResponse regResponse = userController.registerHandler(new UserDTO(TEST_ID, TEST_NAME, TEST_EMAIL, TEST_PASSWORD));
        assertFalse(regResponse.isSuccess());
    }

    @Test
    public void testUserLoginWithValidCredentials() {
        CommonResponse loginResponse = userController.loginHandler(new UserDTO(TEST_EMAIL, TEST_PASSWORD));
        assertTrue(loginResponse.isSuccess());
    }

    @Test
    public void testUserRemoveAccount() {
        UserDTO loggedUserObj = new UserDTO(TEST_ID, TEST_NAME, TEST_EMAIL, TEST_PASSWORD);
        CommonResponse removeResp = userController.removeAccountHandler(loggedUserObj);
        assertTrue(removeResp.isSuccess());
    }

    @Test
    public void testRegisterAnotherNewUserWithValidConditions() {
        CommonResponse regResponse = userController.registerHandler(new UserDTO(2, "Test", "test@gmail.com", "test@123"));
        assertTrue(regResponse.isSuccess());
    }
}
