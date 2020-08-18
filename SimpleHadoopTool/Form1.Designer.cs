namespace SimpleHadoopTool
{
    partial class Form1
    {
        /// <summary>
        /// 必需的设计器变量。
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// 清理所有正在使用的资源。
        /// </summary>
        /// <param name="disposing">如果应释放托管资源，为 true；否则为 false。</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows 窗体设计器生成的代码

        /// <summary>
        /// 设计器支持所需的方法 - 不要修改
        /// 使用代码编辑器修改此方法的内容。
        /// </summary>
        private void InitializeComponent()
        {
            this.groupBox1 = new System.Windows.Forms.GroupBox();
            this.btnLoad = new System.Windows.Forms.Button();
            this.txtAppRoot = new System.Windows.Forms.TextBox();
            this.label5 = new System.Windows.Forms.Label();
            this.txtHadoopBin = new System.Windows.Forms.TextBox();
            this.label4 = new System.Windows.Forms.Label();
            this.txtMasterPassword = new System.Windows.Forms.TextBox();
            this.label3 = new System.Windows.Forms.Label();
            this.txtMasterUser = new System.Windows.Forms.TextBox();
            this.label2 = new System.Windows.Forms.Label();
            this.txtMasterHost = new System.Windows.Forms.TextBox();
            this.label1 = new System.Windows.Forms.Label();
            this.groupBox2 = new System.Windows.Forms.GroupBox();
            this.cbAutomatic = new System.Windows.Forms.CheckBox();
            this.button1 = new System.Windows.Forms.Button();
            this.txtInputs = new System.Windows.Forms.TextBox();
            this.label14 = new System.Windows.Forms.Label();
            this.label13 = new System.Windows.Forms.Label();
            this.numDelay = new System.Windows.Forms.NumericUpDown();
            this.label12 = new System.Windows.Forms.Label();
            this.txtJobName = new System.Windows.Forms.TextBox();
            this.label7 = new System.Windows.Forms.Label();
            this.txtMainClass = new System.Windows.Forms.TextBox();
            this.label11 = new System.Windows.Forms.Label();
            this.btnSaveAsPath = new System.Windows.Forms.Button();
            this.txtDownloadPath = new System.Windows.Forms.TextBox();
            this.label10 = new System.Windows.Forms.Label();
            this.txtArgs = new System.Windows.Forms.TextBox();
            this.txtJobDesc = new System.Windows.Forms.TextBox();
            this.label9 = new System.Windows.Forms.Label();
            this.label8 = new System.Windows.Forms.Label();
            this.btnOpenJar = new System.Windows.Forms.Button();
            this.txtJarFile = new System.Windows.Forms.TextBox();
            this.label6 = new System.Windows.Forms.Label();
            this.btnAnalyze = new System.Windows.Forms.Button();
            this.txtInfo = new System.Windows.Forms.Label();
            this.btnReset = new System.Windows.Forms.Button();
            this.label15 = new System.Windows.Forms.Label();
            this.txtTraceId = new System.Windows.Forms.TextBox();
            this.btnJobLog = new System.Windows.Forms.Button();
            this.label16 = new System.Windows.Forms.Label();
            this.txtJobId = new System.Windows.Forms.TextBox();
            this.label17 = new System.Windows.Forms.Label();
            this.txtTag = new System.Windows.Forms.TextBox();
            this.groupBox1.SuspendLayout();
            this.groupBox2.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.numDelay)).BeginInit();
            this.SuspendLayout();
            // 
            // groupBox1
            // 
            this.groupBox1.Controls.Add(this.btnLoad);
            this.groupBox1.Controls.Add(this.txtAppRoot);
            this.groupBox1.Controls.Add(this.label5);
            this.groupBox1.Controls.Add(this.txtHadoopBin);
            this.groupBox1.Controls.Add(this.label4);
            this.groupBox1.Controls.Add(this.txtMasterPassword);
            this.groupBox1.Controls.Add(this.label3);
            this.groupBox1.Controls.Add(this.txtMasterUser);
            this.groupBox1.Controls.Add(this.label2);
            this.groupBox1.Controls.Add(this.txtMasterHost);
            this.groupBox1.Controls.Add(this.label1);
            this.groupBox1.Location = new System.Drawing.Point(48, 23);
            this.groupBox1.Name = "groupBox1";
            this.groupBox1.Size = new System.Drawing.Size(662, 223);
            this.groupBox1.TabIndex = 0;
            this.groupBox1.TabStop = false;
            this.groupBox1.Text = "Hadoop Environment: ";
            // 
            // btnLoad
            // 
            this.btnLoad.Location = new System.Drawing.Point(500, 27);
            this.btnLoad.Name = "btnLoad";
            this.btnLoad.Size = new System.Drawing.Size(139, 62);
            this.btnLoad.TabIndex = 10;
            this.btnLoad.Text = "Load from a project";
            this.btnLoad.UseVisualStyleBackColor = true;
            this.btnLoad.Click += new System.EventHandler(this.btnLoad_Click);
            // 
            // txtAppRoot
            // 
            this.txtAppRoot.Location = new System.Drawing.Point(178, 176);
            this.txtAppRoot.Name = "txtAppRoot";
            this.txtAppRoot.Size = new System.Drawing.Size(240, 28);
            this.txtAppRoot.TabIndex = 9;
            this.txtAppRoot.Text = "/usr/hadoop_apps";
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Location = new System.Drawing.Point(33, 179);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(80, 18);
            this.label5.TabIndex = 8;
            this.label5.Text = "appRoot:";
            // 
            // txtHadoopBin
            // 
            this.txtHadoopBin.Location = new System.Drawing.Point(178, 142);
            this.txtHadoopBin.Name = "txtHadoopBin";
            this.txtHadoopBin.Size = new System.Drawing.Size(240, 28);
            this.txtHadoopBin.TabIndex = 7;
            this.txtHadoopBin.Text = "/usr/hadoop/bin/hadoop";
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(33, 145);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(98, 18);
            this.label4.TabIndex = 6;
            this.label4.Text = "hadoopBin:";
            // 
            // txtMasterPassword
            // 
            this.txtMasterPassword.Location = new System.Drawing.Point(178, 108);
            this.txtMasterPassword.Name = "txtMasterPassword";
            this.txtMasterPassword.PasswordChar = '*';
            this.txtMasterPassword.Size = new System.Drawing.Size(240, 28);
            this.txtMasterPassword.TabIndex = 5;
            this.txtMasterPassword.Text = "Passw0rd";
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(33, 111);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(134, 18);
            this.label3.TabIndex = 4;
            this.label3.Text = "masterPassword";
            // 
            // txtMasterUser
            // 
            this.txtMasterUser.Location = new System.Drawing.Point(178, 74);
            this.txtMasterUser.Name = "txtMasterUser";
            this.txtMasterUser.Size = new System.Drawing.Size(240, 28);
            this.txtMasterUser.TabIndex = 3;
            this.txtMasterUser.Text = "hadoop";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(33, 77);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(107, 18);
            this.label2.TabIndex = 2;
            this.label2.Text = "masterUser:";
            // 
            // txtMasterHost
            // 
            this.txtMasterHost.Location = new System.Drawing.Point(178, 40);
            this.txtMasterHost.Name = "txtMasterHost";
            this.txtMasterHost.Size = new System.Drawing.Size(240, 28);
            this.txtMasterHost.TabIndex = 1;
            this.txtMasterHost.Text = "192.168.159.132";
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(33, 43);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(107, 18);
            this.label1.TabIndex = 0;
            this.label1.Text = "masterHost:";
            // 
            // groupBox2
            // 
            this.groupBox2.Controls.Add(this.cbAutomatic);
            this.groupBox2.Controls.Add(this.button1);
            this.groupBox2.Controls.Add(this.txtInputs);
            this.groupBox2.Controls.Add(this.label14);
            this.groupBox2.Controls.Add(this.label13);
            this.groupBox2.Controls.Add(this.numDelay);
            this.groupBox2.Controls.Add(this.label12);
            this.groupBox2.Controls.Add(this.txtJobName);
            this.groupBox2.Controls.Add(this.label7);
            this.groupBox2.Controls.Add(this.txtMainClass);
            this.groupBox2.Controls.Add(this.label11);
            this.groupBox2.Controls.Add(this.btnSaveAsPath);
            this.groupBox2.Controls.Add(this.txtDownloadPath);
            this.groupBox2.Controls.Add(this.label10);
            this.groupBox2.Controls.Add(this.txtArgs);
            this.groupBox2.Controls.Add(this.txtJobDesc);
            this.groupBox2.Controls.Add(this.label9);
            this.groupBox2.Controls.Add(this.label8);
            this.groupBox2.Controls.Add(this.btnOpenJar);
            this.groupBox2.Controls.Add(this.txtJarFile);
            this.groupBox2.Controls.Add(this.label6);
            this.groupBox2.Location = new System.Drawing.Point(52, 262);
            this.groupBox2.Name = "groupBox2";
            this.groupBox2.Size = new System.Drawing.Size(657, 464);
            this.groupBox2.TabIndex = 1;
            this.groupBox2.TabStop = false;
            this.groupBox2.Text = "Hadoop Task Info: ";
            // 
            // cbAutomatic
            // 
            this.cbAutomatic.AutoSize = true;
            this.cbAutomatic.Location = new System.Drawing.Point(385, 415);
            this.cbAutomatic.Name = "cbAutomatic";
            this.cbAutomatic.Size = new System.Drawing.Size(241, 22);
            this.cbAutomatic.TabIndex = 33;
            this.cbAutomatic.Text = "Use Automatic Detecting";
            this.cbAutomatic.UseVisualStyleBackColor = true;
            // 
            // button1
            // 
            this.button1.Location = new System.Drawing.Point(535, 38);
            this.button1.Name = "button1";
            this.button1.Size = new System.Drawing.Size(100, 28);
            this.button1.TabIndex = 32;
            this.button1.Text = "Open...";
            this.button1.UseVisualStyleBackColor = true;
            this.button1.Click += new System.EventHandler(this.button1_Click);
            // 
            // txtInputs
            // 
            this.txtInputs.Location = new System.Drawing.Point(174, 38);
            this.txtInputs.Name = "txtInputs";
            this.txtInputs.Size = new System.Drawing.Size(342, 28);
            this.txtInputs.TabIndex = 31;
            // 
            // label14
            // 
            this.label14.AutoSize = true;
            this.label14.Location = new System.Drawing.Point(18, 41);
            this.label14.Name = "label14";
            this.label14.Size = new System.Drawing.Size(71, 18);
            this.label14.TabIndex = 30;
            this.label14.Text = "Inpus: ";
            // 
            // label13
            // 
            this.label13.AutoSize = true;
            this.label13.Location = new System.Drawing.Point(319, 415);
            this.label13.Name = "label13";
            this.label13.Size = new System.Drawing.Size(44, 18);
            this.label13.TabIndex = 29;
            this.label13.Text = "secs";
            // 
            // numDelay
            // 
            this.numDelay.Location = new System.Drawing.Point(248, 413);
            this.numDelay.Name = "numDelay";
            this.numDelay.Size = new System.Drawing.Size(56, 28);
            this.numDelay.TabIndex = 28;
            this.numDelay.Value = new decimal(new int[] {
            20,
            0,
            0,
            0});
            // 
            // label12
            // 
            this.label12.AutoSize = true;
            this.label12.Location = new System.Drawing.Point(18, 415);
            this.label12.Name = "label12";
            this.label12.Size = new System.Drawing.Size(224, 18);
            this.label12.TabIndex = 27;
            this.label12.Text = "Delay time to download: ";
            // 
            // txtJobName
            // 
            this.txtJobName.Location = new System.Drawing.Point(174, 124);
            this.txtJobName.Name = "txtJobName";
            this.txtJobName.Size = new System.Drawing.Size(342, 28);
            this.txtJobName.TabIndex = 26;
            this.txtJobName.Text = "MyJob";
            // 
            // label7
            // 
            this.label7.AutoSize = true;
            this.label7.Location = new System.Drawing.Point(18, 124);
            this.label7.Name = "label7";
            this.label7.Size = new System.Drawing.Size(98, 18);
            this.label7.TabIndex = 25;
            this.label7.Text = "Job Name: ";
            // 
            // txtMainClass
            // 
            this.txtMainClass.Location = new System.Drawing.Point(174, 212);
            this.txtMainClass.Name = "txtMainClass";
            this.txtMainClass.Size = new System.Drawing.Size(342, 28);
            this.txtMainClass.TabIndex = 24;
            this.txtMainClass.Text = "cn.edu.bjtu.cdh.examples.wordcount.WordCount";
            // 
            // label11
            // 
            this.label11.AutoSize = true;
            this.label11.Location = new System.Drawing.Point(29, 215);
            this.label11.Name = "label11";
            this.label11.Size = new System.Drawing.Size(107, 18);
            this.label11.TabIndex = 23;
            this.label11.Text = "Main Class:";
            // 
            // btnSaveAsPath
            // 
            this.btnSaveAsPath.Location = new System.Drawing.Point(535, 371);
            this.btnSaveAsPath.Name = "btnSaveAsPath";
            this.btnSaveAsPath.Size = new System.Drawing.Size(100, 30);
            this.btnSaveAsPath.TabIndex = 22;
            this.btnSaveAsPath.Text = "Select...";
            this.btnSaveAsPath.UseVisualStyleBackColor = true;
            this.btnSaveAsPath.Click += new System.EventHandler(this.btnSaveAsPath_Click);
            // 
            // txtDownloadPath
            // 
            this.txtDownloadPath.Location = new System.Drawing.Point(212, 371);
            this.txtDownloadPath.Name = "txtDownloadPath";
            this.txtDownloadPath.Size = new System.Drawing.Size(304, 28);
            this.txtDownloadPath.TabIndex = 21;
            this.txtDownloadPath.Text = "output";
            // 
            // label10
            // 
            this.label10.AutoSize = true;
            this.label10.Location = new System.Drawing.Point(18, 374);
            this.label10.Name = "label10";
            this.label10.Size = new System.Drawing.Size(188, 18);
            this.label10.TabIndex = 20;
            this.label10.Text = "Download Result To: ";
            // 
            // txtArgs
            // 
            this.txtArgs.Location = new System.Drawing.Point(174, 264);
            this.txtArgs.Multiline = true;
            this.txtArgs.Name = "txtArgs";
            this.txtArgs.ScrollBars = System.Windows.Forms.ScrollBars.Vertical;
            this.txtArgs.Size = new System.Drawing.Size(461, 93);
            this.txtArgs.TabIndex = 19;
            this.txtArgs.Text = "/data/cdh/examples/wordcount/input /data/cdh/examples/wordcount/output";
            // 
            // txtJobDesc
            // 
            this.txtJobDesc.Location = new System.Drawing.Point(174, 168);
            this.txtJobDesc.Name = "txtJobDesc";
            this.txtJobDesc.Size = new System.Drawing.Size(240, 28);
            this.txtJobDesc.TabIndex = 18;
            this.txtJobDesc.Text = "This is a hadoop task!";
            // 
            // label9
            // 
            this.label9.AutoSize = true;
            this.label9.Location = new System.Drawing.Point(29, 264);
            this.label9.Name = "label9";
            this.label9.Size = new System.Drawing.Size(53, 18);
            this.label9.TabIndex = 16;
            this.label9.Text = "Args:";
            // 
            // label8
            // 
            this.label8.AutoSize = true;
            this.label8.Location = new System.Drawing.Point(18, 165);
            this.label8.Name = "label8";
            this.label8.Size = new System.Drawing.Size(161, 18);
            this.label8.TabIndex = 15;
            this.label8.Text = "Job Description: ";
            // 
            // btnOpenJar
            // 
            this.btnOpenJar.Location = new System.Drawing.Point(535, 83);
            this.btnOpenJar.Name = "btnOpenJar";
            this.btnOpenJar.Size = new System.Drawing.Size(100, 30);
            this.btnOpenJar.TabIndex = 12;
            this.btnOpenJar.Text = "Select...";
            this.btnOpenJar.UseVisualStyleBackColor = true;
            this.btnOpenJar.Click += new System.EventHandler(this.btnOpenJar_Click);
            // 
            // txtJarFile
            // 
            this.txtJarFile.Location = new System.Drawing.Point(174, 80);
            this.txtJarFile.Name = "txtJarFile";
            this.txtJarFile.Size = new System.Drawing.Size(342, 28);
            this.txtJarFile.TabIndex = 11;
            this.txtJarFile.Text = "HPExamples.jar";
            // 
            // label6
            // 
            this.label6.AutoSize = true;
            this.label6.Location = new System.Drawing.Point(18, 83);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(98, 18);
            this.label6.TabIndex = 10;
            this.label6.Text = "Jar File: ";
            // 
            // btnAnalyze
            // 
            this.btnAnalyze.Location = new System.Drawing.Point(46, 842);
            this.btnAnalyze.Name = "btnAnalyze";
            this.btnAnalyze.Size = new System.Drawing.Size(195, 48);
            this.btnAnalyze.TabIndex = 2;
            this.btnAnalyze.Text = "Analyze";
            this.btnAnalyze.UseVisualStyleBackColor = true;
            this.btnAnalyze.Click += new System.EventHandler(this.btnAnalyze_Click);
            // 
            // txtInfo
            // 
            this.txtInfo.AutoSize = true;
            this.txtInfo.Location = new System.Drawing.Point(342, 896);
            this.txtInfo.Name = "txtInfo";
            this.txtInfo.Size = new System.Drawing.Size(35, 18);
            this.txtInfo.TabIndex = 3;
            this.txtInfo.Tag = "";
            this.txtInfo.Text = "...";
            // 
            // btnReset
            // 
            this.btnReset.Location = new System.Drawing.Point(262, 842);
            this.btnReset.Name = "btnReset";
            this.btnReset.Size = new System.Drawing.Size(164, 48);
            this.btnReset.TabIndex = 4;
            this.btnReset.Text = "Reset";
            this.btnReset.UseVisualStyleBackColor = true;
            this.btnReset.Click += new System.EventHandler(this.btnReset_Click);
            // 
            // label15
            // 
            this.label15.AutoSize = true;
            this.label15.Location = new System.Drawing.Point(52, 742);
            this.label15.Name = "label15";
            this.label15.Size = new System.Drawing.Size(89, 18);
            this.label15.TabIndex = 5;
            this.label15.Text = "Trace_ID:";
            // 
            // txtTraceId
            // 
            this.txtTraceId.Location = new System.Drawing.Point(154, 739);
            this.txtTraceId.Name = "txtTraceId";
            this.txtTraceId.Size = new System.Drawing.Size(393, 28);
            this.txtTraceId.TabIndex = 6;
            // 
            // btnJobLog
            // 
            this.btnJobLog.Location = new System.Drawing.Point(460, 843);
            this.btnJobLog.Name = "btnJobLog";
            this.btnJobLog.Size = new System.Drawing.Size(182, 46);
            this.btnJobLog.TabIndex = 7;
            this.btnJobLog.Text = "getJobLog";
            this.btnJobLog.UseVisualStyleBackColor = true;
            this.btnJobLog.Click += new System.EventHandler(this.btnJobLog_Click);
            // 
            // label16
            // 
            this.label16.AutoSize = true;
            this.label16.Location = new System.Drawing.Point(52, 787);
            this.label16.Name = "label16";
            this.label16.Size = new System.Drawing.Size(71, 18);
            this.label16.TabIndex = 8;
            this.label16.Text = "JobId: ";
            // 
            // txtJobId
            // 
            this.txtJobId.Location = new System.Drawing.Point(154, 787);
            this.txtJobId.Name = "txtJobId";
            this.txtJobId.Size = new System.Drawing.Size(393, 28);
            this.txtJobId.TabIndex = 9;
            // 
            // label17
            // 
            this.label17.AutoSize = true;
            this.label17.Location = new System.Drawing.Point(569, 749);
            this.label17.Name = "label17";
            this.label17.Size = new System.Drawing.Size(53, 18);
            this.label17.TabIndex = 10;
            this.label17.Text = "Tag: ";
            // 
            // txtTag
            // 
            this.txtTag.Location = new System.Drawing.Point(631, 745);
            this.txtTag.Name = "txtTag";
            this.txtTag.Size = new System.Drawing.Size(77, 28);
            this.txtTag.TabIndex = 11;
            this.txtTag.Text = "NoTag";
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(9F, 18F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(759, 923);
            this.Controls.Add(this.txtTag);
            this.Controls.Add(this.label17);
            this.Controls.Add(this.txtJobId);
            this.Controls.Add(this.label16);
            this.Controls.Add(this.btnJobLog);
            this.Controls.Add(this.txtTraceId);
            this.Controls.Add(this.label15);
            this.Controls.Add(this.btnReset);
            this.Controls.Add(this.txtInfo);
            this.Controls.Add(this.btnAnalyze);
            this.Controls.Add(this.groupBox2);
            this.Controls.Add(this.groupBox1);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedSingle;
            this.MaximizeBox = false;
            this.MinimizeBox = false;
            this.Name = "Form1";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "A Simple Hadoop Task Tool using HadoopUserApi";
            this.Load += new System.EventHandler(this.Form1_Load);
            this.groupBox1.ResumeLayout(false);
            this.groupBox1.PerformLayout();
            this.groupBox2.ResumeLayout(false);
            this.groupBox2.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.numDelay)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.GroupBox groupBox1;
        private System.Windows.Forms.TextBox txtAppRoot;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.TextBox txtHadoopBin;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.TextBox txtMasterPassword;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.TextBox txtMasterUser;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.TextBox txtMasterHost;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.GroupBox groupBox2;
        private System.Windows.Forms.Button btnSaveAsPath;
        private System.Windows.Forms.TextBox txtDownloadPath;
        private System.Windows.Forms.Label label10;
        private System.Windows.Forms.TextBox txtArgs;
        private System.Windows.Forms.TextBox txtJobDesc;
        private System.Windows.Forms.Label label9;
        private System.Windows.Forms.Label label8;
        private System.Windows.Forms.Button btnOpenJar;
        private System.Windows.Forms.TextBox txtJarFile;
        private System.Windows.Forms.Label label6;
        private System.Windows.Forms.Button btnAnalyze;
        private System.Windows.Forms.TextBox txtMainClass;
        private System.Windows.Forms.Label label11;
        private System.Windows.Forms.Label txtInfo;
        private System.Windows.Forms.Button btnReset;
        private System.Windows.Forms.TextBox txtJobName;
        private System.Windows.Forms.Label label7;
        private System.Windows.Forms.NumericUpDown numDelay;
        private System.Windows.Forms.Label label12;
        private System.Windows.Forms.Label label13;
        private System.Windows.Forms.Button btnLoad;
        private System.Windows.Forms.TextBox txtInputs;
        private System.Windows.Forms.Label label14;
        private System.Windows.Forms.Button button1;
        private System.Windows.Forms.Label label15;
        private System.Windows.Forms.TextBox txtTraceId;
        private System.Windows.Forms.Button btnJobLog;
        private System.Windows.Forms.Label label16;
        private System.Windows.Forms.TextBox txtJobId;
        private System.Windows.Forms.CheckBox cbAutomatic;
        private System.Windows.Forms.Label label17;
        private System.Windows.Forms.TextBox txtTag;
    }
}

