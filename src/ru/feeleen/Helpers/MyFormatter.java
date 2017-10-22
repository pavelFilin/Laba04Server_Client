package ru.feeleen.Helpers;

import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class MyFormatter extends Formatter {
    @Override
    public String format(LogRecord record) {
        Date date = new Date();
        String time = new String(date.toString());
        return time + " " + record.getLevel() + ": " + record.getMessage() + "\n";
    }
}


