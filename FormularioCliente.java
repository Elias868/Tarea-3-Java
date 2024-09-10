import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormularioCliente extends JFrame {
    private JTextField codigoField, nombresField, apellidosField, direccionField, telefonoField;
    private JSpinner fechaNacimientoSpinner;
    private JComboBox<String> puestoComboBox;
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton agregarButton, actualizarButton, eliminarButton;
    private int selectedRow = -1;

    public FormularioCliente() {
        setTitle("Tarea 3 Java");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel formPanel = new JPanel();
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GroupLayout layout = new GroupLayout(formPanel);
        formPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        // Crear los componentes del formulario
        JLabel codigoLabel = new JLabel("Código:");
        codigoField = new JTextField(30);

        JLabel nombresLabel = new JLabel("Nombres:");
        nombresField = new JTextField(20);

        JLabel apellidosLabel = new JLabel("Apellidos:");
        apellidosField = new JTextField(20);

        JLabel direccionLabel = new JLabel("Dirección:");
        direccionField = new JTextField(20);

        JLabel telefonoLabel = new JLabel("Teléfono:");
        telefonoField = new JTextField(20);

        JLabel fechaNacimientoLabel = new JLabel("Fecha de Nacimiento:");
        fechaNacimientoSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(fechaNacimientoSpinner, "dd/MM/yyyy");
        fechaNacimientoSpinner.setEditor(dateEditor);

        JLabel puestoLabel = new JLabel("Puesto:");
        puestoComboBox = new JComboBox<>(new String[] {"Analista", "Programador"});

        agregarButton = new JButton("Agregar");
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarCliente();
            }
        });

        actualizarButton = new JButton("Actualizar");
        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedRow != -1) {
                    actualizarCliente();
                } else {
                    JOptionPane.showMessageDialog(FormularioCliente.this, "Seleccione un cliente para actualizar.");
                }
            }
        });

        eliminarButton = new JButton("Eliminar");
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedRow != -1) {
                    borrarCliente();
                } else {
                    JOptionPane.showMessageDialog(FormularioCliente.this, "Seleccione un cliente para eliminar.");
                }
            }
        });

        // Configurar el GroupLayout
        layout.setHorizontalGroup(
            layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(codigoLabel)
                    .addComponent(nombresLabel)
                    .addComponent(apellidosLabel)
                    .addComponent(direccionLabel)
                    .addComponent(telefonoLabel)
                    .addComponent(fechaNacimientoLabel)
                    .addComponent(puestoLabel))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(codigoField)
                    .addComponent(nombresField)
                    .addComponent(apellidosField)
                    .addComponent(direccionField)
                    .addComponent(telefonoField)
                    .addComponent(fechaNacimientoSpinner)
                    .addComponent(puestoComboBox)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(agregarButton)
                        .addComponent(actualizarButton)
                        .addComponent(eliminarButton)))
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(codigoLabel)
                    .addComponent(codigoField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(nombresLabel)
                    .addComponent(nombresField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(apellidosLabel)
                    .addComponent(apellidosField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(direccionLabel)
                    .addComponent(direccionField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(telefonoLabel)
                    .addComponent(telefonoField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(fechaNacimientoLabel)
                    .addComponent(fechaNacimientoSpinner))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(puestoLabel)
                    .addComponent(puestoComboBox))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(agregarButton)
                    .addComponent(actualizarButton)
                    .addComponent(eliminarButton))
        );

        // Crear tabla para mostrar los datos
        String[] columnNames = {"Código", "Nombres", "Apellidos", "Dirección", "Teléfono", "Fecha de Nacimiento", "Puesto"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(table);

        // Agregar paneles al frame
        add(formPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void agregarCliente() {
        String codigo = codigoField.getText();
        String nombres = nombresField.getText();
        String apellidos = apellidosField.getText();
        String direccion = direccionField.getText();
        String telefono = telefonoField.getText();
        String fechaNacimiento = ((JSpinner.DateEditor) fechaNacimientoSpinner.getEditor()).getFormat().format(fechaNacimientoSpinner.getValue());
        String puesto = (String) puestoComboBox.getSelectedItem();

        Object[] row = {codigo, nombres, apellidos, direccion, telefono, fechaNacimiento, puesto};
        tableModel.addRow(row);

        // Limpiar campos
        limpiarCampos();
    }

    private void actualizarCliente() {
        if (selectedRow != -1) {
            String codigo = codigoField.getText();
            String nombres = nombresField.getText();
            String apellidos = apellidosField.getText();
            String direccion = direccionField.getText();
            String telefono = telefonoField.getText();
            String fechaNacimiento = ((JSpinner.DateEditor) fechaNacimientoSpinner.getEditor()).getFormat().format(fechaNacimientoSpinner.getValue());
            String puesto = (String) puestoComboBox.getSelectedItem();

            tableModel.setValueAt(codigo, selectedRow, 0);
            tableModel.setValueAt(nombres, selectedRow, 1);
            tableModel.setValueAt(apellidos, selectedRow, 2);
            tableModel.setValueAt(direccion, selectedRow, 3);
            tableModel.setValueAt(telefono, selectedRow, 4);
            tableModel.setValueAt(fechaNacimiento, selectedRow, 5);
            tableModel.setValueAt(puesto, selectedRow, 6);

            // Limpiar campos
            limpiarCampos();
            selectedRow = -1;
        }
    }

    private void borrarCliente() {
        if (selectedRow != -1) {
            tableModel.removeRow(selectedRow);
            limpiarCampos();
            selectedRow = -1;
        }
    }

    private void limpiarCampos() {
        codigoField.setText("");
        nombresField.setText("");
        apellidosField.setText("");
        direccionField.setText("");
        telefonoField.setText("");
        puestoComboBox.setSelectedIndex(0);
        fechaNacimientoSpinner.setValue(new java.util.Date());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                FormularioCliente frame = new FormularioCliente();
                frame.setVisible(true);
                frame.table.getSelectionModel().addListSelectionListener(e -> {
                    if (!e.getValueIsAdjusting()) {
                        int selectedRow = frame.table.getSelectedRow();
                        if (selectedRow != -1) {
                            frame.selectedRow = selectedRow;
                            frame.codigoField.setText((String) frame.tableModel.getValueAt(selectedRow, 0));
                            frame.nombresField.setText((String) frame.tableModel.getValueAt(selectedRow, 1));
                            frame.apellidosField.setText((String) frame.tableModel.getValueAt(selectedRow, 2));
                            frame.direccionField.setText((String) frame.tableModel.getValueAt(selectedRow, 3));
                            frame.telefonoField.setText((String) frame.tableModel.getValueAt(selectedRow, 4));
                            frame.fechaNacimientoSpinner.setValue(frame.tableModel.getValueAt(selectedRow, 5));
                            frame.puestoComboBox.setSelectedItem(frame.tableModel.getValueAt(selectedRow, 6));
                        }
                    }
                });
            }
        });
    }
}
