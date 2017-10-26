package vulpes.base;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

/**
 * commonsLogging 是 apache commons类库中的一员 可提供基本的日志功能 并能灵活的选择使用jdk默认的日志 或是 log4j提供的日志
 * Created by kadokawa on 25/10/17.
 */
public class commonsLogging {

    private static Log log = LogFactory.getLog(commonsLogging.class);

    @Test
    public void test(){
        log.debug("debug()...");
        log.error("error()...");
        log.info("info()...");
    }


}
