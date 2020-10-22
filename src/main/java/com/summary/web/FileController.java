package com.summary.web;


import com.google.gson.Gson;
import com.summary.util.JSONUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 文件的管理的功能
 */
@MultipartConfig
public class FileController extends HttpServlet {

    @Override

        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
        {

            String uri = request.getRequestURI();
            switch (uri) {
                //上传图片
                case "/file/download":
                    doDownload(request, response);
                    break;
                default:
                    doNULL(request,response);
                    break;
            }
        }


    @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String uri = request.getRequestURI();
            switch (uri) {
                //上传图片
                case "/file/img":
                    imgsFile(request, response);
                    break;
            case "/file/answer":
                answerFile(request, response);
                break;
             default:
                    doNULL(request,response);
                    break;
            }

        }

        private void imgsFile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            Part part = request.getPart("img");
            //获取上传的文件名扩展名
            String disposition = part.getSubmittedFileName();
            String suffix = disposition.substring(disposition.lastIndexOf("."));
            //随机的生成uuid
            String filename = UUID.randomUUID()+suffix;
            String serverpath = request.getServletContext().getRealPath("imgs");
            //不存在文件夹则新建一个
            File fileDisk = new File(serverpath);
            if (!fileDisk.exists()){
                fileDisk.mkdir();
            }
            String fileparts = serverpath + "/" + filename;
            part.write(fileparts);
            String projectServerPath = request.getScheme()+"://"+request.getServerName()+":"
                    +request.getServerPort()+request.getContextPath()+"/imgs/"+filename;
            PrintWriter out = response.getWriter();
            Gson gson = new Gson();
            Map map = new HashMap();
            map.put("code" , 1 );
            map.put("Image",projectServerPath);
            String jsonString = gson.toJson(map);
            out.print(jsonString);
            out.flush();
        }


    /**
     * 不存在这个
     * @param request
     * @param response
     * @throws IOException
     */
    private void doNULL(HttpServletRequest request, HttpServletResponse response)throws IOException {

        String jsonString = JSONUtil.getJson(request);
        Gson gson = new Gson();
        Map map = new HashMap();
        map.put("code",6);
        map.put("msg","该页面不存在");

        jsonString = gson.toJson(map);
        PrintWriter out = response.getWriter();
        out.print(jsonString);
        out.flush();

    }

    private void answerFile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part part = request.getPart("file");
        //获取上传的文件名扩展名
        String disposition = part.getSubmittedFileName();
        String suffix = disposition.substring(disposition.lastIndexOf("."));
        //随机的生成uuid
        String filename = UUID.randomUUID()+suffix;
        String serverpath = request.getServletContext().getRealPath("upload");
        //不存在文件夹则新建一个
        File fileDisk = new File(serverpath);
        if (!fileDisk.exists()){
            fileDisk.mkdir();
        }
        String fileparts = serverpath + "/" + filename;
        part.write(fileparts);
        String projectServerPath = request.getScheme()+"://"+request.getServerName()+":"
                +request.getServerPort()+request.getContextPath()+"/upload/"+filename;
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        Map map = new HashMap();
        map.put("code" , 0 );
        map.put("answerFile",projectServerPath);
        String jsonString = gson.toJson(map);
        out.print(jsonString);
        out.flush();
    }

    private void doDownload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        //获取文件名
        String filename = request.getParameter("filename");
        //文件所在的文件夹
        String folder="/upload/";
        //通知浏览器以下载的方式打开
        response.addHeader("Content-Type","application/octet-stream");
        response.addHeader("Content-Disposition","attachment;filename="+filename);
        //通过文件输入流读取文件
        InputStream in=getServletContext().getResourceAsStream(folder+filename);
        OutputStream out=response.getOutputStream();
        byte[] bytes=new byte[1024];
        int len=0;
        while ((len=in.read(bytes))!=-1){
            out.write(bytes,0,len);
        }
    }

}
