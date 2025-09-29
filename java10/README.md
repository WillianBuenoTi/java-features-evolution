# ☕ Java 10 – Principais Features

O **Java 10** foi lançado em março de 2018, apenas 6 meses após o Java 9, inaugurando o novo ciclo de **releases semestrais**.
Não é uma versão LTS, mas trouxe recursos importantes para melhorar a produtividade e a performance da JVM.

---

## 🔹 Principais novidades

### 1. `var` – Inferência de Tipo Local
- Agora é possível declarar variáveis locais sem especificar o tipo explicitamente.
- O compilador infere o tipo com base no valor atribuído.

```java
var texto = "Hello Java 10";  // tipo inferido: String
var numero = 42;              // tipo inferido: int
var lista = List.of("A", "B"); // tipo inferido: List<String>
```

⚠️ Regras:
- Só funciona para **variáveis locais** (não em parâmetros, atributos de classe ou retornos de método).
- O tipo é **estático** (não é dinâmico como em linguagens de script).

---

### 2. G1 como Coletor de Lixo Padrão
- O **Garbage Collector G1** (introduzido no Java 7) passa a ser o **coletor padrão** no lugar do Parallel GC.
- Otimizado para reduzir pausas (baixa latência).

---

### 3. Copiar Arrays no `Set`
- Novo método `Set.copyOf(Collection)` cria conjuntos imutáveis a partir de coleções existentes.

```java
List<String> nomes = List.of("Ana", "João");
Set<String> copia = Set.copyOf(nomes); // imutável
```

---

### 4. API de `Optional` Melhorada
- `Optional.orElseThrow()` agora está disponível **sem precisar passar exceção manualmente**.

```java
Optional<String> opt = Optional.of("valor");
String v = opt.orElseThrow(); // lança NoSuchElementException se vazio
```

---

### 5. Novo Container Awareness
- JVM agora detecta **limites de CPU e memória** em **containers Docker/Kubernetes**.
- Ajusta automaticamente o uso de recursos.
- Essencial para ambientes de **cloud e microservices**.

---

### 6. Novo JEP 296 – `JEP Consolidation`
- Consolidação do código-fonte da JDK em **um único repositório** (antes eram múltiplos).
- Facilita contribuição e manutenção.

---

### 7. Outras melhorias
- `List.copyOf()`, `Map.copyOf()` → coleções imutáveis a partir de outras coleções.  
- Melhorias no **GC**: Parallel Full GC para o G1.  
- Novas APIs para **root certificates** (mais segurança).  
- Suporte inicial para **graal JIT compiler** como experimento.  

---

## 📚 Referências
- [Documentação oficial Java 10](https://docs.oracle.com/javase/10/)
- [JEPs do Java 10](https://openjdk.org/projects/jdk/10/)
- [Baeldung – Java 10 Features](https://www.baeldung.com/java-10-overview)
