package extension.provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import extension.annotation.JsonSource;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.support.AnnotationConsumer;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class JsonArgumentProvider implements ArgumentsProvider, AnnotationConsumer<JsonSource> {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private String resource;

    @Override
    public void accept(JsonSource jsonSource) {
        resource = jsonSource.value();
    }

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        Class<?> testClass = extensionContext.getRequiredTestClass();
        Class<?> parameterType = extensionContext.getRequiredTestMethod().getParameterTypes()[0];

        try (InputStream inputStream = testClass.getResourceAsStream(resource)) {
            return readValues(inputStream, parameterType).map(Arguments::of);
        }
    }

    private <T> Stream<T> readValues(InputStream inputStream, Class<T> parameterType) throws IOException {
        Iterator<T> iterator = OBJECT_MAPPER.readerFor(parameterType).readValues(inputStream);
        Spliterator<T> spliterator = Spliterators.spliteratorUnknownSize(iterator, Spliterator.ORDERED);

        return StreamSupport.stream(spliterator, false);
    }
}
