package jogodaforca;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class TelaJogo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JogoDaForca jogo;
	private ImageIcon icon;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaJogo frame = new TelaJogo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaJogo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 590, 280);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Dica:");
		lblNewLabel_1.setBounds(123, 36, 306, 14);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(10, 12, 215, 13);
		contentPane.add(lblNewLabel);
	
		JLabel lblNewLabel_2 = new JLabel("Mensagem:");
		lblNewLabel_2.setBounds(10, 118, 306, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Letras:");
		lblNewLabel_3.setBounds(123, 61, 173, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Acertos:");
		lblNewLabel_4.setBounds(10, 61, 77, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Penalidade:");
		lblNewLabel_5.setBounds(327, 12, 218, 12);
		contentPane.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setBounds(356, 36, 160, 159);
		contentPane.add(lblNewLabel_6);
		
		JButton iniciar = new JButton("Iniciar");
		JButton analisar = new JButton("Analisar");
		
		iniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					jogo = new JogoDaForca();
					jogo.iniciar();
					lblNewLabel_2.setText("Mensagem:");
					lblNewLabel_3.setText("Palavra: " + jogo.getPalavraAdivinhada());
					ImageIcon icon = new ImageIcon(getClass().getResource("/imagensJOGO/" + jogo.getNumeroPenalidade() + ".png"));
					
					icon.setImage(icon.getImage().getScaledInstance(

					lblNewLabel_6.getWidth(),
					lblNewLabel_6.getHeight(),
					Image.SCALE_DEFAULT
					));
					
					lblNewLabel_6.setIcon(icon);
					analisar.setEnabled(true);
					}
				catch(Exception e1){
					lblNewLabel_2.setText("Mensagem: " + e1.getMessage());
				}
				lblNewLabel_1.setText("Dica: " + jogo.getDica());		
				lblNewLabel.setText("Tamanho: " + jogo.getTamanho() );
			}
		});
		iniciar.setBounds(162, 151, 89, 23);
		contentPane.add(iniciar);
		
		textField = new JTextField();
		textField.setBounds(123, 86, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    analisar.doClick();
				}
			}
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}


			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

		});
      
      
          
		analisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(jogo == null) {
						lblNewLabel_2.setText("Mensagem: Jogo não iniciado");
						return;
					}
					String letra = textField.getText();
				
					ArrayList<Integer> ocorrencias = jogo.getOcorrencias(letra);
				
					if(!ocorrencias.isEmpty()) {
						lblNewLabel_2.setText("Acertou: " + letra);
						lblNewLabel_4.setText("Acertos: " + jogo.getAcertos());
						lblNewLabel_3.setText("Palavra: " + jogo.getPalavraAdivinhada());
					}
					
					else {
						lblNewLabel_2.setText("Errou: " + letra);
						lblNewLabel_5.setText("Penalidade: " + jogo.getNomePenalidade());
					}
					
					if(jogo.terminou()) {
						lblNewLabel_2.setText("Mensagem: " + jogo.getResultado());
						analisar.setEnabled(false);
					}
				}
			
			
				catch(Exception e2) {
					lblNewLabel_2.setText("Mensagem: " + e2.getMessage());
				}
				finally {
					textField.setText("");
				}
				
				ImageIcon icon = new ImageIcon(getClass().getResource("/imagensJOGO/" + jogo.getNumeroPenalidade() + ".png"));
				
				icon.setImage(icon.getImage().getScaledInstance(

				lblNewLabel_6.getWidth(),
				lblNewLabel_6.getHeight(),
				Image.SCALE_DEFAULT
				));
				
				lblNewLabel_6.setIcon(icon);
			}
		});
		analisar.setBounds(233, 85, 89, 23);
		contentPane.add(analisar);
	
	
	
	}

}
