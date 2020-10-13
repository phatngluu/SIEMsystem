package SIEMsystem.collector;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.espertech.esper.runtime.client.EPRuntime;

import nl.basjes.parse.core.Parser;
import nl.basjes.parse.core.exceptions.DissectionFailure;
import nl.basjes.parse.core.exceptions.InvalidDissectorException;
import nl.basjes.parse.core.exceptions.MissingDissectorsException;
import nl.basjes.parse.httpdlog.HttpdLoglineParser;


public class EventCollector {
    private EPRuntime runtime;

    public EventCollector(EPRuntime runtime) {
        this.runtime = runtime;
    }
    
    public void collectAccessLog(){
        // while true
        // if new access log
        //      parse & send event bean
        File file = new File(".resources/logs/input.log");

        String logformat = "%h %l %u %t \"%r\" %>s %b \"%{Referer}i\" \"%{User-Agent}i\"";
        Parser<AccessLog> dummyParser = new HttpdLoglineParser<AccessLog>(AccessLog.class, logformat);

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String log = scanner.nextLine();
                AccessLog logObj;
                try {
                    logObj = dummyParser.parse(log);
                    System.out.println(logObj);
                    
                    // Send event bean
                    this.runtime.getEventService().sendEventBean(logObj, "AccessLog");
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
