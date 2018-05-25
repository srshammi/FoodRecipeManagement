package FoodRecipeManagement;

import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;

public class Login extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldusername;
	private JPasswordField passwordField;
	JButton btnLogin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login loginframe = new Login();
					loginframe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			btnLogin.doClick();
		}
	}

	/**
	 * Create the frame.
	 */
	Connection conn = null;

	public Login() {

		initialize();

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		SwingUtilities.updateComponentTreeUI(contentPane);
		FrameinMiddle();
	}

	public void FrameinMiddle() {

		Dimension screenSize, frameSize;
		int x, y;
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frameSize = getSize();
		x = (screenSize.width - frameSize.width) / 2;
		y = (screenSize.height - frameSize.height) / 2;
		setLocation(x, y);
	}

	private void initialize() {
		setResizable(false);

		conn = sqliteConnection.dbConnector();

		setTitle("FRM - Login");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/img/mainicon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				FRM frame = new FRM();
				frame.setVisible(true);
				dispose();

			}
		});
		button.setIcon(new ImageIcon(Login.class.getResource("/img/back (1).png")));
		button.setFont(new Font("Tahoma", Font.PLAIN, 18));
		button.setBounds(715, 11, 59, 60);
		contentPane.add(button);

		textFieldusername = new JTextField();
		textFieldusername.setColumns(10);
		textFieldusername.setBounds(644, 301, 130, 30);
		contentPane.add(textFieldusername);

		passwordField = new JPasswordField();
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {

					btnLogin.doClick();
				}
			}
		});
		passwordField.setEchoChar('*');
		passwordField.setBounds(644, 342, 130, 30);
		contentPane.add(passwordField);

		btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {

				// String query="select * from FRMAdmin where username=? and
				// password=?";
				// PreparedStatement pst=connection.prepareStatement(query);
				try {
					String query = "select * from AdminLogin where Username=? and Password=?";

					PreparedStatement pst = conn.prepareStatement(query);
					pst.setString(1, textFieldusername.getText());
					pst.setString(2, passwordField.getText());

					ResultSet rs = pst.executeQuery();
					int count = 0;
					while (rs.next()) {
						count = count + 1;
					}
					if (count == 1) {
						// JOptionPane.showMessageDialog(null,"Logged in
						// successfully");

						AdminPanel adminpanelframe = new AdminPanel();
						adminpanelframe.setVisible(true);
						dispose();

					} else if (count > 1) {
						JOptionPane.showMessageDialog(null, "Duplicate username and Password");
					} else {
						JOptionPane.showMessageDialog(null, "Username and Password is not Correct");
					}

					rs.close();
					pst.close();

				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1);

				}

			}
		});
		btnLogin.setIcon(new ImageIcon(Login.class.getResource("/img/key.png")));
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnLogin.setBounds(654, 383, 103, 40);
		contentPane.add(btnLogin);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(Login.class.getResource("/img/Administrator-icon.png")));
		lblNewLabel_1.setBounds(644, 162, 128, 128);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Login.class.getResource("/img/Background1.jpg")));
		lblNewLabel.setBounds(0, 0, 794, 471);
		contentPane.add(lblNewLabel);
	}
}
