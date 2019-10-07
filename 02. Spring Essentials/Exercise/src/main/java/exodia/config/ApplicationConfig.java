package exodia.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import javax.validation.Validator;
import javax.validation.spi.ValidationProvider;
import java.util.TimeZone;

@Configuration
public class ApplicationConfig {

    /**
     * Configure {@link ModelMapper} to use field access (hibernate will use reflection to access fields of the entity),
     * instead of property(hibernate will use the getters of the entity) access for mapping between classes
     * and instances thus promoting better encapsulation and immutability.
     */
    @Bean
    ModelMapper createModelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration()
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                .setFieldMatchingEnabled(true);

        return modelMapper;
    }

    /**
     * Set system {@link TimeZone} to {@value #"UTC"} to match setting used for database connection
     */
    @PostConstruct
    void started() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }
}
