## Comercio
Algoritmo que ofrezce como salida la lista de identificadores de producto, ordenados por el campo sequence, que cumplan unas condiciones de visibilidad, separados por comas.

La primera es cuando una talla está marcada como back soon, en este caso, aunque la talla
no tenga stock, el producto debe mostrarse igual, puesto que es un producto que va a
volver a estar a la venta cuando vuelva a entrar stock.
La segunda es cuando un producto tiene tallas “especiales”, en este caso el producto solo
estará visible si al menos una talla especial y una no especial tiene stock (o está marcada
como back soon). Si solo tienen stock (o están marcadas como back soon) tallas de uno de
los dos grupos el producto no debe mostrarse. Esta casuística se utiliza en productos
compuestos, por ejemplo, un cojín que consta de un relleno y una funda, solo se muestra si
hay stock tanto del relleno como de la funda, si no hay stock de ninguno o solo del relleno o
solo de la funda, entonces el cojín no se muestra.


``` 
docker compose up
```
aplicar migraciones a BD
``` 
mvn flyway:migrate
```

``` 
mvn clean install
```