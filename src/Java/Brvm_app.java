package Java;
import quickfix.*;
import quickfix.field.*;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import static quickfix.field.SecurityListRequestType.ALL_SECURITIES;

public class Brvm_app extends MessageCracker implements Application {
    Logger logger = Logger.getInstance();
    public void onCreate(SessionID sessionId){
        System.out.println("onCreate"+sessionId.toString());
        logger.log(sessionId);
    }

    //methode onLogon
    public void onLogon(SessionID sessionId){
        System.out.println("onLogon"+sessionId.toString());
        logger.log(sessionId);


        quickfix.fix44.SecurityListRequest message = new quickfix.fix44.SecurityListRequest(

                new SecurityReqID( new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date())),
                new SecurityListRequestType(4)
        );

        //SecurityReqID securityReqID, SecurityListRequestType securityListRequestType

        //message.set(new SubscriptionRequestType("1"));//SNAPSHOT_PLUS_UPDATES


        try {
            Session.sendToTarget(message, sessionId);
        } catch (SessionNotFound sessionNotFound) {
            sessionNotFound.printStackTrace();
        }



     //demande de la liste des instruments

       // quickfix.fix44.SecurityListRequest  message = new quickfix.fix44.SecurityListRequest( );
        /* new SecurityListRequestType.ALL_SECURITIES); */

           // message.set(new SubscriptionRequestType());

        //fin de demande de la liste des instruments
    }
    //fin methode onLogon


    public void onLogout(SessionID sessionId){
        System.out.println("onLogout"+sessionId.toString());
        logger.log(sessionId);
    }
    public void toAdmin(Message message, SessionID sessionId) {
        System.out.println("toAdmin" + sessionId.toString());
        System.out.println("toAdmin" + message.toString());
        logger.log(sessionId,message);

    }
    public void toApp(Message message, SessionID sessionId)
            throws DoNotSend{
        System.out.println("toApp"+sessionId.toString());
        System.out.println("toApp"+message.toString());
        logger.log(sessionId,message);
    }
    public void fromAdmin(Message message, SessionID sessionId)
            throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, RejectLogon{
        System.out.println("fromAdmin"+sessionId.toString());
        System.out.println("fromAdmin"+message.toString());
        logger.log(sessionId,message);
    }
    public void fromApp(Message message, SessionID sessionId)
            throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, UnsupportedMessageType{
        crack(message, sessionId);
        System.out.println("fromApp"+sessionId.toString());
        System.out.println("fromApp"+message.toString());
        logger.log(sessionId,message);
    }
    /*public void onMessage(quickfix.fix42.NewOrderSingle message, SessionID sessionID)
            throws FieldNotFound, UnsupportedMessageType, IncorrectTagValue {

        ClOrdID clOrdID = new ClOrdID();
        message.get(clOrdID);

        ClearingAccount clearingAccount = new ClearingAccount();
        message.get(clearingAccount);
    }
    public void onMessage(quickfix.fix42.OrderCancelRequest message, SessionID sessionID)
            throws FieldNotFound, UnsupportedMessageType, IncorrectTagValue {

        ClOrdID clOrdID = new ClOrdID();
        message.get(clOrdID);

        // compile time error!! field not defined for OrderCancelRequest
        ClearingAccount clearingAccount = new ClearingAccount();
        //message.get(clearingAccount);
    }
    @Handler
    public void myEmailHandler(quickfix.fix50.Email email, SessionID sessionID) {
        // handler implementation
    }
    public void onMessage(quickfix.fix44.Email email, SessionID sessionID) {
        // handler implementation
    }

    void sendOrderCancelRequest() throws SessionNotFound
    {
        quickfix.fix41.OrderCancelRequest message = new quickfix.fix41.OrderCancelRequest(
                new OrigClOrdID("123"),
                new ClOrdID("321"),
                new Symbol("LNUX"),
                new Side(Side.BUY));

        message.set(new Text("Cancel My Order!"));

        Session.sendToTarget(message, "TW", "TARGET");
    }*/

}
