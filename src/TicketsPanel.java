import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class TicketsPanel extends JPanel {
    private User user;
    static JTable table;

    public TicketsPanel(User user) {
        this.user = user;

        setLayout(null);

        ImageIcon ticketPage = new ImageIcon("background\\TicketsPage.png");
        JLabel ticketImageLabel = new JLabel(ticketPage);
        ticketImageLabel.setBounds(380, 45, 700, 350);
        add(ticketImageLabel);

        JLabel myTicketsLabel = new JLabel("<html><font color='#EBC548FF'>|</font> " + " My Tickets:");
        myTicketsLabel.setFont(new Font("Arial", Font.BOLD, 35));
        myTicketsLabel.setForeground(new Color(69, 71, 75));
        myTicketsLabel.setBounds(50, 75, 400, 50);
        add(myTicketsLabel);

        JLabel title4 = new JLabel("Enter your ticket id to cancel it: ");
        title4.setFont(new Font("Agency FB", Font.BOLD, 24));
        title4.setBounds(75, 200, 900, 60);
        add(title4);

        JTextField idTextField = new JTextField();
        idTextField.setBounds(75, 250, 240, 25);
        add(idTextField);

        JButton cancelTicketButton = new JButton("Cancel Ticket") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                int arcDiameter = 10;
                RoundRectangle2D roundRect = new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), arcDiameter, arcDiameter);

                if (getModel().isArmed()) {
                    g2.setColor(new Color(235, 197, 72)); // Color when pressed
                } else if (getModel().isRollover()) {
                    g2.setColor(new Color(235, 197, 72, 204)); // Color when hovered
                } else {
                    g2.setColor(new Color(235, 197, 72)); // Default color
                }

                g2.fill(roundRect);

                g2.setColor(Color.DARK_GRAY);
                g2.setFont(new Font("Agency FB", Font.BOLD, 22));

                FontMetrics fm = g.getFontMetrics();
                String text = getText();
                int textWidth = fm.stringWidth(text);
                int textHeight = fm.getHeight();

                int x = (getWidth() - textWidth) / 2;
                int y = (getHeight() - textHeight) / 2 + fm.getAscent();

                g2.drawString(text, x - 6, y + 2);

                g2.dispose();
            }
        };
        cancelTicketButton.setBorderPainted(false);
        cancelTicketButton.setFocusPainted(false);
        cancelTicketButton.setContentAreaFilled(false);
        cancelTicketButton.setBorder(null);
        cancelTicketButton.setBounds(325, 250, 150, 25);
        cancelTicketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idText = idTextField.getText();
                if (!idText.isEmpty()) {
                    try {
                        int ticketId = Integer.parseInt(idText);
                        if(!Ticketing.cancelTicket(user, user.getTicketById(ticketId))){
                            JOptionPane.showMessageDialog(null, "there is no ticket with this ID", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        // Reload data in the background
                        new DataReloadWorker().execute();
                        idTextField.setText("");
                    } catch (NumberFormatException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "The text box is empty", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        add(cancelTicketButton);

        // Create the custom table model and set it to the JTable
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(new String[]{"Ticket ID", "Movie name", "Hall", "Time", "Date", "Seat", "Payment way", "Price", "Cancel"});

        table = new JTable(tableModel);
        // Set renderers and editors for the "Cancel" column
        table.getColumn("Cancel").setCellRenderer(new JButtonRenderer());
        table.getColumn("Cancel").setCellEditor(new JButtonEditor(new JCheckBox()));
        // Modify table properties
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
        table.getTableHeader().setBackground(new Color(73, 94, 87));
        table.getTableHeader().setForeground(Color.WHITE);
        table.setFont(new Font("SansSerif", Font.PLAIN, 14));
        table.setRowHeight(40);
        table.setIntercellSpacing(new Dimension(15, 20));
        table.setDefaultRenderer(Object.class, new CustomTableCellRenderer());

        // Set colors
        table.setGridColor(Color.WHITE);
        table.setBackground(new Color(240, 240, 240));

        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 400, 1050, 300);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        add(scrollPane);

        // Initial data load in the background
        new DataReloadWorker().execute();
    }
    // Renderer for customizing the appearance of cells in the JTable
    private class CustomTableCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component rendererComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            // Set odd and even row colors
            Color oddRowColor = new Color(184, 206, 200);
            Color evenRowColor = new Color(200, 200, 200);
            if (row % 2 == 0) {
                rendererComponent.setBackground(evenRowColor);
            } else {
                rendererComponent.setBackground(oddRowColor);
            }
            return rendererComponent;
        }
    }

    // Renderer for rendering a JButton in a JTable cell
    private class JButtonRenderer extends JButton implements TableCellRenderer {
        private JButton button;

        public JButtonRenderer() {
            setOpaque(true);
            button = new JButton("Cancel");
            setBackground(new Color(244, 206, 20)); // Set background color
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText((value == null) ? "" : value.toString());
            return this;
        }
    }

    // Editor for rendering and handling events for a JButton in a JTable cell
    private class JButtonEditor extends DefaultCellEditor {
        private JButton button;

        public JButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton("Cancel");
            button.setBackground(new Color(244, 206, 20)); // Set background color
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        cancelSelectedTicket(selectedRow);
                    }
                    fireEditingStopped();
                }
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            return "Cancel";
        }
    }

    // Method for canceling the selected ticket in the JTable
    private void cancelSelectedTicket(int rowIndex) {
        Ticketing.cancelTicket(user, user.getTickets().get(rowIndex));
        new DataReloadWorker().execute();
    }

    // SwingWorker for reloading data in the background
    public class DataReloadWorker extends SwingWorker<Void, Void> {
        @Override
        protected Void doInBackground() throws Exception {
            updateTableData();
            return null;
        }
    }

    // Method for updating the JTable data based on the user's tickets
    public void updateTableData() {
        // Assuming that the table model is of type DefaultTableModel
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Clear existing data

        // Reload data from the user's tickets
        for (Ticket ticket : user.getTickets()) {
            Map<Integer, Movie> movieMap = Movie.readFile();
            Movie movie = movieMap.get(ticket.getMovie_id());
            model.addRow(new Object[]{
                    ticket.getId(),
                    movie.getName(),
                    ticket.getCinema_id(),
                    ticket.getShowtime().getInterval().time,
                    ticket.getShowtime().getDate(),
                    "Row: " + ticket.getSeat().getFirst() + ", Col: " + ticket.getSeat().getSecond(),
                    ticket.getPayment(),
                    ticket.getPrice(),
                    "Cancel"
            });
        }
    }
}

