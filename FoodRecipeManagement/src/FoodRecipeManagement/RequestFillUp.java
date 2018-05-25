package FoodRecipeManagement;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.Box;
import javax.swing.border.SoftBevelBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.border.BevelBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class RequestFillUp extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JLabel lblClock;
	private JTable table2;
	JTextPane textPaneAboutFood;
	JTextPane textPaneName;
	JTextPane textPaneContact;
	JTextPane textPaneAdminReply;
	JTextPane textPaneFN;
	JTextPane textPaneRN;
	JScrollPane scrollPane;
	JScrollPane scrollPane_2;

	Connection conn = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RequestFillUp frame = new RequestFillUp();
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

			table2.setModel(DbUtils.resultSetToTableModel(rs));

			pst.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clock() {
		Thread clock = new Thread() {
			public void run() {
				try {

					for (;;)

					// while(true)
					{

						Calendar cal = new GregorianCalendar();
						int day = cal.get(Calendar.DAY_OF_MONTH);
						int month = cal.get(Calendar.MONTH);
						int year = cal.get(Calendar.YEAR);

						int second = cal.get(Calendar.SECOND);
						int minute = cal.get(Calendar.MINUTE);
						int hour = cal.get(Calendar.HOUR);

						lblClock.setText("Time = " + hour + ":" + minute + ":" + second + "   Date: " + day + "/"
								+ month + "/" + year + "");

						sleep(1000);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};

		clock.start();

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

	public void TableData() {
		try {
			String query = "select * from FoodRequest";
			PreparedStatement pst = conn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();

			table2.setModel(DbUtils.resultSetToTableModel(rs));
			pst.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	public RequestFillUp()

	{
		setResizable(false);
		initialize();
		clock();
		TableData();
		FrameinMiddle();

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		SwingUtilities.updateComponentTreeUI(contentPane);

		JLabel label = new JLabel("");
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getClickCount() > 0) {
					if (Desktop.isDesktopSupported()) {
						Desktop desktop = Desktop.getDesktop();
						try {
							URI uri = new URI("http://www.food.com");
							desktop.browse(uri);
						} catch (IOException ex) {
							ex.printStackTrace();
						} catch (URISyntaxException ex) {
							ex.printStackTrace();
						}
					}
				}
			}
		});
		label.setIcon(new ImageIcon(RequestFillUp.class.getResource("/img/fast.png")));
		label.setBounds(378, 74, 130, 130);
		contentPane.add(label);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(RequestFillUp.class.getResource("/img/IMG_63144.jpg")));
		lblNewLabel_1.setBounds(0, 0, 794, 471);
		contentPane.add(lblNewLabel_1);
	}

	private void initialize() {

		conn = sqliteConnection.dbConnector();

		setIconImage(Toolkit.getDefaultToolkit().getImage(RequestFillUp.class.getResource("/img/mainicon.png")));
		setTitle("FRM - Request FillUp");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(143, 150, 192, 34);
		contentPane.add(scrollPane_3);

		textPaneFN = new JTextPane();
		scrollPane_3.setViewportView(textPaneFN);
		textPaneFN.setFont(new Font("Tahoma", Font.PLAIN, 15));

		JLabel label = new JLabel("Food Name :");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Tahoma", Font.BOLD, 14));
		label.setBounds(6, 150, 127, 35);
		contentPane.add(label);

		JLabel lblAboutFood = new JLabel("About Food:");
		lblAboutFood.setForeground(Color.WHITE);
		lblAboutFood.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAboutFood.setBounds(6, 195, 127, 35);
		contentPane.add(lblAboutFood);

		JLabel lblRequesttable = new JLabel("Request Table:");
		lblRequesttable.setForeground(Color.WHITE);
		lblRequesttable.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblRequesttable.setBounds(6, 257, 112, 35);
		contentPane.add(lblRequesttable);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(345, 215, 348, 235);
		contentPane.add(scrollPane_1);

		textPaneAdminReply = new JTextPane();
		// textPaneAdminReply.addMouseListener(new MouseAdapter() {
		// @Override
		// public void mouseClicked(MouseEvent e) {
		// textPaneAdminReply.setText("");
		// }
		// });
		textPaneAdminReply.setText("Reply........");
		scrollPane_1.setViewportView(textPaneAdminReply);
		textPaneAdminReply.setFont(new Font("Tahoma", Font.PLAIN, 16));

		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminPanel adminpanelframe = new AdminPanel();
				adminpanelframe.setVisible(true);
				dispose();
			}
		});
		button.setIcon(new ImageIcon(RequestFillUp.class.getResource("/img/back (1).png")));
		button.setFont(new Font("Tahoma", Font.PLAIN, 18));
		button.setBounds(715, 11, 59, 60);
		contentPane.add(button);

		JButton buttonExit = new JButton("");
		buttonExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		buttonExit.setIcon(new ImageIcon(RequestFillUp.class.getResource("/img/Exit.png")));
		buttonExit.setFont(new Font("Tahoma", Font.PLAIN, 18));
		buttonExit.setBounds(703, 393, 81, 57);
		contentPane.add(buttonExit);

		JButton buttonReply = new JButton("");
		buttonReply.setIcon(new ImageIcon(RequestFillUp.class.getResource("/img/green-mail-send-icon.png")));
		buttonReply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query = "Update FoodRequest set AdminReply ='" + textPaneAdminReply.getText()
							+ "',Count=0 where RequestNO ='" + textPaneRN.getText() + "'";

					PreparedStatement pst = conn.prepareStatement(query);

					pst.execute();
					JOptionPane.showMessageDialog(null, "Done");

					pst.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				refreshTable();
			}
		});
		buttonReply.setFont(new Font("Tahoma", Font.PLAIN, 15));
		buttonReply.setBounds(703, 186, 81, 57);
		contentPane.add(buttonReply);

		JButton buttonReset = new JButton("");
		buttonReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				textPaneName.setText("");
				textPaneContact.setText("");
				textPaneFN.setText("");
				textPaneAboutFood.setText("");
				textPaneRN.setText("");
				textPaneAdminReply.setText("Reply........");

			}
		});
		buttonReset.setIcon(new ImageIcon(RequestFillUp.class.getResource("/img/Reset-icon.png")));
		buttonReset.setFont(new Font("Tahoma", Font.PLAIN, 15));
		buttonReset.setBounds(703, 254, 81, 60);
		contentPane.add(buttonReset);

		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(143, 104, 192, 34);
		contentPane.add(scrollPane_4);

		textPaneContact = new JTextPane();
		scrollPane_4.setViewportView(textPaneContact);
		textPaneContact.setFont(new Font("Tahoma", Font.PLAIN, 15));

		JLabel lblYourContact = new JLabel("Contact :");
		lblYourContact.setForeground(Color.WHITE);
		lblYourContact.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblYourContact.setBounds(6, 104, 127, 35);
		contentPane.add(lblYourContact);

		JLabel lblName = new JLabel("Name:");
		lblName.setForeground(Color.WHITE);
		lblName.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblName.setBounds(6, 58, 127, 35);
		contentPane.add(lblName);

		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(143, 58, 192, 34);
		contentPane.add(scrollPane_5);

		textPaneName = new JTextPane();
		scrollPane_5.setViewportView(textPaneName);
		textPaneName.setFont(new Font("Tahoma", Font.PLAIN, 15));

		lblClock = new JLabel("Clock");
		lblClock.setForeground(Color.WHITE);
		lblClock.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblClock.setBounds(387, 11, 274, 35);
		contentPane.add(lblClock);

		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(143, 195, 192, 70);
		contentPane.add(scrollPane_2);

		textPaneAboutFood = new JTextPane();
		scrollPane_2.setViewportView(textPaneAboutFood);
		textPaneAboutFood.setFont(new Font("Tahoma", Font.PLAIN, 15));

		scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 296, 329, 154);
		contentPane.add(scrollPane);

		table2 = new JTable();
		table2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {

					int row = table2.getSelectedRow();
					String textPaneRN_ = (table2.getModel().getValueAt(row, 0)).toString();

					String query = "select * from FoodRequest where RequestNO='" + textPaneRN_ + "'";
					PreparedStatement pst = conn.prepareStatement(query);

					ResultSet rs = pst.executeQuery();

					while (rs.next()) {
						textPaneRN.setText(rs.getString("RequestNO"));
						textPaneName.setText(rs.getString("Name"));
						textPaneContact.setText(rs.getString("Contact"));
						textPaneFN.setText(rs.getString("FoodName"));
						textPaneAboutFood.setText(rs.getString("AboutFood"));
						textPaneAdminReply.setText(rs.getString("AdminReply"));

					}

					pst.close();
					// rs.close();

				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		scrollPane.setViewportView(table2);

		Box horizontalBox = Box.createHorizontalBox();
		horizontalBox.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		horizontalBox.setForeground(Color.BLACK);
		horizontalBox.setBounds(377, 11, 284, 35);
		contentPane.add(horizontalBox);

		JLabel lblRequestNo = new JLabel("Request No:");
		lblRequestNo.setForeground(Color.WHITE);
		lblRequestNo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblRequestNo.setBounds(7, 11, 127, 35);
		contentPane.add(lblRequestNo);

		textPaneRN = new JTextPane();
		textPaneRN.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				try {
					String query = "select * from FoodRequest where RequestNO = ?";
					PreparedStatement pst = conn.prepareStatement(query);
					pst.setString(1, textPaneRN.getText());
					ResultSet rs = pst.executeQuery();

					table2.setModel(DbUtils.resultSetToTableModel(rs));

					pst.close();
					rs.close();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		textPaneRN.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textPaneRN.setBounds(145, 12, 190, 32);
		contentPane.add(textPaneRN);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() > 0) {
					if (Desktop.isDesktopSupported()) {
						Desktop desktop = Desktop.getDesktop();
						try {
							URI uri = new URI("http://www.google.com");
							desktop.browse(uri);
						} catch (IOException ex) {
							ex.printStackTrace();
						} catch (URISyntaxException ex) {
							ex.printStackTrace();
						}
					}
				}
			}
		});
		lblNewLabel.setIcon(new ImageIcon(RequestFillUp.class.getResource("/img/Google Logo.png")));
		lblNewLabel.setBounds(564, 74, 130, 130);
		contentPane.add(lblNewLabel);

		JButton buttonDeleteRequest = new JButton("");
		buttonDeleteRequest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int action = JOptionPane.showConfirmDialog(null, "Do You Really Want To Delete This Request ?",
						"Delete", JOptionPane.YES_NO_CANCEL_OPTION);
				if (action == 0) {

					try {
						String query = "delete from FoodRequest where RequestNO='" + textPaneRN.getText() + "'";
						PreparedStatement pst = conn.prepareStatement(query);
						pst.execute();

						JOptionPane.showMessageDialog(null, "Request Deleted Successfully");

						pst.close();
					} catch (Exception ed) {
						ed.printStackTrace();
					}
					refreshTable();
				}
			}
		});
		buttonDeleteRequest.setIcon(new ImageIcon(RequestFillUp.class.getResource("/img/Email-Delete-icon.png")));
		buttonDeleteRequest.setFont(new Font("Tahoma", Font.PLAIN, 15));
		buttonDeleteRequest.setBounds(703, 325, 81, 57);
		contentPane.add(buttonDeleteRequest);
	}
}
