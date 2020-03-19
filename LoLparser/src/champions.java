import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class champions {

    public champions() throws IOException {

    }

    public void getData() throws IOException {
        String outputFile = "LoLparser/CSVs/Champions.csv";
        String urlWhole = "http://ddragon.leagueoflegends.com/cdn/10.6.1/data/en_US/champion.json";
        String championData = parser.returnJsonStringFromUrl(urlWhole,"");
        System.out.println(championData);

        String replaceString = '"' + "type" +'"';
        String replace = '"' + "data" + '"';
        String with = '"' + "champions";
        championData = championData.substring(74,championData.length()-1);
        System.out.println(championData);
        championData = "{" + '"' + "champions" + '"' + ":[" + championData + "]}";
        System.out.println(championData);
        parser.generateCSVFromJString(championData,outputFile);
    }
}
