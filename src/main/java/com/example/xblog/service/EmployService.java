package com.example.xblog.service;

import com.example.xblog.domain.Employ;
import com.example.xblog.domain.EmployExample;
import com.example.xblog.domain.Industry;
import com.example.xblog.domain.IndustryExample;
import com.example.xblog.exception.BusinessException;
import com.example.xblog.exception.BusinessExceptionCode;
import com.example.xblog.mapper.EmployMapper;
import com.example.xblog.mapper.IndustryMapper;
import com.example.xblog.req.EmployReq;
import com.example.xblog.req.EmploySaveReq;
import com.example.xblog.req.IndustryReq;
import com.example.xblog.req.PageResp;
import com.example.xblog.resp.EmployResp;
import com.example.xblog.resp.IndustryResp;
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
public class EmployService {
    private static final Logger LOG = (Logger) LoggerFactory.getLogger(EmployService.class);

    @Resource
    public EmployMapper employMapper;
    //判断名称重复的方法
    public Employ selectByName(String name){
        //固定写法
        EmployExample example = new EmployExample();
        //固定写法
        EmployExample.Criteria criteria = example.createCriteria();
        //查询用户名
        criteria.andUsernameEqualTo(name);
        //返回查询的实体类
        List<Employ> employList = employMapper.selectByExample(example);
        //判断是否有数据
        if(CollectionUtils.isEmpty(employList)){
            return null;
        }else{
            return employList.get(0);
        }
    }
    //查询数据
    public PageResp<EmployResp> list(EmployReq employReq) {
        //固定写法
        EmployExample example = new EmployExample();
        //固定写法
        EmployExample.Criteria criteria = example.createCriteria();
        if (!ObjectUtils.isEmpty(employReq.getCity())) {
            criteria.andCityLike("%" + employReq.getCity() + "%");
        }
        if (!ObjectUtils.isEmpty(employReq.getUsername())) {
            criteria.andUsernameLike("%" + employReq.getUsername() + "%");
        }
        if (!ObjectUtils.isEmpty(employReq.getName())) {
            criteria.andNameLike("%" + employReq.getName() + "%");
        }
        if (!ObjectUtils.isEmpty(employReq.getAvatar())) {
            criteria.andAvatarLike("%" + employReq.getAvatar() + "%");
        }
        if (!ObjectUtils.isEmpty(employReq.getAddress())) {
            criteria.andAddressLike("%" + employReq.getAddress() + "%");
        }
        if (!ObjectUtils.isEmpty(employReq.getRole())) {
            criteria.andRoleLike("%" + employReq.getRole() + "%");
        }
        if (!ObjectUtils.isEmpty(employReq.getScale())) {
            criteria.andScaleLike("%" + employReq.getScale() + "%");
        }
        if (!ObjectUtils.isEmpty(employReq.getStage())) {
            criteria.andStageLike("%" + employReq.getStage() + "%");
        }
        if (!ObjectUtils.isEmpty(employReq.getStatus())) {
            criteria.andStatusLike("%" + employReq.getStatus() + "%");
        }
        //分页(获取从页面传来的数据)
        PageHelper.startPage(employReq.getPage(), employReq.getSize());
        //类接收返回的数据
        List<Employ> employList = employMapper.selectByExample(example);
        //将返回的数据进行封装,某些信息是不需要返回的
        List<EmployResp> data = CopyUtil.copyList(employList, EmployResp.class);
        //定义分页获取总数
        PageInfo<Employ> pageInfo = new PageInfo<>(employList);
        //定义分页
        PageResp<EmployResp> pageResp = new PageResp<>();
        //将分页的数据进行总和
        pageResp.setTotal(pageInfo.getTotal());
        //将分页的数据加入类
        pageResp.setList(data);
        return pageResp;
    }
    //增加数据
    public void save(EmploySaveReq employSaveReq) {
        Employ employ = CopyUtil.copy(employSaveReq, Employ.class);
        //判断企业名是否存在
        Employ employname = selectByName(employSaveReq.getUsername());
        if(!ObjectUtils.isEmpty(employname)){
            //判断企业名是否存在
            throw new BusinessException(BusinessExceptionCode.USER_EMPLOY_NAME_EXIST);
        }else{
            //增加数据
            if (ObjectUtils.isEmpty(employSaveReq.getId())) {
                employMapper.insertSelective(employ);
            } else {
                //更新数据
                employMapper.updateByPrimaryKeySelective(employ);
            }
        }

    }
    //删除数据
    public void delete(Integer id) {
        //删除数据
        employMapper.deleteByPrimaryKey(id);
    }

    //批量删除
    public void deleteBatch(List<Integer> ids) {
        employMapper.deleteByIds(ids);
    }
}
