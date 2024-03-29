package kroryi.pro15;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

@WebServlet("/download.do")
public class FileDownload extends HttpServlet {
    private static final String CHARSET = "UTF-8";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doHandle(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doHandle(request, response);
    }

    protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding(CHARSET);
        Charset charset = Charset.forName(CHARSET);
        ServletContext sc = request.getServletContext();
        Properties properties = new Properties();
        properties.load(new FileReader(sc.getRealPath(sc.getInitParameter("contextConfigLocation"))));
        Path currentDirPath = Paths.get((String) properties.get("upload.directory"));
        File dirFile = new File(String.valueOf(currentDirPath)); // 파일 목록 가져올거임
        File[] fileList = dirFile.listFiles(); // 파일 배열
        System.out.println(Arrays.toString(fileList));

        ArrayList<String> fileNames = new ArrayList<String>();
        for (File file : fileList) {
            if (file.isFile()) { // 파일 일 때
                String fileName = file.getName();
                fileNames.add(fileName);
                System.out.println("File Name : " + fileName);
            }
        }
        request.setAttribute("fileNames", fileNames);
        RequestDispatcher re = request.getRequestDispatcher("/DownList.jsp");
        re.forward(request, response);
    }
}

