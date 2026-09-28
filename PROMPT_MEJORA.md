# Prompt para Mejorar el Codigo Base

Copia y pega el contenido del bloque de abajo en un asistente de IA (Claude, ChatGPT)
para obtener un ZIP con el proyecto completo y arrancable.

Si preferis trabajar en tu editor con un agente local (Claude Code, Cursor, Copilot), usa `AGENTS.md` en vez de este archivo: dice lo mismo pero para que escriba los archivos en disco.

## Las dos reglas que no se negocian

1. **Completa el boilerplate.** Todo lo que el proyecto necesita para compilar y arrancar: manifiesto de dependencias, punto de entrada, configuracion, capa de interfaz, y las capas del patron arquitectonico declarado. Eso es andamiaje y es tu trabajo.
2. **NO resuelvas el reto.** Los entregables de las fases son el trabajo de la persona. El hueco pedagogico se deja como esta: el proyecto arranca, pero lo que el reto pide implementar NO esta implementado.

Dicho de otra forma: si algo impide compilar, arreglalo. Si algo es logica de negocio incompleta, validaciones ausentes, un secreto hardcodeado o un patron mejorable, dejalo exactamente como esta — es lo que la persona tiene que encontrar.

## Superficie de practica — NO resuelvas

Estos archivos SON el ejercicio de la persona. No los implementes; deja stubs.

- `src/main/java/com/transaccionesfinancieras/config/Resilience4jConfig.java` — El topic pide resiliencia: este archivo es el ejercicio.

## Lo que le falta a este proyecto

Esto NO lo tenes que adivinar: salio de comparar el proyecto contra la arquitectura declarada del reto y de un analisis estatico del codigo. Completalo TODO.

### Referencias colgando en el codigo que si esta

Cada una rompe la compilacion:

- `src/main/java/com/transaccionesfinancieras/Application.java` — `reactor.core.publisher`: El import reactor.core.publisher.Hooks pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/transaccionesfinancieras/application/port/in/ProcesarTransaccionUseCase.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/transaccionesfinancieras/application/port/out/BuroRiesgoPort.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/transaccionesfinancieras/application/port/out/MotorAntifraudePort.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/transaccionesfinancieras/config/WebClientConfig.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/transaccionesfinancieras/config/WebClientConfig.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/transaccionesfinancieras/config/WebClientConfig.java` — `reactor.util.retry`: El import reactor.util.retry.Retry pertenece a reactor.util.retry, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/transaccionesfinancieras/adapter/in/web/TransaccionController.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/transaccionesfinancieras/adapter/in/web/TransaccionController.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/transaccionesfinancieras/adapter/out/external/BuroRiesgoAdapter.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/transaccionesfinancieras/adapter/out/external/BuroRiesgoAdapter.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/transaccionesfinancieras/adapter/out/external/BuroRiesgoAdapter.java` — `reactor.util.retry`: El import reactor.util.retry.Retry pertenece a reactor.util.retry, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/transaccionesfinancieras/adapter/out/external/MotorAntifraudeAdapter.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/transaccionesfinancieras/adapter/out/external/MotorAntifraudeAdapter.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/transaccionesfinancieras/adapter/out/external/MotorAntifraudeAdapter.java` — `reactor.util.retry`: El import reactor.util.retry.Retry pertenece a reactor.util.retry, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/transaccionesfinancieras/application/service/TransaccionService.java` — `com.transaccionesfinanciales.application`: El import com.transaccionesfinanciales.application.port.in.ProcesarTransaccionUseCase pertenece a com.transaccionesfinanciales.application, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/transaccionesfinancieras/application/service/TransaccionService.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/transaccionesfinancieras/application/service/TransaccionService.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/transaccionesfinancieras/application/service/TransaccionService.java` — `reactor.util.function`: El import reactor.util.function.Tuple2 pertenece a reactor.util.function, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/test/java/com/transaccionesfinancieras/application/service/TransaccionServiceTest.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/test/java/com/transaccionesfinancieras/adapter/in/web/TransaccionControllerTest.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/transaccionesfinancieras/Application.java` — `ApplicationProperties.getBuroRiesgoBaseUrl`: Se invoca `getBuroRiesgoBaseUrl` sobre `ApplicationProperties`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/transaccionesfinancieras/Application.java` — `ApplicationProperties.getBuroRiesgoTimeout`: Se invoca `getBuroRiesgoTimeout` sobre `ApplicationProperties`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/transaccionesfinancieras/domain/exception/TransaccionRechazadaException.java` — `MotivoRechazo.getDescripcion`: Se invoca `getDescripcion` sobre `MotivoRechazo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/transaccionesfinancieras/domain/exception/TransaccionRechazadaException.java` — `MotivoRechazo.name`: Se invoca `name` sobre `MotivoRechazo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/transaccionesfinancieras/adapter/in/web/TransaccionController.java` — `Transaccion.id`: Se invoca `id` sobre `Transaccion`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/transaccionesfinancieras/adapter/out/external/BuroRiesgoAdapter.java` — `Transaccion.idCliente`: Se invoca `idCliente` sobre `Transaccion`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/transaccionesfinancieras/adapter/out/external/BuroRiesgoAdapter.java` — `Transaccion.monto`: Se invoca `monto` sobre `Transaccion`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/transaccionesfinancieras/adapter/out/external/BuroRiesgoAdapter.java` — `Transaccion.tipoTransaccion`: Se invoca `tipoTransaccion` sobre `Transaccion`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/transaccionesfinancieras/adapter/out/external/MotorAntifraudeAdapter.java` — `Transaccion.id`: Se invoca `id` sobre `Transaccion`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/transaccionesfinancieras/application/service/TransaccionService.java` — `Transaccion.id`: Se invoca `id` sobre `Transaccion`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/transaccionesfinancieras/application/service/TransaccionService.java` — `Transaccion.idCliente`: Se invoca `idCliente` sobre `Transaccion`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/transaccionesfinancieras/application/service/TransaccionService.java` — `Transaccion.monto`: Se invoca `monto` sobre `Transaccion`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/transaccionesfinancieras/application/service/TransaccionService.java` — `Transaccion.moneda`: Se invoca `moneda` sobre `Transaccion`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/transaccionesfinancieras/application/service/TransaccionService.java` — `Transaccion.tipo`: Se invoca `tipo` sobre `Transaccion`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/transaccionesfinancieras/application/service/TransaccionService.java` — `Transaccion.descripcion`: Se invoca `descripcion` sobre `Transaccion`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/transaccionesfinancieras/application/service/TransaccionService.java` — `ResultadoEvaluacion.estado`: Se invoca `estado` sobre `ResultadoEvaluacion`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/transaccionesfinancieras/adapter/in/web/TransaccionControllerTest.java` — `ResultadoEvaluacion.getId`: Se invoca `getId` sobre `ResultadoEvaluacion`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

## Como saber que terminaste

```bash
mvn clean compile
```

Ese comando corriendo sin errores es la definicion de "listo".

---

```
## Briefing del reto (autoridad)
Este bloque manda sobre los archivos adjuntos. El stack y el rol salen de AQUÍ, no de un topic genérico ni de markdown placeholder.

### Perfil
Chapter Backend, Especialidad Desarrollador, Tecnología Java, Master

### Brecha de conocimiento
Implementa un paradigma de programación distinto al imperativo, como el paradigma reactivo o el paradigma funcional. Domina los cuatro pilares especificados en el manifiesto de sistemas reactivos, favoreciendo mejor rendimiento, una mayor escalabilidad y una mayor resiliencia. Conoce las ventajas, desventajas y operadores básicos en la implementación de este paradigma.

### Misión / candidato
Candidato con experiencia Master en Backend Java

### Reto
- Tema: Adopción de Paradigmas de Programación No Imperativos: Con Enfoque Reactivo y Funcional
- Seniority: master-l1
- Tipo: theoretical
- Título: Exploración y Análisis de Paradigmas No Imperativos en Sistemas Reactivos
- Tiempo estimado: 8 horas

### Fases (trabajo del HUMANO — PROHIBIDO completarlas)
No implementes estos entregables. Dejalos como hueco pedagógico. El asistente solo materializa el proyecto arrancable para que el participante pueda trabajar.
- Fase 1: Identificación de Requisitos y Restricciones — objetivo: Comprender los requisitos funcionales y no funcionales del sistema de procesamiento de transacciones, así como las restricciones operativas. — entregable (NO resolver): Documento que describe los actores, interacciones, umbrales y restricciones del sistema.
- Fase 2: Exploración de Paradigmas No Imperativos — objetivo: Evaluar los paradigmas reactivo y funcional en términos de sus ventajas, desventajas y operadores básicos. — entregable (NO resolver): Documento que describe los cuatro pilares de los sistemas reactivos, las ventajas y desventajas de los paradigmas reactivo y funcional, y los operadores básicos de cada uno.
- Fase 3: Análisis de Aplicación en el Dominio — objetivo: Analizar cómo los paradigmas no imperativos pueden aplicarse al dominio de las transacciones financieras. — entregable (NO resolver): Documento que propone una estrategia para implementar los paradigmas no imperativos en el sistema de procesamiento de transacciones, considerando los requisitos y restricciones identificados.

Eres un asistente experto en análisis, corrección y generación de archivos de cualquier tipo:
código fuente, documentación, hojas de cálculo, documentos Word, configuraciones, entre otros.
Voy a enviarte una cadena de texto que contiene uno o más archivos. Cada archivo está delimitado por un marcador con el siguiente formato:
// === ARCHIVO: ruta/del/archivo.extension ===
o también puede aparecer como:
## === ARCHIVO: ruta/del/archivo.extension ===
Lo que sigue al marcador puede ser:

El contenido real del archivo (código, texto, YAML, etc.)
Una descripción en lenguaje natural de lo que debe contener el archivo


TU TAREA
PASO 0 — ¿Esto es un proyecto o una carcasa?
Antes de extraer archivos, leé el Briefing (si está) y diagnosticá el adjunto.

Es CARCASA si ocurre CUALQUIERA de estas:
- No hay manifiesto de dependencias del stack del briefing (manifest.json de VTEX IO / package.json / pom.xml / build.gradle / requirements.txt / go.mod / *.tf / *.csproj, según corresponda)
- Hay un "binario" que en realidad es un comentario ("no puede ser mostrado como texto plano", placeholder .fig/.docx vacío)
- Los markdowns ya completan entregables de fases posteriores ("se implementó fade-in", lista de áreas ya resuelta)

Si es CARCASA:
- MATERIALIZÁ un proyecto que arranca en el stack del briefing (VTEX IO Store Framework, Angular, Terraform, pytest, Nest, etc.). Incluí manifiesto, punto de entrada y capa de interfaz reales.
- NO copies los markdowns de "solución" como si fueran el producto. Son ruido de generación.
- NO resuelvas las fases del briefing (están marcadas PROHIBIDO). Dejá el hueco pedagógico: el flujo existe, las microinteracciones/calidad/infra que el reto pide NO están hechas.
- Después seguí al PASO 5 (ZIP).

Si es un proyecto REAL (manifiesto + código que compila o arranca):
- Seguí PASO 1 en adelante. 🔴 compilación sí. 🟡 pedagógico no.

PASO 1 — Detección y extracción
Identifica todos los archivos presentes en la cadena. Para cada archivo extrae:

Su ruta completa (ej: src/main/java/com/pragma/Service.java)
Su contenido o descripción

PASO 2 — Clasificación por tipo
Clasifica cada archivo en una de estas categorías:
A) Código fuente (Java, Python, TypeScript, JavaScript, Kotlin, etc.)
B) Configuración / documentación (YAML, properties, Markdown, JSON, txt, etc.)
C) Excel (.xlsx, .xls, .csv)
D) Word (.docx, .doc)
E) Otro tipo de archivo binario o especial
PASO 3 — Clasificación de errores en código fuente

Objetivo prioritario: que el proyecto compile. No corrijas flujo de negocio ni lógica funcional.

Antes de modificar cualquier archivo de código fuente, clasifica cada problema encontrado en una de estas dos categorías:
🔴 ERROR DE COMPILACIÓN — corregir siempre
Son errores que impiden que el proyecto arranque, sin valor pedagógico:

Import faltante o incorrecto
Clase, método o variable referenciada que no existe en ningún archivo del proyecto
Error de sintaxis
Anotación con atributos inválidos
Dependencia ausente en pom.xml, package.json, etc.
Archivo referenciado que no existe y debe ser creado con implementación mínima

→ CORREGIR estos errores.
🟡 PROBLEMA FUNCIONAL O DE CALIDAD — preservar siempre
Son problemas que no impiden compilar. Pueden ser intencionales para el aprendizaje:

Clave secreta hardcodeada ("secret", "password123")
API deprecada que funciona pero tiene reemplazo moderno
Lógica de negocio incorrecta o incompleta
Código redundante o de baja legibilidad
Falta de validaciones en flujo de negocio
Patrones de diseño incorrectos pero funcionales
Concurrencia no segura
Configuración funcional pero no óptima

→ PRESERVAR tal cual. No corregir, no mejorar, no comentar.
PASO 4 — Procesamiento según tipo de archivo
Tipo A — Código fuente
Aplica únicamente las correcciones clasificadas como 🔴 ERROR DE COMPILACIÓN.
No alteres ningún elemento clasificado como 🟡 PROBLEMA FUNCIONAL O DE CALIDAD.
Si falta un archivo referenciado, créalo con la implementación mínima necesaria para compilar.
Tipo B — Configuración / documentación
Extrae el contenido tal cual, sin modificaciones salvo errores evidentes de sintaxis
(ej: YAML mal indentado).
Tipo C — Excel (.xlsx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un archivo Excel funcional con:

Fila de encabezados en negrita con color de fondo distintivo
Columnas con ancho ajustado al contenido
Tipos de dato correctos por columna
Validaciones si la descripción lo indica
Hojas nombradas descriptivamente si hay más de una
Filas de ejemplo si no hay datos reales

Tipo D — Word (.docx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un documento Word funcional con:

Estilos de título (Título 1, Título 2) para jerarquía de secciones
Fuente legible (Calibri o equivalente), tamaño 11-12pt para cuerpo
Márgenes estándar
Tabla de contenido si tiene múltiples secciones
Tablas con encabezados en negrita si aplica

Tipo E — Otro
Genera el archivo con el contenido o estructura más apropiada según la descripción.
PASO 5 — Exportación en ZIP
Empaqueta todos los archivos en un único archivo ZIP descargable respetando exactamente
la estructura de rutas indicada por los marcadores.
El ZIP debe incluir:

Archivos de código con únicamente los errores de compilación corregidos
Archivos de configuración y documentación sin cambios
Archivos nuevos creados para resolver dependencias de compilación faltantes
Archivos Excel y Word generados desde descripción

IMPORTANTE: El ZIP debe estar listo para descargar al finalizar. No preguntes si el usuario
quiere generarlo. Simplemente genera el archivo y proporciona el enlace de descarga; No debes desplegar en el chat el resumen de lo que arreglaste al Zip, solo entregalo.

REGLAS IMPORTANTES

No omitas ningún archivo aunque no tenga errores ni modificaciones
Respeta los nombres y rutas exactas indicadas por los marcadores
Si un archivo no tiene marcador claro, infiere el nombre desde su contenido
Si la cadena contiene solo documentación, placeholders o binarios fake, NO la reproduzcas:
aplicá PASO 0 (materializar el proyecto del briefing). Reproducir la carcasa es un fallo.
No agregues texto después del enlace de descarga del ZIP
No preguntes si el usuario quiere el ZIP: simplemente generalo siempre
Si detectas que falta un archivo de configuración necesario para compilar
(pom.xml, package.json, requirements.txt, build.gradle, etc.), créalo e inclúyelo
inferiendo su contenido desde los imports y frameworks detectados en el código
Nunca corrijas problemas 🟡 aunque parezcan obvios o fáciles de mejorar.
El participante que recibirá este proyecto los debe encontrar y resolver él mismo.


INPUT
Aquí está la cadena con los archivos:

// === ARCHIVO: pom.xml ===
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.5.6</version>
        <relativePath/>
    </parent>

    <groupId>com.transaccionesfinancieras</groupId>
    <artifactId>transacciones-financieras</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>transacciones-financieras</name>
    <description>Microservicio reactivo para procesamiento de transacciones financieras</description>

    <properties>
        <java.version>21</java.version>
        <resilience4j.version>2.2.0</resilience4j.version>
    </properties>

    <dependencies>
        <!-- Spring WebFlux -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-webflux</artifactId>
        </dependency>

        <!-- Actuator -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>

        <!-- Resilience4j -->
        <dependency>
            <groupId>io.github.resilience4j</groupId>
            <artifactId>resilience4j-spring-boot3</artifactId>
            <version>${resilience4j.version}</version>
        </dependency>
        <dependency>
            <groupId>io.github.resilience4j</groupId>
            <artifactId>resilience4j-reactor</artifactId>
            <version>${resilience4j.version}</version>
        </dependency>

        <!-- Testing -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>io.projectreactor</groupId>
            <artifactId>reactor-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-api</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.mockito</groupId>
            <artifactId>mockito-core</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <configuration>
                    <source>${java.version}</source>
                    <target>${java.version}</target>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>

// === ARCHIVO: src/main/java/com/transaccionesfinancieras/Application.java ===
package com.transaccionesfinancieras;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Hooks;

@SpringBootApplication
public class Application {
    private final ApplicationProperties properties;

    public Application(ApplicationProperties properties) {
        this.properties = properties;
        validateProperties();
    }

    public static void main(String[] args) {
        Hooks.onOperatorDebug();
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public WebClient webClient(WebClient.Builder builder) {
        return builder
                .baseUrl(properties.getBuroRiesgoBaseUrl())
                .defaultHeader("Content-Type", "application/json")
                .build();
    }

    private void validateProperties() {
        if (properties.getBuroRiesgoBaseUrl() == null || properties.getBuroRiesgoBaseUrl().isBlank()) {
            throw new IllegalStateException("La URL base del buró de riesgos no puede estar vacía");
        }
        if (properties.getBuroRiesgoTimeout() <= 0) {
            throw new IllegalStateException("El timeout del buró de riesgos debe ser positivo");
        }
    }

    // Clase interna para propiedades de configuración
    public static class ApplicationProperties {
        private String buroRiesgoBaseUrl;
        private int buroRiesgoTimeout;

        public String getBuroRiesgoBaseUrl() {
            return buroRiesgoBaseUrl;
        }

        public void setBuroRiesgoBaseUrl(String buroRiesgoBaseUrl) {
            this.buroRiesgoBaseUrl = buroRiesgoBaseUrl;
        }

        public int getBuroRiesgoTimeout() {
            return buroRiesgoTimeout;
        }

        public void setBuroRiesgoTimeout(int buroRiesgoTimeout) {
            this.buroRiesgoTimeout = buroRiesgoTimeout;
        }
    }
}

// === ARCHIVO: src/main/resources/application.yml ===
spring:
  application:
    name: transacciones-financieras
  profiles:
    active: dev

server:
  port: 8080
  netty:
    connection-timeout: 2s
    idle-timeout: 15s

management:
  endpoints:
    web:
      exposure:
        include: health,metrics,prometheus
  endpoint:
    health:
      show-details: always

resilience4j:
  circuitbreaker:
    configs:
      default:
        slidingWindowType: TIME_BASED
        slidingWindowSize: 10
        minimumNumberOfCalls: 5
        permittedNumberOfCallsInHalfOpenState: 3
        automaticTransitionFromOpenToHalfOpenEnabled: true
        waitDurationInOpenState: 5s
        failureRateThreshold: 50
        eventConsumerBufferSize: 10
        recordExceptions:
          - org.springframework.web.reactive.function.client.WebClientResponseException
          - java.util.concurrent.TimeoutException
          - com.transaccionesfinancieras.domain.exception.TimeoutBuroException
          - com.transaccionesfinancieras.domain.exception.Respuesta5xxException
    instances:
      buroRiesgo:
        baseConfig: default
        failureRateThreshold: 60
        waitDurationInOpenState: 10s
  retry:
    configs:
      default:
        maxAttempts: 3
        waitDuration: 500ms
        enableExponentialBackoff: true
        exponentialBackoffMultiplier: 2
        retryExceptions:
          - org.springframework.web.reactive.function.client.WebClientResponseException
          - java.util.concurrent.TimeoutException
          - com.transaccionesfinancieras.domain.exception.TimeoutBuroException
    instances:
      buroRiesgo:
        baseConfig: default
  bulkhead:
    configs:
      default:
        maxConcurrentCalls: 20
        maxWaitDuration: 500ms
    instances:
      buroRiesgo:
        baseConfig: default

app:
  buro-riesgo:
    base-url: http://localhost:8081/api
    timeout: 2000
  motor-antifraude:
    base-url: http://localhost:8082/api
    timeout: 1500

// === ARCHIVO: src/main/java/com/transaccionesfinancieras/application/port/in/ProcesarTransaccionUseCase.java ===
package com.transaccionesfinancieras.application.port.in;

import com.transaccionesfinancieras.domain.model.Transaccion;
import com.transaccionesfinancieras.domain.model.ResultadoEvaluacion;
import reactor.core.publisher.Mono;

/**
 * Caso de uso para procesar transacciones financieras.
 * Define la interfaz que el dominio expone para procesar una transacción,
 * incluyendo la evaluación de riesgos y antifraude de manera reactiva.
 */
public interface ProcesarTransaccionUseCase {

    /**
     * Procesa una transacción financiera evaluando su riesgo y potencial fraude.
     *
     * @param transaccion La transacción a procesar, incluyendo detalles como monto, origen y destino.
     * @return Mono<ResultadoEvaluacion> que emite el resultado de la evaluación una vez completada.
     *         El Mono puede emitir errores tipados como TimeoutBuroException, Respuesta5xxException
     *         o TransaccionRechazadaException dependiendo del escenario.
     */
    Mono<ResultadoEvaluacion> procesarTransaccion(Transaccion transaccion);

    /**
     * Consulta el estado actual de una transacción previamente procesada.
     *
     * @param idTransaccion Identificador único de la transacción.
     * @return Mono<ResultadoEvaluacion> que emite el estado actual de la transacción.
     *         Si la transacción no existe, emite un Mono vacío.
     */
    Mono<ResultadoEvaluacion> consultarEstadoTransaccion(String idTransaccion);
}

// === ARCHIVO: src/main/java/com/transaccionesfinancieras/application/port/out/BuroRiesgoPort.java ===
package com.transaccionesfinancieras.application.port.out;

import com.transaccionesfinancieras.domain.model.Transaccion;
import reactor.core.publisher.Mono;

/**
 * Puerto para interactuar con el buró de riesgos externo.
 * Define el contrato que la capa de infraestructura debe implementar para evaluar
 * el riesgo crediticio asociado a una transacción.
 */
public interface BuroRiesgoPort {

    /**
     * Evalúa el riesgo crediticio de una transacción consultando el buró de riesgos.
     *
     * @param transaccion La transacción a evaluar, incluyendo datos del cliente y monto.
     * @return Mono<Double> que emite la puntuación de riesgo (0.0 a 1.0) donde 1.0 es el riesgo máximo.
     *         Puede emitir errores como TimeoutBuroException si el buró no responde en el tiempo esperado,
     *         o Respuesta5xxException si el buró devuelve un error del servidor.
     */
    Mono<Double> evaluarRiesgo(Transaccion transaccion);

    /**
     * Recupera el historial de evaluaciones de riesgo para un cliente específico.
     *
     * @param idCliente Identificador único del cliente.
     * @return Mono<String> que emite un resumen del historial de riesgo en formato JSON.
     *         Si no hay historial, emite un Mono vacío.
     */
    Mono<String> obtenerHistorialRiesgo(String idCliente);
}

// === ARCHIVO: src/main/java/com/transaccionesfinancieras/application/port/out/MotorAntifraudePort.java ===
package com.transaccionesfinancieras.application.port.out;

import com.transaccionesfinancieras.domain.model.Transaccion;
import reactor.core.publisher.Mono;

/**
 * Puerto para interactuar con el motor antifraude externo.
 * Define el contrato que la capa de infraestructura debe implementar para evaluar
 * el potencial fraude en una transacción.
 */
public interface MotorAntifraudePort {

    /**
     * Evalúa una transacción en busca de patrones de fraude.
     *
     * @param transaccion La transacción a evaluar, incluyendo detalles como monto, origen, destino y timestamp.
     * @return Mono<Boolean> que emite true si la transacción es sospechosa de fraude, false en caso contrario.
     *         Puede emitir errores como TimeoutBuroException si el motor no responde en el tiempo esperado,
     *         o Respuesta5xxException si el motor devuelve un error del servidor.
     */
    Mono<Boolean> evaluarFraude(Transaccion transaccion);

    /**
     * Registra una transacción evaluada en el motor antifraude para mejorar futuras detecciones.
     *
     * @param transaccion La transacción evaluada, incluyendo su resultado de fraude.
     * @return Mono<Void> que completa cuando la transacción ha sido registrada.
     *         No emite valor, solo completa o emite error.
     */
    Mono<Void> registrarTransaccionEvaluada(Transaccion transaccion);
}

// === ARCHIVO: src/main/java/com/transaccionesfinancieras/domain/model/Transaccion.java ===
package com.transaccionesfinancieras.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Transaccion {
    private final String id;
    private final String idCliente;
    private final String idOriginador;
    private final BigDecimal monto;
    private final String moneda;
    private final String tipoTransaccion;
    private final String cuentaOrigen;
    private final String cuentaDestino;
    private final LocalDateTime fechaCreacion;
    private final LocalDateTime fechaProcesamiento;
    private final String estado;
    private final String descripcion;
    private final String canalOrigen;
    private final String ipOrigen;

    public Transaccion(String id, String idCliente, String idOriginador, BigDecimal monto, String moneda,
                       String tipoTransaccion, String cuentaOrigen, String cuentaDestino,
                       LocalDateTime fechaCreacion, LocalDateTime fechaProcesamiento, String estado,
                       String descripcion, String canalOrigen, String ipOrigen) {
        this.id = id != null ? id : UUID.randomUUID().toString();
        this.idCliente = idCliente;
        this.idOriginador = idOriginador;
        this.monto = monto;
        this.moneda = moneda;
        this.tipoTransaccion = tipoTransaccion;
        this.cuentaOrigen = cuentaOrigen;
        this.cuentaDestino = cuentaDestino;
        this.fechaCreacion = fechaCreacion != null ? fechaCreacion : LocalDateTime.now();
        this.fechaProcesamiento = fechaProcesamiento;
        this.estado = estado;
        this.descripcion = descripcion;
        this.canalOrigen = canalOrigen;
        this.ipOrigen = ipOrigen;
    }

    public String getId() {
        return id;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public String getIdOriginador() {
        return idOriginador;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public String getMoneda() {
        return moneda;
    }

    public String getTipoTransaccion() {
        return tipoTransaccion;
    }

    public String getCuentaOrigen() {
        return cuentaOrigen;
    }

    public String getCuentaDestino() {
        return cuentaDestino;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public LocalDateTime getFechaProcesamiento() {
        return fechaProcesamiento;
    }

    public String getEstado() {
        return estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getCanalOrigen() {
        return canalOrigen;
    }

    public String getIpOrigen() {
        return ipOrigen;
    }

    public boolean esMontoValido() {
        return monto != null && monto.compareTo(BigDecimal.ZERO) > 0;
    }

    public boolean esTransaccionMayorA(double umbral) {
        return monto != null && monto.doubleValue() > umbral;
    }

    public Transaccion conEstado(String nuevoEstado) {
        return new Transaccion(
            this.id, this.idCliente, this.idOriginador, this.monto, this.moneda,
            this.tipoTransaccion, this.cuentaOrigen, this.cuentaDestino,
            this.fechaCreacion, LocalDateTime.now(), nuevoEstado,
            this.descripcion, this.canalOrigen, this.ipOrigen
        );
    }

    public Transaccion conFechaProcesamiento(LocalDateTime fechaProcesamiento) {
        return new Transaccion(
            this.id, this.idCliente, this.idOriginador, this.monto, this.moneda,
            this.tipoTransaccion, this.cuentaOrigen, this.cuentaDestino,
            this.fechaCreacion, fechaProcesamiento, this.estado,
            this.descripcion, this.canalOrigen, this.ipOrigen
        );
    }
}

// === ARCHIVO: src/main/java/com/transaccionesfinancieras/domain/model/ResultadoEvaluacion.java ===
package com.transaccionesfinancieras.domain.model;

import java.time.LocalDateTime;
import java.util.List;

public class ResultadoEvaluacion {
    private final String idTransaccion;
    private final boolean aprobada;
    private final String codigoResultado;
    private final String mensaje;
    private final double puntuacionRiesgo;
    private final boolean fraudeDetectado;
    private final String idEvaluadorRiesgo;
    private final String idEvaluadorFraude;
    private final LocalDateTime fechaEvaluacion;
    private final List<String> razonesRechazo;
    private final double montoAprobado;
    private final String nivelRiesgo;

    public ResultadoEvaluacion(String idTransaccion, boolean aprobada, String codigoResultado,
                                String mensaje, double puntuacionRiesgo, boolean fraudeDetectado,
                                String idEvaluadorRiesgo, String idEvaluadorFraude,
                                LocalDateTime fechaEvaluacion, List<String> razonesRechazo,
                                double montoAprobado, String nivelRiesgo) {
        this.idTransaccion = idTransaccion;
        this.aprobada = aprobada;
        this.codigoResultado = codigoResultado;
        this.mensaje = mensaje;
        this.puntuacionRiesgo = puntuacionRiesgo;
        this.fraudeDetectado = fraudeDetectado;
        this.idEvaluadorRiesgo = idEvaluadorRiesgo;
        this.idEvaluadorFraude = idEvaluadorFraude;
        this.fechaEvaluacion = fechaEvaluacion != null ? fechaEvaluacion : LocalDateTime.now();
        this.razonesRechazo = razonesRechazo;
        this.montoAprobado = montoAprobado;
        this.nivelRiesgo = nivelRiesgo;
    }

    public String getIdTransaccion() {
        return idTransaccion;
    }

    public boolean isAprobada() {
        return aprobada;
    }

    public String getCodigoResultado() {
        return codigoResultado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public double getPuntuacionRiesgo() {
        return puntuacionRiesgo;
    }

    public boolean isFraudeDetectado() {
        return fraudeDetectado;
    }

    public String getIdEvaluadorRiesgo() {
        return idEvaluadorRiesgo;
    }

    public String getIdEvaluadorFraude() {
        return idEvaluadorFraude;
    }

    public LocalDateTime getFechaEvaluacion() {
        return fechaEvaluacion;
    }

    public List<String> getRazonesRechazo() {
        return razonesRechazo;
    }

    public double getMontoAprobado() {
        return montoAprobado;
    }

    public String getNivelRiesgo() {
        return nivelRiesgo;
    }

    public boolean requiereRevisionManual() {
        return puntuacionRiesgo >= 70.0 && puntuacionRiesgo < 90.0;
    }

    public boolean esAltoRiesgo() {
        return puntuacionRiesgo >= 90.0;
    }

    public boolean esMontoParcialmenteAprobado() {
        return montoAprobado > 0 && !aprobada;
    }

    public Resultado evaluarNuevoMonto(double nuevoMonto) {
        if (nuevoMonto <= 0) {
            return this;
        }
        return new ResultadoEvaluacion(
            this.idTransaccion,
            this.aprobada,
            this.codigoResultado,
            this.mensaje,
            this.puntuacionRiesgo,
            this.fraudeDetectado,
            this.idEvaluadorRiesgo,
            this.idEvaluadorFraude,
            this.fechaEvaluacion,
            this.razonesRechazo,
            nuevoMonto,
            this.nivelRiesgo
        );
    }

    public static ResultadoEvaluacion aprobacion(String idTransaccion, double puntuacionRiesgo,
                                                   String nivelRiesgo, String idEvaluadorRiesgo,
                                                   String idEvaluadorFraude, double montoAprobado) {
        return new ResultadoEvaluacion(
            idTransaccion, true, "APROBADA", "Transacción aprobada por riesgo y antifraude",
            puntuacionRiesgo, false, idEvaluadorRiesgo, idEvaluadorFraude,
            LocalDateTime.now(), null, montoAprobado, nivelRiesgo
        );
    }

    public static ResultadoEvaluacion rechazo(String idTransaccion, String codigo, String mensaje,
                                               List<String> razones, String idEvaluadorRiesgo,
                                               String idEvaluadorFraude, double puntuacionRiesgo) {
        return new ResultadoEvaluacion(
            idTransaccion, false, codigo, mensaje, puntuacionRiesgo, true,
            idEvaluadorRiesgo, idEvaluadorFraude, LocalDateTime.now(),
            razones, 0.0, "ALTO"
        );
    }

    public static ResultadoEvaluacion aprobacionParcial(String idTransaccion, double puntuacionRiesgo,
                                                          String nivelRiesgo, double montoAprobado,
                                                          List<String> razones, String idEvaluadorRiesgo,
                                                          String idEvaluadorFraude) {
        return new ResultadoEvaluacion(
            idTransaccion, false, "APROBADA_PARCIAL",
            "Transacción aprobada por monto menor al solicitado",
            puntuacionRiesgo, false, idEvaluadorRiesgo, idEvaluadorFraude,
            LocalDateTime.now(), razones, montoAprobado, nivelRiesgo
        );
    }

    public static class Resultado {
    }
}

// === ARCHIVO: src/main/java/com/transaccionesfinancieras/domain/exception/TimeoutBuroException.java ===
package com.transaccionesfinancieras.domain.exception;

public class TimeoutBuroException extends RuntimeException {
    private final String idTransaccion;
    private final String idCliente;
    private final long tiempoEsperaMs;
    private final String mensajeOriginal;

    public TimeoutBuroException(String mensaje) {
        super(mensaje);
        this.idTransaccion = null;
        this.idCliente = null;
        this.tiempoEsperaMs = 0;
        this.mensajeOriginal = mensaje;
    }

    public TimeoutBuroException(String mensaje, Throwable causa) {
        super(mensaje, causa);
        this.idTransaccion = null;
        this.idCliente = null;
        this.tiempoEsperaMs = 0;
        this.mensajeOriginal = mensaje;
    }

    public TimeoutBuroException(String idTransaccion, String idCliente, long tiempoEsperaMs) {
        super(String.format("Timeout al consultar buró de riesgos para transacción %s del cliente %s. " +
                           "Tiempo de espera excedido: %d ms", idTransaccion, idCliente, tiempoEsperaMs));
        this.idTransaccion = idTransaccion;
        this.idCliente = idCliente;
        this.tiempoEsperaMs = tiempoEsperaMs;
        this.mensajeOriginal = null;
    }

    public TimeoutBuroException(String idTransaccion, String idCliente, long tiempoEsperaMs, Throwable causa) {
        super(String.format("Timeout al consultar buró de riesgos para transacción %s del cliente %s. " +
                           "Tiempo de espera excedido: %d ms", idTransaccion, idCliente, tiempoEsperaMs), causa);
        this.idTransaccion = idTransaccion;
        this.idCliente = idCliente;
        this.tiempoEsperaMs = tiempoEsperaMs;
        this.mensajeOriginal = null;
    }

    public String getIdTransaccion() {
        return idTransaccion;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public long getTiempoEsperaMs() {
        return tiempoEsperaMs;
    }

    public String getMensajeOriginal() {
        return mensajeOriginal;
    }

    public boolean tieneDatosContexto() {
        return idTransaccion != null && idCliente != null;
    }

    public String getCodigoError() {
        return "TIMEOUT_BURO_001";
    }

    public String getSeverity() {
        return "HIGH";
    }

    public String getCategoria() {
        return "INFRAESTRUCTURA";
    }

    public boolean esRecuperable() {
        return true;
    }
}

// === ARCHIVO: src/main/java/com/transaccionesfinancieras/domain/exception/Respuesta5xxException.java ===
package com.transaccionesfinancieras.domain.exception;

import java.util.Map;

public class Respuesta5xxException extends RuntimeException {
    
    private final int codigoEstado;
    private final String mensajeError;
    private final String cuerpoRespuesta;
    private final String endpoint;
    private final Map<String, String> headers;

    public Respuesta5xxException(int codigoEstado, String mensajeError, String cuerpoRespuesta, 
                                   String endpoint, Map<String, String> headers) {
        super(String.format("Error %d del core bancario en endpoint %s: %s", codigoEstado, endpoint, mensajeError));
        this.codigoEstado = codigoEstado;
        this.mensajeError = mensajeError;
        this.cuerpoRespuesta = cuerpoRespuesta;
        this.endpoint = endpoint;
        this.headers = headers != null ? Map.copyOf(headers) : Map.of();
    }

    public Respuesta5xxException(int codigoEstado, String mensajeError, String endpoint) {
        this(codigoEstado, mensajeError, null, endpoint, null);
    }

    public int getCodigoEstado() {
        return codigoEstado;
    }

    public String getMensajeError() {
        return mensajeError;
    }

    public String getCuerpoRespuesta() {
        return cuerpoRespuesta;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public boolean isErrorServidor() {
        return codigoEstado >= 500 && codigoEstado < 600;
    }

    public boolean isErrorBaseDatos() {
        return cuerpoRespuesta != null && cuerpoRespuesta.toLowerCase().contains("database");
    }

    public boolean isErrorTimeout() {
        return cuerpoRespuesta != null && 
               (cuerpoRespuesta.toLowerCase().contains("timeout") || 
                cuerpoRespuesta.toLowerCase().contains("timeout exceeded"));
    }

    public String getDetalleTecnico() {
        StringBuilder sb = new StringBuilder();
        sb.append("Codigo: ").append(codigoEstado).append("\n");
        sb.append("Endpoint: ").append(endpoint).append("\n");
        sb.append("Mensaje: ").append(mensajeError).append("\n");
        if (cuerpoRespuesta != null && !cuerpoRespuesta.isBlank()) {
            sb.append("Cuerpo: ").append(cuerpoRespuesta).append("\n");
        }
        if (!headers.isEmpty()) {
            sb.append("Headers: ").append(headers).append("\n");
        }
        return sb.toString();
    }

    public static Respuesta5xxException desdeWebClientResponse(int statusCode, String body, String url) {
        String mensaje = body != null && !body.isBlank() ? body : "Error sin mensaje";
        return new Respuesta5xxException(statusCode, mensaje, body, url, null);
    }
}

// === ARCHIVO: src/main/java/com/transaccionesfinancieras/domain/exception/TransaccionRechazadaException.java ===
package com.transaccionesfinancieras.domain.exception;

import com.transaccionesfinancieras.domain.model.Transaccion;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

public class TransaccionRechazadaException extends RuntimeException {

    public enum MotivoRechazo {
        RIESGO_ALTO("Riesgo alto detectado"),
        FRAUDE_DETECTADO("Transaccion marcada como fraude"),
        MONTO_EXCEDIDO("Monto excede limite permitido"),
        CUENTA_BLOQUEADA("Cuenta bloqueada o inactiva"),
        SCORE_INSUFICIENTE("Score de riesgo insuficiente"),
        PATRON_SOSPECHOSO("Patron de comportamiento sospechoso"),
        RULE_ENGINE("Regla de negocio rechazada"),
        BLACKLIST("Entidad en lista negra"),
        VELOCIDAD_EXCESIVA("Demasiadas transacciones en periodo corto"),
        GEOLOCALIZACION_INVALIDA("Ubicacion geografica no valida");

        private final String descripcion;

        MotivoRechazo(String descripcion) {
            this.descripcion = descripcion;
        }

        public String getDescripcion() {
            return descripcion;
        }
    }

    private final String idTransaccion;
    private final MotivoRechazo motivo;
    private final BigDecimal monto;
    private final String idCliente;
    private final LocalDateTime timestamp;
    private final Double scoreRiesgo;
    private final Map<String, Object> metadatos;

    public TransaccionRechazadaException(String idTransaccion, MotivoRechazo motivo, 
                                          BigDecimal monto, String idCliente) {
        super(String.format("Transaccion %s rechazada por: %s - Monto: %s - Cliente: %s", 
                idTransaccion, motivo.getDescripcion(), monto, idCliente));
        this.idTransaccion = idTransaccion;
        this.motivo = motivo;
        this.monto = monto;
        this.idCliente = idCliente;
        this.timestamp = LocalDateTime.now();
        this.scoreRiesgo = null;
        this.metadatos = Map.of();
    }

    public TransaccionRechazadaException(String idTransaccion, MotivoRechazo motivo,
                                          BigDecimal monto, String idCliente, Double scoreRiesgo) {
        super(String.format("Transaccion %s rechazada por: %s (score: %.2f) - Monto: %s", 
                idTransaccion, motivo.getDescripcion(), scoreRiesgo, monto));
        this.idTransaccion = idTransaccion;
        this.motivo = motivo;
        this.monto = monto;
        this.idCliente = idCliente;
        this.timestamp = LocalDateTime.now();
        this.scoreRiesgo = scoreRiesgo;
        this.metadatos = Map.of("scoreRiesgo", scoreRiesgo);
    }

    public TransaccionRechazadaException(Transaccion transaccion, MotivoRechazo motivo, Double scoreRiesgo) {
        super(String.format("Transaccion %s rechazada por: %s", 
                transaccion.getId(), motivo.getDescripcion()));
        this.idTransaccion = transaccion.getId();
        this.motivo = motivo;
        this.monto = transaccion.getMonto();
        this.idCliente = transaccion.getIdCliente();
        this.timestamp = LocalDateTime.now();
        this.scoreRiesgo = scoreRiesgo;
        this.metadatos = Map.of("scoreRiesgo", scoreRiesgo != null ? scoreRiesgo : 0.0);
    }

    public String getIdTransaccion() {
        return idTransaccion;
    }

    public MotivoRechazo getMotivo() {
        return motivo;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public Double getScoreRiesgo() {
        return scoreRiesgo;
    }

    public Map<String, Object> getMetadatos() {
        return metadatos;
    }

    public boolean esPorRiesgo() {
        return motivo == MotivoRechazo.RIESGO_ALTO || 
               motivo == MotivoRechazo.SCORE_INSUFICIENTE ||
               motivo == MotivoRechazo.PATRON_SOSPECHOSO;
    }

    public boolean esPorFraude() {
        return motivo == MotivoRechazo.FRAUDE_DETECTADO ||
               motivo == MotivoRechazo.BLACKLIST ||
               motivo == MotivoRechazo.VELOCIDAD_EXCESIVA;
    }

    public String getCodigoError() {
        return "TXN_" + motivo.name();
    }
}

// === ARCHIVO: src/main/java/com/transaccionesfinancieras/config/WebClientConfig.java ===
package com.transaccionesfinancieras.config;

import com.transaccionesfinancieras.domain.exception.Respuesta5xxException;
import com.transaccionesfinancieras.domain.exception.TimeoutBuroException;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.retry.RetryRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.function.Function;

@Configuration
public class WebClientConfig {

    private static final Logger log = LoggerFactory.getLogger(WebClientConfig.class);
    private static final int TIMEOUT_CONNECT_MS = 3000;
    private static final int TIMEOUT_RESPONSE_MS = 5000;
    private static final int MAX_REINTENTOS = 3;
    private static final Duration DURACION_REINTENTO = Duration.ofMillis(500);

    @Bean
    public WebClient webClientCoreBancario(WebClient.Builder builder) {
        return builder
                .baseUrl("http://localhost:8080")
                .defaultHeader("Content-Type", "application/json")
                .defaultHeader("Accept", "application/json")
                .filter(logRequest())
                .filter(logResponse())
                .filter(errorHandler())
                .clientConnector(new reactor.netty.http.client.HttpClient()
                        .responseTimeout(Duration.ofMillis(TIMEOUT_RESPONSE_MS))
                        .connectTimeout(Duration.ofMillis(TIMEOUT_CONNECT_MS)))
                .build();
    }

    @Bean
    public WebClient webClientBuroRiesgo(WebClient.Builder builder) {
        return builder
                .baseUrl("http://localhost:8081")
                .defaultHeader("Content-Type", "application/json")
                .defaultHeader("X-Service", "transacciones-financieras")
                .filter(logRequest())
                .filter(errorHandlerBuro())
                .clientConnector(new reactor.netty.http.client.HttpClient()
                        .responseTimeout(Duration.ofMillis(TIMEOUT_RESPONSE_MS))
                        .connectTimeout(Duration.ofMillis(TIMEOUT_CONNECT_MS)))
                .build();
    }

    @Bean
    public WebClient webClientAntifraude(WebClient.Builder builder) {
        return builder
                .baseUrl("http://localhost:8082")
                .defaultHeader("Content-Type", "application/json")
                .filter(logRequest())
                .filter(errorHandler())
                .clientConnector(new reactor.netty.http.client.HttpClient()
                        .responseTimeout(Duration.ofMillis(TIMEOUT_RESPONSE_MS))
                        .connectTimeout(Duration.ofMillis(TIMEOUT_CONNECT_MS)))
                .build();
    }

    private ExchangeFilterFunction logRequest() {
        return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
            log.debug("Request: {} {}", clientRequest.method(), clientRequest.url());
            clientRequest.headers().forEach((name, values) -> 
                    log.trace("Header {}: {}", name, values));
            return Mono.just(clientRequest);
        });
    }

    private ExchangeFilterFunction logResponse() {
        return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
            log.debug("Response status: {}", clientResponse.statusCode());
            return Mono.just(clientResponse);
        });
    }

    private ExchangeFilterFunction errorHandler() {
        return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
            if (clientResponse.statusCode().is5xxServerError()) {
                return clientResponse.bodyToMono(String.class)
                        .flatMap(body -> {
                            String mensaje = String.format("Error %d del core: %s", 
                                    clientResponse.statusCode().value(), body);
                            log.error("Error 5xx recibido del servicio externo: {}", mensaje);
                            return Mono.error(new Respuesta5xxException(
                                    clientResponse.statusCode().value(),
                                    mensaje,
                                    body,
                                    clientResponse.url().toString(),
                                    obtenerHeaders(clientResponse)));
                        });
            }
            if (clientResponse.statusCode().value() == 429) {
                String retryAfter = clientResponse.headers().asHttpHeaders().getFirst("Retry-After");
                log.warn("Rate limit excedido. Retry-After: {}", retryAfter);
            }
            return Mono.just(clientResponse);
        });
    }

    private ExchangeFilterFunction errorHandlerBuro() {
        return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
            int statusCode = clientResponse.statusCode().value();
            if (statusCode >= 500) {
                return clientResponse.bodyToMono(String.class)
                        .flatMap(body -> {
                            if (body.toLowerCase().contains("timeout") || 
                                body.toLowerCase().contains("deadline")) {
                                log.error("Timeout detectado en el buró de riesgo");
                                return Mono.error(new TimeoutBuroException(
                                        "Timeout en el buró de riesgo", 
                                        clientResponse.url().toString()));
                            }
                            log.error("Error {} del buró de riesgo: {}", statusCode, body);
                            return Mono.error(new Respuesta5xxException(
                                    statusCode, body, clientResponse.url().toString()));
                        });
            }
            if (statusCode >= 400 && statusCode < 500) {
                return clientResponse.bodyToMono(String.class)
                        .flatMap(body -> {
                            log.warn("Error {} del buró de riesgo: {}", statusCode, body);
                            return Mono.error(new Respuesta5xxException(
                                    statusCode, body, clientResponse.url().toString()));
                        });
            }
            return Mono.just(clientResponse);
        });
    }

    private java.util.Map<String, String> obtenerHeaders(ClientResponse response) {
        java.util.Map<String, String> headers = new java.util.HashMap<>();
        response.headers().asHttpHeaders().forEach((key, values) -> 
                headers.put(key, String.join(", ", values)));
        return headers;
    }

    @Bean
    public CircuitBreakerRegistry circuitBreakerRegistry() {
        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
                .failureRateThreshold(50)
                .waitDurationInOpenState(Duration.ofSeconds(30))
                .slidingWindowSize(10)
                .minimumNumberOfCalls(5)
                .permittedNumberOfCallsInHalfOpenState(3)
                .automaticTransitionFromOpenToHalfOpenEnabled(true)
                .ignoreExceptions(TimeoutBuroException.class)
                .build();
        return CircuitBreakerRegistry.of(config);
    }

    @Bean
    public RetryRegistry retryRegistry() {
        RetryConfig config = RetryConfig.custom()
                .maxAttempts(MAX_REINTENTOS)
                .waitDuration(DURACION_REINTENTO)
                .retryExceptions(TimeoutBuroException.class, 
                                org.springframework.web.reactive.function.client.WebClientRequestException.class)
                .ignoreExceptions(Respuesta5xxException.class)
                .build();
        return RetryRegistry.of(config);
    }

    public static class RetrySpec {
        public static Retry withDefaultBackoff() {
            return Retry.backoff(MAX_REINTENTOS, DURACION_REINTENTO)
                    .filter(e -> e instanceof TimeoutBuroException || 
                                 e instanceof org.springframework.web.reactive.function.client.WebClientRequestException);
        }

        public static Retry withExponentialBackoff(int maxAttempts, Duration initial) {
            return Retry.backoff(maxAttempts, initial)
                    .filter(e -> e instanceof TimeoutBuroException ||
                                 e instanceof org.springframework.web.reactive.function.client.WebClientRequestException)
                    .doBeforeRetry(signal -> 
                            log.warn("Reintentando operación. Intento {} por: {}", 
                                    signal.totalRetries() + 1, signal.failure().getMessage()));
        }
    }
}

// === ARCHIVO: src/main/java/com/transaccionesfinancieras/config/Resilience4jConfig.java ===
package com.transaccionesfinancieras.config;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.retry.RetryRegistry;
import io.github.resilience4j.bulkhead.BulkheadConfig;
import io.github.resilience4j.bulkhead.BulkheadRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class Resilience4jConfig {

    @Bean
    public CircuitBreakerRegistry circuitBreakerRegistry() {
        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
                .failureRateThreshold(50)
                .waitDurationInOpenState(Duration.ofSeconds(30))
                .slidingWindowSize(10)
                .minimumNumberOfCalls(5)
                .permittedNumberOfCallsInHalfOpenState(3)
                .automaticTransitionFromOpenToHalfOpenEnabled(true)
                .build();
        return CircuitBreakerRegistry.of(config);
    }

    @Bean
    public RetryRegistry retryRegistry() {
        RetryConfig config = RetryConfig.custom()
                .maxAttempts(3)
                .waitDuration(Duration.ofMillis(500))
                .build();
        return RetryRegistry.of(config);
    }

    @Bean
    public BulkheadRegistry bulkheadRegistry() {
        BulkheadConfig config = BulkheadConfig.custom()
                .maxConcurrentCalls(100)
                .maxWaitDuration(Duration.ofMillis(200))
                .build();
        return BulkheadRegistry.of(config);
    }
}

// === ARCHIVO: src/main/java/com/transaccionesfinancieras/adapter/in/web/TransaccionController.java ===
package com.transaccionesfinancieras.adapter.in.web;

import com.transaccionesfinancieras.application.port.in.ProcesarTransaccionUseCase;
import com.transaccionesfinancieras.domain.model.ResultadoEvaluacion;
import com.transaccionesfinancieras.domain.model.Transaccion;
import com.transaccionesfinancieras.domain.exception.TransaccionRechazadaException;
import com.transaccionesfinancieras.domain.exception.TimeoutBuroException;
import com.transaccionesfinancieras.domain.exception.Respuesta5xxException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/transacciones")
public class TransaccionController {

    private static final Logger log = LoggerFactory.getLogger(TransaccionController.class);
    private final ProcesarTransaccionUseCase procesarTransaccionUseCase;

    public TransaccionController(ProcesarTransaccionUseCase procesarTransaccionUseCase) {
        this.procesarTransaccionUseCase = procesarTransaccionUseCase;
    }

    @PostMapping("/procesar")
    public Mono<ResponseEntity<ResultadoEvaluacion>> procesarTransaccion(@RequestBody Transaccion transaccion) {
        log.info("Recibida solicitud de procesamiento para transaccion: {}", transaccion.id());
        
        return procesarTransaccionUseCase.procesarTransaccion(transaccion)
                .map(resultado -> {
                    log.info("Transaccion {} procesada con estado: {}", transaccion.id(), resultado.estado());
                    if ("APROBADA".equals(resultado.estado())) {
                        return ResponseEntity.ok(resultado);
                    } else {
                        return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(resultado);
                    }
                })
                .onErrorResume(TransaccionRechazadaException.class, e -> {
                    log.warn("Transaccion rechazada por regla de negocio: {}", e.getMessage());
                    return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .body(ResultadoEvaluacion.error(transaccion.id(), e.getMessage())));
                })
                .onErrorResume(TimeoutBuroException.class, e -> {
                    log.error("Timeout al consultar buró de riesgo para transaccion: {}", transaccion.id());
                    return Mono.just(ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT)
                            .body(ResultadoEvaluacion.error(transaccion.id(), "Timeout del servicio de riesgo")));
                })
                .onErrorResume(Respuesta5xxException.class, e -> {
                    log.error("Error 5xx del servicio de riesgo para transaccion: {}", transaccion.id());
                    return Mono.just(ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                            .body(ResultadoEvaluacion.error(transaccion.id(), "Error en el servicio de riesgo")));
                })
                .onErrorResume(Exception.class, e -> {
                    log.error("Error inesperado procesando transaccion: {}", transaccion.id(), e);
                    return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body(ResultadoEvaluacion.error(transaccion.id(), "Error interno del servidor")));
                });
    }

    @GetMapping("/{idTransaccion}")
    public Mono<ResponseEntity<ResultadoEvaluacion>> consultarEstado(@PathVariable String idTransaccion) {
        log.info("Consultando estado de transaccion: {}", idTransaccion);
        
        return procesarTransaccionUseCase.consultarEstadoTransaccion(idTransaccion)
                .map(resultado -> ResponseEntity.ok(resultado))
                .onErrorResume(Exception.class, e -> {
                    log.error("Error consultando estado de transaccion: {}", idTransaccion, e);
                    return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND)
                            .body(ResultadoEvaluacion.error(idTransaccion, "Transaccion no encontrada")));
                });
    }

    @GetMapping("/health")
    public Mono<ResponseEntity<Map<String, String>>> healthCheck() {
        return Mono.just(ResponseEntity.ok(Map.of("status", "UP", "service", "transacciones-financieras")));
    }
}

// === ARCHIVO: src/main/java/com/transaccionesfinancieras/adapter/out/external/BuroRiesgoAdapter.java ===
package com.transaccionesfinancieras.adapter.out.external;

import com.transaccionesfinancieras.application.port.out.BuroRiesgoPort;
import com.transaccionesfinancieras.domain.exception.TimeoutBuroException;
import com.transaccionesfinancieras.domain.exception.Respuesta5xxException;
import com.transaccionesfinancieras.domain.model.Transaccion;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.Map;

@Component
public class BuroRiesgoAdapter implements BuroRiesgoPort {

    private static final Logger log = LoggerFactory.getLogger(BuroRiesgoAdapter.class);
    private static final String BURO_RIESGO_BASE_URL = "http://localhost:8081/api/buro-riesgo";
    private static final Duration TIMEOUT = Duration.ofSeconds(3);

    private final WebClient webClient;

    public BuroRiesgoAdapter(@Qualifier("buroRiesgoWebClient") WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    @CircuitBreaker(name = "buroRiesgoCircuitBreaker", fallbackMethod = "evaluarRiesgoFallback")
    @Retry(name = "buroRiesgoRetry")
    @Bulkhead(name = "buroRiesgoBulkhead")
    public Mono<Double> evaluarRiesgo(Transaccion transaccion) {
        log.debug("Evaluando riesgo para cliente: {} con monto: {}", transaccion.idCliente(), transaccion.monto());
        
        return webClient
                .post()
                .uri("/evaluar")
                .bodyValue(Map.of(
                        "idCliente", transaccion.idCliente(),
                        "monto", transaccion.monto(),
                        "tipoTransaccion", transaccion.tipoTransaccion()
                ))
                .retrieve()
                .onStatus(HttpStatusCode::is5xxServerError, response -> {
                    log.error("Respuesta 5xx del buró de riesgo: {}", response.statusCode());
                    return Mono.error(new Respuesta5xxException("Error del servidor del buró de riesgo"));
                })
                .bodyToMono(Map.class)
                .timeout(TIMEOUT)
                .map(response -> {
                    Object riesgo = response.get("nivelRiesgo");
                    log.info("Riesgo evaluado para cliente {}: {}", transaccion.idCliente(), riesgo);
                    if (riesgo instanceof Number) {
                        return ((Number) riesgo).doubleValue();
                    }
                    return parseDoubleOrDefault(riesgo.toString(), 0.5);
                })
                .doOnError(WebClientResponseException.NotFound.class, e -> {
                    log.warn("Cliente {} no encontrado en buró de riesgo", transaccion.idCliente());
                })
                .onErrorResume(WebClientResponseException.class, e -> {
                    log.error("Error de cliente web al consultar buró de riesgo: {}", e.getMessage());
                    if (e.getStatusCode().is5xxServerError()) {
                        return Mono.error(new Respuesta5xxException("Error del servidor del buró de riesgo"));
                    }
                    return Mono.just(0.5);
                });
    }

    @Override
    @CircuitBreaker(name = "buroRiesgoCircuitBreaker", fallbackMethod = "obtenerHistorialFallback")
    @Retry(name = "buroRiesgoRetry")
    @Bulkhead(name = "buroRiesgoBulkhead")
    public Mono<String> obtenerHistorialRiesgo(String idCliente) {
        log.debug("Obteniendo historial de riesgo para cliente: {}", idCliente);
        
        return webClient
                .get()
                .uri("/historial/{idCliente}", idCliente)
                .retrieve()
                .onStatus(HttpStatusCode::is5xxServerError, response -> {
                    log.error("Respuesta 5xx al obtener historial: {}", response.statusCode());
                    return Mono.error(new Respuesta5xxException("Error del servidor del buró de riesgo"));
                })
                .bodyToMono(Map.class)
                .timeout(TIMEOUT)
                .map(response -> {
                    Object historial = response.get("historial");
                    return historial != null ? historial.toString() : "SIN_HISTORIAL";
                })
                .onErrorResume(Exception.class, e -> {
                    log.error("Error al obtener historial de riesgo para cliente: {}", idCliente, e);
                    return Mono.just("ERROR_OBTENIENDO_HISTORIAL");
                });
    }

    private Mono<Double> evaluarRiesgoFallback(Transaccion transaccion, Throwable e) {
        log.warn("Fallback activado para evaluarRiesgo. Cliente: {}. Error: {}", transaccion.idCliente(), e.getMessage());
        if (e instanceof TimeoutBuroException || e.getCause() instanceof java.util.concurrent.TimeoutException) {
            log.error("Timeout en el fallback de buró de riesgo");
            return Mono.error(new TimeoutBuroException("Timeout al evaluar riesgo (fallback)"));
        }
        return Mono.just(0.5);
    }

    private Mono<String> obtenerHistorialFallback(String idCliente, Throwable e) {
        log.warn("Fallback activado para obtenerHistorialRiesgo. Cliente: {}. Error: {}", idCliente, e.getMessage());
        return Mono.just("HISTORIAL_NO_DISPONIBLE");
    }

    private double parseDoubleOrDefault(String value, double defaultValue) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            log.warn("No se pudo parsear el valor '{}' a double, usando valor por defecto: {}", value, defaultValue);
            return defaultValue;
        }
    }
}

// === ARCHIVO: src/main/java/com/transaccionesfinancieras/adapter/out/external/MotorAntifraudeAdapter.java ===
package com.transaccionesfinancieras.adapter.out.external;


import com.transaccionesfinancieras.domain.model.Resultado;
import com.transaccionesfinancieras.application.port.out.MotorAntifraudePort;
import com.transaccionesfinancieras.domain.exception.Respuesta5xxException;
import com.transaccionesfinancieras.domain.model.Transaccion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;

@Component
public class MotorAntifraudeAdapter implements MotorAntifraudePort {

    private static final Logger log = LoggerFactory.getLogger(MotorAntifraudeAdapter.class);
    private static final String SERVICIO_ANTIFRAUDE = "motor-antifraude";
    private static final String ENDPOINT_EVALUAR = "/evaluar";
    private static final String ENDPOINT_REGISTRAR = "/registrar";

    private final WebClient webClient;
    private final int timeoutSegundos;
    private final int reintentos;
    private final long duracionReintentoMs;

    public MotorAntifraudeAdapter(
            @Qualifier("webClientAntifraude") WebClient webClient,
            @Qualifier("timeoutAntifraude") int timeoutSegundos,
            @Qualifier("reintentosAntifraude") int reintentos,
            @Qualifier("duracionReintentoAntifraude") long duracionReintentoMs) {
        this.webClient = webClient;
        this.timeoutSegundos = timeoutSegundos;
        this.reintentos = reintentos;
        this.duracionReintentoMs = duracionReintentoMs;
    }

    @Override
    public Mono<Boolean> evaluarFraude(Transaccion transaccion) {
        log.info("Evaluando fraude para transacción: {}", transaccion.id());
        
        return webClient
                .post()
                .uri(ENDPOINT_EVALUAR)
                .bodyValue(transaccion)
                .retrieve()
                .bodyToMono(Boolean.class)
                .timeout(Duration.ofSeconds(timeoutSegundos))
                .doOnSuccess(resultado -> log.info("Resultado evaluación fraude: {} para tx: {}", resultado, transaccion.id()))
                .doOnError(error -> log.error("Error en evaluación de fraude para tx {}: {}", transaccion.id(), error.getMessage()))
                .onErrorResume(WebClientResponseException.class, this::manejarErrorRespuesta)
                .onErrorResume(TimeoutException.class, this::manejarTimeout)
                .retryWhen(Retry.backoff(reintentos, Duration.ofMillis(duracionReintentoMs))
                        .filter(throwable -> throwable instanceof WebClientResponseException serverError 
                                && serverError.getStatusCode().is5xxServerError()))
                .defaultIfEmpty(Boolean.FALSE);
    }

    @Override
    public Mono<Void> registrarTransaccionEvaluada(Transaccion transaccion) {
        log.info("Registrando transacción evaluada: {}", transaccion.id());
        
        return webClient
                .post()
                .uri(ENDPOINT_REGISTRAR)
                .bodyValue(transaccion)
                .retrieve()
                .bodyToMono(Void.class)
                .timeout(Duration.ofSeconds(timeoutSegundos))
                .doOnSuccess(aVoid -> log.info("Transacción registrada exitosamente: {}", transaccion.id()))
                .doOnError(error -> log.error("Error al registrar tx {}: {}", transaccion.id(), error.getMessage()))
                .onErrorResume(WebClientResponseException.class, this::manejarErrorRespuesta)
                .onErrorResume(TimeoutException.class, this::manejarTimeout);
    }

    private Mono<Boolean> manejarErrorRespuesta(WebClientResponseException ex) {
        HttpStatusCode status = ex.getStatusCode();
        log.error("Respuesta de error del servicio {}: {} - {}", SERVICIO_ANTIFRAUDE, status.value(), ex.getResponseBodyAsString());
        
        if (status.is5xxServerError()) {
            return Mono.error(new Respuesta5xxException(
                    SERVICIO_ANTIFRAUDE, 
                    status.value(), 
                    ex.getMessage()));
        }
        return Mono.error(ex);
    }

    private Mono<Boolean> manejarTimeout(TimeoutException ex) {
        log.error("Timeout al conectar con servicio {}", SERVICIO_ANTIFRAUDE);
        return Mono.error(ex);
    }
}

// === ARCHIVO: src/main/java/com/transaccionesfinancieras/application/service/TransaccionService.java ===
package com.transaccionesfinancieras.application.service;

import com.transaccionesfinanciales.application.port.in.ProcesarTransaccionUseCase;
import com.transaccionesfinancieras.application.port.out.BuroRiesgoPort;
import com.transaccionesfinancieras.application.port.out.MotorAntifraudePort;
import com.transaccionesfinancieras.domain.exception.Respuesta5xxException;
import com.transaccionesfinancieras.domain.exception.TimeoutBuroException;
import com.transaccionesfinancieras.domain.exception.TransaccionRechazadaException;
import com.transaccionesfinancieras.domain.model.ResultadoEvaluacion;
import com.transaccionesfinancieras.domain.model.Transaccion;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class TransaccionService implements ProcesarTransaccionUseCase {

    private static final Logger log = LoggerFactory.getLogger(TransaccionService.class);
    private static final double UMBRAL_RIESGO_ALTO = 0.75;
    private static final double UMBRAL_RIESGO_MEDIO = 0.40;
    private static final String ESTADO_APROBADA = "APROBADA";
    private static final String ESTADO_RECHAZADA = "RECHAZADA";
    private static final String ESTADO_PENDIENTE = "PENDIENTE";
    private static final String ESTADO_FALLIDA = "FALLIDA";

    private final BuroRiesgoPort buróRiesgoPort;
    private final MotorAntifraudePort motorAntifraudePort;

    public TransaccionService(BuroRiesgoPort buróRiesgoPort, MotorAntifraudePort motorAntifraudePort) {
        this.buróRiesgoPort = buróRiesgoPort;
        this.motorAntifraudePort = motorAntifraudePort;
    }

    @Override
    @CircuitBreaker(name = "transaccionCircuitBreaker", fallbackMethod = "procesarTransaccionFallback")
    @Retry(name = "transaccionRetry")
    public Mono<ResultadoEvaluacion> procesarTransaccion(Transaccion transaccion) {
        log.info("Iniciando procesamiento de transacción: {}", transaccion.id());
        
        String idTransaccion = transaccion.id() != null ? transaccion.id() : UUID.randomUUID().toString();
        Transaccion transaccionConId = new Transaccion(
                idTransaccion,
                transaccion.idCliente(),
                transaccion.monto(),
                transaccion.moneda(),
                transaccion.tipo(),
                transaccion.descripcion(),
                LocalDateTime.now()
        );

        return evaluarRiesgoYFraude(transaccionConId)
                .flatMap(this::determinarResultado)
                .doOnSuccess(resultado -> log.info("Transacción {} procesada con estado: {}", idTransaccion, resultado.estado()))
                .doOnError(error -> log.error("Error procesando transacción {}: {}", idTransaccion, error.getMessage()));
    }

    private Mono<Tuple2<Double, Boolean>> evaluarRiesgoYFraude(Transaccion transaccion) {
        Mono<Double> evaluacionRiesgo = buróRiesgoPort.evaluarRiesgo(transaccion)
                .doOnNext(riesgo -> log.debug("Riesgo evaluado para tx {}: {}", transaccion.id(), riesgo));

        Mono<Boolean> evaluacionFraude = motorAntifraudePort.evaluarFraude(transaccion)
                .doOnNext(fraude -> log.debug("Fraude evaluado para tx {}: {}", transaccion.id(), fraude));

        return Mono.zip(evaluacionRiesgo, evaluacionFraude)
                .timeout(Duration.ofSeconds(3))
                .switchIfEmpty(Mono.error(new TimeoutBuroException(transaccion.id())));
    }

    private Mono<ResultadoEvaluacion> determinarResultado(Tuple2<Double, Boolean> evaluaciones) {
        Double riesgo = evaluations.getT1();
        Boolean esFraude = evaluations.getT2();

        if (Boolean.TRUE.equals(esFraude)) {
            return construirResultado(ESTADO_RECHAZADA, "Transacción rechazada por detección de fraude", riesgo, esFraude);
        }

        if (riesgo >= UMBRAL_RIESGO_ALTO) {
            return construirResultado(ESTADO_RECHAZADA, "Riesgo demasiado alto para aprobar la transacción", riesgo, esFraude);
        }

        if (riesgo >= UMBRAL_RIESGO_MEDIO) {
            return construirResultado(ESTADO_PENDIENTE, "Transacción requiere revisión manual", riesgo, esFraude);
        }

        return construirResultado(ESTADO_APROBADA, "Transacción aprobada", riesgo, esFraude);
    }

    private Mono<ResultadoEvaluacion> construirResultado(String estado, String mensaje, Double riesgo, Boolean esFraude) {
        ResultadoEvaluacion resultado = new ResultadoEvaluacion(
                UUID.randomUUID().toString(),
                estado,
                mensaje,
                riesgo,
                esFraude,
                LocalDateTime.now()
        );
        return Mono.just(resultado);
    }

    private Mono<ResultadoEvaluacion> procesarTransaccionFallback(Transaccion transaccion, Throwable error) {
        log.warn("Fallback activado para transacción {}: {}", transaccion.id(), error.getMessage());
        
        String estado fallback;
        String mensaje;
        
        if (error instanceof TimeoutBuroException) {
            estado = ESTADO_PENDIENTE;
            mensaje = "Transacción en revisión por timeout del buró de riesgo";
        } else if (error instanceof Respuesta5xxException) {
            estado = ESTADO_PENDIENTE;
            mensaje = "Transacción en revisión por error en servicio externo";
        } else if (error instanceof TransaccionRechazadaException) {
            estado = ESTADO_RECHAZADA;
            mensaje = error.getMessage();
        } else {
            estado = ESTADO_FALLIDA;
            mensaje = "Error interno al procesar la transacción";
        }

        ResultadoEvaluacion resultadoFallback = new ResultadoEvaluacion(
                transaccion.id() != null ? transaccion.id() : UUID.randomUUID().toString(),
                estado,
                mensaje,
                -1.0,
                false,
                LocalDateTime.now()
        );

        return Mono.just(resultadoFallback);
    }

    @Override
    public Mono<ResultadoEvaluacion> consultarEstadoTransaccion(String idTransaccion) {
        log.info("Consultando estado de transacción: {}", idTransaccion);
        return buróRiesgoPort.obtenerHistorialRiesgo(idTransaccion)
                .flatMap(historial -> {
                    ResultadoEvaluacion resultado = new ResultadoEvaluacion(
                            idTransaccion,
                            ESTADO_PENDIENTE,
                            "Estado consultado desde historial",
                            0.0,
                            false,
                            LocalDateTime.now()
                    );
                    return Mono.just(resultado);
                })
                .switchIfEmpty(Mono.error(new TransaccionRechazadaException(
                        "Transacción no encontrada: " + idTransaccion)));
    }
}

// === ARCHIVO: src/test/java/com/transaccionesfinancieras/application/service/TransaccionServiceTest.java ===
package com.transaccionesfinancieras.application.service;

import com.transaccionesfinancieras.application.port.out.BuroRiesgoPort;
import com.transaccionesfinancieras.application.port.out.MotorAntifraudePort;
import com.transaccionesfinancieras.domain.exception.Respuesta5xxException;
import com.transaccionesfinancieras.domain.exception.TimeoutBuroException;
import com.transaccionesfinancieras.domain.exception.TransaccionRechazadaException;
import com.transaccionesfinancieras.domain.model.ResultadoEvaluacion;
import com.transaccionesfinancieras.domain.model.Transaccion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tests unitarios para TransaccionService")
class TransaccionServiceTest {

    @Mock
    private BuroRiesgoPort burOriesgoPort;

    @Mock
    private MotorAntifraudePort motorAntifraudePort;

    private TransaccionService transaccionService;

    private Transaccion transaccionValida;
    private ResultadoEvaluacion resultadoAprobado;
    private ResultadoEvaluacion resultadoRechazado;

    @BeforeEach
    void setUp() {
        transaccionService = new TransaccionService(burOriesgoPort, motorAntifraudePort);

        transaccionValida = new Transaccion(
            UUID.randomUUID().toString(),
            "CLIENTE-001",
            new BigDecimal("5000.00"),
            "CREDITO",
            LocalDateTime.now()
        );

        resultadoAprobado = new ResultadoEvaluacion(
            UUID.randomUUID().toString(),
            transaccionValida.getId(),
            "APROBADA",
            0.25,
            false,
            LocalDateTime.now()
        );

        resultadoRechazado = new ResultadoEvaluacion(
            UUID.randomUUID().toString(),
            transaccionValida.getId(),
            "RECHAZADA",
            0.85,
            true,
            LocalDateTime.now()
        );
    }

    @Test
    @DisplayName("procesarTransaccion - flujo exitoso con bajo riesgo")
    void procesarTransaccion_flujoExitoso() {
        when(burOriesgoPort.evaluarRiesgo(any(Transaccion.class)))
            .thenReturn(Mono.just(0.25));
        when(motorAntifraudePort.evaluarFraude(any(Transaccion.class)))
            .thenReturn(Mono.just(false));
        when(motorAntifraudePort.registrarTransaccionEvaluada(any(Transaccion.class)))
            .thenReturn(Mono.empty());

        StepVerifier.create(transaccionService.procesarTransaccion(transaccionValida))
            .expectNextMatches(resultado -> 
                resultado.getEstado().equals("APROBADA") && 
                resultado.getScoreRiesgo() == 0.25 &&
                !resultado.isFlagFraude()
            )
            .verifyComplete();

        verify(burOriesgoPort, times(1)).evaluarRiesgo(any(Transaccion.class));
        verify(motorAntifraudePort, times(1)).evaluarFraude(any(Transaccion.class));
        verify(motorAntifraudePort, times(1)).registrarTransaccionEvaluada(any(Transaccion.class));
    }

    @Test
    @DisplayName("procesarTransaccion - transaccion rechazada por alto riesgo")
    void procesarTransaccion_rechazadaPorRiesgo() {
        when(burOriesgoPort.evaluarRiesgo(any(Transaccion.class)))
            .thenReturn(Mono.just(0.85));
        when(motorAntifraudePort.evaluarFraude(any(Transaccion.class)))
            .thenReturn(Mono.just(false));
        when(motorAntifraudePort.registrarTransaccionEvaluada(any(Transaccion.class)))
            .thenReturn(Mono.empty());

        StepVerifier.create(transaccionService.procesarTransaccion(transaccionValida))
            .expectError(TransaccionRechazadaException.class)
            .verify();
    }

    @Test
    @DisplayName("procesarTransaccion - transaccion rechazada por fraude detectado")
    void procesarTransaccion_rechazadaPorFraude() {
        when(burOriesgoPort.evaluarRiesgo(any(Transaccion.class)))
            .thenReturn(Mono.just(0.25));
        when(motorAntifraudePort.evaluarFraude(any(Transaccion.class)))
            .thenReturn(Mono.just(true));
        when(motorAntifraudePort.registrarTransaccionEvaluada(any(Transaccion.class)))
            .thenReturn(Mono.empty());

        StepVerifier.create(transaccionService.procesarTransaccion(transaccionValida))
            .expectError(TransaccionRechazadaException.class)
            .verify();
    }

    @Test
    @DisplayName("procesarTransaccion - timeout del buró de riesgo")
    void procesarTransaccion_timeoutBuro() {
        when(burOriesgoPort.evaluarRiesgo(any(Transaccion.class)))
            .thenReturn(Mono.error(new TimeoutBuroException("Timeout al evaluar riesgo")));

        StepVerifier.create(transaccionService.procesarTransaccion(transaccionValida))
            .expectError(TimeoutBuroException.class)
            .verify();
    }

    @Test
    @DisplayName("procesarTransaccion - error 5xx del buró de riesgo")
    void procesarTransaccion_error5xxBuro() {
        when(burOriesgoPort.evaluarRiesgo(any(Transaccion.class)))
            .thenReturn(Mono.error(new Respuesta5xxException("Error interno del buró")));

        StepVerifier.create(transaccionService.procesarTransaccion(transaccionValida))
            .expectError(Respuesta5xxException.class)
            .verify();
    }

    @Test
    @DisplayName("consultarEstadoTransaccion - retorna estado guardado")
    void consultarEstadoTransaccion_existe() {
        String idTransaccion = transaccionValida.getId();

        when(burOriesgoPort.obtenerHistorialRiesgo(idTransaccion))
            .thenReturn(Mono.just("Historial: bajo riesgo"));

        StepVerifier.create(transaccionService.consultarEstadoTransaccion(idTransaccion))
            .expectNextMatches(resultado -> 
                resultado.getIdTransaccion().equals(idTransaccion)
            )
            .verifyComplete();
    }

    @Test
    @DisplayName("consultarEstadoTransaccion - transaccion no encontrada")
    void consultarEstadoTransaccion_noEncontrada() {
        String idInexistente = "TX-INEXISTENTE";

        when(burOriesgoPort.obtenerHistorialRiesgo(idInexistente))
            .thenReturn(Mono.empty());

        StepVerifier.create(transaccionService.consultarEstadoTransaccion(idInexistente))
            .verifyComplete();
    }
}

// === ARCHIVO: src/test/java/com/transaccionesfinancieras/adapter/in/web/TransaccionControllerTest.java ===
package com.transaccionesfinancieras.adapter.in.web;

import com.transaccionesfinancieras.application.port.in.ProcesarTransaccionUseCase;
import com.transaccionesfinancieras.domain.exception.Respuesta5xxException;
import com.transaccionesfinancieras.domain.exception.TimeoutBuroException;
import com.transaccionesfinancieras.domain.exception.TransaccionRechazadaException;
import com.transaccionesfinancieras.domain.model.ResultadoEvaluacion;
import com.transaccionesfinancieras.domain.model.Transaccion;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebFluxTest(TransaccionController.class)
@DisplayName("Tests de integración para TransaccionController")
class TransaccionControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private ProcesarTransaccionUseCase procesarTransaccionUseCase;

    private Transaccion crearTransaccionValida() {
        return new Transaccion(
            UUID.randomUUID().toString(),
            "CLIENTE-001",
            new BigDecimal("5000.00"),
            "CREDITO",
            LocalDateTime.now()
        );
    }

    private ResultadoEvaluacion crearResultadoAprobado(String idTransaccion) {
        return new ResultadoEvaluacion(
            UUID.randomUUID().toString(),
            idTransaccion,
            "APROBADA",
            0.25,
            false,
            LocalDateTime.now()
        );
    }

    @Test
    @DisplayName("POST /api/transacciones - retorna 200 cuando la transacción es aprobada")
    void procesarTransaccion_retorna200CuandoAprobada() {
        Transaccion transaccion = crearTransaccionValida();
        ResultadoEvaluacion resultado = crearResultadoAprobado(transaccion.getId());

        when(procesarTransaccionUseCase.procesarTransaccion(any(Transaccion.class)))
            .thenReturn(Mono.just(resultado));

        webTestClient
            .post()
            .uri("/api/transacciones")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(transaccion)
            .exchange()
            .expectStatus().isOk()
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id").isEqualTo(resultado.getId())
            .jsonPath("$.idTransaccion").isEqualTo(transaccion.getId())
            .jsonPath("$.estado").isEqualTo("APROBADA")
            .jsonPath("$.scoreRiesgo").isEqualTo(0.25)
            .jsonPath("$.flagFraude").isEqualTo(false);
    }

    @Test
    @DisplayName("POST /api/transacciones - retorna 400 cuando la transacción es rechazada")
    void procesarTransaccion_retorna400CuandoRechazada() {
        Transaccion transaccion = crearTransaccionValida();

        when(procesarTransaccionUseCase.procesarTransaccion(any(Transaccion.class)))
            .thenReturn(Mono.error(new TransaccionRechazadaException("Riesgo demasiado alto")));

        webTestClient
            .post()
            .uri("/api/transacciones")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(transaccion)
            .exchange()
            .expectStatus().isBadRequest()
            .expectBody()
            .jsonPath("$.mensaje").isEqualTo("Riesgo demasiado alto");
    }

    @Test
    @DisplayName("POST /api/transacciones - retorna 504 cuando hay timeout del buró")
    void procesarTransaccion_retorna504CuandoTimeout() {
        Transaccion transaccion = crearTransaccionValida();

        when(procesarTransaccionUseCase.procesarTransaccion(any(Transaccion.class)))
            .thenReturn(Mono.error(new TimeoutBuroException("Timeout en el servicio de riesgo")));

        webTestClient
            .post()
            .uri("/api/transacciones")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(transaccion)
            .exchange()
            .expectStatus().isEqualTo(504)
            .expectBody()
            .jsonPath("$.mensaje").exists();
    }

    @Test
    @DisplayName("POST /api/transacciones - retorna 502 cuando hay error 5xx del buró")
    void procesarTransaccion_retorna502CuandoError5xx() {
        Transaccion transaccion = crearTransaccionValida();

        when(procesarTransaccionUseCase.procesarTransaccion(any(Transaccion.class)))
            .thenReturn(Mono.error(new Respuesta5xxException("Error interno del buró")));

        webTestClient
            .post()
            .uri("/api/transacciones")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(transaccion)
            .exchange()
            .expectStatus().isEqualTo(502)
            .expectBody()
            .jsonPath("$.mensaje").exists();
    }

    @Test
    @DisplayName("POST /api/transacciones - retorna 400 cuando el body está vacío")
    void procesarTransaccion_retorna400CuandoBodyVacio() {
        webTestClient
            .post()
            .uri("/api/transacciones")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue("{}")
            .exchange()
            .expectStatus().isBadRequest();
    }

    @Test
    @DisplayName("GET /api/transacciones/{id} - retorna 200 cuando existe la transacción")
    void consultarEstado_retorna200CuandoExiste() {
        String idTransaccion = "TX-12345";
        ResultadoEvaluacion resultado = crearResultadoAprobado(idTransaccion);

        when(procesarTransaccionUseCase.consultarEstadoTransaccion(idTransaccion))
            .thenReturn(Mono.just(resultado));

        webTestClient
            .get()
            .uri("/api/transacciones/{id}", idTransaccion)
            .exchange()
            .expectStatus().isOk()
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.idTransaccion").isEqualTo(idTransaccion)
            .jsonPath("$.estado").isEqualTo("APROBADA");
    }

    @Test
    @DisplayName("GET /api/transacciones/{id} - retorna 404 cuando no existe la transacción")
    void consultarEstado_retorna404CuandoNoExiste() {
        String idInexistente = "TX-INEXISTENTE";

        when(procesarTransaccionUseCase.consultarEstadoTransaccion(idInexistente))
            .thenReturn(Mono.empty());

        webTestClient
            .get()
            .uri("/api/transacciones/{id}", idInexistente)
            .exchange()
            .expectStatus().isNotFound();
    }
}
```
