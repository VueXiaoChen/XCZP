package com.example.xblog.service;

import com.example.xblog.domain.Employ;
import com.example.xblog.domain.UserExample;
import com.example.xblog.domain.User;
import com.example.xblog.domain.UserExample;
import com.example.xblog.exception.BusinessException;
import com.example.xblog.exception.BusinessExceptionCode;
import com.example.xblog.mapper.EmployMapper;
import com.example.xblog.mapper.UserMapper;
import com.example.xblog.req.*;
import com.example.xblog.resp.UserLoadingResp;
import com.example.xblog.resp.UserResp;
import com.example.xblog.util.CopyUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class UserService {
    private static final Logger LOG = (Logger) LoggerFactory.getLogger(UserService.class);

    @Resource
    public UserMapper userMapper;
    //判断名称重复的方法
    public User selectByName(String name){
        //固定写法
        UserExample example = new UserExample();
        //固定写法
        UserExample.Criteria criteria = example.createCriteria();
        //查询用户名
        criteria.andUsernameEqualTo(name);
        //返回查询的实体类
        List<User> userList = userMapper.selectByExample(example);
        //判断是否有数据
        if(CollectionUtils.isEmpty(userList)){
            return null;
        }else{
            return userList.get(0);
        }
    }
    //查询数据
    public PageResp<UserResp> list(UserReq userReq) {
        //固定写法
        UserExample example = new UserExample();
        //固定写法
        UserExample.Criteria criteria = example.createCriteria();
        if (!ObjectUtils.isEmpty(userReq.getUsername())) {
            criteria.andUsernameLike("%" + userReq.getUsername() + "%");
        }
        if (!ObjectUtils.isEmpty(userReq.getName())) {
            criteria.andNameLike("%" + userReq.getName() + "%");
        }
        if (!ObjectUtils.isEmpty(userReq.getAvatar())) {
            criteria.andAvatarLike("%" + userReq.getAvatar() + "%");
        }
        if (!ObjectUtils.isEmpty(userReq.getRole())) {
            criteria.andRoleLike("%" + userReq.getRole() + "%");
        }
        if (!ObjectUtils.isEmpty(userReq.getPhone())) {
            criteria.andPhoneLike("%" + userReq.getPhone() + "%");
        }
        if (!ObjectUtils.isEmpty(userReq.getEmail())) {
            criteria.andEmailLike("%" + userReq.getEmail() + "%");
        }
        if (!ObjectUtils.isEmpty(userReq.getCreatetime())) {
            criteria.andCreatetimeGreaterThanOrEqualTo(userReq.getCreatetime());
        }
        if (!ObjectUtils.isEmpty(userReq.getUpdatetime())) {
            criteria.andUpdatetimeGreaterThanOrEqualTo(userReq.getUpdatetime());
        }

        //分页(获取从页面传来的数据)
        PageHelper.startPage(userReq.getPage(), userReq.getSize());
        //类接收返回的数据
        List<User> userList = userMapper.selectByExample(example);
        //将返回的数据进行封装,某些信息是不需要返回的
        List<UserResp> data = CopyUtil.copyList(userList, UserResp.class);
        //定义分页获取总数
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        //定义分页
        PageResp<UserResp> pageResp = new PageResp<>();
        //将分页的数据进行总和
        pageResp.setTotal(pageInfo.getTotal());
        //将分页的数据加入类
        pageResp.setList(data);
        return pageResp;
    }
    //增加数据
    public void save(UserSaveReq userSaveReq) {
        User user = CopyUtil.copy(userSaveReq, User.class);
        //判断企业名是否存在
        User usernames = selectByName(userSaveReq.getUsername());
        if(!ObjectUtils.isEmpty(usernames)){
            //判断账号是否存在
            throw new BusinessException(BusinessExceptionCode.USER__NAME_EXIST);
        }else{
            //增加数据
            if (ObjectUtils.isEmpty(userSaveReq.getId())) {
                userMapper.insertSelective(user);
            } else {
                //更新数据
                user.setUpdatetime(new Date());
                userMapper.updateByPrimaryKeySelective(user);
            }
        }
    }
    public void delete(Integer id) {
        //删除数据
        userMapper.deleteByPrimaryKey(id);
    }

    public UserLoadingResp loading(UserLoadingReq userLoadingReq) {
        User user = selectByName(userLoadingReq.getUsername());
        if(!ObjectUtils.isEmpty(user)){
            //判断用户名是否一样
            if(user.getPassword().equals(userLoadingReq.getPassword())){
                //登录成功
                UserLoadingResp userLoadingResp = CopyUtil.copy(user,UserLoadingResp.class);
                userLoadingResp.setUserid(Long.valueOf(user.getId()));
                LOG.info("登录成功");
                return userLoadingResp;
            }else{
                //账号或者密码错误
                throw new BusinessException(BusinessExceptionCode.LOGIN_USER_ERROR);
            }
        }else{
            throw new BusinessException(BusinessExceptionCode.LOGIN_USER_ERROR);
            //
        }
    }

    //修改密码
    public void updatepassword(UserLoadingReq userLoadingReq) {
        //通过用户名查询数据
        User user = selectByName(userLoadingReq.getUsername());
        //修改密码
        user.setPassword(userLoadingReq.getPassword());
        //更新数据库的密码
        userMapper.updateByPrimaryKeySelective(user);
    }
}
