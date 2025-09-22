package util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Utilitário para impressão formatada de resultados em exemplos.
 */
public final class PrinterUtil {

    private final String titulo;

    private PrinterUtil(String titulo) {
        this.titulo = titulo;
    }

    public static void run(String titulo, List<Supplier<Map<String, Object>>> suppliers) {
        PrinterUtil util = new PrinterUtil(titulo);
        util.banner();
        suppliers.forEach(supp -> {
            Map<String, Object> map = Optional.ofNullable(supp.get()).orElse(Collections.emptyMap());
            map.forEach(util::show);
        });
        util.footer();
    }

    public static void run(String titulo, Supplier<Map<String, Object>> suppliers) {
        PrinterUtil util = new PrinterUtil(titulo);
        util.banner();
        suppliers.get().forEach(util::show);
        util.footer();
    }

    public static Supplier<Map<String, Object>> item(String label, Object value) {
        return () -> {
            Map<String, Object> m = new LinkedHashMap<>();
            m.put(label, value);
            return m;
        };
    }

    @SafeVarargs
    public static Supplier<Map<String, Object>> items(Map.Entry<String, Object>... entries) {
        return () -> {
            Map<String, Object> m = new LinkedHashMap<>();
            for (Map.Entry<String, Object> e : entries) {
                m.put(e.getKey(), e.getValue());
            }
            return m;
        };
    }

    public static Map.Entry<String, Object> entry(String label, Object value) {
        return new AbstractMap.SimpleImmutableEntry<>(label, value);
    }

    private void show(String label, Object value) {
        System.out.printf("%-50s : %s%n", label, stringify(value));
    }

    private void banner() {
        String data = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        String line = repeat(Math.max(60, titulo.length() + 80));
        System.out.println(line);
        System.out.printf("  %s  |  %s%n", titulo, data);
        System.out.println(line);
    }

    private void footer() {
        System.out.println(repeat(Math.max(60, titulo.length() + 80)));
    }

    private static String stringify(Object o) {
        return switch (o) {
            case null -> "null";

            // Tipos simples
            case CharSequence cs     -> cs.toString();
            case Number n            -> n.toString();
            case Boolean b           -> b.toString();
            case Enum<?> e           -> e.toString();

            // Optionals
            case Optional<?> opt     -> opt.map(PrinterUtil::stringify).orElse("Optional.empty");
            case OptionalInt oi      -> oi.isPresent() ? Integer.toString(oi.getAsInt()) : "OptionalInt.empty";
            case OptionalLong ol     -> ol.isPresent() ? Long.toString(ol.getAsLong())   : "OptionalLong.empty";
            case OptionalDouble od   -> od.isPresent() ? Double.toString(od.getAsDouble()): "OptionalDouble.empty";

            // Estatísticas
            case IntSummaryStatistics s -> String.format(
                    "count=%d, sum=%d, min=%d, max=%d, avg=%.2f",
                    s.getCount(), s.getSum(), s.getMin(), s.getMax(), s.getAverage()
            );

            // Stream: consome para lista e delega
            case Stream<?> st -> stringify(st.collect(Collectors.toList()));

            // Arrays (objetos e primitivos)
            case Object[] arr        -> Arrays.stream(arr).map(PrinterUtil::stringify)
                    .collect(Collectors.joining(", ", "[", "]"));
            case int[]    arr        -> Arrays.toString(arr);
            case long[]   arr        -> Arrays.toString(arr);
            case double[] arr        -> Arrays.toString(arr);
            case boolean[] arr       -> Arrays.toString(arr);
            case byte[]   arr        -> Arrays.toString(arr);
            case short[]  arr        -> Arrays.toString(arr);
            case char[]   arr        -> Arrays.toString(arr);
            case float[]  arr        -> Arrays.toString(arr);

            // Collections
            case Collection<?> c -> c.stream()
                    .map(PrinterUtil::stringify)
                    .collect(Collectors.joining(", ", "[", "]"));

            // Maps
            case Map<?, ?> m -> m.entrySet().stream()
                    .map(e -> stringify(e.getKey()) + " -> " + stringify(e.getValue()))
                    .collect(Collectors.joining(", ", "{", "}"));

            // Fallback
            default -> String.valueOf(o);
        };
    }

    private static String repeat(int n) {
        char[] arr = new char[n];
        Arrays.fill(arr, '═');
        return new String(arr);
    }
}