package FoodRecipeManagement;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FirstFrame extends JFrame implements ActionListener {

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
					FirstFrame frame = new FirstFrame();
					frame.setVisible(true);

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

	int time = 0;
	Timer tm = new Timer(1000, this);

	public void logic() {
		time++;
		// lblNewLabel1.setText(""+time);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (time != 2) {
			FrameinMiddle();
			logic();

		} else {
			// JOptionPane.showMessageDialog(null, "Time UP");
			tm.stop();
			dispose();
			FRM frame = new FRM();
			frame.setVisible(true);

		}

		repaint();
	}

	/**
	 * Create the frame.
	 */
	public FirstFrame() {

		tm.start();
		setType(Type.POPUP);
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(FirstFrame.class.getResource("/img/mainicon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 310, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(FirstFrame.class.getResource("/img/FoodSplash.png")));
		lblNewLabel.setBounds(0, 0, 294, 271);
		contentPane.add(lblNewLabel);
	}

}
