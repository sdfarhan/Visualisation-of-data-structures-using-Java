
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Selection extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Selection frame = new Selection();
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
	public Selection() {
		setTitle("VISUALIZATION ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1000, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton stack_btn = new JButton("STACK");
		stack_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Stack_Setup stack=new Stack_Setup("STACK",1000,700);
				dispose();
				stack.start();
				
			}
		});
		stack_btn.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 30));
		stack_btn.setBounds(273, 221, 526, 92);
		contentPane.add(stack_btn);
		
		JButton btnQueue = new JButton("QUEUE");
		btnQueue.addActionListener(new ActionListener() {
			public synchronized void actionPerformed(ActionEvent arg0) {
				Queue_Setup qs=new Queue_Setup("QUEUE",1000,700);
				dispose();
				qs.start();
			}
		});
		btnQueue.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 30));
		btnQueue.setBounds(273, 363, 526, 92);
		contentPane.add(btnQueue);
		
		JButton btnLinkList = new JButton("LINK LIST");
		btnLinkList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 Linklist_Setup ls=new Linklist_Setup("LINKLIST",1000,700);
				 dispose();
				 ls.start();
			}
		});
		btnLinkList.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 30));
		btnLinkList.setBounds(273, 505, 526, 92);
		contentPane.add(btnLinkList);
		
		JLabel lblNewLabel = new JLabel("SELECT ANY ONE ALGORITHM TO VISUALIZE");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel.setBounds(12, 116, 997, 92);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("       WELCOME TO VISUALIZATION OF DATA STRUCTURES ALGORITHMS ");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		lblNewLabel_1.setBounds(0, -3, 982, 106);
		contentPane.add(lblNewLabel_1);
	}
}
