﻿using KSLUploader.Classes;
using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Windows;

namespace KSLUploader
{
    public partial class App : Application
    {
        private SystemTray ksluTray;
        private KeyListener keyListener;

        protected override void OnStartup(StartupEventArgs e)
        {
            Logger.Set("KSLU Started!");

            //no double instance!
            if(Process.GetProcessesByName(Process.GetCurrentProcess().ProcessName).Length > 1)
            {
                MessageBox.Show("KSLUploader is already running.", "Error");
                Current.Shutdown();
                return;
            }
           
            Current.ShutdownMode = ShutdownMode.OnExplicitShutdown;

            AppConstants.InizializeSettings();
            Utils.CheckRunAtStartup();
            
            keyListener = new KeyListener();
            ksluTray = new SystemTray();
        }
        
    }
}
