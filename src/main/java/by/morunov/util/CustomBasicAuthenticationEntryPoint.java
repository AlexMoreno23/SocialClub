package by.morunov.util;

import by.morunov.domain.dto.ErrorRsp;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Alex Morunov
 */
@Component
public class CustomBasicAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {
    private final static Logger LOGGER = LoggerFactory.getLogger(CustomBasicAuthenticationEntryPoint.class);
    private final ObjectMapper mapper;

    public CustomBasicAuthenticationEntryPoint(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authEx)
            throws IOException {
        response.addHeader("WWW-Authenticate", "Basic realm=\"" + getRealmName() + "\"");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        PrintWriter writer = response.getWriter();
        ErrorRsp error = new ErrorRsp("Cannot find user with this name and password", 403);
        LOGGER.info("Cannot authenticate user. Reason: [{}]", authEx.getMessage());
        writer.println(mapper.writeValueAsString(error));
    }

    @Override
    public void afterPropertiesSet() {
        setRealmName("Demo");
        super.afterPropertiesSet();
    }
}
