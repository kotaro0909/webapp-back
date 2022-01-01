package com.example.backed.controller;

import com.example.backed.libs.DateUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class HealthCheckControllerTest {

    private MockMvc mockMvc;
    private String URL_HEALTH = HealthCheckController.URL_CHECK_APPSERVER_HEALTH;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(new HealthCheckController()).build();
    }

    /**
     * 返却値のオブジェクトの設定値確認
     */
    @Test
    void testHealthCheckResult() {
        HealthCheckController.HealthCheckResult res = new HealthCheckController.HealthCheckResult();
        assertResult(res.getResult());
        assertCheckDatetime(res.getCheckDatetime());
    }

    /**
     * コントローラの戻り値：ステータスコード 200
     * @throws Exception
     */
    @Test
    void testCheckHealth_HttpStatusCode() throws Exception {
        mockMvc.perform(get(URL_HEALTH)).andExpect(status().isOk());
    }

    /**
     * コントローラーの戻り値：result="success"
     * @throws Exception
     */
    @Test
    void testCheckHealth_Result() throws Exception {
        String json = mockMvc.perform(get(URL_HEALTH)).andReturn().getResponse().getContentAsString();
        Map<String, String> resMap = new ObjectMapper().readValue(json, new TypeReference<>(){});
        assertResult(resMap.get("result"));
    }

    /**
     * コントローラーの戻り値：checkDatetime=システム日時
     * @throws Exception
     */
    @Test
    void testCheckHealth_CheckDatetime() throws Exception {
        String json = mockMvc.perform(get(URL_HEALTH)).andReturn().getResponse().getContentAsString();
        Map<String, String> resMap = new ObjectMapper().readValue(json, new TypeReference<>(){});
        assertCheckDatetime(resMap.get("checkDatetime"));
    }

    /**
     * 戻り値[result]のassertion
     * @param actual コントローラーから返却された戻り値
     */
    private void assertResult(String actual) {
        assertEquals("success", actual);
    }

    /**
     * 戻り値[checkDatetime]のassertion
     * @param actual コントローラーから返却された戻り値
     */
    private void assertCheckDatetime(String actual) {
        assertEquals(true, DateUtils.isDate(actual));
    }
}