#  TecnoStore  
Sistema de Gestión de Ventas e Inventario – Java + MySQL

---

##  Descripción

**TecnoStore** es una aplicación de consola en **Java** que automatiza la gestión de **celulares, clientes y ventas** de una tienda minorista.  
El sistema reemplaza el uso de hojas de cálculo y permite controlar inventario, registrar ventas y generar reportes usando **POO, JDBC, Stream API y patrones de diseño**.

---

##  Funcionalidades Principales

###  Celulares
- Registrar, actualizar, eliminar y listar celulares.
- Atributos: id, marca, modelo, sistema operativo, gama, precio y stock.
- Validación de precio y stock positivos.

###  Clientes
- Registro de clientes con validación de correo.
- Identificación única por cliente.

###  Ventas
- Registro de ventas con uno o más celulares.
- Cálculo automático del total con IVA (19%).
- Actualización automática del stock.
- Persistencia en MySQL usando JDBC.

###  Reportes
- Celulares con stock menor a 5.
- Top 3 celulares más vendidos.
- Ventas totales por mes (Stream API).
- Generación del archivo `reporte_ventas.txt`.

---

##  Tecnologías

- Java (JDK 8+)
- MySQL
- JDBC
- Stream API
- Programación Orientada a Objetos
- Patrón DAO

---

##  Estructura del Proyecto

```text
TecnoStore/
├── MODELO/
├── CONTROLADOR/
├── DAO/
├── PERSISTENCIA/
├── UTILIDADES/
├── PATRON/
├── VISTA/
└── README.md
 Base de Datos MySQL
Base de datos: tecnostore_db

 Script SQL Completo
CREATE DATABASE IF NOT EXISTS tecnostore_db;
USE tecnostore_db;

-- Tabla de celulares
CREATE TABLE celulares (
    id INT AUTO_INCREMENT PRIMARY KEY,
    marca VARCHAR(50) NOT NULL,
    modelo VARCHAR(50) NOT NULL,
    sistema_operativo VARCHAR(50),
    gama ENUM('Alta', 'Media', 'Baja') NOT NULL,
    precio DOUBLE NOT NULL CHECK (precio > 0),
    stock INT NOT NULL CHECK (stock >= 0)
);

-- Tabla de clientes
CREATE TABLE clientes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    identificacion VARCHAR(30) NOT NULL UNIQUE,
    correo VARCHAR(100) NOT NULL,
    telefono VARCHAR(20)
);

-- Tabla de ventas
CREATE TABLE ventas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_cliente INT NOT NULL,
    fecha DATE NOT NULL,
    total DOUBLE NOT NULL,
    FOREIGN KEY (id_cliente) REFERENCES clientes(id)
);

-- Tabla de detalle de ventas
CREATE TABLE detalle_ventas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_venta INT NOT NULL,
    id_celular INT NOT NULL,
    cantidad INT NOT NULL,
    subtotal DOUBLE NOT NULL,
    FOREIGN KEY (id_venta) REFERENCES ventas(id),
    FOREIGN KEY (id_celular) REFERENCES celulares(id)
);
