package com.example.demo.mssqluser.service;

import com.example.demo.mssqluser.model.SQLCredentialsAndRequest;
import com.example.demo.mssqluser.model.SQLCredentialsWithColumnRequest;
import com.example.demo.mssqluser.model.SQLRequest;
import com.example.demo.mssqluser.model.UserCredential;
import com.example.demo.mssqluser.sqlconnector.DataAccessConnector;
import com.example.demo.mssqluser.sqlconnector.SQLConnector;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateSQLUserServiceTest {

    @Mock
    private SQLConnector dataAccessConnector;

    @InjectMocks
    private SQLService createSQLService;

    @Test
    public void createSQLUser_givenUserCredentials_shouldReturnUserName() throws Exception {
        // arrange
        UserCredential userCredential = new UserCredential("user1", "Password");
        when(dataAccessConnector.createUser(userCredential)).thenReturn(true);

        // act
        String result = createSQLService.createSQLUser(userCredential);

        // assert
        assertThat(result).isEqualTo("user1");
    }

    @Test
    public void createSQLUser_givenUserCredentials_shouldTriggerDataAccessConnectorCreateUser() throws Exception {
        // arrange
        UserCredential userCredential = new UserCredential("user1", "Password");
        when(dataAccessConnector.createUser(userCredential)).thenReturn(true);

        // act
        String result = createSQLService.createSQLUser(userCredential);

        // assert
        verify(dataAccessConnector).createUser(userCredential);
    }

    @Test
    public void assignSQLUserToDatabase_givenSQLCredentialsAndRequest_shouldReturnDatabaseName() throws SQLException, ClassNotFoundException {
        // arrange
        UserCredential userCredential = new UserCredential("user1", "Password1");
        SQLRequest sqlRequest =  new SQLRequest("database", "test");
        when(dataAccessConnector.createDatabaseUser(userCredential, sqlRequest)).thenReturn(true);

        // act
        String result = createSQLService.assignSQLUserToDatabase(new SQLCredentialsAndRequest(userCredential, sqlRequest));

        // assert
        assertThat(result).isEqualTo("test");
    }

    @Test
    public void assignSQLUserToDatabase_givenSQLCredentialsAndRequest_shouldTriggerDataAccessConnectorCreateUser() throws Exception {
        // arrange
        UserCredential userCredential = new UserCredential("user1", "Password1");
        SQLRequest sqlRequest =  new SQLRequest("database", "test");
        when(dataAccessConnector.createDatabaseUser(userCredential, sqlRequest)).thenReturn(true);

        // act
        String result = createSQLService.assignSQLUserToDatabase(new SQLCredentialsAndRequest(userCredential, sqlRequest));

        // assert
        verify(dataAccessConnector).createDatabaseUser(userCredential, sqlRequest);
    }

    @Test
    public void grantTableAccessToSQLUser_givenSQLCredentialsAndRequest_shouldReturnTableName() throws SQLException, ClassNotFoundException {
        // arrange
        UserCredential userCredential = new UserCredential("user1", "Password1");
        SQLRequest sqlRequest =  new SQLRequest("table", "test_table");
        when(dataAccessConnector.grantWholeTableAccess(userCredential, sqlRequest)).thenReturn(true);

        // act
        String result = createSQLService.grantTableAccessToSQLUser(new SQLCredentialsAndRequest(userCredential, sqlRequest));

        // assert
        assertThat(result).isEqualTo("test_table");
    }

    @Test
    public void grantTableAccessToSQLUser_givenSQLCredentialsAndRequest_shouldTriggerDataAccessConnectorCreateUser() throws SQLException, ClassNotFoundException {
        // arrange
        UserCredential userCredential = new UserCredential("user1", "Password1");
        SQLRequest sqlRequest =  new SQLRequest("table", "test_table");
        when(dataAccessConnector.grantWholeTableAccess(userCredential, sqlRequest)).thenReturn(true);

        // act
        String result = createSQLService.grantTableAccessToSQLUser(new SQLCredentialsAndRequest(userCredential, sqlRequest));

        // assert
        verify(dataAccessConnector).grantWholeTableAccess(userCredential, sqlRequest);
    }

    @Test
    public void grantColumnAccessToSQLUser_givenSQLCredentialsAndTableRequestAndColumnRequest_shouldReturnColumnName() throws SQLException, ClassNotFoundException {
        // arrange
        UserCredential userCredential = new UserCredential("user1", "Password1");
        SQLRequest sqlTableRequest =  new SQLRequest("table", "test_table");
        SQLRequest sqlColumnRequest =  new SQLRequest("column", "test_column");
        when(dataAccessConnector.grantColumnAccess(userCredential, sqlTableRequest, sqlColumnRequest)).thenReturn(true);

        // act
        String result = createSQLService.grantColumnAccessToSQLUser(new SQLCredentialsWithColumnRequest(userCredential, sqlTableRequest, sqlColumnRequest));

        // assert
        assertThat(result).isEqualTo("test_column");
    }

    @Test
    public void grantColumnAccessToSQLUser_givenSQLCredentialsAndTableRequestAndColumnRequest_shouldTriggerDataAccessConnector() throws SQLException, ClassNotFoundException {
        // arrange
        UserCredential userCredential = new UserCredential("user1", "Password1");
        SQLRequest sqlTableRequest =  new SQLRequest("table", "test_table");
        SQLRequest sqlColumnRequest =  new SQLRequest("column", "test_column");
        when(dataAccessConnector.grantColumnAccess(userCredential, sqlTableRequest, sqlColumnRequest)).thenReturn(true);

        // act
        String result = createSQLService.grantColumnAccessToSQLUser(new SQLCredentialsWithColumnRequest(userCredential, sqlTableRequest, sqlColumnRequest));

        // assert
        verify(dataAccessConnector).grantColumnAccess(userCredential, sqlTableRequest, sqlColumnRequest);
    }
}
