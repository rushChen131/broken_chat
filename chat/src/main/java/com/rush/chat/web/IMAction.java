package com.rush.chat.web;

import com.rush.chat.models.TRegister;
import com.rush.chat.models.UpLoadResult;
import com.rush.chat.models.common.RequestMessage;
import com.rush.chat.models.common.ResponseMessage;
import com.rush.chat.tools.VaildUtils;
import com.rush.chat.tools.GlobalUtils;
import com.rush.chat.tools.UpLoadUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Vector;

@Controller
@RequestMapping("/im")
public class IMAction {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public static final List<TRegister> boyList = new Vector<TRegister>();//未匹配的用户

    public static final List<TRegister> grilList = new Vector<TRegister>();//未匹配的用户

    /**
     * 登录
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.POST )
    @ResponseBody
    public ResponseMessage login(RequestMessage requestMessage)throws Exception {

    }


        /**
         * 文件上传
         * @return
         */
    @RequestMapping(value = "uploadFile", method = RequestMethod.POST )
    @ResponseBody
    public ResponseMessage uploadImg(@RequestParam MultipartFile files,HttpServletResponse response)throws Exception{
        if (files == null ){
            return ResponseMessage.paramErr();
        }
        logger.info("====================upLoadFile===="+files.getOriginalFilename());
        String name =  files.getOriginalFilename();
        String fileName = null;
        String homePath = GlobalUtils.getString("file_path");
        String filePath = null;
        String[] names = name.split("\\.");
        String filesType = names[names.length-1];
        if (filesType!= null){
            filesType = filesType.toLowerCase();
            if (VaildUtils.isImg(filesType)){
                filePath = GlobalUtils.getString("image_type_path");
            }else{
                filePath = GlobalUtils.getString("file_type_path");
            }
        }
        try {
            fileName = UpLoadUtils.getFileName(files, homePath, filePath, filesType);
        } catch (IOException e) {
            logger.error("上传文件失败",e);
            return ResponseMessage.error();
        }
        UpLoadResult result = new UpLoadResult();
        result.setNickName(name);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(GlobalUtils.getString("file_url")).append(filePath).append(fileName);
        result.setUrl(stringBuffer.toString());
        result.setFileName(fileName);
        logger.info("uploadFile result"+ response.getContentType()+"ruslt "+response.getHeaderNames().toString()+"rrr");
        return ResponseMessage.success(result);
    }

    /**
     * 文件下载功能
     * @return
     */
    @RequestMapping(value = "download", method = RequestMethod.GET)
    @ResponseBody
    public ResponseMessage download(HttpServletResponse response ,String nickName ,String fileName )throws Exception{
        if (fileName == null || "".equals(fileName) || nickName  == null|| "".equals(nickName) ){
            ResponseMessage.paramErr();
            return null;
        }
        String[] names = fileName.split("\\.");
        String filesType = names[names.length-1];
        String homePath = GlobalUtils.getString("file_path");
        String filePath = null;
        if ("img".equals(filesType)){
            filePath = GlobalUtils.getString("image_type_path");
        }else{
            filePath = GlobalUtils.getString("file_type_path");
        }
        response.setHeader("content-type", "application/octet-stream");
        response.setHeader("Content-Disposition","attachment; filename="+new String(nickName.getBytes("gb2312"),"ISO-8859-1"));
        try {
            UpLoadUtils.download(homePath+filePath+fileName, response.getOutputStream());
        } catch (Exception e) {
            logger.error("下载文件失败",e);
            return ResponseMessage.error();
        }
        return ResponseMessage.success();
    }

    /**
     * 语音上传功能
     * @return
     */
    @RequestMapping(value = "voice", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage voice(HttpServletRequest request,String ext)throws Exception{
     InputStream files = request.getInputStream();
        if (files == null ){
            return ResponseMessage.paramErr();
        }
        logger.info("====================upLoadVocie====");
        String homePath = GlobalUtils.getString("file_path");
        String filePath = GlobalUtils.getString("voice_type_path");
        String fileName = UpLoadUtils.upVoice(files,homePath+filePath,ext);
        UpLoadResult result = new UpLoadResult();
        result.setNickName(fileName);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(GlobalUtils.getString("file_url")).append(filePath).append(fileName);
        result.setUrl(stringBuffer.toString());
        result.setFileName(fileName);
        return ResponseMessage.success(result);
    }
}