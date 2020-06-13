package com.example.demo.mssqluser;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CreateSQLUserControllerTest {

    @Mock
    private CreateSQLService createSQLService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void createSQLUser_givenUserNameAndPassWord_shouldReturnUserName() throws Exception {
        // arrange
        when(createSQLService.createSQLUser(new UserCredential("user1", "Password1"))).thenReturn("user1");

        // act, assert
        mockMvc.perform(post( "/sql-user")
                .contentType("application/json")
                .content("{\"userName\": \"user1\", \"password\":\"Password\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("user1"));

    }

}
