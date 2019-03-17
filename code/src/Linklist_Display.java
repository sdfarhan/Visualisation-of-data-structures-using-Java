
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


public class Linklist_Display {
	private String title;
	private int height,width;
	private JFrame frame;
	private Canvas canvas;
	private static Linklist_Display display;
	private final JPanel panel_1 = new JPanel();
	private  static String get_text;
	private JButton back_btn;
	private JPanel panel;
	private JButton reset_btn;
	private JTextField insrt_ele;
	public static int pos;
	public Linklist_Display(String title,int width,int height) {
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
		canvas.setPreferredSize(new Dimension(width+500,height));
		canvas.setMaximumSize(new Dimension(width,height));
		canvas.setMinimumSize(new Dimension(width,height));
		canvas.setFocusable(false);
		frame.getContentPane().add(canvas);
		frame.getContentPane().add(panel_1, BorderLayout.WEST);
		
		insrt_ele = new JTextField();
		insrt_ele.setText(" ");
		panel_1.add(insrt_ele);
		insrt_ele.setColumns(10);
		
		JButton insert_btn = new JButton("Insert");
		insert_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!(insrt_ele.getText().contentEquals(" "))){
					String s=JOptionPane.showInputDialog(null, "Input the position for new node");
				int i=Integer.parseInt(s);
				pos=i;
				if(i<=Linklist_Setup.linklist_length+1&&i>=1) {
					Linklist_Setup.insert=true;
					get_text=insrt_ele.getText();
					insrt_ele.setText(" ");
				}
				else {
					JOptionPane.showMessageDialog(null, "Given position:"+s+" is invalid");
					insrt_ele.setText(" ");
			}
				}
				else {
					JOptionPane.showMessageDialog(null, "enter a valid element!");
				}
				}
		});
		panel_1.add(insert_btn);
		
		JButton delete_btn = new JButton("Delete");
		delete_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pos=Integer.parseInt(JOptionPane.showInputDialog(null, "enter the position of node!"));
				
				
				if(pos<=Linklist_Setup.linklist_length&&pos>0) {
		
					Linklist_Setup.delete=true;
				}
				else{
						JOptionPane.showMessageDialog(null, "Given position:"+pos+" is invalid");
				}
				
		}});
		panel_1.add(delete_btn);
		
		panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.EAST);
		
		back_btn = new JButton("BACK");
		back_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Linklist_Setup.reset();
				Selection s=new Selection();
				s.setVisible(true);
				frame.dispose();
			}
		});
		
		reset_btn = new JButton("Reset");
		reset_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Linklist_Setup.reset=true;
				JOptionPane.showMessageDialog(null, "linklist is reset");
			}
		});
		panel.add(reset_btn);
		panel.add(back_btn);
		frame.pack();
	}
	public Canvas getCanvas() {
		return canvas;
	}
	public static String get_insrt_ele() {
		return get_text;
	}
	
	
	}

