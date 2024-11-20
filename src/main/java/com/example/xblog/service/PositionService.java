package com.example.xblog.service;

import com.example.xblog.domain.*;
import com.example.xblog.exception.BusinessException;
import com.example.xblog.exception.BusinessExceptionCode;
import com.example.xblog.mapper.EmployMapper;
import com.example.xblog.mapper.PositionMapper;
import com.example.xblog.req.*;
import com.example.xblog.resp.EmployResp;
import com.example.xblog.resp.PositionResp;
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
public class PositionService {
    private static final Logger LOG = (Logger) LoggerFactory.getLogger(PositionService.class);

    @Resource
    public PositionMapper positionMapper;


    //判断名称重复的方法
    public Position selectByName(String name){
        //固定写法
        PositionExample example = new PositionExample();
        //固定写法
        PositionExample.Criteria criteria = example.createCriteria();
        //查询用户名
        criteria.andNameEqualTo(name);
        //返回查询的实体类
        List<Position> positionList = positionMapper.selectByExample(example);
        //判断是否有数据
        if(CollectionUtils.isEmpty(positionList)){
            return null;
        }else{
            return positionList.get(0);
        }
    }
    //查询数据
    public PageResp<PositionResp> list(PositionReq positionReq) {
        //固定写法
        PositionExample example = new PositionExample();
        //固定写法
        PositionExample.Criteria criteria = example.createCriteria();
        if (!ObjectUtils.isEmpty(positionReq.getName())) {
            criteria.andNameLike("%" + positionReq.getName() + "%");
        }
        if (!ObjectUtils.isEmpty(positionReq.getEmployId())) {
            criteria.andEmployIdEqualTo(positionReq.getEmployId());
        }
        if (!ObjectUtils.isEmpty(positionReq.getIndustryId())) {
            criteria.andIndustryIdEqualTo(positionReq.getIndustryId());
        }
        if (!ObjectUtils.isEmpty(positionReq.getType())) {
            criteria.andTypeLike("%" + positionReq.getType() + "%");
        }
        if (!ObjectUtils.isEmpty(positionReq.getExperience())) {
            criteria.andExperienceLike("%" + positionReq.getExperience() + "%");
        }
        if (!ObjectUtils.isEmpty(positionReq.getSalary())) {
            criteria.andSalaryLike("%" + positionReq.getSalary() + "%");
        }
        if (!ObjectUtils.isEmpty(positionReq.getEducation())) {
            criteria.andEducationLike("%" + positionReq.getEducation() + "%");
        }
        if (!ObjectUtils.isEmpty(positionReq.getTags())) {
            criteria.andTagsLike("%" + positionReq.getTags() + "%");
        }
        if (!ObjectUtils.isEmpty(positionReq.getStatus())) {
            criteria.andStatusLike("%" + positionReq.getStatus() + "%");
        }
        if (!ObjectUtils.isEmpty(positionReq.getCreatetime())) {
            criteria.andCreatetimeGreaterThanOrEqualTo(positionReq.getCreatetime());
        }
        if (!ObjectUtils.isEmpty(positionReq.getUpdatetime())) {
            criteria.andUpdatetimeGreaterThanOrEqualTo(positionReq.getUpdatetime());
        }
        //分页(获取从页面传来的数据)
        PageHelper.startPage(positionReq.getPage(), positionReq.getSize());
        //类接收返回的数据
        List<Position> positionList = positionMapper.selectByExampleWithBLOBs(example);
        //将返回的数据进行封装,某些信息是不需要返回的
        List<PositionResp> data = CopyUtil.copyList(positionList, PositionResp.class);
        //定义分页获取总数
        PageInfo<Position> pageInfo = new PageInfo<>(positionList);
        //定义分页
        PageResp<PositionResp> pageResp = new PageResp<>();
        //将分页的数据进行总和
        pageResp.setTotal(pageInfo.getTotal());
        //将分页的数据加入类
        pageResp.setList(data);
        return pageResp;
    }
    //增加数据
    public void save(PositionSaveReq positionSaveReq) {
        Position position = CopyUtil.copy(positionSaveReq, Position.class);
        //判断企业名是否存在
        Position position1 = selectByName(positionSaveReq.getName());
        if(!ObjectUtils.isEmpty(position1)){
            //判断企业名是否存在
            throw new BusinessException(BusinessExceptionCode.USER_POSITION_NAME_EXIST);
        }else{
            //增加数据
            if (ObjectUtils.isEmpty(positionSaveReq.getId())) {
                positionMapper.insertSelective(position);
            } else {
                //更新数据
                position.setUpdatetime(new Date());
                positionMapper.updateByPrimaryKeySelective(position);
            }
        }
    }
    public void delete(Integer id) {
        //删除数据
        positionMapper.deleteByPrimaryKey(id);
    }

}
