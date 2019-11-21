/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

/**
 *
 * @author mohsen
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game extends JPanel implements KeyListener, Runnable {
//    ImageIcon ic = new ImageIcon(
//            new ImageIcon(getClass().getResource("black22.png"))
//                    .getImage()
//                    .getScaledInstance(frameWidth, frameHeight, java.awt.Image.SCALE_SMOOTH)
//    );

    //private ImageIcon tankIcon = new ImageIcon("image2/tank.png")

    //    private ImageIcon tankIcon = new ImageIcon(
//            new ImageIcon("image2/tank.png")
//                    .getImage()
//                    .getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH)
//    );
//
//    private JLabel tank = new JLabel(tankIcon);


    private Explosion ex;
    private Tank tank;
    private Mine mine;
    private boolean gameStatus = false;

    public Game() {
        addKeyListener(this);
        setFocusable(true);
        /*
        setSize(1000, 800);    // Set the frame size
        setLocationRelativeTo(null);         //center the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);         //No layout manager

        //tank = new Tank();
        //add(tank);

        setVisible(true);        //Make your frame visible
*/
        mine = new Mine(800, 700, 100, 100);
        tank = new Tank(20, 20, 100, 100);
        ex = new Explosion(300, 300, 300, 300, false);
        new Thread(this).start();
        new Thread(tank).start();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_UP) {
            System.out.println("UP#########");
            tank.run(0, false);
        } else if (code == KeyEvent.VK_RIGHT) {
            System.out.println("RIGHT#########");
            tank.run(1, false);
        } else if (code == KeyEvent.VK_DOWN) {
            System.out.println("DOWN#########");
            tank.run(2, false);
        } else if (code == KeyEvent.VK_LEFT) {
            System.out.println("LEFT#########");
            tank.run(3, false);
        } else if (code == KeyEvent.VK_SPACE) {
            System.out.println("SPACE#########");
            tank.run(0, true);
        }

        gameStatus = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);


        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.ORANGE);
        g2d.fillRect(0, 0, getWidth(), getHeight());


        if (ex.isVisible()) {
            g.drawImage(ex.getImage(), (int) mine.getX(), (int) mine.getY(), (int) 100, (int) 100, null);
        }
        if (mine.isVisible())
            g.drawImage(mine.getImage(), (int) mine.getX(), (int) mine.getY(), (int) mine.getWidth(), (int) mine.getHeight(), null);

        Missile m = tank.missile;
        if (m.isVisible() && (((int)m.getX() <= 0) || ((int)m.getX() >= 1500) || m.y <= 0 || m.y >= 900) )
            m.setVisible(false);
        if (m.isVisible()) {
            g.drawImage(m.getImage(), (int) m.x, (int) m.y,(int) m.getWidth(), (int)m.getHeight(), this);
        }

        if (tank.isVisible()) {
            g2d.rotate(Math.toRadians(tank.orientation * 45), tank.getX() + tank.getWidth() / 2, tank.getY() + tank.getHeight() / 2);
            g2d.drawImage(tank.getImage(), (int) tank.getX(), (int) tank.getY(), (int) tank.getWidth(), (int) tank.getHeight(), null);
        }



    }

    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setTitle("Tank War Game");
        f.setSize(1500, 900);    // Set the frame size
        f.setLocationRelativeTo(null);         //center the frame
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //f.setLayout(null);         //No layout manager

        //tank = new Tank();
        //f.add(new Game(), BorderLayout.CENTER);
        f.add(new Game());

        f.setVisible(true);        //Make your frame visible
    }

    @Override
    public void run() {
        while (true) {
            if (gameStatus) {
                repaint();
               // gameStatus = false;
            }
            if (tank_intersects_mine(tank, mine)) {
                System.out.println("Collision");
                Rectangle r = tank.intersection(mine);
                ex.setX(mine.getX());
                ex.setY(mine.getY());
                ex.setVisible(true);
                tank.setVisible(false);
                mine.setVisible(false);

                repaint();
                //break;
            }
            try {
                Thread.currentThread().sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean tank_intersects_mine(Tank t, Mine m) {
        return t.intersects(m);
    }

}

