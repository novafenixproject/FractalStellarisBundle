# FractalStellarisBundle


>#### **autor**: Gabriel Synapse
>#### **data**: 11/03/2026

---

# 🌌 FractalStellaris | Client Module (Internal)

Este módulo é a camada de interface e renderização do projeto **FractalStellaris**, desenvolvido exclusivamente para o ecossistema da **Nova Fénix**. Ele é responsável por transformar os dados lógicos do servidor em experiências visuais imersivas.

---
### 🎨 Propriedade e Direitos de Uso (Assets)

Todos os arquivos contidos em `src/main/resources/assets/` estão protegidos e não podem ser extraídos ou redistribuídos:

- **Modelos 3D, Texturas e Ícones:** São de propriedade exclusiva da **Nova Fénix / Gabriel Synapse**. O uso não autorizado destes arquivos é proibido.

- **Trilhas Sonoras e Áudios:** São obras de **Liborio Conti**, utilizadas sob licença comercial/pessoal limitada. O download deste mod não concede permissão para a extração ou reutilização destas músicas em outros projetos, respeitando o [Contrato de Licenciamento](https://www.no-copyright-music.com/) do autor original.
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

### 📡 Networking (S2C)

Este módulo escuta pacotes enviados pelo servidor (`ServerPlayNetworking`) para disparar eventos visuais que não afetam a lógica de jogo, como:

* Animações de ambiente.
* Efeitos sonoros localizados.
* Mudanças climáticas na dimensão Fractal.

---

## 💎 Créditos e Autoria

Desenvolvido por **Gabriel Synapse**.

Musicas **Liborio Conti:** [no-copyright-music](https://www.no-copyright-music.com)

Todos os direitos reservados à marca **Nova Fénix**.
