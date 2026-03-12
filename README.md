# FractalStellarisBundle


>#### **autor**: Gabriel Synapse
>#### **data**: 11/03/2026

---

# 🌌 FractalStellaris | Client Module (Internal)

Este módulo é a camada de interface e renderização do projeto **FractalStellaris**, desenvolvido exclusivamente para o ecossistema da **Nova Fénix**. Ele é responsável por transformar os dados lógicos do servidor em experiências visuais imersivas.

## 📜 Licenciamento e Direitos Autorais

**ESTE MÓDULO É OPEN SOURCE SOB REGIME "READ-ONLY" (APENAS LEITURA).**

* **Código-Fonte:** Disponibilizado para fins de estudo e transparência. **Não é permitida** a modificação, redistribuição ou uso de partes deste código em outros projetos sem autorização prévia.
* **Assets (Modelos, Texturas e Sons):** Todos os arquivos contidos em `src/main/resources/assets/` são de propriedade exclusiva e **não podem ser extraídos, utilizados ou replicados**.
* **Dependência:** Este módulo não possui funcionalidade "Standalone". Ele foi projetado para operar exclusivamente em conjunto com o servidor oficial da Nova Fénix.

---

## 🎨 Funcionalidades do Módulo

* **Fractal Rendering:** Algoritmos de renderização de partículas e efeitos visuais baseados na geometria fractal da dimensão.
* **Custom Entity Models:** Integração com modelos proprietários (via GeckoLib/Blockbench) para as criaturas da FractalStellaris.
* **Skybox Dinâmica:** Sistema de renderização de céu que reflete as nebulosas e constelações de *Lyra Stellaris*.
* **Shaders Internos:** Efeitos de pós-processamento para distorção visual em áreas de alta instabilidade fractal.

---

## 🛠️ Arquitetura Client-Side

O módulo segue o padrão de separação rigorosa do Fabric para evitar vazamento de memória e conflitos de threads:

### 🎮 Entrypoints

Definidos em `Client.java`:

* `EntityRendererRegistry`: Registro dos renderizadores das novas entidades.
* `BlockRenderLayerMap`: Definição de transparências para blocos de cristal e gases.
* `ParticleFactoryRegistry`: Registro de partículas estelares customizadas.

### 📡 Networking (S2C)

Este módulo escuta pacotes enviados pelo servidor (`ServerPlayNetworking`) para disparar eventos visuais que não afetam a lógica de jogo, como:

* Animações de ambiente.
* Efeitos sonoros localizados.
* Mudanças climáticas na dimensão Fractal.

---

## ⚠️ Avisos Técnicos

1. **Compatibilidade:** Este módulo é otimizado para o hardware do cliente. Evite adicionar lógicas pesadas no `WorldTick` do cliente para não reduzir o FPS.
2. **Assets Hardcoded:** Algumas referências de texturas estão vinculadas diretamente ao `modid` interno. Alterar o nome do projeto quebrará todos os modelos.
3. **Segurança:** Nenhuma lógica crítica (como permissões ou spawn) deve ser processada aqui. O cliente apenas "desenha" o que o servidor autoriza.

---

## 💎 Créditos e Autoria

Desenvolvido por **Gabriel Synapse**.
Todos os direitos reservados à marca **Nova Fénix**.

---

### Dica para o seu projeto:

No seu `build.gradle`, você pode configurar o Gradle para não incluir as pastas de código-fonte no `.jar` final, ou usar uma ferramenta de **Obfuscação** se quiser proteger ainda mais a lógica interna, mesmo que o código no GitHub seja aberto.

