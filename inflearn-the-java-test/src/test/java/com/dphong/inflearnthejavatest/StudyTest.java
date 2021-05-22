package com.dphong.inflearnthejavatest;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.DisabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.condition.OS;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class StudyTest {

    @FastTest
    @DisplayName("스터디 만들기")
//    @EnabledOnOs(OS.LINUX)
    @DisabledOnJre(JRE.OTHER)
    void create_new_study() {
        String test_env = System.getenv("TEST_ENV");

        System.out.println(test_env);
//        assumeTrue("LOCAL".equalsIgnoreCase(test_env));
//        assumingThat("LOCAL".equalsIgnoreCase(test_env), () -> {
//
//        });
//
//        assumingThat("dphong".equalsIgnoreCase(test_env), () -> {
//
//        });
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Study(-10));

        assertEquals("limit은 0보다 커야 한다.", exception.getMessage());

//        assertTimeout(Duration.ofMillis(100), () -> {
//            new Study(10);
//            Thread.sleep(300);
//        });
    }

    @SlowTest
    void create_new_study_again() {
//        Study study = new Study();
//        assertNotNull(study);
        System.out.println("slow");
    }
}