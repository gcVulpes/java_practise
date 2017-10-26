package vulpes.tools;

/**
 * 读取本地配置文件内容
 *
 * Created by kadokawa on 24/10/17.
 */
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Constants {
    public final static String FrameSessionUserKey="FrameSessionUserKey";

    private static Properties properties = null;

    public static String getLocaleValue(String key){

        Log log = LogFactory.getLog(Constants.class);

        if (properties == null) {
            properties = new Properties();
            File ipsFile = new File(Constants.class.getResource("/").getPath() + File.separator + "locale.properties");
            log.info("--locale file-->:" + ipsFile.getPath());
            try {
                properties.load(new FileInputStream(ipsFile));
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        return properties.getProperty(key);

    }

}