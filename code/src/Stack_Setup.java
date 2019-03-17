import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Stack_Setup implements Runnable{

	private String title;
	private static int height,width;
	private Thread thread;
	private static BufferStrategy buffer;
	private static Graphics g;
	private boolean running;
	private static Display display;
	private static int x=10;
	public static int stack_length=0;
	static public boolean push,pop,reset; 
	private static int t_x[]={10,35,60};
	public Stack_Setup(String title,int width,int height) {
		this.title=title;
		Stack_Setup.width=width;
		Stack_Setup.height=height;
	}
	public void init() {
		display=new Display(title,width,height);
		
	}
	public synchronized void start() {
		if(running) {
			return;
		}
		running=true;
		if(thread==null) {
			thread=new Thread(this);
			thread.start();
		}
	}
	public synchronized void stop() {
		if(!running) {
			return;
		}
		running= false;
		try {
			thread.join();
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
		
		if(stack_length==0 && x<11) {
			g.drawString("Top",15, 450);
			g.setColor(Color.RED);
			g.setFont(new Font("Times Roman",Font.PLAIN|Font.ITALIC,50));
			g.drawString("-1", 10, 320);
			g.drawPolygon(t_x, new int[]{430,370,430},3);
			g.setColor(Color.black);
			g.setFont(new Font("Times Roman",Font.PLAIN|Font.ITALIC,20));
		}
		for(int i=1;i<=15;i++) {
			
			g.setColor(Color.magenta);
			g.drawRect(i*60,250, 60, 100);
		}
		if(stack_length>0)
			g.clearRect(0,250, 60, 100);
		if(x<stack_length*60+10)
		{
			//g.setFont(new Font("Times Roman",Font.PLAIN|Font.ITALIC,20));
			g.clearRect(0, 351,18*60, 150);
			g.drawString("Top",x+5, 450);
			g.setColor(Color.GREEN);
			g.fillPolygon(new int[] {t_x[0]+x-10,t_x[1]+x-10,t_x[2]+x-10}, new int[] {430, 370,430},3);
			x+=1;
		}
	    
		if((x>stack_length*60+10))
		{
			//g.setFont(new Font("Times Roman",Font.PLAIN|Font.ITALIC,20));
			g.clearRect(0, 355,16*60, 150);
			g.drawString("Top",x+5, 450);
			g.setColor(Color.GREEN);
			if(x>30)
				g.fillPolygon(new int[] {t_x[0]+x-10,t_x[1]+x-10,t_x[2]+x-10}, new int[] {430, 370,430},3);
			x=x-1;
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
		String s=Display.get_text();
		if(push&&!(s.contentEquals(""))) {
			stack_length+=1;
			
			g.drawString(Display.get_text(),60*stack_length+15, 300);
			push=false;
		}
		if(pop) {
			stack_length=stack_length-1;
			g.clearRect((stack_length+1)*60+5,255, 50, 90);
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
		 stack_length=0;
		 t_x[0]=10;
		 t_x[1]=35;
		 t_x[2]=60;
	}
	}
	

