package com.example.tablebooker.user.dto;

import com.example.tablebooker.user.type.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInputDto {

    @NotBlank(message = "이메일은 필수 항목입니다.")
    @Email(message = "유효한 이메일 형식이어야 합니다.")
    private String email;

    @NotBlank(message = "비밀번호는 필수 항목입니다.")
    @Size(min = 8, max = 50, message = "비밀번호는 8자 이상 50자 이하이어야 합니다.")
    private String password;

    @NotBlank(message = "휴대폰 번호는 필수 항목입니다.")
    @Pattern(regexp = "\\d{3}-\\d{3,4}-\\d{4}$", message = "유효한 휴대폰 번호 형식이어야 합니다.")
    private String mobileNumber;

    @NotNull(message = "사용자 유형은 필수 항목입니다.")
    private UserType userType;

}
