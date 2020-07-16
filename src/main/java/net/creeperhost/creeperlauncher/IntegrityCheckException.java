package net.creeperhost.creeperlauncher;

import net.creeperhost.creeperlauncher.api.handlers.InstallInstanceHandler;

import java.util.ArrayList;
import java.util.List;

public class IntegrityCheckException extends RuntimeException
{
    private Throwable otherThrowable = null;
    private int errorCode = 0;
    private String checksum = "";
    private List<String> checksums = new ArrayList<>();
    private long size = 0;
    private long expectedSize = 0;
    private String source = "";
    private String destination = "";

    public IntegrityCheckException(String detailMessage)
    {
        super(detailMessage);
    }

    public IntegrityCheckException(Throwable t, int errorCode, String checksum, List<String> checksums, long size, long expectedSize, String source, String destination)
    {
        this(t.getMessage(), errorCode, checksum, checksums, size, expectedSize, source, destination);
        initCause(t);
        otherThrowable = t;
    }

    public IntegrityCheckException(String detailMessage, int errorCode, String checksum, List<String> checksums, long size, long expectedSize, String source, String destination)
    {
        super(detailMessage);
        this.errorCode = errorCode;
        this.checksum = checksum;
        this.checksums = checksums;
        this.size = size;
        this.expectedSize = expectedSize;
        this.source = source;
        this.destination = destination;
        //TODO: Remove these once exceptions work properly again
        InstallInstanceHandler.hasError.set(true);
        InstallInstanceHandler.lastError.set(detailMessage);
        this.printStackTrace();
    }

    @Override
    public void printStackTrace()
    {
        StringBuilder errorStr = new StringBuilder();
        super.printStackTrace();
        if (otherThrowable != null)
        {
            errorStr.append("Caught throwable: \n");
            otherThrowable.printStackTrace();
        }
        errorStr.append("errorCode: ").append(errorCode).append("\n");
        errorStr.append("checksum: ").append(checksum).append("\n");
        if (checksums != null)
        {
            for (String validChecksum : checksums)
            {
                errorStr.append("validChecksum: ").append(validChecksum).append("\n");
            }
        }
        errorStr.append("size: ").append(size).append("\n");
        errorStr.append("expectedSize: ").append(expectedSize).append("\n");
        errorStr.append("source: ").append(source).append("\n");
        errorStr.append("destination: ").append(destination).append("\n");
        CreeperLogger.INSTANCE.error(errorStr.toString());
    }
}
