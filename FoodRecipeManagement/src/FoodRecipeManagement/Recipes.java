package FoodRecipeManagement;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import net.proteanit.sql.DbUtils;
import java.sql.Connection;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import javax.swing.AbstractButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Recipes extends JFrame {

	/**
	 * 
	 */

	static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Recipes recipesframe = new Recipes();
					recipesframe.setVisible(true);
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

	JTextPane textPaneSerial_1;
	JTextPane textPaneCategory_1;
	JTextPane textPaneFoodName_1;
	JTextPane textPaneProcedure_1;
	JTextPane textPaneNutritionalValue_1;
	JTextPane textPaneIngredients_1;
	JScrollPane scrollPane;
	private JLabel lblNewLabelmage;
	private ImageIcon format = null;
	private JPanel contentPane;
	private JTable tableFoodList;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBoxFoodName;
	private JComboBox<String> comboBoxFoodSearch;

	String filename = null;
	int s = 0;
	byte[] image = null;
	AbstractButton path;

	@SuppressWarnings("unchecked")
	public void fillComboBox() {
		try {
			String query = "select FoodName from FoodList";
			PreparedStatement pst = conn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				comboBoxFoodName.addItem(rs.getString("FoodName"));
			}

			pst.close();
			rs.close();

		} catch (Exception e) {
			e.printStackTrace();

		}
		refreshTable();
	}

	public void refreshTable() {

		try {
			String query = "select Serial,FoodName,Category from FoodList";
			PreparedStatement pst = conn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();

			tableFoodList.setModel(DbUtils.resultSetToTableModel(rs));

			pst.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
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

	public Recipes() {
		setResizable(false);
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

	@SuppressWarnings({ "rawtypes", "unchecked" })

	private void initialize() {

		conn = sqliteConnection.dbConnector();

		setTitle("Food Recipe");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Recipes.class.getResource("/img/mainicon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnBack = new JButton("");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				FRM frame = new FRM();
				frame.setVisible(true);
				dispose();

			}

		});
		btnBack.setIcon(new ImageIcon(Recipes.class.getResource("/img/back (1).png")));
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnBack.setBounds(715, 11, 59, 60);
		contentPane.add(btnBack);

		tableFoodList = new JTable();
		scrollPane = new JScrollPane();
		scrollPane.setBounds(450, 11, 204, 133);
		contentPane.add(scrollPane);

		tableFoodList.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e0) {

				try {

					int row = tableFoodList.getSelectedRow();
					String Serial_ = (tableFoodList.getModel().getValueAt(row, 0)).toString();
					// String query = "select * from FoodList Where FoodName=?";
					String query = "select * from FoodList Where Serial='" + Serial_ + "'";
					PreparedStatement pst = conn.prepareStatement(query);

					ResultSet rs = pst.executeQuery();

					while (rs.next()) {

						textPaneSerial_1.setText(rs.getString("Serial"));
						textPaneCategory_1.setText(rs.getString("Category"));
						textPaneFoodName_1.setText(rs.getString("FoodName"));
						textPaneIngredients_1.setText(rs.getString("Ingredients"));
						textPaneProcedure_1.setText(rs.getString("Procedure"));
						textPaneNutritionalValue_1.setText(rs.getString("NutritionalValue"));
						byte[] imagedata = rs.getBytes("Image");
						format = new ImageIcon(imagedata);
						lblNewLabelmage.setIcon(format);

					}

					pst.close();
					rs.close();

				} catch (Exception e) {
					e.printStackTrace();
				}
				// refreshTable();
			}
		});

		scrollPane.setViewportView(tableFoodList);

		JLabel lblSearcyBy = new JLabel("Search by");
		lblSearcyBy.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSearcyBy.setBounds(27, 11, 85, 31);
		contentPane.add(lblSearcyBy);

		comboBoxFoodSearch = new JComboBox<String>();
		comboBoxFoodSearch.setModel(new DefaultComboBoxModel(new String[] { "FoodName", "Category", "Serial" }));
		comboBoxFoodSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		comboBoxFoodSearch.setFont(new Font("Tahoma", Font.PLAIN, 16));
		comboBoxFoodSearch.setToolTipText("");
		comboBoxFoodSearch.setBounds(122, 11, 118, 31);
		contentPane.add(comboBoxFoodSearch);

		JTextPane textPaneSearchBox = new JTextPane();
		textPaneSearchBox.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent arg0) {

				{

					try {
						String selection = (String) comboBoxFoodSearch.getSelectedItem();
						String query = "select * from FoodList where " + selection + "=?";
						System.out.println(query);
						PreparedStatement pst = conn.prepareStatement(query);
						pst.setString(1, textPaneSearchBox.getText());
						ResultSet rs = pst.executeQuery();
						tableFoodList.setModel(DbUtils.resultSetToTableModel(rs));
						pst.close();
						rs.close();

					} catch (Exception ef) {
						ef.printStackTrace();
					}
				}

			}
		});

		textPaneSearchBox.setBounds(269, 11, 148, 31);
		contentPane.add(textPaneSearchBox);

		JButton btnRefreshFoods = new JButton("Refresh Foods");
		btnRefreshFoods.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {

					textPaneSerial_1.setText("");
					textPaneCategory_1.setText("");
					textPaneFoodName_1.setText("");
					textPaneProcedure_1.setText("");
					textPaneNutritionalValue_1.setText("");
					textPaneIngredients_1.setText("");
					textPaneSearchBox.setText("");

					String query = "select Serial,FoodName,Category from FoodList";
					PreparedStatement pst = conn.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					// refreshTable();
					tableFoodList.setModel(DbUtils.resultSetToTableModel(rs));
					pst.execute();
					pst.close();
					rs.close();

				} catch (Exception e) {
					e.printStackTrace();
				}

				refreshTable();
			}
		});

		btnRefreshFoods.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnRefreshFoods.setBounds(663, 82, 111, 31);
		contentPane.add(btnRefreshFoods);

		JLabel lblFoodNumber = new JLabel("Recipe Num.");
		lblFoodNumber.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblFoodNumber.setBounds(27, 71, 98, 31);
		contentPane.add(lblFoodNumber);

		JLabel lblFoodName = new JLabel("Food Name");
		lblFoodName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblFoodName.setBounds(142, 71, 98, 31);
		contentPane.add(lblFoodName);

		JLabel lblFoodCategory = new JLabel("Food Category");
		lblFoodCategory.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblFoodCategory.setBounds(295, 71, 105, 31);
		contentPane.add(lblFoodCategory);

		JScrollPane scrollPane1 = new JScrollPane();
		scrollPane1.setBounds(27, 113, 85, 31);
		contentPane.add(scrollPane1);

		textPaneSerial_1 = new JTextPane();
		textPaneSerial_1.setEditable(false);
		scrollPane1.setViewportView(textPaneSerial_1);

		JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.setBounds(122, 113, 130, 31);
		contentPane.add(scrollPane2);

		textPaneFoodName_1 = new JTextPane();
		textPaneFoodName_1.setEditable(false);
		scrollPane2.setViewportView(textPaneFoodName_1);

		JScrollPane scrollPane3 = new JScrollPane();
		scrollPane3.setBounds(269, 113, 148, 31);
		contentPane.add(scrollPane3);

		textPaneCategory_1 = new JTextPane();
		textPaneCategory_1.setEditable(false);
		scrollPane3.setViewportView(textPaneCategory_1);

		JScrollPane scrollPane4 = new JScrollPane();
		scrollPane4.setBounds(27, 227, 98, 206);
		contentPane.add(scrollPane4);

		textPaneIngredients_1 = new JTextPane();
		textPaneIngredients_1.setEditable(false);
		scrollPane4.setViewportView(textPaneIngredients_1);

		JScrollPane scrollPane5 = new JScrollPane();
		scrollPane5.setBounds(157, 227, 202, 206);
		contentPane.add(scrollPane5);

		textPaneProcedure_1 = new JTextPane();
		textPaneProcedure_1.setEditable(false);
		scrollPane5.setViewportView(textPaneProcedure_1);

		JScrollPane scrollPane6 = new JScrollPane();
		scrollPane6.setBounds(399, 227, 128, 206);
		contentPane.add(scrollPane6);

		textPaneNutritionalValue_1 = new JTextPane();
		textPaneNutritionalValue_1.setEditable(false);
		scrollPane6.setViewportView(textPaneNutritionalValue_1);

		JLabel lblIngreditents = new JLabel("Ingreditents");
		lblIngreditents.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblIngreditents.setBounds(27, 185, 98, 31);
		contentPane.add(lblIngreditents);

		JLabel lblProcedure = new JLabel("Procedure");
		lblProcedure.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblProcedure.setBounds(209, 185, 98, 31);
		contentPane.add(lblProcedure);

		JLabel lblNutritionalValue = new JLabel("Nutritional Value");
		lblNutritionalValue.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNutritionalValue.setBounds(399, 185, 130, 31);
		contentPane.add(lblNutritionalValue);

		JLabel lblRecipePicture = new JLabel("Recipe Picture");
		lblRecipePicture.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblRecipePicture.setBounds(609, 185, 118, 31);
		contentPane.add(lblRecipePicture);

		JDesktopPane desktopPaneImage = new JDesktopPane();
		desktopPaneImage.setBounds(568, 239, 200, 140);
		contentPane.add(desktopPaneImage);

		lblNewLabelmage = new JLabel("");
		lblNewLabelmage.setBounds(0, 0, 200, 140);
		desktopPaneImage.add(lblNewLabelmage);

		JButton buttonExit = new JButton("");
		buttonExit.setIcon(new ImageIcon(Recipes.class.getResource("/img/Exit.png")));
		buttonExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		buttonExit.setFont(new Font("Tahoma", Font.PLAIN, 18));
		buttonExit.setBounds(715, 390, 59, 60);
		contentPane.add(buttonExit);

		comboBoxFoodName = new JComboBox<String>();
		comboBoxFoodName.setModel(new DefaultComboBoxModel(new String[] { "Select Food" }));
		comboBoxFoodName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					String query = "select * from FoodList Where FoodName=?";
					PreparedStatement pst = conn.prepareStatement(query);
					pst.setString(1, (String) comboBoxFoodName.getSelectedItem());
					ResultSet rs = pst.executeQuery();

					while (rs.next()) {
						// textField.setText(rs.getString("Serial"));
						textPaneSerial_1.setText(rs.getString("Serial"));
						textPaneCategory_1.setText(rs.getString("Category"));
						textPaneFoodName_1.setText(rs.getString("FoodName"));
						textPaneIngredients_1.setText(rs.getString("Ingredients"));
						textPaneProcedure_1.setText(rs.getString("Procedure"));
						textPaneNutritionalValue_1.setText(rs.getString("NutritionalValue"));
						byte[] imagedata = rs.getBytes("Image");
						format = new ImageIcon(imagedata);
						lblNewLabelmage.setIcon(format);

					}
					pst.close();
					rs.close();

				} catch (Exception ef) {
					ef.printStackTrace();
				}

			}
		});
		comboBoxFoodName.setToolTipText("");
		comboBoxFoodName.setBounds(664, 124, 110, 20);
		contentPane.add(comboBoxFoodName);

		JButton buttonRequest = new JButton("");
		buttonRequest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Requests frame = new Requests();
				frame.setVisible(true);
				dispose();
			}
		});
		buttonRequest.setIcon(new ImageIcon(Recipes.class.getResource("/img/RequestR.jpg")));
		buttonRequest.setFont(new Font("Tahoma", Font.PLAIN, 18));
		buttonRequest.setBounds(578, 390, 105, 60);
		contentPane.add(buttonRequest);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setIcon(new ImageIcon(Recipes.class.getResource("/img/Background5.jpg")));
		lblNewLabel.setBounds(0, 0, 794, 471);
		contentPane.add(lblNewLabel);
		refreshTable();
		fillComboBox();

	}
}
