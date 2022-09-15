import extension.annotation.JsonSource;
import org.junit.jupiter.params.ParameterizedTest;
import velykyi.vladyslav.Worker;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

class ArgumentsProviderTest {

    private static final String EMPTY = "";

    @ParameterizedTest
    @JsonSource("test-data.json")
    void testArgumentsFromJsonExtensionViaCustomAnnotation(Worker worker) {
        Worker invalidWorker = buildInvalidWorker();

        assertNotEquals(invalidWorker.getName(), worker.getName());
        assertNotEquals(invalidWorker.getSurname(), worker.getSurname());
    }

    private Worker buildInvalidWorker() {
        return new Worker(null, EMPTY);
    }
}
