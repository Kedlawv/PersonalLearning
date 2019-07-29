package logback_slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Foo {

    static final Logger LOGGER = LoggerFactory.getLogger(Foo.class);

    int i;

    public void doIt(){
        i++;
        LOGGER.debug("Foo logging. {}", i);

    }
}
