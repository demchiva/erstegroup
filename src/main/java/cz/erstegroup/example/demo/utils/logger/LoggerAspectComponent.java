package cz.erstegroup.example.demo.utils.logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.Arrays;

/**
 * Component for creating aspect for logging
 */
@Component
@Aspect
class LoggerAspectComponent {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggerAspectComponent.class);

    /**
     * Logs all method calls for controllers and services.
     */
    @Before(
            "within(cz.erstegroup.example.demo.service..*) " +
                    "|| within(cz.erstegroup.example.demo.controller..*)"
    )
    public static void logExecutedMethod(@NotNull final JoinPoint joinPoint) {
        StringBuilder sb = new StringBuilder();
        sb.append("Entering ").append(joinPoint.getSignature());

        //print arguments
        Object[] args = joinPoint.getArgs();
        if (args != null && args.length != 0) {
            sb.append(" Parameters:").append(Arrays.toString(args));
        }

        LOGGER.info(sb.toString());
    }
}
