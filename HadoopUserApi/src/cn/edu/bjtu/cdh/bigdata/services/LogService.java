package cn.edu.bjtu.cdh.bigdata.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.util.ToolRunner;

import cn.edu.bjtu.cdh.bigdata.utils.CompactAlgorithm;

@Path("/log")
public class LogService {


	@Context
	ServletContext servletContext;
	@Context
	HttpServletRequest request;
	
	@POST
	@Path("/detect")
 
	public String detectIfFinished(
			@FormParam("appId") String appId, 
			@FormParam("userId") String userId,
			@FormParam("masterHost") String masterHost,
			@FormParam("jobId") String jobId,
			@FormParam("traceId") String traceId
			) {

		String root = this.servletContext.getInitParameter("sys_root");	

	 
			
		  FileSystem hdfs = null;
		  
	        try{
	        	
	        	if(jobId.equals("")) {
	        		jobId="unknow_jobId";
	        	}
	        	
	        	String hdfsPath="hdfs://"+masterHost+":"+"9000";
	    		 
	     		
	        	System.out.println("begining");
	            Configuration config = new Configuration();
	            
	            String hdfs_root=hdfsPath;
	            
	            config.set("mapred.jop.tracker", hdfs_root.replace("9000", "9001"));
	            config.set("fs.defaultFS", hdfs_root);
	            
	            hdfs = FileSystem.get(new URI(hdfs_root),
	                    config, "hadoop");
	            org.apache.hadoop.fs.Path donePath = new org.apache.hadoop.fs.Path("/tmp/hadoop-yarn/staging/history/done/*/*/*/*/*");

	            FileStatus[] doneFiles = hdfs.globStatus(donePath, new PathFilter() {
	                @Override
	                public boolean accept(org.apache.hadoop.fs.Path path) {
	                   // String contidion = "abc";
	                    // 过滤出路径中包含 abc字符串 的路径
	                   // return  path.toString().contains(contidion);
	                	String pathStr=path.toString();
	                	
	                	try {
	                 pathStr=java.net.URLDecoder.decode(pathStr, "utf-8");
	                	}catch(Exception ex) {
	                		ex.printStackTrace();
	                	}
	                			System.out.println(pathStr);
	                	if(pathStr.contains("[")&&pathStr.contains("]")) {
	                		int b_i=pathStr.indexOf("[");
	                		int e_i=pathStr.indexOf("]");
	                		
	                		String trace_id=pathStr.substring(b_i+1,e_i);
	                		
	                		if(trace_id.contains("-")) {
	                			trace_id=trace_id.split("-")[0];
	                		}
	                		
	                		if(trace_id.equals(traceId)) {
	                			return true;
	                		}
	                		System.out.println(trace_id);
	                	 
	                	}
	                	return false;
	                }
	            });
	            
	            org.apache.hadoop.fs.Path interPath = new org.apache.hadoop.fs.Path("/tmp/hadoop-yarn/staging/history/done_intermediate/hadoop/*");

	            FileStatus[] interFiles = hdfs.globStatus(interPath, new PathFilter() {
	                @Override
	                public boolean accept(org.apache.hadoop.fs.Path path) {
	                   // String contidion = "abc";
	                    // 过滤出路径中包含 abc字符串 的路径
	                   // return  path.toString().contains(contidion);
	                	String pathStr=path.toString();
	                	
	                	try {
	                 pathStr=java.net.URLDecoder.decode(pathStr, "utf-8");
	                	}catch(Exception ex) {
	                		ex.printStackTrace();
	                	}
	                			System.out.println(pathStr);
	                	if(pathStr.contains("[")&&pathStr.contains("]")) {
	                		int b_i=pathStr.indexOf("[");
	                		int e_i=pathStr.indexOf("]");
	                		
	                		String trace_id=pathStr.substring(b_i+1,e_i);
	                		
	                		if(trace_id.contains("-")) {
	                			trace_id=trace_id.split("-")[0];
	                		}
	                		
	                		if(trace_id.equals(traceId)) {
	                			return true;
	                		}
	                		System.out.println(trace_id);
	                	 
	                	}
	                	return false;
	                }
	            });
	            
	            if(doneFiles.length>0&&interFiles.length<=0) {
	            	return "1";//finished
	            }else if (doneFiles.length<=0&&interFiles.length>0) {
	            	return "2";//running
	            }else if(doneFiles.length<=0&&interFiles.length<=0) {
	            	return "3";//not finished or log service not start or config_path is not config!
	            }else {
	            	return "0";
	            }
	            
	        }catch(Exception ex) {
	        	ex.printStackTrace();
	        	return "0";
	        }
	        
	        
	}
	
	@POST
	@Path("/trace")
 
	public Response trace(
			@FormParam("appId") String appId, 
			@FormParam("userId") String userId,
			@FormParam("masterHost") String masterHost,
			@FormParam("jobId") String jobId,
			@FormParam("traceId") String traceId
			) {

		String root = this.servletContext.getInitParameter("sys_root");	

	 
			
		  FileSystem hdfs = null;
		  
	        try{
	        	
	        	if(jobId.equals("")) {
	        		jobId="unknow_jobId";
	        	}
	        	
	        	String hdfsPath="hdfs://"+masterHost+":"+"9000";
	    		 
	     		
	        	System.out.println("begining");
	            Configuration config = new Configuration();
	            
	            String hdfs_root=hdfsPath;
	            
	            config.set("mapred.jop.tracker", hdfs_root.replace("9000", "9001"));
	            config.set("fs.defaultFS", hdfs_root);
	            
	            hdfs = FileSystem.get(new URI(hdfs_root),
	                    config, "hadoop");
	            org.apache.hadoop.fs.Path allPath = new org.apache.hadoop.fs.Path("/tmp/hadoop-yarn/staging/history/done/*/*/*/*/*");

	            FileStatus[] fileGlobStatuses = hdfs.globStatus(allPath, new PathFilter() {
	                @Override
	                public boolean accept(org.apache.hadoop.fs.Path path) {
	                   // String contidion = "abc";
	                    // 过滤出路径中包含 abc字符串 的路径
	                   // return  path.toString().contains(contidion);
	                	String pathStr=path.toString();
	                	
	                	try {
	                 pathStr=java.net.URLDecoder.decode(pathStr, "utf-8");
	                	}catch(Exception ex) {
	                		ex.printStackTrace();
	                	}
	                			System.out.println(pathStr);
	                	if(pathStr.contains("[")&&pathStr.contains("]")) {
	                		int b_i=pathStr.indexOf("[");
	                		int e_i=pathStr.indexOf("]");
	                		
	                		String trace_id=pathStr.substring(b_i+1,e_i);
	                		
	                		if(trace_id.contains("-")) {
	                			trace_id=trace_id.split("-")[0];
	                		}
	                		
	                		if(trace_id.equals(traceId)) {
	                			return true;
	                		}
	                		System.out.println(trace_id);
	                	 
	                	}
	                	return false;
	                }
	            });
	            
	           
	            List<String> loglist=new ArrayList<String>();
	            List<String> conflist=new ArrayList<String>();
	            List<String> traceList=new ArrayList<String>();
	            List<String> toList=new ArrayList<String>();
	            
	            org.apache.hadoop.fs.Path[] globPaths = FileUtil.stat2Paths(fileGlobStatuses);
	            
	            System.out.println("found_file:"+globPaths.length);
	            String traceFolder=root+"/apps/"+appId+"/"+userId+"/"+jobId+"/trace/"+traceId;
	            String tmpFolder=root+"/apps/"+appId+"/"+userId+"/"+jobId+"/tmp";
	            
	            
        		
	        	if(!new File(traceFolder).exists() )
        			new File(traceFolder).mkdirs();
	        	
	        	if(!new File(tmpFolder).exists() )
        			new File(tmpFolder).mkdirs();
	        	
	            for (int i=0;i<globPaths.length;i++){
	            	org.apache.hadoop.fs.Path p =globPaths[i];
	            	String log_name=""+p;
	            	loglist.add(log_name);
	            	
	            	System.out.println("selected: "+p);
	            	String[] ls=p.getName().split("-");
	            	String conf_name=ls[0]+"_conf.xml";
	            	
	            	String conf_path=p.getParent()+"/"+conf_name;
	            	conflist.add(conf_path);
	            	System.out.println("selected: "+conf_path);
	            	
	            	org.apache.hadoop.tools.rumen.TraceBuilder tb=new org.apache.hadoop.tools.rumen.TraceBuilder();
	            	
	            	String myId=getTraceId(log_name);
	            	String sub_id=i+"";;
	            	if(myId.contains("-")) {
	            		sub_id=myId.split("-")[1];
	            		myId=myId.substring(0, myId.indexOf("-"));
	            	}
	            	
	            	
	            	String traceFile=p.getParent()+"/job-trace_"+myId+"_"+sub_id+".json";
	            	String toFile=p.getParent()+"/topology_"+myId+"_"+sub_id+".json";
	            	
	            	traceList.add(traceFile);
	            	toList.add(toFile);
	            	
	            	System.out.println("traceFile="+traceFile);
	            	System.out.println("toFile="+toFile);
	            	System.out.println("log_name="+log_name);
	            	
	            
	        		int r=ToolRunner.run(tb, new String[] {traceFile,toFile, log_name});
	
	        		this.writeFromHDFS2Local(hdfs, traceFile, traceFolder+"/"+new File(traceFile).getName());
	        		this.writeFromHDFS2Local(hdfs, toFile, traceFolder+"/"+new File(toFile).getName());
		        	 
	            	
	            	System.out.println("RUMEN RESTULT="+r);

	            	
	            }
	            
	        	String zipFileName=appId+"_"+userId+"_"+jobId+"_"+traceId+".zip";
				new CompactAlgorithm(new File( tmpFolder,zipFileName)).zipFiles(new File(traceFolder));
	            
	            StreamingOutput fileStream =  new StreamingOutput()
		        {
		            @Override
		            public void write(java.io.OutputStream output) throws IOException, WebApplicationException
		            {
		                try
		                {
		                	if(traceList.size()>0) {
		                    java.nio.file.Path path = Paths.get(tmpFolder+"/"+zipFileName);
		                    byte[] data = Files.readAllBytes(path);
		                    output.write(data);
		                    output.flush();
		                	}else {
		                		System.out.println("error: no found trace file");
		                	}
		                }
		                catch (Exception e)
		                {
		                   e.printStackTrace();
		                }
		            }
		        };

		    	
			    return Response
		                .ok(fileStream, MediaType.APPLICATION_OCTET_STREAM)
		                .header("content-disposition","attachment; filename ="+traceId+".zip")
		                .build();

	        }catch(Exception e){
	            e.printStackTrace();
	            return Response.ok("failed").build();
	        }

	}
	
	
	private void writeFromHDFS2Local(FileSystem fs,String CLOUD_DESC,String LOCAL_SRC) {
		try {
		// 读出流
		FSDataInputStream HDFS_IN = fs.open(new org.apache.hadoop.fs.Path(CLOUD_DESC));
		// 写入流
		OutputStream OutToLOCAL = new FileOutputStream(LOCAL_SRC);
		// 将InputStrteam 中的内容通过IOUtils的copyBytes方法复制到OutToLOCAL中
		IOUtils.copyBytes(HDFS_IN, OutToLOCAL, 1024, true);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
		public static String getTraceId(String path) {
			String pathStr=path.toString();
	    	
	    	try {
	     pathStr=java.net.URLDecoder.decode(pathStr, "utf-8");
	    	}catch(Exception ex) {
	    		ex.printStackTrace();
	    	}
	    			System.out.println(pathStr);
	    	if(pathStr.contains("[")&&pathStr.contains("]")) {
	    		int b_i=pathStr.indexOf("[");
	    		int e_i=pathStr.indexOf("]");
	    		
	    		String trace_id=pathStr.substring(b_i+1,e_i);
	    	 
	    		return trace_id;
	    	 
	    	}
	    	return "";
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
