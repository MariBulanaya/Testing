/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mari2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    private final static JFrame mMainFrame = new JFrame("Программа для тестирования знаний");
    private final static JFrame mInfoFrame = new JFrame("Введите ваши данные");
    private final static String[] ANSWERS = new String[]{"OTBET 4", "OTBET 2", "OTBET 3", "OTBET 1", "OTBET 5"};
    private final static String[] ANSWERS2 = new String[]{"OTBET 1", "OTBET 2", "OTBET 6", "OTBET 4", "OTBET 5"};
    private final static String[] ANSWERS3 = new String[]{"OTBET 6", "OTBET 2", "OTBET 3", "OTBET 4", "OTBET 5"};
    private final static String[] ANSWERS4 = new String[]{"OTBET 1", "OTBET 2", "OTBET 3", "OTBET 4", "OTBET 5"};
    private final static String[] ANSWERS5 = new String[]{"OTBET 1", "OTBET 2", "OTBET 3", "OTBET 4", "OTBET 5"};
    private final static String[] CORRECT_ANSWERS = new String[]{"OTBET 4", "OTBET 6", "OTBET 6", "OTBET 4", "OTBET 5"};
    private static String mFirstName;
    private static String mLastName;
    private static File LOG_FILE = new File("Log_of_testing.txt");

    public static void main(String[] args) {
        try {
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        mMainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mMainFrame.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent focusEvent) {
                if (mFirstName != null && !mFirstName.isEmpty() && mLastName != null && !mLastName.isEmpty()) {
                    beginTest();
                }
            }

            @Override
            public void focusLost(FocusEvent focusEvent) {
            }
        });

        Font font = new Font("Courier New", Font.BOLD, 16);

        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("Файл");
        fileMenu.setFont(font);

        JMenu helpMenu = new JMenu("Справка");
        helpMenu.setFont(font);

        JMenuItem newTest = new JMenuItem("Новый тест");
        newTest.setFont(font);
        newTest.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mFirstName = null;
                mLastName = null;

                Container container = mMainFrame.getContentPane();
                container.removeAll();
                mMainFrame.pack();

                JPanel panelFirstName = new JPanel(new FlowLayout());
                JPanel panelLastName = new JPanel(new FlowLayout());
                JPanel panelButton = new JPanel(new FlowLayout());

                JLabel jLabelFirstName = new JLabel("Имя");
                jLabelFirstName.setPreferredSize(new Dimension(80, 30));

                final JTextField jTextFieldFirstName = new JTextField("");
                jTextFieldFirstName.setPreferredSize(new Dimension(300, 30));

                panelFirstName.add(jLabelFirstName);
                panelFirstName.add(jTextFieldFirstName);

                JLabel jLabelLastName = new JLabel("Фамилия");
                jLabelLastName.setPreferredSize(new Dimension(80, 30));

                final JTextField jTextFieldLastName = new JTextField("");
                jTextFieldLastName.setPreferredSize(new Dimension(300, 30));

                panelLastName.add(jLabelLastName);
                panelLastName.add(jTextFieldLastName);

                JButton jButton = new JButton("Ок");
                jButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String firstName = jTextFieldFirstName.getText().toString();

                        String lastName = jTextFieldLastName.getText().toString();

                        if (!firstName.isEmpty() && !lastName.isEmpty()) {
                            mFirstName = firstName;
                            mLastName = lastName;
                            mInfoFrame.dispose();
                        } else {
                            JOptionPane.showMessageDialog(null, "Некорректные данные, введите имя и фамилию");
                        }

                    }
                });
                panelButton.add(jButton);

                panelFirstName.setAlignmentX(Component.CENTER_ALIGNMENT);
                panelLastName.setAlignmentX(Component.CENTER_ALIGNMENT);
                panelButton.setAlignmentX(Component.CENTER_ALIGNMENT);

                container = mInfoFrame.getContentPane();
                container.setLayout(new BoxLayout(mInfoFrame.getContentPane(), BoxLayout.Y_AXIS));
                container.removeAll();
                container.add(panelFirstName);
                container.add(panelLastName);
                container.add(panelButton);

                mInfoFrame.setPreferredSize(new Dimension(450, 150));
                mInfoFrame.pack();
                mInfoFrame.setLocationRelativeTo(mMainFrame);
                mInfoFrame.setVisible(true);
            }
        });


        JMenuItem exit = new JMenuItem("Выход");
        exit.setFont(font);
        exit.setMnemonic(KeyEvent.VK_E);
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        JMenuItem about = new JMenuItem("О программе");
        about.setFont(font);
        about.addActionListener(new ActionListener() {
            @SuppressWarnings("serial")
            public void actionPerformed(ActionEvent e) {
                JPanel panelAbout = new JPanel() {
                    public void paint(Graphics g) {
                        g.setFont(new Font("Verdana", Font.PLAIN, 18));
                        g.setColor(Color.black);
                        g.drawString("Программа для тестирования знаний", 5, 25);
                        g.drawString("Версия: 1.0", 5, 50);
                        g.drawString("Создатель:", 5, 75);
                        g.drawString("Студентка КС Буланая Маша", 5, 100);
                        g.drawString("(c) Все права защищены =)", 5, 125);
                    }
                };
                panelAbout.repaint();
                panelAbout.setBounds(0, 0, 400, 420);
                panelAbout.setVisible(true);
                Container container = mMainFrame.getContentPane();
                container.removeAll();
                container.setLayout(null);
                container.setBackground(new Color(200, 200, 200));   // цвет фона
                container.add(panelAbout);
            }
        });

        fileMenu.add(newTest);
        fileMenu.addSeparator();
        fileMenu.add(exit);
        helpMenu.add(about);

        menuBar.add(fileMenu);
        menuBar.add(helpMenu);

        mMainFrame.setJMenuBar(menuBar);
        mMainFrame.setPreferredSize(new Dimension(500, 600));
        mMainFrame.pack();
        mMainFrame.setResizable(false);
        mMainFrame.setLocation(250, 100);
        mMainFrame.setVisible(true);

    }

    private static void beginTest() {
        final JLabel jLabelInfo = new JLabel("Тест. Студент(ка): " + mFirstName + " " + mLastName);
        jLabelInfo.setPreferredSize(new Dimension(450, 30));

        JPanel panelInfo = new JPanel(new FlowLayout());
        panelInfo.add(jLabelInfo);
        panelInfo.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JLabel jLabel1 = new JLabel("Bonpoc 1: 2+2?");
        jLabel1.setPreferredSize(new Dimension(450, 30));

        final JComboBox comboBox1 = new JComboBox(ANSWERS);
        comboBox1.setPreferredSize(new Dimension(450, 30));

        JPanel panelQuestion1 = new JPanel(new FlowLayout());
        panelQuestion1.add(jLabel1);
        panelQuestion1.add(comboBox1);
        panelQuestion1.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JLabel jLabel2 = new JLabel("Bonpoc 2: 3+3?");
        jLabel2.setPreferredSize(new Dimension(450, 30));

        final JComboBox comboBox2 = new JComboBox(ANSWERS2);
        comboBox2.setPreferredSize(new Dimension(450, 30));

        JPanel panelQuestion2 = new JPanel(new FlowLayout());
        panelQuestion2.add(jLabel2);
        panelQuestion2.add(comboBox2);
        panelQuestion2.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JLabel jLabel3 = new JLabel("Bonpoc 3: 2+2*2?");
        jLabel3.setPreferredSize(new Dimension(450, 30));

        final JComboBox comboBox3 = new JComboBox(ANSWERS3);
        comboBox3.setPreferredSize(new Dimension(450, 30));

        JPanel panelQuestion3 = new JPanel(new FlowLayout());
        panelQuestion3.add(jLabel3);
        panelQuestion3.add(comboBox3);
        panelQuestion3.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JLabel jLabel4 = new JLabel("Bonpoc 4: 5-1?");
        jLabel4.setPreferredSize(new Dimension(450, 30));

        final JComboBox comboBox4 = new JComboBox(ANSWERS4);
        comboBox4.setPreferredSize(new Dimension(450, 30));

        JPanel panelQuestion4 = new JPanel(new FlowLayout());
        panelQuestion4.add(jLabel4);
        panelQuestion4.add(comboBox4);
        panelQuestion4.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JLabel jLabel5 = new JLabel("Bonpoc 5: 20/4?");
        jLabel5.setPreferredSize(new Dimension(450, 30));

        final JComboBox comboBox5 = new JComboBox(ANSWERS5);
        comboBox5.setPreferredSize(new Dimension(450, 30));

        JPanel panelQuestion5 = new JPanel(new FlowLayout());
        panelQuestion5.add(jLabel5);
        panelQuestion5.add(comboBox5);
        panelQuestion5.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton jButton = new JButton("Завершить");
        jButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String log = jLabelInfo.getText() + "\n";
                log += "\n" + jLabel1.getText() + " - " + String.valueOf(comboBox1.getSelectedItem());
                log += "\n" + jLabel2.getText() + " - " + String.valueOf(comboBox2.getSelectedItem());
                log += "\n" + jLabel3.getText() + " - " + String.valueOf(comboBox3.getSelectedItem());
                log += "\n" + jLabel4.getText() + " - " + String.valueOf(comboBox4.getSelectedItem());
                log += "\n" + jLabel5.getText() + " - " + String.valueOf(comboBox5.getSelectedItem());

                int rating = 0;
                if (CORRECT_ANSWERS[0].equals(String.valueOf(comboBox1.getSelectedItem()))) {
                    rating++;
                }
                if (CORRECT_ANSWERS[1].equals(String.valueOf(comboBox2.getSelectedItem()))) {
                    rating++;
                }
                if (CORRECT_ANSWERS[2].equals(String.valueOf(comboBox3.getSelectedItem()))) {
                    rating++;
                }
                if (CORRECT_ANSWERS[3].equals(String.valueOf(comboBox4.getSelectedItem()))) {
                    rating++;
                }
                if (CORRECT_ANSWERS[4].equals(String.valueOf(comboBox5.getSelectedItem()))) {
                    rating++;
                }

                log += "\n\nОценка " + rating + " баллов";
                wtireToFile(log);

                showRating(rating);
            }
        });
        JPanel panelButton = new JPanel(new FlowLayout());
        panelButton.add(jButton);
        panelButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        Container container = mMainFrame.getContentPane();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.removeAll();
        container.add(panelInfo);
        container.add(panelQuestion1);
        container.add(panelQuestion2);
        container.add(panelQuestion3);
        container.add(panelQuestion4);
        container.add(panelQuestion5);
        container.add(panelButton);
        mMainFrame.pack();

    }

    private static void showRating(int rating) {
        final JLabel jLabelInfo = new JLabel("Тест окончен. Студент(ка): " + mFirstName + " " + mLastName
                + " набрал(а) " + rating + " баллов");
        jLabelInfo.setPreferredSize(new Dimension(450, 30));

        JPanel panelInfo = new JPanel(new FlowLayout());
        panelInfo.add(jLabelInfo);
        panelInfo.setAlignmentX(Component.CENTER_ALIGNMENT);

        Container container = mMainFrame.getContentPane();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.removeAll();
        container.add(panelInfo);
        mMainFrame.pack();
    }

    public static void wtireToFile(String log) {
        BufferedWriter bufferWritter = null;
        try {
            log = "-----------------------------\n" + log;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss");
            log = simpleDateFormat.format(System.currentTimeMillis()) + "\n" + log + "\n\n";

            if (!LOG_FILE.exists()) {
                LOG_FILE.createNewFile();
            }

            FileWriter fileWritter = new FileWriter(LOG_FILE.getPath(), true);
            bufferWritter = new BufferedWriter(fileWritter);
            bufferWritter.write(log);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferWritter != null) {
                try {
                    bufferWritter.close();
                } catch (IOException e) {
                }
            }
        }
    }
}