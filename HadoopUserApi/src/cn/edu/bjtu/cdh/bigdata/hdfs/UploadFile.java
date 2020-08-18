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
	 * ���ļ��ϴ�
	 */
	
public static void copyFileToHDFS(String hdfs_root,String srcFile,String destPath)throws Exception{
		
        FileInputStream fis=new FileInputStream(new File(srcFile));//��ȡ�����ļ�
        Configuration config=new Configuration();
        FileSystem fs=FileSystem.get(URI.create(hdfs_root+destPath), config);
        OutputStream os=fs.create(new Path(destPath));
        
        byte[] by=new byte[1024*1024*5];//byte[]����Ĵ�С�����ݸ����ļ��Ĵ�С���Ե�����1Gһ�¿���5M��1G����150M���Լ�������  
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
            System.out.print("\r                                         \r"+(1-fis.available()/begin)*100+"%");//��ʾ���ȣ�����ļ�����  
            //��2G���ϣ�����һ��ʼ��һֱ��ʾ0.0%����Ϊ��������ݹ�С����ʧ�ˣ����������ڸ��ƣ�Ҫ�ȵ�  
        }  
        
        long e=System.nanoTime();  
        System.out.println("\n��ʱ"+(e-f)/1000000000+"��");//��ʾ����ʱ
        //copy
        IOUtils.copyBytes(fis, os, 4096, true);
        
        System.out.println("�������...");
        
        
        
        fs.close();
    }
	
	
	public static void copyFileToHDFS(String srcFile,String destPath)throws Exception{
		
        FileInputStream fis=new FileInputStream(new File(srcFile));//��ȡ�����ļ�
        Configuration config=new Configuration();
        FileSystem fs=FileSystem.get(URI.create(HDFSUri+destPath), config);
        OutputStream os=fs.create(new Path(destPath));
        
        byte[] by=new byte[1024*1024*5];//byte[]����Ĵ�С�����ݸ����ļ��Ĵ�С���Ե�����1Gһ�¿���5M��1G����150M���Լ�������  
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
            System.out.print("\r                                         \r"+(1-fis.available()/begin)*100+"%");//��ʾ���ȣ�����ļ�����  
            //��2G���ϣ�����һ��ʼ��һֱ��ʾ0.0%����Ϊ��������ݹ�С����ʧ�ˣ����������ڸ��ƣ�Ҫ�ȵ�  
        }  
        
        long e=System.nanoTime();  
        System.out.println("\n��ʱ"+(e-f)/1000000000+"��");//��ʾ����ʱ
        //copy
        IOUtils.copyBytes(fis, os, 4096, true);
        
        System.out.println("�������...");
        
        
        
        fs.close();
    }
	
	
	
	

    /**
     *  ������ļ��ϴ��� HDFS
     * 
     * @param srcPath Դ·��
     * @param destPath Ŀ��·��
     * @throws URISyntaxException
     * @throws IOException
     */
	public static void MutiuploadFile(String srcPath,String destPath) throws URISyntaxException, IOException {
	    // ��ȡ�����ļ�
	    Configuration conf = new Configuration();
	  
		// Զ���ļ�ϵͳ
	    URI uri = new URI(HDFSUri.trim());
	    FileSystem remote = FileSystem.get(uri,conf);;
	    // ��ñ����ļ�ϵͳ
	    FileSystem local = FileSystem.getLocal(conf);
	    
	    // ֻ�ϴ�srcPathĿ¼�·���filter�������ļ�
	    FileStatus[] localStatus = local.globStatus(new Path(srcPath));
	    // ��������ļ�·��
	    Path[] listedPaths = FileUtil.stat2Paths(localStatus);
	       
	    if(listedPaths != null){
	        for(Path path : listedPaths){
	                // �������ļ��ϴ���HDFS
	            remote.copyFromLocalFile(path, new Path(HDFSUri + destPath));
	        }
	    }
	    JOptionPane.showMessageDialog(null, "�����ϴ��ɹ�");
	}

	public static void main(String[] args) throws IOException,URISyntaxException {
		    // ��һ��������������Դ·��
		    // �ڶ���������������Ŀ¼·��
		    // ������������������������������ֻ���ϴ�txt�ļ�������������^.*txt$
		    MutiuploadFile("D:\\mj\\*","/user/mj/");
		 }

}
