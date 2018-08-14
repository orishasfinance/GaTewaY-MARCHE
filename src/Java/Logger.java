package Java;

import quickfix.Message;
import quickfix.SessionID;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

public  class Logger {
    private static final Logger logger = new Logger();
    private static BufferedWriter output  = null;
    private static File file = null;
    private Logger() {
    }
    public static final Logger getInstance() {
        return logger;
    }

    public static void log(SessionID sessionId){
        try {
            file = new File("output.txt");
            output = new BufferedWriter(new FileWriter(file, true));
            output.write(sessionId.toString());
            output.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
    public static void log(SessionID sessionId, Message message){
        try {
            file = new File("output.txt");
            output = new BufferedWriter(new FileWriter(file, true));
            output.write(sessionId.toString());
            output.newLine();
            output.write(message.toString());
            output.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
