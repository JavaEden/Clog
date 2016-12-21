package com.caseyjbrooks.clog;

import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;

public class DefaultLogger implements ClogLogger {

    static {
        AnsiConsole.systemInstall();
    }

    private String logger;
    private int priority;

    public DefaultLogger(String logger, int priority) {
        this.logger = logger;
        this.priority = priority;
    }

    @Override
    public boolean isActive() {
        return true;
    }

    @Override
    public int log(String tag, String message) {
        System.out.println(tag + ": " + message);
        return 0;
    }

    @Override
    public int log(String tag, String message, Throwable throwable) {
        System.out.println(tag + ": " + message + " (" + throwable.getMessage() + ")");
        return 0;
    }

    @Override
    public int priority() {
        return priority;
    }

    private String getLevelString() {

        String out = "";

        switch(logger) {
            case Clog.KEY_V:   out += Ansi.ansi().fg(Ansi.Color.CYAN).a("VERBOSE: ").reset();  break;
            case Clog.KEY_D:   out += Ansi.ansi().fg(Ansi.Color.MAGENTA).a("DEBUG: ").reset(); break;
            case Clog.KEY_I:   out += Ansi.ansi().fg(Ansi.Color.GREEN).a("INFO: ").reset();    break;
            case Clog.KEY_W:   out += Ansi.ansi().fg(Ansi.Color.YELLOW).a("WARN: ").reset();   break;
            case Clog.KEY_E:   out += Ansi.ansi().fg(Ansi.Color.RED).a("ERROR: ").reset();     break;
            case Clog.KEY_WTF: out += Ansi.ansi().fg(Ansi.Color.RED).a("FATAL: ").reset();     break;
            default: break;
        }

        return out;
    }
}
