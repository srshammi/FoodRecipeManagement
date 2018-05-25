package FoodRecipeManagement;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FRM extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FRM frame = new FRM();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FRM() {
		initialize();

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		SwingUtilities.updateComponentTreeUI(contentPane);

		JButton buttonRecipe = new JButton("Recipes");
		buttonRecipe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Recipes recipesframe = new Recipes();
				recipesframe.setVisible(true);
				dispose();
			}
		});
		buttonRecipe.setIcon(new ImageIcon(FRM.class.getResource("/img/Recipes-Title.png")));
		buttonRecipe.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 20));
		buttonRecipe.setBounds(32, 34, 151, 60);
		contentPane.add(buttonRecipe);

		JButton buttonRequest = new JButton("Request");
		buttonRequest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Requests frame = new Requests();
				frame.setVisible(true);
				dispose();
			}
		});
		buttonRequest.setIcon(new ImageIcon(FRM.class.getResource("/img/request_icon_1.png")));
		buttonRequest.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 20));
		buttonRequest.setBounds(32, 105, 151, 60);
		contentPane.add(buttonRequest);

		JButton buttonAdvice = new JButton("Advice");
		buttonAdvice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Suggestions suggestionsframe = new Suggestions();
				suggestionsframe.setVisible(true);
				dispose();
			}
		});
		buttonAdvice.setIcon(new ImageIcon(FRM.class.getResource("/img/sug.gif")));
		buttonAdvice.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 20));
		buttonAdvice.setBounds(32, 176, 151, 60);
		contentPane.add(buttonAdvice);

		JButton buttonAdmin = new JButton("Admin");
		buttonAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login loginframe = new Login();
				loginframe.setVisible(true);
				dispose();
			}
		});
		buttonAdmin.setIcon(new ImageIcon(FRM.class.getResource("/img/conf.png")));
		buttonAdmin.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 20));
		buttonAdmin.setBounds(32, 247, 151, 60);
		contentPane.add(buttonAdmin);

		JButton buttonAboutus = new JButton("About Us");
		buttonAboutus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Aboutus aboutusframe = new Aboutus();
				aboutusframe.setVisible(true);
				dispose();
			}
		});
		buttonAboutus.setIcon(new ImageIcon(FRM.class.getResource("/img/About-Us.jpg")));
		buttonAboutus.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 20));
		buttonAboutus.setBounds(32, 317, 151, 60);
		contentPane.add(buttonAboutus);

		JButton btnExit = new JButton(" Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setIcon(new ImageIcon(FRM.class.getResource("/img/Exit.png")));
		btnExit.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 20));
		btnExit.setBounds(32, 389, 151, 60);
		contentPane.add(btnExit);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(FRM.class.getResource("/img/background.jpg")));
		lblNewLabel.setBounds(0, 0, 794, 471);
		contentPane.add(lblNewLabel);

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

	public void initialize() {
		setTitle("FRM - Main");
		setIconImage(Toolkit.getDefaultToolkit().getImage(FRM.class.getResource("/img/mainicon.png")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}
}
