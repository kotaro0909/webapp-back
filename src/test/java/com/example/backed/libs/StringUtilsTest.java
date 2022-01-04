package com.example.backed.libs;

import com.example.common.StringUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {

    @Test
    void isEmpty() {
        assertEquals(true, StringUtils.isEmpty( ""));
        assertEquals(true, StringUtils.isEmpty(null));
        assertEquals(false, StringUtils.isEmpty( "xxx"));
    }

    @Test
    void testEquals() {
        assertEquals(true, StringUtils.equals("xxx", "xxx"));
        assertEquals(false, StringUtils.equals("xxx", "yyy"));
    }

    @Test
    void notEquals() {
        assertEquals(false, StringUtils.notEquals("xxx", "xxx"));
        assertEquals(true, StringUtils.notEquals("xxx", "yyy"));
    }

    @Test
    void random() {
        assertEquals(0, StringUtils.random(0).length());
        assertEquals(12, StringUtils.random(12).length());
    }
}