package com.java.ex.TM;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TMFrame extends JFrame {
	TacticsDAO tacticsDAO = new TacticsDAO();
	TacticsDTO tacticsDTO = new TacticsDTO();

	private JButton btnLoadTactics, btnDupCheck, btnAddTactics, btnModTactics, btnDelTactics, btnReset, btnExit;
	private JTextField tfTacticsName, tfT_TacticsComment;
	private JComboBox<String> cbTactics;

	private JLabel[] labelPos1 = new JLabel[9];
	private JLabel[] labelPos2 = new JLabel[3];
	private JLabel[] labelPos3 = new JLabel[5];
	private JLabel[] labelPos4 = new JLabel[2];
	private JLabel[] labelPos5 = new JLabel[2];
	private JComboBox[] cbPlayer = new JComboBox[9];
	private JComboBox[] cbChange = new JComboBox[3];
	private JComboBox[] cbPitcher = new JComboBox[5];
	private JComboBox[] cbMiddlePitcher = new JComboBox[2];
	private JComboBox[] cbLastPitcher = new JComboBox[2];

	private JComboBox cbFormation;
	private String strPos[] = { "DH", "C", "1B", "2B", "3B", "SS", "LF", "CF", "RF" };
	private String strChange[] = { "C", "SS", "CF" };
	private String strPitcher[] = { "SP", "SP", "SP", "SP", "SP" };
	private String strMiddlePitcher[] = { "RP", "RP" };
	private String strLastPitcher[] = { "CP", "CP" };

	Color color = new Color(128, 0, 33);

	public TMFrame() {
		setTitle("전술 관리");
		setSize(1000, 629);
		setResizable(false);
		setLocation(500, 200);

		JPanel panel = new JPanel();
		tmPanel(panel);

		add(panel);

		setVisible(true);
	}

	public void tmPanel(JPanel panel) {
		panel.setLayout(null);
		panel.setBackground(Color.white);

		JLabel label_logo = new JLabel(new ImageIcon("Image/logo.png"));
		label_logo.setBounds(5, 5, 50, 50);
		panel.add(label_logo);

		JLabel labelPanelName = new JLabel("전 술 관 리");
		labelPanelName.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		labelPanelName.setForeground(color);
		labelPanelName.setBounds(65, 0, 200, 50);
		panel.add(labelPanelName);

		cbTactics = new JComboBox<String>(
				tacticsDAO.getTacticsName("load").toArray(new String[tacticsDAO.getTacticsName("load").size()]));
		cbTactics.setBounds(245, 15, 200, 25);
		cbTactics.setBackground(Color.white);

		panel.add(cbTactics);

		btnLoadTactics = new JButton("전술 불러오기"); //저장된 전술 불러오기
		btnLoadTactics.setBounds(455, 15, 140, 25); //버튼 크기, 위치 설정
		btnLoadTactics.setBackground(color); //버튼배경 색
		btnLoadTactics.setForeground(Color.white); //버튼 글자 색
		panel.add(btnLoadTactics); //버튼을 패널에 추가
		btnLoadTactics.addActionListener(new ActionListener() { //btnLoadTactics에 ActionListener를 추가
			@Override
			public void actionPerformed(ActionEvent e) { //ActionListener의 본체
				tacticsDTO = tacticsDAO.loadTactics(cbTactics.getSelectedItem().toString()); //DB에서 데이터를 가져와 DTO에 넣는 작업
				if (tacticsDTO != null) { //DTO가 비어있지 않으면, 아래 내용 실행
					tfTacticsName.setEnabled(false); //전술 이름 textfield (수정이 불가능 하도록 함 / 전술이름이 기본키이기 때문)
					btnDupCheck.setEnabled(false);  //중복 버튼 비활성화 (이름을 비활성화 했기 때문에 중복확인을 할 필요X)
					btnModTactics.setEnabled(true);  //전술 수정 버튼 활성화 (수정할 수 있도록 전술 수정 버튼 활성화)
					tfTacticsName.setText(tacticsDTO.getTactics_name()); //전술 이름에 DB에서 가져온 tacticsname을 넣어준다.
					cbPlayer[0].setSelectedItem(tacticsDTO.getDh());  //DB에서 가져온 데이터를 바탕으로 각 포지션에 맞는 콤보박스에 선수를 입력하는 작업
					cbPlayer[1].setSelectedItem(tacticsDTO.getC()); 
					cbPlayer[2].setSelectedItem(tacticsDTO.getB1());
					cbPlayer[3].setSelectedItem(tacticsDTO.getB2());
					cbPlayer[4].setSelectedItem(tacticsDTO.getB3());
					cbPlayer[5].setSelectedItem(tacticsDTO.getSs());
					cbPlayer[6].setSelectedItem(tacticsDTO.getLf());
					cbPlayer[7].setSelectedItem(tacticsDTO.getCf());
					cbPlayer[8].setSelectedItem(tacticsDTO.getRf());
					cbChange[0].setSelectedItem(tacticsDTO.getCh1());
					cbChange[1].setSelectedItem(tacticsDTO.getCh2());
					cbChange[2].setSelectedItem(tacticsDTO.getCh3());
					cbPitcher[0].setSelectedItem(tacticsDTO.getSp1());
					cbPitcher[1].setSelectedItem(tacticsDTO.getSp2());
					cbPitcher[2].setSelectedItem(tacticsDTO.getSp3());
					cbPitcher[3].setSelectedItem(tacticsDTO.getSp4());
					cbPitcher[4].setSelectedItem(tacticsDTO.getSp5());
					cbMiddlePitcher[0].setSelectedItem(tacticsDTO.getRp1());
					cbMiddlePitcher[1].setSelectedItem(tacticsDTO.getRp2());
					cbLastPitcher[0].setSelectedItem(tacticsDTO.getCp1());
					cbLastPitcher[1].setSelectedItem(tacticsDTO.getCp2()); //DB에서 가져온 데이터를 바탕으로 각 포지션에 맞는 콤보박스에 선수를 입력하는 작업
				}
			}
		});

		JLabel labelContour = new JLabel();
		labelContour.setBounds(0, 60, 1000, 5);
		labelContour.setOpaque(true);
		labelContour.setBackground(color);
		panel.add(labelContour);

		JLabel labelTacticsName = new JLabel("전술 이름");
		labelTacticsName.setBounds(360, 80, 60, 25);
		panel.add(labelTacticsName);

		tfTacticsName = new JTextField();
		tfTacticsName.setBounds(420, 80, 160, 25);
		panel.add(tfTacticsName);

		btnDupCheck = new JButton("중복");
		btnDupCheck.setBounds(590, 80, 60, 25);
		btnDupCheck.setBackground(color);
		btnDupCheck.setForeground(Color.white);
		panel.add(btnDupCheck);
		btnDupCheck.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (tacticsDAO.duplicateCheck(tfTacticsName.getText())) {
					JOptionPane.showMessageDialog(null, "사용 중인 이름입니다. 다시 시도해 주세요.", "실패", JOptionPane.ERROR_MESSAGE);
					btnAddTactics.setEnabled(false);
					btnModTactics.setEnabled(false);
				} else {
					JOptionPane.showMessageDialog(null, "사용 가능한 이름입니다.");
					tfTacticsName.setEnabled(false);
					btnDupCheck.setEnabled(false);
					btnAddTactics.setEnabled(true);
					btnModTactics.setEnabled(false);
				}
			}
		});

		JLabel labelHit = new JLabel("타선 설정");
		labelHit.setBounds(360, 110, 60, 25);
		labelHit.setForeground(color);
		panel.add(labelHit);

		int y = 140;
		for (int i = 0; i < 9; i++) {
			labelPos1[i] = new JLabel(strPos[i]);
			labelPos1[i].setBounds(360, y, 30, 25);
			cbPlayer[i] = new JComboBox<String>(tacticsDAO.getPlayerName(strPos[i])
					.toArray(new String[tacticsDAO.getPlayerName(strPos[i]).size()]));
			cbPlayer[i].setBounds(400, y, 255, 25);
			cbPlayer[i].setBackground(Color.white);
			panel.add(labelPos1[i]);
			panel.add(cbPlayer[i]);
			y = y + 30;
		}

		JLabel labelChange = new JLabel("교체 설정");
		labelChange.setBounds(360, 430, 60, 25);
		labelChange.setForeground(color);
		panel.add(labelChange);

		int y2 = 470;
		for (int i = 0; i < 3; i++) {
			labelPos2[i] = new JLabel(strChange[i]);
			labelPos2[i].setBounds(360, y2, 30, 25);
			cbChange[i] = new JComboBox<String>(tacticsDAO.getPlayerName(strChange[i])
					.toArray(new String[tacticsDAO.getPlayerName(strChange[i]).size()]));
			cbChange[i].setBounds(400, y2, 255, 25);
			cbChange[i].setBackground(Color.white);
			panel.add(labelPos2[i]);
			panel.add(cbChange[i]);
			y2 = y2 + 30;
		}

		JLabel labelGroundImg = new JLabel(new ImageIcon("Image/ground.png"));
		labelGroundImg.setBounds(0, 60, 350, 540);
		panel.add(labelGroundImg);

		JLabel labelPitcher = new JLabel("선발투수 설정");
		labelPitcher.setForeground(color);
		labelPitcher.setBounds(695, 70, 300, 30);
		panel.add(labelPitcher);

		int y3 = 110;
		for (int i = 0; i < 5; i++) {
			labelPos3[i] = new JLabel(strPitcher[i]);
			labelPos3[i].setBounds(680, y3, 30, 25);
			cbPitcher[i] = new JComboBox<String>(tacticsDAO.getPlayerName(strPitcher[i])
					.toArray(new String[tacticsDAO.getPlayerName(strPitcher[i]).size()]));
			cbPitcher[i].setBounds(715, y3, 255, 25);
			cbPitcher[i].setBackground(Color.white);
			panel.add(labelPos3[i]);
			panel.add(cbPitcher[i]);
			y3 = y3 + 30;
		}

		JLabel labelMP = new JLabel("중간계투 설정");
		labelMP.setForeground(color);
		labelMP.setBounds(695, 260, 300, 30);
		panel.add(labelMP);

		int y4 = 290;
		for (int i = 0; i < 2; i++) {
			labelPos4[i] = new JLabel(strMiddlePitcher[i]);
			labelPos4[i].setBounds(680, y4, 30, 25);
			cbMiddlePitcher[i] = new JComboBox<String>(tacticsDAO.getPlayerName(strMiddlePitcher[i])
					.toArray(new String[tacticsDAO.getPlayerName(strMiddlePitcher[i]).size()]));
			cbMiddlePitcher[i].setBounds(715, y4, 255, 25);
			cbMiddlePitcher[i].setBackground(Color.white);
			panel.add(labelPos4[i]);
			panel.add(cbMiddlePitcher[i]);
			y4 = y4 + 30;
		}

		JLabel labelLP = new JLabel("마무리투수 설정");
		labelLP.setForeground(color);
		labelLP.setBounds(695, 350, 300, 30);
		panel.add(labelLP);

		int y5 = 380;
		for (int i = 0; i < 2; i++) {
			labelPos5[i] = new JLabel(strLastPitcher[i]);
			labelPos5[i].setBounds(680, y5, 30, 25);
			cbLastPitcher[i] = new JComboBox<String>(tacticsDAO.getPlayerName(strLastPitcher[i])
					.toArray(new String[tacticsDAO.getPlayerName(strLastPitcher[i]).size()]));
			cbLastPitcher[i].setBounds(715, y5, 255, 25);
			cbLastPitcher[i].setBackground(Color.white);
			panel.add(labelPos5[i]);
			panel.add(cbLastPitcher[i]);
			y5 = y5 + 30;
		}

		btnAddTactics = new JButton("전술 추가");
		btnAddTactics.setBounds(690, 475, 140, 30);
		btnAddTactics.setBackground(color);
		btnAddTactics.setForeground(Color.white);
		btnAddTactics.setEnabled(false);
		panel.add(btnAddTactics);
		btnAddTactics.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tacticsDTO.setTactics_name(tfTacticsName.getText());
				tacticsDTO.setDh(cbPlayer[0].getSelectedItem().toString());
				tacticsDTO.setC(cbPlayer[1].getSelectedItem().toString());
				tacticsDTO.setB1(cbPlayer[2].getSelectedItem().toString());
				tacticsDTO.setB2(cbPlayer[3].getSelectedItem().toString());
				tacticsDTO.setB3(cbPlayer[4].getSelectedItem().toString());
				tacticsDTO.setSs(cbPlayer[5].getSelectedItem().toString());
				tacticsDTO.setLf(cbPlayer[6].getSelectedItem().toString());
				tacticsDTO.setCf(cbPlayer[7].getSelectedItem().toString());
				tacticsDTO.setRf(cbPlayer[8].getSelectedItem().toString());
				tacticsDTO.setCh1(cbChange[0].getSelectedItem().toString());
				tacticsDTO.setCh2(cbChange[1].getSelectedItem().toString());
				tacticsDTO.setCh3(cbChange[2].getSelectedItem().toString());
				tacticsDTO.setSp1(cbPitcher[0].getSelectedItem().toString());
				tacticsDTO.setSp2(cbPitcher[1].getSelectedItem().toString());
				tacticsDTO.setSp3(cbPitcher[2].getSelectedItem().toString());
				tacticsDTO.setSp4(cbPitcher[3].getSelectedItem().toString());
				tacticsDTO.setSp5(cbPitcher[4].getSelectedItem().toString());
				tacticsDTO.setRp1(cbMiddlePitcher[0].getSelectedItem().toString());
				tacticsDTO.setRp2(cbMiddlePitcher[1].getSelectedItem().toString());
				tacticsDTO.setCp1(cbLastPitcher[0].getSelectedItem().toString());
				tacticsDTO.setCp2(cbLastPitcher[1].getSelectedItem().toString());
				int result1 = 0, result2 = 0, result3 = 0, result4 = 0, result5 = 0;
				int result6 = 0, result7 = 0, result8 = 0, result9 = 0, result10 = 0;
				for (int i = 0; i < cbPlayer.length; i++) {
					if (cbPlayer[i].getSelectedIndex() == 0) {
						result1++;
					}
				}
				for (int i = 0; i < cbChange.length; i++) {
					if (cbChange[i].getSelectedIndex() == 0) {
						result2++;
					}
				}
				for (int i = 0; i < cbPitcher.length; i++) {
					if (cbPitcher[i].getSelectedIndex() == 0) {
						result3++;
					}
				}
				for (int i = 0; i < cbMiddlePitcher.length; i++) {
					if (cbMiddlePitcher[i].getSelectedIndex() == 0) {
						result4++;
					}
				}
				for (int i = 0; i < cbLastPitcher.length; i++) {
					if (cbLastPitcher[i].getSelectedIndex() == 0) {
						result5++;
					}
				}
				for (int i = 0; i < 9; i++) {
					for (int j = 0; j < 9; j++) {
						if (cbPlayer[i].getSelectedItem().toString().equals(cbPlayer[j].getSelectedItem().toString())) {
							result6++;
						}
					}
				}
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						if (cbChange[i].getSelectedItem().toString().equals(cbChange[j].getSelectedItem().toString())) {
							result7++;
						}
					}
				}
				for (int i = 0; i < 5; i++) {
					for (int j = 0; j < 5; j++) {
						if (cbPitcher[i].getSelectedItem().toString().equals(cbPitcher[j].getSelectedItem().toString())) {
							result8++;
						}
					}
				}
				for (int i = 0; i < 2; i++) {
					for (int j = 0; j < 2; j++) {
						if (cbMiddlePitcher[i].getSelectedItem().toString().equals(cbMiddlePitcher[j].getSelectedItem().toString())) {
							result9++;
						}
					}
				}
				for (int i = 0; i < 2; i++) {
					for (int j = 0; j < 2; j++) {
						if (cbLastPitcher[i].getSelectedItem().toString().equals(cbLastPitcher[j].getSelectedItem().toString())) {
							result10++;
						}
					}
				}

				if (result1 == 0 && result2 == 0 && result3 == 0 && result4 == 0 && result5 == 0 && result6 == 9 && result7 == 3 && result8 == 5 && result9 == 2 && result10 == 2) {
					if (tacticsDAO.addTactics(tacticsDTO)) {
						JOptionPane.showMessageDialog(null, "전술 추가가 완료되었습니다.");
						cbTactics = new JComboBox<String>(tacticsDAO.getTacticsName("load")
								.toArray(new String[tacticsDAO.getTacticsName("load").size()]));
					} else {
						JOptionPane.showMessageDialog(null, "전술 추가 중 오류가 발생하였습니다. 확인 후 다시 시도해 주세요.", "실패",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "선수를 추가하지 않은 포지션이 있거나, 중복된 선수가 있습니다. 확인 후 다시 시도해 주세요.", "실패",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		btnModTactics = new JButton("전술 수정");
		btnModTactics.setBounds(840, 475, 140, 30);
		btnModTactics.setBackground(color);
		btnModTactics.setForeground(Color.white);
		btnModTactics.setEnabled(false);
		panel.add(btnModTactics);
		btnModTactics.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tacticsDTO.setTactics_name(tfTacticsName.getText());
				tacticsDTO.setDh(cbPlayer[0].getSelectedItem().toString());
				tacticsDTO.setC(cbPlayer[1].getSelectedItem().toString());
				tacticsDTO.setB1(cbPlayer[2].getSelectedItem().toString());
				tacticsDTO.setB2(cbPlayer[3].getSelectedItem().toString());
				tacticsDTO.setB3(cbPlayer[4].getSelectedItem().toString());
				tacticsDTO.setSs(cbPlayer[5].getSelectedItem().toString());
				tacticsDTO.setLf(cbPlayer[6].getSelectedItem().toString());
				tacticsDTO.setCf(cbPlayer[7].getSelectedItem().toString());
				tacticsDTO.setRf(cbPlayer[8].getSelectedItem().toString());
				tacticsDTO.setCh1(cbChange[0].getSelectedItem().toString());
				tacticsDTO.setCh2(cbChange[1].getSelectedItem().toString());
				tacticsDTO.setCh3(cbChange[2].getSelectedItem().toString());
				tacticsDTO.setSp1(cbPitcher[0].getSelectedItem().toString());
				tacticsDTO.setSp2(cbPitcher[1].getSelectedItem().toString());
				tacticsDTO.setSp3(cbPitcher[2].getSelectedItem().toString());
				tacticsDTO.setSp4(cbPitcher[3].getSelectedItem().toString());
				tacticsDTO.setSp5(cbPitcher[4].getSelectedItem().toString());
				tacticsDTO.setRp1(cbMiddlePitcher[0].getSelectedItem().toString());
				tacticsDTO.setRp2(cbMiddlePitcher[1].getSelectedItem().toString());
				tacticsDTO.setCp1(cbLastPitcher[0].getSelectedItem().toString());
				tacticsDTO.setCp2(cbLastPitcher[1].getSelectedItem().toString());
				int result1 = 0, result2 = 0, result3 = 0, result4 = 0, result5 = 0;
				for (int i = 0; i < cbPlayer.length; i++) {
					if (cbPlayer[i].getSelectedIndex() == 0) {
						result1++;
					}
				}
				for (int i = 0; i < cbChange.length; i++) {
					if (cbChange[i].getSelectedIndex() == 0) {
						result2++;
					}
				}
				for (int i = 0; i < cbPitcher.length; i++) {
					if (cbPitcher[i].getSelectedIndex() == 0) {
						result3++;
					}
				}
				for (int i = 0; i < cbMiddlePitcher.length; i++) {
					if (cbMiddlePitcher[i].getSelectedIndex() == 0) {
						result4++;
					}
				}
				for (int i = 0; i < cbLastPitcher.length; i++) {
					if (cbLastPitcher[i].getSelectedIndex() == 0) {
						result5++;
					}
				}

				if (result1 == 0 && result2 == 0 && result3 == 0 && result4 == 0 && result5 == 0) {
					if (tacticsDAO.updateTactics(tacticsDTO)) {
						JOptionPane.showMessageDialog(null, "전술 수정이 완료되었습니다.");
					} else {
						JOptionPane.showMessageDialog(null, "전술 수정 중 오류가 발생하였습니다. 확인 후 다시 시도해 주세요.", "실패",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "선수를 추가하지 않은 포지션이 있거나, 중복된 선수가 있습니다. 확인 후 다시 시도해 주세요.", "실패",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		btnDelTactics = new JButton("전술 삭제");
		btnDelTactics.setBounds(690, 515, 140, 30);
		btnDelTactics.setBackground(color);
		btnDelTactics.setForeground(Color.white);
		panel.add(btnDelTactics);
		btnDelTactics.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TDelFrame tDelFrame = new TDelFrame();
			}
		});

		btnReset = new JButton("전술 초기화");
		btnReset.setBounds(840, 515, 140, 30);
		btnReset.setBackground(color);
		btnReset.setForeground(Color.white);
		panel.add(btnReset);
		btnReset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cbTactics.setSelectedIndex(0);
				tfTacticsName.setEnabled(true);
				tfTacticsName.setText("");
				for (int i = 0; i < cbPlayer.length; i++) {
					cbPlayer[i].setSelectedIndex(0);
				}
				for (int i = 0; i < cbChange.length; i++) {
					cbChange[i].setSelectedIndex(0);
				}
				for (int i = 0; i < cbPitcher.length; i++) {
					cbPitcher[i].setSelectedIndex(0);
				}
				for (int i = 0; i < cbMiddlePitcher.length; i++) {
					cbMiddlePitcher[i].setSelectedIndex(0);
				}
				for (int i = 0; i < cbLastPitcher.length; i++) {
					cbLastPitcher[i].setSelectedIndex(0);
				}
				btnDupCheck.setEnabled(true);
				btnAddTactics.setEnabled(false);
				btnModTactics.setEnabled(false);
				JOptionPane.showMessageDialog(null, "초기화 되었습니다.");
			}
		});

		btnExit = new JButton("나가기");
		btnExit.setBounds(840, 555, 140, 30);
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