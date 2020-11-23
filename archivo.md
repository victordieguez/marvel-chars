Proyecto que hace uso de la API de marvel.
Dispone de una actividad principal que muestra un listado con todos los personajes devueltos por el endpoint GET /v1/public/characters pudiendo filtrar por nombre,
o dejándolo en blanco y obteniendo así un listado de todos los personajes.
Se ha implementado un scroll listener para este y todos los recycler view de las vistas, para ir cargando los siguientes personajes (o comics o lo que sea en cada caso) cuando se esté llegando al final.
Al pulsar en cualquier fila que represente un personaje se viaja a la segunda actividad, la cual muestra dicho personaje en detalle con las siguientes pestañas:
    - Biografía: Se muestra una imagen del personaje y su descripción (si dispone de ella)
    - Comics: Se muestra una galería con todos los comics en los que aparece dicho personaje
    - Series: Se muestra una galería con todos las series en los que aparece dicho personaje
    - Eventos: Se muestra una galería con todos los eventos en los que aparece dicho personaje

Liberias externas utilizadas:
RecyclerView: Utilizada para mostrar un listado de componentes, en este caso por ejemplo todos los personajes, con esta librería se va cargando la información según va siendo necesaria para verse en la aplicación
Cardview: Utilizada para mostrar cada objeto de un recycler view (sea la fila con la información del personaje o el cuadrado con la imagen del comic, serie o evento)
Retrofit: Para realizar cualquier llamada HTTP a la API
Gson: Para realizar el  mapeo automático de los JSON recibidos por la API a objetos propios
Glide: Utilizada para realizar la carga de imagenes de forma dinámica (se van cargando según si necesitan ser mostradas), además utiliza un sistema de cacheo para evitar repetir llamadas


Ya que las imágenes que nos da la API son todas en páginas http (no https) ha sido necesario añadir lo siguiente a la etiqueta application del AndroidManifest.xml
android:usesCleartextTraffic="true"