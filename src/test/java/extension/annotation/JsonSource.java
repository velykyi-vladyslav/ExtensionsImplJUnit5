package extension.annotation;

import org.junit.jupiter.params.provider.ArgumentsSource;
import extension.provider.JsonArgumentProvider;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target(METHOD)
@ArgumentsSource(JsonArgumentProvider.class)
public @interface JsonSource {

    String value();
}
