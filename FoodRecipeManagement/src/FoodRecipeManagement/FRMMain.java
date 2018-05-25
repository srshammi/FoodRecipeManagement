package FoodRecipeManagement;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class FRMMain extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JFrame frmFoodRecipeManagement;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FRMMain window = new FRMMain();
					window.frmFoodRecipeManagement.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */

	public FRMMain() {

		initialize();

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		SwingUtilities.updateComponentTreeUI(frmFoodRecipeManagement);

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

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// FrameinMiddle();
		frmFoodRecipeManagement = new JFrame();
		frmFoodRecipeManagement.setResizable(false);
		frmFoodRecipeManagement
				.setIconImage(Toolkit.getDefaultToolkit().getImage(FRMMain.class.getResource("/img/mainicon.png")));
		frmFoodRecipeManagement.setTitle("Food Recipe Management");
		frmFoodRecipeManagement.setBounds(100, 100, 800, 500);
		frmFoodRecipeManagement.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmFoodRecipeManagement.getContentPane().setLayout(null);

		JButton btnRecipe = new JButton("Recipes");
		btnRecipe.setIcon(new ImageIcon(FRMMain.class.getResource("/img/Recipes-Title.png")));
		btnRecipe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				frmFoodRecipeManagement.setVisible(false);
				Recipes recipesframe = new Recipes();
				recipesframe.setVisible(true);

			}
		});
		btnRecipe.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnRecipe.setBounds(31, 35, 140, 60);
		frmFoodRecipeManagement.getContentPane().add(btnRecipe);

		JButton btnSuggestions = new JButton("Advice");
		btnSuggestions.setIcon(new ImageIcon(FRMMain.class.getResource("/img/sug.gif")));
		btnSuggestions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				frmFoodRecipeManagement.setVisible(false);
				Suggestions suggestionsframe = new Suggestions();
				suggestionsframe.setVisible(true);

			}
		});
		btnSuggestions.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSuggestions.setBounds(31, 177, 140, 60);
		frmFoodRecipeManagement.getContentPane().add(btnSuggestions);

		JButton btnAbout = new JButton("About Us");
		btnAbout.setIcon(new ImageIcon(FRMMain.class.getResource("/img/About-Us.jpg")));
		btnAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmFoodRecipeManagement.setVisible(false);
				Aboutus aboutusframe = new Aboutus();
				aboutusframe.setVisible(true);

			}
		});
		btnAbout.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAbout.setBounds(31, 318, 140, 60);
		frmFoodRecipeManagement.getContentPane().add(btnAbout);

		JButton btnAdminLogin = new JButton("Admin");
		btnAdminLogin.setIcon(new ImageIcon(FRMMain.class.getResource("/img/conf.png")));
		btnAdminLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				frmFoodRecipeManagement.setVisible(false);
				Login loginframe = new Login();
				loginframe.setVisible(true);

			}
		});
		btnAdminLogin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAdminLogin.setBounds(31, 248, 140, 60);
		frmFoodRecipeManagement.getContentPane().add(btnAdminLogin);

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				System.exit(0);

			}

		});
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnExit.setIcon(new ImageIcon(FRMMain.class.getResource("/img/Exit.png")));
		btnExit.setBounds(31, 390, 140, 60);
		frmFoodRecipeManagement.getContentPane().add(btnExit);

		JButton btnRequest = new JButton("Request");
		btnRequest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmFoodRecipeManagement.setVisible(false);
				Requests frame = new Requests();
				frame.setVisible(true);

			}
		});
		btnRequest.setIcon(new ImageIcon(FRMMain.class.getResource("/img/request_icon_1.png")));
		btnRequest.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnRequest.setBounds(31, 106, 140, 60);
		frmFoodRecipeManagement.getContentPane().add(btnRequest);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(FRMMain.class.getResource("/img/background.jpg")));
		lblNewLabel.setBounds(0, 0, 794, 471);
		frmFoodRecipeManagement.getContentPane().add(lblNewLabel);
	}
}
