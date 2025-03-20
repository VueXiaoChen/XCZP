package com.example.xblog.mapper;

import com.example.xblog.domain.UserFriends;
import com.example.xblog.domain.UserFriendsExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface UserFriendsMapper {
    long countByExample(UserFriendsExample example);

    int deleteByExample(UserFriendsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserFriends row);

    int insertSelective(UserFriends row);

    List<UserFriends> selectByExample(UserFriendsExample example);

    UserFriends selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") UserFriends row, @Param("example") UserFriendsExample example);

    int updateByExample(@Param("row") UserFriends row, @Param("example") UserFriendsExample example);

    int updateByPrimaryKeySelective(UserFriends row);

    int updateByPrimaryKey(UserFriends row);
}