package pacote;


public class Main {
    private static void mostrarTodasAsNotasCadastradas(Aluno aluno, Disciplina disciplina){
        for (int i = 0; i < aluno.getTAM(); i++){
            if (aluno.getTodasAsNotas(disciplina)[i] != null){
                System.out.println("O(a) aluno(a) " + aluno.getNome() + " tirou " + aluno.getTodasAsNotas(disciplina)[i] +
                                   " na " + (i + 1) + "° prova de " + disciplina.getNome());
            }
            else{
                break;
            }
        }
    }
    //Esses métodos foram feitos antes de criar um atributo no aluno, para guardar esses valores
    private static void mostrarResultadoFinalDoAluno(Aluno aluno, Disciplina disciplina){
        double mediaFinalDoAluno = aluno.getMediaFinal(disciplina);
        if (mediaFinalDoAluno != -1 && aluno.podeSerAprovado(disciplina)){
            System.out.print("O(a) aluno(a) " + aluno.getNome() + " ficou com uma média final de " + mediaFinalDoAluno + " pontos, logo ele(a) está ");
            if (mediaFinalDoAluno >= 7.0){
                System.out.println("aprovado(a)!");
            }
            else if (mediaFinalDoAluno < 4.0){
                System.out.println("reprovado(a)!");
            }
            else{
                System.out.println("de recuperação!");
            }
        }
        else if(mediaFinalDoAluno == -1){
            System.out.println("O(a) aluno(a) " + aluno.getNome() + " ainda não realizou todas as avaliações.");
        }
        else {
            System.out.println("O(a) aluno(a) " + aluno.getNome() + " reprovou por faltas!");
        }
    }
    public static void mostrarConceitoDoAluno(Aluno aluno, Disciplina disciplina){
        double mediaFinalDoAluno = aluno.getMediaFinal(disciplina);
        if (mediaFinalDoAluno != -1 && aluno.podeSerAprovado(disciplina)){
            System.out.print("O(a) aluno(a) " + aluno.getNome() + " ficou com um conceito de ");
            if (mediaFinalDoAluno >= 9.0){
                System.out.println("A");
            }
            else if (mediaFinalDoAluno >= 7.0 && mediaFinalDoAluno < 9.0){
                System.out.println("B");
            }
            else if (mediaFinalDoAluno >= 4.0 && mediaFinalDoAluno < 7.0){
                System.out.println("C");
            }
            else {
                System.out.println("D");
            }
        }
        else if(mediaFinalDoAluno == -1){
            System.out.println("O(a) aluno(a) " + aluno.getNome() + " ainda não realizou todas as avaliações.");
        }
        else {
            System.out.println("O(a) aluno(a) " + aluno.getNome() + " reprovou por faltas!");
        }
    }
    public static void main(String[] args) {
        Professor elson = new Professor("Elson");
        Professor anderson = new Professor("Anderson");


        Disciplina matematica = new Disciplina("Matemática", 500, elson);
        Disciplina programacaoDeAplicativo = new Disciplina("Programação de Aplicativo", 120, anderson);

        System.out.println("A disciplina " + matematica.getNome() + " tem uma carga horaria de " + matematica.getCargaHoraria() + " horas.");

        Aluno rafael = new Aluno("Rafael", "12345678995", "email@email.org.br", 999999999, "Rua imaginaria", "Bairro dos sonhos", "Cidade Maravilha", 50);

        rafael.adicionarDisciplina(matematica);
        rafael.adicionarDisciplina(programacaoDeAplicativo);


        rafael.adicionarUmaFalta(matematica);
        rafael.adicionarUmaFalta(matematica);

        rafael.adicionarUmaFalta(programacaoDeAplicativo);

        System.out.println("O " +  rafael.getNome() + " tem " + rafael.getFaltas(matematica) + " faltas em " + matematica.getNome());
        System.out.println("O " +  rafael.getNome() + " tem " + rafael.getFaltas(programacaoDeAplicativo) + " faltas em " + programacaoDeAplicativo.getNome());

        rafael.alterarFaltas(matematica, 20);
        System.out.println("O " +  rafael.getNome() + " agora tem " + rafael.getFaltas(matematica) + " faltas em matemática");
        System.out.println();
        rafael.removerUmaFalta(matematica);
        System.out.println("O " +  rafael.getNome() + " agora tem " + rafael.getFaltas(matematica) + " faltas em matemática");

        System.out.println(rafael.getEndereco());
        System.out.println();

        rafael.adicionarNota(matematica, 7.0, 1);
        mostrarTodasAsNotasCadastradas(rafael, matematica);
        System.out.println();
        rafael.adicionarNota(matematica, 7.0, 2);
        rafael.adicionarNota(matematica, 7.0, 3);
        rafael.adicionarNota(matematica, 8.0, 4);
        mostrarTodasAsNotasCadastradas(rafael, matematica);
        System.out.println();

        mostrarResultadoFinalDoAluno(rafael, matematica);
        rafael.alterarFaltas(matematica, 126);
        mostrarResultadoFinalDoAluno(rafael, matematica);
        rafael.removerUmaFalta(matematica);
        rafael.adicionarNota(matematica, 6, 4);
        mostrarResultadoFinalDoAluno(rafael, matematica);
        rafael.adicionarNota(matematica, 10, 4);
        mostrarResultadoFinalDoAluno(rafael, matematica);
        mostrarConceitoDoAluno(rafael, matematica);
        System.out.println();
        System.out.println("O aluno " + rafael.getNome() + " ficou com " + (rafael.getPercFaltas(matematica) * 100) + "% de faltas em " + matematica.getNome());
        System.out.println("O aluno " + rafael.getNome() + " ficou com o conceito " + rafael.getConceito(matematica) + " em " + matematica.getNome());
        System.out.println("O aluno " + rafael.getNome() + " ficou com " + rafael.getMediaFinal(matematica) + " de média final em " + matematica.getNome());
    }

}
