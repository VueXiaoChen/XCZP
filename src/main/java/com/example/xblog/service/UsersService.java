package com.example.xblog.service;

import com.example.xblog.domain.Users;
import com.example.xblog.domain.UsersExample;
import com.example.xblog.exception.BusinessException;
import com.example.xblog.exception.BusinessExceptionCode;
import com.example.xblog.mapper.UsersMapper;
import com.example.xblog.req.PageResp;
import com.example.xblog.req.UsersLoadingReq;
import com.example.xblog.req.UsersReq;
import com.example.xblog.req.UsersSaveReq;
import com.example.xblog.resp.UsersLoadingResp;
import com.example.xblog.resp.UsersResp;
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
public class UsersService {
    private static final Logger LOG = (Logger) LoggerFactory.getLogger(UsersService.class);

    @Resource
    public UsersMapper usersMapper;

    @Resource
    public GlobalConfigService globalConfigService;
    //判断名称重复的方法
    public Users selectByName(String name){
        //固定写法
        UsersExample example = new UsersExample();
        //固定写法
        UsersExample.Criteria criteria = example.createCriteria();
        //查询用户名
        criteria.andUserNameEqualTo(name);
        //返回查询的实体类
        List<Users> userList = usersMapper.selectByExample(example);
        //判断是否有数据
        if(CollectionUtils.isEmpty(userList)){
            return null;
        }else{
            return userList.get(0);
        }
    }
    //查询数据
    public PageResp<UsersResp> list(UsersReq usersReq) {
        //固定写法
        UsersExample example = new UsersExample();
        //固定写法
        UsersExample.Criteria criteria = example.createCriteria();
        if (!ObjectUtils.isEmpty(usersReq.getUserName())) {
            criteria.andUserNameLike("%" + usersReq.getUserName() + "%");
        }
        
        if (!ObjectUtils.isEmpty(usersReq.getUserRegistrationTime())) {
            criteria.andUserRegistrationTimeEqualTo(usersReq.getUserRegistrationTime());
        }
        if (!ObjectUtils.isEmpty(usersReq.getUserBirthday())) {
            criteria.andUserBirthdayEqualTo(usersReq.getUserBirthday());
        }

        //分页(获取从页面传来的数据)
        PageHelper.startPage(usersReq.getPage(), usersReq.getSize());
        //类接收返回的数据
        List<Users> userList = usersMapper.selectByExample(example);
        //将返回的数据进行封装,某些信息是不需要返回的
        List<UsersResp> data = CopyUtil.copyList(userList, UsersResp.class);
        //定义分页获取总数
        PageInfo<Users> pageInfo = new PageInfo<>(userList);
        //定义分页
        PageResp<UsersResp> pageResp = new PageResp<>();
        //将分页的数据进行总和
        pageResp.setTotal(pageInfo.getTotal());
        //将分页的数据加入类
        pageResp.setList(data);
        return pageResp;
    }
    //增加数据
    public void save(UsersSaveReq usersSaveReq) {
        Users user = CopyUtil.copy(usersSaveReq, Users.class);
        //判断企业名是否存在
        Users usernames = selectByName(usersSaveReq.getUserName());
        if(!ObjectUtils.isEmpty(usernames)){
            //判断账号是否存在
            throw new BusinessException(BusinessExceptionCode.USER__NAME_EXIST);
        }else{
            //增加数据
            if (ObjectUtils.isEmpty(usersSaveReq.getUserId())) {
                usersMapper.insertSelective(user);
            } else {
                //更新数据
                usersMapper.updateByPrimaryKeySelective(user);
            }
        }
    }
    public void delete(Long id) {
        //删除数据
        usersMapper.deleteByPrimaryKey(id);
    }

    public UsersLoadingResp loading(UsersLoadingReq usersLoadingReq) {
        Users user = selectByName(usersLoadingReq.getUserName());
        if(!ObjectUtils.isEmpty(user)){
            //判断用户名是否一样
            if(user.getUserPassword().equals(usersLoadingReq.getUserPassword())){
                //登录成功
                UsersLoadingResp usersLoadingResp = CopyUtil.copy(user,UsersLoadingResp.class);
                usersLoadingResp.setUserid(Long.valueOf(user.getUserId()));
                usersLoadingResp.setUsername(user.getUserName());
                //全局变量储存用户的姓名
                globalConfigService.SaveName(user.getUserName());
                LOG.info("登录成功");
                return usersLoadingResp;
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
    public void updatepassword(UsersLoadingReq usersLoadingReq) {
        //通过用户名查询数据
        Users user = selectByName(usersLoadingReq.getUserName());
        //修改密码
        user.setUserPassword(usersLoadingReq.getUserPassword());
        //更新数据库的密码
        usersMapper.updateByPrimaryKeySelective(user);
    }
}
