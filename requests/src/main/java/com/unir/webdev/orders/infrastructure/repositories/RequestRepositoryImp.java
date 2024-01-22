package com.unir.webdev.orders.infrastructure.repositories;

import com.unir.webdev.orders.domain.Request;
import com.unir.webdev.orders.domain.repository.RequestRepository;
import com.unir.webdev.orders.infrastructure.persistence.RequestRepositoryJPA;
import com.unir.webdev.orders.infrastructure.persistence.mapper.RequestMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
@FieldDefaults (makeFinal = true, level = AccessLevel.PRIVATE)
public class RequestRepositoryImp implements RequestRepository {

    RequestRepositoryJPA requestRepositoryJPA;
    RequestMapper requestMapper;

    @Override
    public void registerNewOrder(Request request) {
        requestRepositoryJPA.save(requestMapper.adaptDomainToDb(request));
    }

    @Override
    public List<Request> getAllOrders() {
        return requestRepositoryJPA.findAll()
                                   .stream()
                                   .map(requestMapper :: adaptDbToDomain)
                                   .toList();
    }

    @Override
    public void deleteOrder(UUID request) {
        requestRepositoryJPA.deleteById(request);
    }

    @Override
    public Optional<Request> getRequestById(UUID id) {
        return requestRepositoryJPA.findById(id)
                                   .map(requestMapper :: adaptDbToDomain);
    }

    @Override
    public boolean unknownRequest(UUID id) {
        return getRequestById(id).isEmpty();
    }

    @Override
    public List<UUID> getBookIDsOfRequest(UUID id) {
        return requestRepositoryJPA.findById(id)
                                   .map(requestMapper :: adaptDbToDomain)
                                   .map(Request :: getBooksID)
                                   .get();
    }

    @Override
    public boolean isEmptyRequest(UUID id) {
        return getBookIDsOfRequest(id).isEmpty();
    }

}
