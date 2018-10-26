package cn.lyf1433223.contorller;

import cn.lyf1433223.dao.StudentDao;
import cn.lyf1433223.pojo.Student;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Set;

public class Test {


    public static void main(String[] args) {
//        Gson gson = new Gson();
//
//        Logger logger = LogManager.getLogger();
//        Jedis jedis = new Jedis("192.168.43.178");
//        Set<String> keys = jedis.keys("*");
//        char a = '项';

//        List<String> studentList = jedis.lrange("studentList", 0, -1);
//        for (String s : studentList){
//            logger.debug(s);
//        }

//        jedis.del("studentList");

//        List<String> list = jedis.lrange("11", 0, -1);

//        String a = new String("aa");
//        String b = new String("aa");
//        System.out.println(a.equals(b));



    }



}
