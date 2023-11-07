package com.compassuol.sp.challenge.msuser.services.assembler;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.addConverter(stringToDateConverter()); // Adiciona um conversor para a data
        return modelMapper;
    }

    @Bean
    public Converter<String, Date> stringToDateConverter() {
        return new Converter<String, Date>() {
            @Override
            public Date convert(MappingContext<String, Date> context) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
                try {
                    return dateFormat.parse(context.getSource());
                } catch (ParseException e) {
                    throw new IllegalArgumentException("Erro ao converter String para Date", e);
                }
            }
        };
    }
}

