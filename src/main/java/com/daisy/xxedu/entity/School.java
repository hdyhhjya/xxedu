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
public class School {
    private Long id;
    private final String name;
    private final String address;
}
