package com.example.tablebooker.store.serivce;

import com.example.tablebooker.store.dto.StoreInputDto;
import com.example.tablebooker.store.entity.Store;
import com.example.tablebooker.user.entity.User;

public interface StoreService {
    Store createStore(StoreInputDto store, Long userId);
}
