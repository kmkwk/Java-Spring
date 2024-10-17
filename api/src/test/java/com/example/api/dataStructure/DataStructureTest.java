package com.example.api.dataStructure;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;

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

    @Test
    @DisplayName("functional test")
    public void functional() {
        String interfaceTypeName = "SNMP";
        String active = "ACTIVE";
        String inActive = "INACTIVE";

        InterfaceAvailabilityDTO interfaceAvailabilityDTO = InterfaceAvailabilityDTO.of(interfaceTypeName);

        assertThat(interfaceAvailabilityDTO.agent).isEqualTo(inActive);
        assertThat(interfaceAvailabilityDTO.snmp).isEqualTo(active);
        assertThat(interfaceAvailabilityDTO.api).isEqualTo(inActive);
    }


    @Test
    @DisplayName("functional test")
    public void functional_null() {
        String interfaceTypeName = null;
        String active = "ACTIVE";
        String inActive = "INACTIVE";

        InterfaceAvailabilityDTO interfaceAvailabilityDTO = InterfaceAvailabilityDTO.of(interfaceTypeName);

        assertThat(interfaceAvailabilityDTO.agent).isEqualTo(inActive);
        assertThat(interfaceAvailabilityDTO.snmp).isEqualTo(inActive);
        assertThat(interfaceAvailabilityDTO.api).isEqualTo(inActive);
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder(toBuilder = true)
    public static class InterfaceAvailabilityDTO {

        private String agent;

        private String snmp;

        private String api;


        public static InterfaceAvailabilityDTO of(String interfaceTypeName) {
            if(interfaceTypeName == null) {
                return build();
            }

            InterfaceType interfaceType = InterfaceType.valueOf(interfaceTypeName);
            InterfaceAvailabilityDTO interfaceAvailability = build();

            EnumMap<InterfaceType, Function<InterfaceAvailabilityDTO, InterfaceAvailabilityDTO>> statusSetters = getInterfaceTypeFunctionEnumMap();

            return statusSetters
                    .getOrDefault(interfaceType, Function.identity())
                    .apply(interfaceAvailability);
        }

        @NotNull
        private static EnumMap<InterfaceType, Function<InterfaceAvailabilityDTO, InterfaceAvailabilityDTO>> getInterfaceTypeFunctionEnumMap() {
            EnumMap<InterfaceType, Function<InterfaceAvailabilityDTO, InterfaceAvailabilityDTO>> statusSetters = new EnumMap<>(InterfaceType.class);

            statusSetters.put(InterfaceType.AGENT, d -> d.toBuilder().agent("ACTIVE").build());
            statusSetters.put(InterfaceType.SNMP, d -> d.toBuilder().snmp("ACTIVE").build());
            statusSetters.put(InterfaceType.API, d -> d.toBuilder().api("ACTIVE").build());

            return statusSetters;
        }

        private static InterfaceAvailabilityDTO build() {
            String status = "INACTIVE";

            return InterfaceAvailabilityDTO.builder()
                    .agent(status)
                    .snmp(status)
                    .api(status)
                    .build();
        }

        @Getter
        @AllArgsConstructor
        public enum InterfaceType {

            AGENT( "AGENT"),
            SNMP("SNMP"),
            API("API");

            private final String name;
        }
    }
}