package com.example.tablebooker.store.serivce;

import com.example.tablebooker.store.dto.StoreInputDto;
import com.example.tablebooker.store.entity.Store;
import com.example.tablebooker.store.repository.StoreRepository;
import com.example.tablebooker.user.entity.User;
import com.example.tablebooker.user.exception.UnauthorizedAccessException;
import com.example.tablebooker.user.exception.UserNotFoundException;
import com.example.tablebooker.user.repository.UserRepository;
import com.example.tablebooker.user.service.PartnerUserService;
import com.example.tablebooker.utils.JWTUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;
    private final UserRepository userRepository;

    @Override
    public Store createStore(StoreInputDto storeInputDto, Long userId) {

        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("등록된 사용자가 없습니다."));
        Store store = Store.builder()
                .name(storeInputDto.getName())
                .address(storeInputDto.getAddress())
                .description(storeInputDto.getDescription())
                .owner(existingUser)
                .reservationStatus(storeInputDto.getReservationStatus())
                .build();

        return storeRepository.save(store);
    }

}
