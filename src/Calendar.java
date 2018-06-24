import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.awt.image.renderable.*;
import java.text.AttributedCharacterIterator;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Date;
import java.util.Map;
import java.util.Calendar;

class CalendarP extends JFrame {
    private final int DEFAULT_WIDTH = 640;
    private final int DEFAULT_HEIGHT = 480;
    private JPanel calendarPanel;
    private JPanel chooseMonthAndYear;
    private JPanel calendarDays;
    private GridBagConstraints gbc;


    //Calendar k;

    LocalDate date;

    int month = 1;
    int year = 1;

    private JComboBox<String> monthChoice;
    private JComboBox<Integer> yearChoice;

    private String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};


    public CalendarP() {
        calendarPanel = new JPanel(new BorderLayout());
        chooseMonthAndYear = new JPanel(new FlowLayout());
        calendarDays = new JPanel(new GridBagLayout());
        gbc = new GridBagConstraints();
        addMonth();
        addYear();

        calendarPanel.add(chooseMonthAndYear, BorderLayout.PAGE_START);
        calendarPanel.add(calendarDays, BorderLayout.CENTER);
        add(calendarPanel);
        add(new FontComponent(), BorderLayout.SOUTH);
        setSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void addMonth() {
        monthChoice = new JComboBox(months);

        monthChoice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                month = monthChoice.getSelectedIndex() + 1;
                System.out.println("Month: " + month);
                getDays();

            }
        });
        getDays();
        chooseMonthAndYear.add(monthChoice);
    }

    private void addYear() {
        yearChoice = new JComboBox<>();
        for (int i = 1990; i < 2050; i++) {
            yearChoice.addItem(i);
        }
        yearChoice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                year = yearChoice.getSelectedIndex() + 1990;
                System.out.println("Year: " + (year + 1990));
                getDays();

            }
        });
        getDays();
        chooseMonthAndYear.add(yearChoice);
    }

    class FontComponent extends JComponent {
        private static final int COMP_WIDTH = 640;
        private static final int COMP_HEIGHT = 400;

        public void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            date = LocalDate.of(year, month, 1);
            Font f = new Font("Helvetica", Font.BOLD, 18);
            g2.setColor(new Color(50, 0, 255));
            DayOfWeek weekday = date.getDayOfWeek();
            g2.setFont(f);
            g2.drawString("Mon  Tue  Wed   Thu  Fri   Sat   Sun",COMP_WIDTH/2-160,40);
            f = new Font("Helvetica", Font.BOLD, 32);
            g2.setFont(f);
            int i = 1;
            int x = COMP_WIDTH / 2 - 200;
            int y = 100;

            switch (weekday.getValue()) {
                case 1:
                    break;
                case 2:
                    x += 44;
                    break;
                case 3:
                    x += 88;
                    break;
                case 4:
                    x+=132;
                    break;
                case 5:
                    x+=176;
                    break;
                case 6:
                    x+=220;
                    break;
                case 7:
                    x+=264;
                    break;
            }


            while (date.getMonthValue() == month) {
                g2.drawString("" + i, x += 44, y);

                date = date.plusDays(1);
                if (date.getDayOfWeek().getValue() == 1) {
                    y += 34;
                    x = COMP_WIDTH / 2 - 200;
                }

                i++;
            }
            repaint();
        }

        public Dimension getPreferredSize() {
            return new Dimension(COMP_WIDTH, COMP_HEIGHT);
        }
    }

    public void getDays() {
        add(new FontComponent());
    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(() -> new CalendarP());
    }
}