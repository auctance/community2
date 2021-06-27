package life.majiang.community2.mapper;


import life.majiang.community2.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.mybatis.spring.annotation.MapperScan;

@Mapper
public interface UserMapper {
    @Insert("insert into (name,accountId,token,gmtCreate,gmtModified) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified})")
    void insert(User user);
}