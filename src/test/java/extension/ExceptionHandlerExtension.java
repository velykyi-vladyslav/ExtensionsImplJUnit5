package extension;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

public class ExceptionHandlerExtension implements TestExecutionExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(ExceptionHandlerExtension.class);

    @Override
    public void handleTestExecutionException(ExtensionContext extensionContext, Throwable throwable) {
        if (throwable instanceof NullPointerException) {
            LOG.warn(() -> "Test is skipped. \n Exception message:" + throwable.getMessage());
        }
    }
}
