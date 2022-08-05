/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swlc.bolton.notifier;

import com.swlc.bolton.notifier.config.email.EmailConfigUtility;
import com.swlc.bolton.notifier.config.email.EmailUtility;
import static com.swlc.bolton.notifier.constants.ApplicationConstant.*;

import com.swlc.bolton.notifier.controller.ControllerFactory;
import com.swlc.bolton.notifier.controller.UserController;
import com.swlc.bolton.notifier.dto.UserDTO;
import com.swlc.bolton.notifier.enums.ControllerTypes;
import com.swlc.bolton.notifier.json.CommonResponse;
import java.util.Properties;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author athukorala
 */
public class RegisterTest {
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
    public void testRegisterNewUserWithValidConditions() {
        CommonResponse regResponse = userController.registerHandler(new UserDTO(TEST_ID, TEST_NAME, TEST_EMAIL, TEST_PASSWORD));
        assertTrue(regResponse.isSuccess());
    }
    
    @Test
    public void testRegisterNewUserWithAlreadyRegisteredEmail() {
        CommonResponse regResponse = userController.registerHandler(new UserDTO(TEST_ID, TEST_NAME, TEST_EMAIL, TEST_PASSWORD));
        assertEquals("Added email already exist.",regResponse.getMessage());
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
    public void testRegisterAnotherNewUserWithValidConditions() {
        CommonResponse regResponse = userController.registerHandler(new UserDTO(2, "Test", "test@gmail.com", "test@123"));
        assertTrue(regResponse.isSuccess());
    }
    
}
