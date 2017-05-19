package com.rush.chat.tools;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import java.io.*;
import java.util.Random;


public class UpLoadUtils {

    private static Logger logger = LoggerFactory.getLogger(UpLoadUtils.class);

    /**
     *
     * @param typeName 1级目录
     * @param ext 文件类型
     * @return
     * ,String brandName
     * , brandName
     */
	public static String getFileName(MultipartFile upFile,String path,String typeName,String ext) throws IOException{
		//String fileName = imgFile.getOriginalFilename();
		//随机数加当前日期
        String  fileName = getRandom()+ DateTimeUtils.getCurrentDateTime();
        if (ext != null)    {
            fileName +="."+ext;
        }
		File file = creatFolder(path ,typeName, fileName);
		try {
			// 保存上传的文件
            upFile.transferTo(file);
		} catch (IllegalStateException e) {
            logger.error("upFile error",e);
            throw e;
		}
		return file.getName();
    }

    public static String upVoice(InputStream in ,String path ,String ext)throws IOException{
        String  fileName = getRandom()+ DateTimeUtils.getCurrentDateTime()+"."+ext;
        int len = 0;
        File file = new File(path+"/"+fileName);
        FileOutputStream fos = new FileOutputStream(file);
        try {
            while((len=in.read())!=-1){//当没有读取完时，继续读取
                fos.write(len);
            }
        } catch (IOException e) {
            throw e;
        }finally {
            in.close();
            fos.close();
        }
        return fileName;
    }

    /**
     * ,String brandName
     * @param typeName 1级目录
     * @param fileName 文件名
     * @return
     */
	private static File creatFolder(String path,String typeName,String fileName) {
        File file = null;  
        //一级文件夹
        File firstFolder = new File(path + typeName);
      //如果一级文件夹存在，则检测二级文件夹
        if(firstFolder.exists()) {
            file = new File(firstFolder,fileName);
        }else {
            firstFolder.mkdir();  
            file = new File(firstFolder,fileName);
        }
        return file;  
   }
	
	private static String getRandom(){
		int max = 99999;
		int min = 1;
		Random random = new Random();
		int s = random.nextInt(max)%(max-min+1) + min;
		return String.valueOf(s);
	}

    //使用nio流读写文件
    public static void download(String inFile, ServletOutputStream fos)
            throws FileNotFoundException, IOException {
        //获取源文件和目标文件的输入输出流
        FileInputStream fin = new FileInputStream(inFile);
        try{
            int len = 0;
            byte[] buffer = new byte[1024];
            while((len = fin.read(buffer)) > 0){
                //从输入通道中将数据读入到缓冲区
                fos.write(buffer,0,len);
            }
        } finally {
            if(fin != null) fin.close();
            if(fos != null) fos.close();
        }
    }


}
