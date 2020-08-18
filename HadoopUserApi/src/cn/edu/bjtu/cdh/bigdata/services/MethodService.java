package cn.edu.bjtu.cdh.bigdata.services;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import cn.edu.bjtu.bigdata.cdh.hadoopuser.HadoopAppUser;

@Path("/method")
public class MethodService {
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
			@FormParam("libId") String libId,
			@FormParam("methodId") String methodId
			
			) {
		 
		String root = this.servletContext.getInitParameter("sys_root");
		HadoopAppUser hadoopAppUser = new HadoopAppUser(root, appId, userId);


		hadoopAppUser.deleteMethod(methodId);
		
		return "1";
	 
	}
	
	@GET
	@Path("/list")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String getAllMethods(
			@QueryParam("appId") String appId, 
			@QueryParam("userId") String userId,
			@QueryParam("libId") String libId
			) {
		 
		String root = this.servletContext.getInitParameter("sys_root");
		HadoopAppUser hadoopAppUser = new HadoopAppUser(root, appId, userId);

		List<String> list=hadoopAppUser.getMethodList(libId);
		
		return new Gson().toJson(list);
	 
	}
	
	
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String addMethod(@FormParam("appId") String appId, @FormParam("userId") String userId,
			@FormParam("libId") String libId,
			@FormParam("methodName") String methodName,
			@FormParam("mainClass") String mainClass,
			@FormParam("methodDesc") String methodDesc
			) {
		try {

		String root = this.servletContext.getInitParameter("sys_root");
		HadoopAppUser hadoopAppUser = new HadoopAppUser(root, appId, userId);

		String methodId=hadoopAppUser.addMethod(libId, methodName, mainClass, methodDesc);
		
		if (!methodId.isEmpty()) {
			return "1";
		}else {
			return "0";
		}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return "-1";
	 
	}
}
