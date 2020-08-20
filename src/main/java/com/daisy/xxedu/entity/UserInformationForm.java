package com.daisy.xxedu.entity;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@Data
@NoArgsConstructor(access=AccessLevel.PRIVATE, force=true)
@AllArgsConstructor
@RequiredArgsConstructor
public class UserInformationForm {
    private Long userId;
    @NonNull
//    @Pattern(regexp = "[a-zA-Z,0-9]+", message = "用户名只能为英文或数字")
    private String username;
    @NonNull
    private String password;
    private Long teacherId;
    @NonNull
    private String name;
    @NonNull
    private String email;
    @NonNull
    private Long schoolId;

    private final String school;

    private final String address;

    public UserInformationForm encipher(PasswordEncoder passwordEncoder) {
        return new UserInformationForm(
                username, passwordEncoder.encode(password),
                name, email, schoolId, school, address);
    }
}
