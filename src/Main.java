import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Main {
	GraphFrame frame;
    public static void main(String[] args){
    	new Main();
    	System.out.println("Done!");
    }
    public static void replace(String fileName) {
        String search = ".";
        String replace = ",";
        Charset charset = StandardCharsets.UTF_8;
        Path path = Paths.get(fileName);
        try {
	        Files.write(path,
	            new String(Files.readAllBytes(path), charset).replace(search, replace)
	                .getBytes(charset));
        } catch (IOException e) {
	        e.printStackTrace();
        }
    }
    Main()
    {
    	frame = new GraphFrame("Окошко", 600, 600);
    	frame.DrawPolynom();
    	frame.DrawApprox();
    }
}
