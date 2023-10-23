package com.java.ex.Login; //회원가입 Form

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class RegisterForm extends JFrame { //extends=외부 객체를 사용하는 키워드 / JFrame의 매서드를 사용
	private JTextField tfUserId, tfUserPw, tfUserPw2, tfUserName, tfUserBirth, tfUserEmail, tfUserPhone;
	private JButton btnDuplicateCheck, btnRegister, btnCancel;
	Color color = new Color(128, 0, 33);

	public RegisterForm() {
		setTitle("회원가입");
		setSize(390, 270);
		setResizable(false);
		setLocation(800, 200);

		JPanel panel = new JPanel();
		RegisterPanel(panel);

		add(panel);

		setVisible(true);
	}

	public void RegisterPanel(JPanel panel) {
		panel.setLayout(null);
		panel.setBackground(Color.white);

		JLabel labelUserId = new JLabel("아이디");
		labelUserId.setBounds(10, 10, 40, 25);
		labelUserId.setForeground(color);
		panel.add(labelUserId);

		tfUserId = new JTextField(20);
		tfUserId.setBounds(60, 10, 200, 25);
		panel.add(tfUserId);

		btnDuplicateCheck = new JButton("중복 확인");
		btnDuplicateCheck.setBounds(270, 10, 100, 25);
		btnDuplicateCheck.setBackground(color);
		btnDuplicateCheck.setForeground(Color.white);
		panel.add(btnDuplicateCheck);
		btnDuplicateCheck.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				UserDAO userDao = new UserDAO();
				if (userDao.duplicateCheck(tfUserId.getText())) {
					JOptionPane.showMessageDialog(null, "이미 존재하는 아이디입니다.", "중복", JOptionPane.ERROR_MESSAGE);
					btnRegister.setEnabled(false);
				} else if (tfUserId.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "아이디를 입력해주세요.", "중복", JOptionPane.ERROR_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "사용 가능한 아이디입니다.");
					tfUserId.setEnabled(false);
					btnDuplicateCheck.setEnabled(false);
					btnRegister.setEnabled(true);
				}
			}
		});

		JLabel labelUserPw = new JLabel("비밀번호");
		labelUserPw.setBounds(10, 40, 60, 25);
		labelUserPw.setForeground(color);
		panel.add(labelUserPw);

		tfUserPw = new JTextField(20);
		tfUserPw.setBounds(70, 40, 200, 25);
		panel.add(tfUserPw);

		JLabel labelUserName = new JLabel("이름");
		labelUserName.setBounds(10, 70, 30, 25);
		labelUserName.setForeground(color);
		panel.add(labelUserName);

		tfUserName = new JTextField(20);
		tfUserName.setBounds(45, 70, 100, 25);
		panel.add(tfUserName);

		JLabel labelUserBirth = new JLabel("생년월일");
		labelUserBirth.setBounds(10, 100, 60, 25);
		labelUserBirth.setForeground(color);
		panel.add(labelUserBirth);

		tfUserBirth = new JTextField(20);
		tfUserBirth.setBounds(70, 100, 200, 25);
		panel.add(tfUserBirth);

		JLabel labelUserEmail = new JLabel("이메일");
		labelUserEmail.setBounds(10, 130, 40, 25);
		labelUserEmail.setForeground(color);
		panel.add(labelUserEmail);

		tfUserEmail = new JTextField(20);
		tfUserEmail.setBounds(60, 130, 300, 25);
		panel.add(tfUserEmail);

		JLabel labelUserPhone = new JLabel("연락처");
		labelUserPhone.setBounds(10, 160, 50, 25);
		labelUserPhone.setForeground(color);
		panel.add(labelUserPhone);

		tfUserPhone = new JTextField(20);
		tfUserPhone.setBounds(60, 160, 180, 25);
		panel.add(tfUserPhone);

		btnCancel = new JButton("취소");
		btnCancel.setBounds(160, 210, 100, 25);
		btnCancel.setBackground(color);
		btnCancel.setForeground(Color.white);
		panel.add(btnCancel);
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		btnRegister = new JButton("가입");
		btnRegister.setBounds(270, 210, 100, 25);
		btnRegister.setBackground(color);
		btnRegister.setForeground(Color.white);
		panel.add(btnRegister);
		btnRegister.setEnabled(false);
		btnRegister.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				UserDTO userDto = new UserDTO();
				UserDAO userDao = new UserDAO();

				userDto.setId(tfUserId.getText());
				userDto.setPw(tfUserPw.getText());
				userDto.setName(tfUserName.getText());
				userDto.setBirth(tfUserBirth.getText());
				userDto.setPhone(tfUserPhone.getText());
				userDto.setEmail(tfUserEmail.getText());

				if (tfUserPw.getText().equals("") || tfUserName.getText().equals("") || tfUserBirth.equals("")
						|| tfUserPhone.getText().equals("") || tfUserEmail.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "입력하지않은 정보가 있습니다, 확인 후 다시 시도해주세요.", "실패",
							JOptionPane.ERROR_MESSAGE);
				} else {
					if (userDao.register(userDto)) {
						JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다.");
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, "회원가입 실패, 다시 시도해 주세요.", "실패", JOptionPane.ERROR_MESSAGE);
						dispose();
					}
				}
			}
		});
	}
}
