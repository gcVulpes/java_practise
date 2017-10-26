package vulpes.tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 利用正则表达式判断字符串是否是数字
 * Created by kadokawa on 24/10/17.
 */
public class test {

    public boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
//        if( !isNum.matches() ){
//            return false;
//        }
//        return true;
        return isNum.matches();
    }
}