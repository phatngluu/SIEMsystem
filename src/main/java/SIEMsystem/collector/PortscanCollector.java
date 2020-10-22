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

public class PortscanCollector extends Thread {
    @Override
    public void run() {
        int numberoflog = 0;
        while (true) {
            ArrayList<AccessLogEvent> event = null;
            try {
                event = getEvent();
            } catch (IOException e) {
                e.printStackTrace();
            }
            int currnumberoflog = event.size();
            if (numberoflog < currnumberoflog) {
                for (int i = numberoflog; i < currnumberoflog; i++) {
                    CEPEngine.getCreatedInstance().getRuntime().getEventService().sendEventBean(event.get(i), "AccessLogEvent");
                }
                numberoflog = currnumberoflog;
            }
        }
    }

    private ArrayList<AccessLogEvent> getEvent() throws IOException {
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
            } catch (DissectionFailure | InvalidDissectorException | MissingDissectorsException e) {
                System.out.println("Failed to parse.");
            }
        }
        return rs;
    }
}
