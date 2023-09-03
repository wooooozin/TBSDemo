package com.example.tablebooker.user.dto;

import com.example.tablebooker.user.type.UserType;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.*;


@Data
@Builder
public class UserInputDTO {

    @NotBlank(message = "이메일은 필수 항목입니다.")
    @Email(message = "유효한 이메일 형식이어야 합니다.")
    private String email;

    @NotBlank(message = "비밀번호는 필수 항목입니다.")
    @Size(min = 8, max = 50, message = "비밀번호는 8자 이상 50자 이하이어야 합니다.")
    private String password;

    @NotBlank(message = "휴대폰 번호는 필수 항목입니다.")
    @Pattern(regexp = "^[0-9]{10,11}$", message = "유효한 휴대폰 번호 형식이어야 합니다.")
    private String mobileNumber;

    @NotNull(message = "사용자 유형은 필수 항목입니다.")
    private UserType userType;

}
