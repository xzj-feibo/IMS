package com.xzj.mapper;

import com.xzj.model.Users;
import com.xzj.model.UsersExample;
import java.util.List;

import com.xzj.resp.UserResp;
import com.xzj.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UsersMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user1
     *
     * @mbg.generated Thu Jul 06 21:22:20 CST 2023
     */
    long countByExample(UsersExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user1
     *
     * @mbg.generated Thu Jul 06 21:22:20 CST 2023
     */
    int deleteByExample(UsersExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user1
     *
     * @mbg.generated Thu Jul 06 21:22:20 CST 2023
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user1
     *
     * @mbg.generated Thu Jul 06 21:22:20 CST 2023
     */
    int insert(Users record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user1
     *
     * @mbg.generated Thu Jul 06 21:22:20 CST 2023
     */
    int insertSelective(Users record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user1
     *
     * @mbg.generated Thu Jul 06 21:22:20 CST 2023
     */
    List<Users> selectByExample(UsersExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user1
     *
     * @mbg.generated Thu Jul 06 21:22:20 CST 2023
     */
    Users selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user1
     *
     * @mbg.generated Thu Jul 06 21:22:20 CST 2023
     */
    int updateByExampleSelective(@Param("record") Users record, @Param("example") UsersExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user1
     *
     * @mbg.generated Thu Jul 06 21:22:20 CST 2023
     */
    int updateByExample(@Param("record") Users record, @Param("example") UsersExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user1
     *
     * @mbg.generated Thu Jul 06 21:22:20 CST 2023
     */
    int updateByPrimaryKeySelective(Users record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user1
     *
     * @mbg.generated Thu Jul 06 21:22:20 CST 2023
     */
    int updateByPrimaryKey(Users record);
    UserResp login(@Param("account") String account, @Param("password") String password);

    List<UserVo> selectUserList(@Param("userName") String userName, @Param("userMobile") String userMobile);

    int resetPwd(Long userId);
}