import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Queue_Display {
	private String title;
	private int height,width;
	private JFrame frame;
	private Canvas canvas;
	private final JPanel panel_1 = new JPanel();
	private JTextField textField;
	private  static String get_text;
	private JButton back_btn;
	private JPanel panel;
	private JButton reset_btn;
	public Queue_Display(String title,int width,int height) {
		this.title=title;
		this.width=width;
		this.height=height;
		create();
	}
	public void create() {
		frame=new JFrame();
		frame.setTitle(title);
		frame.setBounds(0,0,width,height);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBackground(Color.magenta);
		canvas= new Canvas();
		canvas.setPreferredSize(new Dimension(width,height));
		canvas.setMaximumSize(new Dimension(width,height));
		canvas.setMinimumSize(new Dimension(width,height));
		canvas.setFocusable(false);
		frame.getContentPane().add(canvas);
		frame.getContentPane().add(panel_1, BorderLayout.WEST);
		
		textField = new JTextField();
		panel_1.add(textField);
		textField.setColumns(10);
		
		JButton btnPush = new JButton("Enqueue");
		btnPush.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(Queue_Setup.queue_length>=14) {
					JOptionPane.showMessageDialog(null, "Stack is full");
				}
				else {
				Queue_Setup.push=true;				
				get_text=textField.getText();
				textField.setText("");
			}}
		});
		panel_1.add(btnPush);
		
		JButton btnPop = new JButton("Dequeue");
		btnPop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(Queue_Setup.queue_length==0) {
					JOptionPane.showMessageDialog(null, "Queue is empty");
				}
				else {
				Queue_Setup.pop=true;				
				get_text=textField.getText();
				textField.setText("");
			}
		}});
		panel_1.add(btnPop);
		
		panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.EAST);
		
		back_btn = new JButton("BACK");
		back_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Queue_Setup.reset();
				Selection s=new Selection();
				s.setVisible(true);
				frame.dispose();
			}
		});
		
		reset_btn = new JButton("Reset");
		reset_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Queue_Setup.reset();
				JOptionPane.showMessageDialog(null, "Queue is reset");
			}
		});
		panel.add(reset_btn);
		panel.add(back_btn);
		frame.pack();
	}
	public Canvas getCanvas() {
		return canvas;
	}
	public static String get_text() {
		return get_text;
	}
	
	}

