package jogodaforca;

import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class JogoDaForca {
    private int tentativas = 6;
    private ArrayList<String> listaDePalavras = new ArrayList<>();
    private ArrayList<String> listaDeDicas = new ArrayList<>();
    private String[] letrasSorteadas;
    private String[] nomeDaspenalidades = new String[]{"Sem penalidades" , "perdeu uma perna","perdeu a outra perna", "perdeu um braço", "perdeu outro braço","perdeu o tronco", "perdeu a cabeça" };
    private int acertos;
    private int penalidade;
    private String dicas;
    private String[] letrasAdivinhadas;
    private String[] letrasErradas;


    public JogoDaForca() throws Exception{
        InputStream stream = this.getClass().getResourceAsStream("/palavras.txt");
        if (stream == null) {
            throw new Exception("Arquivo de palavras inexistente");
        } else {
            Scanner arquivo = new Scanner(stream);

            while(arquivo.hasNext()) {
                String linha = arquivo.nextLine();
                this.listaDePalavras.add(linha.split(";")[0]);
                this.listaDeDicas.add(linha.split(";")[1]);
            }

            arquivo.close();
        }
    }

    public void iniciar(){
        Random random = new Random();
        int indice = random.nextInt(this.listaDePalavras.size());
        this.letrasSorteadas = this.listaDePalavras.get(indice).split("");
        this.dicas = this.listaDeDicas.get(indice);
        this.letrasAdivinhadas = new String[this.letrasSorteadas.length];
        Arrays.fill(this.letrasAdivinhadas, "*");
        this.letrasErradas = new String[this.letrasSorteadas.length];
    }


    public String getDica(){
        return this.dicas;
    }

    public int getTamanho(){
        return letrasSorteadas.length;
    }

    public ArrayList<Integer> getOcorrencias(String letra) throws Exception{
        ArrayList<Integer> posicoes = new ArrayList<>();
        letra = letra.toUpperCase();
        if (letra.length() > 1){
            throw new Exception("Apenas 1 caracter por vez");
        }
        else if (letra.isEmpty()){
            throw new Exception("Letra Vazia: Digite um caracter");
        }
        
        else if (Arrays.asList(this.letrasAdivinhadas).contains(letra) || Arrays.asList(this.letrasErradas).contains(letra)) {        	throw new Exception("Letra já adivinhada");
        }
        	
        else {
            for (int i = 0 ; i < this.letrasSorteadas.length; i++){  
            	if(this.letrasSorteadas[i].equals(letra)) {
            		posicoes.add(i);
            		this.letrasAdivinhadas[i] = letra;
            	}
            	else {
            		this.letrasErradas[i] = letra;
            	}
          
            }

            if (posicoes.size()> 0) {
                this.acertos += posicoes.size();
            } 
            else {
                this.penalidade++;
            }

            return posicoes;
        }

    }

    public boolean terminou(){
        return  this.acertos == this.letrasSorteadas.length || this.penalidade == this.tentativas;
    }

    public String getPalavraAdivinhada(){
        return String.join("", this.letrasAdivinhadas);
    }

    public int getAcertos(){
        return this.acertos;
    }

    public int getNumeroPenalidade(){
        return this.penalidade;
    }

    public String getNomePenalidade(){
       return this.nomeDaspenalidades[this.penalidade];
    }

    public String getResultado(){
        if(this.acertos == this.letrasSorteadas.length){
            return "Você venceu";
        }
        else if (this.penalidade == this.tentativas) {
            return "Você foi enforcado";
        }
        else {
            return "Jogo em andamento";
        }

    }
}