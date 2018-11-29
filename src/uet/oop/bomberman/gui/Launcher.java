
package uet.oop.bomberman.gui;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.gui.button.AudioButton;
import uet.oop.bomberman.gui.button.BackButton;
import uet.oop.bomberman.gui.button.Button;
import uet.oop.bomberman.gui.button.HowToPlayButton;
import uet.oop.bomberman.gui.button.MultiPlayButton;
import uet.oop.bomberman.gui.button.PlayButton;
import uet.oop.bomberman.gui.button.QuitButton;
import uet.oop.bomberman.input.Mouse;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import static uet.oop.bomberman.BombermanGame.musicPlayer;

public class Launcher extends JFrame
{
    private BombermanGame bomberman;

    private int xLocation;
    private int yLocation;

    private JPanel infoPanel;
    private JPanel menuPanel;
//    private JLabel titleMenu;

    public Launcher(BombermanGame bomberman) {
        this.bomberman = bomberman;
        xLocation = 0;
        yLocation = 0;
        musicPlayer.loop();
        initComponents();
    }

    private void initComponents() {
        menuPanel = new JPanel();
        infoPanel = new JPanel();

        // Thiết lập cửa sổ menu
        createWindow();

        // Thiết lập panel hướng dẫn how to play
        createGuidePanel();

        // Thiết lập panel menu
        createMenuPanel();

        // Vẽ background
        createBackground();

        // Do some stuff
        setLockAndFeel();

        pack();
        setVisible(true);
    }

    private void setLockAndFeel() {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Launcher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Launcher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Launcher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Launcher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    private void createBackground() {
        JLabel backgroundLabel = new JLabel();
        backgroundLabel.setIcon(new ImageIcon(getClass().getResource("/images/bgk.jpeg")));
        backgroundLabel.setText("jLabel2");
        getContentPane().add(backgroundLabel, new AbsoluteConstraints(0, 0, 1150, 630));
    }

    private void createImagePanel() {
        JPanel imagePanel = new JPanel();
        imagePanel.setBackground(MyColor.TRANSPARENT);
        imagePanel.setLayout(new AbsoluteLayout());

        JLabel titleImage = new JLabel();
        // Drag and drop window
        titleImage.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                mousePressedHandle(mouseEvent);
            }
        });
        titleImage.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent mouseEvent) {
                mouseDragListener(mouseEvent);
            }
        });

        imagePanel.add(titleImage, new AbsoluteConstraints(0, 0, 580, 620));

        getContentPane().add(imagePanel, new AbsoluteConstraints(0, 0, 580, 620));
    }

    private void createBackgroundPanel(JPanel panel) {
        panel.setBackground(MyColor.DARK_GRAY);
        panel.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        panel.setLayout(new AbsoluteLayout());
    }

    private void createMenuPanel() {
        createBackgroundPanel(menuPanel);

        createGameLogo();

        addButtonIntoMenu();

        getContentPane().add(menuPanel, new AbsoluteConstraints(580, 0, 570, 630));
    }

    private void createGameLogo() {
        JLabel titleLabel = new JLabel();
        titleLabel.setIcon(new ImageIcon(getClass().getResource("/images/scaledTitle.png")));
        titleLabel.setText("jLabel1");
        menuPanel.add(titleLabel, new AbsoluteConstraints(50, 10, 490, 250));
    }

    private void addButtonIntoMenu() {
        menuPanel.add(new QuitButton(this),
                new AbsoluteConstraints(200, 540, Button.WIDTH, Button.HEIGHT));

        menuPanel.add(new PlayButton(this),
                new AbsoluteConstraints(200, 300, Button.WIDTH, Button.HEIGHT));

        menuPanel.add(new MultiPlayButton(this),
                new AbsoluteConstraints(200, 360, Button.WIDTH, Button.HEIGHT));

        menuPanel.add(new AudioButton(this),
                new AbsoluteConstraints(200, 480, Button.WIDTH, Button.HEIGHT));

        menuPanel.add(new HowToPlayButton(this),
                new AbsoluteConstraints(200, 420, Button.WIDTH, Button.HEIGHT));
    }

    private void createGuidePanel() {
        createBackgroundPanel(infoPanel);

        createGuideDescription();

        infoPanel.add(new BackButton(this),
                new AbsoluteConstraints(200, 390, Button.WIDTH, Button.HEIGHT));

        infoPanel.setVisible(false);

        getContentPane().add(infoPanel, new AbsoluteConstraints(580, 0, 570, 630));
    }

    private void createGuideDescription() {
        JLabel text3 = new JLabel();
        text3.setFont(MyFont.SMALL); // NOI18N
        text3.setForeground(MyColor.WHITE);
        text3.setText("Use Space or J key to place bomb.");
        infoPanel.add(text3, new AbsoluteConstraints(60, 300, 470, 60));

        JLabel text2 = new JLabel();
        text2.setFont(MyFont.SMALL); // NOI18N
        text2.setForeground(MyColor.WHITE);
        text2.setText("Left, Right, Up, Down Arrows to move;");
        infoPanel.add(text2, new AbsoluteConstraints(60, 240, 470, 60));

        JLabel text1 = new JLabel();
        text1.setFont(MyFont.SMALL); // NOI18N
        text1.setForeground(MyColor.WHITE);
        text1.setText("Use W, A, S, D key or");
        infoPanel.add(text1, new AbsoluteConstraints(60, 180, 470, 60));
    }

    private void createWindow() {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        setMaximumSize(new Dimension(1150, 630));
        setSize(new Dimension(1150, 630));

        setResizable(false);

        setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        setUndecorated(true);

        getContentPane().setLayout(new AbsoluteLayout());

        // Drag and drop window

//        titleMenu.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mousePressed(MouseEvent mouseEvent) {
//                mousePressedHandle(mouseEvent);
//            }
//        });
//        titleMenu.addMouseMotionListener(new MouseMotionAdapter() {
//            @Override
//            public void mouseDragged(MouseEvent mouseEvent) {
//                mouseDragListener(mouseEvent);
//            }
//        });

//        getContentPane().add(titleMenu, new AbsoluteConstraints(0, 0, 1100, 30));

        createImagePanel();
    }

    public void createCloseButton() {
        JButton closeBtn = new JButton();

        closeBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close.png")));
        closeBtn.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        closeBtn.setBorderPainted(false);
        closeBtn.setContentAreaFilled(false);
        closeBtn.addActionListener(this::quitBtnActionPerformed);
        closeBtn.setForeground(MyColor.WHITE);
        getContentPane().add(closeBtn, new AbsoluteConstraints(1115, 1, 32, 32));
    }

    public BombermanGame getGame() {
        return bomberman;
    }

    private void mousePressedHandle(MouseEvent mouseEvent)
    {
        this.xLocation = mouseEvent.getX();
        this.yLocation = mouseEvent.getY();
    }

    private void quitBtnActionPerformed(ActionEvent evt)
    {
        System.exit(0);
    }

    /*
    private void playBtnActionPerformed(ActionEvent evt)
    {
        bomberman.changeState(BombermanGame.STATE.GAME);
        dispose();
    }

    private void howBtnActionPerformed(ActionEvent evt)
    {
        infoPanel.setVisible(true);
        menuPanel.setVisible(false);
    }

    private void backBtnActionPerformed(ActionEvent evt)
    {
        infoPanel.setVisible(false);
        menuPanel.setVisible(true);
    }
    */

    public JPanel getInfoPanel() {
        return infoPanel;
    }

    public JPanel getMenuPanel() {
        return menuPanel;
    }

    private void mouseDragListener(MouseEvent evt) {
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();

        setLocation(x - xLocation, y - yLocation);
    }
}
