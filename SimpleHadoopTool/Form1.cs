using BigDataClient.Models;
using BigDataClient.Utils;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace SimpleHadoopTool
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        System.Timers.Timer t = null;

        public string Current_Job_Id = null;

        public string traceId = null;

        private void btnAnalyze_Click(object sender, EventArgs e)
        {
            txtInfo.Text = "...";

          

            if (!File.Exists(txtJarFile.Text))
            {
                MessageBox.Show("请选择有效的Jar文件!");
                return;
            }

            if (!Directory.Exists(txtDownloadPath.Text))
            {
                MessageBox.Show("请选择有效的输出文件夹!");
                return;
            }

            var he = new
            {
                masterHost = txtMasterHost.Text,
                masterUser = txtMasterUser.Text,
                masterPassword = txtMasterPassword.Text,
                masterPort = "22",
                appRoot = txtAppRoot.Text,
                hadoopBin = txtHadoopBin.Text
            };


            


            string inputs_folder = txtInputs.Text;


            string[] files = Directory.GetFiles(inputs_folder, "*.*", SearchOption.AllDirectories);

            foreach (string file in files)
            {
                Console.WriteLine(file);

                string result = ApiUtils.uploadFile(Shared.API_ROOT, "hdfs", "upload", "post", file,
         new string[] { "appId", "userId", "hdfsPath" },
         new string[] { "guest_app", "guest", ApiUtils.encodeBase64("hdfs://"+txtMasterHost.Text+":9000" + file.Replace(inputs_folder, "").Replace("\\", "/")) });

            }

            traceId = DateTime.Now.ToString("yyyyMMddHHmmss");
            txtTraceId.Text = traceId;


            string localFile =txtJarFile.Text;
            string job_id = ApiUtils.uploadFile(Shared.API_ROOT, "guest", "submit", "post", localFile,
                new string[] { "jobName", "jarName", "jobDesc", "masterHost", "masterPassword", "masterPort", "masterUser", "hadoopBin", "appRoot", "mainClass", "args" },
                new string[] {
                     ApiUtils.encodeBase64(txtJobName.Text),
                    ApiUtils.encodeBase64(Path.GetFileName(txtJarFile.Text)),
                    ApiUtils.encodeBase64(txtJobDesc.Text),
                    he.masterHost,
                    he.masterPassword,
                    he.masterPort,
                    he.masterUser,
                    he.hadoopBin,
                    he.appRoot,
                   txtMainClass.Text,
                    ApiUtils.encodeBase64(txtArgs.Text+" @traceId="+traceId)
         });


            if (!string.IsNullOrEmpty(job_id))
            {
                job_id = job_id.Replace("\"", "");

                this.Current_Job_Id = job_id;
                txtJobId.Text = this.Current_Job_Id;

                if (cbAutomatic.Checked)
                {
                    t = new System.Timers.Timer(1000); //设置时间间隔为5秒

                    t.Elapsed += new System.Timers.ElapsedEventHandler(DetectJobStatus);
                    t.AutoReset = true;
                    t.Enabled = true;
                    t.Start();

                }

            }
            else
            {
                MessageBox.Show("提交失败!");
            }

        }

        public void ShowMyCounter(string str)
        {
        //    txtInfo.Text = "Detecting if successfully: " + timer_counter;
            txtInfo.Text = str;
        }

    private delegate void ShowCounter(string str);

    int timer_counter = 0;

        //函数形式参数必须是object格式
        public void DetectJobStatus(object sender, System.Timers.ElapsedEventArgs e)
        {
            timer_counter++;

      

            txtInfo.Invoke(new ShowCounter(ShowMyCounter),new object[] { "delaying : " + timer_counter });

            if (timer_counter <= (int)numDelay.Value)
            {

                return;
            }
            else
            {
                txtInfo.Invoke(new ShowCounter(ShowMyCounter), new object[] { "Detecting if successfully: " + timer_counter });
            }

            string job_result = ApiUtils.callApi(Shared.API_ROOT, "job", "status", "post", new string[] { "appId", "userId", "jobId" },
       new string[] { "guest_app", "guest", this.Current_Job_Id });

            if (job_result == "1")
            {
                txtInfo.Invoke(new ShowCounter(ShowMyCounter), new object[] { "success!"});

                ApiUtils.callApiForDownload(Shared.API_ROOT, "job", "downloadfile", "post",
new string[] { "appId", "userId", "jobId" },
new string[] { "guest_app", "guest",this.Current_Job_Id},
txtDownloadPath.Text+"/"+txtJobName.Text+"-"+Current_Job_Id+"-"+DateTime.Now.ToString("yyyyHHmmHHmmss")+ ".zip");

                ((System.Timers.Timer)sender).Stop();

                txtInfo.Invoke(new ShowCounter(ShowMyCounter), new object[] { "Analyze and download completed!" });

             
            }

            
        }

        private void btnSaveAsPath_Click(object sender, EventArgs e)
        {
            FolderBrowserDialog fbd = new FolderBrowserDialog();
            if (fbd.ShowDialog() == DialogResult.OK)
            {
                txtDownloadPath.Text = fbd.SelectedPath;
            }
        }

        private void btnOpenJar_Click(object sender, EventArgs e)
        {
            OpenFileDialog ofg = new OpenFileDialog();
            if (ofg.ShowDialog()==DialogResult.OK)
            {
                txtJarFile.Text = ofg.FileName;
            }
        }

        private void btnReset_Click(object sender, EventArgs e)
        {
            if (t != null)
            {
                t.Stop();
                t.Enabled = false;
                t = null;
       
            }
            txtInfo.Text = "...";
            timer_counter = 0;
            this.txtJobId.Text = "";
            this.txtTraceId.Text = "";
        }

        public Dictionary<string,string> GetDict(string s)
        {
            Dictionary<string, string> map =
          new Dictionary<string, string>();
            string[] ls = s.Split('\n');
            for(int i = 0; i < ls.Length; i++)
            {
                string line = ls[i].Trim();
                if (line != "" && !line.StartsWith("#"))
                {
                    string[] fs = line.Split(' ');
                    string key = fs[0];
                    string rest = "";
                    if(fs.Length>1)
                    for(int j = 1; j < fs.Length; j++)
                    {
                        if (j != fs.Length - 1)
                        {
                            rest += fs[j] + " ";
                        }
                        else
                        {
                            rest += fs[j];
                        }
                    }
                    map.Add(key, rest);
                }
            }
            return map;

        }

        private void btnLoad_Click(object sender, EventArgs e)
        {
            FolderBrowserDialog fbd = new FolderBrowserDialog();
            if (fbd.ShowDialog() == DialogResult.OK)
            {
                FileInfo[] flist = new DirectoryInfo(fbd.SelectedPath).GetFiles();
                for(int i = 0; i < flist.Length; i++)
                {
                    FileInfo file = flist[i];
                    if (file.Name.StartsWith("env_"))
                    {
                        String env_text = System.IO.File.ReadAllText(file.FullName);
                        Dictionary<string, string> env_map = GetDict(env_text);

                        txtMasterHost.Text = env_map["MasterHost"];
                        txtMasterPassword.Text = env_map["MasterPassword"];
                        txtMasterUser.Text = env_map["MasterUser"];
                        txtHadoopBin.Text = env_map["HadoopBin"];
                        txtAppRoot.Text = env_map["AppRoot"];

                        continue;
                    }


                    if (file.Name.StartsWith("task_"))
                    {
                        String tast_text = System.IO.File.ReadAllText(file.FullName);
                        Dictionary<string, string> task_map = GetDict(tast_text);
                        string task_type = task_map["Task"];
                        if (task_type == "upload")
                        {
                            txtJarFile.Text = fbd.SelectedPath + "/" + task_map["LocalJarPath"]; 

                        }

                        if (task_type == "submit")
                        {
                            txtMainClass.Text = task_map["MainClass"];
                            txtArgs.Text = task_map["Args"];
                            
                        }
                    }

                    if (file.Name.StartsWith("_hproj"))
                    {
                        String proj_text = System.IO.File.ReadAllText(file.FullName);
                        Dictionary<string, string> proj_map = GetDict(proj_text);

                        txtJobName.Text = proj_map["Id"];
                        txtDownloadPath.Text = fbd.SelectedPath + "/" + proj_map["Outputs"];
                        txtJobDesc.Text =  proj_map["Description"];

                        txtInputs.Text = fbd.SelectedPath + "/" + proj_map["Inputs"];
                    }


                }
                
                    
            }

        }

        private void button1_Click(object sender, EventArgs e)
        {
            FolderBrowserDialog fbd = new FolderBrowserDialog();
            if (fbd.ShowDialog() == DialogResult.OK)
            {
                txtInputs.Text = fbd.SelectedPath;
            }
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            string apiURL = System.Configuration.ConfigurationManager.AppSettings["HADOOP_API_ROOT"];
            Shared.API_ROOT = apiURL;
        }

        private void btnJobLog_Click(object sender, EventArgs e)
        {
            /*
            string job_result = ApiUtils.callApi(Shared.API_ROOT, "job", "status", "post", new string[] { "appId", "userId", "jobId" },
 new string[] { "guest_app", "guest", this.Current_Job_Id });

            if (job_result == "1")
            {
            */
                string detect_result = ApiUtils.callApi(Shared.API_ROOT, "log", "detect", "post",
new string[] { "appId", "userId", "masterHost", "jobId", "traceId" },
new string[] { "guest_app", "guest", txtMasterHost.Text, txtJobId.Text, txtTraceId.Text });

                if (detect_result == "1")
                {

                    ApiUtils.callApiForDownload(Shared.API_ROOT, "log", "trace", "post",
    new string[] { "appId", "userId", "masterHost", "jobId", "traceId" },
    new string[] { "guest_app", "guest", txtMasterHost.Text, txtJobId.Text, txtTraceId.Text },
    txtDownloadPath.Text + "/" + txtJobName.Text + "-" + txtJobId.Text+"-"+ txtTraceId.Text + "-"+txtTag.Text+"-jobhistory.zip");

                    MessageBox.Show("下载完成");


                }
                else if (detect_result == "2")
                {
                    MessageBox.Show("日志正在生成，请稍后");

                }
                else if (detect_result == "3")
                {
                    MessageBox.Show("Job还没开始或JobServer还没启动，请稍后。");

                }


                else
                {
                    MessageBox.Show("出现未知错误，无法下载!");
                }


        /*}
            else
            {
                MessageBox.Show("正在运行中，无法下载!");
            }
*/

        }
    }
}
