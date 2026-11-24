# LlanquihueTourApp – Desarrollo Orientado a Objetos I (Semana 5)

## Autor
- Nombre: IVAN ANDREE CALDERA ALARCON
- Sección: 002A - 204
- Carrera: Analista Programador Computacional
- Institución: Duoc UC Online
- Fecha 23-11-2026

## Descripción
Aplicación de consola para administrar personas vinculadas a la agencia turística Llanquihue Tour. Carga registros desde un archivo CSV a una colección `ArrayList`, evita identificadores duplicados y permite listar, buscar y filtrar personas, clientes, empleados y guías turísticos.

## Estructura
```text
LlanquihueTourApp/
├── data/
│   └── personas.csv
├── src/
│   ├── app/
│   │   └── Main.java
│   ├── model/
│   │   ├── Cliente.java
│   │   ├── Direccion.java
│   │   ├── Empleado.java
│   │   ├── GuiaTuristico.java
│   │   └── Persona.java
│   ├── service/
│   │   └── RegistroPersonasService.java
│   └── util/
│       └── Validaciones.java
└── README.md
```

## Clases y responsabilidades
- `Persona`: clase base con datos comunes y una instancia de `Direccion` (composición).
- `Direccion`: representa la dirección de una persona.
- `Empleado`: extiende `Persona` y agrega cargo, sueldo y fecha de ingreso.
- `GuiaTuristico`: extiende `Empleado` y agrega idiomas, especialidad y experiencia.
- `Cliente`: extiende `Persona` y administra nacionalidad, tipo y reservas.
- `Validaciones`: centraliza validaciones de campos obligatorios, correo y números no negativos.
- `RegistroPersonasService`: lee el CSV, almacena objetos en `ArrayList`, evita duplicados y realiza búsquedas/filtros.
- `Main`: clase principal y menú de consola.

## Formato del archivo CSV
El archivo `data/personas.csv` utiliza punto y coma (`;`) como separador. La primera columna indica el tipo (`PERSONA`, `EMPLEADO`, `CLIENTE` o `GUIA`). Las columnas restantes contienen los datos correspondientes al tipo. Para agregar registros, respeta el orden y completa los campos requeridos; el programa omitirá líneas inválidas e informará el motivo.

## Requisitos
- JDK 11 o superior.
- IntelliJ IDEA (o cualquier IDE Java).

## Ejecutar en IntelliJ IDEA
1. Descomprime el proyecto y abre la carpeta `LlanquihueTourApp` en IntelliJ IDEA.
2. Configura un JDK 11 o superior.
3. Marca `src` como **Sources Root** si IntelliJ no lo reconoce automáticamente.
4. Abre `src/app/Main.java` y ejecuta el método `main`.
5. Ejecuta desde la carpeta raíz del proyecto para que encuentre `data/personas.csv`.

## Ejecutar desde terminal
Desde la carpeta raíz del proyecto:

```bash
javac -encoding UTF-8 -d out src/util/*.java src/model/*.java src/service/*.java src/app/*.java
java -cp out app.Main
```

También se puede indicar una ruta distinta al CSV:

```bash
java -cp out app.Main data/personas.csv
```

## Funcionalidades
1. Mostrar todos los registros cargados.
2. Buscar por RUT/identificador.
3. Buscar por nombre o apellido.
4. Filtrar por tipo de registro.
5. Validar datos y controlar errores de lectura o conversión.

## Conceptos aplicados
Encapsulamiento, herencia, composición, constructores, getters/setters, `toString()`, `ArrayList`, lectura de archivos, búsqueda, filtrado y manejo de excepciones.
