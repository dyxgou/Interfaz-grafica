package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;

public class TiendaDeLibrosLogin extends JFrame {
    private JTextField nombreField;
    private JPasswordField contrasenaField;
    private JButton entrarButton;

    public TiendaDeLibrosLogin() {
        // Set up the frame
        setTitle("Tienda de libros");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel with a grid layout
        JPanel mainPanel = new JPanel(new GridLayout(6, 1, 0, 0));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Header panel
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBorder(new MatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY));
        JLabel titleLabel = new JLabel("Tienda de libros", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        headerPanel.add(titleLabel, BorderLayout.CENTER);

        // Session panel
        JPanel sessionPanel = new JPanel(new BorderLayout());
        sessionPanel.setBorder(new MatteBorder(0, 1, 1, 1, Color.LIGHT_GRAY));
        JLabel sessionLabel = new JLabel("Inicio de sesión", SwingConstants.CENTER);
        sessionPanel.add(sessionLabel, BorderLayout.CENTER);

        // Username panel
        JPanel nombrePanel = new JPanel(new BorderLayout());
        nombrePanel.setBorder(new MatteBorder(0, 1, 1, 1, Color.LIGHT_GRAY));
        JLabel nombreLabel = new JLabel("Nombre");
        nombreLabel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
        nombreField = new JTextField();
        nombreField.setBackground(Color.LIGHT_GRAY);
        nombrePanel.add(nombreLabel, BorderLayout.NORTH);
        nombrePanel.add(nombreField, BorderLayout.CENTER);

        // Password panel
        JPanel contrasenaPanel = new JPanel(new BorderLayout());
        contrasenaPanel.setBorder(new MatteBorder(0, 1, 1, 1, Color.LIGHT_GRAY));
        JLabel contrasenaLabel = new JLabel("Contraseña");
        contrasenaLabel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
        contrasenaField = new JPasswordField();
        contrasenaField.setBackground(Color.LIGHT_GRAY);
        contrasenaPanel.add(contrasenaLabel, BorderLayout.NORTH);
        contrasenaPanel.add(contrasenaField, BorderLayout.CENTER);

        // Button panel
        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.setBorder(new MatteBorder(0, 1, 1, 1, Color.LIGHT_GRAY));
        entrarButton = new JButton("Entrar");
        entrarButton.setBackground(Color.DARK_GRAY);
        entrarButton.setForeground(Color.BLACK);
        buttonPanel.add(entrarButton, BorderLayout.CENTER);

        // Add action listener to the button
        entrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = nombreField.getText();
                String contrasena = new String(contrasenaField.getPassword());

                // Check if both fields are filled
                if (!nombre.isEmpty() && !contrasena.isEmpty()) {
                    // Hide login form
                    TiendaDeLibrosLogin.this.setVisible(false);

                    // Show the book store UI
                    SwingUtilities.invokeLater(() -> {
                        TiendaLibros bookstore = new TiendaLibros();
                        bookstore.setVisible(true);
                    });
                } else {
                    JOptionPane.showMessageDialog(TiendaDeLibrosLogin.this,
                                                 "Por favor, complete todos los campos",
                                                 "Error de inicio de sesión",
                                                 JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Empty panel for spacing at the bottom
        JPanel emptyPanel = new JPanel();
        emptyPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Add all panels to the main panel
        mainPanel.add(headerPanel);
        mainPanel.add(sessionPanel);
        mainPanel.add(nombrePanel);
        mainPanel.add(contrasenaPanel);
        mainPanel.add(buttonPanel);
        mainPanel.add(emptyPanel);

        // Add main panel to the frame
        add(mainPanel);
    }

    public static void main(String[] args) {
        // Set look and feel to system default
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create and show the application
        SwingUtilities.invokeLater(() -> {
            TiendaDeLibrosLogin app = new TiendaDeLibrosLogin();
            app.setVisible(true);
        });
    }
}

class TiendaLibros extends JFrame {
    private JList<String> librosList;
    private DefaultListModel<String> librosModel;
    private JTextArea infoLibroTextArea;
    private JButton perfilButton, comprarButton;
    private JButton anadirButton, eliminarButton, actualizarButton;

    public TiendaLibros() {
        // Set up the frame
        setTitle("Tienda de Libros");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create main panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Create header panel
        JPanel headerPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("Tienda de Libros", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        headerPanel.add(titleLabel, BorderLayout.CENTER);

        // Create profile button
        perfilButton = new JButton("Perfil");
        perfilButton.setBackground(Color.WHITE);
        JPanel perfilPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        perfilPanel.add(perfilButton);
        headerPanel.add(perfilPanel, BorderLayout.EAST);

        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // Create content panel
        JPanel contentPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Left panel - Book list
        JPanel leftPanel = new JPanel(new BorderLayout(0, 10));
        JLabel listadoLabel = new JLabel("Listado de Libros", SwingConstants.CENTER);
        leftPanel.add(listadoLabel, BorderLayout.NORTH);

        // Create JList for books
        librosModel = new DefaultListModel<>();
        librosModel.addElement("Libro 1");
        librosModel.addElement("Libro 2");
        librosModel.addElement("Libro 3");
        librosModel.addElement("Libro 4");
        librosModel.addElement("Libro 5");
        librosModel.addElement("Libro 6");

        librosList = new JList<>(librosModel);
        librosList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        librosList.setBackground(Color.LIGHT_GRAY);

        // Custom cell renderer for rounded corners
        librosList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                         boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                label.setBackground(Color.WHITE);
                label.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
                return label;
            }
        });

        JScrollPane listScrollPane = new JScrollPane(librosList);
        listScrollPane.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        listScrollPane.setBackground(Color.LIGHT_GRAY);
        leftPanel.add(listScrollPane, BorderLayout.CENTER);

        contentPanel.add(leftPanel);

        // Right panel - Book information
        JPanel rightPanel = new JPanel(new BorderLayout(0, 10));
        JLabel infoLabel = new JLabel("Información del Libro", SwingConstants.CENTER);
        rightPanel.add(infoLabel, BorderLayout.NORTH);

        // Create text area for book information
        infoLibroTextArea = new JTextArea();
        infoLibroTextArea.setText("Libro X:\nISBN\nTítulo\nAutor(es)\nGénero\nEditorial\nN° Páginas\nPrecio de venta\nCantidad Disponible\nFormato");
        infoLibroTextArea.setEditable(false);
        infoLibroTextArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        infoLibroTextArea.setBackground(Color.WHITE);

        JScrollPane infoScrollPane = new JScrollPane(infoLibroTextArea);
        infoScrollPane.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        rightPanel.add(infoScrollPane, BorderLayout.CENTER);

        // Add buy button
        comprarButton = new JButton("Comprar");
        comprarButton.setBackground(Color.WHITE);
        JPanel comprarPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        comprarPanel.add(comprarButton);
        rightPanel.add(comprarPanel, BorderLayout.SOUTH);

        contentPanel.add(rightPanel);

        mainPanel.add(contentPanel, BorderLayout.CENTER);

        // Bottom panel - Action buttons
        JPanel bottomPanel = new JPanel(new GridLayout(1, 3, 10, 0));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

        anadirButton = new JButton("Añadir");
        anadirButton.setBackground(Color.LIGHT_GRAY);

        eliminarButton = new JButton("Eliminar");
        eliminarButton.setBackground(Color.LIGHT_GRAY);

        actualizarButton = new JButton("Actualizar");
        actualizarButton.setBackground(Color.LIGHT_GRAY);

        bottomPanel.add(anadirButton);
        bottomPanel.add(eliminarButton);
        bottomPanel.add(actualizarButton);

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        // Add listener for list selection
        librosList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedIndex = librosList.getSelectedIndex();
                if (selectedIndex != -1) {
                    String selectedBook = librosModel.getElementAt(selectedIndex);
                    updateBookInfo(selectedBook);
                }
            }
        });

        // Add to frame
        add(mainPanel);
    }

    private void updateBookInfo(String bookTitle) {
        // In a real application, this would fetch book details from a database
        infoLibroTextArea.setText(bookTitle + ":\nISBN: 978-3-16-148410-0\nTítulo: " + bookTitle +
                               "\nAutor(es): Autor Ejemplo\nGénero: Ficción\nEditorial: Editorial Ejemplo" +
                               "\nN° Páginas: 250\nPrecio de venta: €19.99\nCantidad Disponible: 10\nFormato: Tapa blanda");
    }
}
