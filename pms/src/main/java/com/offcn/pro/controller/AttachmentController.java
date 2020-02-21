package com.offcn.pro.controller;

import com.offcn.pro.bean.Attachment;
import com.offcn.pro.service.AttachmentService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/attachment")
public class AttachmentController {
    @Autowired
    private AttachmentService attachmentService;

    @RequestMapping(value = "/addAttachment",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> addAttachment(Attachment attachment, MultipartFile file, HttpServletRequest request){
        Map<String,Object> map = new HashMap<String, Object>();
        try{
            String realPath = request.getSession().getServletContext().getRealPath("/");
            File discription = new File(realPath + "upload");
            if (!discription.exists()){
                discription.mkdirs();
            }
            String filename = UUID.randomUUID().toString().replaceAll("-","") + "_" + file.getOriginalFilename();
            attachment.setPath(filename);
            FileOutputStream fos = new FileOutputStream(new File(realPath+"/upload/"+filename));
            InputStream ins = file.getInputStream();
            IOUtils.copy(ins,fos);
            fos.close();
            ins.close();
            boolean flag = attachmentService.addAttachment(attachment);
            if (flag){
                map.put("statusCode",200);
            } else {
                map.put("statusCode",499);
            }
        } catch (Exception ex){
            ex.printStackTrace();
            map.put("statusCode",500);
        }
        return map;
    }
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public List<Attachment> getList(){
        List<Attachment> list = attachmentService.getList();
        return list;
    }

    @RequestMapping(value = "/deleteAttachment",method = RequestMethod.DELETE)
    @ResponseBody
    public Map<String,Object> deleteAttachment(@RequestParam("ids[]") Integer[] ids){
        Map<String,Object> map = new HashMap<String, Object>();
        boolean flag = attachmentService.deleteAttachment(ids);
        if (flag){
            map.put("statusCode",200);
        } else {
            map.put("statusCode",500);
        }
        return map;
    }
    @RequestMapping(value = "/deleteById/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public Map<String,Object> deleteById(@PathVariable("id") Integer id){
        Map<String,Object> map = new HashMap<String, Object>();
        boolean flag = attachmentService.deleteById(id);
        if (flag){
            map.put("statusCode",200);
        } else {
            map.put("statusCode",500);
        }
        return map;
    }

    @RequestMapping(value = "/getAttachmentById/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Attachment getAttachmentById(@PathVariable("id")Integer id){
        Attachment attachment = attachmentService.getAttachmentById(id);
        return attachment;
    }
    @RequestMapping(value = "/download",method = RequestMethod.GET)
    public ResponseEntity<byte[]> download(HttpServletRequest request, String path) throws Exception{
        String fileName=path;
        String realPath = request.getSession().getServletContext().getRealPath("/upload/");
        realPath = realPath + "/" + fileName;
        FileInputStream fis = new FileInputStream(new File(realPath));
        byte[] body = new byte[fis.available()];
        fis.read(body);
        MultiValueMap<String, String> headers = new HttpHeaders();
        fileName = new String(fileName.getBytes("gbk"),"iso8859-1");
        headers.add("Content-Disposition", "attachment;filename="+fileName);
        HttpStatus statusCode = HttpStatus.OK;
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<byte[]>(body, headers, statusCode);
        return responseEntity;
    }

    @RequestMapping(value = "/updateAttachment",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> updateAttachment(Attachment attachment, MultipartFile file, HttpServletRequest request){
        Map<String,Object> map = new HashMap<String, Object>();
        try{
            String realPath = request.getSession().getServletContext().getRealPath("/");
            File discription = new File(realPath + "upload");
            String filename = UUID.randomUUID().toString().replaceAll("-","") + "_" + file.getOriginalFilename();
            attachment.setPath(filename);
            FileOutputStream fos = new FileOutputStream(new File(realPath+"/upload/"+filename));
            InputStream ins = file.getInputStream();
            IOUtils.copy(ins,fos);
            fos.close();
            ins.close();
            boolean flag = attachmentService.updateAttachment(attachment);
            if (flag){
                map.put("statusCode",200);
            } else {
                map.put("statusCode",499);
            }
        } catch (Exception ex){
            ex.printStackTrace();
            map.put("statusCode",500);
        }
        return map;
    }

}
