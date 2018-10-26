package cn.lyf1433223.contorller;


import cn.lyf1433223.dao.StudentDao;
import cn.lyf1433223.pojo.Student;
import cn.lyf1433223.util.JedisUtil;
import com.google.gson.Gson;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
@Scope("prototype")
public class StudentContorller {


    private static final ServletRequestAttributes servletRequestAttributes =
            (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
    private static final HttpServletRequest request = servletRequestAttributes.getRequest();
    private static final HttpSession session = request.getSession();
    private static final HttpServletResponse response = servletRequestAttributes.getResponse();


    private static final Logger logger = LogManager.getLogger();
    private static final Gson gson = new Gson();
//    private static final Jedis jedis = new Jedis("192.168.43.178");


    @Autowired
    @Qualifier("studentDao")
    private StudentDao studentDao;


    @Resource
    private JedisUtil jedisutil;



    @RequestMapping(value = "student",method = RequestMethod.GET)
    public String GetStudent(Model model){
        List<Student> students = new ArrayList<>();
        Jedis jedis = jedisutil.GetJedis();
        Boolean boo = jedis.exists("StudentList");
//        List<String> studentList = jedis.lrange("studentList", 0, -1);
        if(boo==false){
            long l = System.currentTimeMillis();
            students = studentDao.GetAll();
            for (Student stu : students){
                jedis.lpush("StudentList",gson.toJson(stu));
            }
            long l1 = System.currentTimeMillis();
            logger.debug("数据库取值："+(l1-l));
        }else{
            long l = System.currentTimeMillis();
            List<String> studentList1 = jedis.lrange("StudentList", 0, -1);
            for (String stu : studentList1){
                students.add(gson.fromJson(stu,Student.class));
            }
            long l1 = System.currentTimeMillis();
            logger.debug("redis取值："+(l1-l));
        }
        model.addAttribute("student",students);
//        session.setAttribute("student",students);
        return "hello.jsp";
    }

    @RequestMapping(value = "add",method = RequestMethod.POST)
    public String Add(Model model,Student student){
        int add = studentDao.Add(student);
        model.addAttribute("add",add);
//        session.setAttribute("add",add);
        return "hello.jsp";
    }

    @RequestMapping(value = "update",method = RequestMethod.POST)
    public String update(Model model,Student student){
        int update = studentDao.Update(student);
        model.addAttribute("update",update);
//        session.setAttribute("update",update);
        return "hello.jsp";
    }

    @RequestMapping(value = "delete",method = RequestMethod.POST)
    public String delete(Model model,Student student){
//        int delete = studentDao.Delete(student.getSid());
//        model.addAttribute("delete",delete);
//        session.setAttribute("delete",delete);
        Jedis jedis = jedisutil.GetJedis();
        Long studentList = jedis.del("StudentList");
        logger.debug(studentList);
        return "hello.jsp";
    }

    @RequestMapping(value = "selectOne")
    @ResponseBody
    public void SelectOne(){
        System.out.println("aaaaa111");
        try {
            PrintWriter outout = response.getWriter();
            outout.write("aa");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "file",method = RequestMethod.POST)
    public String File(Model model,MultipartFile[] files) throws Exception{

        long l = System.currentTimeMillis();
//        File file = new File("G:\\image\\"+files.getOriginalFilename());
//        files.transferTo(file);

        for (int i =0;i<files.length;i++){
            logger.debug(files[i].getOriginalFilename().toString());
        }

        long l1 = System.currentTimeMillis();
        logger.debug(l1-l);
        return "hello.jsp";
    }








}
