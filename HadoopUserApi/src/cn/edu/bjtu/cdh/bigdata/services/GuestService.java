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
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.io.FileUtils;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

import cn.edu.bjtu.bigdata.cdh.hadooptask.HadoopEnv;
import cn.edu.bjtu.bigdata.cdh.hadoopuser.HadoopAppUser;
import cn.edu.bjtu.cdh.bigdata.utils.Base64;

@Path("/guest")
public class GuestService {
	@Context
	ServletContext servletContext;
	@Context
	HttpServletRequest request;

	@POST
	@Path("/submit")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response submit(

			@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail, 
			@FormDataParam("jobName") String jobName,
			@FormDataParam("jarName") String jarName,
			@FormDataParam("jobDesc") String jobDesc, @FormDataParam("masterHost") String masterHost,
			@FormDataParam("masterPassword") String masterPassword, @FormDataParam("masterPort") String masterPort,
			@FormDataParam("masterUser") String masterUser, @FormDataParam("hadoopBin") String hadoopBin,
			@FormDataParam("appRoot") String appRoot, @FormDataParam("mainClass") String mainClass,
			@FormDataParam("args") String args) {
		try {
			System.out.println("in simple model");
			// basic infor
			String root = this.servletContext.getInitParameter("sys_root");

			HadoopEnv he = new HadoopEnv();
			he.setAppRoot(appRoot);
			he.setMasterHost(masterHost);
			he.setHadoopBin(hadoopBin);
			he.setHdfsPort(9000);
			he.setMasterHost(masterHost);
			he.setMasterUser(masterUser);
			he.setMasterPassword(masterPassword);
			he.setMasterPort(Integer.parseInt(masterPort));

			// chinese string decoding

			jarName = Base64.decode(jarName);
			jobName = Base64.decode(jobName);
			jobDesc = Base64.decode(jobDesc);
			args = Base64.decode(args);

			System.out.println("jarName = " + jarName);
			System.out.println("jobDesc = " + jobDesc);
			System.out.println("mainClass = " + mainClass);
			System.out.println("args = " + args);

			// 直接调用
			String ids = HadoopAppUser.submitJobForGuestAwait(he, root, jobName, jobDesc, jarName, mainClass, args);

			String[] fs = ids.split(",");

			String libId = fs[0];
			String jobId = fs[1];

			// 存成文件
			String upload_folder = root + "/libs/guest_app/guest/" + libId;
			if (!new File(upload_folder).exists())
				new File(upload_folder).mkdirs();

			String upload_path = upload_folder + "/" + jarName;

			writeToFile(uploadedInputStream, upload_path);

			String job_folder = root + "/apps/guest_app/guest/" + jobId;
			String jar_folder = job_folder + "/" + "jars";
			if (!new File(jar_folder).exists())
				new File(jar_folder).mkdirs();

			FileUtils.copyFile(new File(upload_folder + "/" + jarName), new File(jar_folder + "/" + jarName));

			// 返回状态
			HadoopAppUser hau = new HadoopAppUser(root, "guest_app", "guest",he);

			if (hau.login("123456")) {

				String ret = hau.submitJob(jobId, false);
				
				System.out.println(ret);

				return Response.ok(jobId).build();

			} else
				return Response.ok("error").build();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return Response.ok("error").build();

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
