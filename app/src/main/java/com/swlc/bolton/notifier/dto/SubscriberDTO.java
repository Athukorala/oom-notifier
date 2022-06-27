/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swlc.bolton.notifier.dto;

/**
 *
 * @author athukorala
 */
public class SubscriberDTO {
    private long subscribeId;
    private long subscriberUserId;
    private long subscribedBy;

    public SubscriberDTO(long subscribeId, long subscriberUserId, long subscribedBy) {
        this.subscribeId = subscribeId;
        this.subscriberUserId = subscriberUserId;
        this.subscribedBy = subscribedBy;
    }

    public SubscriberDTO(long subscriberUserId, long subscribedBy) {
        this.subscriberUserId = subscriberUserId;
        this.subscribedBy = subscribedBy;
    }

    public long getSubscribeId() {
        return subscribeId;
    }

    public void setSubscribeId(long subscribeId) {
        this.subscribeId = subscribeId;
    }

    public long getSubscriberUserId() {
        return subscriberUserId;
    }

    public void setSubscriberUserId(long subscriberUserId) {
        this.subscriberUserId = subscriberUserId;
    }

    public long getSubscribedBy() {
        return subscribedBy;
    }

    public void setSubscribedBy(long subscribedBy) {
        this.subscribedBy = subscribedBy;
    }

    @Override
    public String toString() {
        return "SubscriberDTO{" + "subscribeId=" + subscribeId + ", subscriberUserId=" + subscriberUserId + ", subscribedBy=" + subscribedBy + '}';
    }    
}
