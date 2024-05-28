import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * The SeatSelectionFrame class represents a frame for selecting movie seats and confirming the purchase.
 */
public class SeatSelectionFrame extends JFrame {
    private JButton[][] seatButtons;
    private JComboBox<Integer> seatCountComboBox;
    private JLabel paymentLabel;
    private JComboBox<String> timeOptions;
    private Queue<Pair<Integer, Integer>> reservedSeatsQueue;
    private int selectedSeatCount = 0;
    int currentRow = 0;
    int currentCol = 0;
    Movie movie;

    public SeatSelectionFrame(User user, Movie movie) {
        super("Seat Selection");
        this.movie = movie;
        setSize(1100, 600);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        ImageIcon seatSelectionImage = new ImageIcon("background\\SeatSelection.png");
        JLabel seatSelectionLabel = new JLabel(seatSelectionImage);
        seatSelectionLabel.setBounds(700, 75, 360, 315);
        add(seatSelectionLabel);

        JLabel yourInformationLabel = new JLabel("<html><font color='#EBC548FF'>|</font> " + " Choose what suits you:");
        yourInformationLabel.setFont(new Font("Arial", Font.BOLD, 35));
        yourInformationLabel.setForeground(new Color(69, 71, 75));
        yourInformationLabel.setBounds(50, 50, 600, 50);
        add(yourInformationLabel);

        timeOptions = new JComboBox<>(new String[]{movie.getShowtimes().get(0).getDate() + " " + movie.getShowtimes().get(0).getInterval().time, movie.getShowtimes().get(1).getDate() + " " + movie.getShowtimes().get(1).getInterval().time, movie.getShowtimes().get(2).getDate() + " " + movie.getShowtimes().get(2).getInterval().time});
        timeOptions.setBounds(380, 506, 170, 20);
        timeOptions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateComboBoxItems(seatCountComboBox);
            }
        });
        add(timeOptions);

        JPanel headerPanel = new JPanel(new FlowLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                int arcDiameter = 10;
                RoundRectangle2D roundRect = new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), arcDiameter, arcDiameter);
                g2.fill(roundRect);
                g2.dispose();
            }
        };
        headerPanel.setBounds(75, 130, 600, 20);

        JLabel screenLabel = new JLabel("Screen");
        screenLabel.setFont(new Font("Calibri Light", Font.BOLD, 14));
        screenLabel.setForeground(Color.WHITE);
        headerPanel.add(screenLabel);

        add(headerPanel);

        seatCountComboBox = new JComboBox<>();
        for (int i = 1; i <= countGreenSeats(movie); i++) {
            seatCountComboBox.addItem(i);
        }
        seatCountComboBox.setBounds(470, 445, 80, 20);
        Timer timer = new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedSeatCount = (int) seatCountComboBox.getSelectedItem();
                if (reservedSeatsQueue.size() > selectedSeatCount) {
                    Pair<Integer, Integer> oldSeat = reservedSeatsQueue.poll();
                    seatButtons[oldSeat.getFirst()][oldSeat.getSecond()].setBackground(new Color(73, 94, 87));
                }
                paymentLabel.setText(selectedSeatCount * movie.getPrice() + " SP");
            }
        });
        timer.start();
        add(seatCountComboBox);

        JComboBox<String> paymentOptions = new JComboBox<>(new String[]{"Cash", "Credit Card"});
        paymentOptions.setBounds(450, 475, 100, 20);
        add(paymentOptions);

        JLabel chooseNumLabel = new JLabel("<html><font color='#495E57FF'>■</font> " + " Choose the number of tickets that suits you:");
        chooseNumLabel.setFont(new Font("Bahnschrift", Font.BOLD, 16));
        chooseNumLabel.setForeground(new Color(69, 71, 75));
        chooseNumLabel.setBounds(85, 444, 600, 20);
        add(chooseNumLabel);

        JLabel choosePayMethodLabel = new JLabel("<html><font color='#495E57FF'>■</font> " + " Choose the payment method that suits you:");
        choosePayMethodLabel.setFont(new Font("Bahnschrift", Font.BOLD, 16));
        choosePayMethodLabel.setForeground(new Color(69, 71, 75));
        choosePayMethodLabel.setBounds(85, 475, 600, 20);
        add(choosePayMethodLabel);

        JLabel chooseTimeLabel = new JLabel("<html><font color='#495E57FF'>■</font> " + " Choose the time that suits you:");
        chooseTimeLabel.setFont(new Font("Bahnschrift", Font.BOLD, 16));
        chooseTimeLabel.setForeground(new Color(69, 71, 75));
        chooseTimeLabel.setBounds(85, 505, 600, 20);
        add(chooseTimeLabel);

        reservedSeatsQueue = new LinkedList<>();
        JPanel buttonsPanel = new JPanel(new GridLayout(4, 10, 10, 10));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buttonsPanel.setBounds(75, 150, 600, 240);
        seatButtons = new JButton[5][11];
        for (int row = 1; row < 5; row++) {
            for (int col = 1; col < 11; col++) {
                JButton seatButton = new JButton();
                int finalRow = row;
                int finalCol = col;
                seatButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (seatButton.getBackground().equals(new Color(73, 94, 87))) {
                            if (reservedSeatsQueue.size() >= selectedSeatCount) {
                                Pair<Integer, Integer> oldSeat = reservedSeatsQueue.poll();
                                seatButtons[oldSeat.getFirst()][oldSeat.getSecond()].setBackground(new Color(73, 94, 87));
                            }
                            seatButton.setBackground(new Color(235, 197, 72));
                            reservedSeatsQueue.add(new Pair<>(finalRow, finalCol));
                            currentRow = finalRow;
                            currentCol = finalCol;
                        }
                    }
                });
                seatButtons[row][col] = seatButton;
                buttonsPanel.add(seatButton);
            }
        }
        add(buttonsPanel);

        JPanel hallPanel = new JPanel(new FlowLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                int arcDiameter = 10;
                RoundRectangle2D roundRect = new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), arcDiameter, arcDiameter);
                g2.fill(roundRect);
                g2.dispose();
            }
        };
        hallPanel.setBounds(225, 390, 300, 20);

        JLabel hallLabel = new JLabel();
        hallLabel.setFont(new Font("Calibri Light", Font.BOLD, 14));
        hallLabel.setForeground(Color.WHITE);
        hallPanel.add(hallLabel);

        add(hallPanel);

        paymentLabel = new JLabel(movie.getPrice() + " SP");
        paymentLabel.setFont(new Font("Agency FB", Font.BOLD, 28));
        paymentLabel.setBounds(930, 465, 150, 40);
        add(paymentLabel);

        JButton purchaseButton = new JButton("Confirm Purchase") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                int arcDiameter = 15;
                RoundRectangle2D roundRect = new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), arcDiameter, arcDiameter);
                if (getModel().isArmed()) {
                    g2.setColor(new Color(69, 71, 75));
                } else if (getModel().isRollover()) {
                    g2.setColor(new Color(235, 197, 72, 191));
                } else {
                    g2.setColor(new Color(235, 197, 72));
                }
                g2.fill(roundRect);
                g2.setColor(Color.DARK_GRAY);
                g2.setFont(new Font("Bahnschrift", Font.BOLD, 16));
                FontMetrics fm = g.getFontMetrics();
                String text = getText();
                int textWidth = fm.stringWidth(text);
                int textHeight = fm.getHeight();
                int x = (getWidth() - textWidth) / 2;
                int y = (getHeight() - textHeight) / 2 + fm.getAscent();
                g2.drawString(text, x - 15, y);
                g2.dispose();
            }
        };
        purchaseButton.setBorderPainted(false);
        purchaseButton.setFocusPainted(false);
        purchaseButton.setContentAreaFilled(false);
        purchaseButton.setBorder(null);
        purchaseButton.setBackground(new Color(235, 197, 72));
        purchaseButton.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, new Color(235, 197, 72)));
        purchaseButton.setBounds(700, 460, 200, 50);
        purchaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (reservedSeatsQueue.size() != selectedSeatCount) {
                    JOptionPane.showMessageDialog(null, "Please choose a seat!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                while (!reservedSeatsQueue.isEmpty()) {
                    Pair<Integer, Integer> seat = reservedSeatsQueue.poll();
                    Ticket ticket = new Ticket(movie.getId(), movie.getPrice(), movie.getShowtimes().get(timeOptions.getSelectedIndex()).getCinema_id(), seat, paymentOptions.getSelectedItem().toString(), movie.getShowtimes().get(timeOptions.getSelectedIndex()), timeOptions.getSelectedIndex());
                    movie.getShowtimes().get(timeOptions.getSelectedIndex()).getSeats()[seat.getFirst()][seat.getSecond()] = true;
                    Ticketing.bookTicket(user, ticket, timeOptions.getSelectedIndex());
                    updateSeatButtons(movie);
                }
                dispose();
                JOptionPane.showMessageDialog(SeatSelectionFrame.this, "Purchase confirmed!");
                SideBarFrame.ticketsPanel.updateTableData();
            }
        });
        add(purchaseButton);

        Timer seatUpdateTimer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateSeatButtons(movie);
                hallLabel.setText("Hall " + movie.getShowtimes().get(timeOptions.getSelectedIndex()).getCinema_id());
            }
        });
        seatUpdateTimer.start();

        setVisible(true);
    }

    private void updateSeatButtons(Movie movie) {
        movie = Movie.findMovie(movie);
        for (int row = 1; row < 5; row++) {
            for (int col = 1; col < 11; col++) {
                JButton seatButton = seatButtons[row][col];
                if (!movie.getShowtimes().get(timeOptions.getSelectedIndex()).getSeats()[row][col]) {
                    Color currentColor = seatButton.getBackground();
                    if (currentColor.equals(new Color(235, 197, 72))) {
                        // Do not update color if the button is yellow
                    } else {
                        seatButton.setBackground(new Color(73, 94, 87));
                    }
                } else {
                    seatButton.setBackground(new Color(134, 43, 13));
                    Iterator<Pair<Integer, Integer>> iterator = reservedSeatsQueue.iterator();
                    while (iterator.hasNext()) {
                        Pair<Integer, Integer> seat = iterator.next();
                        int reservedRow = seat.getFirst();
                        int reservedCol = seat.getSecond();
                        if (reservedRow == row && reservedCol == col) {
                            iterator.remove();
                            break;
                        }
                    }
                }
            }
        }
    }
    private int countGreenSeats(Movie movie) {
        int greenSeats = 0;
        for (int row = 1; row < 5; row++) {
            for (int col = 1; col < 11; col++) {
                if (!movie.getShowtimes().get(timeOptions.getSelectedIndex()).getSeats()[row][col]) {
                    greenSeats++;
                }
            }
        }
        return greenSeats;
    }

    private void updateComboBoxItems(JComboBox<Integer> comboBox) {
        comboBox.removeAllItems();
        for (int i = 1; i <= countGreenSeats(movie); i++) {
            comboBox.addItem(i);
        }
    }
}