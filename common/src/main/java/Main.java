import cfg.Tables;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException {
        URL resource = Class.class.getResource("classpath:/json");
        System.out.println(resource);
        Tables tables = new Tables(file -> JsonParser.parseString(
                new String(Files.readAllBytes(Paths.get("classpath:/json", file + ".json")), "UTF-8")));
        System.out.println("== run == " + tables.getTbGlobalConfig().getX1());
    }
}
