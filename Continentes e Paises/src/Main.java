/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author José Pedro
 */

import java.util.ArrayList;
import java.util.List;

public class Main {
    
    public static void main(String[] args) {
       
        Pais brasil = new Pais("BRA", "Brasil", 8515767.049);
        brasil.setPopulacao(214000000);

        Pais argentina = new Pais("ARG", "Argentina", 2780400.0);
        argentina.setPopulacao(46000000);

        Pais uruguai = new Pais("URY", "Uruguai", 176215.0);
        uruguai.setPopulacao(3500000);

        Pais chile = new Pais("CHL", "Chile", 756102.0);
        chile.setPopulacao(19200000);

        Pais paraguai = new Pais("PRY", "Paraguai", 406752.0);
        paraguai.setPopulacao(7200000);

        Pais bolivia = new Pais("BOL", "Bolivia", 1098581.0);
        bolivia.setPopulacao(12000000);

        Pais peru = new Pais("PER", "Peru", 1285216.0);
        peru.setPopulacao(34000000);

        Pais equador = new Pais("ECU", "Equador", 256370.0);
        equador.setPopulacao(18500000);

        Pais colombia = new Pais("COL", "Colômbia", 1141748.0);
        colombia.setPopulacao(53000000);

        Pais venezuela = new Pais("VEN", "Venezuela", 916445.0);
        venezuela.setPopulacao(28000000);

        Pais guiana = new Pais("GUY", "Guiana", 214969.0);
        guiana.setPopulacao(800000);

        brasil.adicionarFronteira(argentina);
        brasil.adicionarFronteira(uruguai);
        brasil.adicionarFronteira(paraguai);
        brasil.adicionarFronteira(bolivia);
        brasil.adicionarFronteira(venezuela);
        brasil.adicionarFronteira(guiana);

        argentina.adicionarFronteira(chile);
        argentina.adicionarFronteira(paraguai);
        argentina.adicionarFronteira(bolivia);

        bolivia.adicionarFronteira(chile);
        bolivia.adicionarFronteira(peru);

        peru.adicionarFronteira(equador);
        peru.adicionarFronteira(colombia);

        venezuela.adicionarFronteira(colombia);
        venezuela.adicionarFronteira(guiana);
        
        Continente americaDoSul = new Continente("América do Sul");
        americaDoSul.adicionarPais(brasil);
        americaDoSul.adicionarPais(argentina);
        americaDoSul.adicionarPais(uruguai);
        americaDoSul.adicionarPais(chile);
        americaDoSul.adicionarPais(paraguai);
        americaDoSul.adicionarPais(bolivia);
        americaDoSul.adicionarPais(peru);
        americaDoSul.adicionarPais(equador);
        americaDoSul.adicionarPais(colombia);
        americaDoSul.adicionarPais(venezuela);
        americaDoSul.adicionarPais(guiana);

        System.out.println("Informacoes Geograficas e Territoriais:");
        System.out.println("---------------------------------------------------------");
        System.out.println("Densidade do Brasil: " + brasil.densidadePopulacional());
        System.out.println("Densidade da Argentina: " + argentina.densidadePopulacional());
        System.out.println("---------------------------------------------------------");

        System.out.println("Vizinhos comuns entre Brasil e Argentina:");
        for (Pais p : brasil.vizinhosComuns(argentina)) {
            System.out.println(p.getNome());
        }

        System.out.println("---------------------------------------------------------");
        System.out.println("Populacao total do continente: " + americaDoSul.populacaoTotal());
        System.out.println("---------------------------------------------------------");
        System.out.println("Densidade populacional do continente: " + americaDoSul.densidadePopulacional());
        System.out.println("---------------------------------------------------------");
        System.out.println("Pais com maior populacao: " + americaDoSul.paisComMaiorPopulacao().getNome());
        System.out.println("---------------------------------------------------------");
        System.out.println("Pais com menor populacao: " + americaDoSul.paisComMenorPopulacao().getNome());
        System.out.println("---------------------------------------------------------");
    }
}

class Pais {
    private String codigoISO;
    private String nome;
    private long populacao;
    private double dimensao;
    private List<Pais> fronteiras;

    // Construtor
    public Pais(String codigoISO, String nome, double dimensao) {
        this.codigoISO = codigoISO;
        this.nome = nome;
        this.dimensao = dimensao;
        this.fronteiras = new ArrayList<>();
    }

    // Getters e Setters
    public String getCodigoISO() {
        return codigoISO;
    }

    public void setCodigoISO(String codigoISO) {
        this.codigoISO = codigoISO;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getPopulacao() {
        return populacao;
    }

    public void setPopulacao(long populacao) {
        this.populacao = populacao;
    }

    public double getDimensao() {
        return dimensao;
    }

    public void setDimensao(double dimensao) {
        this.dimensao = dimensao;
    }

    public List<Pais> getFronteiras() {
        return fronteiras;
    }

    // Métodos
    public boolean isIgual(Pais outro) {
        return this.codigoISO.equals(outro.getCodigoISO());
    }

    public boolean isLimitrofe(Pais outro) {
        return this.fronteiras.contains(outro);
    }

    public double densidadePopulacional() {
        return this.populacao / this.dimensao;
    }

    public List<Pais> vizinhosComuns(Pais outro) {
        List<Pais> comuns = new ArrayList<>();
        for (Pais p : this.fronteiras) {
            if (outro.getFronteiras().contains(p)) {
                comuns.add(p);
            }
        }
        return comuns;
    }

    public void adicionarFronteira(Pais pais) {
        if (this.fronteiras.size() < 40 && !this.isIgual(pais) && !this.isLimitrofe(pais)) {
            this.fronteiras.add(pais);
            pais.getFronteiras().add(this); // Relação bidirecional
        }
    }
}

class Continente {
    private String nome;
    private List<Pais> paises;

    // Construtor
    public Continente(String nome) {
        this.nome = nome;
        this.paises = new ArrayList<>();
    }

    // Métodos
    public void adicionarPais(Pais pais) {
        this.paises.add(pais);
    }

    public double dimensaoTotal() {
        return this.paises.stream().mapToDouble(Pais::getDimensao).sum();
    }

    public long populacaoTotal() {
        return this.paises.stream().mapToLong(Pais::getPopulacao).sum();
    }

    public double densidadePopulacional() {
        double dimensaoTotal = this.dimensaoTotal();
        return dimensaoTotal == 0 ? 0 : this.populacaoTotal() / dimensaoTotal;
    }

    public Pais paisComMaiorPopulacao() {
        return this.paises.stream().max((p1, p2) -> Long.compare(p1.getPopulacao(), p2.getPopulacao())).orElse(null);
    }

    public Pais paisComMenorPopulacao() {
        return this.paises.stream().min((p1, p2) -> Long.compare(p1.getPopulacao(), p2.getPopulacao())).orElse(null);
    }

    public Pais paisComMaiorDimensao() {
        return this.paises.stream().max((p1, p2) -> Double.compare(p1.getDimensao(), p2.getDimensao())).orElse(null);
    }

    public Pais paisComMenorDimensao() {
        return this.paises.stream().min((p1, p2) -> Double.compare(p1.getDimensao(), p2.getDimensao())).orElse(null);
    }

    public double razaoTerritorial() {
        Pais maior = paisComMaiorDimensao();
        Pais menor = paisComMenorDimensao();
        return (maior != null && menor != null && menor.getDimensao() != 0) ?
                maior.getDimensao() / menor.getDimensao() : 0;
    }
}
