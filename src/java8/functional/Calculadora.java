package java8.functional;

@FunctionalInterface
interface Calculadora {

    int executar(int x, int y);

    default int dobrarResultado(int x, int y) {
        return executar(x, y) * 2;
    }

    static Calculadora somar() {
        return Integer::sum;
    }

    static Calculadora multiplicar() {
        return (a, b) -> a * b;
    }
}
