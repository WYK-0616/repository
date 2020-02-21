package com.offcn.pro.service.impl;

import com.offcn.pro.bean.Attachment;
import com.offcn.pro.bean.AttachmentExample;
import com.offcn.pro.bean.Project;
import com.offcn.pro.dao.AttachmentMapper;
import com.offcn.pro.dao.ProjectMapper;
import com.offcn.pro.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
public class AttachmentServiceImpl implements AttachmentService {
    @Autowired
    private AttachmentMapper attachmentMapper;
    @Autowired
    private ProjectMapper projectMapper;

    public boolean addAttachment(Attachment attachment) {
        int insert = attachmentMapper.insert(attachment);
        return insert > 0;
    }

    public List<Attachment> getList() {
        AttachmentExample example = new AttachmentExample();
        List<Attachment> list = attachmentMapper.selectByExample(example);
        for (Attachment attachment : list) {
            Project project = projectMapper.selectByPrimaryKey(attachment.getProFk());
            attachment.setProject(project);
        }
        return list;
    }
    @Transactional
    public boolean deleteAttachment(Integer[] ids) {
        AttachmentExample example = new AttachmentExample();
        AttachmentExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(Arrays.asList(ids));
        int result = attachmentMapper.deleteByExample(example);
        return result == ids.length;
    }

    @Transactional
    public boolean deleteById(Integer id) {
        int i = attachmentMapper.deleteByPrimaryKey(id);
        return i > 0;
    }

    public Attachment getAttachmentById(Integer id) {
        Attachment attachment = attachmentMapper.selectByPrimaryKey(id);
        Project project = projectMapper.selectByPrimaryKey(attachment.getProFk());
        attachment.setProject(project);
        return attachment;
    }

    public boolean updateAttachment(Attachment attachment) {
        int i = attachmentMapper.updateByPrimaryKeySelective(attachment);
        return i > 0;
    }
}
