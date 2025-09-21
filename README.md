# ☕ Java Features Evolution

Este repositório tem como objetivo **demonstrar a evolução das features do Java** a partir da versão **8 até a 25 (LTS)**.  
Cada versão terá um pacote próprio com exemplos de código (quando aplicável) e documentação (quando for apenas estrutural).  

A ideia é usar **testes unitários** e **exemplos práticos** para evidenciar as mudanças mais importantes da linguagem e da plataforma.

---

## 📂 Estrutura do Projeto

```
src/
 └── main/
     └── java/
         └── evolution/
             ├── java8/
             ├── java9/
             ├── java10/
             ├── java11/
             ├── java12_13/
             ├── java14/
             ├── java15/
             ├── java16/
             ├── java17/
             ├── java18_20/
             ├── java21/
             ├── java22/
             ├── java23/
             ├── java24/
             └── java25/
```

- **Exemplos de código** → features de linguagem (records, text blocks, virtual threads, etc.).  
- **Somente documentação** → mudanças estruturais (JPMS, remoções, Security Manager, etc.).  

---

## 📖 Diferenças de cada versão (Java 8 → 25)

### 🔹 Java 8 (baseline)
- Streams API, Lambdas, Method references
- Functional interfaces (`Function`, `Predicate`, etc.)
- `Optional`
- Date/Time API (`java.time`)

### 🔹 Java 9
- **JPMS (módulos)**  
- JShell (REPL)
- API `Stream` melhorada

### 🔹 Java 10
- **`var`** (inference para variáveis locais)

### 🔹 Java 11 (LTS)
- **HTTP Client** estável (HTTP/2, WebSocket)
- TLS 1.3
- Strings helpers (`isBlank`, `lines`, `repeat`)
- Flight Recorder (JFR) aberto

### 🔹 Java 12 e 13
- **Switch expressions** (preview)

### 🔹 Java 14
- **Switch expressions** finalizadas
- **Helpful NullPointerException**

### 🔹 Java 15
- **Text blocks** (strings multilinha)
- Remoção do Nashorn JS engine

### 🔹 Java 16
- **Records**
- **Pattern Matching para instanceof**

### 🔹 Java 17 (LTS)
- **Sealed classes**
- Novos PRNGs
- Security Manager deprecado

### 🔹 Java 18 a 20
- Incubação de APIs (Vector API, Pattern Matching avançado)
- Melhorias contínuas no GC

### 🔹 Java 21 (LTS)
- **Virtual Threads** (Project Loom)
- **Pattern Matching para switch**
- **Record patterns**
- **Sequenced Collections**

### 🔹 Java 22
- **Foreign Function & Memory API** (final)
- **Unnamed variables/patterns** (`_`)

### 🔹 Java 23
- Melhorias incrementais nas APIs de concorrência e GC

### 🔹 Java 24
- **Security Manager permanentemente desabilitado**

### 🔹 Java 25 (LTS)
- **Scoped Values (final)**
- **Structured Concurrency (preview)**
- **Vector API (incubating)**
- **Compact source/instance main**
- **Remoção de suporte a x86 32-bit**

---

## 📚 Plano de Estudo

Cada versão terá:  
1. **Pacote dedicado** (`evolution.javaXX`)  
2. **Classes de exemplo** com testes unitários para features de linguagem/biblioteca  
3. **Documentação em Markdown** para mudanças estruturais ou remoções

### Ordem sugerida de estudo

1. **Revisão (Java 8)** → Streams, Lambdas, Optional.  
2. **Novidades até o 11 (LTS)** → var, HTTP Client, JShell, JPMS.  
3. **Java 14–17 (LTS)** → switch expressions, records, sealed classes.  
4. **Java 21 (LTS)** → virtual threads, pattern matching, record patterns.  
5. **Java 22–25** → FFM API, Scoped Values, Structured Concurrency, Vector API.  

---

## 🚀 Objetivo

Ao final, este repositório deve servir como:  
- **Guia de referência** para quem quer entender a evolução do Java.  
- **Material de estudo**.  
- **Portfólio público** demonstrando domínio das features modernas do Java.  

---
