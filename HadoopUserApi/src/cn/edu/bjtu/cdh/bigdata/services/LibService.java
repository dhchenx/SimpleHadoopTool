package cn.edu.bjtu.cdh.bigdata.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.*;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

import cn.edu.bjtu.bigdata.cdh.hadoopuser.HadoopAppUser;
import cn.edu.bjtu.cdh.bigdata.utils.Base64;


@Path("/lib")
public class LibService {
	
	@Context
	ServletContext servletContext;
	@Context
	HttpServletRequest request;
	
	@DELETE
	@Path("/delete")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String delete(
			@FormParam("appId") String appId, 
			@FormParam("userId") String userId,
			@FormParam("libId") String libId
			) {
		 
		String root = this.servletContext.getInitParameter("sys_root");
		HadoopAppUser hadoopAppUser = new HadoopAppUser(root, appId, userId);


		hadoopAppUser.deleteLibrary(libId);
		
		return "-1";
	 
	}
	
	@GET
	@Path("/list")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String getAllLibrary(
			@QueryParam("appId") String appId, 
			@QueryParam("userId") String userId
			) {
		 
		String root = this.servletContext.getInitParameter("sys_root");
		HadoopAppUser hadoopAppUser = new HadoopAppUser(root, appId, userId);

		List<String> list=hadoopAppUser.getLibraryList();
		
		return new Gson().toJson(list);
	 
	}
	
	@POST
	@Path("/add")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response addLibrary(
			@FormDataParam("appId") String appId, 
			@FormDataParam("userId") String userId,
	@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail, @FormDataParam("libName") String libName
			, @FormDataParam("libDesc") String libDesc,@FormDataParam("jarName") String jarName) {
		
		String root = this.servletContext.getInitParameter("sys_root");	
		
		 HadoopAppUser hadoopAppUser = new HadoopAppUser(root, appId, userId);

		 libName=Base64.decode(libName);
		 jarName=Base64.decode(jarName);
		 libDesc=Base64.decode(libDesc);

		String libId=hadoopAppUser.addLibrary(libName, libDesc, jarName);
	
		if(libId==null)
			Response.ok("failed").build();
		
		String upload_folder = root + "/libs/"+appId+"/"+userId+"/" + libId;
		if(!new File(upload_folder).exists())
			new File(upload_folder).mkdirs();
		
		String upload_path=upload_folder+"/"+jarName;
		
		writeToFile(uploadedInputStream, upload_path);
		
		return Response.ok("ok").build();
		
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
