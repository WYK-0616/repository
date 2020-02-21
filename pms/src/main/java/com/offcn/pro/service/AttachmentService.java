package com.offcn.pro.service;

import com.offcn.pro.bean.Attachment;

import java.util.List;

public interface AttachmentService {

    boolean addAttachment(Attachment attachment);

    List<Attachment> getList();

    boolean deleteAttachment(Integer[] ids);

    boolean deleteById(Integer id);

    Attachment getAttachmentById(Integer id);

    boolean updateAttachment(Attachment attachment);
}
