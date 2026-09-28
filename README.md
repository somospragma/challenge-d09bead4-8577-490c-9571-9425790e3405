# Exploración y Análisis de Paradigmas No Imperativos en Sistemas Reactivos

La banca moderna demanda sistemas altamente resilientes, escalables y de alto rendimiento. Tu misión es explorar y analizar cómo los paradigmas no imperativos, específicamente el reactivo y el funcional, pueden mejorar estos atributos en un sistema de procesamiento de transacciones financieras. Identificarás actores clave (originador de créditos, motor antifraude, buró de riesgos), sus interacciones y los umbrales operativos (1 500 solicitudes/segundo en hora pico). Evalúas cómo estos paradigmas abordan la consistencia de datos, la latencia en respuestas y la recuperación ante fallos (timeout del buró >2s, respuesta 5xx del core).

## Informacion General

| Campo | Valor |
|-------|-------|
| **Tema** | Adopción de Paradigmas de Programación No Imperativos: Con Enfoque Reactivo y Funcional |
| **Nivel** | master-l1 |
| **Tipo** | theoretical |
| **Tiempo estimado** | 8 horas |

## Fases del Reto

### Fase 0: Configuración del Proyecto

**Objetivo:** Obtener el proyecto base funcional enviando el Código Base a un asistente de IA, que lo analizará, corregirá errores y generará un ZIP listo para usar.

**Tiempo estimado:** 15-30 minutos

**Instrucciones:**

- Asegúrate de tener instalado para ejecutar el proyecto: JDK 17+, Maven 3.9+, IDE con soporte Java.
- Copia todo el contenido del campo **Código Base** de este reto — incluyendo el texto de instrucciones que aparece al inicio.
- Abre un asistente de IA (Claude en claude.ai, ChatGPT o Gemini — se recomienda Claude), pega el contenido copiado en el chat y envíalo.
- El asistente analizará los archivos, corregirá errores y generará un archivo ZIP descargable. Descárgalo y extráelo en la carpeta donde quieras trabajar.
- Ejecuta `mvn compile` en la raíz. Si no hay errores, estás listo.

**Entregable:** El proyecto compila/arranca sin errores.

<details>
<summary>Pistas de conocimiento</summary>

- Copia el Código Base completo incluyendo el texto de instrucciones al inicio — esas instrucciones le indican al asistente exactamente qué hacer con los archivos.
- Si el asistente no genera el ZIP automáticamente al terminar el análisis, escríbele: "genera el ZIP ahora".
- Si el proyecto tiene errores al arrancar, comparte el mensaje de error con el mismo asistente para que lo corrija.

</details>

### Fase 1: Identificación de Requisitos y Restricciones

**Objetivo:** Comprender los requisitos funcionales y no funcionales del sistema de procesamiento de transacciones, así como las restricciones operativas.

**Tiempo estimado:** 2 horas

**Instrucciones:**

- Identifica y describe los actores clave y sus interacciones en el dominio de las transacciones financieras.
- Enumera los umbrales operativos y las restricciones del sistema.
- Analiza cómo estos requisitos y restricciones impactan en la elección del paradigma de programación.

**Entregable:** Documento que describe los actores, interacciones, umbrales y restricciones del sistema.

<details>
<summary>Pistas de conocimiento</summary>

- Considera cómo los diferentes paradigmas abordan la concurrencia y la asincronía.
- Reflexiona sobre la importancia de la idempotencia en las transacciones financieras.

</details>

### Fase 2: Exploración de Paradigmas No Imperativos

**Objetivo:** Evaluar los paradigmas reactivo y funcional en términos de sus ventajas, desventajas y operadores básicos.

**Tiempo estimado:** 3 horas

**Instrucciones:**

- Investiga y describe los cuatro pilares del manifiesto de sistemas reactivos.
- Evalúa las ventajas y desventajas de los paradigmas reactivo y funcional.
- Identifica y describe los operadores básicos de cada paradigma.

**Entregable:** Documento que describe los cuatro pilares de los sistemas reactivos, las ventajas y desventajas de los paradigmas reactivo y funcional, y los operadores básicos de cada uno.

<details>
<summary>Pistas de conocimiento</summary>

- Considera cómo cada paradigma aborda la resiliencia y la escalabilidad.
- Reflexiona sobre los casos de uso adecuados para cada paradigma.

</details>

### Fase 3: Análisis de Aplicación en el Dominio

**Objetivo:** Analizar cómo los paradigmas no imperativos pueden aplicarse al dominio de las transacciones financieras.

**Tiempo estimado:** 3 horas

**Instrucciones:**

- Evalúa cómo los paradigmas reactivo y funcional pueden mejorar la consistencia de datos, la latencia en respuestas y la recuperación ante fallos en el sistema de procesamiento de transacciones.
- Propón una estrategia para implementar estos paradigmas en el sistema, considerando los requisitos y restricciones identificados en la fase 1.
- Identifica posibles desafíos y trade-offs en la implementación.

**Entregable:** Documento que propone una estrategia para implementar los paradigmas no imperativos en el sistema de procesamiento de transacciones, considerando los requisitos y restricciones identificados.

<details>
<summary>Pistas de conocimiento</summary>

- Considera cómo la asincronía y la concurrencia pueden ser aprovechadas en cada paradigma.
- Reflexiona sobre los trade-offs entre consistencia y disponibilidad.

</details>

## Dimensiones Evaluadas

- **queEs**: ¿Qué son los paradigmas reactivo y funcional y cuáles son sus características principales?
- **paraQueSirve**: ¿Cómo pueden los paradigmas reactivo y funcional mejorar la resiliencia, escalabilidad y rendimiento de un sistema de procesamiento de transacciones?
- **comoSeUsa**: ¿Cuáles son los operadores básicos de los paradigmas reactivo y funcional y cómo se aplican en un sistema?
- **erroresComunes**: ¿Cuáles son los errores comunes al implementar paradigmas no imperativos en sistemas de procesamiento de transacciones?
- **queDecisionesImplica**: ¿Qué decisiones de diseño implica la adopción de paradigmas no imperativos en un sistema de procesamiento de transacciones?

## Criterios de Evaluacion

- Identificación correcta de los actores, interacciones, umbrales y restricciones del sistema.
- Descripción precisa de los cuatro pilares de los sistemas reactivos, ventajas y desventajas de los paradigmas reactivo y funcional, y operadores básicos.
- Propuesta de una estrategia viable para implementar los paradigmas no imperativos en el sistema de procesamiento de transacciones, considerando los requisitos y restricciones identificados.
- Identificación de posibles desafíos y trade-offs en la implementación.

## Como trabajar con un asistente de IA

Hay dos caminos, elegi uno:

- **AGENTS.md** (recomendado) — instrucciones nativas del repo. Abri esta carpeta con tu agente local (Claude Code, Cursor, Codex, Copilot, Gemini) y las carga solo. Sabe que archivos faltan y con que comando se verifica, y completa el scaffold escribiendo en disco.
- **PROMPT_MEJORA.md** — para copiar y pegar en un chat (claude.ai, ChatGPT). Devuelve un ZIP con el proyecto. Sirve si no tenes un agente en el IDE.

Ninguno de los dos resuelve las fases del reto: eso es tu trabajo.

## Verificacion

El proyecto esta listo para trabajar cuando este comando corre sin errores:

```bash
mvn clean compile
```

---

*Reto generado automaticamente por Challenge Generator - Pragma*
