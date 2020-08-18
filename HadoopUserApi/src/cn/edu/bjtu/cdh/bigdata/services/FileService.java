package cn.edu.bjtu.cdh.bigdata.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

import cn.edu.bjtu.bigdata.cdh.hadoopuser.HadoopAppUser;
import cn.edu.bjtu.cdh.bigdata.hdfs.UploadFile;
import cn.edu.bjtu.cdh.bigdata.utils.Base64;


@Path("hdfs")
public class FileService {
	@Context
	ServletContext servletContext;
	@Context
	HttpServletRequest request;
	
	@POST
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response addLibrary(
			@FormDataParam("appId") String appId, 
			@FormDataParam("userId") String userId,
	@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail,
			@FormDataParam("hdfsPath") String hdfsPath
			) {
		
		try {
		
		String root = this.servletContext.getInitParameter("sys_root");	
	
		 
		 hdfsPath=Base64.decode(hdfsPath);
		 
		
		String upload_folder = root + "/hdfs/"+appId+"/"+userId;
		if(!new File(upload_folder).exists())
			new File(upload_folder).mkdirs();
		
		String upload_path=upload_folder+hdfsPath.substring(hdfsPath.lastIndexOf(":")+5);
		
		String ufolder=upload_path.substring(0,upload_path.lastIndexOf("/"));
		
		if(!new File(ufolder).exists()) {
			new File(ufolder).mkdirs();
		}  
		
		writeToFile(uploadedInputStream, upload_path);
		
		String hdfs_root=hdfsPath.substring(0, hdfsPath.lastIndexOf(":")+5);
		String hdfs_rel_path=hdfsPath.substring(hdfsPath.lastIndexOf(":")+5);
		
		System.out.println("hdfs_root="+hdfs_root);
		System.out.println("upload_path="+upload_path);
		System.out.println("hdfs_path="+hdfs_rel_path);
		
		//¸´ÖÆµ½HDFSÖÐ
	    UploadFile.copyFileToHDFS(hdfs_root,upload_path, hdfs_rel_path);
 
		
		return Response.ok("ok").build();
		
		}catch(Exception ex) {
			ex.printStackTrace();
		}

		return Response.ok("failed").build();
		
	}
	
	
	// save uploaded file to new location
	private void writeToFile(InputStream uploadedInputStream, String uploadedFileLocation) {

		try {
			OutputStream out = new FileOutputStream(new File(uploadedFileLocation));
			int read = 0;
			byte[] bytes = new byte[1024];

			out = new FileOutputStream(new File(uploadedFileLocation));
			while ((read = uploadedInputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			out.flush();
			out.close();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}
}
