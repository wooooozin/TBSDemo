package com.example.tablebooker.user.entity;

import com.example.tablebooker.store.entity.Store;
import com.example.tablebooker.user.type.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserType userType;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Store> stores;


    // TODO - 예약 엔티티 추가

    // TODO - 리뷰 엔티티 추가

}
