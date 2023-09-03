package com.example.tablebooker.store.dto;

import com.example.tablebooker.store.type.StoreReservationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreInputDto {
    @NotBlank(message = "매장명은 필수 항목입니다.")
    private String name;

    @NotBlank(message = "주소는 필수 항목 입니다.")
    private String address;

    private String description;

    @NotNull(message = "예약 상태는 필수 항목입니다.")
    private StoreReservationStatus reservationStatus;
}
