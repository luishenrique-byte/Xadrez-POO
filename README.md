# ♟️ Jogo de Xadrez - Programação Orientada a Objetos

<div align="center">

![Java](https://img.shields.io/badge/Java-17+-orange?style=for-the-badge&logo=java)
![Status](https://img.shields.io/badge/Status-Em%20Desenvolvimento-yellow?style=for-the-badge)
![License](https://img.shields.io/badge/Licen%C3%A7a-Acad%C3%AAmico-blue?style=for-the-badge)

Um simulador de xadrez completo desenvolvido em Java puro, implementando todas as regras oficiais do jogo e padrões de Programação Orientada a Objetos.

[Funcionalidades](#-funcionalidades) •
[Como Jogar](#-como-jogar) •
[Arquitetura](#-arquitetura) •
[Como Executar](#-como-executar)

</div>

---

## 📖 Sobre o Projeto

Este projeto implementa um jogo de xadrez completo jogável via terminal/console, desenvolvido como trabalho prático da disciplina de **Programação Orientada a Objetos**. O sistema simula uma partida de xadrez entre dois jogadores, respeitando todas as regras oficiais do jogo.

### 🎯 Objetivos

- Aplicar conceitos de **POO** (Herança, Polimorfismo, Encapsulamento, Abstração)
- Implementar **lógica complexa de jogo** (movimentos, validações, estados)
- Desenvolver **sistema de detecção de check e checkmate**
- Criar **arquitetura escalável e manutenível**

---

## ✨ Funcionalidades

### Implementadas ✅

- [x] **Movimentos de todas as peças** - Rei, Rainha, Torre, Bispo, Cavalo e Peão
- [x] **Captura de peças** - Sistema completo de captura e remoção
- [x] **Roque (Castling)** - Ambos os tipos (pequeno e grande)
  - Validação de primeiro movimento
  - Verificação de caminho livre
  - Detecção de casas atacadas
- [x] **Promoção de Peão** - Conversão ao alcançar a última fileira
- [x] **Detecção de Check** - Aviso quando o rei está em xeque
- [x] **Detecção de Checkmate** - Fim de jogo ao xeque-mate
- [x] **Validação de movimentos legais** - Impede jogadas que deixam o rei em check
- [x] **Sistema de desfazer jogada** - Rollback automático para jogadas inválidas
- [x] **Alternância de turnos** - Controle automático de jogador atual

### Planejadas 🚧

- [ ] **En Passant** - Captura especial de peão
- [ ] **Stalemate** - Empate por afogamento
- [ ] **Histórico de jogadas** - Log completo da partida
- [ ] **Notação algébrica** - Registro em formato padrão (e4, Nf3, etc)
- [ ] **Interface gráfica** - GUI com JavaFX ou Swing

---

## 🏗️ Arquitetura

### Estrutura de Pacotes
```
src/
├── pecas/                    # Implementação de todas as peças
│   ├── Rei.java             # Rei com roque e detecção de check/checkmate
│   ├── Dama.java            # Rainha (movimentos combinados)
│   ├── Torre.java           # Torre com flag de primeiro movimento
│   ├── Bispo.java           # Bispo (movimentos diagonais)
│   ├── Cavalo.java          # Cavalo (movimento em L)
│   └── Peao.java            # Peão com promoção
│
├── tabuleiro/               # Sistema do tabuleiro
│   ├── Tabuleiro.java       # Gerenciamento de peças e posições
│   └── Posicao.java         # Representação de coordenadas
│
├── xadrez/                  # Lógica do jogo
│   ├── PartidaDeXadrez.java # Controlador principal da partida
│   ├── Peca.java            # Classe abstrata base
│   └── Cor.java             # Enum para cor das peças
│
├── ui/                      # Interface com usuário
│   └── Tela.java            # Renderização do tabuleiro no terminal
│
└── Program.java             # Ponto de entrada da aplicação
```

### Diagrama de Classes Simplificado
```
┌─────────────┐
│   Peca      │ (abstract)
│ - cor       │
│ - posicao   │
│ - tabuleiro │
└──────┬──────┘
       │
       ├─────────────────────────────────┐
       │                                 │
  ┌────▼────┐  ┌──────┐  ┌───────┐  ┌──▼───┐
  │   Rei   │  │ Dama │  │ Torre │  │ Peao │ ...
  │         │  │      │  │       │  │      │
  └─────────┘  └──────┘  └───────┘  └──────┘
```

### Padrões de Projeto Utilizados

- **Template Method** - `Peca.movimentosPossiveis()` define o contrato
- **Strategy** - Cada peça implementa sua própria lógica de movimento
- **Composite** - Tabuleiro gerencia coleção de peças
- **State** - Controle de estados como `primeiroMovimento`

---

## 🎮 Como Jogar

### Interface do Tabuleiro
```
8  r  c  b  d  r  b  c  t
7  p  p  p  p  p  p  p  p
6  -  -  -  -  -  -  -  -
5  -  -  -  -  -  -  -  -
4  -  -  -  -  -  -  -  -
3  -  -  -  -  -  -  -  -
2  P  P  P  P  P  P  P  P
1  T  C  B  D  R  B  C  T
   a  b  c  d  e  f  g  h
```

### Legenda das Peças

|  Símbolo  |    Peça    |     Cor      |
|-----------|------------|--------------|
| `R` / `r` | Rei        | Branca/Preta |
| `D` / `d` | Dama       | Branca/Preta |
| `T` / `t` | Torre      | Branca/Preta |
| `B` / `b` | Bispo      | Branca/Preta |
| `C` / `c` | Cavalo     | Branca/Preta |
| `P` / `p` | Peão       | Branca/Preta |

### Entrada de Coordenadas
```
linha origem (1 a 8): 2
coluna origem (a a h): e

linha destino (1 a 8): 4
coluna destino (a a h): e
```

### Movimentos Especiais

**Roque:**
- Move o rei 2 casas na direção da torre
- Sistema valida automaticamente todas as condições

**Promoção de Peão:**
- Ao alcançar a última fileira, escolha a peça:
  - `D` - Dama
  - `T` - Torre
  - `B` - Bispo
  - `C` - Cavalo

---

## 🚀 Como Executar

### Pré-requisitos

- **Java JDK 17+** instalado
- Terminal/Console com suporte a caracteres UTF-8

### Compilação
```bash
# Clone o repositório
git clone https://github.com/luishenrique-byte/Xadrez-POO.git
cd Xadrez-POO

# Compile todos os arquivos
javac -d out -sourcepath src src/Program.java
```
```bash
# Ou compile manualmente:
javac -d out src/pecas/*.java src/tabuleiro/*.java src/xadrez/*.java src/ui/*.java src/Program.java
```

### Execução
```bash
# Execute o programa
java -cp out Program
```

### Troubleshooting

**Problema:** `Error: Could not find or load main class Program`
**Solução:** Certifique-se de estar no diretório correto e que a compilação foi bem-sucedida.

**Problema:** Caracteres estranhos no terminal
**Solução:** Configure seu terminal para usar encoding UTF-8.

---

## 🧪 Testando o Projeto

### Cenários de Teste Recomendados

1. **Movimentos Básicos**
   - Mova cada tipo de peça para verificar validações

2. **Roque**
   - Tente roque com rei em check (deve falhar)
   - Tente roque com caminho bloqueado (deve falhar)
   - Execute roque válido

3. **Check e Checkmate**
   - Coloque o rei adversário em check
   - Execute um checkmate simples (ex: Mate do Pastor)

4. **Promoção**
   - Avance um peão até a última fileira
   - Teste todas as opções de promoção

---

## 📚 Conceitos de POO Aplicados

### 1. **Herança**
```java
public abstract class Peca { ... }

public class Rei extends Peca { ... }
public class Dama extends Peca { ... }
```

### 2. **Polimorfismo**
```java
// Cada peça implementa sua própria lógica
@Override
public void movimentosPossiveis() {
    // Lógica específica da peça
}
```

### 3. **Encapsulamento**
```java
private boolean primeiroMovimento;  // Atributo privado

public boolean isPrimeiroMovimento() {  // Getter público
    return primeiroMovimento;
}
```

### 4. **Abstração**
```java
// Classe abstrata define o contrato
public abstract void movimentosPossiveis();
```

---

## 🔧 Detalhes Técnicos

### Representação do Tabuleiro

- **Matriz 8x8** de objetos `Peca`
- Coordenadas: `[linha][coluna]` onde `0,0` é o canto superior esquerdo
- Conversão automática de notação algébrica (a1-h8) para índices de matriz

### Sistema de Validação
```java
// Validação em três níveis:
1. Movimento é geometricamente possível?
2. Caminho está livre?
3. Movimento deixa o rei em check?
```

### Otimizações

- **Matriz de movimentos booleana** - Cache de movimentos possíveis
- **Lista de peças ativa** - Evita iterar matriz inteira
- **Early return** - Validações falham rapidamente

---

## 📊 Histórico de Desenvolvimento

### Commits Principais
```
feat(xadrez): implementa movimento de roque
- Valida condições necessárias (primeiro movimento, sem check)
- Verifica caminho livre entre rei e torre
- Identifica torre válida para roque pequeno e grande

fix(Rei): resolve StackOverflowError na validação de roque
- Problema: estaEmCheck() chamava movimentosPossiveis() recursivamente
- Solução: implementado método direto de verificação de ataques

refactor(Rei): extrai cálculo de movimentos básicos em método auxiliar
- Cria método calcularMovimentosBasicos() para reutilização
- Elimina duplicação de código

refactor(Tabuleiro): centraliza atualização de primeiroMovimento
- Move lógica de PartidaDeXadrez para Tabuleiro.posicionarPeca()
- Garante atualização consistente para Rei, Torre e Peão
```

---

## 🐛 Problemas Conhecidos

- [ ] En Passant não implementado
- [ ] Empate por repetição não detectado
- [ ] Empate por 50 movimentos não implementado
- [ ] Interface de terminal não funciona bem em todos os sistemas operacionais

---

## 🤝 Contribuindo

Este é um projeto acadêmico, mas sugestões são bem-vindas!

### Como Contribuir

1. Fork o projeto
2. Crie uma branch para sua feature (`git checkout -b feature/NovaFuncionalidade`)
3. Commit suas mudanças (`git commit -m 'feat: adiciona nova funcionalidade'`)
4. Push para a branch (`git push origin feature/NovaFuncionalidade`)
5. Abra um Pull Request

### Convenção de Commits

Seguimos o padrão **Conventional Commits**:
```
feat: nova funcionalidade
fix: correção de bug
refactor: refatoração de código
docs: alterações em documentação
test: adição/correção de testes
```
---
## 📄 Licença

Este é um **projeto acadêmico** desenvolvido para fins educacionais. 
Não possui licença de código aberto.

**Uso:** Apenas para estudo e portfólio pessoal.

---

## 👨‍💻 Autor

**Luís Henrique**
- Disciplina: Programação Orientada a Objetos
- Instituição: UCSal
- Semestre: 2025/02

---

<div align="center">

**Desenvolvido com ♟️ e ☕ por Luís Henrique Freitas Mendes**

⭐ Se este projeto te ajudou, considere dar uma estrela!

</div>

---

## 📝 Notas Adicionais

### Possíveis Melhorias Futuras

1. **Interface Gráfica**
   - Implementar com JavaFX para experiência visual
   - Adicionar animações de movimento
   - Tema claro/escuro

2. **IA (Inteligência Artificial)**
   - Algoritmo Minimax para jogador computador
   - Diferentes níveis de dificuldade
   - Análise de melhores jogadas

3. **Multiplayer Online**
   - Sistema cliente-servidor com Sockets
   - Matchmaking entre jogadores
   - Chat integrado

4. **Análise de Partidas**
   - Exportar partida em formato PGN
   - Sugestões de melhores jogadas
   - Estatísticas de desempenho

5. **Acessibilidade**
   - Modo para daltônicos
   - Leitor de tela
   - Atalhos de teclado

---

**Última atualização:** Março 2026
