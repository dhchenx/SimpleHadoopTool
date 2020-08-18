package cn.edu.bjtu.cdh.bigdata.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;

import cn.edu.bjtu.bigdata.cdh.hadoopuser.HadoopAppUser;
import cn.edu.bjtu.cdh.bigdata.utils.CompactAlgorithm;

@Path("/job")
public class JobService {
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
			@FormParam("jobId") String jobId 
			) {
		 
		String root = this.servletContext.getInitParameter("sys_root");
		HadoopAppUser hadoopAppUser = new HadoopAppUser(root, appId, userId);
		
		boolean flag=hadoopAppUser.deleteJob(jobId);
		
		if(flag)
			return "1";
		else
			return "0";
	 
	}
	
	
	@POST
	@Path("/submit")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String submit(
			@FormParam("appId") String appId, 
			@FormParam("userId") String userId,
			@FormParam("jobId") String jobId 
			) {
		 
		String root = this.servletContext.getInitParameter("sys_root");
		HadoopAppUser hadoopAppUser = new HadoopAppUser(root, appId, userId);
		
		String ret=hadoopAppUser.submitJob(jobId);
		
		return ret;
	 
	}
	
	@POST
	@Path("/status")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String isSuccess(
			@FormParam("appId") String appId, 
			@FormParam("userId") String userId,
			@FormParam("jobId") String jobId 
			) {
		 
		String root = this.servletContext.getInitParameter("sys_root");
		HadoopAppUser hadoopAppUser = new HadoopAppUser(root, appId, userId);
		
		System.out.println("is checking status...");
		
		boolean flag=hadoopAppUser.checkJobStatus(jobId);
		if(flag)
			return "1";
		return "0";
	 
	}
	
	@POST
	@Path("/download")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String download(
			@FormParam("appId") String appId, 
			@FormParam("userId") String userId,
			@FormParam("jobId") String jobId 
			) {
		 
		String root = this.servletContext.getInitParameter("sys_root");
		HadoopAppUser hadoopAppUser = new HadoopAppUser(root, appId, userId);
		
		boolean flag=hadoopAppUser.downloadJobReslt(jobId);


		
		if(flag) {
			return "1";
			
		}else
			return "0";
		 
	 
	}
	
	 	@POST
	    @Path("/downloadfile")
	    public Response downloadPdfFile(
	    		@FormParam("appId") String appId, 
				@FormParam("userId") String userId,
				@FormParam("jobId") String jobId )
	    {
	 		
			String root = this.servletContext.getInitParameter("sys_root");
			HadoopAppUser hadoopAppUser = new HadoopAppUser(root, appId, userId);
			
			boolean flag=hadoopAppUser.downloadJobReslt(jobId);
			
			if(flag) {
			
			File f = new File(root+"/apps/"+appId+"/"+userId+"/"+jobId+"/outputs");
			String output_zip_folder=root+"/apps/"+appId+"/"+userId+"/"+jobId+"/outputs-zip";
			
			if(!new File(output_zip_folder).exists())
				new File(output_zip_folder).mkdirs();
			
			String zipFileName=appId+"_"+userId+"_"+System.currentTimeMillis()+".zip";
			new CompactAlgorithm(new File( output_zip_folder,zipFileName)).zipFiles(f);
	 		
			
			String target_file=output_zip_folder+"/"+zipFileName;
			
			if(new File(target_file).exists()) {
			
	        StreamingOutput fileStream =  new StreamingOutput()
	        {
	            @Override
	            public void write(java.io.OutputStream output) throws IOException, WebApplicationException
	            {
	                try
	                {
	                    java.nio.file.Path path = Paths.get(target_file);
	                    byte[] data = Files.readAllBytes(path);
	                    output.write(data);
	                    output.flush();
	                }
	                catch (Exception e)
	                {
	                   e.printStackTrace();
	                }
	            }
	        };
	        
	      
	        
	    	
		    return Response
	                .ok(fileStream, MediaType.APPLICATION_OCTET_STREAM)
	                .header("content-disposition","attachment; filename ="+zipFileName)
	                .build();
	        
			}
		
	        
			}
			
			return Response
	                .ok("no file")
	                .build();
	    
	    }
	
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String addUser(@FormParam("appId") String appId, @FormParam("userId") String userId,
			@FormParam("libId") String libId,@FormParam("methodId") String methodId,
			@FormParam("jobName") String jobName,@FormParam("jobDesc") String jobDesc,
			@FormParam("args") String args
			) {
		try {
			
		String root = this.servletContext.getInitParameter("sys_root");
		HadoopAppUser hadoopAppUser = new HadoopAppUser(root, appId, userId);
		String jobId=hadoopAppUser.addJob(libId, methodId, jobName, jobDesc, args);
		if (jobId!=null) {
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
