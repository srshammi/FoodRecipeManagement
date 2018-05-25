package FoodRecipeManagement;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JLabel;

public class Aboutus extends JFrame {

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
					Aboutus frame = new Aboutus();
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
	public Aboutus()

	{
		initilize();
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

	public void initilize() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Aboutus.class.getResource("/img/mainicon.png")));
		setTitle("FRM - About Us");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JButton button = new JButton("");
		button.setBounds(711, 402, 73, 49);
		// Image img=new
		// ImageIcon(this.getClass().getResource("/img/Back3.png")).getImage();
		button.setIcon(new ImageIcon(Aboutus.class.getResource("/img/back (1).png")));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FRM frame = new FRM();
				frame.setVisible(true);
				dispose();
			}
		});
		contentPane.setLayout(null);
		button.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPane.add(button);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setForeground(new Color(148, 0, 211));
		tabbedPane.setFont(new Font("Tahoma", Font.BOLD, 18));
		tabbedPane.setBounds(10, 30, 764, 361);
		contentPane.add(tabbedPane);

		JPanel panel = new JPanel();
		tabbedPane.addTab("About us", null, panel, null);
		panel.setLayout(null);

		JLabel lblNameLazy = new JLabel("LAZY FIVE");
		lblNameLazy.setForeground(Color.RED);
		lblNameLazy.setFont(new Font("Tahoma", Font.BOLD, 45));
		lblNameLazy.setBounds(254, 11, 234, 55);
		panel.add(lblNameLazy);

		JLabel lblLazy = new JLabel("");
		Image img1 = new ImageIcon(this.getClass().getResource("/img/Lazy.png")).getImage();
		lblLazy.setIcon(new ImageIcon(img1));
		lblLazy.setBounds(161, 75, 426, 239);
		panel.add(lblLazy);

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Afiqur", null, panel_1, null);
		panel_1.setLayout(null);

		JLabel lblPiash = new JLabel("");
		Image img11 = new ImageIcon(this.getClass().getResource("/img/Piash.jpg")).getImage();
		lblPiash.setIcon(new ImageIcon(img11));
		lblPiash.setBounds(485, 11, 264, 303);
		panel_1.add(lblPiash);

		JLabel lblNameAfiqur = new JLabel("Name : Afiqur Rahman Piash");
		lblNameAfiqur.setForeground(new Color(220, 20, 60));
		lblNameAfiqur.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNameAfiqur.setBounds(10, 75, 290, 25);
		panel_1.add(lblNameAfiqur);

		JLabel lblId = new JLabel("ID : 152-15-5670");
		lblId.setForeground(new Color(220, 20, 60));
		lblId.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblId.setBounds(10, 111, 179, 25);
		panel_1.add(lblId);

		JLabel lblEmailAfiqurdiuedubd = new JLabel("Email : piash15-5670@diu.edu.bd");
		lblEmailAfiqurdiuedubd.setForeground(new Color(220, 20, 60));
		lblEmailAfiqurdiuedubd.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblEmailAfiqurdiuedubd.setBounds(10, 147, 341, 25);
		panel_1.add(lblEmailAfiqurdiuedubd);

		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Nas", null, panel_2, null);
		panel_2.setLayout(null);

		JLabel lblNas = new JLabel("");
		Image img111 = new ImageIcon(this.getClass().getResource("/img/Nas.jpeg")).getImage();
		lblNas.setIcon(new ImageIcon(img111));
		lblNas.setBounds(485, 11, 264, 303);
		panel_2.add(lblNas);

		JLabel lblNameNafees = new JLabel("Name : Nafees Ahmed (Nas)");
		lblNameNafees.setForeground(new Color(220, 20, 60));
		lblNameNafees.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNameNafees.setBounds(10, 75, 284, 25);
		panel_2.add(lblNameNafees);

		JLabel lblId_1 = new JLabel("ID : 152-15-5824");
		lblId_1.setForeground(new Color(220, 20, 60));
		lblId_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblId_1.setBounds(10, 111, 179, 25);
		panel_2.add(lblId_1);

		JLabel lblEmailShihabdiuedubd = new JLabel("Email : shihab15-5824@diu.edu.bd");
		lblEmailShihabdiuedubd.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblEmailShihabdiuedubd.setForeground(new Color(220, 20, 60));
		lblEmailShihabdiuedubd.setBounds(10, 147, 354, 25);
		panel_2.add(lblEmailShihabdiuedubd);

		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Bijoy", null, panel_3, null);
		panel_3.setLayout(null);

		JLabel lblBijoy = new JLabel("");
		Image img1111 = new ImageIcon(this.getClass().getResource("/img/Bijoy.jpg")).getImage();
		lblBijoy.setIcon(new ImageIcon(img1111));
		lblBijoy.setBounds(488, 11, 264, 303);
		panel_3.add(lblBijoy);

		JLabel lblNameBijoy = new JLabel("Name : Bijoy Ghosh");
		lblNameBijoy.setForeground(new Color(220, 20, 60));
		lblNameBijoy.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNameBijoy.setBounds(10, 75, 197, 25);
		panel_3.add(lblNameBijoy);

		JLabel lblId_2 = new JLabel("ID : 152-15-5556");
		lblId_2.setForeground(new Color(220, 20, 60));
		lblId_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblId_2.setBounds(10, 111, 179, 25);
		panel_3.add(lblId_2);

		JLabel lblEmailGhoshdiuedubd = new JLabel("Email : ghosh15-5666@diu.edu.bd");
		lblEmailGhoshdiuedubd.setForeground(new Color(220, 20, 60));
		lblEmailGhoshdiuedubd.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblEmailGhoshdiuedubd.setBounds(10, 147, 348, 25);
		panel_3.add(lblEmailGhoshdiuedubd);

		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("Shaila", null, panel_4, null);
		panel_4.setLayout(null);

		JLabel lblShaila = new JLabel("");
		Image img11111 = new ImageIcon(this.getClass().getResource("/img/Shaila.jpg")).getImage();
		lblShaila.setIcon(new ImageIcon(img11111));
		lblShaila.setBounds(527, 11, 264, 303);
		panel_4.add(lblShaila);

		JLabel lblNameShaila = new JLabel("Name : Shaila Rahman Shammi");
		lblNameShaila.setForeground(new Color(220, 20, 60));
		lblNameShaila.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNameShaila.setBounds(10, 75, 317, 25);
		panel_4.add(lblNameShaila);

		JLabel lblId_3 = new JLabel("ID : 152-15-5840");
		lblId_3.setForeground(new Color(220, 20, 60));
		lblId_3.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblId_3.setBounds(10, 111, 179, 25);
		panel_4.add(lblId_3);

		JLabel lblEmailShailadiuedubd = new JLabel("Email : shammi15-5840@diu.edu.bd");
		lblEmailShailadiuedubd.setForeground(new Color(220, 20, 60));
		lblEmailShailadiuedubd.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblEmailShailadiuedubd.setBounds(10, 147, 366, 25);
		panel_4.add(lblEmailShailadiuedubd);

		JPanel panel_5 = new JPanel();
		tabbedPane.addTab("Meher", null, panel_5, null);
		panel_5.setLayout(null);

		JLabel lblMeher = new JLabel("");
		Image img111111 = new ImageIcon(this.getClass().getResource("/img/Meher.jpg")).getImage();
		lblMeher.setIcon(new ImageIcon(img111111));
		lblMeher.setBounds(485, 11, 264, 303);
		panel_5.add(lblMeher);

		JLabel lblNameMeher = new JLabel("Name : Meher Niger");
		lblNameMeher.setForeground(new Color(220, 20, 60));
		lblNameMeher.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNameMeher.setBounds(10, 75, 202, 25);
		panel_5.add(lblNameMeher);

		JLabel lblId_4 = new JLabel("ID : 152-15-5566");
		lblId_4.setForeground(new Color(220, 20, 60));
		lblId_4.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblId_4.setBounds(10, 111, 179, 25);
		panel_5.add(lblId_4);

		JLabel lblEmailNigerdiuedubd = new JLabel("Email : niger15-5566@diu.edu.bd");
		lblEmailNigerdiuedubd.setForeground(new Color(220, 20, 60));
		lblEmailNigerdiuedubd.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblEmailNigerdiuedubd.setBounds(10, 147, 340, 25);
		panel_5.add(lblEmailNigerdiuedubd);

		JLabel lblAboutBackground = new JLabel("");
		// Image img1111111=new
		// ImageIcon(this.getClass().getResource("/img/Background6.jpg")).getImage();
		lblAboutBackground.setIcon(new ImageIcon(Aboutus.class.getResource("/img/bbb.jpg")));
		lblAboutBackground.setBounds(0, 0, 794, 471);
		contentPane.add(lblAboutBackground);
	}
}
