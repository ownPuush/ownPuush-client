package it.ksuploader.dialogs;

import it.ksuploader.main.Main;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

@SuppressWarnings("serial")
public class SettingsDialog extends JDialog {
	private final JTextField ftpAddr;
	private final JTextField ftpPort;
	private final JTextField ftpDir;
	private final JTextField ftpWeburl;
	private final JTextField ftpUser;
	private final JTextField ftpPassw;
	private final JTextField srvAddr;
	private final JTextField srvPassw;
	private final JTextField srvPort;
	private final JCheckBox ftpEnabled;
	private final JCheckBox saveEnabled;
    private final JCheckBox startUpEnabled;
	private JFileChooser saveDir;

	public SettingsDialog() {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException ex) {
			Main.myLog(ex.toString());
		}
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Settings");
		setBounds(100, 100, 480, 300);
		setResizable(false);
		getContentPane().setLayout(null);

		ftpEnabled = new JCheckBox("FTP Enabled?");
		ftpEnabled.setBounds(6, 7, 130, 23);

		ftpEnabled.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				update();
			}
		});

		getContentPane().add(ftpEnabled);
		{
			JPanel panel = new JPanel();
			panel.setBounds(0, 37, 474, 234);
			getContentPane().add(panel);
			panel.setLayout(null);
			{
				JLabel labelFtpUser = new JLabel("FTP User");
				labelFtpUser.setBounds(10, 111, 101, 14);
				panel.add(labelFtpUser);
			}
			{
				JLabel labelFtpAddr = new JLabel("FTP Address");
				labelFtpAddr.setBounds(10, 11, 101, 14);
				panel.add(labelFtpAddr);
			}
			{
				JLabel labelFtpDir = new JLabel("FTP Directory");
				labelFtpDir.setBounds(10, 61, 101, 14);
				panel.add(labelFtpDir);
			}
			{
				JLabel labelFtpWeburl = new JLabel("FTP Weburl");
				labelFtpWeburl.setBounds(10, 86, 101, 14);
				panel.add(labelFtpWeburl);
			}
			{
				JLabel labelFtpPort = new JLabel("FTP Port");
				labelFtpPort.setBounds(10, 36, 101, 14);
				panel.add(labelFtpPort);
			}
			{
				JLabel labelFtpPassw = new JLabel("FTP Password");
				labelFtpPassw.setBounds(10, 136, 101, 14);
				panel.add(labelFtpPassw);
			}

			ftpAddr = new JTextField();
			ftpAddr.setBounds(100, 8, 127, 20);
			panel.add(ftpAddr);
			ftpAddr.setColumns(10);

			ftpPort = new JTextField();
			ftpPort.setBounds(100, 33, 127, 20);
			panel.add(ftpPort);
			ftpPort.setColumns(10);

			ftpDir = new JTextField();
			ftpDir.setBounds(100, 58, 127, 20);
			panel.add(ftpDir);
			ftpDir.setColumns(10);

			ftpWeburl = new JTextField();
			ftpWeburl.setBounds(100, 83, 127, 20);
			panel.add(ftpWeburl);
			ftpWeburl.setColumns(10);

			ftpUser = new JTextField();
			ftpUser.setBounds(100, 108, 127, 20);
			panel.add(ftpUser);
			ftpUser.setColumns(10);

			ftpPassw = new JTextField();
			ftpPassw.setBounds(100, 133, 127, 20);
			panel.add(ftpPassw);
			ftpPassw.setColumns(10);

			JLabel labelSrvSrv = new JLabel("Server Address");
			labelSrvSrv.setBounds(237, 11, 107, 14);
			panel.add(labelSrvSrv);

			JLabel labelSrvPassw = new JLabel("Server Password");
			labelSrvPassw.setBounds(237, 36, 107, 14);
			panel.add(labelSrvPassw);

			JLabel labelSrvPort = new JLabel("Server Port");
			labelSrvPort.setBounds(237, 61, 107, 14);
			panel.add(labelSrvPort);

			srvAddr = new JTextField();
			srvAddr.setBounds(327, 8, 127, 20);
			panel.add(srvAddr);
			srvAddr.setColumns(10);

			srvPassw = new JTextField();
			srvPassw.setBounds(327, 33, 127, 20);
			panel.add(srvPassw);
			srvPassw.setColumns(10);

			srvPort = new JTextField();
			srvPort.setBounds(327, 58, 127, 20);
			panel.add(srvPort);
			srvPort.setColumns(10);
			{
				JButton okButton = new JButton("OK");
				okButton.setBounds(324, 200, 65, 23);
				panel.add(okButton);
				okButton.setActionCommand("OK");
				getRootPane().setDefaultButton(okButton);
				{
					JButton cancelButton = new JButton("Cancel");
					cancelButton.setBounds(399, 200, 65, 23);
					panel.add(cancelButton);
					cancelButton.setActionCommand("Cancel");

					cancelButton.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							setVisible(false);
						}
					});
				}

				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Main.config.storeNewConfig(ftpWeburl.getText(), ftpDir.getText(), ftpPort.getText(),
								ftpPassw.getText(), ftpUser.getText(), ftpAddr.getText(), ftpEnabled.isSelected() + "",
								srvPassw.getText(), srvPort.getText(), srvAddr.getText(),
								saveEnabled.isSelected() + "", saveDir.getSelectedFile().getPath());
						setVisible(false);
					}
				});
			}
		}

		saveEnabled = new JCheckBox("Save a local copy of images");
		saveEnabled.setBounds(136, 7, 200, 23);
		saveEnabled.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (saveEnabled.isSelected()) {
					saveDir.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					if (saveDir.showOpenDialog(null) != JFileChooser.APPROVE_OPTION)
						saveDir.setSelectedFile(new File("."));
				} else {
					saveDir.setSelectedFile(new File("."));
				}
			}
		});
		getContentPane().add(saveEnabled);
		
		startUpEnabled = new JCheckBox("Open at startup");
		startUpEnabled.setBounds(338, 7, 130, 23);
		getContentPane().add(startUpEnabled);
	}

	public void loadCurrentConfig() {
		try {
			this.ftpAddr.setText(Main.config.getFtpAddr());
			this.ftpDir.setText(Main.config.getFtpDir());
			this.ftpEnabled.setSelected(Main.config.getFtpEnabled());
			this.ftpPassw.setText(Main.config.getFtpPass());
			this.ftpPort.setText(Main.config.getFtpPort() + "");
			this.ftpUser.setText(Main.config.getFtpUser());
			this.ftpWeburl.setText(Main.config.getFtpWebUrl());
			this.srvAddr.setText(Main.config.getIp());
			this.srvPassw.setText(Main.config.getPass());
			this.srvPort.setText(Main.config.getPort() + "");
			this.saveEnabled.setSelected(Main.config.isSaveEnabled());
			this.saveDir = new JFileChooser();
			this.saveDir.setSelectedFile(new File(Main.config.getSaveDir()));
		} catch (Exception e) {
			e.printStackTrace();
			new NotificationDialog().show("Config error", "Error during the config loading");
			Main.myErr(Arrays.toString(e.getStackTrace()).replace(",", "\n"));
		}

		update();
	}

	private void update() {
		if (!this.ftpEnabled.isSelected()) {
			this.ftpAddr.setEnabled(false);
			this.ftpDir.setEnabled(false);
			this.ftpPassw.setEnabled(false);
			this.ftpPort.setEnabled(false);
			this.ftpUser.setEnabled(false);
			this.ftpWeburl.setEnabled(false);

			this.srvAddr.setEnabled(true);
			this.srvPassw.setEnabled(true);
			this.srvPort.setEnabled(true);

		} else {
			this.ftpAddr.setEnabled(true);
			this.ftpDir.setEnabled(true);
			this.ftpPassw.setEnabled(true);
			this.ftpPort.setEnabled(true);
			this.ftpUser.setEnabled(true);
			this.ftpWeburl.setEnabled(true);

			this.srvAddr.setEnabled(false);
			this.srvPassw.setEnabled(false);
			this.srvPort.setEnabled(false);
		}
	}

}
