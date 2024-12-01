Aquí tienes un README adaptado a tu proyecto de Conversor de Monedas en Java:

---

# Conversor de Monedas

Este proyecto es una solución para un desafío técnico propuesto, desarrollado como parte del programa Oracle Next Education (ONE). Es un sistema completo que permite convertir valores entre distintas monedas, utilizando datos en tiempo real obtenidos de la API **ExchangeRate-API**.

## 🚀 Características

- **Conversión entre monedas:** Soporte para múltiples monedas como ARS, BOB, BRL, CLP, COP, USD, entre otras.
- **Obtención de tasas en tiempo real:** Uso de la API ExchangeRate-API para garantizar precisión en las tasas de cambio.
- **Interfaz interactiva en consola:** Los usuarios pueden ingresar datos directamente y obtener resultados de manera sencilla.
- **Filtrado de monedas específicas:** Soporte para listar tasas de cambio seleccionadas.
- **Modularidad:** Código organizado en clases específicas para facilitar su mantenimiento y escalabilidad.

---

## 🛠️ Tecnologías Utilizadas

- **Lenguaje:** Java
- **IDE:** Visual Studio Code / IntelliJ IDEA
- **Dependencias:**
  - [Gson](https://github.com/google/gson): Biblioteca para manejar datos JSON.
  - [Dotenv](https://github.com/cdimascio/java-dotenv): Manejo de variables de entorno para la configuración de la API.
- **API:** [ExchangeRate-API](https://www.exchangerate-api.com/docs/overview)

---

## 📁 Estructura del Proyecto

```plaintext
.vscode/
    settings.json
conversor_de_monedas/
    .mvn/
        jvm.config
        maven.config
    pom.xml
    src/
        main/
            java/
                com/
                    jesusalvarez/
                        conversordemonedas/
                            App.java
                            ExchangeRateApiClient.java
                            CurrencyConverter.java
        test/
            java/
                com/
                    jesusalvarez/
                        conversordemonedas/
                            AppTest.java
    target/
README.md
```

---

## 🚀 Cómo Ejecutar el Proyecto

1. **Clonar el Repositorio:**
   ```bash
   git clone https://github.com/tuusuario/conversor-de-monedas.git
   cd conversor-de-monedas
   ```

2. **Configurar Dependencias:**
   Asegúrate de que tienes Maven instalado para gestionar las dependencias. Luego, ejecuta:
   ```bash
   mvn install
   ```

3. **Configurar Variables de Entorno:**
   Crea un archivo `.env` en la raíz del proyecto con la clave de API de **ExchangeRate-API**:
   ```plaintext
   EXCHANGE_RATE_API_KEY=tu_clave_api
   APP_MODE=dev # Opcional: cambiar a prod para suprimir logs de depuración
   ```

4. **Compilar y Ejecutar:**
   ```bash
   mvn compile
   mvn exec:java -Dexec.mainClass="com.jesusalvarez.conversordemonedas.App"
   ```

---

## 📋 Funcionalidades

### 1. Conversión entre monedas
El programa solicita:
- Moneda base
- Moneda objetivo
- Cantidad a convertir

Y devuelve el monto convertido según las tasas de cambio actuales.

### 2. Mostrar tasas de cambio filtradas
Lista las tasas de cambio de las siguientes monedas:
- ARS (Peso argentino)
- BOB (Boliviano boliviano)
- BRL (Real brasileño)
- CLP (Peso chileno)
- COP (Peso colombiano)
- USD (Dólar estadounidense)

---

## 🧪 Pruebas

El proyecto incluye pruebas básicas en la clase `AppTest.java`. Para ejecutarlas:
```bash
mvn test
```

---

## 🌟 Contribuciones

¡Las contribuciones son bienvenidas! Si encuentras algún problema o tienes sugerencias, no dudes en abrir un issue o enviar un pull request.

---

## 📜 Licencia

Este proyecto está bajo la Licencia MIT. Consulta el archivo `LICENSE` para más información.

---

Con este README, tu proyecto estará listo para ser presentado en GitHub o cualquier otra plataforma de control de versiones. ¿Te gustaría personalizar algún aspecto o necesitas ayuda con otro archivo? 🚀