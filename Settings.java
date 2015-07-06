import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import java.net.URL;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import javax.swing.JCheckBox;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;


public class Settings extends JFrame
{
	private static final long serialVersionUID = 123456;
	private JPanel contentPane;
	
	//Start
	public boolean bRun = false;
	
	//Staking Settings
	public JComboBox<String> mode;
	public JTextField amount;
	public JCheckBox shards;
	
	//Message Settings
	public JComboBox<String> color;
	public JComboBox<String> effect;
	public JTextField message;
	
	//Dynamic Signatures
	public JTextField username;
		
	private Image getImage(String url)
	{
		try{ return ImageIO.read(new URL(url)); }
		catch(Exception exception) { return null; }
	}
	
	@SuppressWarnings("all")
	public Settings()
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch(Exception e){}
		
		setDefaultLookAndFeelDecorated(true);
		setIconImage(getImage("http://parabot.xero.pw/img/x.png"));
		setTitle("Settings");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 510, 180);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel copyright = new JLabel("\u00A9 2014-2015 XERO.pw");
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		
		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				bRun = true;
				setVisible(false);
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(185)
							.addComponent(copyright)
							.addPreferredGap(ComponentPlacement.RELATED, 129, Short.MAX_VALUE)
							.addComponent(btnStart))
						.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 484, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(copyright)
						.addComponent(btnStart))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Start", null, panel_3, null);
		
		JLabel imageLabel = new JLabel(new ImageIcon(getImage("http://parabot.xero.pw/img/staking/gui.png")));
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addGap(18)
					.addComponent(imageLabel, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(44, Short.MAX_VALUE))
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addContainerGap()
					.addComponent(imageLabel, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(21, Short.MAX_VALUE))
		);
		panel_3.setLayout(gl_panel_3);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Staking Settings", null, panel, null);
		
		JLabel lblMode = new JLabel("Mode:");
		
		mode = new JComboBox<String>();
		mode.setModel(new DefaultComboBoxModel(new String[] {"DDS", "Box(tbd)", "Whip(tbd)", "VLS(tbd)", "Mb(tbd)"}));
		
		amount = new JTextField();
		amount.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(amount.getText().length() >= 6)
				{
					amount.setText(amount.getText().substring(0, 5));
				}
			}
		});
		amount.setColumns(6);
		
		JLabel lblNewLabel = new JLabel("Amount:");
		
		shards = new JCheckBox("Allow shards?");
		shards.setEnabled(false);
		
		JLabel lblM = new JLabel("M");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(8)
							.addComponent(lblMode)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(mode, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(amount, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblM, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(112)
							.addComponent(shards)))
					.addContainerGap(97, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMode)
						.addComponent(mode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel)
						.addComponent(amount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblM))
					.addGap(18)
					.addComponent(shards)
					.addGap(30))
		);
		panel.setLayout(gl_panel);
		
		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("Message Settings", null, panel_4, null);
		
		color = new JComboBox<String>();
		color.setModel(new DefaultComboBoxModel<String>(new String[] {"default", "cyan", "green", "purple", "red", "white", "flash1", "flash2", "flash3", "glow1", "glow2", "glow3"}));
		
		effect = new JComboBox<String>();
		effect.setModel(new DefaultComboBoxModel<String>(new String[] {"default", "scroll", "shake", "slide", "wave", "wave2"}));
		
		JLabel lblColor = new JLabel("Color:");
		
		JLabel lblEffect = new JLabel("Effect:");
		
		message = new JTextField();
		message.setColumns(10);
		
		JLabel lblMessage = new JLabel("Message:");
		GroupLayout gl_panel_4 = new GroupLayout(panel_4);
		gl_panel_4.setHorizontalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
						.addComponent(message, GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE)
						.addGroup(gl_panel_4.createSequentialGroup()
							.addComponent(lblColor)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(color, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblEffect)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(effect, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblMessage))
					.addContainerGap())
		);
		gl_panel_4.setVerticalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblColor)
						.addComponent(color, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEffect)
						.addComponent(effect, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(lblMessage)
					.addPreferredGap(ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
					.addComponent(message, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		panel_4.setLayout(gl_panel_4);
		
		JPanel panel_1 = new JPanel();
		panel_1.setEnabled(false);
		tabbedPane.addTab("Dynamic Signatures", null, panel_1, null);
		
		JLabel lblEnterYourUsername = new JLabel("Enter your forum username below:");
		
		username = new JTextField();
		username.setEditable(false);
		username.setHorizontalAlignment(SwingConstants.CENTER);
		username.setText("Will be a checkbox when in BDN/SDN");
		username.setColumns(10);
		
		JLabel lblYourCanFind = new JLabel("Your can find your signature at:");
		
		JTextField example = new JTextField();
		example.setHorizontalAlignment(SwingConstants.CENTER);
		example.setEditable(false);
		example.setText("http://parabot.xero.pw/staking/signature#USER");
		example.setColumns(10);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblEnterYourUsername, Alignment.LEADING)
						.addComponent(lblYourCanFind, Alignment.LEADING)
						.addComponent(example, GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE)
						.addComponent(username, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(6)
					.addComponent(lblEnterYourUsername)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(username, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblYourCanFind)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(example, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		panel_1.setLayout(gl_panel_1);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Changelog", null, panel_2, null);
		
		
		JTextArea changelog = new JTextArea();
		changelog.setEditable(false);
		changelog.setText("v1.5 (July 5, 2015)\r\n- Decreased Special Attack Spamming\r\n\r\nv1.4 (July 1, 2015)\r\n- Added Informative Paint\r\n\r\nv1.3 (June 26, 2015)\r\n- Added Shard Exchange\r\n\r\nv1.2 (June 26, 2015)\r\n- Added Previous Staking Settings\r\n\r\nv1.1 (June 24, 2015)\r\n- Added Settings GUI\r\n- Added Message Effects\r\n\r\nv1.0 (June 20, 2015)\r\n- Initial Release");
		changelog.setLineWrap(true);
		JScrollPane scroll = new JScrollPane(changelog);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		changelog.setCaretPosition(0);
		
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(scroll, GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(5)
					.addComponent(scroll, GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
					.addContainerGap())
		);
		panel_2.setLayout(gl_panel_2);
		contentPane.setLayout(gl_contentPane);
	}
}
