package com.sirui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

public class MainUI extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JTextArea textOne, textTwo;
    private JButton btn1;
    
	public MainUI() {
		setTitle("android:id init");
		setSize(800, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(getOwner());
		setLayout(new BorderLayout(5, 5));
		try {
			String os = System.getProperty("os.name");
			if ("Linux".equals(os)) {
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
			} else {
				UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			}
		} catch (Exception e) {}
		addTop();
		addLeft();
		addRight();
		setVisible(true);
		setClick();
	}
	
	private void addTop() {
		JPanel mPanel = new JPanel();
		mPanel.setBorder(new TitledBorder("操作"));
		mPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		btn1 = new JButton("自动初始化");
		mPanel.add(btn1,BorderLayout.CENTER);
		getContentPane().add(mPanel, BorderLayout.NORTH);
	}

	private void setClick(){
		btn1.addActionListener(this);
	}

	public void addLeft() {
		JPanel mPanel = new JPanel();
		mPanel.setLayout(new BorderLayout());
		textOne = new JTextArea();
		JScrollPane jp = new JScrollPane(textOne);
		jp.setPreferredSize(new Dimension(400, 0));
		mPanel.add(jp, BorderLayout.CENTER);
		getContentPane().add(mPanel, BorderLayout.CENTER);
	}

	public void addRight() {
		JPanel mPanel = new JPanel();
		mPanel.setLayout(new BorderLayout());
		textTwo = new JTextArea();
		JScrollPane jp = new JScrollPane(textTwo);
		jp.setPreferredSize(new Dimension(400, 0));
		mPanel.add(jp, BorderLayout.EAST);
		getContentPane().add(mPanel, BorderLayout.EAST);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals( btn1.getActionCommand())){
			if (textOne.getText() == null || "".equals(textOne.getText())) {
				return;
			}
			Utils.createFileds(textOne, textTwo);
			Utils.initFileds(textOne, textTwo);
		}
	}
	
}