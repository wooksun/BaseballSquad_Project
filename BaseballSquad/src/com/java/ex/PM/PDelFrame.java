package com.java.ex.PM;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class PDelFrame extends JFrame {
	private JTextField tfBackNum;
	
	private JButton btnDel;
	private JButton btnCancel;
	
	Color color = new Color(128, 0, 33);
	
	public PDelFrame() {
		setTitle("선수 삭제");
		setSize(300, 200);
		setLocation(850, 300);
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
		
		JLabel labelPanelName = new JLabel("선 수 삭 제");
		labelPanelName.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		labelPanelName.setForeground(color);
		labelPanelName.setBounds(65, 0, 790, 50);
		panel.add(labelPanelName);
		
		JLabel labelExplain = new JLabel("삭제할 선수의 등 번호를 입력하세요.");
		labelExplain.setBounds(10, 65, 280, 25);
		panel.add(labelExplain);
		
		JLabel labelName = new JLabel("등 번호");
		labelName.setBounds(10, 95, 50, 25);
		panel.add(labelName);
		
		tfBackNum = new JTextField(20);
		tfBackNum.setBounds(70, 95, 50, 25);
		panel.add(tfBackNum);
		
		btnDel = new JButton("선수 삭제");
		btnDel.setBounds(70, 135, 100, 30);
		btnDel.setBackground(color);
		btnDel.setForeground(Color.white);
		panel.add(btnDel);
		btnDel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PlayerDAO playerDAO = new PlayerDAO();
				if(tfBackNum.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "등 번호 입력해주세요.", "선수 삭제", JOptionPane.ERROR_MESSAGE);
				} else {
					int result = JOptionPane.showConfirmDialog(null, "삭제하시겠습니까?", "선수 삭제", JOptionPane.OK_CANCEL_OPTION);
					if(result == 0) {
						if(playerDAO.duplicateCheck(Integer.parseInt(tfBackNum.getText()))) {
							if(playerDAO.deletePlayer(Integer.parseInt(tfBackNum.getText()))) {
								JOptionPane.showMessageDialog(null, "삭제되었습니다.");
								dispose();
							} else {
								JOptionPane.showMessageDialog(null, "삭제 실패, 다시 시도해 주세요.", "선수 삭제", JOptionPane.ERROR_MESSAGE);
							}
						} else {
							JOptionPane.showMessageDialog(null, "해당 등 번호의 선수는 존재하지 않습니다.", "선수 삭제", JOptionPane.ERROR_MESSAGE);
							tfBackNum.setText("");
						}
					} else {
						tfBackNum.setText("");
					}
				}
			}
		});
		
		btnCancel = new JButton("취소");
		btnCancel.setBounds(180, 135, 100, 30);
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
}
