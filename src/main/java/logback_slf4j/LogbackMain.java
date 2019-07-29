package logback_slf4j;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//https://logback.qos.ch/manual/architecture.html

public class LogbackMain {

    final static Logger LOGGER = LoggerFactory.getLogger(LogbackMain.class);
    public static void main(String[] args) {

        int testVar = 34;

        LOGGER.info("Logger test");

        //print internal state of the LOGGER
        //  LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        //  StatusPrinter.print(loggerContext);
        //added <statusListener class="ch.qos.logback.core.status.OnConsoleStatusListener" />
        //to the logback.xml which does the same thing

        LOGGER.info("This is not optimised standard concatenation log with variable: " + testVar);

        if(LOGGER.isInfoEnabled()){
            LOGGER.info("This log uses standard concatenation but checks if info level " +
                    "logging is enabled variable: " + testVar);
        }

        LOGGER.info("This is parameterized log with variable: {} More convenient way of the example above",testVar);

        Foo foo = new Foo();
        foo.doIt();
        Bar bar = new Bar();
        bar.doIt();



    }
}
