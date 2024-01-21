package com.unir.webdev.orders.infrastructur.controllers;

import com.unir.webdev.orders.application.DeleteRequestsUseCase;
import com.unir.webdev.orders.domain.response.Result;
import com.unir.webdev.orders.infrastructur.controllers.dto.DeleteRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/api/v1/requests")
@RequiredArgsConstructor
@FieldDefaults (makeFinal = true, level = AccessLevel.PRIVATE)
public class DeleteRequestController {
    DeleteRequestsUseCase deleteRequestsUseCase;
    @DeleteMapping
    public ResponseEntity<?> deleteRequest(DeleteRequest deleteRequest){
        Result result = deleteRequestsUseCase.deleteRequest(deleteRequest.requestUUID());
        return result.isSuccess()? ResponseEntity.ok(result.getSuccess()):
                ResponseEntity.badRequest().body(result.getError());
    }
}
