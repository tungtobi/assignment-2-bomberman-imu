
package uet.oop.bomberman.gui;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.input.Mouse;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Launcher extends JFrame
{
    BombermanGame bomberman;

    public Launcher(BombermanGame bomberman)
    {
        this.bomberman = bomberman;
        initComponents();
    }


    private void initComponents()
    {
        menuPanel = new JPanel();
        titleLabel = new JLabel();
        movement = new JLabel();
        placeBomb = new JLabel();
        quitBtn = new JButton();
        playBtn = new JButton();
        howBtn = new JButton();
        backBtn = new JButton();
        imagePanel = new JPanel();
        backgroundLabel = new JLabel();
        infoPanel = new JPanel();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setMaximumSize(new Dimension(1150, 630));
        setResizable(false);
        getContentPane().setLayout(new AbsoluteLayout());

        infoPanel.setBackground(new Color(35, 35, 35, 200));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        infoPanel.setLayout(new AbsoluteLayout());

        movement.setFont(new Font("Noto Sans", 0, 18)); // NOI18N
        movement.setForeground(new Color(255, 255, 255));
        movement.setText("Use Space to place bomb");
        infoPanel.add(movement, new AbsoluteConstraints(180, 240, 240, 60));

        placeBomb.setFont(new Font("Noto Sans", 0, 18)); // NOI18N
        placeBomb.setForeground(new Color(255, 255, 255));
        placeBomb.setText("Use W,A,S,D or Left, Right, Up, Down Arrows to move");
        infoPanel.add(placeBomb, new AbsoluteConstraints(60, 180, 470, 60));

        backBtn.setBackground(new Color(35, 35, 35,50));
        backBtn.setFont(new Font("Noto Sans", 0, 20)); // NOI18N
        backBtn.setForeground(new Color(255, 255, 255));
        backBtn.setText("Back");
        backBtn.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        backBtn.setBorderPainted(false);
        backBtn.setFocusPainted(false);
        backBtn.addMouseListener(new Mouse());
        backBtn.addActionListener(this::backBtnActionPerformed);
        infoPanel.add(backBtn, new AbsoluteConstraints(200, 330, 200, 50));
        infoPanel.setVisible(false);

        getContentPane().add(infoPanel, new AbsoluteConstraints(580, 0, 570, 630));

        menuPanel.setBackground(new Color(35, 35, 35, 200));
        menuPanel.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        menuPanel.setLayout(new AbsoluteLayout());

        titleLabel.setIcon(new ImageIcon(getClass().getResource("/images/scaledTitle.png")));
        titleLabel.setText("jLabel1");
        menuPanel.add(titleLabel, new AbsoluteConstraints(50, 10, 490, 250));

        quitBtn.setBackground(new Color(35, 35, 35,50));
        quitBtn.setFont(new Font("Noto Sans", 0, 20));
        quitBtn.setForeground(new Color(255, 255, 255));
        quitBtn.setText("Quit");
        quitBtn.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        quitBtn.setBorderPainted(false);
        quitBtn.setFocusPainted(false);
        quitBtn.addActionListener(this::quitBtnActionPerformed);
        quitBtn.addMouseListener(new Mouse());
        menuPanel.add(quitBtn, new AbsoluteConstraints(200, 420, 200, 50));

        playBtn.setBackground(new Color(35, 35, 35,50));
        playBtn.setFont(new Font("Noto Sans", 0, 20));
        playBtn.setForeground(new Color(255, 255, 255));
        playBtn.setText("Play");
        playBtn.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        playBtn.setBorderPainted(false);
        playBtn.setFocusPainted(false);
        playBtn.addActionListener(this::playBtnActionPerformed);
        playBtn.addMouseListener(new Mouse());
        menuPanel.add(playBtn, new AbsoluteConstraints(200, 300, 200, 50));
        howBtn.setBackground(new Color(35, 35, 35,50));
        howBtn.setFont(new Font("Noto Sans", 0, 20));
        howBtn.setForeground(new Color(255, 255, 255));
        howBtn.setText("How to play");
        howBtn.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        howBtn.setBorderPainted(false);
        howBtn.setFocusPainted(false);
        howBtn.addMouseListener(new Mouse());
        howBtn.addActionListener(this::howBtnActionPerformed);
        menuPanel.add(howBtn, new AbsoluteConstraints(200, 360, 200, 50));

        getContentPane().add(menuPanel, new AbsoluteConstraints(580, 0, 570, 630));

        imagePanel.setBackground(new Color(0, 0, 0, 0));
        imagePanel.setLayout(new AbsoluteLayout());
        getContentPane().add(imagePanel, new AbsoluteConstraints(0, 0, 580, 620));

        backgroundLabel.setIcon(new ImageIcon(getClass().getResource("/images/bgk.jpeg")));
        backgroundLabel.setText("jLabel2");
        getContentPane().add(backgroundLabel, new AbsoluteConstraints(0, 0, 1150, 630));


        try
        {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(Launcher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(Launcher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(Launcher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(Launcher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        pack();
        setVisible(true);
    }

    private void quitBtnActionPerformed(ActionEvent evt)
    {
        System.exit(0);
    }

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

    private JLabel backgroundLabel;
    private JButton howBtn;
    private JPanel imagePanel;
    private JPanel infoPanel;
    private JLabel movement;
    private JLabel placeBomb;
    private JPanel menuPanel;
    private JButton playBtn;
    private JButton quitBtn;
    private JButton backBtn;
    private JLabel titleLabel;
}
