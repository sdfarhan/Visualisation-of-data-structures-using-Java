
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Linklist_Setup implements Runnable{

	private String title,s="";
	private static int height,width;
	private Thread thread1;
	private static BufferStrategy buffer;
	private static Graphics g;
	private boolean running;
	private static Linklist_Display display;
	private static int x=0,x1=0,m=0,n=0;
	public static int linklist_length=0;
	private String[] ele=new String[10];
	private boolean march_frwd,march_bckwrd;
	static public boolean insert,delete,reset;
	public Linklist_Setup(String title,int width,int height) {
		this.title=title;
		Linklist_Setup.width=width;
		Linklist_Setup.height=height; 
	}
	public void init() {
		 display=new Linklist_Display(title,width,height);
	}
	public synchronized void start() {
		if(running) {
			return;
		}
		running=true;
		if(thread1==null) {
			thread1=new Thread(this);
			thread1.start();
		}
	}
	private void march_frwd() {
		buffer=display.getCanvas().getBufferStrategy();
		if(buffer==null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g=buffer.getDrawGraphics();
		if(x<=150*m) {
				g.clearRect((Linklist_Display.pos*100)+((Linklist_Display.pos-1)*50)+2, 250,1500 , 85);
				x++;
				for(int i=linklist_length-1;i>=Linklist_Display.pos;i--) {
					g.drawRect(i*150+(x-(150*(m-1))), 250, 50, 80);
					g.drawRect(i*150+50+(x-(150*(m-1))), 250, 50, 80);
					g.setFont(new Font("Times Roman",Font.PLAIN|Font.ITALIC,30));
					g.drawString(ele[i+1], i*150+10+(x-(150*(m-1))), 300);
					g.drawLine((i-1)*150+100+(x-(150*(m-1))), 290, (i-1)*150+150+(x-(150*(m-1))), 290);
					
				}
				g.drawString("X", (linklist_length-1)*150+65+(x-(150*(m-1))), 300);
				if(x>150*m) {
					g.drawRect(Linklist_Display.pos*150, 250, 50, 80);
					g.drawRect(Linklist_Display.pos*150+50, 250, 50, 80);
					g.setFont(new Font("Times Roman",Font.PLAIN|Font.ITALIC,30));
					g.drawString(s, Linklist_Display.pos*150+10, 300);
					g.drawLine((Linklist_Display.pos-1)*150+100, 290, (Linklist_Display.pos-1)*150+150, 290);
					march_frwd=false;
				}
		}
		
		buffer.show();
		g.dispose();
		
	}
	private void march_bckwrd() {
		buffer=display.getCanvas().getBufferStrategy();
		if(buffer==null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g=buffer.getDrawGraphics();
		if(x1<=150*n) {
				g.clearRect((Linklist_Display.pos*100)+((Linklist_Display.pos-1)*50)+1, 250,800 , 90);
				x1++;
				for(int i=linklist_length+1;i>Linklist_Display.pos;i--) {
					g.drawRect(i*150-(x1-(150*(n-1))), 250, 50, 80);
					g.drawRect(i*150+50-(x1-(150*(n-1))), 250, 50, 80);
					g.setFont(new Font("Times Roman",Font.PLAIN|Font.ITALIC,30));
					g.drawString(ele[i-1], i*150+10-(x1-(150*(n-1))), 300);
					g.drawLine((i-1)*150+100-(x1-(150*(n-1))), 290, (i-1)*150+150-(x1-(150*(n-1))), 290);
					
				}
				if(linklist_length>0&&!(Linklist_Display.pos==linklist_length+1))
					g.drawString("X", (linklist_length+1)*150+65-(x1-(150*(n-1))), 300);
				if(x1>150*n) {
					if(Linklist_Display.pos==linklist_length+1) {
						g.setFont(new Font("Times Roman",Font.PLAIN|Font.ITALIC,30));
						g.drawString("X", linklist_length*150+65, 300);
					}
					march_bckwrd=false;
				}
	}
	}
	public void tick() {
		
		buffer=display.getCanvas().getBufferStrategy();
		if(buffer==null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g=buffer.getDrawGraphics();
		//start to draw
				g.drawRect(0, 250, 50, 80);
				g.drawRect(50, 250, 50, 80);
				g.drawString("Dummy", 02, 295);
				g.setFont(new Font("Times Roman",Font.PLAIN|Font.ITALIC,20));
				g.drawString("Head", 0, 230);
				if(linklist_length==0)
					g.drawString("X", 65, 295);
				if(march_frwd) {
					march_frwd();	
				}
				if(march_bckwrd) {
					march_bckwrd();
				}
				
		//end of draw
		buffer.show();
		g.dispose();
		
		
	}
	public void render() {
		buffer=display.getCanvas().getBufferStrategy();
		if(buffer==null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g=buffer.getDrawGraphics();
		 s=Linklist_Display.get_insrt_ele();
		if(insert) {
				if(Linklist_Display.pos==linklist_length+1) {
					linklist_length++;
					move_Frwd(Linklist_Display.pos);
					g.drawRect(Linklist_Display.pos*150, 250, 50, 80);
					g.drawRect(Linklist_Display.pos*150+50, 250, 50, 80);
					g.setFont(new Font("Times Roman",Font.PLAIN|Font.ITALIC,30));
					g.drawString(s, Linklist_Display.pos*150+10, 300);
					g.drawLine((Linklist_Display.pos-1)*150+100, 290, (Linklist_Display.pos-1)*150+150, 290);
					g.clearRect((Linklist_Display.pos-1)*150+55, 260, 40, 70);
					g.drawString("X", Linklist_Display.pos*150+65, 300);
				}
				else if(Linklist_Display.pos<=linklist_length){
					linklist_length++;
					m++;
					move_Frwd(Linklist_Display.pos);
					march_frwd=true;
				}
				
					
				
				
				insert=false;
		}
		if(delete) {	
			linklist_length--;
			n++;
			move_bckwrd(Linklist_Display.pos);
			march_bckwrd=true;
			delete=false;
		}
		buffer.show();
		g.dispose();
		

	} 
	
	void move_Frwd(int n) {
		int i;
		for(i=linklist_length;i>n;i--) {
			ele[i]=ele[i-1];	
		}
		ele[i]=s;
		
	}
	
	private void move_bckwrd(int n) {
		int i;
		for(i=n;i<=linklist_length;i++) {
				ele[i]=ele[i+1];  
		}
		ele[i]="";
	}
	public void run() {
		init();
		int fps=50;
		double timepertick=100000000/fps;
		double delta=0;
		long current=System.nanoTime();
		while(running) {
			delta=delta+(System.nanoTime()-current)/timepertick;
			current=System.nanoTime();
			if(delta>=1) {
			
				if(reset) {
					reset();
					reset=false;
				}
				tick();
				render();
				
				delta++;
				}
				
				
			}
			
		}

	
	public static void reset() {
		buffer=display.getCanvas().getBufferStrategy();
		if(buffer==null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g=buffer.getDrawGraphics();
		g.clearRect(0, 0, width, height);
		
		buffer.show();
		g.dispose();
		m=0;
		x=0;
		 x1=0;
		 linklist_length=0;
	}
	
}
	