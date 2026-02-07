#  Sistema de Venta de Celulares – TecnoStore

TecnoStore es una tienda minorista dedicada a la venta de teléfonos celulares de diferentes marcas y gamas.  
Este proyecto consiste en el desarrollo de un **sistema de consola en Java** que permite automatizar el control de **ventas, inventario y clientes**, aplicando Programación Orientada a Objetos, JDBC y buenas prácticas de desarrollo.

---

##  Objetivo del Proyecto

Automatizar la gestión de celulares, clientes y ventas que anteriormente se realizaba de forma manual en hojas de cálculo, permitiendo:

- Control de inventario
- Registro de ventas con validaciones
- Persistencia en base de datos MySQL
- Generación de reportes básicos

---

##  Funcionalidades del Sistema

### Gestión de Celulares
- Registrar celulares
- Listar celulares
- Actualizar celulares
- Eliminar celulares
- Validar que **precio y stock sean positivos**

Cada celular contiene:
- id
- marca
- modelo
- sistema operativo
- gama (Alta, Media, Baja)
- precio
- stock

---

### Gestión de Clientes
- Registrar clientes
- Listar clientes
- Actualizar clientes
- Eliminar clientes
- Validar formato básico del correo

Cada cliente contiene:
- id
- nombre
- apellido
- correo
- teléfono

---

### Gestión de Ventas
- Registrar ventas seleccionando cliente y celular
- Validar stock disponible
- Calcular subtotal, IVA (19%) y total automáticamente
- Actualizar stock del celular vendido
- Guardar ventas y detalles en base de datos usando JDBC

---

### Reportes
- Celulares con stock bajo (≤ 5 unidades)
- Listados claros en consola

---

## Estructura del Proyecto

src/
├── MODELO
│ ├── Celular.java
│ ├── Cliente.java
│ ├── Venta.java
│ └── DetalleVenta.java
│
├── DAO
│ ├── CelularDAO.java
│ ├── ClienteDAO.java
│ ├── VentaDAO.java
│ └── DetalleVentaDAO.java
│
├── CONTROLADOR
│ ├── GestorCelulares.java
│ ├── GestorClientes.java
│ └── GestorVentas.java
│
├── PERSISTENCIA
│ └── Conexion.java
│
├── UTILIDADES
│ └── ReporteUtils.java
│
└── VISTA
└── Main.java


---

##  Base de Datos MySQL

Nombre de la base de datos: **tecnostore_db**

###  Script SQL (`tecnostore_db.sql`)

```sql
CREATE DATABASE IF NOT EXISTS tecnostore_db;
USE tecnostore_db;

CREATE TABLE clientes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    correo VARCHAR(100) NOT NULL,
    telefono VARCHAR(20) NOT NULL
);

CREATE TABLE celulares (
    id INT AUTO_INCREMENT PRIMARY KEY,
    marca VARCHAR(50) NOT NULL,
    modelo VARCHAR(50) NOT NULL,
    sistema_operativo VARCHAR(30) NOT NULL,
    gama VARCHAR(30) NOT NULL,
    precio DOUBLE NOT NULL,
    stock INT NOT NULL
);

CREATE TABLE ventas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_cliente INT NOT NULL,
    fecha DATE NOT NULL,
    total DOUBLE NOT NULL,
    FOREIGN KEY (id_cliente) REFERENCES clientes(id)
);

CREATE TABLE detalle_ventas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_venta INT NOT NULL,
    id_celular INT NOT NULL,
    cantidad INT NOT NULL,
    subtotal DOUBLE NOT NULL,
    FOREIGN KEY (id_venta) REFERENCES ventas(id),
    FOREIGN KEY (id_celular) REFERENCES celulares(id)
);
 Ejecución del Programa
Crear la base de datos ejecutando el script SQL.

Configurar la conexión en la clase Conexion.java:

DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/tecnostore_db",
    "root",
    "TU_CONTRASEÑA"
);
Ejecutar la clase Main.java.

Usar el menú de consola para gestionar celulares, clientes y ventas.

 Evidencias de Ejecución
Se incluyen capturas de pantalla donde se evidencia:

Registro de celulares

Registro de clientes

Registro de ventas

Actualización de stock

Listados en consola

 Tecnologías Utilizadas
Java

JDBC

MySQL

Programación Orientada a Objetos

Consola (CLI)

 Conclusión
El sistema TecnoStore permite gestionar de manera eficiente el inventario, clientes y ventas, garantizando la persistencia de datos y validaciones básicas, cumpliendo con los requerimientos planteados para el proyecto académico.

