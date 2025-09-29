# ☕ Java 9 – Principais Features

O **Java 9** foi lançado em setembro de 2017 e trouxe mudanças estruturais profundas, além de novos recursos para tornar
o desenvolvimento mais produtivo.

---

## 🔹 Principais novidades

### 1. JPMS – Java Platform Module System (Jigsaw)
- Introduziu o conceito de **módulos nativos da linguagem** (`module-info.java`).
- Permite **encapsular pacotes** e **controlar dependências** entre módulos.
- Substitui o uso exclusivo do **classpath**, evitando problemas de *classpath hell*.
- Possibilita criar runtimes enxutos com `jlink`.

Exemplo de módulo:
```java
module br.com.exemplo.app {
    requires java.sql;
    exports br.com.exemplo.api;
    opens br.com.exemplo.entity to org.hibernate.orm.core;
}
```

---

### 2. JShell (REPL)
- Novo **ambiente interativo** para Java.
- Permite testar expressões, classes e métodos rapidamente, sem criar um projeto.
- Útil para aprendizado, prototipagem e testes rápidos.

Exemplo no JShell:
```java
jshell> int x = 5;
x ==> 5

jshell> x * 2
$2 ==> 10
```

---

### 3. Melhorias na API de Streams
- Novos métodos:  
  - `takeWhile(Predicate)`  
  - `dropWhile(Predicate)`  
  - `ofNullable(T)`  
  - `iterate(seed, hasNext, next)` (nova sobrecarga).
- Facilitam operações em coleções com mais controle e menos código.

---

### 4. Interface Collections – Método `of()`
- Agora é possível criar listas, conjuntos e mapas **imutáveis** de forma simples.

```java
List<String> lista = List.of("A", "B", "C");
Set<Integer> set = Set.of(1, 2, 3);
Map<Integer, String> map = Map.of(1, "um", 2, "dois");
```

---

### 5. Private Methods em Interfaces
- Interfaces podem ter **métodos privados** para reutilizar lógica interna.
- Ajuda a evitar duplicação de código entre *default methods*.

```java
interface Loggable {
    default void logInfo(String msg) {
        log("INFO", msg);
    }
    default void logError(String msg) {
        log("ERROR", msg);
    }
    private void log(String level, String msg) {
        System.out.println(level + ": " + msg);
    }
}
```

---

### 6. Outros destaques
- **Process API** melhorada (`ProcessHandle`) para inspecionar processos do SO.
- **HTTP/2 Client** (incubating) – substituto moderno do `HttpURLConnection`.
- **Compact Strings** → `String` passa a usar `byte[]` em vez de `char[]` para economizar memória quando possível.
- Depreciações: `Applet API`, `JavaEE` e `CORBA` marcados para remoção futura.

---

## 📚 Referências
- [Documentação oficial Java 9](https://docs.oracle.com/javase/9/)
- [Guia Baeldung – Java 9](https://www.baeldung.com/java-9-new-features)
