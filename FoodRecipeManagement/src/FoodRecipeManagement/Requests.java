package FoodRecipeManagement;

import java.awt.EventQueue;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import net.proteanit.sql.DbUtils;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JScrollPane;
import java.awt.Toolkit;
import javax.swing.Box;
import java.awt.Color;

import javax.swing.border.LineBorder;

public class Requests extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtRequestNumber;
	JTextPane textPaneFN;
	JTextPane textPaneAboutFood;
	JTextPane textPaneName;
	JTextPane textPaneContact;
	JTextPane textPaneAdminReply;
	JScrollPane scrollPane;
	/**
	 * Launch the application.
	 */

	Connection conn = null;
	private JTable table;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Requests frame = new Requests();
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void refreshTable() {

		try {
			String query = "select * from FoodRequest";
			PreparedStatement pst = conn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();

			table.setModel(DbUtils.resultSetToTableModel(rs));

			pst.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	public Requests() {

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

	public void initialize()

	{

		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Requests.class.getResource("/img/mainicon.png")));
		setTitle("FRM - Request Panel");

		conn = sqliteConnection.dbConnector();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setForeground(Color.LIGHT_GRAY);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textPaneFN = new JTextPane();
		textPaneFN.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textPaneFN.setBounds(161, 176, 192, 34);
		contentPane.add(textPaneFN);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 263, 294, 113);
		contentPane.add(scrollPane_1);

		textPaneAboutFood = new JTextPane();
		scrollPane_1.setViewportView(textPaneAboutFood);
		textPaneAboutFood.setFont(new Font("Tahoma", Font.PLAIN, 13));

		JLabel lblNewLabel = new JLabel("Food Name :");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 175, 127, 35);
		contentPane.add(lblNewLabel);

		JLabel lblWriteSomeInformation = new JLabel("Write some information about the food.");
		lblWriteSomeInformation.setForeground(Color.WHITE);
		lblWriteSomeInformation.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblWriteSomeInformation.setBounds(10, 217, 294, 35);
		contentPane.add(lblWriteSomeInformation);

		JLabel lblAdminReply = new JLabel("Admin's Reply");
		lblAdminReply.setForeground(Color.WHITE);
		lblAdminReply.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		lblAdminReply.setBounds(523, 123, 127, 35);
		contentPane.add(lblAdminReply);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(386, 157, 388, 220);
		contentPane.add(scrollPane_2);

		textPaneAdminReply = new JTextPane();
		textPaneAdminReply.setForeground(Color.BLACK);
		textPaneAdminReply.setText("Reply will appair here...");
		scrollPane_2.setViewportView(textPaneAdminReply);
		textPaneAdminReply.setEditable(false);
		textPaneAdminReply.setFont(new Font("Tahoma", Font.PLAIN, 15));

		JLabel lblSearchForYour = new JLabel("Check Your Requested Recipe:");
		lblSearchForYour.setForeground(Color.WHITE);
		lblSearchForYour.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSearchForYour.setBounds(10, 11, 171, 35);
		contentPane.add(lblSearchForYour);

		txtRequestNumber = new JTextField();
		txtRequestNumber.setForeground(Color.GRAY);
		txtRequestNumber.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				try {
					String query = "select Name,Contact,FoodName from FoodRequest where Contact = ?";
					PreparedStatement pst = conn.prepareStatement(query);
					pst.setString(1, txtRequestNumber.getText());
					ResultSet rs = pst.executeQuery();

					table.setModel(DbUtils.resultSetToTableModel(rs));
					pst.close();
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		txtRequestNumber.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtRequestNumber.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				txtRequestNumber.setText("");
			}
		});
		txtRequestNumber.setText("Enter Contact No.");
		txtRequestNumber.setToolTipText("");
		txtRequestNumber.setBounds(191, 11, 162, 35);
		contentPane.add(txtRequestNumber);
		txtRequestNumber.setColumns(10);

		JButton buttonBack = new JButton("");
		buttonBack.setIcon(new ImageIcon(Requests.class.getResource("/img/back (1).png")));
		buttonBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				FRM frame = new FRM();
				frame.setVisible(true);
				dispose();
			}
		});
		buttonBack.setFont(new Font("Tahoma", Font.PLAIN, 18));
		buttonBack.setBounds(715, 11, 59, 60);
		contentPane.add(buttonBack);

		JButton buttonExit = new JButton("");
		buttonExit.setIcon(new ImageIcon(Requests.class.getResource("/img/Exit.png")));
		buttonExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		buttonExit.setFont(new Font("Tahoma", Font.PLAIN, 18));
		buttonExit.setBounds(715, 390, 59, 60);
		contentPane.add(buttonExit);

		JButton btnSend = new JButton("");
		btnSend.setIcon(new ImageIcon(Requests.class.getResource("/img/Send-icon.png")));
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "insert into FoodRequest (Name,Contact,FoodName,AboutFood,AdminReply,Count) values (?,?,?,?,'Not replied yet',1)";

					PreparedStatement pst = conn.prepareStatement(query);

					pst.setString(1, textPaneName.getText());
					pst.setString(2, textPaneContact.getText());
					pst.setString(3, textPaneFN.getText());
					pst.setString(4, textPaneAboutFood.getText());

					pst.execute();

					JOptionPane.showMessageDialog(null,
							"Your request has been Sent. Please Check After 24h. Thank You...");

					pst.close();

				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});
		btnSend.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSend.setBounds(52, 387, 81, 57);
		contentPane.add(btnSend);

		JButton btnReset = new JButton("");
		btnReset.setIcon(new ImageIcon(Requests.class.getResource("/img/Reset-icon.png")));
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				textPaneName.setText("");
				textPaneContact.setText("");
				textPaneFN.setText("");
				textPaneAboutFood.setText("");
				txtRequestNumber.setText("Enter Contact No.");
				textPaneAdminReply.setText("Reply will appair here...");

			}
		});
		btnReset.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnReset.setBounds(161, 387, 81, 57);
		contentPane.add(btnReset);

		textPaneContact = new JTextPane();
		textPaneContact.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textPaneContact.setBounds(161, 123, 192, 34);
		contentPane.add(textPaneContact);

		JLabel lblYourContactNumber = new JLabel("Contact Number :");
		lblYourContactNumber.setForeground(Color.WHITE);
		lblYourContactNumber.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblYourContactNumber.setBounds(10, 123, 144, 35);
		contentPane.add(lblYourContactNumber);

		JLabel lblYourContactName = new JLabel("Your Short Name:");
		lblYourContactName.setBackground(Color.LIGHT_GRAY);
		lblYourContactName.setForeground(Color.WHITE);
		lblYourContactName.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblYourContactName.setBounds(10, 75, 144, 35);
		contentPane.add(lblYourContactName);

		textPaneName = new JTextPane();
		textPaneName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textPaneName.setBounds(161, 75, 192, 34);
		contentPane.add(textPaneName);

		Box horizontalBox1 = Box.createHorizontalBox();
		horizontalBox1.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
		horizontalBox1.setBounds(40, 381, 213, 69);
		contentPane.add(horizontalBox1);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(409, 11, 296, 104);
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					int row = table.getSelectedRow();
					String textPaneContact_ = (table.getModel().getValueAt(row, 1)).toString();
					String query = "select * from FoodRequest where Contact = '" + textPaneContact_ + "'";
					PreparedStatement pst = conn.prepareStatement(query);

					ResultSet rs2 = pst.executeQuery();

					while (rs2.next()) {
						textPaneName.setText(rs2.getString("Name"));
						textPaneContact.setText(rs2.getString("Contact"));
						textPaneFN.setText(rs2.getString("FoodName"));
						textPaneAboutFood.setText(rs2.getString("AboutFood"));
						textPaneAdminReply.setText(rs2.getString("AdminReply"));

					}

					pst.close();
					rs2.close();

				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Requests.class.getResource("/img/lowcarbhighfat-300x82.png")));
		lblNewLabel_2.setBounds(386, 381, 300, 82);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabelBG = new JLabel("");
		lblNewLabelBG.setIcon(new ImageIcon(Requests.class.getResource("/img/simple-green-highlight.jpg")));
		lblNewLabelBG.setBounds(0, 0, 794, 471);
		contentPane.add(lblNewLabelBG);

	}
}
