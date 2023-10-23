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

public class PModFrame extends JFrame {
	private JTextField tfModBackNum, tfBackNum, tfName, tfBirth, tfHeight, tfWeight, tfPos;
	private ButtonGroup rdoGroup;
	private JRadioButton LL, LR, RR, RL;
	private JButton btnConfirm, btnMod, btnCancel;
	private String selectedRdoPos;
	Color color = new Color(128, 0, 33);

	public PModFrame() {
		setTitle("선수 수정");
		setSize(300, 480);
		setLocation(850, 250);
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

		JLabel labelPanelName = new JLabel("선 수 수 정");
		labelPanelName.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		labelPanelName.setForeground(color);
		labelPanelName.setBounds(65, 0, 790, 50);
		panel.add(labelPanelName);

		JLabel labelModExplain = new JLabel("수정할 선수의 등 번호을 입력하세요.");
		labelModExplain.setBounds(10, 65, 280, 25);
		panel.add(labelModExplain);

		JLabel labelModName = new JLabel("등 번호");
		labelModName.setBounds(10, 95, 50, 25);
		panel.add(labelModName);

		tfModBackNum = new JTextField(20);
		tfModBackNum.setBounds(70, 95, 50, 25);
		panel.add(tfModBackNum);

		btnConfirm = new JButton("확인");
		btnConfirm.setBounds(135, 95, 60, 25);
		btnConfirm.setBackground(color);
		btnConfirm.setForeground(Color.white);
		panel.add(btnConfirm);
		btnConfirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PlayerDAO playerDAO = new PlayerDAO();
				if (playerDAO.duplicateCheck(Integer.parseInt(tfModBackNum.getText()))) {
					PlayerDTO playerDTO = playerDAO.confirmPlayer(Integer.parseInt(tfModBackNum.getText()));
					JOptionPane.showMessageDialog(null, "확인되었습니다.");
					tfBackNum.setText(Integer.toString(playerDTO.getPlayer_backnumber()));
					tfName.setText(playerDTO.getPlayer_name());
					tfBirth.setText(Integer.toString(playerDTO.getPlayer_age()));
					tfHeight.setText(Integer.toString(playerDTO.getPlayer_height()));
					tfWeight.setText(Integer.toString(playerDTO.getPlayer_weight()));
					tfPos.setText(playerDTO.getPlayer_position());

					btnMod.setEnabled(true);
				} else {
					JOptionPane.showMessageDialog(null, "해당 등 번호의 선수가 없습니다. 다시 시도해 주세요.", "실패",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		JLabel labelLine = new JLabel(new ImageIcon("Images/line.png"));
		labelLine.setBounds(10, 125, 275, 25);
		panel.add(labelLine);

		JLabel labelBackNum = new JLabel("등 번호");
		labelBackNum.setBounds(10, 155, 50, 25);
		panel.add(labelBackNum);

		tfBackNum = new JTextField(20);
		tfBackNum.setBounds(70, 155, 50, 25);
		tfBackNum.setEnabled(false);
		panel.add(tfBackNum);

		JLabel labelThrowHit = new JLabel("선수투/타");
		labelThrowHit.setBounds(10, 185, 70, 25);
		panel.add(labelThrowHit);

		JRadioButton LL = new JRadioButton("LL");
		LL.setSelected(true);
		LL.setBounds(80, 185, 50, 25);
		JRadioButton LR = new JRadioButton("LR");
		LR.setBounds(130, 185, 50, 25);
		JRadioButton RR = new JRadioButton("RR");
		RR.setBounds(180, 185, 50, 25);
		JRadioButton RL = new JRadioButton("RL");
		RL.setBounds(230, 185, 50, 25);
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
		labelName.setBounds(10, 215, 30, 25);
		panel.add(labelName);

		tfName = new JTextField(20);
		tfName.setBounds(50, 215, 180, 25);
		panel.add(tfName);

		JLabel labelBirth = new JLabel("생년월일");
		labelBirth.setBounds(10, 245, 60, 25);
		panel.add(labelBirth);

		tfBirth = new JTextField(20);
		tfBirth.setBounds(80, 245, 100, 25);
		panel.add(tfBirth);

		JLabel labelBirthEx = new JLabel("EX) YYYYMMDD");
		labelBirthEx.setBounds(190, 245, 100, 25);
		panel.add(labelBirthEx);

		JLabel labelHeight = new JLabel("키");
		labelHeight.setBounds(10, 275, 30, 25);
		panel.add(labelHeight);

		tfHeight = new JTextField(20);
		tfHeight.setBounds(50, 275, 60, 25);
		panel.add(tfHeight);

		JLabel labelWeight = new JLabel("몸무게");
		labelWeight.setBounds(10, 305, 40, 25);
		panel.add(labelWeight);

		tfWeight = new JTextField(20);
		tfWeight.setBounds(60, 305, 60, 25);
		panel.add(tfWeight);

		JLabel labelPos = new JLabel("포지션");
		labelPos.setBounds(10, 335, 50, 25);
		panel.add(labelPos);

		JLabel labelPos1 = new JLabel("(  P  /  C  /  1B  /  2B  /  3B  /  SS  /  RF  /  CF  /  LF  )");
		labelPos1.setBounds(10, 350, 600, 25);
		panel.add(labelPos1);

		tfPos = new JTextField(100);
		tfPos.setBounds(10, 380, 60, 25);
		panel.add(tfPos);

		btnMod = new JButton("선수 수정");
		btnMod.setBounds(70, 410, 100, 20);
		btnMod.setBackground(color);
		btnMod.setForeground(Color.white);
		btnMod.setEnabled(false);
		panel.add(btnMod);
		btnMod.addActionListener(new ActionListener() {
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
					if (playerDAO.updatePlayer(playerDTO)) {
						JOptionPane.showMessageDialog(null, "선수 수정이 완료되었습니다.");
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, "선수 수정 실패, 다시 시도해 주세요.", "실패", JOptionPane.ERROR_MESSAGE);
						dispose();
					}
				}
			}
		});

		btnCancel = new JButton("취소");
		btnCancel.setBounds(180, 410, 100, 20);
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
