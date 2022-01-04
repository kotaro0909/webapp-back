package com.example.common;

import java.util.UUID;

/**
 * UUIDを生成する
 */
public class UUIDGenerator  {
    /**
     * UUIDを生成(バージョン4)し取得する
     * @return UUID
     */
    public static String newId() {
        return UUID.randomUUID().toString();
    }
}
