/**
 * Created by WillianF.Bueno on 21/09/2025
 */
void main() {
    exemplosVar();
    exemplosCopyOf();
    exemploOrElseThrow();
}

private static void exemplosVar() {
    var x = 10;
    var y = 20.0;
    var z = "Hello World!";
    var xy = x + y;
    var lista = List.of("A", "B");

    System.out.println(x);
    System.out.println(y);
    System.out.println(xy);
    System.out.println(z);
    System.out.println(lista);
}

private void exemplosCopyOf() {
    List<String> nomes = List.of("Ana", "João");
    List<String> copiaLista = List.copyOf(nomes);
    Set<String> copiaSet = Set.copyOf(nomes);
    Map<Integer, String> copiaMap = Map.copyOf(Map.of(1, "um", 2, "dois"));

    System.out.println(copiaLista);
    System.out.println(copiaSet);
    System.out.println(copiaMap);
}

private void exemploOrElseThrow() {
    Optional<String> opt = Optional.of("Java 10");
    String valor = opt.orElseThrow(); // lança NoSuchElementException se vazio
    System.out.println(valor);
}

