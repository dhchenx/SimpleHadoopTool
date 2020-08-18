using BigDataClient.Models;
using RestSharp;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Security.Cryptography;
using System.Text;
using RestSharp.Extensions;

namespace BigDataClient.Utils
{

    public class JsonTools
    {
        // 从一个对象信息生成Json串
        public static string ObjectToJson(object obj)
        {
            return Newtonsoft.Json.JsonConvert.SerializeObject(obj);
        }
        // 从一个Json串生成对象信息
        public static T JsonToObject<T>(string jsonString)
        {
            return (T)Newtonsoft.Json.JsonConvert.DeserializeObject(jsonString,typeof(T));
        }
    }
  
    public class ApiUtils
    {
        public static string encodeBase64(string s)
        {
            return Convert.ToBase64String(System.Text.Encoding.GetEncoding("GBK").
                     GetBytes(s));
        }

        public static string GetMD5(string str)
        {
            string cl = str;
            string pwd = "";
            MD5 md5 = MD5.Create();//实例化一个md5对像
            // 加密后是一个字节类型的数组，这里要注意编码UTF8/Unicode等的选择　
            byte[] s = md5.ComputeHash(Encoding.UTF8.GetBytes(cl));
            // 通过使用循环，将字节类型的数组转换为字符串，此字符串是常规字符格式化所得
            for (int i = 0; i < s.Length; i++)
            {
                // 将得到的字符串使用十六进制类型格式。格式后的字符是小写的字母，如果使用大写（X）则格式后的字符是大写字符 
                pwd = pwd + s[i].ToString("x2");

            }
            return pwd;
        }


        public static string saveFileInfo(string userId, string filelocalpath, string filePath,string taskid)
        {
            var client = new RestClient(Shared.API_ROOT+"file");
            // client.Authenticator = new HttpBasicAuthenticator(username, password);

            var request = new RestRequest("info", Method.POST);
            request.AddParameter("userId", userId);
            request.AddParameter("taskid", taskid);
            request.AddParameter("fileName", Convert.ToBase64String(System.Text.Encoding.GetEncoding("GBK").GetBytes(Path.GetFileName(filelocalpath)))); // replaces matching token in request.Resource
            request.AddParameter("filelocalpath", Convert.ToBase64String(System.Text.Encoding.GetEncoding("GBK").GetBytes(filelocalpath))); // replaces matching token in request.Resource
            request.AddParameter("filepath", Convert.ToBase64String(System.Text.Encoding.GetEncoding("GBK").GetBytes(filePath))); // replaces matching token in request.Resource
            request.AddHeader("Accept", "text/plain");
            request.AddHeader("content-type", "application/x-www-form-urlencoded");
            // request.AddParameter("application/x-www-form-urlencoded",
            //     "userId=chendonghua&fileName=" + Convert.ToBase64String(System.Text.Encoding.GetEncoding("GBK").GetBytes("我的文件.tx")) + 
            //    "&clientFolder=c1&serverFolder=c2", ParameterType.RequestBody);  

            // execute the request
            IRestResponse response = client.Execute(request);
            var content = response.Content; // raw content as string
            Console.WriteLine(content);

            string[] ps = content.Split(':');

            return ps[1];

            //MessageBox.Show(content);
        }

        public static string uploadFile(string fileId, string localFile, string hdfs_path)
        {
            var client = new RestClient(Shared.API_ROOT+"file");
            // client.Authenticator = new HttpBasicAuthenticator(username, password);

            var request = new RestRequest("upload", Method.POST);

            request.AddFile("file", localFile);
            request.AddParameter("fileId", fileId);
            request.AddParameter("hdfs_path", hdfs_path);
            request.AddParameter("fileName", Convert.ToBase64String(System.Text.Encoding.GetEncoding("GBK").GetBytes(Path.GetFileName(Path.GetFileName(localFile)))));
            // request.AddParameter("application/x-www-form-urlencoded",
            //     "userId=chendonghua&fileName=" + Convert.ToBase64String(System.Text.Encoding.GetEncoding("GBK").GetBytes("我的文件.tx")) + 
            //    "&clientFolder=c1&serverFolder=c2", ParameterType.RequestBody);  

            // execute the request
            IRestResponse response = client.Execute(request);
            var content = response.Content; // raw content as string

            return content;
        }


        public static string uploadFile(string API_ROOT, string service, string action, string method, string localFile, string[] keys,string[] values)
        {
            var client = new RestClient(Shared.API_ROOT + service);
            // client.Authenticator = new HttpBasicAuthenticator(username, password);



            method = method.ToLower();
            Method m = Method.POST;
            if (method == "post")
                m = Method.POST;
            if (method == "get")
                m = Method.GET;
            if (method == "put")
                m = Method.PUT;
            if (method == "delete")
                m = Method.DELETE;


            var request = new RestRequest(action, m);

            request.AddFile("file", localFile);
            for(int i = 0; i < keys.Length; i++)
            {
                request.AddParameter(keys[i], values[i]);
            }

         //   request.AddParameter("fileName", Convert.ToBase64String(System.Text.Encoding.GetEncoding("GBK").GetBytes(Path.GetFileName(Path.GetFileName(localFile)))));
            // request.AddParameter("application/x-www-form-urlencoded",
            //     "userId=chendonghua&fileName=" + Convert.ToBase64String(System.Text.Encoding.GetEncoding("GBK").GetBytes("我的文件.tx")) + 
            //    "&clientFolder=c1&serverFolder=c2", ParameterType.RequestBody);  

            // execute the request
            IRestResponse response = client.Execute(request);
            var content = response.Content; // raw content as string

            return content;
        }


        public static string callApi(string API_ROOT,string service, string action, string method,string [] keys,string[] values)
        {

            var client = new RestClient(API_ROOT+service);
            // client.Authenticator = new HttpBasicAuthenticator(username, password);
            method = method.ToLower();
            Method m = Method.POST;
            if (method == "post")
                m = Method.POST;
            if (method == "get")
                m = Method.GET;
            if (method == "put")
                m = Method.PUT;
            if (method == "delete")
                m = Method.DELETE;

            var request = new RestRequest(action, m);
            for (int i = 0; i < keys.Length; i++)
            {
                request.AddParameter(keys[i], values[i]);
            }
           
            request.AddHeader("Accept", "text/plain");
            request.AddHeader("content-type", "application/x-www-form-urlencoded");
         
            IRestResponse response = client.Execute(request);
            var content = response.Content; // raw content as string
            return content;

        }

        public static void callApiForDownload(string API_ROOT, string service, string action, string method, string[] keys, string[] values,string save_as_path)
        {

            var client = new RestClient(API_ROOT + service);
            // client.Authenticator = new HttpBasicAuthenticator(username, password);
            method = method.ToLower();
            Method m = Method.POST;
            if (method == "post")
                m = Method.POST;
            if (method == "get")
                m = Method.GET;
            if (method == "put")
                m = Method.PUT;
            if (method == "delete")
                m = Method.DELETE;

            var request = new RestRequest(action, m);
            for (int i = 0; i < keys.Length; i++)
            {
                request.AddParameter(keys[i], values[i]);
            }

           // request.AddHeader("Accept", "text/plain");
            request.AddHeader("content-type", "application/x-www-form-urlencoded");

            client.DownloadData(request).SaveAs(save_as_path);

        }


        public static string callApi(string API_ROOT, string service, string action, string method, object obj)
        {

            var client = new RestClient(API_ROOT + service);
            // client.Authenticator = new HttpBasicAuthenticator(username, password);
            method = method.ToLower();
            Method m = Method.POST;
            if (method == "post")
                m = Method.POST;
            if (method == "get")
                m = Method.GET;
            if (method == "put")
                m = Method.PUT;
            if (method == "delete")
                m = Method.DELETE;

            var request = new RestRequest(action, m);
           

            request.AddHeader("Accept", "text/plain");
            request.AddHeader("Content-type", "application/json");
            request.AddJsonBody(obj);

            IRestResponse response = client.Execute(request);
            var content = response.Content; // raw content as string
            return content;

        }


        public static T callApiModel<T>(string API_ROOT, string service, string action, string method, string[] keys, string[] values) 
        {

            var client = new RestClient(API_ROOT + service);
            // client.Authenticator = new HttpBasicAuthenticator(username, password);
            method = method.ToLower();
            Method m = Method.POST;
            if (method == "post")
                m = Method.POST;
            if (method == "get")
                m = Method.GET;
            if (method == "put")
                m = Method.PUT;
            if (method == "delete")
                m = Method.DELETE;

            

            var request = new RestRequest(action, m);

         //   request.RequestFormat = DataFormat.Json;

          //  request.AddBody(request.JsonSerializer.Serialize(new { A = "foo", B = "bar" }));

            for (int i = 0; i < keys.Length; i++)
            {
                request.AddParameter(keys[i], values[i]);
            }

           request.AddHeader("Accept", "text/plain");
          request.AddHeader("content-type", "application/x-www-form-urlencoded");

            IRestResponse response = client.Execute(request);
            string jsonstr = response.Content; // raw content as string

            T t = JsonTools.JsonToObject<T>(jsonstr);

            return t;

        }
   
    }
}
