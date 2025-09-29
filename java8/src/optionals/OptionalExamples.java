package optionals;

import util.PrinterUtil;

import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Created by WillianF.Bueno on 21/09/2025
 * Exemplos básicos e didáticos da API de Optional introduzida no Java 8.
 */
public class OptionalExamples {

    private static final String TITULO = "Java 8 Optional - Demonstrações";

    public static void main(String[] args) {
        PrinterUtil.run(TITULO, PrinterUtil.items(
                PrinterUtil.entry("of (valor não-nulo)", ofExample()),
                PrinterUtil.entry("ofNullable (não-nulo)", ofNullableExample("Batman")),
                PrinterUtil.entry("ofNullable (null)", ofNullableExample(null)),
                PrinterUtil.entry("orElse (não-nulo)", orElseExample("Superman")),
                PrinterUtil.entry("orElse (null)", orElseExample(null)),
                PrinterUtil.entry("orElseGet (não-nulo)", orElseGetExample("Flash")),
                PrinterUtil.entry("orElseGet (null)", orElseGetExample(null)),
                PrinterUtil.entry("isPresent / ifPresent", isPresentExample("Aquaman")),
                PrinterUtil.entry("orElseThrow (não-nulo)", orElseThrowExample("Coringa")),
                // cuidado: se passar null aqui, lança exceção de propósito
                PrinterUtil.entry("map (transformação)", mapExample("java")),
                PrinterUtil.entry("map (null)", mapExample(null)),
                PrinterUtil.entry("flatMap (encadeando Optionals)", flatMapExample("hello")),
                PrinterUtil.entry("flatMap (null)", flatMapExample(null)),
                PrinterUtil.entry("filter (condição satisfeita)", filterExample("Flash")),
                PrinterUtil.entry("filter (condição não satisfeita)", filterExample("Batman")),
                PrinterUtil.entry("get (valor presente)", getExample("Arlequina"))
                // se passar null no getExample, lança exceção
        ));
    }

    private static String ofExample() {
        Optional<String> opt = Optional.of("Valor Presente");
        return "of: " + opt.get();
    }

    private static String ofNullableExample(String valor) {
        Optional<String> opt = Optional.ofNullable(valor);
        return valor == null
                ? "ofNullable(null) -> Default aplicado"
                : "ofNullable(" + valor + ") -> " + opt.get();
    }

    private static String orElseExample(String valor) {
        String result = Optional.ofNullable(valor).orElse("Substituto Default");
        return valor == null
                ? "Entrada null, retornou: " + result
                : "Entrada '" + valor + "', manteve: " + result;
    }

    private static String orElseGetExample(String valor) {
        String result = Optional.ofNullable(valor).orElseGet(() -> "Gerado via Supplier");
        return valor == null
                ? "Entrada null, Supplier gerou: " + result
                : "Entrada '" + valor + "', manteve: " + result;
    }

    private static String isPresentExample(String nome) {
        Optional<String> opt = Optional.ofNullable(nome);

        StringBuilder sb = new StringBuilder();
        if (opt.isPresent()) {
            sb.append("isPresent=true -> ").append(opt.get());
        } else {
            sb.append("isPresent=false");
        }
        opt.ifPresent(v -> sb.append(" | ifPresent consumiu: ").append(v.toUpperCase()));

        return sb.toString();
    }

    private static String orElseThrowExample(String valor) {
        return Optional.ofNullable(valor)
                .orElseThrow(() -> new NoSuchElementException("Valor ausente!"));
    }

    private static String mapExample(String palavra) {
        return Optional.ofNullable(palavra)
                .map(String::toUpperCase)
                .orElse("Nada para mapear");
    }

    private static String flatMapExample(String valor) {
        return Optional.ofNullable(valor)
                .flatMap(v -> Optional.of("Encadeado: " + v))
                .orElse("Vazio");
    }

    private static String filterExample(String nome) {
        return Optional.ofNullable(nome)
                .filter(n -> n.startsWith("F"))
                .orElse("Não começa com F");
    }

    private static String getExample(String valor) {
        Optional<String> opt = Optional.ofNullable(valor);
        return "get() -> " + opt.get(); // geralmente utiliza isPresent ou ifPresent ou até mesmo orElse para não deixar lançar nullpointer.
    }
}
