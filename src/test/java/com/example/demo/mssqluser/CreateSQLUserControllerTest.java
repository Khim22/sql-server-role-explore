package com.example.demo.mssqluser;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
public class CreateSQLUserControllerTest {

    @Mock
    private CreateSQLService createSQLService;

    @InjectMocks
    private CreateSQLUserController createSQLUserController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setupMockMvc(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(createSQLUserController).build();
    }

    @Test
    public void createSQLUser_givenUserNameAndPassWord_shouldReturnUserName() throws Exception {
        // arrange
        when(createSQLService.createSQLUser(new UserCredential("user1", "Password1"))).thenReturn("user1");

        // act, assert
        mockMvc.perform(post( "/sql-user")
                .contentType("application/json")
                .content("{\"userName\": \"user1\", \"password\":\"Password1\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("user1"));
    }

    @Test
    public void assignSQLUserToDatabase_givenUserCredentialAndDatabaseName_shouldReturnDatabaseName() throws Exception {
        // arrange
        ObjectMapper om = new ObjectMapper();

        UserCredential userCredential = new UserCredential("user1", "Password1");
        SQLRequest sqlRequest =  new SQLRequest("database", "test");
        when(createSQLService.assignSQLUserToDatabase(new SQLCredentialsAndRequest(userCredential, sqlRequest))).thenReturn("user1");

        // act, assert
        mockMvc.perform(post( "/database-user")
                .contentType("application/json")
                .content(om.writeValueAsString(new SQLCredentialsAndRequest(userCredential, sqlRequest))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("test"));
    }

    @Test
    public void grantSQLUserToTable_givenUserCredentialAndTableName_shouldReturnTableName() throws Exception {
        // arrange
        ObjectMapper om = new ObjectMapper();

        UserCredential userCredential = new UserCredential("user1", "Password1");
        SQLRequest sqlRequest =  new SQLRequest("table", "test_table");
        when(createSQLService.grantTableAccessToSQLUser(new SQLCredentialsAndRequest(userCredential, sqlRequest))).thenReturn("test_table");

        // act, assert
        mockMvc.perform(post( "/table-user")
                .contentType("application/json")
                .content(om.writeValueAsString(new SQLCredentialsAndRequest(userCredential, sqlRequest))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("test_table"));
    }

    @Test
    public void grantSQLUserToColumn_givenUserCredentialAndTableNameAndColumnName_shouldReturnColumnName() throws Exception {
        // arrange
        ObjectMapper om = new ObjectMapper();

        UserCredential userCredential = new UserCredential("user1", "Password1");
        SQLRequest sqlTableRequest =  new SQLRequest("table", "test_table");
        SQLRequest sqlColumnRequest =  new SQLRequest("column", "test_col_1");
        when(createSQLService.grantColumnAccessToSQLUser(new SQLCredentialsWithColumnRequest(userCredential, sqlTableRequest, sqlColumnRequest)))
                .thenReturn("test_col_1");

        // act, assert
        mockMvc.perform(post( "/column-user")
                .contentType("application/json")
                .content(om.writeValueAsString(new SQLCredentialsWithColumnRequest(userCredential, sqlTableRequest, sqlColumnRequest))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("test_col_1"));
    }

}
