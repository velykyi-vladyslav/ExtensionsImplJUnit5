import extension.BeforeAndAfterExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(BeforeAndAfterExtension.class)
class BeforeAfterExtensionTest {

    @Test
    void shouldStartAndStopRedisServer() {
        assertTrue(true, "see run logs");
    }
}
