import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Decrypt_Window extends JFrame implements ActionListener{

    JButton Decrypt_button;
    JButton Back_button;
    JButton SelectFile;
    JButton Key1;
    JButton Key2;
    JButton Key3;
    JButton Key4;
    JButton SaveLocation;
    int selectresponse=1;
    int K1response=1;
    int K2response=1;
    int K3response=1;
    int K4response=1;
    int saveresponse=1;
    static String FilePATH;
    static String SaveFilePATH;
    static String ParentDirectory;
    static String FileName;
    static String K1;
    static String K2;
    static String K3;
    static String K4;
    Decrypt_Window(){

        //Back Button
        Back_button = new JButton("Back");
        Back_button.setBounds(870,10,100,30);
        Back_button.addActionListener(this);


        //Upload Image------------------------------------------------
        JLabel label = new JLabel("Upload Encrypted Image");
        label.setBorder(BorderFactory.createEtchedBorder());
        label.setBounds(200,50,180,40);
        label.setForeground(Color.BLACK);
        label.setOpaque(false);

        SelectFile = new JButton("Select Image");
        SelectFile.setBounds(420,50,160,40);
        SelectFile.addActionListener(this);

        //Key 1------------------------------------------------
        JLabel K1Label = new JLabel("Key 1");
        K1Label.setBorder(BorderFactory.createEtchedBorder());
        K1Label.setBounds(200,100,180,40);
        K1Label.setForeground(Color.BLACK);
        K1Label.setOpaque(false);

        Key1 = new JButton("Select Key");
        Key1.setBounds(420,100,160,40);
        Key1.addActionListener(this);

        //Key 2------------------------------------------------
        JLabel K2Label = new JLabel("Key 2");
        K2Label.setBorder(BorderFactory.createEtchedBorder());
        K2Label.setBounds(200,150,180,40);
        K2Label.setForeground(Color.BLACK);
        K2Label.setOpaque(false);

        Key2 = new JButton("Select Key");
        Key2.setBounds(420,150,160,40);
        Key2.addActionListener(this);

        //Key 3------------------------------------------------
        JLabel K3Label = new JLabel("Key 3");
        K3Label.setBorder(BorderFactory.createEtchedBorder());
        K3Label.setBounds(200,200,180,40);
        K3Label.setForeground(Color.BLACK);
        K3Label.setOpaque(false);

        Key3 = new JButton("Select Key");
        Key3.setBounds(420,200,160,40);
        Key3.addActionListener(this);

        //Key 4------------------------------------------------
        JLabel K4Label = new JLabel("Key 4");
        K4Label.setBorder(BorderFactory.createEtchedBorder());
        K4Label.setBounds(200,250,180,40);
        K4Label.setForeground(Color.BLACK);
        K4Label.setOpaque(false);

        Key4 = new JButton("Select Key");
        Key4.setBounds(420,250,160,40);
        Key4.addActionListener(this);

        //Save Location------------------------------------------------
        JLabel saveLabel = new JLabel("Save Decrypted Image");
        saveLabel.setBorder(BorderFactory.createEtchedBorder());
        saveLabel.setBounds(200,300,180,40);
        saveLabel.setForeground(Color.BLACK);
        saveLabel.setOpaque(false);

        SaveLocation = new JButton("Select Location");
        SaveLocation.setBounds(420,300,160,40);
        SaveLocation.addActionListener(this);

        //Decrypt Button------------------------------------------------
        Decrypt_button = new JButton("Decrypt");
        Decrypt_button.setBounds(400,400,150,50);
        Decrypt_button.addActionListener(this);

        //Decrypt JFrame
        this.setTitle("Decryption");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000,600);
        this.setLayout(null);
        this.setVisible(true);
        this.add(label);
        this.add(K1Label);
        this.add(K2Label);
        this.add(K3Label);
        this.add(K4Label);
        this.add(saveLabel);
        this.add(SelectFile);
        this.add(Key1);
        this.add(Key2);
        this.add(Key3);
        this.add(Key4);
        this.add(SaveLocation);
        this.add(Decrypt_button);
        this.add(Back_button);

        this.setBackground(Color.CYAN);
        this.getContentPane().setBackground(Color.PINK);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //When Back Button is clicked
        if(e.getSource()==Back_button){
            new MyFrame();
            this.dispose();
        }

        //When SelectFile Button is clicked
        if(e.getSource()==SelectFile){
            JFileChooser fileChooser = new JFileChooser();
            selectresponse = fileChooser.showOpenDialog(null);
            if(selectresponse==JFileChooser.APPROVE_OPTION){
                SelectFile.setText("File Selected");
                File filepath = new File(fileChooser.getSelectedFile().getAbsolutePath());
                FilePATH = String.valueOf(filepath);
            }
        }

        //When Key 1 is clicked
        if(e.getSource()==Key1){
            JFileChooser fileChooser = new JFileChooser();
            K1response = fileChooser.showOpenDialog(null);
            if(K1response==JFileChooser.APPROVE_OPTION){
                Key1.setText("Selected");
                File filepath = new File(fileChooser.getSelectedFile().getAbsolutePath());
                K1 = String.valueOf(filepath);
            }
        }

        //When Key 2 is clicked
        if(e.getSource()==Key2){
            JFileChooser fileChooser = new JFileChooser();
            K2response = fileChooser.showOpenDialog(null);
            if(K2response==JFileChooser.APPROVE_OPTION){
                Key2.setText("Selected");
                File filepath = new File(fileChooser.getSelectedFile().getAbsolutePath());
                K2 = String.valueOf(filepath);
            }
        }

        //When Key 3 is clicked
        if(e.getSource()==Key3){
            JFileChooser fileChooser = new JFileChooser();
            K3response = fileChooser.showOpenDialog(null);
            if(K3response==JFileChooser.APPROVE_OPTION){
                Key3.setText("Selected");
                File filepath = new File(fileChooser.getSelectedFile().getAbsolutePath());
                K3 = String.valueOf(filepath);
            }
        }

        //When Key 4 is clicked
        if(e.getSource()==Key4){
            JFileChooser fileChooser = new JFileChooser();
            K4response = fileChooser.showOpenDialog(null);
            if(K4response==JFileChooser.APPROVE_OPTION){
                Key4.setText("Selected");
                File filepath = new File(fileChooser.getSelectedFile().getAbsolutePath());
                K4 = String.valueOf(filepath);
            }
        }

        //When Save Location Button is clicked
        if(e.getSource()==SaveLocation) {
            JFileChooser fileChooser = new JFileChooser();
            saveresponse = fileChooser.showSaveDialog(null);
            if(saveresponse==JFileChooser.APPROVE_OPTION){
                SaveLocation.setText("Location Selected");  //Button
                File filepath = new File(fileChooser.getSelectedFile().getAbsolutePath());
                SaveFilePATH= String.valueOf(filepath);
                //System.out.println("Save Image Location : " + filepath);

                //Getting Directory of Save Image Location to Save Key at that location
                File parentDir = filepath.getParentFile();
                ParentDirectory = String.valueOf(parentDir);
                //System.out.println("Parent Directory : " + ParentDirectory);

                //Getting File Name of Save Image if extension is not correct in that
                FileName = filepath.getName();
                //int last_index=-2;
                int last_index = FileName.lastIndexOf(".");
                if(last_index>0)
                    FileName = FileName.substring(0, FileName.lastIndexOf("."));
                else if(last_index==0)
                    FileName = "DecryptedImage";
                //System.out.println("FileName : " + FileName);
            }
        }

        //When Decrypt Button is clicked
        if(e.getSource()==Decrypt_button){
            //Below if condition is used so that it checks all the necessary file is uploaded
            //If it's not uploaded then it does not work
            if((selectresponse==JFileChooser.APPROVE_OPTION)&&(K1response==JFileChooser.APPROVE_OPTION)&&(K2response==JFileChooser.APPROVE_OPTION)&&(K3response==JFileChooser.APPROVE_OPTION)&&(K4response==JFileChooser.APPROVE_OPTION)&&(saveresponse==JFileChooser.APPROVE_OPTION)){
                try {
                    new ImageDecryption();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        }


    }
}
