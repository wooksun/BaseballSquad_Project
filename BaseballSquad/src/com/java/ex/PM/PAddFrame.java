package com.java.ex.PM;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class PAddFrame extends JFrame {
	private JTextField tfBackNum, tfName, tfBirth, tfHeight, tfWeight, tfPos;
	private ButtonGroup rdoGroup;
	private JRadioButton LL, LR, RR, RL;
	private JButton btnDuplicateCheck, btnAdd, btnCancel;
	private String selectedRdoPos;
	Color color = new Color(128, 0, 33);

	public PAddFrame() {
		setTitle("선수추가");
		setSize(300, 415);
		setLocation(850, 230);
		setResizable(false);

		JPanel panel = new JPanel();
		PAddPanel(panel);

		add(panel);

		setVisible(true);
	}

	public void PAddPanel(JPanel panel) {
		panel.setLayout(null);
		panel.setBackground(Color.white);

		JLabel label_logo = new JLabel(new ImageIcon("Images/everton_logo_small.png"));
		label_logo.setBounds(5, 5, 50, 50);
		panel.add(label_logo);

		JLabel labelPanelName = new JLabel("선 수 추 가");
		labelPanelName.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		labelPanelName.setForeground(color);
		labelPanelName.setBounds(65, 0, 790, 50);
		panel.add(labelPanelName);

		JLabel labelBackNum = new JLabel("등 번호");
		labelBackNum.setBounds(10, 65, 50, 25);
		panel.add(labelBackNum);

		tfBackNum = new JTextField(20);
		tfBackNum.setBounds(70, 65, 50, 25);
		panel.add(tfBackNum);

		btnDuplicateCheck = new JButton("중복 확인");
		btnDuplicateCheck.setBounds(140, 65, 90, 25);
		btnDuplicateCheck.setBackground(color);
		btnDuplicateCheck.setForeground(Color.white);
		panel.add(btnDuplicateCheck);
		btnDuplicateCheck.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PlayerDAO playerDAO = new PlayerDAO();
				if (playerDAO.duplicateCheck(Integer.parseInt(tfBackNum.getText()))) {
					JOptionPane.showMessageDialog(null, "사용중인 등 번호입니다.", "중복", JOptionPane.ERROR_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "사용 가능한 등 번호입니다.");
					tfBackNum.setEnabled(false);
					btnDuplicateCheck.setEnabled(false);
					btnAdd.setEnabled(true);
				}
			}
		});

		JLabel labelThrowHit = new JLabel("선수투/타");
		labelThrowHit.setBounds(10, 95, 70, 25);
		panel.add(labelThrowHit);

		JRadioButton LL = new JRadioButton("LL");
		LL.setSelected(true);
		LL.setBounds(80, 95, 50, 25);
		JRadioButton LR = new JRadioButton("LR");
		LR.setBounds(130, 95, 50, 25);
		JRadioButton RR = new JRadioButton("RR");
		RR.setBounds(180, 95, 50, 25);
		JRadioButton RL = new JRadioButton("RL");
		RL.setBounds(230, 95, 50, 25);
		rdoGroup = new ButtonGroup();
		rdoGroup.add(LL);
		rdoGroup.add(LR);
		rdoGroup.add(RR);
		rdoGroup.add(RL);
		panel.add(LL);
		panel.add(LR);
		panel.add(RR);
		panel.add(RL);

		JLabel labelName = new JLabel("이름");
		labelName.setBounds(10, 125, 30, 25);
		panel.add(labelName);

		tfName = new JTextField(20);
		tfName.setBounds(50, 125, 180, 25);
		panel.add(tfName);

		JLabel labelBirth = new JLabel("생년월일");
		labelBirth.setBounds(10, 155, 60, 25);
		panel.add(labelBirth);

		tfBirth = new JTextField(20);
		tfBirth.setBounds(80, 155, 100, 25);
		panel.add(tfBirth);

		JLabel labelBirthEx = new JLabel("EX) YYYYMMDD");
		labelBirthEx.setBounds(190, 155, 100, 25);
		panel.add(labelBirthEx);

		JLabel labelHeight = new JLabel("키");
		labelHeight.setBounds(10, 185, 20, 25);
		panel.add(labelHeight);

		tfHeight = new JTextField(20);
		tfHeight.setBounds(50, 185, 100, 25);
		panel.add(tfHeight);

		JLabel labelWeight = new JLabel("몸무게");
		labelWeight.setBounds(10, 215, 40, 25);
		panel.add(labelWeight);

		tfWeight = new JTextField(20);
		tfWeight.setBounds(60, 215, 60, 25);
		panel.add(tfWeight);

		JLabel labelPos = new JLabel("포지션");
		labelPos.setBounds(10, 245, 50, 25);
		panel.add(labelPos);

		JLabel labelPos1 = new JLabel("(  P  /  C  /  1B  /  2B  /  3B  /  SS  /  RF  /  CF  /  LF  )");
		labelPos1.setBounds(10, 260, 600, 25);
		panel.add(labelPos1);

		tfPos = new JTextField(20);
		tfPos.setBounds(10, 290, 60, 25);
		panel.add(tfPos);

		btnAdd = new JButton("선수 추가");
		btnAdd.setBounds(70, 345, 100, 30);
		btnAdd.setBackground(color);
		btnAdd.setForeground(Color.white);
		btnAdd.setEnabled(false);
		panel.add(btnAdd);
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PlayerDAO playerDAO = new PlayerDAO();
				PlayerDTO playerDTO = new PlayerDTO();

				playerDTO.setPlayer_backnumber(Integer.parseInt(tfBackNum.getText()));
				getSelectedRdoPos();
				playerDTO.setPlayer_pitchingandhitting(selectedRdoPos);
				playerDTO.setPlayer_name(tfName.getText());
				playerDTO.setPlayer_age(Integer.parseInt(tfBirth.getText()));
				playerDTO.setPlayer_height(Integer.parseInt(tfHeight.getText()));
				playerDTO.setPlayer_weight(Integer.parseInt(tfWeight.getText()));
				playerDTO.setPlayer_position(tfPos.getText());

				if (tfName.getText().equals("") || tfBirth.getText().equals("") || tfHeight.getText().equals("")
						|| tfWeight.equals("") || tfPos.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "입력하지않은 정보가 있습니다, 확인 후 다시 시도해주세요.", "실패",
							JOptionPane.ERROR_MESSAGE);
				} else {
					if (playerDAO.addPlayer(playerDTO)) {
						JOptionPane.showMessageDialog(null, "선수 추가가 완료되었습니다.");
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, "선수 추가 실패, 다시 시도해 주세요.", "실패", JOptionPane.ERROR_MESSAGE);
						dispose();
					}
				}
			}
		});

		btnCancel = new JButton("취소");
		btnCancel.setBounds(180, 345, 100, 30);
		btnCancel.setBackground(color);
		btnCancel.setForeground(Color.white);
		panel.add(btnCancel);
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}

	public void getSelectedRdoPos() {
		Enumeration<AbstractButton> enums = rdoGroup.getElements();
		while (enums.hasMoreElements()) {
			AbstractButton ab = enums.nextElement();
			JRadioButton jb = (JRadioButton) ab;
			if (jb.isSelected()) {
				selectedRdoPos = jb.getText();
			}
		}
	}
}