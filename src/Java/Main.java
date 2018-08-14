package Java;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import quickfix.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class Main {


    public static void main(String[] args) {
        /*PrintStream o = new PrintStream(new File("output.txt"));
        PrintStream console = System.out;
        System.setOut(o);*/
        //if (args.length != 1) return;
        //String fileName = args[0];
        InputStream inputStream = null;
        // FooApplication is your class that implements the Application interface
        Brvm_app application = new Brvm_app();
        if (args.length == 0) {
                inputStream = Main.class.getClassLoader().getResourceAsStream("brvm.cfg");
        }
        /*} else if (args.length == 1) {
            inputStream = new FileInputStream(args[0]);
        }*/
        if (inputStream == null) {

            System.out.println("usage: " + Main.class.getName() + " [configFile].");
            return;
        }
        SessionSettings settings = null;
        try {
            settings = new SessionSettings(inputStream);
        } catch (ConfigError configError) {
            configError.printStackTrace();
        }
        MessageStoreFactory storeFactory = new FileStoreFactory(settings);
        LogFactory logFactory = new FileLogFactory(settings);
        MessageFactory messageFactory = new DefaultMessageFactory();
        Initiator initiator = null;
            try {
                initiator = new SocketInitiator(
                        application, storeFactory, settings, logFactory, messageFactory);
            } catch (ConfigError configError1) {
                    configError1.printStackTrace();
            }
        try {
            initiator.start();

        } catch (ConfigError configError) {
            configError.printStackTrace();
        }

        while(true) {
            try {
                Thread.sleep(1);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        //initiator.stop();

    }
}
