//GetSheets class is the same as previous version
import javax.swing.*;

import model.TeacherSchedule;
import process.ScheduleGenerator;

import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ScheduleGUI extends JPanel {
    private static final String FILE_NAME = "SavedSchedule.txt";
    TeacherSchedule teacherSchedule = new TeacherSchedule();

    public ScheduleGUI() {
        super(new GridLayout(1, 0));

        Object[] options = {"Load Schedule", "Create new Schedule"};
int n = JOptionPane.showOptionDialog(null,
    "Would you like to create or load a schedule?",
    "Supervision Schedule",
    JOptionPane.YES_NO_OPTION,
    JOptionPane.QUESTION_MESSAGE,
    null,
    options,
    options[1]);

        String[] columnNames = {"Time",
                "Duty",
                "Monday",
                "Tuesday",
                "Wednesday",
                "Thursday",
                "Friday"
        };

        Object[][] data = null;
        if (n == 0){
            data = loadSchedule();
        }
        else{
            // ArrayList<Object[]> list;
            // try {
                ScheduleGenerator generator = new ScheduleGenerator();
                data = generator.processSchedule();
                saveScheduleToFile(data);
                //list = new ArrayList<>(GetSheets.fetchDataFromSheet());
                // data = createNewScedule(list);
            // } catch (IOException e) {
            //     e.printStackTrace();
            // } catch (GeneralSecurityException e) {
            //     e.printStackTrace();
            // }
        }
        
        final JTable table = new JTable(data, columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(1300, 250));
        table.setFillsViewportHeight(true);
        table.setEnabled(false);

        // Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);

        // Add the scroll pane to this panel.
        add(scrollPane);
    }

    private static void saveScheduleToFile(Object[][] data) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME));
            writer.write("");
            for (Object[] rowData : data) {
                for (Object cellData : rowData) {
                    if (cellData != null) {
                        writer.write(cellData.toString());
                    }
                    writer.write("_");

                }
                writer.newLine();
            }
            writer.close();
            System.out.println("Schedule saved to " + FILE_NAME);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error saving schedule to " + FILE_NAME);
        }
    }
    // (rows)[columns]
    // private static Object[][] createNewScedule(ArrayList<Object[]> list) throws IOException, GeneralSecurityException {

    // Object[][] data = {
    //         {"11:25 am - 12:02 pm", "Cafeteria", list.get(0)[0], list.get(0)[1], list.get(0)[2], list.get(0)[3], list.get(0)[4]}, 
    //         {" ", "Library", list.get(1)[0], list.get(1)[1], list.get(1)[2], list.get(1)[3], list.get(1)[4]},
    //         {"11:35 am - 12:12 pm", "Back Foyer & Art/ASD hallways", list.get(2)[0], list.get(2)[1], list.get(2)[2], list.get(2)[3], list.get(2)[4]},
    //         {" ", "Front Foyer & Gym/Tech hallways", list.get(3)[0], list.get(3)[1], list.get(3)[2], list.get(3)[3], list.get(3)[4]},
    //         {" ", "Library", list.get(4)[0], list.get(4)[1], list.get(4)[2], list.get(4)[3], list.get(4)[4]},
    //         {"11:38 am - 12:15 pm", "Gym/Weight Room", list.get(5)[0], list.get(5)[1], list.get(5)[2], list.get(5)[3], list.get(5)[4]},
    //         {" ", "Student Services", list.get(6)[0], list.get(6)[1], list.get(6)[2], list.get(6)[3], list.get(6)[4]},
    //         {" ", "Front & Back Foyer", list.get(7)[0], list.get(7)[1], list.get(7)[2], list.get(7)[3], list.get(7)[4]},
    //         {" ", "Floor 2 & 3", list.get(8)[0], list.get(8)[1], list.get(8)[2], list.get(8)[3], list.get(8)[4]},
    //         {" ", "Room 314", list.get(9)[0], list.get(9)[1], list.get(9)[2], list.get(9)[3], list.get(9)[4]},
    //         {" ", "ASD", list.get(10)[0], list.get(10)[1], list.get(10)[2], list.get(10)[3], list.get(10)[4]},
    //         {"11:48 am - 12:25 pm", "Back Foyer & Art/ASD hallways", list.get(11)[0], list.get(11)[1], list.get(11)[2], list.get(11)[3], list.get(11)[4]},
    //         {" ", "Front Foyer & Gym/Tech hallways", list.get(12)[0], list.get(12)[1], list.get(12)[2], list.get(12)[3], list.get(12)[4]},
    //         {" ", "Cafeteria", list.get(13)[0], list.get(13)[1], list.get(13)[2], list.get(13)[3], list.get(13)[4]},
    //         {" ", "Library", list.get(14)[0], list.get(14)[1], list.get(14)[2], list.get(14)[3], list.get(14)[4]}
    //     };
    //     saveScheduleToFile(data);
    //     return data;

        
    // }

    private static Object[][] loadSchedule(){
        Object[][] scheduleData = new Object[16][7];

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            int row = 0;

            while ((line = reader.readLine()) != null && row < 16) {
                String[] cellData = line.split("_");

                for (int col = 0; col < Math.min(cellData.length, 7); col++) {
                    scheduleData[row][col] = cellData[col];
                }

                row++;
            }

            System.out.println("Schedule loaded from " + FILE_NAME);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading schedule from " + FILE_NAME);
        }

        return scheduleData;
    }

    /**
     * Create the GUI and show it. For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        // Create and set up the window.
        JFrame frame = new JFrame("Supervision Schedule");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create and set up the content pane.
        ScheduleGUI newContentPane = new ScheduleGUI();
        newContentPane.setOpaque(true); // content panes must be opaque
        frame.setContentPane(newContentPane);

        // Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        // Schedule a job for the event-dispatching thread:
        // creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}