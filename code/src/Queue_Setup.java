import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Queue_Setup implements Runnable{

	private String title;
	private static int height,width;
	private Thread thread1;
	private static BufferStrategy buffer;
	private static Graphics g;
	private boolean running;
	private static Queue_Display display;
	private static int x=10;
	private static int x1=10;
	public static int queue_length=0;
	private static int frnt_acs=0;
	private static int rear_acs=0;
	static public boolean push,pop,reset;
	private static int t_x[]={65,85,105};
	public Queue_Setup(String title,int width,int height) {
		this.title=title;
		this.width=width;
		this.height=height; 
	}
	public void init() {
		 display=new Queue_Display(title,width,height);
		
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
	public synchronized void stop() {
		if(!running) {
			return;
		}
		running= false;
		try {
			thread1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
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
		g.setFont(new Font("Times Roman",Font.PLAIN|Font.ITALIC,20));
		if(queue_length==0 && x<11)
		{
			g.drawString("Rear",68, 450);
			g.drawPolygon(t_x, new int[]{430,370,430},3);
			g.drawString("Front",68, 165);
			g.drawPolygon(t_x,new int[] {170,230,170},3);
		}
		for(int i=1;i<=15;i++) {
			g.setColor(Color.magenta);
			g.drawRect(i*60,250, 60, 100);
		}
		
		
		//end of draw
		buffer.show();
		//g.dispose();
		if(x1<frnt_acs*60+10) {
			g.clearRect(0, 120,16*60, 130);
			g.drawString("Front",x1+60, 165);
			g.setColor(Color.RED);
			g.fillPolygon(new int[] {t_x[0]+x1,t_x[1]+x1,t_x[2]+x1},new int[] {170,230,170},3 );
			x1+=1;
		}
		if(x<rear_acs*60+10)
		{
			g.clearRect(0, 355,16*60, 150);
			g.drawString("Rear",x+60, 450);
			g.setColor(Color.GREEN);
			g.fillPolygon(new int[] {t_x[0]+x,t_x[1]+x,t_x[2]+x}, new int[] {430, 370,430},3);
			x+=1;
		}
		
	}
	public void render() {
		buffer=display.getCanvas().getBufferStrategy();
		if(buffer==null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g=buffer.getDrawGraphics();
		String s=Queue_Display.get_text();
		if(push&&!(s.contentEquals(""))) {
			queue_length+=1;
			rear_acs+=1;
			g.drawString(Queue_Display.get_text(),60*rear_acs+15, 300);
			push=false;
		}
		if(pop) {
			queue_length=queue_length-1;
			frnt_acs=frnt_acs+1;
			g.clearRect((frnt_acs)*60+5,255, 50, 90);
			pop=false;
		}
		buffer.show();
		g.dispose();
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
				tick();
				render();
				delta--;
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
		
		x=10;
		 x1=10;
		 queue_length=0;
		 frnt_acs=0;
		 rear_acs=0;
		 t_x[0]=65;
		 t_x[1]=85;
		 t_x[2]=105;
	}
	}
	
