package com.unir.webdev.orders.infrastructur.controllers.dto;

import java.util.UUID;

public record DeleteRequest(UUID requestUUID) {
    public boolean isNotNull(){
        return requestUUID!=null;
    }
}
