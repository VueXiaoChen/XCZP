package com.example.xblog.service;

import com.example.xblog.domain.ArticleLabels;
import com.example.xblog.domain.ArticleLabelsExample;
import com.example.xblog.mapper.ArticleLabelsMapper;
import com.example.xblog.req.ArticleLabelsReq;
import com.example.xblog.req.PageResp;
import com.example.xblog.resp.ArticleLabelsResp;
import com.example.xblog.util.CopyUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class ArticleLabelsService {
    private static final Logger LOG = (Logger) LoggerFactory.getLogger(ArticleLabelsService.class);

    @Resource
    public ArticleLabelsMapper articleLabelsMapper;

    public PageResp<ArticleLabelsResp> list(ArticleLabelsReq articleLabelsReq) {
        //固定写法
        ArticleLabelsExample example = new ArticleLabelsExample();
        //固定写法
        ArticleLabelsExample.Criteria criteria = example.createCriteria();
        //分页(获取从页面传来的数据)
        PageHelper.startPage(articleLabelsReq.getPage(), articleLabelsReq.getSize());
        //类接收返回的数据
        List<ArticleLabels> articleLabelsList = articleLabelsMapper.selectByExample(example);
        //将返回的数据进行封装,某些信息是不需要返回的
        List<ArticleLabelsResp> data = CopyUtil.copyList(articleLabelsList, ArticleLabelsResp.class);
        //定义分页获取总数
        PageInfo<ArticleLabels> pageInfo = new PageInfo<>(articleLabelsList);
        //定义分页
        PageResp<ArticleLabelsResp> pageResp = new PageResp<>();
        //将分页的数据进行总和
        pageResp.setTotal(pageInfo.getTotal());
        //将分页的数据加入类
        pageResp.setList(data);
        return pageResp;
    }

    //增加修改数据
    public void save(ArticleLabelsReq articleLabelsReq) {
        ArticleLabels articleLabels = CopyUtil.copy(articleLabelsReq, ArticleLabels.class);
        //增加数据
        if (ObjectUtils.isEmpty(articleLabelsReq.getArticleId())) {
            articleLabelsMapper.insertSelective(articleLabels);
        } else {
            //更新数据
            articleLabelsMapper.updateByPrimaryKeySelective(articleLabels);
        }
    }

    //删除数据
    public void delete(long id) {
        //删除数据
        articleLabelsMapper.deleteByPrimaryKey(id);
    }

}
