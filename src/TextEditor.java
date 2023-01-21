import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {

    JFrame frame;
    JMenuBar menuBar;
    JMenu file,edit;
    JMenuItem newFile,saveFile,openFile;
    JMenuItem cut,copy,paste,selectALL,close;
    JTextArea textArea;

    TextEditor(){
        //intialize frame
        frame = new JFrame();
        //intialize textarea
        textArea = new JTextArea();
        //intialize menu bar
        menuBar = new JMenuBar();
        // initialize menu
        file = new JMenu("File");
        edit = new JMenu("Edit");
        //intilalize menu items
        //file menu items
        newFile = new JMenuItem("New window");
        saveFile = new JMenuItem("Save File");
        openFile = new JMenuItem("Open File");
                //edit menu items
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectALL = new JMenuItem("Select ALL");
        close =  new JMenuItem("Close Window");

        // add action listiner  to file  menu items
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);

        // add action listiner to edit menu items
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectALL.addActionListener(this);
        close.addActionListener(this);

        //adding menu items to file menu
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        //Adding menu items to edit menu
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectALL);
        edit.add(close);

        //add menu to menubar
        menuBar.add(file);
        menuBar.add(edit);

        //set menubar to our frame
        frame.setJMenuBar(menuBar);
        //create contet panel
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0,0));

        //add text area to panel
        panel.add(textArea,BorderLayout.CENTER);
        //create a scroll bar
        JScrollPane scrollPane = new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        //add scroll to pane to panel
        panel.add(scrollPane);
        // add pannel to frame
        frame.add(panel);
        //add text area to frame
      //  frame.add(textArea);
        //set dimensions of frame
        frame.setBounds(100,100,400,400);
        //set frame to be visible
        frame.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        // if cut menu item is clicked
        if (actionEvent.getSource() == cut) {
            textArea.cut();
        }
        if (actionEvent.getSource() == copy) {
            textArea.copy();
        }
        if (actionEvent.getSource() == paste) {
            textArea.paste();
        }
        if (actionEvent.getSource() == selectALL) {
            textArea.selectAll();
        }
        if (actionEvent.getSource() == close) {
            System.exit(0);
        }
        if (actionEvent.getSource() == saveFile) {
            //initialize file chooser
            JFileChooser fileChooser = new JFileChooser("C:");
            //get choosen option from file chooser
            int chooseOption = fileChooser.showSaveDialog(null);
            // if choosen option is the opprove option
            if (chooseOption == JFileChooser.APPROVE_OPTION) {
                // create a new file with directorys path
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath() + ".txt");
                try {
                    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
                    textArea.write(bufferedWriter);
                    bufferedWriter.close();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        }

        if(actionEvent.getSource()==openFile){
            //intialized a file chooser
            JFileChooser fileChooser = new JFileChooser("C:");
            //getting chosen option in file chooser lwa
            int chooseOption = fileChooser.showOpenDialog(null);
            //if choose option is the approve option
            if(chooseOption==JFileChooser.APPROVE_OPTION){
                //getting selected fike from file chooser
                File file = fileChooser.getSelectedFile();
                // getting path of the selected file
                String filePath = file.getPath();
                try {
                    //intialize file reader
                    FileReader fileReader = new FileReader(filePath);
                    //intialize bufferreader
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    //intermediate for current linr ; outut line for content of file
                    String intermediate = " ", output = " ";
                    //read line by line
                    while((intermediate = bufferedReader.readLine())!= null){
                        output += intermediate+"\n";
                    }
                    //set text area with the content of the file
                    textArea.setText(output);
                }
                    catch (Exception exception){
                    exception.printStackTrace();
                    }
            }
        }
        if(actionEvent.getSource() == newFile){
            TextEditor newWindow = new TextEditor();
        }
    }
    public static void main(String[] args){

        TextEditor textEditor = new TextEditor();
    }
}