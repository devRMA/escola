package pacote;

import java.util.ArrayList;

public class Aluno {
    private final int TAM = 4;
    private String nome;
    private String cpf;
    private String email;
    private int telefone;
    private Endereço endereco;
    private ArrayList<Disciplina> disciplinas;
    private ArrayList<Double[]> notas;
    private ArrayList<Integer> faltas;
    private ArrayList<Double> mediaFinal;
    private ArrayList<Double> percentualFaltas;
    private ArrayList<String> conceitos;

    public Aluno(String nome, String cpf, String email, int telefone, String rua, String bairro, String cidade, int numero) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.endereco = new Endereço(rua, bairro, cidade, numero);
        this.disciplinas = new ArrayList<Disciplina>();
        this.notas = new ArrayList<Double[]>();
        this.faltas = new ArrayList<Integer>();
        this.mediaFinal = new ArrayList<Double>();
        this.percentualFaltas = new ArrayList<Double>();
        this.conceitos = new ArrayList<String>();
    }

    private int pegarIndex(Disciplina disc) {
        return disciplinas.indexOf(disc);
    }

    //Método para adicionar diciplina
    public void adicionarDisciplina(Disciplina disciplina) {
        disciplinas.add(disciplina);
        notas.add(new Double[TAM]);
        faltas.add(0);
        mediaFinal.add(0.0);
        percentualFaltas.add(0.0);
        conceitos.add("");
    }

    //Métodos de falta
    public void adicionarUmaFalta(Disciplina disciplina) {
        faltas.set(pegarIndex(disciplina), (faltas.get(pegarIndex(disciplina)) + 1));
    }
    public void removerUmaFalta(Disciplina disciplina) {
        faltas.set(pegarIndex(disciplina), (faltas.get(pegarIndex(disciplina)) - 1));
    }
    public void alterarFaltas(Disciplina disciplina, int novaFalta) {
        faltas.set(pegarIndex(disciplina), novaFalta);
    }
    public int getFaltas(Disciplina disciplina) {
        return faltas.get(pegarIndex(disciplina));
    }
    public double getPercFaltas(Disciplina disciplina){
        //                                            Typecast
        percentualFaltas.set(pegarIndex(disciplina), ((double) getFaltas(disciplina) / (double) disciplina.getCargaHoraria()));
        return percentualFaltas.get(pegarIndex(disciplina));
    }
    //aqui, eu levei em consideração, 1 aula tendo 1 hora, logo 1 falta, diz que o aluno não participou de 1 hora de aula
    public boolean podeSerAprovado(Disciplina disciplina){
        if (getPercFaltas(disciplina) > 0.25){
            return false;
        }
        return true;
    }

    //Métodos de nota
    /**
     * @param disciplina é a disciplina, onde vai ser colocada a nota do aluno
     * @param nota é a nota em si, que o aluno tirou na prova
     * @param avaliacao espera qual avaliação, o aluno tirou essa nota
     * ex: obj.adicionarNotas(matematica, 10.0, 1);
     *         Vai adicionar a nota 10.0, na prova do 1° Bimestre/Trimestre, na matéria matematica
     *         Se der um obj.getNotas(matematica), vai retornar um vetor, e a nota 10.0 vai estar na posição 0
     */
    public void adicionarNota(Disciplina disciplina, double nota, int avaliacao){
        Double[] vet = notas.get(pegarIndex(disciplina));
        vet[avaliacao -1] = nota;
        notas.set(pegarIndex(disciplina), vet);
        vet = null;
    }
    public Double[] getTodasAsNotas(Disciplina disciplina){
        return notas.get(pegarIndex(disciplina));
    }
    public double getUmaNota(Disciplina disciplina, int avaliacao){
        try {
            return notas.get(pegarIndex(disciplina))[avaliacao - 1];
        } catch (Exception e) {
            return -1;
        }
    }
    public double getMediaFinal(Disciplina disciplina){
        //vai tentar pegar a nota da ultima avaliação, se retornar -1, significa que o aluno ainda não fez todas as provas
        if (getUmaNota(disciplina, TAM) != -1) {
            double media = 0.0;
            for (double a : getTodasAsNotas(disciplina)) {
                media += a;
            }
            media = media / TAM;
            mediaFinal.set(pegarIndex(disciplina), media);
            return media;
        }
        return -1;
    }
    private void calcularConceito(Disciplina disciplina){
        double mediaFinalDoAluno = getMediaFinal(disciplina);
        String conceito = "";
        if (mediaFinalDoAluno != -1 && podeSerAprovado(disciplina)){
            if (mediaFinalDoAluno >= 9.0){
                conceito = "A";
            }
            else if (mediaFinalDoAluno >= 7.0 && mediaFinalDoAluno < 9.0){
                conceito = "B";
            }
            else if (mediaFinalDoAluno >= 4.0 && mediaFinalDoAluno < 7.0){
                conceito = "C";
            }
            else {
                conceito = "D";
            }
        }
        conceitos.set(pegarIndex(disciplina), conceito);
    }
    public String getConceito(Disciplina disciplina){
        calcularConceito(disciplina);
        String conceito = conceitos.get(pegarIndex(disciplina));
        if(conceito != ""){
            return conceito;
        }
        else {
            return "-1";
        }
    }

    //Gets publicos
    public String getNome() {
        return nome;
    }
    public String getCpf() {
        return cpf;
    }
    public String getEmail() {
        return email;
    }
    public int getTelefone() {
        return telefone;
    }
    public Endereço getEndereco() {
        return endereco;
    }
    public ArrayList<Disciplina> getDisciplinas() {
        return disciplinas;
    }
    //Retorna o tamanho do vetor de notas, que também é a quantidade de notas que o aluno vai ter no ano
    protected int getTAM(){
        return this.TAM;
    }
}
