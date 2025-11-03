# Gestor del Titanic

***Miembros del grupo:*** Aitor Rebato, Erik De La Cruz

## Analisis del problema

Se pide implementar un programa que genere un documento sobre la cantidad de personas en cada bote.

Cada bote tiene una cantidad de personas que debe ser anotado de manera individual

Hay 20 botes en total cada uno con un identificador distindo

Hay personas que no se salvarán, por lo que no hay que contar que sobren

Los botes tardan de 2 a 6 segundos en contabilizar las personas que hay en el

Por lo que el programa tendrá que ver las personas que hay en cada bote y generar el informe con los datos de cada bote

---

## Diseño de la solución

### Arquitectura

Explicación: Necesitamos un programa principal que gestione los botes, los lance, reciba la información y escriba el informe, los botes son gestionados de manera individual, tiene que recibir el numero de bote que le corresponde.

Funcionamiento:

- Iniciamos el programa
- Servicio de emergencias genera 20 botes
- Como los botes tardan de 2 a 6 segundos en ejecutarse, el programa deberá funcionar de manera *asincrona* y *bloqueante*, de esta manera cuando estan todos los botes en el mar, comienza a intentar leerlo
    > El tiempo de espera será el tiempo del bote que mas tardó en ejecutarse
- Los botes devuelven la cantidad de personas que contienen
- Con los datos de los botes se generará el informe final

![Dibujo de la arquitectura](doc/Arquitectura1.png)

### Componentes

***Servicio de emergencia:*** Recibe internamente la lista de los botes para tenerla en un array, lleva los botes al metodo `enviarBote()`, cuando todos los botes estan enviados, hace la función `leerBote()` para obtener un listado de las personas de todos los botes, finalmente manda esta información a `generarInforme()` para generar el informe.

***Ejecuter:*** Es una interfaz que recibe un comando de `enviarBote` y lo ejecuta de manera asincrona, en este caso lanza bote y recibe su información para enviarla a `leerBote()`.

***Bote:*** Es ejecutado la cantidad de botes que tenga el barco, tiene una función que le asigna un numero de personas sin pasarse de la cantidad maxima en este caso 100 `asignarNumeroPersona()` , y devuelve el numero de personas que hay.

***UniversalWriter:*** Se ejecuta desde `generarInforme()` para crear el `informe.md` final donde esta escrita la cantidad de personas en cada bote.

### Funcionalidad completa

---

## Servicio de emergencia:
	- Contiene su lista de botes
	- Envia botes al mar
	- Lee los resultados en conjunto
	- Crea los datos del informe y lo manda a generar

### Envia botes al mar (Por cada bote):
	- Recibe un numero de bote
	- Crea el proceso de bote
	- Devuelve el proceso

#### Crea el proceso de bote
	- Llama a Ejecuter
	
### Lee los resultados en conjunto (lee un solo bote cada vez)
	- Le pasan un proceso de tipo Ejecuter
	- Lee el proceso (Si no ha terminado Ejecuter espera a que termine)
	- Devuelve el resultado del proceso en un string

#### Devuelve el resultado del proceso en un string	
	- Contiene toda la información del bote en una sola linea separada por espacios con el formato [Numero Bote] [Total] [Mujeres] [Hombres] [Niños]
	
### Crea los datos del informe y lo manda a generar
	- Recibe los datos de los botes (todos)
	- Crea la lista con los datos de los botes
	- Genera el informe

---

## Bote:
	- Asigne un numero de personas repartidos entre hombres, mujeres y niños
	- Haga el conteo de las personas
	- Envia cuantas personas hay de cada tipo en el bote y su total al servicio de emergencia

### Asigne un numero de personas repartidos entre hombres, mujeres y niños
	- Se tiene que asignar un numero no superior a 100 personas maximas en el bote

### Envia cuantas personas hay de cada tipo en el bote y su total al servicio de emergencia
	- Se envia por la salida estandar los datos de las personas
		- Manda 4 numeros, con el orden: [Total] [Mujeres] [Hombres] [Niños] separados por espacio en una sola linea

---

## Extras:
	- Hay que crear una clase que contenga las personas de un bote, esta clase tiene:
		Total:
		Mujeres:
		Hombres:
	    Niños:

---

### Protocolo de comunicación

`Bote` enviará la información a través de un `System.out.print` para poder leer la salida estandar del proceso, y lo mandara con un formato ( [Total] [Numero de Mujeres] [Numero de varones] [Numero de niños] ) separados con un espacio en una sola linea para su procesamiento en `generarInforme()`

### Plan de pruebas

Usaremos **JUNIT** y **Mockito** para esta parte y probaremos:

**Bote:** Lo ejecutaremos 100 veces y de esas 100 veces ninguna deberá traer un valor total de personas mayor a 100, se simularán los datos de entrada y que saque el conteo de personas bien.

**Ejecuter:** le mandaremos un programa que ejecute simple y probar que `getSalida()` lea bien, tambien probaremos a ejecutar un comando erroneo y que `getSalida()` tenga el codigo de error.

**UniversalWriter:** Le pondremos a generar un informe y verlo que salga bien

**ServicioDeEmergencias:** Se simularán los datos de los botes que reciba para comprobar que genera el informe final correctamente.

---

## Manual de usuario

Para ejecutar el programa se debe lanzar `ServicioEmergencias.java`, y esperar a que en /informe salga el `informe.md`.

Si se quieren cambiar la cantidad maxima de personas en cada bote: meterse en `Botes.java` y cambiar el valor del atributo cantidadMaximaPersonas (Cambiarlo despues con respecto)

Si se quieren cambiar la cantidad maxima de botes lanzados, ir a `ServicioEmergencias.java` y cambiar el valor del atributo (pensar despues como se llama)

---

## Elementos destacables del desarrollo

A la hora de recibir los datos de los botes se ha conseguido que el tiempo de espera sea el minimo.

---

## Problemas encontrados

