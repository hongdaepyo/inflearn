package com.dphong.inflearnthejavatest;

import com.dphong.inflearnthejavatest.domain.Study;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.DisabledOnJre;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.jupiter.params.provider.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//@ExtendWith(FindSlowTestExtension.class)
class StudyTest {

    int value = 1;

    @RegisterExtension
    static FindSlowTestExtension findSlowTestExtension =
            new FindSlowTestExtension(1000L);

    @Order(2)
    @FastTest
    @DisplayName("스터디 만들기")
//    @EnabledOnOs(OS.LINUX)
    @DisabledOnJre(JRE.OTHER)
    void create_new_study() {
        String test_env = System.getenv("TEST_ENV");

        System.out.println("value = " + value++);
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

    @Order(1)
    @SlowTest
    @DisplayName("스터디 만들기 slow")
    void create_new_study_again() throws InterruptedException {
//        Study study = new Study();
//        assertNotNull(study);
        Thread.sleep(1005L);
        System.out.println("slow");
        System.out.println("value = " + value++);

    }

    @DisplayName("스터디 만들기 반복")
    @RepeatedTest(value = 10, name = "{displayName}, {currentRepetition}/{totalRepetitions}")
    void repeatTest(RepetitionInfo repetitionInfo) {
        System.out.println("test" + repetitionInfo.getCurrentRepetition() + "/" +
                repetitionInfo.getTotalRepetitions());
    }

    @DisplayName("스터디 만들기 반복2")
    @ParameterizedTest(name = "{index} {displayName} message={0}")
    @ValueSource(ints = {10, 20, 40})
//    @NullSource
//    @EmptySource
//    @NullAndEmptySource
    void parameterizedTest(@ConvertWith(StudyCoverter.class) Study study) {
        System.out.println(study.getLimit());
    }

    static class StudyCoverter extends SimpleArgumentConverter {

        @Override
        protected Object convert(Object source, Class<?> targetType) throws ArgumentConversionException {
            assertEquals(Study.class, targetType, "Can only convert to Study");
            return new Study(Integer.parseInt(source.toString()));
        }
    }

    @DisplayName("스터디 만들기 반복3")
    @ParameterizedTest(name = "{index} {displayName} message={0}")
    @CsvSource({"10, '자바 스터디'", "20, '스프링'"})
    void parameterizedTest2(ArgumentsAccessor argumentsAccessor) {
        Study study = new Study(argumentsAccessor.getInteger(0), argumentsAccessor.getString(1));
        System.out.println(study);
    }

    @DisplayName("스터디 만들기 반복3")
    @ParameterizedTest(name = "{index} {displayName} message={0}")
    @CsvSource({"10, '자바 스터디'", "20, '스프링'"})
    void parameterizedTest3(@AggregateWith(StudyAggregator.class) Study study) {
        System.out.println(study);
    }

    static class StudyAggregator implements ArgumentsAggregator {
        @Override
        public Object aggregateArguments(ArgumentsAccessor accessor, ParameterContext context) throws ArgumentsAggregationException {
            return new Study(accessor.getInteger(0), accessor.getString(1));
        }
    }

    @BeforeAll
    void beforeAll() {
        System.out.println("before All");
    }

    @AfterAll
    void afterAll() {
        System.out.println("after All");
    }

}