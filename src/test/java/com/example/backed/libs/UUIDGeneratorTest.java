package com.example.backed.libs;

import com.example.common.UUIDGenerator;
import org.junit.jupiter.api.Test;

class UUIDGeneratorTest {

    @Test
    void generate() {
        String uuid = UUIDGenerator.newId();
        assert(uuid.matches("[0-9a-z]{8}-[0-9a-z]{4}-[0-9a-z]{4}-[0-9a-z]{4}-[0-9a-z]{12}"));
    }
}