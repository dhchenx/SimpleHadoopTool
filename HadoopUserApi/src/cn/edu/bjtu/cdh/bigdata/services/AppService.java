package cn.edu.bjtu.cdh.bigdata.services;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import cn.edu.bjtu.bigdata.cdh.hadooptask.HadoopEnv;
import cn.edu.bjtu.bigdata.cdh.hadoopuser.HadoopAppUser;
import cn.edu.bjtu.cdh.bigdata.models.NewAppInfo;

@Path("/app")
public class AppService {
	@Context
	ServletContext servletContext;
	@Context
	HttpServletRequest request;

	@POST
	@Path("/info")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String getInfo(@FormParam("appId") String appId) {
		
		String root = this.servletContext.getInitParameter("sys_root");
		HadoopAppUser hadoopAppUser = new HadoopAppUser(root, appId, "");

		String ret=hadoopAppUser.getAppInfo(appId);

		return ret;
	}
	
	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String createNewApp(NewAppInfo info) {

		try {
			String root = this.servletContext.getInitParameter("sys_root");
			HadoopEnv he = new HadoopEnv();
			he.setMasterHost(info.getMasterHost());
			he.setMasterPassword(info.getMasterPassword());
			he.setMasterPort(Integer.parseInt(info.getMasterPort()));
			he.setMasterUser(info.getMasterUser());
			he.setHadoopBin(info.getHadoopBin());
			he.setAppRoot(info.getAppRoot());

			HadoopAppUser.createAppIfNotExists(root, he, info.getAppId(), info.getAppId(), info.getUserId(),
					info.getUserPwd());

			return "1";
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "0";

	}
}
