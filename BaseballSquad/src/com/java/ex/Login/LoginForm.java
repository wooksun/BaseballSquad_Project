package com.java.ex.Login; //로그인 Form

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.java.ex.Main.Main;
import com.java.ex.Main.MainFrame;

public class LoginForm extends JFrame { //extends=외부 객체를 사용하는 키워드 / JFrame의 매서드를 사용
	private Main main;

	private JButton btnLogin, btnRegister, btnFindId, btnFindPw, btnExit;
	private JTextField tfUserId;
	private JPasswordField tfUserPw;
	Color color = new Color(128, 0, 33);

	public LoginForm() { // 초기화면 설정(생성자)
		setTitle("로그인");
		setSize(345, 185);
		setResizable(false); // 창크기 설정(true면 자유롭게 화면을 늘릴 수 있음)
		setLocation(500, 25); // 창이 화면 어느 위치에 띄울지 설정가는 값
		setDefaultCloseOperation(EXIT_ON_CLOSE); // 창을 닫을 때 완전히 종료시켜주는 값(필수)

		JPanel panel = new JPanel(); // (필수) 도화지같은 개념/없으면 구성한 값들이 보이지않음.
		LoginPanel(panel);

		add(panel); // 만든 패널을 폼 위에 올리는 것.

		setVisible(true); // (필수) 선언하지 않으면 보이지않음.
	}

	public void LoginPanel(JPanel panel) {
		panel.setLayout(null);
		panel.setBackground(Color.white);

		JLabel labelUserId = new JLabel("아이디"); //라벨 설정
		labelUserId.setBounds(40, 10, 40, 25); //라벨 설정
		labelUserId.setForeground(color); //라벨 설정
		panel.add(labelUserId); //라벨을 추가하는 값

		tfUserId = new JTextField(20); //텍스트필드 설정
		tfUserId.setBounds(90, 10, 200, 25); //텍스트필드 설정
		panel.add(tfUserId); //텍스트필드 추가하는 값

		JLabel labelUserPw = new JLabel("비밀번호");
		labelUserPw.setBounds(30, 40, 60, 25);
		panel.add(labelUserPw);
		labelUserPw.setForeground(color);
		tfUserPw = new JPasswordField(20);
		tfUserPw.setBounds(90, 40, 200, 25);
		panel.add(tfUserPw);

		btnLogin = new JButton("로그인");
		btnLogin.setBounds(10, 110, 100, 25);
		btnLogin.setBackground(color);
		btnLogin.setForeground(Color.white);
		panel.add(btnLogin);
		btnLogin.addActionListener(new ActionListener() { //기능
			@Override
			public void actionPerformed(ActionEvent e) {
	            UserDAO userDao = new UserDAO();
	            String name = userDao.login(tfUserId.getText(), tfUserPw.getText());
	            if(tfUserId.getText().equals("") || tfUserPw.getText().equals("")) { //입력되지 않으면 경고창을 띄움 (아래와 같이)
	               JOptionPane.showMessageDialog(null, "입력하지않은 정보가 있습니다, 확인 후 다시 시도해주세요.", "로그인", JOptionPane.ERROR_MESSAGE);
	            } else {
	               if(name != null) {
	                  JOptionPane.showMessageDialog(null, name + "님 환영합니다." );
	                  MainFrame mainframe = new MainFrame();
	                  dispose();
	               }
	               else {
	                  JOptionPane.showMessageDialog(null, "가입하지 않은 아이디이거나, 잘못된 비밀번호입니다.", "로그인", JOptionPane.ERROR_MESSAGE);
	               }
	            }
			}
		});

		btnRegister = new JButton("회원 가입");
		btnRegister.setBounds(10, 80, 100, 25);
		btnRegister.setBackground(color);
		btnRegister.setForeground(Color.white);
		panel.add(btnRegister);
		btnRegister.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
	            RegisterForm registerForm = new RegisterForm();
			}
		});

		btnFindId = new JButton("ID 찾기");
		btnFindId.setBounds(120, 80, 100, 25);
		btnFindId.setBackground(color);
		btnFindId.setForeground(Color.white);
		panel.add(btnFindId);
		btnFindId.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
	            FindIdForm findIdForm = new FindIdForm();
			}
		});
		btnFindPw = new JButton("PW 찾기");
		btnFindPw.setBounds(230, 80, 100, 25);
		btnFindPw.setBackground(color);
		btnFindPw.setForeground(Color.white);
		panel.add(btnFindPw);
		btnFindPw.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
	            FindPwForm findPwForm = new FindPwForm();
			}
		});

		btnExit = new JButton("끝내기");
		btnExit.setBounds(230, 110, 100, 25);
		btnExit.setBackground(color);
		btnExit.setForeground(Color.white);
		panel.add(btnExit);
		btnExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
}
