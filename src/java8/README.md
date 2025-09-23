# ☕ Java 8 – Principais Features

O **Java 8** foi um marco na linguagem, trazendo conceitos funcionais e APIs modernas.

---

## 🔹 Principais novidades

### 1. Streams API
- Processamento de coleções de forma **declarativa** (estilo funcional).
- Operações intermediárias (map, filter, sorted) e terminais (collect, forEach, reduce).
- Lazy evaluation → só executa quando necessário.

### 2. Lambdas
- Funções anônimas → deixam o código mais conciso.
- Usadas principalmente com Streams e APIs que recebem interfaces funcionais.

### 3. Method References
- Forma simplificada de usar métodos existentes em lugar de lambdas.
- Exemplo: `list.forEach(System.out::println)`.

### 4. Functional Interfaces
- Interfaces com **um único método abstrato**.
- Ex.: `Predicate<T>`, `Function<T,R>`, `Supplier<T>`, `Consumer<T>`.

### 5. Optional
- Wrapper para valores que **podem estar ausentes**.
- Evita `NullPointerException` quando usado corretamente.
- Métodos úteis: `of`, `ofNullable`, `empty`, `ifPresent`, `orElse`, `map`, `flatMap`.

### 6. Nova API de Data e Hora (`java.time`)
- Baseada em Joda-Time.
- Imutável, thread-safe, muito mais completa.
- Principais classes:
    - `LocalDate`
    - `LocalTime`
    - `LocalDateTime`
    - `ZonedDateTime`
    - `Duration`, `Period`

---

## 📚 Referências
- [Documentação oficial Java 8](https://docs.oracle.com/javase/8/docs/)
- [Guia Baeldung – Java 8](https://www.baeldung.com/java-8-new-features)

---
