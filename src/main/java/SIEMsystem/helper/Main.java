package SIEMsystem.helper;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import nl.basjes.parse.core.Parser;
import nl.basjes.parse.core.exceptions.DissectionFailure;
import nl.basjes.parse.core.exceptions.InvalidDissectorException;
import nl.basjes.parse.core.exceptions.MissingDissectorsException;
import nl.basjes.parse.httpdlog.HttpdLoglineParser;

public class Main {
    public static void main(String[] args) {
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
                } catch (DissectionFailure | InvalidDissectorException | MissingDissectorsException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

   }
}
