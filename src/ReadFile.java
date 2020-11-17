import com.google.gson.Gson;
import jdk.nashorn.api.scripting.URLReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TimerTask;

public class ReadFile extends TimerTask {

    @Override
    public void run() {
        try {
            String s = "/tmp/exchange/buildID.txt";
            URL url1 = new URL(s);

            // read from the URL
            Scanner scan = new Scanner(url1.openStream());
            String str = new String();
            while (scan.hasNext()){
                str += scan.nextLine();
            }

            scan.close();

//            System.out.println(str);
            Gson gson = new Gson();
            JsonType test = gson.fromJson(str, JsonType.class);
            HashMap<String, String> map = new HashMap<>();
            String oldId = "";
            String buildId = test.getBuildID();
            if(buildId != oldId){
                String fileName = test.getFileName();
                String filePath = "/tmp/exchange/" + fileName;

                URL url = new URL(filePath);

                BufferedReader reader = new BufferedReader(new URLReader(url));
                String content;
                while ((content = reader.readLine())!=null){
                    content = content.trim();

                    String[] parts = content.split(":", 2);
                    if(parts.length == 2){
                        String key = parts[0];
                        System.out.println(key);
                        String value = parts[1].substring(0,parts[1].length()-1);
                        System.out.println(value);
                        map.put(key, value);
                    }

                }
                reader.close();
                System.out.println(map.toString());

                //System.out.println(java.time.LocalDateTime.now());
            }

        }catch ( IOException e){
            System.out.println(e);
        }


    }
}

