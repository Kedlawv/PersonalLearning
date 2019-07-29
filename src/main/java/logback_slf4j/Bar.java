package logback_slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Bar {

    static final Logger LOGGER = LoggerFactory.getLogger(Bar.class);

    int i;

    public void doIt(){
        i++;
        LOGGER.debug("Bar logging. {}", i);

    }
}