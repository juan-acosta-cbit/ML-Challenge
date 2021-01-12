  # Operación FUEGO DE QUASAR - JUAN CARLOS ACOSTA RAMIREZ

  Este es un Challenge denominado FUEGO DE QUASAR, basado en una situación en la cual los Rebeldes deben interceptar un mensaje triangulando la posición mediante tres satélites
  Aquí se presenta una API REST bajo a través de HTTP POST - GET el cual recibe objetos en formato JSON.
  
  El servicio API REST fue construido en el lenguaje de programación JAVA (1.8), con Framework SpringBoot (2.3.4) y como Front y documentación del servicio se usó la herramienta Swagger (2.9.2).
  Este servicio se encuentra publicado en una máquina virtual de AWS (EC2), corriendo en un servidor Tomcat 9 por el puerto 80 (HTTP), dada que su IP es dinámica no se puede apagar la maquina ya que cambiaría la dirección del mismo, 
  actualmente se puede visualizar en:
  
  URL Servicio REST:
  http://ec2-3-21-103-165.us-east-2.compute.amazonaws.com/
  
  URL Documentación y test Servicio:
  http://ec2-3-21-103-165.us-east-2.compute.amazonaws.com/swagger-ui.html#/
  
  
  ## Introducción
  
  Una vez se recibe el mensaje al servicio "/topsecret" con la información del JSON, el servidor basado en 3 Satélites definidos internamente ubicados 
  en las coordenadas:
  
    Kenobi( -500 , -200 )
	Skywalker ( 100 ,  100 )
	Sato(  -4 ,   13 )
 
  interpreta la información (distancia) recibida y basado en una librería que retorna la ubicación [ X, Y ] donde se originó el mensaje.
  Adicional a esto se deben descifrar los mensajes que se encuentran distribuidos en listas, para retornar un JSON con la posición 
  y el mensaje completo, en caso de poder descifrarlo:
  
  En el caso de que no se pudiera calcular las coordenadas de la nave objetivo o no se pudiese reconstruir el mensaje original,
  la aplicación devuelve un RESPONSE CODE: 404.  
  
  La segunda parte del Challenge, se basa en un segundo servicio "topsecret_split" que acepta POST y GET y recibe información de un satélite
  a la vez, y este funciona de la misma manera al primer servicio.
  
  Si el mensaje es recibido por petición GET, la respuesta que envía el servicio es la misma que la de /topsecret/ en caso de
  que se hubiera podido descifrar el mensaje y calcular las coordenadas. Caso contrario, la aplicación responde un
  mensaje de error indicando que no hay información suficiente para realizar los cálculos.
  
  ## Paso a paso
  
  Para consumir el servicio de la Prueba 1, se debe acceder al servicio en la URL:  http://ec2-3-21-103-165.us-east-2.compute.amazonaws.com/topsecret y en un caso 
  ideal se debe enviar la siguiente información con una petición POST bajo el formato JSON:
  
  ``` JSON
{
	"satellites": [
			{
			"name": "kenobi",
			"distance": 100.0,
			"message": ["este", "", "", "mensaje", ""]
			},
			{
			"name": "skywalker",
			"distance": 115.5,
			"message": ["", "es", "", "", "secreto"]
			},
			{
			"name": "sato",
			"distance": 142.7,
			"message": ["", "", "un", "", ""]
			}
	]
}
  ```
  
  Si es exitoso el servicio retornara las coordenadas y el mensaje de la siguiente manera:
  
``` JSON  
{
   "position":    {
      "x": -57.75701183704373,
      "y": -58.808571620680205
   },
   "message": "este es un mensaje secreto"
}
``` 

Para la prueba 2 se debe acceder al servicio en la URL:  http://ec2-3-21-103-165.us-east-2.compute.amazonaws.com/topsecret_split/{satellite_name} , donde
{satellite_name} es el parámetro que recibe el servicio con petición POST o GET y hace referencia al nombre del satélite:


http://ec2-3-21-103-165.us-east-2.compute.amazonaws.com/topsecret_split/sato

``` JSON
{
	"distance": 110.0,
	"message": ["Este", "", "", "", "secreto"]
}
  ```


http://ec2-3-21-103-165.us-east-2.compute.amazonaws.com/topsecret_split/kenobi

``` JSON
{
	"distance": -150.0,
	"message": ["", "es", "", "mensaje", ""]
}
  ```


http://ec2-3-21-103-165.us-east-2.compute.amazonaws.com/topsecret_split/skywalker

``` JSON
{
	"distance": 80.7,
	"message": ["", "", "un", "", ""]
}
  ```


 Una vez se enviaron los datos de cada satélite, si están correctos, al consumir el servicio http://ec2-3-21-103-165.us-east-2.compute.amazonaws.com/topsecret_split/
 mediante una posición GET retornara las coordenadas y el mensaje concatenado de cada satélite de la siguiente manera:
  
``` JSON  
{
   "position":    {
      "x": -57.75701183704373,
      "y": -58.808571620680205
   },
   "message": "este es un mensaje secreto"
}
``` 


● Repositorio: https://github.com/juan-acosta-cbit/ML-Challenge.git

● URL del servicio: http://ec2-3-21-103-165.us-east-2.compute.amazonaws.com/topsecret


Creditos: Juan Carlos Acosta Ramirez
  
  
  
  
