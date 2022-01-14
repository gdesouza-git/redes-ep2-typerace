package br.usp.each.typerace.server;

import java.util.*;

public class Typerace {
/*
    @param palavras: lista de todas palavras;
    @param palavrasJogo: lista das palavras selecionadas para o jogo instanciado;
    @param numeroPalavras: valor passado no parametro pontos pra vencer ao executar.
 */
    private List<String> palavras;
    static List<String> palavrasJogo = new ArrayList<>();
    int numeroPalavras;

    //Construtor:
    public Typerace(int numeroPalavras){
        this.numeroPalavras = numeroPalavras;
    }

    //Gerencia a lista de palavras
    public void lerPalavras() {

        String[] vetorPalavras = {"velar","bacelar","baker","baltasar","baltazar","belchior","berger","breyner","catar","cher","chevalier","christopher","connor","césar","didier","edgar","eisenhower","escobar","euler","fischer","fletcher","gaspar","geiger","gibraltar","gondomar","guadalquivir","gualtar","guiomar","guliver","hanôver","heidegger","heitor","hitler","hélder","ingmar","itamar","júpiter","keller","kepler","lavoisier","leonor","lumiar","luther","lúcifer","madagáscar","manchester","melchior","mianmar","miller","milner","montemor","montpellier","muller","nestor","níger","oder","oliver","omar","ovar","palmer","pasteur","penamacor","pilar","popper","potter","pulitzer","quibir","renoir","rockefeller","salazar","salvador","santander","schiller","schopenhauer","sever","sir","sotomayor","spencer","spenser","tanner","taylor","thor","timor","tudor","turner","tânger","ulster","valdemar","valter","victor","vilar","vladimir","vítor","wagner","walter","weber","weimar","westminster","windsor","xavier","zanzibar","abafador","abafar","abaixador","abaixar","abalançar","abalar","abalizador","abalizar","abalroar","abanador","abanar","abandalhar","abandonar","abarcador","abarcar","abarrotar","abastar","abastardar","abastecedor","abastecer","abastonar","abatatar","abatedor","abater","abaular","abdicador","abdicar","abdutor","abduzir","abeirar","abeiçar","abençoador","abençoar","aberrar","abesoirar","abesourar","abespinhar","abetumar","abismar","abjudicador","abjudicar","abjurador","abjurar","apropositar","apropriador","apropriar","aprosar","aprouver","aprovador","aprovar","aproveitador","aproveitar","aprovisionar","aproximar","aprumar","apunhalar","apupar","apurador","apurar","apuser","aquadrelar","aquadrilhar","encortinar","encostar","encovar","encravar","encrencar","encrespador","encrespar","encriptar","encrostar","encruar","encruzilhar","moedor","moer","mofar","moinar","molar","moldador","questionador","questionar","questor","sobrestimar","sobrevalorizar","sobrevir","sobreviver","sobrevoar","socar","sociabilizar","socializar","socorredor"};
        palavras = new ArrayList<>(Arrays.asList(vetorPalavras));

        Collections.shuffle(palavras);
    }

    //Gera a lista de palavras que será usada no jogo
    public List<String> gerarListaPalavras() {
        lerPalavras();
        for (int i = 0; i < numeroPalavras; i++)
            palavrasJogo.add(palavras.get(i));
        return palavrasJogo;
    }

    //Gera a classificação ordenando a lista de jogadores por meio do compareTo
    public static void classificacao() {
        Collections.sort(Utils.jogadores);
    }

}
