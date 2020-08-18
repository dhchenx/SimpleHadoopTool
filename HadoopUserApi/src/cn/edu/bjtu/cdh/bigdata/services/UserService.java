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


@Path("/user")
public class UserService {
	@Context
	ServletContext servletContext;
	@Context
	HttpServletRequest request;
	
	
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String login(@FormParam("appId") String appId, @FormParam("userId") String userId,@FormParam("userPwd") String userPwd) {
		try {
		String root = this.servletContext.getInitParameter("sys_root");
		HadoopAppUser hadoopAppUser = new HadoopAppUser(root, appId, userId);

		if (hadoopAppUser.login(userPwd)) {
			return "1";
		}else {
			return "0";
		}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return "-1";
	 
	}
	
	
	@DELETE
	@Path("/delete")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteUser(@FormParam("appId") String appId, @FormParam("userID") String userId) {
		try {
		

		String root = this.servletContext.getInitParameter("sys_root");
		HadoopAppUser hadoopAppUser = new HadoopAppUser(root, appId, userId);

		if (hadoopAppUser.deleteUser(appId, userId)) {
			return "1";
		}else {
			return "0";
		}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return "-1";
	 
	}
	
	
	@GET
	@Path("/list")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String getUserList(@QueryParam("appId") String appId) {
	 

		String root = this.servletContext.getInitParameter("sys_root");
		HadoopAppUser hadoopAppUser = new HadoopAppUser(root, appId,"");

		List<String> list=hadoopAppUser.getUserList(appId);
		
		return new Gson().toJson(list);

	}
	
	
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String addUser(@FormParam("appId") String appId, @FormParam("userId") String userId,@FormParam("userName") String userName,@FormParam("userPwd") String userPwd) {
		try {
		

		String root = this.servletContext.getInitParameter("sys_root");
		HadoopAppUser hadoopAppUser = new HadoopAppUser(root, appId, userId);

		if (hadoopAppUser.addUser(userId, userName, userPwd)) {
			return "1";
		}else {
			return "0";
		}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return "-1";
	 
	}
	
	
	@POST
	@Path("/info")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String getInfo(@FormParam("appId") String appId, @FormParam("userId") String userId) {
		
		String root = this.servletContext.getInitParameter("sys_root");
		HadoopAppUser hadoopAppUser = new HadoopAppUser(root, appId, userId);

		String ret=hadoopAppUser.getUserInfo(appId, userId);

		return ret;
	}
	
}