package extension;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import redis.embedded.RedisServer;

import static java.lang.String.format;

public class BeforeAndAfterExtension implements BeforeAllCallback, AfterAllCallback {

    private static final Logger LOGGER = LoggerFactory.getLogger(BeforeAndAfterExtension.class);

    private static final Integer PORT = 7001;

    private RedisServer redisServer;

    @Override
    public void beforeAll(ExtensionContext context) {
        redisServer = buildRedisServer();

        try {
            LOGGER.info(() -> "Starting redis server...");
            if (!redisServer.isActive()) {
                redisServer.start();
            }
            LOGGER.info(() -> format("Started redis server on port %s", redisServer.ports()));

        } catch (RuntimeException e) {
            LOGGER.error(() -> format("Failed to launch redis server, %s", e));
        }
    }

    @Override
    public void afterAll(ExtensionContext context) {
        redisServer.stop();
        LOGGER.info(() -> "Stopped redis server");
    }

    private RedisServer buildRedisServer() {
        return RedisServer.builder().port(PORT).build();
    }
}
