package com.daisy.xxedu.entity;

import lombok.*;
import org.springframework.stereotype.Component;

@Data
@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@RequiredArgsConstructor
public class CourseTime {
    private Long id;
    @NonNull
    private Integer weekday;
    @NonNull
    private Integer number;
    @NonNull
    private Long courseId;
}
