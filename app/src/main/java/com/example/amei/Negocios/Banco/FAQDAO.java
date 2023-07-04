package com.example.amei.Negocios.Banco;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.amei.Modelos.FAQ;
import com.example.amei.Negocios.StringExtensions;

import java.util.ArrayList;
import java.util.List;

public class FAQDAO{

    DataBase banco;

    public FAQDAO(Context context) {
        this.banco = new DataBase(context);
    }

    @SuppressLint("Range")
    public List<FAQ> getAll(){
        SQLiteDatabase dataBase = banco.getReadableDatabase();
        List<FAQ> faqs = new ArrayList<>();

        try{
            Cursor line = dataBase.query("faq", null, null, null, null, null, "id");

            if(line.moveToFirst()){
                do{
                    FAQ faq = new FAQ();
                    faq.setId(line.getInt(line.getColumnIndex("id")));
                    faq.setQuestion(StringExtensions.restaurarQuebrasDeLinha(line.getString(line.getColumnIndex("question"))));
                    faq.setAnswer(StringExtensions.restaurarQuebrasDeLinha(line.getString(line.getColumnIndex("answer"))));

                    faqs.add(faq);
                } while(line.moveToNext());
            }
        }catch(Exception e){
            Log.d("ERROR " + e.getClass(), e.getMessage());
        }finally {
            dataBase.close();
        }

        return faqs;
    }

    static public List<String> insertFAQs(){
        List<String> inserts = new ArrayList<>();

        FAQ faq = new FAQ();
        int id = 0;

        faq.setQuestion("Quais os beneficios de ser um MEI?");
        faq.setAnswer(
                "Se formalizar como MEI traz para o micro empreendedor as seguintes vantagens:\n" +
                        "1- Ter um CNPJ;\n" +
                        "2- Isenção de taxas para registro do MEI;\n" +
                        "3- Pagamento de tributos com valores fixos mensais (INSS, ICMS e/ou ISS);\n" +
                        "4- Inicio das atividades, sem prévio alvará ou licença;\n" +
                        "5- Emissão de notas fiscais;\n" +
                        "6- Maior poder de negociação com fornecedores, podendo obter descontos para pessoas jurídicas;\n" +
                        "7- Acesso mais fácil a serviços financeiros, como conta bancaria jurídica, maquina de cartão, acesso ao credito, entre outros;\n" +
                        "8-Vender e prestar serviços para outras empresas e até mesmo par ao governo."
        );
        id++;

        inserts.add("INSERT INTO faq VALUES ( " + id + ", '"  + faq.getQuestion() + "', '" + faq.getAnswer() + "' )");

        faq.setQuestion("O que é o DAS?");
        faq.setAnswer(
                "O DAS é o Documento de Arrecadação do Simples Nacional, ou seja, é como você, micro empreendedor individual vai recolher os impostos. Nele está incluso a contribuição previdenciário do empresario, como contribuinte individual, essa contribuição é o valor do salário mínimo do ano vigente, R$1,00 de ICMS, caso seja contribuinte desse imposto e R$5,00 de ISS, caso seja contribuinte desse imposto\n" +
                        "Para o MEI, os valores variam da seguinte forma\n" +
                        "R$ 66,10 para Comercio ou Industria;\n" +
                        "R$ 70,10 para Prestação de Serviços e\n" +
                        "R$ 71,10 para Comercio e Serviços juntos\n" +
                        "O pagamento pode ser feito por meio de debito automático, online ou emissão da DAS. Dei ser pago todo dia 20\n" +
                        "Para emitir o documento entre no link abaixo e clique no PGDAS no canto direito da tela\n" +
                        "\nhttps://www8.receita.fazenda.gov.br/SimplesNacional/\n" +
                        "\nCertifique-se de ter seu código de acesso e seu certificado digital em dia"
        );
        id++;

        inserts.add("INSERT INTO faq VALUES ( " + id + ", '"  + faq.getQuestion() + "', '" + faq.getAnswer() + "' )");

        faq.setQuestion("Tenho DAS em atraso. O que faço?");
        faq.setAnswer(
                "Relaxa, é possível parcelar estes valores!" +
                        "Sim!" +
                        "Você pode parcelar os débitos como MEI, desde que já tenha enviado a Declaração Anual de Faturamento (DASN) referente aos anos em atraso\n" +
                        "A solicitação pode ser feita pela internet, a qualquer momento, e você pode dividir o total dos débitos em até 60 vezes, desde que o valor gere pelo menos duas parcelas de, no mínimo, R$ 50,00\n" +
                        "Fique de olho!" +
                        "Para manter o parcelamento ativo é preciso manter as guias em dia e não deixar atrasar mais de 3 parcelas, sejam elas consecutivas ou não\n" +
                        "Agora clique no link abaixo e, no serviço Parcelamento - Microempreendedor Individual, clique na chave em “Código de Acesso” para solicitar seu parcelamento\n" +
                        "\n\nhttps://www.gov.br/empresas-e-negocios/pt-br/empreendedor/servicos-para-mei/pagamento-de-contribuicao-mensal/parcelamento-1"
        );
        id++;

        inserts.add("INSERT INTO faq VALUES ( " + id + ", '"  + faq.getQuestion() + "', '" + faq.getAnswer() + "' )");



        faq.setQuestion("Paguei valor a maior no DAS. O que fazer?");
        faq.setAnswer(
                "Fique tranquilo, é possível receber de volta o valor do INSS!" +
                        "Sim!" +
                        "Se você pagou o boleto duas vezes ou se pagou o boleto enquanto recebeu salário-maternidade, auxílio-doença ou auxílio-reclusão, você pode pedir o reembolso do que pagou a mais\n" +
                        "A restituição do valor pago como contribuição previdenciária (INSS) é solicitada pela internet e, caso seja liberada, será paga diretamente em sua conta bancária, se não houver débitos existentes\n" +
                        "\nPara solicitar o reembolso clique no botão ao lado e, no serviço Pedido Eletrônico de Restituição, clique na chave em “Código de Acesso”\n" +
                        "Já o reembolso dos valores de ICMS e/ou ISS deve ser pedido diretamente no seu estado e/ou município"
        );
        id++;

        inserts.add("INSERT INTO faq VALUES ( " + id + ", '"  + faq.getQuestion() + "', '" + faq.getAnswer() + "' )");




        faq.setQuestion("Benefícios do INSS com o pagamento do DAS");
        faq.setAnswer(
                "Para aproveitar os benefícios, é obrigatório pagar as guias mensais (DAS) até a data do vencimento e cumprir o número mínimo de contribuições(carência INSS)\n" +
                        "\nPara você\n" +
                        "Aposentadoria por idade\n" +
                        "Aposentadoria por invalidez\n" +
                        "Auxílio-doença\n" +
                        "Salário-maternidade\n" +
                        "Para a sua família\n" +
                        "Auxílio-reclusão\n" +
                        "Pensão por morte"
        );
        id++;

        inserts.add("INSERT INTO faq VALUES ( " + id + ", '"  + faq.getQuestion() + "', '" + faq.getAnswer() + "' )");




        faq.setQuestion("Quais os tipos de Nota Fiscal um MEI pode emitir?");
        faq.setAnswer(
                "Um dos grandes benefícios ao se formalizar como MEI é poder emitir nota fiscal (NF) e acessar novos mercados, vendendo para empresas e até mesmo órgãos públicos. Os tipos de nota fiscal variam de acordo com a ocupação que você exerce, entenda a diferença entre as notas fiscais: NF-e e  NFS-e\n" +
                        "A principal diferença está na função de cada modelo. A NF-e tem a função de registrar a venda de produtos, já a NFS-e tem a função de registrar a prestação de serviços. Vamos a um exemplo: uma loja de informática, quando são vendidas as peças, acessórios e computadores deve ser emitida uma NF-e. No caso de manutenção, consertos ou ajustes deve ser emitida uma NFS-e\n" +
                        "Portanto se sua empresa, vende produtos, deve emitir NF-e, se presta serviços deve emitir NFS-e, e faz os dois poderá emitir as duas"
        );
        id++;

        inserts.add("INSERT INTO faq VALUES ( " + id + ", '"  + faq.getQuestion() + "', '" + faq.getAnswer() + "' )");




        faq.setQuestion("É obrigatório preencher o Relatório Mensal de Receitas Brutas?");
        faq.setAnswer(
                "O Relatório Mensal de Receitas Brutas é uma obrigação prevista em lei, que você passa a ter após a formalização\n" +
                        "Apesar de não precisar ser entregue em nenhum órgão, ele deve ser preenchido até o dia 20 do mês seguinte às vendas ou prestações de serviços. Ele deve ser arquivado, junto com as Notas fiscais de compras e vendas, por um período mínimo de 5 anos\n" +
                        "Este relatório, além de te ajudar a controlar a média de faturamento mensal, também facilitará o envio da sua Declaração Anual de Faturamento (DASN), pois ela é o somatório de todos os relatórios preenchidos durante o ano\n" +
                        "Se a sua ocupação for de ‘Comércio’ preencha os campos indicados como revenda de mercadorias\n" +
                        "Caso a sua ocupação seja de ‘Indústria’ preencha os campos indicados como venda dos produtos industrializados\n" +
                        "Se a ocupação for de ‘Prestação de Serviços em geral ou Serviço de transporte municipal’ preencha os campos indicados como prestações de serviço\n" +
                        "Se a ocupação for de ‘Serviço de transporte intermunicipal ou interestadual’ escolha o campo de venda ou revenda de mercadorias\n" +
                        "Este relatório está presente na guia da Contabilidade do app"
        );
        id++;

        inserts.add("INSERT INTO faq VALUES ( " + id + ", '"  + faq.getQuestion() + "', '" + faq.getAnswer() + "' )");




        faq.setQuestion("MEI pode comprar sem Nota Fiscal?");
        faq.setAnswer(
                "Não, toda vez que você compra qualquer produto para a sua atividade como MEI é preciso solicitar a emissão da nota fiscal\n" +
                        "Toda empresa é obrigada e emitir nota fiscal. Se você comprou de um outro MEI, ele também é obrigado a emitir nota fiscal para você\n" +
                        "Lembra que o MEI é obrigado a emitir nota fiscal sempre que vende para outro CNPJ\n" +
                        "Guarde todas as notas fiscais dos produtos comprados ou serviços contratados e anexe junto ao relatório mensal \n" +
                        "Além de ser uma obrigação legal, guardar as notas fiscais de compra e venda também é uma forma de controle financeiro da sua empresa, fundamental para o sucesso do seu negócio!"
        );
        id++;

        inserts.add("INSERT INTO faq VALUES ( " + id + ", '"  + faq.getQuestion() + "', '" + faq.getAnswer() + "' )");




        faq.setQuestion("O que é DASN - SIMEI?");
        faq.setAnswer(
                "A DASN SIMEI é a Declaração Anual do Simples Nacional, exclusiva e obrigatória para o Microempreendedor Individual – MEI, na qual constam dados do faturamento da empresa. Para preencher a declaração, o MEI deve reunir todas as notas emitidas e comprovantes de pagamento do imposto mensal obrigatório\n" +
                        "O preenchimento dessa declaração pode ser realizado pelo site da Receita Federal ou pelo aplicativo MEI, disponível para Android ou IOS. Mesmo se não tiver rendimentos no ano, o MEI deve enviar a declaração com o valor zerado, para não constar pendência na Receita Federal\n" +
                        "Todos os anos, de 1º de janeiro até 31 de maio, o MEI deve entregar a declaração referente aos dados do ano anterior, no ano vigente. Ou seja, este ano ele terá que informar os dados dos 12 meses do ano passado, até a data referida\n" +
                        "Caso a DASN SIMEI seja entregue depois do dia 31 de maio, o MEI terá que pagar multas e juros para regularizar sua situação na Receita Federal. O não envio da declaração pode resultar em entraves de acesso a crédito e serviços financeiros exclusivos para o MEI"
        );
        id++;

        inserts.add("INSERT INTO faq VALUES ( " + id + ", '"  + faq.getQuestion() + "', '" + faq.getAnswer() + "' )");

        return inserts;
    }

}
