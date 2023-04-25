import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame implements ActionListener {
    JButton en_button;
    JButton dec_button;
    MyFrame(){

            JLabel heading = new JLabel();
            heading.setText("AES 128-bit Cipher");
            heading.setHorizontalAlignment(SwingConstants.CENTER);
            heading.setVerticalAlignment(SwingConstants.TOP);
            heading.setForeground(Color.BLACK);
            heading.setOpaque(false);
            heading.setBounds(400,180,200,40);

            en_button = new JButton("Encrypt");
            en_button.setBounds(300,300,150,50);
            en_button.addActionListener(this);

            dec_button = new JButton("Decrypt");
            dec_button.setBounds(550,300,150,50);
            dec_button.addActionListener(this);

            //Frame
            this.setTitle("Shamir Secret Sharing");
            this.setResizable(false);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setSize(1000,600);
            this.setLayout(null);
            this.setVisible(true);
            this.add(heading);
            this.add(en_button);
            this.add(dec_button);
            this.setBackground(Color.CYAN);
            this.getContentPane().setBackground(Color.PINK);
        }

    @Override
    public void actionPerformed(ActionEvent e) {
            //When Encrypt Button is clicked
            if(e.getSource()==en_button){
                        new Encrypt_Window();
                        this.dispose();     //Disposing current opened JFrame
            }
            //When Decrypt Button is clicked
            if(e.getSource()==dec_button){
                        new Decrypt_Window();
                        this.dispose();     //Disposing current opened JFrame
            }
    }
}
