package project;

import javax.swing.*;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;
import project.myresources.ButtonColumn;
import project.myresources.MedicationPlanService;

@Configuration

public class ClientApp extends JFrame {

    private JLabel label = new JLabel("Current time: ");
    private JLabel timeLabel = new JLabel();
    private JPanel panel = new JPanel();
    private JTable table;
    private DefaultTableModel model;

    @Bean
    public HttpInvokerProxyFactoryBean invoker() {
        HttpInvokerProxyFactoryBean invoker = new HttpInvokerProxyFactoryBean();
        invoker.setServiceUrl("https://springs-demo-ds2020.herokuapp.com/booking");
        invoker.setServiceInterface(MedicationPlanService.class);
        return invoker;
    }

    public String[] getRowAt(int row, int colNumber) {
        String[] result = new String[colNumber];

        for (int i = 0; i < colNumber; i++) {
            result[i] = (String) table.getModel().getValueAt(row, i);
        }

        return result;
    }


    private void send(String message, MedicationPlanService service){

        service.sendMessage(message);

    }

    public ClientApp(){

    }


    public ClientApp(String filename,MedicationPlanService service) {

        List<String> response = new ArrayList<>();
        final DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

        String[] columnNames = {"Name", "Dosage", "Start intake", "End intake", "Start tratament", "End tratament", "Taken"};

        try {
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                response.add(myReader.nextLine());
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        int size = response.size();

        Object[][] data = new Object[size][columnNames.length];

        for (int i = 0; i < size; i++) {
            String[] elements = response.get(i).split(" ");
            for (int j = 0; j < elements.length; j++) {

                data[i][j] = elements[j];
                if (j == elements.length - 1) data[i][j + 1] = "Taken";
            }

        }


        model = new DefaultTableModel(data, columnNames);
        table = new JTable(model);


        table.getColumn(columnNames[4]).setMinWidth(75);
        table.getColumn(columnNames[5]).setMinWidth(75);
        table.getColumn(columnNames[6]).setMinWidth(75);


        GridBagLayout layout = new GridBagLayout();
        panel.setLayout(layout);
        GridBagConstraints gbc = new GridBagConstraints();

        // Put constraints on different buttons
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(label, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(timeLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 2;


        panel.add(new JScrollPane(table), gbc);

        add(panel);

        Action delete = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                Date date = new Date();
                Date date1 = new Date();
                Date date2 = new Date();
                JTable table = (JTable) e.getSource();
                int modelRow = Integer.valueOf(e.getActionCommand());
                String[] rowContent = getRowAt(modelRow, columnNames.length);

                String[] timp = rowContent[4].split(":");
                date1.setHours(Integer.parseInt(timp[0]));
                date1.setMinutes(Integer.parseInt(timp[1]));
                if(timp.length>2) {
                    date1.setSeconds(Integer.parseInt(timp[2]));
                }
                else {
                    date1.setSeconds(0);
                }
                timp = rowContent[5].split(":");
                date2.setHours(Integer.parseInt(timp[0]));
                date2.setMinutes(Integer.parseInt(timp[1]));
                if(timp.length>2) {
                    date2.setSeconds(Integer.parseInt(timp[2]));
                }
                else {
                    date2.setSeconds(0);
                }

                if (date.compareTo(date1) >= 0 && date.compareTo(date2) <= 0) {
                    ((DefaultTableModel) table.getModel()).removeRow(modelRow);

                    int count = 0;

                    File input = new File(filename);

                    String filename1 = "medical_plan1.txt";

                    File myObj = new File(filename1);


                    try {

                        if (myObj.createNewFile()) {
                            System.out.println("File created: " + myObj.getName());
                        } else {
                            System.out.println("File already exists.");
                        }
                    } catch (IOException ee) {
                        System.out.println("An error occurred.");
                        ee.printStackTrace();
                    }

                    FileWriter output1 = null;
                    Scanner sc = null;
                    try {
                        output1 = new FileWriter(filename1);
                        sc = new Scanner(input);

                        while (sc.hasNextLine()) {

                            String s = sc.nextLine();
                            if (count != modelRow) {
                                output1.write(s);
                                output1.write("\n");
                            }
                            count++;
                        }

                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    } finally {
                        sc.close();
                        try {
                            output1.close();
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    }

                    if (input.delete()) {
                        System.out.println("Deleted the file: " + input.getName());
                    } else {
                        System.out.println("Failed to delete the file.");
                    }

                    boolean success = myObj.renameTo(input);

                    if (success) {
                        System.out.println("File with name " + filename1 + " renamed to " + filename);
                    }

                    send("The patient took the medicine " + rowContent[0], service);

                }

            }
        };

        ButtonColumn buttonColumn = new ButtonColumn(table, delete, 6);
        buttonColumn.setMnemonic(KeyEvent.VK_D);

        ActionListener timerListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                Date date = new Date();
                Date timeDownload = new Date();
                timeDownload.setHours(1);
                timeDownload.setMinutes(14);
                timeDownload.setSeconds(55);

                String time = timeFormat.format(date);
                timeLabel.setText(time);

                long timp = date.getTime() - timeDownload.getTime();
                if (timp > 0 && timp < 1500) {

                    File myObj = new File(filename);
                    if (myObj.delete()) {
                        System.out.println("Deleted the file: " + myObj.getName());
                    } else {
                        System.out.println("Failed to delete the file.");
                    }

                    try {
                        if (myObj.createNewFile()) {
                            System.out.println("File created: " + myObj.getName());
                        } else {
                            System.out.println("File already exists.");
                        }
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    List<String>  response = service.getMedicalPlan(timeDownload);

                    FileWriter myWriter = null;
                    try {
                        myWriter = new FileWriter(filename);
                        for (String s : response) {
                            String[] components = s.split(" ");
                            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                            Date startDate = df.parse(components[2]);
                            Date endDate = df.parse(components[3]);
                            Date nowDate = new Date();
                            if(nowDate.compareTo(startDate)>=0 && nowDate.compareTo(endDate)<=0) {
                                myWriter.write(s);
                                myWriter.write("\n");
                            }
                        }

                        myWriter.close();

                    } catch (IOException | ParseException ioException) {
                        ioException.printStackTrace();
                    } finally {
                        try {
                            myWriter.close();
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    }


                    setVisible(false);
                    dispose();

                    new ClientApp(filename,service);
                }

                try {
                    File myObj = new File(filename);
                    Scanner myReader = new Scanner(myObj);
                    boolean ok = false;
                    List<String> rows = new ArrayList<>();
                    int counter = 0;
                    while (myReader.hasNextLine()) {
                        String line = myReader.nextLine();
                        String[] components = line.split(" ");
                        Date currentDate = new Date();
                        Date dateForComponent2 = new Date();
                        String[] timeForDate = components[5].split(":");
                        if(timeForDate.length>2) {
                        dateForComponent2.setSeconds(Integer.parseInt(timeForDate[2]));
                        }
                        else {
                            dateForComponent2.setSeconds(0);
                        }
                        dateForComponent2.setMinutes(Integer.parseInt(timeForDate[1]));
                        dateForComponent2.setHours(Integer.parseInt(timeForDate[0]));


                        if (currentDate.compareTo(dateForComponent2) < 0) {
                            rows.add(line);
                        }

                        else {
                            if(table.getModel().getRowCount()!=0) {
                                ((DefaultTableModel) table.getModel()).removeRow(counter);
                                counter--;
                                ok = true;
                                send("The patient did not take the medicine " + components[0],service);
                            }
                        }

                        counter++;
                    }
                    myReader.close();

                    if(ok) {

                        if (myObj.delete()) {
                            System.out.println("Deleted the file: " + myObj.getName());
                        } else {
                            System.out.println("Failed to delete the file.");
                        }


                        myObj = new File(filename);
                        if (myObj.createNewFile()) {
                            System.out.println("File created: " + myObj.getName());
                        } else {
                            System.out.println("File already exists.");
                        }

                        FileWriter writer = null;

                        writer = new FileWriter(myObj);

                        for (String row : rows) {
                            writer.write(row);
                            writer.write("\n");
                        }

                        writer.close();
                    }


                } catch (FileNotFoundException ee) {
                    System.out.println("An error occurred.");
                    ee.printStackTrace();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

            }
        };
        Timer timer = new Timer(1000, timerListener);
        // to make sure it doesn't wait one second at the start
        timer.setInitialDelay(0);
        timer.start();

        setTitle("Medication Dispenser");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        setSize(new Dimension(500, 600));
    }


    public static void main(String args[]) {

        MedicationPlanService service = SpringApplication
                .run(ClientApp.class, args)
                .getBean(MedicationPlanService.class);

        String filename = "medicalplan.txt";

        try {
            File myObj = new File(filename);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }

            new ClientApp(filename,service);
        } catch (Exception ae) {
            System.out.println(ae);
        }

    }


}
