# Inditex E-commerce Pricing Service


## Descripción del proyecto
**Inditex E-commerce Pricing Service** es una aplicación desarrollada con **Spring Boot** que proporciona un servicio REST tanto síncrono como asíncrono para consultar el precio aplicable a un producto para una marca determinada en una fecha específica. 

### Características principales:
- **Consulta de precios**: Permite obtener el precio aplicable a un producto en función de la fecha, el ID del producto y el ID de la marca.
- **Validación de entradas**: Asegura que los parámetros de entrada (fecha de aplicación, ID del producto y ID de la marca) no sean nulos.
- **Manejo de excepciones**: Gestiona adecuadamente los casos en los que no se encuentra un precio aplicable.
- **Pruebas unitarias**: Incluye un conjunto completo de pruebas unitarias para validar el comportamiento del servicio.
- **Documentación de API**: Proporciona documentación interactiva de la API a través de Swagger UI.
- **Contenerización**: Facilita la implementación y ejecución mediante Docker.

### Arquitectura:
El proyecto sigue una **arquitectura hexagonal (Ports and Adapters)**, lo que mejora la mantenibilidad, testabilidad, flexibilidad y escalabilidad del sistema. Esta arquitectura permite desacoplar el núcleo de la aplicación de los detalles de implementación, facilitando la integración con diferentes tecnologías y adaptadores.

```console
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── inditex/
│   │           └── ecommerce/
│   │               ├── application/
│   │               │   └── usecases/
│   │               │       └── FindApplicablePriceUseCaseImpl.java
│   │               ├── domain/
│   │               │   ├── models/
│   │               │   │   ├── CurrencyEnum.java
│   │               │   │   ├── ErrorMessage.java
│   │               │   │   └── PriceDto.java
│   │               │   ├── ports/
│   │               │   │   ├── in/
│   │               │   │   │   └── FindApplicablePriceUseCase.java
│   │               │   │   └── out/
│   │               │   │       └── PriceRepositoryPort.java
│   │               │   └── services/
│   │               │       └── PriceService.java
│   │               ├── infrastructure/
│   │               │   ├── adapters/
│   │               │   │   └── PriceRepositoryAdapter.java
│   │               │   ├── config/
│   │               │   │   ├── AuditingConfig.java
│   │               │   │   └── BeanPriceConfig.java
│   │               │   ├── controllers/
│   │               │   │   └── PriceController.java
│   │               │   ├── entities/
│   │               │   │   ├── BrandEntity.java
│   │               │   │   ├── PriceEntity.java
│   │               │   │   └── ProductEntity.java
│   │               │   ├── exceptions/
│   │               │   │   ├── GlobalControllerAdviceHandler.java
│   │               │   │   └── PriceNotFoundException.java
│   │               │   ├── mappers/
│   │               │   │   └── PriceMapper.java
│   │               │   └── repositories/
│   │               │       ├── BrandRepository.java
│   │               │       ├── PriceRepository.java
│   │               │       └── ProductRepository.java
│   │               └── shared/
│   │                   └── utils/
│   │                       └── DuplicatedOpsUtil.java
│   └── resources/
│       └── application.yml
└── test/
    └── java/
        └── com/
            └── inditex/
                └── ecommerce/
                    ├── application/
                    │   └── usecases/
                    │       └── FindApplicablePriceUseCaseImplTest.java
                    ├── infrastructure/
                    │   └── adapters/
                    │       └── PriceRepositoryAdapterTest.java
                    └── PriceServiceTest.java
   ```

### Componentes principales:
- **Controladores REST**: Gestionan las solicitudes HTTP y delegan la lógica de negocio a los casos de uso.
- **Casos de uso**: Implementan la lógica de negocio principal y coordinan las interacciones entre los diferentes componentes.
- **Adaptadores**: Conectan el núcleo de la aplicación con las implementaciones específicas de los puertos, como repositorios y servicios externos.
- **Repositorios**: Gestionan el acceso a la base de datos, utilizando **H2 Database** para almacenamiento en memoria durante las pruebas.
- **Mapeadores**: Transforman entidades de dominio en DTOs y viceversa, facilitando la comunicación entre capas.


### Esquema de la Base de Datos
El esquema de la base de datos para el E-commerce Pricing Service puede ser representado de la siguiente manera:

#### Tablas Principales
- **Brand**: Almacena información sobre las marcas.
- **Product**: Almacena información sobre los productos.
- **Price**: Almacena los precios aplicables a los productos en diferentes fechas y para diferentes marcas.


## Documentación de la API
La documentación de la API está disponible en los siguientes enlaces:

- **Definición de API:** [InditexEcomAPI.yaml](docs/InditexEcomAPI.yaml)

- **Swagger UI:** [http://localhost:8084/swagger-ui/index.html](http://localhost:8084/swagger-ui/index.html)

- **API Docs:** [http://localhost:8084/v3/api-docs](http://localhost:8084/v3/api-docs)



## Pasos para compilar y desplegar
1. Clona el repositorio:
    ```console
    git clone https://github.com/mr-pgy/pruebaInditex/test-inditex.git
    cd test-inditex/
    ```

2. Construye el proyecto con Maven:
    ```console
    mvn clean install
    ```

3. Construye la imagen Docker:
    ```console
    docker build -t ecommerce-app .
    ```

4. Ejecuta el contenedor Docker:
    ```console
    docker run -p 8084:8084 ecommerce-app
    ```

## Uso
Para consultar el precio aplicable a un producto en una fecha específica para una marca determinada, utiliza el siguiente endpoint:
(llamada síncrona)
```console
curl --location --request GET 'http://localhost:8084/price/sync?date=2020-06-14-15.00.00&productId=35455&brandId=1'
```
(llamada asíncrona)
```console
curl --location --request GET 'http://localhost:8084/price/async?date=2020-06-14-15.00.00&productId=35455&brandId=1'
 ```
Postman: [InditexEcom.postman_collection.json](docs/InditexEcom.postman_collection.json)


## Pruebas
Para ejecutar las pruebas unitarias, utiliza el siguiente comando:
```console
mvn test
```

## Concluciones
La aplicación se ha implementado de forma que se puedan mostrar el mayor numero de habilidades, aunque no tiene mucho sentido poner 2 flujos que hacen lo mismo siendo una prueba me he tomado la libertad de hacerlo para mostrar la forma de hacerlo tanto asíncrona como no, 
aunque no es completamente reactiva ya que la base de datos es jpa. La doble implementación con jpa-jakarta y r2dbc no es muy viable (y tampoco tendría sentido conectar una misma base de datos de las 2 formas).
La prueba se podría mejorar metiendo cachés, registro de llamadas para analíticas, auditorías, seguridad...