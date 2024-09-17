package com.tushar.chatapp.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.tushar.chatapp.dao.UserDAO;
import com.tushar.chatapp.dto.UserDTO;
import com.tushar.chatapp.utils.UserInfo;

import java.awt.Toolkit;

public class UserScreen extends JFrame {
	private JTextField UserIdfield;
	private JPasswordField passwordField;

	
	public static void main(String[] args) {

					UserScreen window = new UserScreen();

	}
	
	UserDAO userDAO= new UserDAO();
	
	private void doLogin() {
		String userid= UserIdfield.getText();
		//String password= passwordField.getText();
		char []password= passwordField.getPassword();
		
	
		UserDTO userDTO = new UserDTO(userid, password);
		try {
			String message="";
			if(userDAO.isLogin(userDTO)) {
				message ="Welcome "+userid;
				UserInfo.USER_NAME= userid;
				JOptionPane.showMessageDialog(this, message);
				setVisible(false);
				dispose();
				DashBoard dashBoard = new DashBoard(message);
				dashBoard.setVisible(true);
			}
			else {
				message="Invalid Userid or Password";
				JOptionPane.showMessageDialog(this, message);
			}
	
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	private void Register() {
		
		String userid= UserIdfield.getText();
		//String password= passwordField.getText();
		char []password= passwordField.getPassword();
		
		UserDTO userDTO = new UserDTO(userid, password);
		try {
		int result= userDAO.add(userDTO);
		if(result>0){
			JOptionPane.showMessageDialog(this, "Registration SuccessFully");
			//System.out.println("RECORD ADDED...");
		}
		else {
			JOptionPane.showMessageDialog(this, "Registration FAILED");
			//System.out.println("RECORD NOT ADDED...");
		}
		}
		catch(ClassNotFoundException | SQLException ex) {
			System.out.println("DATABASE ISSUE....");
			ex.printStackTrace();
		}
		catch(Exception ex) {
			System.out.println("Some GENERIC EXCEPTION is RAISED....");
			ex.printStackTrace();
		}
		System.out.println("userid--> "+ userid + "\npassword--> "+ new String(password)); //password will print ClassName@HashCode(Hexa)
		
	}
	/**
	 * Create the application.
	 */
	public UserScreen() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(UserScreen.class.getResource("/Images/chitchat.png")));
		setResizable(false);
		setTitle("Login");
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setFont(new Font("Lucida Fax", Font.BOLD, 40));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(321, 22, 139, 57);
		getContentPane().add(lblNewLabel);
		
		UserIdfield = new JTextField();
		UserIdfield.setBounds(395, 131, 247, 48);
		getContentPane().add(UserIdfield);
		UserIdfield.setColumns(10);
		
		JLabel Pwdlbl = new JLabel("Password");
		Pwdlbl.setFont(new Font("Times New Roman", Font.BOLD, 18));
		Pwdlbl.setBounds(202, 214, 91, 31);
		getContentPane().add(Pwdlbl);
		
		JLabel UserIdlbl = new JLabel("User ID");
		UserIdlbl.setFont(new Font("Times New Roman", Font.BOLD, 18));
		UserIdlbl.setBounds(202, 137, 91, 31);
		getContentPane().add(UserIdlbl);
		
		passwordField = new JPasswordField();
		passwordField.setColumns(10);
		passwordField.setBounds(395, 208, 247, 48);
		getContentPane().add(passwordField);
		
		JButton Registerbt = new JButton("Register");
		Registerbt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Register();
			}
		});
		Registerbt.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		Registerbt.setBounds(419, 287, 100, 41);
		getContentPane().add(Registerbt);
		
		JButton Loginbt = new JButton("Login");
		Loginbt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doLogin();
			}
		});

		Loginbt.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		Loginbt.setBounds(246, 287, 100, 41);
		getContentPane().add(Loginbt);
		
		
		setBackground(Color.WHITE);
		setSize(833,400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
}
