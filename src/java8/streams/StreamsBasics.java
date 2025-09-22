package java8.streams;

import util.PrinterUtil;

import java.util.*;
import java.util.function.Function;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by WillianF.Bueno on 21/09/2025
 * Exemplos básicos e didáticos da API de Streams introduzida no Java 8.
 */
public class StreamsBasics {

    private static final List<Integer> NUMEROS = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    public static final String TITULO = "Java 8 Streams - Demonstrações";

    public static void main(String[] args) {
        List<String> nomes = Personagem.toList();

        PrinterUtil.run(TITULO, PrinterUtil.items(
                PrinterUtil.entry("filter (pares)", filter(NUMEROS)),
                PrinterUtil.entry("map (upper case)", mapToUpper(nomes)),
                PrinterUtil.entry("sorted (natural)", sorted(nomes)),
                PrinterUtil.entry("limit (3)", limit(nomes)),
                PrinterUtil.entry("count (começam com 'A')", countComecaComA(nomes)),
                PrinterUtil.entry("distinct", distinct(Arrays.asList("A", "B", "A", "C", "B", "D"))),
                PrinterUtil.entry("skip (pular 2)", skip(nomes, 2)),
                PrinterUtil.entry("peek (debug do pipeline)", peekDebug(nomes)),
                PrinterUtil.entry("flatMap (split por espaço)", flatMapTokens(nomes)),
                PrinterUtil.entry("reduce (soma)", reduceSoma(NUMEROS)),
                PrinterUtil.entry("min", min(NUMEROS)),
                PrinterUtil.entry("max", max(NUMEROS)),
                PrinterUtil.entry("anyMatch (nome contém 'man')", anyMatchContemMan(nomes)),
                PrinterUtil.entry("allMatch (todos len >= 3)", allMatchLenMin3(nomes)),
                PrinterUtil.entry("noneMatch (começam com 'Z')", noneMatchZ(nomes)),
                PrinterUtil.entry("findFirst (começa com 'S')", findFirstComecaComS(nomes)),
                PrinterUtil.entry("findAny (paralelo, contém espaço)", findAnyContemEspaco(nomes)),
                PrinterUtil.entry("collect toSet (iniciais)", collectToSetIniciais(nomes)),
                PrinterUtil.entry("toMap (nome -> tamanho)", toMapNomeTamanho(nomes)),
                PrinterUtil.entry("joining (csv)", joiningCSV(nomes)),
                PrinterUtil.entry("groupingBy (primeira letra)", groupingByPrimeiraLetra(nomes)),
                PrinterUtil.entry("partitioningBy (len > 6)", partitioningByLenMaiorQue6(nomes)),
                PrinterUtil.entry("summarizingInt (estatísticas dos números)", summarizingInt(NUMEROS))
        ));
    }

    /**
     * Demonstra o uso de {@link java.util.function.Predicate} em conjunto com a API de {@link java.util.stream.Stream}.
     * <p>
     * O metodo aplica uma condição lógica (definida pelo {@code Predicate}) sobre cada elemento da lista,
     * retornando apenas aqueles que satisfazem o critério estabelecido.
     *
     * @param numeros lista de inteiros de entrada
     * @return nova lista contendo apenas os elementos que atenderam ao filtro (pares)
     */
    private static List<Integer> filter(List<Integer> numeros) {
        return numeros.stream().filter(n -> n % 2 == 0).collect(Collectors.toList());
    }

    /**
     * Demonstra {@link Stream#map(Function)} transformando cada nome deixando eles em uppercase.
     */
    private static List<String> mapToUpper(List<String> nomes) {
        return nomes.stream().map(String::toUpperCase).collect(Collectors.toList());
    }

    /**
     * Demonstra {@link Stream#sorted()} para ordenação natural.
     */
    private static List<String> sorted(List<String> nomes) {
        return nomes.stream().sorted().collect(Collectors.toList());
    }

    /**
     * Demonstra {@link Stream#limit(long)} limitando o tamanho do fluxo.
     */
    private static List<String> limit(List<String> nomes) {
        return nomes.stream().limit(3).collect(Collectors.toList());
    }

    /**
     * Demonstra {@link Stream#count()} após um filtro.
     */
    private static long countComecaComA(List<String> nomes) {
        return nomes.stream().filter(n -> n.startsWith("A")).count();
    }

    /**
     * Demonstra {@link Stream#distinct()} removendo duplicatas.
     */
    private static List<String> distinct(List<String> valores) {
        return valores.stream().distinct().collect(Collectors.toList());
    }

    /**
     * Demonstra {@link Stream#skip(long)} pulando os primeiros N elementos.
     */
    private static List<String> skip(List<String> nomes, long n) {
        return nomes.stream().skip(n).collect(Collectors.toList());
    }

    /**
     * Demonstra {@link Stream#peek(java.util.function.Consumer)} para depuração do pipeline.
     * (Evite efeitos colaterais reais; aqui apenas ilustra o ponto.)
     */
    private static List<String> peekDebug(List<String> nomes) {
        return nomes.stream()
                .peek(s -> { /* debug: poderia logar aqui se necessário */ })
                .map(String::trim)
                .collect(Collectors.toList());
    }

    /**
     * Demonstra {@link Stream#flatMap(Function)} "achatando" coleções:
     * aqui, separa nomes por espaço e retorna todos os tokens em uma única lista.
     */
    private static List<String> flatMapTokens(List<String> nomes) {
        return nomes.stream()
                .map(s -> s.split("\\s+"))
                .flatMap(Arrays::stream)
                .collect(Collectors.toList());
    }

    /**
     * Demonstra {@link Stream#reduce(Object, java.util.function.BinaryOperator)} somando inteiros.
     */
    private static int reduceSoma(List<Integer> numeros) {
        return numeros.stream().reduce(0, Integer::sum);
    }

    /**
     * Demonstra {@link Stream#min(Comparator)}.
     */
    private static Optional<Integer> min(List<Integer> numeros) {
        return numeros.stream().min(Integer::compareTo);
    }

    /**
     * Demonstra {@link Stream#max(Comparator)}.
     */
    private static Optional<Integer> max(List<Integer> numeros) {
        return numeros.stream().max(Integer::compareTo);
    }

    /**
     * Demonstra {@link Stream#anyMatch(java.util.function.Predicate)}.
     */
    private static boolean anyMatchContemMan(List<String> nomes) {
        return nomes.stream().anyMatch(n -> n.toLowerCase().contains("man"));
    }

    /**
     * Demonstra {@link Stream#allMatch(java.util.function.Predicate)}.
     */
    private static boolean allMatchLenMin3(List<String> nomes) {
        return nomes.stream().allMatch(n -> n.length() >= 3);
    }

    /**
     * Demonstra {@link Stream#noneMatch(java.util.function.Predicate)}.
     */
    private static boolean noneMatchZ(List<String> nomes) {
        return nomes.stream().noneMatch(n -> n.startsWith("Z"));
    }

    /**
     * Demonstra {@link Stream#findFirst()} com filtro.
     */
    private static Optional<String> findFirstComecaComS(List<String> nomes) {
        return nomes.stream().filter(n -> n.startsWith("S")).findFirst();
    }

    /**
     * Demonstra {@link Stream#findAny()} em fluxo paralelo (pode retornar qualquer um que case).
     */
    private static Optional<String> findAnyContemEspaco(List<String> nomes) {
        return nomes.parallelStream().filter(n -> n.contains(" ")).findAny();
    }

    /**
     * Demonstra {@link Collectors#toSet()} coletando iniciais únicas.
     */
    private static Set<Character> collectToSetIniciais(List<String> nomes) {
        return nomes.stream()
                .map(s -> s.charAt(0))
                .collect(Collectors.toSet());
    }

    /**
     * Demonstra {@link Collectors#toMap(Function, Function)} mapeando nome->tamanho.
     */
    //TODO PRECISO ESTUDAR
    private static Map<String, Integer> toMapNomeTamanho(List<String> nomes) {
        return nomes.stream()
                .collect(Collectors.toMap(Function.identity(), String::length, (a, b) -> a, LinkedHashMap::new));
    }

    /**
     * Demonstra {@link Collectors#joining(CharSequence)} concatenando em CSV.
     */
    private static String joiningCSV(List<String> nomes) {
        return nomes.stream().collect(Collectors.joining(", "));
    }

    /**
     * Demonstra {@link Collectors#groupingBy(Function)} agrupando pela primeira letra.
     */
    private static Map<Character, List<String>> groupingByPrimeiraLetra(List<String> nomes) {
        return nomes.stream().collect(Collectors.groupingBy(s -> s.charAt(0), LinkedHashMap::new, Collectors.toList()));
    }

    /**
     * Demonstra {@link Collectors#partitioningBy(java.util.function.Predicate)} particionando por tamanho.
     */
    private static Map<Boolean, List<String>> partitioningByLenMaiorQue6(List<String> nomes) {
        return nomes.stream().collect(Collectors.partitioningBy(s -> s.length() > 6, Collectors.toList()));
    }

    /**
     * Demonstra {@link Collectors#summarizingInt(ToIntFunction)} para estatísticas numéricas.
     */
    private static IntSummaryStatistics summarizingInt(List<Integer> numeros) {
        return numeros.stream().collect(Collectors.summarizingInt(Integer::intValue));
    }

    /**
     * Enum com alguns personagens (heróis e vilões) usados nos exemplos.
     */
    private enum Personagem {
        BATMAN("Batman"),
        SUPERMAN("Superman"),
        THE_FLASH("The Flash"),
        AQUAMAN("Aquaman"),
        CORINGA("Coringa"),
        ARLEQUINA("Arlequina");

        private final String nome;

        Personagem(String nome) {
            this.nome = nome;
        }

        public String nome() {
            return nome;
        }

        public static List<String> toList() {
            return Arrays.stream(values())
                    .map(Personagem::nome)
                    .collect(Collectors.toList());
        }
    }
}
