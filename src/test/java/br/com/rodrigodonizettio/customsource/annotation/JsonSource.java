package br.com.rodrigodonizettio.customsource.annotation;

import br.com.rodrigodonizettio.customsource.JsonArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@ArgumentsSource(JsonArgumentsProvider.class)
public @interface JsonSource {
    String text() default "{}";
}
