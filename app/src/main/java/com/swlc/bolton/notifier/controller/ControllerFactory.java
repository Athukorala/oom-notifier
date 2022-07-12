/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swlc.bolton.notifier.controller;

import com.swlc.bolton.notifier.enums.ControllerTypes;

/**
 *
 * @author athukorala
 */
public class ControllerFactory {
    
     private static ControllerFactory controllerFactory;

    private ControllerFactory() {
    }

    public static ControllerFactory getInstance() {
        if (controllerFactory == null) {
            controllerFactory = new ControllerFactory();
        }
        return controllerFactory;
    }
    public SuperController getController(ControllerTypes controller) {
        switch (controller) {
            case USER:
                return new UserController();
            case SUBSCRIPTION:
                return new SubscriptionController();
         
            default:
                return null;
        }
    }
}
