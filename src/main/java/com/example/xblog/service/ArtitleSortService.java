package com.example.xblog.service;

import com.example.xblog.domain.ArtitleSort;
import com.example.xblog.domain.ArtitleSortExample;
import com.example.xblog.mapper.ArtitleSortMapper;
import com.example.xblog.req.ArtitleSortReq;
import com.example.xblog.req.PageResp;
import com.example.xblog.resp.ArtitleSortResp;
import com.example.xblog.util.CopyUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ArtitleSortService {
    private static final Logger LOG = (Logger) LoggerFactory.getLogger(ArtitleSortService.class);

    @Resource
    public ArtitleSortMapper artitleSortMapper;

    public PageResp<ArtitleSortResp> list(ArtitleSortReq artitleSortReq) {
        //固定写法
        ArtitleSortExample example = new ArtitleSortExample();
        //固定写法
        ArtitleSortExample.Criteria criteria = example.createCriteria();
        //分页(获取从页面传来的数据)
        PageHelper.startPage(artitleSortReq.getPage(), artitleSortReq.getSize());
        //类接收返回的数据
        List<ArtitleSort> artitleSortList = artitleSortMapper.selectByExample(example);
        //将返回的数据进行封装,某些信息是不需要返回的
        List<ArtitleSortResp> data = CopyUtil.copyList(artitleSortList, ArtitleSortResp.class);
        //定义分页获取总数
        PageInfo<ArtitleSort> pageInfo = new PageInfo<>(artitleSortList);
        //定义分页
        PageResp<ArtitleSortResp> pageResp = new PageResp<>();
        //将分页的数据进行总和
        pageResp.setTotal(pageInfo.getTotal());
        //将分页的数据加入类
        pageResp.setList(data);
        return pageResp;
    }

    //增加修改数据
    public void save(ArtitleSortReq artitleSortReq) {
        ArtitleSort artitleSort = CopyUtil.copy(artitleSortReq, ArtitleSort.class);
        //固定写法
        ArtitleSortExample example = new ArtitleSortExample();
        //固定写法
        ArtitleSortExample.Criteria criteria = example.createCriteria();
        //增加数据
        if (ObjectUtils.isEmpty(artitleSortReq.getArticleId())) {
            artitleSortMapper.insertSelective(artitleSort);
        } else {
            //更新数据
            artitleSortMapper.updateByExampleSelective(artitleSort,example);
        }
    }

    //删除数据
    public void delete(long articleId,long sortId) {
        //删除数据
        artitleSortMapper.deleteByPrimaryKey(articleId,sortId);
    }

}
