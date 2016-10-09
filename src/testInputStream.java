import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by juedaiyuer on 16-9-28.
 */
public class testInputStream {
    public static void main(String[] args) {
        try {
            FileInputStream fis = new FileInputStream(new File("/home/juedaiyuer/opensource/jdk1.8/README.html"));
            try{
                byte[] b = new byte[fis.available()];
                fis.read(b);
                fis.close();
                String str2 = new String(b);
                System.out.println(str2);
            }catch (IOException e){
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}