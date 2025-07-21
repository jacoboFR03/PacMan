#  Prueba comercial Pacman back

API REST que ordena camisetas en base a criterios  como unidades vendidas y stock, usando una **arquitectura hexagonal**.

---

##  Estructura del Proyecto
La estructura de paquetes empleada para este proyecto sigue el esquema que conforma una aplicación Hexagonal, formada 
por el dominio, la capa de aplicación y la capa de infraestructura.

En el Dominio se encuentra las entidades de la aplicación, en este caso las Camisetas y el Value Object usado para la 
creación de los identificadores de las estrategias de ordenación. Junto a esto, también se encuentra el servicio de dominio
encargado de la ordenación y aplicación de los criterios.

En la capa de aplicación se encuentran los puertos de entrada y salida de la aplicación junto a un servicio que
implementa esos casos.

Por último, la capa de infraestructura contiene los adaptadores que se encargan de la interacción con elementos externos,
está formada por un controlador REST y una clase Repository que simula, por ejemplo, un repositorio de JPA que permitiría 
la obtención de las camisetas de una BD relacional.

### Diseño
Para el diseño de esta aplicación se usó el patrón Estrategia. Este patron permite hacer extensible el número de criterios 
de ordenación ya que abstrae al servicio de dominio de las estrategias mediante la interfaz  (`SortingStrategy`) . 
Para añadir una nueva implementación simplemente tendríamos que crear una nueva clase que heredara la lógica común de todas
las estrategias de la clase (`BaseStrategy`), implementar la interfaz y redefinir el método (`evaluate`).

##  Ejecución del proyecto

###  Ejecutar localmente la aplicación 

```bash
./mvnw clean install
```

```bash
./mvnw spring-boot:run
```

###  Ejecutar los tests

```bash
./mvnw clean test
```

### LLamada a la API Rest
Para realizar una llamada a la API, se puede hacer directamente desde la documentación de Swagger de la API o bien desde
Postman o alguna herramienta similar.

Para realizar la prueba se debe enviar una petición HTTP de tipo POST a la url donde arranca el servicio
 (`http://localhost:8080/pacman/shirt/sort`) enviando en el body de la petición un JSON como el siguiente
indicando los pesos para los criterios de ordenación:

```
{
  "salesUnitsWeight": 0.6,
  "stockWeight": 1
}
```
