console.log("hola mundo")

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
            <div class="encabezadoPubli">
              <img src="burrita.jpg" alt="burrita" id="imagen">
              <h2>${first.userPublication}</h2>
            </div>
            <div class="cuerpoPubli">
              <h2>${first.caption}</h2>
              <img id="imgPublicacion" src="${first.imageUrl}" alt="Imagen de la publicación">
              <p>${new Date(first.publicationDate).toLocaleString()}</p>
            </div>
            <div class="comentarios">
              <!-- Aquí podrías agregar los comentarios si los tienes -->
            </div>
          </div>
        `;
      }
  
      publicacion.innerHTML = contenidoPublicaciones;
  
    } catch (error) {
      console.error('Hubo un problema con la solicitud:', error);
    }
  }
  fetchData();



