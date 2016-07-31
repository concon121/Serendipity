package uk.co.cbsoftware.serendipity.util.spring.logging;

import org.springframework.stereotype.Component;

import uk.co.cbsoftware.serendipity.util.spring.logging.impl.LogAsString;

@Component("ToLog")
public class ToLog {
    
    @LogResult(implementation = LogAsString.class, logLevel = LogLevel.ERROR)
    public String logThis() {
        return "Is this working???";
    }

}
