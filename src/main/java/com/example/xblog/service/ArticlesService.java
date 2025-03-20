package com.example.xblog.service;

import com.example.xblog.domain.Articles;
import com.example.xblog.domain.ArticlesExample;
import com.example.xblog.mapper.ArticlesMapper;
import com.example.xblog.req.ArticlesReq;
import com.example.xblog.req.PageResp;
import com.example.xblog.resp.ArticlesResp;
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
public class ArticlesService {
    private static final Logger LOG = (Logger) LoggerFactory.getLogger(ArticlesService.class);

    @Resource
    public ArticlesMapper articlesMapper;

    public PageResp<ArticlesResp> list(ArticlesReq articlesReq) {
        //固定写法
        ArticlesExample example = new ArticlesExample();
        //固定写法
        ArticlesExample.Criteria criteria = example.createCriteria();
        if (!ObjectUtils.isEmpty(articlesReq.getArticleTitle())) {
            criteria.andArticleTitleLike("%" + articlesReq.getArticleTitle() + "%");
        }
        if (!ObjectUtils.isEmpty(articlesReq.getArticleViews())) {
            criteria.andArticleViewsEqualTo(articlesReq.getArticleViews());
        }
        if (!ObjectUtils.isEmpty(articlesReq.getArticleLikeCount())) {
            criteria.andArticleLikeCountEqualTo(articlesReq.getArticleLikeCount());
        }
        if (!ObjectUtils.isEmpty(articlesReq.getArticleCommentCount())) {
            criteria.andArticleCommentCountEqualTo(articlesReq.getArticleCommentCount());
        }
        if (!ObjectUtils.isEmpty(articlesReq.getArticleDate())) {
            criteria.andArticleDateGreaterThanOrEqualTo(articlesReq.getArticleDate());
        }
        if (!ObjectUtils.isEmpty(articlesReq.getArticleUpdatedate())) {
            criteria.andArticleUpdatedateGreaterThanOrEqualTo(articlesReq.getArticleUpdatedate());
        }
        //分页(获取从页面传来的数据)
        PageHelper.startPage(articlesReq.getPage(), articlesReq.getSize());
        //类接收返回的数据
        List<Articles> articlesList = articlesMapper.selectByExampleWithBLOBs(example);
        //将返回的数据进行封装,某些信息是不需要返回的
        List<ArticlesResp> data = CopyUtil.copyList(articlesList, ArticlesResp.class);
        //定义分页获取总数
        PageInfo<Articles> pageInfo = new PageInfo<>(articlesList);
        //定义分页
        PageResp<ArticlesResp> pageResp = new PageResp<>();
        //将分页的数据进行总和
        pageResp.setTotal(pageInfo.getTotal());
        //将分页的数据加入类
        pageResp.setList(data);
        return pageResp;
    }

    //增加修改数据
    public void save(ArticlesReq articlesReq) {
        Articles articles = CopyUtil.copy(articlesReq, Articles.class);
        //增加数据
        if (ObjectUtils.isEmpty(articlesReq.getArticleId())) {
            articles.setArticleDate(new Date());
            articlesMapper.insertSelective(articles);
        } else {
            //更新数据
            articles.setArticleUpdatedate(new Date());
            articlesMapper.updateByPrimaryKeySelective(articles);
        }
    }

    //删除数据
    public void delete(long id) {
        //删除数据
        articlesMapper.deleteByPrimaryKey(id);
    }

}
