package apiworkshopmongodb.com.br.workshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;

@Configuration
public class MongoConfig {

    @Bean
    public org.springframework.beans.factory.config.BeanPostProcessor mappingMongoConverterCustomizer() {
        return new org.springframework.beans.factory.config.BeanPostProcessor() {
            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) {
                if (bean instanceof MappingMongoConverter converter) {
                    // Remove o campo _class
                    converter.setTypeMapper(new DefaultMongoTypeMapper(null));
                }
                return bean;
            }
        };
    }
}

