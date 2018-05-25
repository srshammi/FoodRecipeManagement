package FoodRecipeManagement;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import javax.swing.AbstractButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import net.proteanit.sql.DbUtils;
import java.sql.Connection;
import javax.swing.Box;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

public class AdminPanel extends JFrame {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;
	JPanel contentPane;
	JTable table2;
	String ss;
	byte[] image_detail = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminPanel adminpanelframe = new AdminPanel();
					adminpanelframe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	Connection conn = null;

	private ImageIcon format = null;
	JTextPane textPaneSerial1;
	JTextPane textPaneCategory1;
	JTextPane textPaneFoodName1;
	JTextPane textPaneProcedure1;
	JTextPane textPaneNutritionalValue1;
	JTextPane textPaneIngredients1;
	JTextPane textPaneSearch;
	JTextPane textPaneSearch1;
	@SuppressWarnings("rawtypes")
	JComboBox comboBox2;
	@SuppressWarnings("rawtypes")
	JComboBox comboBoxSearch2;
	JDesktopPane desktopPane;
	private JLabel lblImage1;
	JLabel label_9;
	JButton btnExit;

	String filename = null;
	int s = 0;
	byte[] image = null;

	AbstractButton path;

	public void Requests() {

		try {
			String query = "select sum(Count) from FoodRequest";
			PreparedStatement pst = conn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			// rs.getString(label_9.setValue(""));
			if (rs.next()) {
				String sum = rs.getString("sum(Count)");
				label_9.setText(sum);
			}

			pst.close();
			rs.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void refreshTable() {

		try {
			String query = "select * from FoodList";
			PreparedStatement pst = conn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();

			table2.setModel(DbUtils.resultSetToTableModel(rs));

			pst.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public void fillComboBox()

	{

		try {
			String query = "select FoodName from FoodList";
			PreparedStatement pst = conn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				comboBoxSearch2.addItem(rs.getString("FoodName"));
			}
			pst.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();

		}

		refreshTable();
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

	public AdminPanel()

	{

		initialize();
		Requests();
		FrameinMiddle();

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		SwingUtilities.updateComponentTreeUI(contentPane);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initialize() {

		setResizable(false);

		conn = sqliteConnection.dbConnector();

		setTitle("FRM - Admin Panel");
		setIconImage(Toolkit.getDefaultToolkit().getImage(AdminPanel.class.getResource("/img/mainicon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				FRM frame = new FRM();
				frame.setVisible(true);
				dispose();

			}
		});
		button.setIcon(new ImageIcon(AdminPanel.class.getResource("/img/back1.png")));
		button.setFont(new Font("Tahoma", Font.PLAIN, 18));
		button.setBounds(617, 400, 59, 60);
		contentPane.add(button);

		JLabel label = new JLabel("Search by");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Tahoma", Font.BOLD, 16));
		label.setBounds(10, 11, 85, 31);
		contentPane.add(label);

		JComboBox<String> comboBoxSearch = new JComboBox<String>();
		comboBoxSearch.setModel(new DefaultComboBoxModel<String>(
				new String[] { "Serial", "Category", "FoodName", "Ingredients", "Procedure", "NutritionalValue" }));
		comboBoxSearch.setToolTipText("");
		comboBoxSearch.setFont(new Font("Tahoma", Font.PLAIN, 16));
		comboBoxSearch.setBounds(105, 11, 118, 31);
		contentPane.add(comboBoxSearch);

		textPaneSearch = new JTextPane();
		textPaneSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

				try {

					String selection = (String) comboBoxSearch.getSelectedItem();
					String query = "select * from FoodList where " + selection + "=?";
					System.out.println(query);
					PreparedStatement pst = conn.prepareStatement(query);
					pst.setString(1, textPaneSearch.getText());
					ResultSet rs = pst.executeQuery();
					table2.setModel(DbUtils.resultSetToTableModel(rs));
					pst.close();
					rs.close();

				} catch (Exception ef) {
					ef.printStackTrace();
				}

			}
		});
		textPaneSearch.setBounds(252, 11, 148, 31);
		contentPane.add(textPaneSearch);

		JButton btnRefreshFood = new JButton("");
		btnRefreshFood.setIcon(new ImageIcon(AdminPanel.class.getResource("/img/refresh.png")));
		btnRefreshFood.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

					String query = "select * from FoodList";
					PreparedStatement pst = conn.prepareStatement(query);
					ResultSet rs = pst.executeQuery();

					textPaneSerial1.setText("");
					textPaneCategory1.setText("");
					textPaneFoodName1.setText("");
					textPaneProcedure1.setText("");
					textPaneNutritionalValue1.setText("");
					textPaneIngredients1.setText("");
					textPaneSearch.setText("");
					// lblImage1..setIcon(format);

					table2.setModel(DbUtils.resultSetToTableModel(rs));
					// table3.setModel(DbUtils.resultSetToTableModel(rs2));
					pst.close();
					rs.close();
				} catch (Exception e1) {
					e1.printStackTrace();

					refreshTable();
				}

			}
		});

		btnRefreshFood.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnRefreshFood.setBounds(705, 11, 59, 60);
		contentPane.add(btnRefreshFood);

		JLabel label_1 = new JLabel("Recipe Num.");
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_1.setBounds(10, 226, 98, 31);
		contentPane.add(label_1);

		JLabel label_2 = new JLabel("Food Name");
		label_2.setForeground(Color.WHITE);
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_2.setBounds(142, 226, 98, 31);
		contentPane.add(label_2);

		JLabel label_3 = new JLabel("Food Category");
		label_3.setForeground(Color.WHITE);
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_3.setBounds(300, 226, 105, 31);
		contentPane.add(label_3);

		textPaneSerial1 = new JTextPane();
		textPaneSerial1.setBounds(10, 268, 85, 31);
		contentPane.add(textPaneSerial1);

		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(142, 268, 130, 31);
		contentPane.add(scrollPane_3);

		textPaneFoodName1 = new JTextPane();
		scrollPane_3.setViewportView(textPaneFoodName1);

		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(300, 268, 148, 31);
		contentPane.add(scrollPane_4);

		textPaneCategory1 = new JTextPane();
		scrollPane_4.setViewportView(textPaneCategory1);

		JLabel label_4 = new JLabel("Ingreditents");
		label_4.setForeground(Color.WHITE);
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label_4.setBounds(10, 310, 98, 31);
		contentPane.add(label_4);

		JLabel label_5 = new JLabel("Procedure");
		label_5.setForeground(Color.WHITE);
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label_5.setBounds(142, 310, 98, 31);
		contentPane.add(label_5);

		JLabel label_6 = new JLabel("Nutritional Value");
		label_6.setForeground(Color.WHITE);
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label_6.setBounds(355, 310, 130, 31);
		contentPane.add(label_6);

		JLabel label_7 = new JLabel("Recipe Picture");
		label_7.setForeground(Color.WHITE);
		label_7.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label_7.setBounds(627, 164, 118, 31);
		contentPane.add(label_7);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 348, 96, 60);
		contentPane.add(scrollPane);

		textPaneIngredients1 = new JTextPane();
		scrollPane.setViewportView(textPaneIngredients1);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(142, 348, 200, 60);
		contentPane.add(scrollPane_1);

		textPaneProcedure1 = new JTextPane();
		scrollPane_1.setViewportView(textPaneProcedure1);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(355, 348, 126, 60);
		contentPane.add(scrollPane_2);

		textPaneNutritionalValue1 = new JTextPane();
		scrollPane_2.setViewportView(textPaneNutritionalValue1);

		btnExit = new JButton("");
		btnExit.setIcon(new ImageIcon(AdminPanel.class.getResource("/img/Exit.png")));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnExit.setBounds(686, 400, 59, 60);
		contentPane.add(btnExit);

		JButton btnAdd = new JButton("");
		btnAdd.setFont(new Font("Dialog", Font.BOLD, 12));
		btnAdd.setIcon(new ImageIcon(AdminPanel.class.getResource("/img/Add.png")));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					// String query = " insert into FoodList where
					// Serial='"+textFieldSerial.getText()+",
					// Category="+textFieldCategory.getText()+",FoodName="+textFieldFoodName.getText()+"'Ingredients="+textFieldIngredients.getText()+"'";
					String query = " insert into FoodList (Serial,Category,FoodName,Ingredients,Procedure,NutritionalValue,Image) values (?,?,?,?,?,?,?)";
					PreparedStatement pst = conn.prepareStatement(query);

					// InputStream is = new FileInputStream(new File(ss));
					// byte[] bt = new byte[is.available()];

					if (textPaneSerial1.getText().equals("")) {
						pst.setString(1, null);

					} else {
						pst = conn.prepareStatement(query);
						pst.setString(1, textPaneSerial1.getText());
					}
					// pst.setString(1,textPaneSerial1.getText());
					pst.setString(2, textPaneCategory1.getText());
					pst.setString(3, textPaneFoodName1.getText());
					pst.setString(4, textPaneIngredients1.getText());
					pst.setString(5, textPaneProcedure1.getText());
					pst.setString(6, textPaneNutritionalValue1.getText());
					pst.setBytes(7, image_detail);
					// pst.setBytes(7, bt);
					// pst.setBinaryStream(7, is, is.available());
					// byte[]imagedata = query.getBytes("Image");
					// format = new ImageIcon (imagedata);
					// textFieldImage.setAction(format);

					pst.execute();

					JOptionPane.showMessageDialog(null, "Recipe Saved Successfully");

					pst.close();

				} catch (Exception es) {
					es.printStackTrace();
				}

				refreshTable();
			}
		});
		btnAdd.setBounds(508, 258, 44, 41);
		contentPane.add(btnAdd);

		JButton btnEdit = new JButton("");
		btnEdit.setFont(new Font("Dialog", Font.BOLD, 12));
		btnEdit.setIcon(new ImageIcon(AdminPanel.class.getResource("/img/Update.png")));
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

					// String query = " insert into FoodList where
					// Serial='"+textFieldSerial.getText()+",
					// Category="+textFieldCategory.getText()+",FoodName="+textFieldFoodName.getText()+"'Ingredients="+textFieldIngredients.getText()+"'";
					String query = "Update FoodList set Serial ='" + textPaneSerial1.getText() + "',Category='"
							+ textPaneCategory1.getText() + "',FoodName='" + textPaneFoodName1.getText()
							+ "',Ingredients='" + textPaneIngredients1.getText() + "',Procedure='"
							+ textPaneProcedure1.getText() + "',NutritionalValue='"
							+ textPaneNutritionalValue1.getText() + "' where Serial ='" + textPaneSerial1.getText()
							+ "'";
					PreparedStatement pst = conn.prepareStatement(query);

					pst.execute();

					JOptionPane.showMessageDialog(null, "Recipe Updated Successfully");

					pst.close();

				} catch (Exception es) {
					es.printStackTrace();
				}

				refreshTable();
			}
		});
		btnEdit.setBounds(508, 314, 44, 41);
		contentPane.add(btnEdit);

		JButton btnDelete = new JButton("");
		btnDelete.setFont(new Font("Dialog", Font.BOLD, 12));
		btnDelete.setIcon(new ImageIcon(AdminPanel.class.getResource("/img/Delete.png")));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int action = JOptionPane.showConfirmDialog(null, "Do You Really Want To Delete This Recipe ?", "Delete",
						JOptionPane.YES_NO_CANCEL_OPTION);
				if (action == 0) {
					try {
						String query = "delete from FoodList where Serial='" + textPaneSerial1.getText() + "'";
						PreparedStatement pst = conn.prepareStatement(query);
						pst.execute();

						JOptionPane.showMessageDialog(null, "Recipe Deleted Successfully");

						pst.close();
					} catch (Exception ed) {
						ed.printStackTrace();
					}
					refreshTable();
				}
			}
		});
		btnDelete.setBounds(508, 373, 44, 41);
		contentPane.add(btnDelete);

		comboBoxSearch2 = new JComboBox<String>();
		comboBoxSearch2.setModel(new DefaultComboBoxModel(new String[] { "Select Food" }));
		comboBoxSearch2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent args0) {

				try {
					String query = "select * from FoodList Where FoodName=?";
					PreparedStatement pst = conn.prepareStatement(query);
					pst.setString(1, (String) comboBoxSearch2.getSelectedItem());
					ResultSet rs = pst.executeQuery();

					while (rs.next()) {
						textPaneSerial1.setText(rs.getString("Serial"));
						textPaneCategory1.setText(rs.getString("Category"));
						textPaneFoodName1.setText(rs.getString("FoodName"));
						textPaneIngredients1.setText(rs.getString("Ingredients"));
						textPaneProcedure1.setText(rs.getString("Procedure"));
						textPaneNutritionalValue1.setText(rs.getString("NutritionalValue"));
						// textFieldImage.setText(rs.getString("Image"));
						byte[] imagedata = rs.getBytes("Image");
						format = new ImageIcon(imagedata);
						lblImage1.setIcon(format);

					}
					pst.close();
					rs.close();

				} catch (Exception ef) {
					ef.printStackTrace();
				}

			}
		});
		comboBoxSearch2.setToolTipText("");
		comboBoxSearch2.setBounds(532, 13, 126, 31);
		contentPane.add(comboBoxSearch2);

		JScrollPane scrollPaneTable2 = new JScrollPane();
		scrollPaneTable2.setBounds(20, 53, 603, 142);
		contentPane.add(scrollPaneTable2);

		table2 = new JTable();
		table2.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					int row = table2.getSelectedRow();
					String Serial_ = (table2.getModel().getValueAt(row, 0)).toString();

					String query = "select * from FoodList where Serial='" + Serial_ + "'";
					PreparedStatement pst = conn.prepareStatement(query);

					ResultSet rs2 = pst.executeQuery();

					// pst.setString(13, (String)comboBox2.getSelectedItem());

					while (rs2.next()) {
						textPaneSerial1.setText(rs2.getString("Serial"));
						textPaneCategory1.setText(rs2.getString("Category"));
						textPaneFoodName1.setText(rs2.getString("FoodName"));
						textPaneIngredients1.setText(rs2.getString("Ingredients"));
						textPaneProcedure1.setText(rs2.getString("Procedure"));
						textPaneNutritionalValue1.setText(rs2.getString("NutritionalValue"));

						byte[] imagedata = rs2.getBytes("Image");
						format = new ImageIcon(imagedata);
						lblImage1.setIcon(format);

					}
					pst.close();
					rs2.close();
				}

				catch (Exception ef) {
					ef.printStackTrace();
				}

			}
		});

		scrollPaneTable2.setViewportView(table2);

		JButton btnAttachImage = new JButton("Image");
		btnAttachImage.setIcon(new ImageIcon(AdminPanel.class.getResource("/img/attach icon.png")));
		btnAttachImage.setFont(new Font("Tahoma", Font.ITALIC, 14));
		btnAttachImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
				FileNameExtensionFilter filter = new FileNameExtensionFilter("*.IMAGE", "jpg", "gif", "png");
				fileChooser.addChoosableFileFilter(filter);
				int result = fileChooser.showSaveDialog(null);
				if (result == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					try {
						@SuppressWarnings("resource")
						FileInputStream fis = new FileInputStream(selectedFile);
						ByteArrayOutputStream bos = new ByteArrayOutputStream();

						byte[] buf = new byte[1024];
						for (int readNum; (readNum = fis.read(buf)) != -1;) {
							bos.write(buf, 0, readNum);
						}

						image_detail = bos.toByteArray();
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					// String path = selectedFile.getAbsolutePath();
					// label.setIcon(ResizeImage(path));
					// s = path;
				} else if (result == JFileChooser.CANCEL_OPTION) {
					System.out.println("No Data");
				}

				btnAttachImage.setText(filename);
			}

		});
		btnAttachImage.setForeground(Color.DARK_GRAY);
		btnAttachImage.setBounds(617, 348, 130, 43);
		contentPane.add(btnAttachImage);

		JDesktopPane desktopPaneImage1 = new JDesktopPane();
		desktopPaneImage1.setBounds(584, 201, 200, 140);
		contentPane.add(desktopPaneImage1);

		lblImage1 = new JLabel("");
		lblImage1.setBounds(0, 0, 200, 140);
		desktopPaneImage1.add(lblImage1);

		JLabel SelectionBox = new JLabel("Selection Box");
		SelectionBox.setForeground(Color.WHITE);
		SelectionBox.setFont(new Font("Tahoma", Font.BOLD, 14));
		SelectionBox.setBounds(424, 11, 98, 31);
		contentPane.add(SelectionBox);

		JLabel label_8 = new JLabel("200*140");
		label_8.setForeground(Color.WHITE);
		label_8.setFont(new Font("Tahoma", Font.PLAIN, 10));
		label_8.setBounds(740, 164, 44, 35);
		contentPane.add(label_8);

		JLabel lblYouHave = new JLabel("You Have");
		lblYouHave.setForeground(Color.LIGHT_GRAY);
		lblYouHave.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblYouHave.setBounds(24, 432, 76, 17);
		contentPane.add(lblYouHave);

		label_9 = new JLabel("0");
		label_9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				RequestFillUp frame = new RequestFillUp();
				frame.setVisible(true);
				dispose();
				// adminpanelframe.setVisible(false);

			}
		});
		label_9.setForeground(Color.WHITE);
		label_9.setBounds(105, 431, 37, 19);
		contentPane.add(label_9);
		label_9.setFont(new Font("Tahoma", Font.BOLD, 16));

		JLabel lblRequests = new JLabel("New Recipe Requests");
		lblRequests.setForeground(Color.LIGHT_GRAY);
		lblRequests.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblRequests.setBounds(127, 431, 184, 19);
		contentPane.add(lblRequests);

		Box horizontalBox = Box.createHorizontalBox();
		horizontalBox.setBorder(new LineBorder(Color.WHITE));
		horizontalBox.setBounds(10, 419, 310, 41);
		contentPane.add(horizontalBox);

		Box verticalBox = Box.createVerticalBox();
		verticalBox.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.LIGHT_GRAY));
		verticalBox.setBounds(497, 244, 66, 186);
		contentPane.add(verticalBox);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 794, 471);
		contentPane.add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon(AdminPanel.class.getResource("/img/DR.png")));
		refreshTable();
		fillComboBox();

	}
}
