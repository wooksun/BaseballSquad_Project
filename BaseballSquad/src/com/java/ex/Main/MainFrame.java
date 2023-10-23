package com.java.ex.Main;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.java.ex.Login.LoginForm;
import com.java.ex.PM.PMFrame;
import com.java.ex.TM.TMFrame;
import com.java.ex.User.UserFrame;



public class MainFrame extends JFrame {
	private JButton btnTM;
	private JButton btnPM;
	private JButton btnUser;
	private JButton btnExit;
	private JTextArea taManagerInfo;
	private JTextArea taPOTS;
	
	Color color = new Color(128, 0, 33);
	
	public MainFrame() {
		setTitle("Baseball Squad");
		setSize(400, 480);
		setResizable(false);
		setLocation(800, 100);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		mainPanel(panel);
		
		add(panel);

		setVisible(true);
	}
	public void mainPanel(JPanel panel) {
		panel.setLayout(null);
		panel.setBackground(Color.white);
		
		ImageIcon iconEvertonLogo = new ImageIcon("Image/kiwoom.png");
		JLabel label_logo = new JLabel(iconEvertonLogo);
		label_logo.setBounds(10, 10, 365, 326);
		panel.add(label_logo);
		
		
//		btnTM = new JButton("로그인");
//		btnTM.setBounds(10, 350, 120, 30);
//		btnTM.setBackground(color);
//		btnTM.setForeground(Color.white);
//		panel.add(btnTM);
//		btnTM.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				LoginForm loginForm = new LoginForm();
//			}
//		});
		
		btnPM = new JButton("선수 관리");
		btnPM.setBounds(10, 350, 175, 30);
		btnPM.setBackground(color);
		btnPM.setForeground(Color.white);
		panel.add(btnPM);
		btnPM.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PMFrame pmFrame = new PMFrame();
			}
		});
		
		btnUser = new JButton("전술 관리");
		btnUser.setBounds(190, 350, 185, 30);
		btnUser.setBackground(color);
		btnUser.setForeground(Color.white);
		panel.add(btnUser);
		btnUser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TMFrame tmFrame = new TMFrame();
			}
		});
		
		btnExit = new JButton("끝내기");
		btnExit.setBounds(10, 390, 365, 30);
		btnExit.setBackground(color);
		btnExit.setForeground(Color.white);
		panel.add(btnExit);
		btnExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}
}