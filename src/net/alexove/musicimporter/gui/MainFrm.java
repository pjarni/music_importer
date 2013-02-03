package net.alexove.musicimporter.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.alexove.musicimporter.lib.MusicImporter;
import net.miginfocom.swing.MigLayout;

public class MainFrm extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JButton btnCerrar;
	private JLabel lblDirectorioDeOrigen;
	private JLabel lblDirectorioDeDestino;
	private JTextField txtDirectorioOrigen;
	private JTextField txtDirectorioDestino;
	private JButton btnExaminar;
	private JButton btnExaminar_1;
	private JPanel panel_3;
	private JPanel panel_4;
	
	private String dir_origen;
	private String dir_destino;
	private JButton btnImportar;
	
	public MainFrm() {
		initGUI();
		pack();
		setLocationRelativeTo(null);
	}
	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new MigLayout("", "[][grow]", "[][]"));
		
		lblDirectorioDeOrigen = new JLabel("Directorio de origen");
		panel.add(lblDirectorioDeOrigen, "cell 0 0,alignx trailing");
		
		txtDirectorioOrigen = new JTextField();
		txtDirectorioOrigen.setEditable(false);
		panel.add(txtDirectorioOrigen, "flowx,cell 1 0,growx");
		txtDirectorioOrigen.setColumns(10);
		
		lblDirectorioDeDestino = new JLabel("Directorio de destino");
		panel.add(lblDirectorioDeDestino, "cell 0 1,alignx trailing");
		
		txtDirectorioDestino = new JTextField();
		txtDirectorioDestino.setEditable(false);
		panel.add(txtDirectorioDestino, "flowx,cell 1 1,growx");
		txtDirectorioDestino.setColumns(10);
		
		btnExaminar = new JButton("Examinar");
		btnExaminar.addActionListener(this);
		panel.add(btnExaminar, "cell 1 0");
		
		btnExaminar_1 = new JButton("Examinar");
		btnExaminar_1.addActionListener(this);
		panel.add(btnExaminar_1, "cell 1 1");
		
		panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.SOUTH);
		
		btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(this);
		
		btnImportar = new JButton("Importar");
		btnImportar.addActionListener(this);
		panel_1.add(btnImportar);
		panel_1.add(btnCerrar);
		
		panel_2 = new JPanel();
		getContentPane().add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new GridLayout(0, 2, 0, 0));
		
		panel_3 = new JPanel();
		panel_2.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		panel_4 = new JPanel();
		panel_2.add(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnImportar) {
			do_btnImportar_actionPerformed(e);
		}
		if (e.getSource() == btnExaminar_1) {
			do_btnExaminar_1_actionPerformed(e);
		}
		if (e.getSource() == btnExaminar) {
			do_btnExaminar_actionPerformed(e);
		}
		if (e.getSource() == btnCerrar) {
			do_btnCerrar_actionPerformed(e);
		}
	}
	protected void do_btnCerrar_actionPerformed(ActionEvent e) {
		System.exit(0);
	}
	protected void do_btnExaminar_actionPerformed(ActionEvent e) {
		JFileChooser jfc=new JFileChooser(System.getProperty("user.home"));
		jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		jfc.showOpenDialog(this);
		dir_origen=jfc.getSelectedFile().getAbsolutePath();
		txtDirectorioOrigen.setText(dir_origen);
	}
	protected void do_btnExaminar_1_actionPerformed(ActionEvent e) {
		JFileChooser jfc=new JFileChooser(System.getProperty("user.home"));
		jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		jfc.showOpenDialog(this);		
		dir_destino=jfc.getSelectedFile().getAbsolutePath();
		txtDirectorioDestino.setText(dir_destino);
	}
	protected void do_btnImportar_actionPerformed(ActionEvent e) {
		MusicImporter musicImporter=new MusicImporter(dir_origen, dir_destino);
		musicImporter.importar();
		JOptionPane.showMessageDialog(this, "Archivos importados correctamente", "Confirmacion", JOptionPane.INFORMATION_MESSAGE);
	}
}
