package com.littlepage.getImage;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class WormWindow extends JFrame {

	private JPanel contentPane;
	private JTextField jtfUrl;

	public WormWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2,2));
		contentPane.add(panel, BorderLayout.CENTER);
		
		JLabel jl = new JLabel("请输入网址");
		jl.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(jl);
		
		jtfUrl = new JTextField();
		panel.add(jtfUrl);
		jtfUrl.setColumns(10);
		
		JButton jbConfirm = new JButton("确认");
		panel.add(jbConfirm);
		
		JButton jbCancel = new JButton("取消");
		panel.add(jbCancel);
		setSize(400,120);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		jbConfirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String url=jtfUrl.getText();
				int num=Worm.doDownLoad(url);
				if(num!=0) JOptionPane.showMessageDialog(null, "下载成功"+num+"条");
				else JOptionPane.showMessageDialog(null, "下载失败");
			}
		});
		jbCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		setTitle("WromPic V1.0");
		setIconImage(new ImageIcon("icon/icon.jpg").getImage());
	}
	public static void main(String[] args) {
		new WormWindow();
	}
}
