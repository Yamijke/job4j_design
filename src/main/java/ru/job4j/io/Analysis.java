package ru.job4j.io;

import java.io.*;

public class Analysis {
    public void unavailable(String source, String target) {
        String startTime = null;
        boolean stat = false;
        try (
                BufferedReader buf = new BufferedReader(new FileReader(source));
                PrintWriter print = new PrintWriter(new FileWriter(target))
        ) {
            String line;
            while ((line = buf.readLine()) != null) {
                String[] parts = line.split(" ");
                String status = parts[0];
                String time = parts[1];
                if ((status.equals("400") || status.equals("500")) && !stat) {
                    startTime = time;
                    stat = true;
                } else if ((status.equals("200") || status.equals("300")) && stat) {
                    print.println(startTime + ";" + time + ";");
                    stat = false;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}
