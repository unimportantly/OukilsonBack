package fr.oukilson.backend.configuration;

import fr.oukilson.backend.entity.RegexCollection;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Configuration
public class RegexConfiguration {

    @Bean
    public RegexCollection regexCollection(@Value("${environment.emailRegex}") String emailPattern,
                                           @Value("${environment.nicknameRegex}") String nicknamePattern,
                                           @Value("${environment.nameRegex}") String namePattern) {
//        List<Pattern> list = new ArrayList<>();
//        list.add(Pattern.compile(emailPattern));
//        list.add(Pattern.compile(nicknamePattern));
//        list.add(Pattern.compile(namePattern));
        return new RegexCollection(Pattern.compile(emailPattern),Pattern.compile(nicknamePattern),
                Pattern.compile(namePattern));
    }

}
