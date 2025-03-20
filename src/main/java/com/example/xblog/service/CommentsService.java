package com.example.xblog.service;

import com.example.xblog.domain.Comments;
import com.example.xblog.domain.CommentsExample;
import com.example.xblog.mapper.CommentsMapper;
import com.example.xblog.req.CommentsReq;
import com.example.xblog.req.PageResp;
import com.example.xblog.resp.CommentsResp;
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
public class CommentsService {
    private static final Logger LOG = (Logger) LoggerFactory.getLogger(CommentsService.class);

    @Resource
    public CommentsMapper commentsMapper;

    public PageResp<CommentsResp> list(CommentsReq commentsReq) {
        //固定写法
        CommentsExample example = new CommentsExample();
        //固定写法
        CommentsExample.Criteria criteria = example.createCriteria();
        if (!ObjectUtils.isEmpty(commentsReq.getArticleId())) {
            criteria.andArticleIdEqualTo(commentsReq.getArticleId());
        }
        if (!ObjectUtils.isEmpty(commentsReq.getUserId())) {
            criteria.andUserIdEqualTo(commentsReq.getUserId());
        }
        //分页(获取从页面传来的数据)
        PageHelper.startPage(commentsReq.getPage(), commentsReq.getSize());
        //类接收返回的数据
        List<Comments> commentsList = commentsMapper.selectByExampleWithBLOBs(example);
        //将返回的数据进行封装,某些信息是不需要返回的
        List<CommentsResp> data = CopyUtil.copyList(commentsList, CommentsResp.class);
        //定义分页获取总数
        PageInfo<Comments> pageInfo = new PageInfo<>(commentsList);
        //定义分页
        PageResp<CommentsResp> pageResp = new PageResp<>();
        //将分页的数据进行总和
        pageResp.setTotal(pageInfo.getTotal());
        //将分页的数据加入类
        pageResp.setList(data);
        return pageResp;
    }

    //增加修改数据
    public void save(CommentsReq commentsReq) {
        Comments comments = CopyUtil.copy(commentsReq, Comments.class);
        //增加数据
        if (ObjectUtils.isEmpty(commentsReq.getCommentId())) {
            comments.setCommentDate(new Date());
            commentsMapper.insertSelective(comments);
        } else {
            //更新数据
            commentsMapper.updateByPrimaryKeySelective(comments);
        }
    }

    //删除数据
    public void delete(long id) {
        //删除数据
        commentsMapper.deleteByPrimaryKey(id);
    }

}
