# AGENTS.md

Instrucciones para el agente de IA que abra este repositorio (Claude Code, Cursor, Codex, Copilot, Gemini). Se cargan solas: no hay que pegar nada en ningun chat.

## Que es este repositorio

Es el codigo base de un reto de aprendizaje de Pragma: **Exploración y Análisis de Paradigmas No Imperativos en Sistemas Reactivos**.

| | |
|---|---|
| Tema | Adopción de Paradigmas de Programación No Imperativos: Con Enfoque Reactivo y Funcional |
| Nivel | master-l1 |
| Chapter | Backend |
| Especialidad | Java |
| Stack | Java / Spring WebFlux 3.5.6 |
| Patron arquitectonico | microservicio reactivo con patron hexagonal/clean |
| Tiempo estimado | 8 horas |

## Receta del stack

Esqueleto obligatorio:

- `pom.xml en la raiz`
- `clase con @SpringBootApplication`
- `application.yml en src/main/resources`
- `capa de dominio con entidades y puertos`
- `capa de aplicacion con casos de uso`
- `capa de infraestructura con adaptadores y @RestController`

Trampas conocidas:

- TODA `<version>` del pom va con tres segmentos: la del parent (ej. `3.5.6`) y la de cada dependencia que la lleve (ej. Resilience4j `2.2.0`). `3.4` y `2.0` no existen como artefacto y el build muere resolviendo dependencias.
- Las dependencias que el parent POM gestiona van SIN `<version>`: `spring-boot-starter-web`, `-data-jpa`, `-validation`, `-test`, etc.
- Resilience4j publica un artefacto por linea de Spring Boot. Con Spring Boot 3 va `resilience4j-spring-boot3` con version de tres segmentos (ej. `2.2.0`, no `2.0`). `resilience4j-spring-boot2` es de Spring Boot 2 y rompe el arranque.
- Si usas anotaciones de validacion (`@NotNull`, `@Size`, `@Positive`) declara `spring-boot-starter-validation`: el starter web no las trae.
- El `spring-boot-maven-plugin` tiene que estar en `<build><plugins>` o no se empaqueta ejecutable.
- Spring Boot 3 usa `jakarta.*`, nunca `javax.*`.
- Cada archivo empieza con su `package` y con un `import` por cada clase del proyecto que viva en otro paquete. Usar `PaymentService` desde `infrastructure` sin `import com.x.application.PaymentService` no compila.

Dependencias:

- org.springframework.boot:spring-boot-starter-webflux 3.5.6
- org.springframework.boot:spring-boot-starter-actuator n/a
- io.projectreactor:reactor-core n/a
- io.projectreactor.netty:reactor-netty n/a
- io.github.resilience4j:resilience4j-spring-boot3 2.2.0
- io.github.resilience4j:resilience4j-reactor 2.2.0
- org.springframework.boot:spring-boot-starter-test n/a
- io.projectreactor:reactor-test n/a
- org.junit.jupiter:junit-jupiter-api n/a
- org.mockito:mockito-core n/a

## Tu tarea

Dejar este proyecto en estado **verificable**: que el comando de verificacion corra sin errores. Escribi los archivos en disco, en este repositorio. No generes ZIPs ni archivos adjuntos.

En orden:

1. Corre `mvn clean compile` y mira que falla.
2. Completa lo que falte de la lista de abajo: manifiesto de dependencias, punto de entrada, capa de interfaz y las capas del patron declarado.
3. Arregla SOLO los errores que impiden compilar o arrancar.
4. Volve a correr `mvn clean compile` hasta que pase.
5. Pará ahí.

## Regla dura: las fases son trabajo del humano

**PROHIBIDO implementar los entregables de las fases.** El valor del reto esta en que la persona los resuelva. Tu trabajo es que tenga un proyecto que arranca; el hueco pedagogico se queda como esta.

No resuelvas nada de esto:

- **Fase 1 — Identificación de Requisitos y Restricciones**: Documento que describe los actores, interacciones, umbrales y restricciones del sistema.
- **Fase 2 — Exploración de Paradigmas No Imperativos**: Documento que describe los cuatro pilares de los sistemas reactivos, las ventajas y desventajas de los paradigmas reactivo y funcional, y los operadores básicos de cada uno.
- **Fase 3 — Análisis de Aplicación en el Dominio**: Documento que propone una estrategia para implementar los paradigmas no imperativos en el sistema de procesamiento de transacciones, considerando los requisitos y restricciones identificados.

Distincion operativa:

- **Arreglar** (si): import faltante, tipo que no existe, dependencia sin declarar, error de sintaxis, archivo referenciado que no existe.
- **No tocar** (no): logica de negocio incompleta, validaciones ausentes, secretos hardcodeados, APIs deprecadas que funcionan, concurrencia insegura, patrones mejorables. Eso es lo que la persona tiene que encontrar.

## Superficie de practica (NO completes)

Estos archivos SON el ejercicio de la persona. No los implementes; deja stubs. No toques la logica que el reto pide completar.

- [ ] `src/main/java/com/transaccionesfinancieras/config/Resilience4jConfig.java` — El topic pide resiliencia: este archivo es el ejercicio.

## Lo que falta y tenes que completar

### 1. Referencias colgando (38)

Salieron de un analisis estatico del codigo que SI esta en el repo. Cada una rompe la compilacion:

- [ ] `src/main/java/com/transaccionesfinancieras/Application.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Hooks pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/transaccionesfinancieras/application/port/in/ProcesarTransaccionUseCase.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/transaccionesfinancieras/application/port/out/BuroRiesgoPort.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/transaccionesfinancieras/application/port/out/MotorAntifraudePort.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/transaccionesfinancieras/config/WebClientConfig.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/transaccionesfinancieras/config/WebClientConfig.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/transaccionesfinancieras/config/WebClientConfig.java` — `reactor.util.retry`
      El import reactor.util.retry.Retry pertenece a reactor.util.retry, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/transaccionesfinancieras/adapter/in/web/TransaccionController.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/transaccionesfinancieras/adapter/in/web/TransaccionController.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/transaccionesfinancieras/adapter/out/external/BuroRiesgoAdapter.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/transaccionesfinancieras/adapter/out/external/BuroRiesgoAdapter.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/transaccionesfinancieras/adapter/out/external/BuroRiesgoAdapter.java` — `reactor.util.retry`
      El import reactor.util.retry.Retry pertenece a reactor.util.retry, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/transaccionesfinancieras/adapter/out/external/MotorAntifraudeAdapter.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/transaccionesfinancieras/adapter/out/external/MotorAntifraudeAdapter.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/transaccionesfinancieras/adapter/out/external/MotorAntifraudeAdapter.java` — `reactor.util.retry`
      El import reactor.util.retry.Retry pertenece a reactor.util.retry, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/transaccionesfinancieras/application/service/TransaccionService.java` — `com.transaccionesfinanciales.application`
      El import com.transaccionesfinanciales.application.port.in.ProcesarTransaccionUseCase pertenece a com.transaccionesfinanciales.application, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/transaccionesfinancieras/application/service/TransaccionService.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/transaccionesfinancieras/application/service/TransaccionService.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/transaccionesfinancieras/application/service/TransaccionService.java` — `reactor.util.function`
      El import reactor.util.function.Tuple2 pertenece a reactor.util.function, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/test/java/com/transaccionesfinancieras/application/service/TransaccionServiceTest.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/test/java/com/transaccionesfinancieras/adapter/in/web/TransaccionControllerTest.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/transaccionesfinancieras/Application.java` — `ApplicationProperties.getBuroRiesgoBaseUrl`
      Se invoca `getBuroRiesgoBaseUrl` sobre `ApplicationProperties`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/transaccionesfinancieras/Application.java` — `ApplicationProperties.getBuroRiesgoTimeout`
      Se invoca `getBuroRiesgoTimeout` sobre `ApplicationProperties`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/transaccionesfinancieras/domain/exception/TransaccionRechazadaException.java` — `MotivoRechazo.getDescripcion`
      Se invoca `getDescripcion` sobre `MotivoRechazo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/transaccionesfinancieras/domain/exception/TransaccionRechazadaException.java` — `MotivoRechazo.name`
      Se invoca `name` sobre `MotivoRechazo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/transaccionesfinancieras/adapter/in/web/TransaccionController.java` — `Transaccion.id`
      Se invoca `id` sobre `Transaccion`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/transaccionesfinancieras/adapter/out/external/BuroRiesgoAdapter.java` — `Transaccion.idCliente`
      Se invoca `idCliente` sobre `Transaccion`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/transaccionesfinancieras/adapter/out/external/BuroRiesgoAdapter.java` — `Transaccion.monto`
      Se invoca `monto` sobre `Transaccion`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/transaccionesfinancieras/adapter/out/external/BuroRiesgoAdapter.java` — `Transaccion.tipoTransaccion`
      Se invoca `tipoTransaccion` sobre `Transaccion`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/transaccionesfinancieras/adapter/out/external/MotorAntifraudeAdapter.java` — `Transaccion.id`
      Se invoca `id` sobre `Transaccion`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/transaccionesfinancieras/application/service/TransaccionService.java` — `Transaccion.id`
      Se invoca `id` sobre `Transaccion`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/transaccionesfinancieras/application/service/TransaccionService.java` — `Transaccion.idCliente`
      Se invoca `idCliente` sobre `Transaccion`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/transaccionesfinancieras/application/service/TransaccionService.java` — `Transaccion.monto`
      Se invoca `monto` sobre `Transaccion`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/transaccionesfinancieras/application/service/TransaccionService.java` — `Transaccion.moneda`
      Se invoca `moneda` sobre `Transaccion`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/transaccionesfinancieras/application/service/TransaccionService.java` — `Transaccion.tipo`
      Se invoca `tipo` sobre `Transaccion`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/transaccionesfinancieras/application/service/TransaccionService.java` — `Transaccion.descripcion`
      Se invoca `descripcion` sobre `Transaccion`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/transaccionesfinancieras/application/service/TransaccionService.java` — `ResultadoEvaluacion.estado`
      Se invoca `estado` sobre `ResultadoEvaluacion`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/transaccionesfinancieras/adapter/in/web/TransaccionControllerTest.java` — `ResultadoEvaluacion.getId`
      Se invoca `getId` sobre `ResultadoEvaluacion`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

### Presentes (19)

- `pom.xml`
- `src/main/java/com/transaccionesfinancieras/Application.java`
- `src/main/resources/application.yml`
- `src/main/java/com/transaccionesfinancieras/application/port/in/ProcesarTransaccionUseCase.java`
- `src/main/java/com/transaccionesfinancieras/application/port/out/BuroRiesgoPort.java`
- `src/main/java/com/transaccionesfinancieras/application/port/out/MotorAntifraudePort.java`
- `src/main/java/com/transaccionesfinancieras/domain/model/Transaccion.java`
- `src/main/java/com/transaccionesfinancieras/domain/model/ResultadoEvaluacion.java`
- `src/main/java/com/transaccionesfinancieras/domain/exception/TimeoutBuroException.java`
- `src/main/java/com/transaccionesfinancieras/domain/exception/Respuesta5xxException.java`
- `src/main/java/com/transaccionesfinancieras/domain/exception/TransaccionRechazadaException.java`
- `src/main/java/com/transaccionesfinancieras/config/WebClientConfig.java`
- `src/main/java/com/transaccionesfinancieras/config/Resilience4jConfig.java`
- `src/main/java/com/transaccionesfinancieras/adapter/in/web/TransaccionController.java`
- `src/main/java/com/transaccionesfinancieras/adapter/out/external/BuroRiesgoAdapter.java`
- `src/main/java/com/transaccionesfinancieras/adapter/out/external/MotorAntifraudeAdapter.java`
- `src/main/java/com/transaccionesfinancieras/application/service/TransaccionService.java`
- `src/test/java/com/transaccionesfinancieras/application/service/TransaccionServiceTest.java`
- `src/test/java/com/transaccionesfinancieras/adapter/in/web/TransaccionControllerTest.java`

### Capas del patron declarado

Cada una tiene que existir como directorio real con al menos un archivo. Codigo plano en la raiz no satisface el patron.

- `src/main/java/com/transaccionesfinancieras/adapter/in/web`
- `src/main/java/com/transaccionesfinancieras/adapter/out/external`
- `src/main/java/com/transaccionesfinancieras/application/port/in`
- `src/main/java/com/transaccionesfinancieras/application/port/out`
- `src/main/java/com/transaccionesfinancieras/application/service`
- `src/main/java/com/transaccionesfinancieras/domain/model`
- `src/main/java/com/transaccionesfinancieras/domain/exception`
- `src/main/java/com/transaccionesfinancieras/config`
- `src/main/resources`
- `src/test/java/com/transaccionesfinancieras`

## Verificacion

```bash
mvn clean compile
```

El comando tiene que pasar SIN implementar los archivos de la superficie de practica: solo andamiaje.

Ese comando pasando es la definicion de "terminado" para vos.

## Convenciones que tenes que respetar

- Un solo ecosistema: no declares librerias de otro lenguaje ni mezcles gestores de paquetes.
- Toda libreria que uses tiene que estar declarada en el manifiesto de dependencias.
- Todo import declarado tiene que usarse; todo tipo usado tiene que existir o venir de una dependencia declarada.
- El patron es **microservicio reactivo con patron hexagonal/clean**: los contratos (interfaces, puertos) los define la capa interna y los implementa la externa, nunca al revés.
- Los archivos que crees llevan implementacion real, no stubs: sin `TODO`, sin cuerpos vacios, sin `// getters y setters`.

## Contexto del candidato

Sirve para calibrar el nivel del codigo, no para resolver las fases.

- Perfil: Chapter Backend, Especialidad Desarrollador, Tecnología Java, Master
- Brecha que el reto ataca: Implementa un paradigma de programación distinto al imperativo, como el paradigma reactivo o el paradigma funcional. Domina los cuatro pilares especificados en el manifiesto de sistemas reactivos, favoreciendo mejor rendimiento, una mayor escalabilidad y una mayor resiliencia. Conoce las ventajas, desventajas y operadores básicos en la implementación de este paradigma.
- Mision: Candidato con experiencia Master en Backend Java

---

*Generado por Challenge Generator — Pragma. `README.md` tiene el enunciado completo del reto para la persona. `PROMPT_MEJORA.md` es la variante para pegar en un chat, si se prefiere ese flujo.*
