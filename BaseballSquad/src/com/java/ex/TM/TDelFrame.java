package com.java.ex.TM;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class TDelFrame extends JFrame {
	TacticsDAO tacticsDAO = new TacticsDAO();
	
	private JButton btnDel, btnExit;
	private JComboBox<String> cbTactics;
	
	Color color = new Color(128, 0, 33);
	
	public TDelFrame() {
		setTitle("전술 삭제");
		setSize(360, 130);
		setResizable(false);
		setLocation(800, 400);
		
		JPanel panel = new JPanel();
		tDelPanel(panel);
		
		add(panel);

		setVisible(true);
	}
	
	public void tDelPanel(JPanel panel) {
		panel.setLayout(null);
		panel.setBackground(Color.white);
		
		cbTactics = new JComboBox<String>(tacticsDAO.getTacticsName("del").toArray(new String[tacticsDAO.getTacticsName("del").size()]));
		cbTactics.setBounds(30, 25, 300, 25);
		cbTactics.setBackground(Color.white);
		panel.add(cbTactics);
		
		btnDel = new JButton("삭제");
		btnDel.setBounds(195, 65, 70, 25);
		btnDel.setBackground(color);
		btnDel.setForeground(Color.white);
		panel.add(btnDel);
		btnDel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(cbTactics.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(null, "삭제할 전술을 선택해주세요.", "실패", JOptionPane.ERROR_MESSAGE);
				} else {
					int result = JOptionPane.showConfirmDialog(null, "삭제하시겠습니까?", "삭제", JOptionPane.OK_CANCEL_OPTION);
					if(result == 0) {
						if(tacticsDAO.delTactics(cbTactics.getSelectedItem().toString())) {
							JOptionPane.showMessageDialog(null, "삭제되었습니다.");
							dispose();
						} else {
							JOptionPane.showMessageDialog(null, "삭제 실패, 다시 시도해 주세요.", "실패", JOptionPane.ERROR_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(null, "취소되었습니다.");
					}
				}
			}
		});
		
		btnExit = new JButton("취소");
		btnExit.setBounds(275, 65, 70, 25);
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
