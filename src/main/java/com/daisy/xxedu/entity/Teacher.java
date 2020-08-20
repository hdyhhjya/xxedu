package com.daisy.xxedu.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force=true)
@RequiredArgsConstructor
@Component
public class Teacher {
    private Long id;
    private final String name;
    private final String email;
    private final Long schoolId;
}
