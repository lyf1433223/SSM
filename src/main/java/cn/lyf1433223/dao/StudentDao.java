package cn.lyf1433223.dao;


import cn.lyf1433223.pojo.Student;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("studentDao")
public interface StudentDao {

    /**
     * 查询所有
     */
    @Select("select * from tb_student")
    public List<Student> GetAll();

    /**
     * 添加一个学生
     */
    @Insert("insert into tb_student values(null,#{sname},#{ssex},#{sage})")
    public int Add(Student student);


    /**
     * 修改一个学生
     */
    @Update("update tb_student set sname=#{sname},ssex=#{ssex},sage=#{sage} where sid=#{sid}")
    public int Update(Student student);

    /**
     * 删除一个学生
     */
    @Delete("delete from tb_student where sid=#{sid}")
    public int Delete(@Param("sid") Integer sid);

    /**
     * 查询一个学生
     */
    @Select("select * from tb_student where sid = #{sid}")
    public Student selectOne(Student student);

}
