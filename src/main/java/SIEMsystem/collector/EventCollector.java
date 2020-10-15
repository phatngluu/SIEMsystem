package SIEMsystem.collector;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import com.espertech.esper.runtime.client.EPRuntime;

import SIEMsystem.event.AccessLogEvent;
import nl.basjes.parse.core.Parser;
import nl.basjes.parse.core.exceptions.DissectionFailure;
import nl.basjes.parse.core.exceptions.InvalidDissectorException;
import nl.basjes.parse.core.exceptions.MissingDissectorsException;
import nl.basjes.parse.httpdlog.HttpdLoglineParser;


public class EventCollector {
    private EPRuntime runtime;
	private static boolean add;

    public EventCollector(EPRuntime runtime) {
        this.runtime = runtime;
    }

    public void collectLog() throws FileNotFoundException {
        int numberoflog = 0;
        while (true){
            ArrayList<AccessLogEvent> event = null;
            try {
                event = getEvent();        
            } catch(IOException e){
                e.printStackTrace();
            }
            int currnumberoflog = event.size();
            if (numberoflog < currnumberoflog){
                for (int i = numberoflog ; i < currnumberoflog ; i++){
                    this.runtime.getEventService().sendEventBean(event.get(i), "AccessLogEvent");
                }
            numberoflog = currnumberoflog;
            }
        }
    }

    public static ArrayList<AccessLogEvent> getEvent() throws IOException {
        ArrayList<AccessLogEvent> rs = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("/var/log/apache2/access.log"));
        String line = null;
        String logformat = "%h %l %u %t \"%r\" %>s %b \"%{Referer}i\" \"%{User-Agent}i\"";
        Parser<AccessLogEvent> dummyParser = new HttpdLoglineParser<AccessLogEvent>(AccessLogEvent.class, logformat);
        while ((line = reader.readLine()) != null) {
            AccessLogEvent logObj;
            try {
                logObj = dummyParser.parse(line);
                rs.add(logObj);
            } catch(DissectionFailure | InvalidDissectorException | MissingDissectorsException e){
                System.out.println("Failed to parse.");  
            }    
        }
        return rs;
    }
    
    public void collectAccessLog(){
        // while true
        // if new access log
        //      parse & send event bean
        File file = new File(".resources/logs/input.log");

        String logformat = "%h %l %u %t \"%r\" %>s %b \"%{Referer}i\" \"%{User-Agent}i\"";
        Parser<AccessLogEvent> dummyParser = new HttpdLoglineParser<AccessLogEvent>(AccessLogEvent.class, logformat);

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String log = scanner.nextLine();
                AccessLogEvent logObj;
                try {
                    logObj = dummyParser.parse(log);
                    System.out.println(logObj);
                    
                    // Send event bean
                    this.runtime.getEventService().sendEventBean(logObj, "AccessLogEvent");
                } catch (DissectionFailure | InvalidDissectorException | MissingDissectorsException e) {
                    // e.printStackTrace();
                    System.out.println("Failed to parse.");
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Failed to open file.");
            // e.printStackTrace();
        }
    }
}
