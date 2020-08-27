package net.creeperhost.creeperlauncher;

import java.io.File;
import java.util.Arrays;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class CreeperLogger
{
    public static CreeperLogger INSTANCE = new CreeperLogger();
    private static Logger logger;
    private String filename = Constants.WORKING_DIR + File.separator + "launcher.log";
    private FileHandler fileHandler;
    private final int limit = 1024 * 10000; //10 MB
    private SimpleFormatter simpleFormatter;

    public CreeperLogger()
    {
        logger = Logger.getLogger("launcher.log");

        simpleFormatter = new SimpleFormatter();
        try
        {
            fileHandler = new FileHandler(filename, limit, 1, true);
            logger.addHandler(fileHandler);
            simpleFormatter = new SimpleFormatter();
            fileHandler.setFormatter(simpleFormatter);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void info(String input)
    {
        logger.info(input);
    }

    private String throwableToString(Throwable ex)
    {
        StringBuilder printStr = new StringBuilder();
        printStr.append(ex.getClass().toString()).append(": ").append(ex.getMessage()).append("\n");
        for(StackTraceElement el: ex.getStackTrace())
        {
            printStr.append(el.toString()).append("\n");
        }
        return printStr.toString();
    }

    public void warning(String input)
    {
        logger.warning(input);
    }

    public void warning(String input, Throwable ex)
    {
        warning(input + "\n" + throwableToString(ex));
    }

    public void error(String input)
    {
        logger.severe(input);
    }

    public void error(String input, Throwable ex)
    {
        error(input + "\n" + throwableToString(ex));
    }

    public void debug(String input, Throwable ex) {
        debug(input + "\n" + throwableToString(ex));
    }

    public void debug(String input)
    {
        if (CreeperLauncher.verbose) logger.log(Level.INFO, input);
    }
}
