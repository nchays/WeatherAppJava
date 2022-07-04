import org.json.simple.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.LinkPermission;
import java.util.Scanner;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class GUI extends JFrame implements ActionListener {
    Scanner scan = new Scanner(System.in);
    private JFrame frame;
    private JPanel panel;
    private JTextField input;
    private JTextArea result;
    private JButton button;
    private JLabel label;
    private WeatherAPI api;


    public JTextField getInput() {
        return input;
    }

    public JTextArea getResult() {
        return result;
    }

    public JButton getButton() {
        return button;
    }

    public void setInput(JTextField input) {
        this.input = input;
    }

    public JLabel getLabel() {
        return label;
    }

    public void setResult(String result) {
        this.result.setText(result);
    }

    public GUI() throws IOException {
        this.frame = new JFrame();
        this.panel = new JPanel();
        this.input = new JTextField();
        this.button = new JButton();
        this.label = new JLabel();
        this.result = new JTextArea();


        input.setColumns(10);


        button.setText("Search");
        button.addActionListener(this);
        label.setText("Enter country name");

        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 10));
        panel.setLayout(new GridLayout(1, 0));
        panel.add(label);
        panel.add(input);
        panel.add(button);
        panel.add(result);

        frame.setPreferredSize(new Dimension(1000, 200));
        frame.setResizable(false);
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Weather");
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            this.api = new WeatherAPI(this.input.getText());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        this.result.setText(api.getResult().toString());
        this.input.setText("");
    }

}
