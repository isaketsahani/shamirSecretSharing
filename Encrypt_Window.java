import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Encrypt_Window extends JFrame implements ActionListener {

    static String FilePATH;
    static String SaveFilePATH;
    static String ParentDirectory;
    static String FileName;
    JButton Encrypt_button;
    JButton Back_button;
    JButton SelectFile;
    JButton SaveFile;

    int response=1; //1 means the file is not chosen
    int saveresponse=1;
    Encrypt_Window(){
        Border border = BorderFactory.createMatteBorder(1,1,1,1, Color.WHITE);


        //Back Button
        Back_button = new JButton("Back");
        Back_button.setBounds(870,10,100,30);
        Back_button.addActionListener(this);


        //Upload Image to Encrypt
        JLabel label = new JLabel("Upload Image");
        label.setBorder(BorderFactory.createEtchedBorder());
        label.setBounds(200,100,120,40);
        label.setForeground(Color.BLACK);
        label.setOpaque(false);

        SelectFile = new JButton("Select File");
        SelectFile.setBounds(400,100,160,40);
        SelectFile.addActionListener(this);


        // Save Location of Encrypted Image
        JLabel saveLabel = new JLabel("Save Image");
        saveLabel.setBorder(BorderFactory.createEtchedBorder());
        saveLabel.setBounds(200,200,120,40);
        saveLabel.setForeground(Color.BLACK);
        saveLabel.setOpaque(false);

        SaveFile = new JButton("Select Location");
        SaveFile.setBounds(400,200,160,40);
        SaveFile.addActionListener(this);


        //Encrypt Button
        Encrypt_button = new JButton("Encrypt");
        Encrypt_button.setBounds(400,350,150,50);
        Encrypt_button.addActionListener(this);



        this.setTitle("Encryption");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000,600);
        this.setLayout(null);
        this.setVisible(true);
        this.add(Encrypt_button);
        this.add(Back_button);
        this.add(label);
        this.add(saveLabel);
        this.add(SelectFile);
        this.add(SaveFile);

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

        //When Select File is clicked
        if(e.getSource()==SelectFile){
            JFileChooser fileChooser = new JFileChooser();
            response = fileChooser.showOpenDialog(null);
            if(response==JFileChooser.APPROVE_OPTION){
                SelectFile.setText("File Uploaded");
                File filepath = new File(fileChooser.getSelectedFile().getAbsolutePath());
                FilePATH= String.valueOf(filepath);
            }
        }

        //When Save File is clicked
        if(e.getSource()==SaveFile){
            JFileChooser fileChooser = new JFileChooser();
            saveresponse = fileChooser.showSaveDialog(null);
            if(saveresponse==JFileChooser.APPROVE_OPTION){
                SaveFile.setText("Location Selected");//Button
                File filepath = new File(fileChooser.getSelectedFile().getAbsolutePath());
                SaveFilePATH= String.valueOf(filepath);//Gets the path to save
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
                        FileName = "EncryptedImage";
                //System.out.println("FileName : " + FileName);
            }
        }

        //System.out.println("Response : " + response);
        //System.out.println("SaveResponse : " + saveresponse);

        //When Encrypt button is clicked
        if(e.getSource()==Encrypt_button){
        if((response==JFileChooser.APPROVE_OPTION)&&(saveresponse==JFileChooser.APPROVE_OPTION)){
                try {
                    //System.out.println("Triggered...");
                    new ImageEncryption();

                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        }

    }
}
