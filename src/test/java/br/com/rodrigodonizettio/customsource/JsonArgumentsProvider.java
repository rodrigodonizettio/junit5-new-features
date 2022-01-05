package br.com.rodrigodonizettio.customsource;

import br.com.rodrigodonizettio.customsource.annotation.JsonSource;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.support.AnnotationConsumer;

import java.util.function.Consumer;
import java.util.stream.Stream;

public class JsonArgumentsProvider implements ArgumentsProvider, AnnotationConsumer<JsonSource> {
    private String text;

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(text);

        return Stream.of(jsonNode).map(Arguments::of);
    }

    @Override
    public void accept(JsonSource jsonSource) {
        this.text = jsonSource.text();
    }

    @Override
    public Consumer<JsonSource> andThen(Consumer<? super JsonSource> after) {
        return AnnotationConsumer.super.andThen(after);
    }
}
