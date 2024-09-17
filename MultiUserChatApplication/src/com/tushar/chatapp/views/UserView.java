package com.tushar.chatapp.views;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class UserView extends JFrame{
	int counter;
	public UserView() {
		counter=0;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500,500);
		setResizable(false);//now the window can't be maximized
		setTitle("Login");
		//setLocation(500,150); //setting location w.r.t. (x,y) coordinates
		setLocationRelativeTo(null);//setting location relative to screen
		JLabel welcome = new JLabel("Login");
		welcome.setFont(new Font("Arial", Font.BOLD,40));
		Container container= this.getContentPane();
		container.setLayout(null);
		welcome.setBounds(100,70,200,60);
		container.add(welcome);
		JButton button= new JButton("Count");
		
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				counter++;
				welcome.setText("Count " + counter);
			}
		});
		
		button.setBounds(100, 300,200,50);
		container.add(button);
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		UserView userview = new UserView();
	}
}
