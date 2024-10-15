package com.example.api.dataStructure;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DataStructureTest {

    @Test
    @DisplayName("MultiValueMap 테스트")
    void MultiValueMapTest() {
        MultiValueMap<Object, Object> multiValueMap = new LinkedMultiValueMap<>();

        multiValueMap.add("문자열", "String");
        multiValueMap.add("문자열", "String2");
        multiValueMap.add("정수", 1);
        multiValueMap.add("정수", 2);

        List<Object> strings = multiValueMap.get("문자열");
        List<Object> numbers = multiValueMap.get("정수");

        assertThat(strings.size()).isEqualTo(2);
        assertThat(numbers.size()).isEqualTo(2);
        assertThat(strings.get(0)).isEqualTo("String");
        assertThat(strings.get(1)).isEqualTo("String2");
        assertThat(numbers.get(0)).isEqualTo(1);
        assertThat(numbers.get(1)).isEqualTo(2);
    }
}