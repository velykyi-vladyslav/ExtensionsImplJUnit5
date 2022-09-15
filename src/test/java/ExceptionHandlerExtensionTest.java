import extension.ExceptionHandlerExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(ExceptionHandlerExtension.class)
class ExceptionHandlerExtensionTest {

    @Test
    @SuppressWarnings("java:S2699")
    void testExceptionHandlerExtension() {
        throw new NullPointerException("Extension test");
    }
}
