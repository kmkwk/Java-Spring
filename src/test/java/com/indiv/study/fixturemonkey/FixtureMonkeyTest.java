package com.indiv.study.fixturemonkey;

import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.api.introspector.ConstructorPropertiesArbitraryIntrospector;
import com.navercorp.fixturemonkey.jakarta.validation.plugin.JakartaValidationPlugin;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class FixtureMonkeyTest {


    @Test
    @DisplayName("FixtureMonkey 테스트")
    @RepeatedTest(value = 100)
    public void fixtureMonkey_test() {
        // given, Validation 을 통과하는 범위의 객체를 생성한다.
        FixtureMonkey fixtureMonkey = FixtureMonkey.builder()
                .objectIntrospector(ConstructorPropertiesArbitraryIntrospector.INSTANCE)
                .plugin(new JakartaValidationPlugin())
                .build();

        // when
        FixtureMonkeyRequest request = fixtureMonkey.giveMeOne(FixtureMonkeyRequest.class);

        // then
        assertNotNull(request.id);
        assertNotNull(request.name);
        assertNotNull(request.email);
        assertNotNull(request.password);
        assertThat(request.size).isLessThan(11).isGreaterThan(0);
    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class FixtureMonkeyRequest {

        @NotNull
        private Long id;

        @NotBlank
        @Size(min = 1, max = 10)
        private String name;

        @NotBlank
        private String email;

        @NotBlank
        private String password;

        @NotNull
        @Min(1)
        @Max(10)
        private int size;

    }
}
