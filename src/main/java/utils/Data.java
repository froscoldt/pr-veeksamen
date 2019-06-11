package utils;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.concurrent.Callable;

/**
 *
 * @author Christian
 */
public class Data implements Callable<String> {

    Integer week;
    String comp;
    String addr;

    public Data(Integer week, String comp, String addr) {
        this.week = week;
        this.comp = comp;
        this.addr = addr;
    }

    @Override
    public String call() throws Exception {
        URL url = new URL("http://localhost:3333/availableCars?week=" + week + "&comp=" + comp + "&addr=" + addr);
        System.out.println(url);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Accept", "application/json;charset=UTF-8");
        con.setRequestProperty("User-Agent", "server");
        Scanner scan = new Scanner(con.getInputStream());
        String jsonStr = "";
        while (scan.hasNext()) {            
            jsonStr += scan.nextLine();
        }
        scan.close();
        return jsonStr;
    }

}
