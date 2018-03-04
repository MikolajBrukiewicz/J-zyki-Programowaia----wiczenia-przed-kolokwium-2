import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/*=======  TU PROSZE SIE PODPISAC ==============================
 *      Imie:
 *  Nazwisko:
 *Nr indeksu:
 *==============================================================
 */

class Odcinek {
	private int x1, y1, x2, y2;

	/*
	 * Konstruktor tworz¹cy nowy obiekt Odcinek px, py - wspó³rzêdne pocz¹tku
	 * odcinka kx, ky - wspó³rzêdne koñca odcinka
	 */
	public Odcinek(int px, int py, int kx, int ky) {
		x1 = px;
		y1 = py;
		x2 = kx;
		y2 = ky;
	}

	public void przesun(int dx, int dy) {
		x1 += dx;
		y1 += dy;
		x2 += dx;
		y2 += dy;
	}

	public void rysuj(Graphics g) {
		g.drawLine(x1, y1, x2, y2);
	}
}

class Rysunek extends JPanel implements  ActionListener, MouseMotionListener, MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	ArrayList<Kolo> lista = new ArrayList<Kolo>();
	Kolo mysz;
	
	class Kolo
	{
		boolean clicked=false, entered = false;
		Kolo(){}
		int x,y;
		
		public void draw(Graphics g)
		{
			g.setColor(Color.BLACK);
			g.drawOval(x-10, y-10, 20, 20);
		}
		
		public void draw2(Graphics g)
		{
			g.setColor(Color.RED);
			g.fillOval(x-10, y-10, 20, 20);
		}
		
		public void move(int nx, int ny)
		{
			x=nx;
			y=ny;
			repaint();
		}
		
	}
	
	Rysunek()
	{
		mysz = new Kolo();
		lista.add(mysz);
	}
	


	public void actionPerformed(ActionEvent evt) {

	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		mysz.move(arg0.getX(), arg0.getY());
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		mysz.move(arg0.getX(), arg0.getY());
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		if(!mysz.entered)
		{
		if(mysz.clicked == false)
			mysz.draw(g);
		else if(mysz.clicked == true) mysz.draw2(g);
		}
		
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		mysz.entered = false;
		lista.clear();
		repaint();
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		mysz = new Kolo();
		mysz.entered = true;
		lista.add(mysz);
		repaint();
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		mysz.clicked=true;
		repaint();
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		mysz.clicked=false;
		repaint();
		
	}

}

public class Edytor extends JFrame implements KeyListener, MouseListener, MouseMotionListener, Runnable, ActionListener {

	private static final long serialVersionUID = 1L;

	private final String OPIS = "OPIS PROGRAMU\n\n" + "Aktywna klawisze:\n" + "   strzalki   ==> przesuwanie rysunku\n"
			+ "   k , K  ==> kasowanie rysunku\n" + "\nOperacje myszka:\n" + "   przeciaganie   ==>  rysowanie lini\n"
			+ "   SHIFT + przeciaganie   ==>  zakreœlanie pola"
			+ "\nWIDAC ZE KOLO SIE PORUSZA NA EKRANIE TYLKO GDY RUSZA SIE MYSZKA";
	JMenuBar glowne = new JMenuBar();
	JMenu menu = new JMenu("Menu");
	JMenuItem [] items = {new JMenuItem("Autor"), new JMenuItem("Opis"), new JMenuItem("Kunic")};
	JButton autor = new JButton("Autor");
	JButton reset = new JButton("Ressetino");
	JLabel sekundy = new JLabel("Sekundy");
	JTextField licznik = new JTextField(3);
	Timer automat;

	
	
	Edytor()
	{
		Rysunek rysunek = new Rysunek();
		rysunek.addMouseMotionListener(rysunek);
		rysunek.addMouseListener(rysunek);
		setSize(400,400);
		setTitle("Miko³aj Brukiewicz");
		menu.add(items[0]);
		menu.add(items[1]);
		menu.add(items[2]);
		glowne.add(menu);
		rysunek.add(autor);
		rysunek.add(sekundy);
		rysunek.add(licznik);
		rysunek.add(reset);
		autor.addActionListener(this);
		reset.addActionListener(this);
		licznik.setEditable(false);
		automat = new Timer(licznik);
		automat.start();
		setContentPane(rysunek);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setJMenuBar(glowne);
		for(int i =0; i< items.length; i++)
			items[i].addActionListener(this);
		setVisible(true);
		
	}
	
	
	class Timer extends Thread
	{
		JTextField a;
		int sekundy = 0;
		Timer(JTextField newTimer)
		{
			a= newTimer;
			a.setText(Integer.toString(sekundy));
		}
		
		public void run()
		{
			while(true)
			{
			try {
				Thread.sleep(1000);
				sekundy++;
				a.setText(Integer.toString(sekundy));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			}
		}
	}

	
	// ***obs³uga zdarzeñ przez s³uchacza zdarzeñ KeyListener ***
		public void keyPressed(KeyEvent e) {
			
		}

		public void keyReleased(KeyEvent evt) {
		}

		public void keyTyped(KeyEvent evt) {
		}

		// ***obs³uga zdarzeñ przez s³uchacza zdarzeñ MouseListener
		public void mouseClicked(MouseEvent e) {

		}

		public void mouseEntered(MouseEvent e) {
			
		}

		public void mouseExited(MouseEvent e) {
		}

		public void mousePressed(MouseEvent e) {

		}

		public void mouseReleased(MouseEvent e) {
		}

		// ***obs³uga zdarzeñ przez s³uchacza zdarzeñ MouseMotionListener***
		public void mouseDragged(MouseEvent e) {
			
		}

		public void mouseMoved(MouseEvent e) {

		}

		// ***implementacja interfejsu Runnable***
		@Override
		public void run() {


		}

		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			if(source == items[0])
			{
				JOptionPane.showMessageDialog(null, "Miko Bruk 225");
			}
			else if(source == items[1])
			{
				JOptionPane.showMessageDialog(null, OPIS);
			}
			else if(source == items[2])
				dispose();
			else if(source == autor)
				JOptionPane.showMessageDialog(null, "Miko Bruk 225");
			else if(source == reset)
			{
				automat.sekundy=0;
				automat.a.setText(Integer.toString(automat.sekundy));
			}

		}

	public static void main(String[] args) {
		new Edytor();

	}

}