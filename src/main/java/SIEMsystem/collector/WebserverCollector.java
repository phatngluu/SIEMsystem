package SIEMsystem.collector;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import SIEMsystem.cep.CEPEngine;
import SIEMsystem.event.AccessLogEvent;
import nl.basjes.parse.core.Parser;
import nl.basjes.parse.core.exceptions.DissectionFailure;
import nl.basjes.parse.core.exceptions.InvalidDissectorException;
import nl.basjes.parse.core.exceptions.MissingDissectorsException;
import nl.basjes.parse.httpdlog.HttpdLoglineParser;
/**
 * This class is for collecting events for web server module.
 * @author Lam Hai Son
 */
public class WebserverCollector extends Thread {
    private static int currLog = 0;
    private static String logFilePath = CEPEngine.getCreatedInstance().getProperty("WEBSERVER_LOG_FILE_PATH");

    /**
     * This method forward the event to CEPEngine every second
     */
    @Override
    public void run() {
        try {
            currLog = runfirst();
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {
            ArrayList<AccessLogEvent> event = null;
            try {
                event = getEvent();
            } catch (IOException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < event.size(); i++) {
                CEPEngine.getCreatedInstance().getRuntime().getEventService().sendEventBean(event.get(i), "AccessLogEvent");
            }
        }
    }

    /**
     * This class is for getting the first n lines in the log file
     * @return current number of line in the log file
     * @throws IOException
     */
    private int runfirst() throws IOException{
        
        BufferedReader reader = new BufferedReader(new FileReader(logFilePath));
        int n = 0;
        while (reader.readLine() != null) {
            n+=1;
        }
        reader.close();
        return n;
    }

    /**
     * This class is for getting the event from the log file
     * @return the array of new event that just added to log file
     * @throws IOException
     */
    private ArrayList<AccessLogEvent> getEvent() throws IOException {
        int rdLog = 0;
        ArrayList<AccessLogEvent> rs = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(logFilePath));
        String line;
        String logformat = "%h %l %u %t \"%r\" %>s %b \"%{Referer}i\" \"%{User-Agent}i\"";
        Parser<AccessLogEvent> dummyParser = new HttpdLoglineParser<AccessLogEvent>(AccessLogEvent.class, logformat);
        while (rdLog < currLog){
            reader.readLine();
            rdLog+=1;
        }
        while ((line = reader.readLine()) != null) {
            AccessLogEvent logObj;
            try {
                logObj = dummyParser.parse(line);
                rs.add(logObj);
            } catch (DissectionFailure | InvalidDissectorException | MissingDissectorsException e) {
                System.out.println("Failed to parse.");
            }
            currLog +=1;
        }
        reader.close();
        return rs;
    }
}
