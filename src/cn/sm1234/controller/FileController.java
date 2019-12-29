package cn.sm1234.controller;

import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import cn.sm1234.domain.Customer;



@Controller
@RequestMapping("/saveFileupload")
public class FileController{
	
	private String dirPath=null;
	List<String> list=new ArrayList<String>();
    
	
	@RequestMapping(value={"/uploadFile"})
	@ResponseBody
	public Map<String, Object> importPicFile1( @RequestParam("file") MultipartFile[] files,
	HttpServletRequest request,HttpServletResponse response)throws Exception {
		Map<String, Object> resultMapsReturn = new HashMap<>();
		dirPath=request.getServletContext().getRealPath("/WEB-INF/upload/");
		for (MultipartFile file : files){
            System.out.println("文件类型:"+file.getContentType());
            String filename = file.getOriginalFilename();
            String suffix = filename.substring(filename.length() - 3);
            System.out.println("文件名:"+filename);
            System.out.println("文件后缀:"+suffix);
            System.out.println("文件大小:"+file.getSize()/1024+"KB");
            //创建要保存文件的路径
            File dirFile = new File(dirPath,filename);
            
            resultMapsReturn.put(filename, filename);
            if (!dirFile.exists()){
                dirFile.mkdirs();
            }
            try {
                //将文件写入创建的路径
                file.transferTo(dirFile);
                System.out.println("文件保存成功~");
            } catch (IOException e) {
                e.printStackTrace();
            }
 
 
        }
		
        return resultMapsReturn;


		}
	
    
	@RequestMapping("/upload")
	@ResponseBody
	public Map<String, Object> upload(@RequestParam("dir") MultipartFile[] file,HttpServletRequest request){

		Map<String, Object> resultMapsReturn = new HashMap<>();
	    for(MultipartFile f:file){

	        File file1 ;
	        String name="";
	        try {
	            if (f instanceof CommonsMultipartFile) {
	                //转换成这个对象，然后我们需要通过里面的FileItem来获得相对路径
	                CommonsMultipartFile f2 = (CommonsMultipartFile) f;
	                name = f2.getFileItem().getName();
	                System.out.println(name + "        ---------相对路径");
	                dirPath=request.getServletContext().getRealPath("/WEB-INF/upload/");
	                file1 = new File(dirPath + "/" + name);
	                file1.mkdirs();
	                file1.createNewFile();
	                f.transferTo(file1);
	            }
	            System.out.println(f.getOriginalFilename() + "   iii         --------");
	            System.out.println("sssss   ");
	        }catch (Exception e){
	            e.printStackTrace();
	        }

	    }


	    return resultMapsReturn;
	}

	


@RequestMapping(value="downfile",method=RequestMethod.GET)
@ResponseBody
public void download(HttpServletRequest request,HttpServletResponse response,String filename) throws IOException {
	
	//解码，免得文件名中文乱码
	filename = URLDecoder.decode(filename,"UTF-8"); 
	//checkPay.apk为需要下载的文件
	//String filename = "checkPay.apk";   //我这里使用的是一个固定的文件，方法可以不用写filename参数
	//获取文件的绝对路径名称，apk为根目录下的一个文件夹，这个只能获取根目录文件夹的绝对路径

    String path = request.getSession().getServletContext().getRealPath("/WEB-INF/upload/")+"\\"+filename;
    
    System.out.println(path);
    
    //得到要下载的文件
    File file = new File(path);
    if (!file.exists()) {

        System.out.println("您要下载的资源已被删除！！");  
        return ;  
	}
	//转码，免得文件名中文乱码  
	filename = new String(filename.getBytes("gbk"),"iso8859-1");
    //设置文件下载头  
    response.addHeader("Content-Disposition", "attachment;filename=" + filename);    
    //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型    
    response.setContentType("multipart/form-data"); 
    // 读取要下载的文件，保存到文件输入流
    FileInputStream in = new FileInputStream(path);
    // 创建输出流
    OutputStream out = response.getOutputStream();
    // 创建缓冲区
    byte buffer[] = new byte[1024]; // 缓冲区的大小设置是个迷  我也没搞明白
    int len = 0;
    //循环将输入流中的内容读取到缓冲区当中
    while((len = in.read(buffer)) > 0){
    	out.write(buffer, 0, len);
    	 
    }
    //关闭文件输入流
    in.close();
    // 关闭输出流
    out.flush(); 
    out.close();
    
   
}
}



