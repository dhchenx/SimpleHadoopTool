package cn.edu.bjtu.cdh.bigdata.hdfs;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;


import javax.swing.JOptionPane;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FileUtil;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;



public class UploadFile {
	
	private static String HDFSUri = "hdfs://192.168.100.91:9000";
	
	
	/*
	 * 单文件上传
	 */
	
public static void copyFileToHDFS(String hdfs_root,String srcFile,String destPath)throws Exception{
		
        FileInputStream fis=new FileInputStream(new File(srcFile));//读取本地文件
        Configuration config=new Configuration();
        FileSystem fs=FileSystem.get(URI.create(hdfs_root+destPath), config);
        OutputStream os=fs.create(new Path(destPath));
        
        byte[] by=new byte[1024*1024*5];//byte[]数组的大小，根据复制文件的大小可以调整，1G一下可以5M。1G以上150M，自己多试试  
        int len;  
        boolean flag=true;  
        long f=System.nanoTime();  
        double begin=fis.available();  
        while(flag)  
        {  
            len=fis.read(by);  
            if(len==-1)  
            {  
                flag=false;  
                continue;  
            }  
            
            os.write(by,0,len);  
            os.flush();  
            System.out.print("\r                                         \r"+(1-fis.available()/begin)*100+"%");//显示进度，如果文件过大  
            //（2G以上，可能一开始会一直显示0.0%，因为算出的数据过小，丢失了，不过还是在复制，要等等  
        }  
        
        long e=System.nanoTime();  
        System.out.println("\n用时"+(e-f)/1000000000+"秒");//显示总用时
        //copy
        IOUtils.copyBytes(fis, os, 4096, true);
        
        System.out.println("拷贝完成...");
        
        
        
        fs.close();
    }
	
	
	public static void copyFileToHDFS(String srcFile,String destPath)throws Exception{
		
        FileInputStream fis=new FileInputStream(new File(srcFile));//读取本地文件
        Configuration config=new Configuration();
        FileSystem fs=FileSystem.get(URI.create(HDFSUri+destPath), config);
        OutputStream os=fs.create(new Path(destPath));
        
        byte[] by=new byte[1024*1024*5];//byte[]数组的大小，根据复制文件的大小可以调整，1G一下可以5M。1G以上150M，自己多试试  
        int len;  
        boolean flag=true;  
        long f=System.nanoTime();  
        double begin=fis.available();  
        while(flag)  
        {  
            len=fis.read(by);  
            if(len==-1)  
            {  
                flag=false;  
                continue;  
            }  
            
            os.write(by,0,len);  
            os.flush();  
            System.out.print("\r                                         \r"+(1-fis.available()/begin)*100+"%");//显示进度，如果文件过大  
            //（2G以上，可能一开始会一直显示0.0%，因为算出的数据过小，丢失了，不过还是在复制，要等等  
        }  
        
        long e=System.nanoTime();  
        System.out.println("\n用时"+(e-f)/1000000000+"秒");//显示总用时
        //copy
        IOUtils.copyBytes(fis, os, 4096, true);
        
        System.out.println("拷贝完成...");
        
        
        
        fs.close();
    }
	
	
	
	

    /**
     *  将多个文件上传至 HDFS
     * 
     * @param srcPath 源路径
     * @param destPath 目标路径
     * @throws URISyntaxException
     * @throws IOException
     */
	public static void MutiuploadFile(String srcPath,String destPath) throws URISyntaxException, IOException {
	    // 读取配置文件
	    Configuration conf = new Configuration();
	  
		// 远端文件系统
	    URI uri = new URI(HDFSUri.trim());
	    FileSystem remote = FileSystem.get(uri,conf);;
	    // 获得本地文件系统
	    FileSystem local = FileSystem.getLocal(conf);
	    
	    // 只上传srcPath目录下符合filter条件的文件
	    FileStatus[] localStatus = local.globStatus(new Path(srcPath));
	    // 获得所有文件路径
	    Path[] listedPaths = FileUtil.stat2Paths(localStatus);
	       
	    if(listedPaths != null){
	        for(Path path : listedPaths){
	                // 将本地文件上传到HDFS
	            remote.copyFromLocalFile(path, new Path(HDFSUri + destPath));
	        }
	    }
	    JOptionPane.showMessageDialog(null, "数据上传成功");
	}

	public static void main(String[] args) throws IOException,URISyntaxException {
		    // 第一个参数：代表是源路径
		    // 第二个参数：代表是目录路径
		    // 第三个参数：代表是正则，这里我们只有上传txt文件，所以正则是^.*txt$
		    MutiuploadFile("D:\\mj\\*","/user/mj/");
		 }

}
