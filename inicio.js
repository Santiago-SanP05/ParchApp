
console.log("hola mundo")

var principal = document.querySelector("#principal");
principal.addEventListener("click", fetchData());

async function fetchData() {
    try {
      const publicacion = document.querySelector(".publicacion");
  

      const response = await fetch('http://localhost:3002/api/post');
  

      if (!response.ok) {
        throw new Error(`Error en la solicitud: ${response.status}`);
      }
  

      const data = await response.json();
  

      let contenidoPublicaciones = "";
      
      for (let first of data) {

        contenidoPublicaciones += `
          <div class="publicacion-item">
            <header class="encabezadoPubli">
              <img src="burrita.jpg" alt="Imagen de perfil del usuario" id="imagen">
              <div>
                <h2>${first.userPublication}</h2>
                <time datetime="${first.publicationDate}">${new Date(first.publicationDate).toLocaleString()}</time>
              </div>
            </header>

            <section class="cuerpoPubli">
              <h2>${first.caption}</h2>
              <img id="imgPublicacion" src="${first.imageUrl}" alt="Imagen de la publicación">
            </section>

            <footer class="contenedorComentarios">
              <div class="comentarios">
                <div class="contenedorReaccion">
                  <a href=""><img src="Me encanta.png" alt="Reacción de me encanta"></a>
                  <p>123</p>
                </div>
                <div class="contenedorimgcoment">
                  <a href=""><img src="Comentarios.png" alt="Icono de comentarios"></a>
                </div>
              </div>
            </footer>
          </div>
        `;
        
      }
  
      publicacion.innerHTML = contenidoPublicaciones;
  
    } catch (error) {
      console.error('Hubo un problema con la solicitud:', error);
    }
  }



  var principal = document.querySelector("#principal");
  principal.addEventListener("click", fetchData);
