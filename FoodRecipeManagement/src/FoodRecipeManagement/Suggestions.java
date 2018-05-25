package FoodRecipeManagement;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import javax.swing.JButton;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;
import java.net.URISyntaxException;
import java.io.IOException;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Color;

public class Suggestions extends JFrame {

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
					Suggestions suggestionsframe = new Suggestions();
					suggestionsframe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
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
	 * Create the frame.
	 */
	public Suggestions() {

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

	public void initialize()

	{

		setResizable(false);
		setTitle("Food Suggestions");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Suggestions.class.getResource("/img/mainicon.png")));
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
		button.setIcon(new ImageIcon(Suggestions.class.getResource("/img/back1.png")));
		button.setFont(new Font("Tahoma", Font.PLAIN, 18));
		button.setBounds(715, 11, 59, 60);
		contentPane.add(button);

		JLabel lblNewLabel = new JLabel("EURO RECIPE");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (e.getClickCount() > 0) {
					if (Desktop.isDesktopSupported()) {
						Desktop desktop = Desktop.getDesktop();
						try {
							URI uri = new URI("http://allrecipes.com/recipes/231/world-cuisine/european/");
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

		lblNewLabel.setBounds(648, 264, 126, 22);
		contentPane.add(lblNewLabel);

		JLabel lblTopReciepie = new JLabel("TOP 15 RECIEPIE SITE (AUGUST 2016)");
		lblTopReciepie.setForeground(Color.WHITE);
		lblTopReciepie.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTopReciepie.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() > 0) {
					if (Desktop.isDesktopSupported()) {
						Desktop desktop = Desktop.getDesktop();
						try {
							URI uri = new URI("http://www.ebizmba.com/articles/recipe-websites");
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
		lblTopReciepie.setBounds(427, 166, 347, 22);
		contentPane.add(lblTopReciepie);

		JLabel lblTopBdRestaurants = new JLabel("TOP BD RESTAURANTS WEB SITE");
		lblTopBdRestaurants.setForeground(Color.WHITE);
		lblTopBdRestaurants.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTopBdRestaurants.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (e.getClickCount() > 0) {
					if (Desktop.isDesktopSupported()) {
						Desktop desktop = Desktop.getDesktop();
						try {
							URI uri = new URI("https://www.google.com.bd/#q=tob+bangladeshi+recipe+website");
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
		lblTopBdRestaurants.setBounds(474, 231, 300, 22);
		contentPane.add(lblTopBdRestaurants);

		JLabel lblBangladeshiRestuarantsList = new JLabel("BANGLADESHI RESTUARANTS LIST");
		lblBangladeshiRestuarantsList.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblBangladeshiRestuarantsList.setForeground(Color.WHITE);
		lblBangladeshiRestuarantsList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() > 0) {
					if (Desktop.isDesktopSupported()) {
						Desktop desktop = Desktop.getDesktop();
						try {
							URI uri = new URI("http://www.mediabangladesh.net/list-of-restaurants-in-bangladesh/");
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
		lblBangladeshiRestuarantsList.setBounds(451, 199, 323, 22);
		contentPane.add(lblBangladeshiRestuarantsList);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(Suggestions.class.getResource("/img/BD.png")));
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() > 0) {
					if (Desktop.isDesktopSupported()) {
						Desktop desktop = Desktop.getDesktop();
						try {
							URI uri = new URI("http://en.bdcuisine.com/");
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
		lblNewLabel_1.setBounds(389, 351, 85, 29);
		contentPane.add(lblNewLabel_1);

		JLabel label = new JLabel("");
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (e.getClickCount() > 0) {
					if (Desktop.isDesktopSupported()) {
						Desktop desktop = Desktop.getDesktop();
						try {
							URI uri = new URI("https://www.foodpanda.com.bd");
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
		label.setIcon(new ImageIcon(Suggestions.class.getResource("/img/Foodpanda.png")));
		label.setBounds(389, 271, 85, 29);
		contentPane.add(label);

		JLabel label_1 = new JLabel("");
		label_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() > 0) {
					if (Desktop.isDesktopSupported()) {
						Desktop desktop = Desktop.getDesktop();
						try {
							URI uri = new URI("www.khanapakana.com");
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
		label_1.setIcon(new ImageIcon(Suggestions.class.getResource("/img/Khanapakana.png")));
		label_1.setBounds(389, 311, 85, 29);
		contentPane.add(label_1);

		JLabel label_2 = new JLabel("");
		label_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (e.getClickCount() > 0) {
					if (Desktop.isDesktopSupported()) {
						Desktop desktop = Desktop.getDesktop();
						try {
							URI uri = new URI("http://www.dhakasnob.com/");
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
		label_2.setIcon(new ImageIcon(Suggestions.class.getResource("/img/Dhakasnob.png")));
		label_2.setBounds(389, 391, 85, 29);
		contentPane.add(label_2);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() > 0) {
					if (Desktop.isDesktopSupported()) {
						Desktop desktop = Desktop.getDesktop();
						try {
							URI uri = new URI(
									"http://www.fitnessmagazine.com/recipes/healthy-eating/superfoods/the-10-healthiest-foods-on-the-planet/");
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
		lblNewLabel_2.setIcon(new ImageIcon(Suggestions.class.getResource("/img/toppp.jpg")));
		lblNewLabel_2.setBounds(484, 297, 310, 163);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (e.getClickCount() > 0) {
					if (Desktop.isDesktopSupported()) {
						Desktop desktop = Desktop.getDesktop();
						try {
							URI uri = new URI("http://www.thekitchn.com/");
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
		lblNewLabel_4.setIcon(new ImageIcon(Suggestions.class.getResource("/img/MyKitchen_2.jpg")));
		lblNewLabel_4.setBounds(374, 431, 100, 29);
		contentPane.add(lblNewLabel_4);

		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(Suggestions.class.getResource("/img/iStock.jpg")));
		lblNewLabel_3.setBounds(0, 0, 794, 471);
		contentPane.add(lblNewLabel_3);

	}
}
