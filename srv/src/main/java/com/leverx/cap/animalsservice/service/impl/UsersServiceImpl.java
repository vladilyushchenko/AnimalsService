package com.leverx.cap.animalsservice.service.impl;

import com.leverx.cap.animalsservice.repository.UsersRepository;
import com.leverx.cap.animalsservice.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;

    @Override
    public boolean existsById(String id) {
        if (id == null) {
            return false;
        }
        return usersRepository.existsById(id);
    }
}