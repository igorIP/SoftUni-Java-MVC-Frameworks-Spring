package exodia.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * For now Spring's auto configuration takes care of everything.
 * If we want to add additional custom configuration (ex: make custom folders with templates)
 * we need to implement the {@link WebMvcConfigurer} interface.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    public static final String SESSION_ATTRIBUTE_USERNAME = "username";

    public static final String URL_INDEX = "/";
    public static final String URL_LOGIN = "/login";
    public static final String URL_LOGOUT = "/logout";
    public static final String URL_REGISTER = "/register";
    public static final String URL_SCHEDULE = "/schedule";
    public static final String URL_DETAILS = "/details";
    public static final String URL_PRINT = "/print";
    public static final String URL_DOWNLOAD = "/download";

}
