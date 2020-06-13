package com.example.demo.mssqluser;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateSQLUserServiceTest {

    @Mock
    private DataAccessConnector dataAccessConnector;

    @InjectMocks
    private CreateSQLService createSQLService;

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
}
