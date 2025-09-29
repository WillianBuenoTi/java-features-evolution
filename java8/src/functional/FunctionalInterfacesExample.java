package functional;

import util.PrinterUtil;

import java.util.Date;
import java.util.function.*;

public class FunctionalInterfacesExample {
    private static final String TITULO = "Java 8 Functional Interfaces - Demonstrações";

    static void main(String[] args) {
        PrinterUtil.run(TITULO, PrinterUtil.items(
                PrinterUtil.entry("Predicate (não vazio - 'Willian')", predicate("Willian")),
                PrinterUtil.entry("Predicate (não vazio - '')", predicate("")),
                PrinterUtil.entry("Function (int → label)", function(42)),
                PrinterUtil.entry("Supplier (data atual)", supplier()),
                PrinterUtil.entry("Consumer (imprime string)", consumer("Olá alunos!")),
                PrinterUtil.entry("BiFunction (soma 2 + 3)", biFunction(2, 3)),
                PrinterUtil.entry("UnaryOperator (uppercase)", unaryOperator("hello")),
                PrinterUtil.entry("BinaryOperator (max entre 10 e 20)", binaryOperator(10, 20)),
                PrinterUtil.entry("BiPredicate (tamanho > n)", biPredicate("Will", 2)),
                PrinterUtil.entry("BiConsumer (nome + idade)", biConsumer("Ana", 25)),
                PrinterUtil.entry("ToIntFunction (tamanho string)", toIntFunction("Willian")),
                PrinterUtil.entry("IntSupplier (dado 1-6)", intSupplier()),
                PrinterUtil.entry("Custom Function",
                        testCustomFunction()
                ),
                PrinterUtil.entry("Custom Function executando métodos default",
                        testCustomFunctionDefault()
                ),
                PrinterUtil.entry("Custom Function usando métodos staticos",
                        testCustomFunctionStatic()
                )
        ));
    }

    private static int testCustomFunction() {
        Calculadora calc = ((x, y) ->  x * y);
        return calc.executar(2, 5);
    }

    private static int testCustomFunctionDefault() {
        Calculadora calc = ((x, y) ->  x * y);
        return calc.dobrarResultado(2, 5);
    }

    private static int testCustomFunctionStatic() {
        int resultadoSoma = Calculadora.somar().executar(5, 5);
        int resultadoMultiplicacao = Calculadora.multiplicar().executar(2, 5);
        return Calculadora.multiplicar().dobrarResultado(resultadoSoma, resultadoMultiplicacao);
    }

    private static Object predicate(String nome) {
        Predicate<String> isNotEmpty = n -> !n.isEmpty();
        return isNotEmpty.test(nome);
    }

    private static Object function(Integer valor) {
        Function<Integer, String> toLabel = n -> "Número: " + n;
        return toLabel.apply(valor);
    }

    private static Object supplier() {
        Supplier<Date> now = Date::new;
        return now.get();
    }

    private static Object consumer(String mensagem) {
        StringBuilder sb = new StringBuilder();
        Consumer<String> printer = s -> sb.append("Consumer recebeu: ").append(s);
        printer.accept(mensagem);
        return sb.toString();
    }

    private static Object biFunction(int a, int b) {
        BiFunction<Integer, Integer, Integer> soma = (x, y) -> x + y;
        return soma.apply(a, b);
    }

    private static Object unaryOperator(String texto) {
        UnaryOperator<String> upper = String::toUpperCase;
        return upper.apply(texto);
    }

    private static Object binaryOperator(int a, int b) {
        BinaryOperator<Integer> maior = Integer::max;
        return maior.apply(a, b);
    }

    private static Object biPredicate(String texto, int n) {
        BiPredicate<String, Integer> maiorQue = (s, i) -> s.length() > i;
        return maiorQue.test(texto, n);
    }

    private static Object biConsumer(String nome, int idade) {
        StringBuilder sb = new StringBuilder();
        BiConsumer<String, Integer> printer = (n, i) -> sb.append(n).append(" tem ").append(i).append(" anos");
        printer.accept(nome, idade);
        return sb.toString();
    }

    private static Object toIntFunction(String texto) {
        ToIntFunction<String> tamanho = String::length;
        return tamanho.applyAsInt(texto);
    }

    private static Object intSupplier() {
        IntSupplier dado = () -> (int) (Math.random() * 6) + 1;
        return dado.getAsInt();
    }
}
