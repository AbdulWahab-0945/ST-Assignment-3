import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {
    private LoginApp loginApp;

    @BeforeEach
    void setUp() {
        loginApp = new LoginApp();
    }

    @Test
    void testSuccessfulLogin() {
        String result = loginApp.authenticateUser("johndoe@example.com", "password123");
        assertNotNull(result, "Valid credentials should return the user's name.");
    }

    @Test
    void testIncorrectEmail() {
        String result = loginApp.authenticateUser("invalid@example.com", "password123");
        assertNull(result, "Invalid email should not return a user's name.");
    }

    @Test
    void testIncorrectPassword() {
        String result = loginApp.authenticateUser("johndoe@example.com", "wrongPassword");
        assertNull(result, "Incorrect password should not return a user's name.");
    }

    @Test
    void testEmptyEmailAndPassword() {
        String result = loginApp.authenticateUser("", "");
        assertNull(result, "Empty credentials should not return a user's name.");
    }

    @Test
    void testSQLInjectionAttempt() {
        String result = loginApp.authenticateUser("' OR '1'='1", "Password");
        assertNull(result, "SQL injection attempt should not return a user's name.");
    }
}
