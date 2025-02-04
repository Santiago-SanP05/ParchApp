
console.log("hola mundo")



var principal = document.querySelector("#principal");
principal.addEventListener("click", fetchData());

async function fetchData() {
  try {
    const token = localStorage.getItem("token");
    const publicacion = document.querySelector(".publicacion");

    const response = await fetch('http://localhost:3002/api/post', {
      method: "GET",
      headers: {
        "Authorization": `Bearer ${token}`,
        "Content-Type": "application/json"
      }
    });

    if (!response.ok) {
      throw new Error(`Error en la solicitud: ${response.status}`);
    }

    const data = await response.json();
    let contenidoPublicaciones = "";

    for (var first of data) {
      // Hacemos la llamada para obtener el nombre de usuario de la publicación
      const responseUser = await fetch('http://localhost:3002/api/users/' + first.userId, {
        method: "GET",
        headers: {
          "Authorization": `Bearer ${token}`,
          "Content-Type": "application/json"
        }
      });
      const data2 = await responseUser.json(); 

      // Ahora creamos la publicación con el nombre del usuario
      const comentariosConNombres = await Promise.all(first.coments.map(async (comment) => {
        const response2 = await fetch('http://localhost:3002/api/users/' + comment.idUser, {
          method: "GET",
          headers: {
            "Authorization": `Bearer ${token}`,
            "Content-Type": "application/json"
          }
        });
        const data3 = await response2.json();
        
        return `
          <div class="etiquetasCommentario">
            <h4>${data3.nameUser}</h4> <!-- Nombre del usuario del comentario -->
            <p>${comment.text}</p>
            <p> ${comment.publicationDate}</p>
          </div>
        `;
      }));

      contenidoPublicaciones += `
        <div class="publicacion-item">
          <header class="encabezadoPubli">
            <img src="${data2.urlPhoto}" alt="Imagen de perfil del usuario" id="imagen">
            <div>
              <h2>${data2.nameUser}</h2> <!-- Ahora mostramos el nombre del usuario de la publicación -->
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
                <a href=""><img src="/Images/Me encanta.png" alt="Reacción de me encanta"></a>
                <p>${first.reactions.length}</p>
              </div>
              <div class="contenedorimgcoment">
                <a id="vercommentarios" href="#"><img src="/Images/Comentarios.png" alt="Icono de comentarios"></a>
                <p>${first.coments.length}</p>
              </div>
            </div>
          </footer>

          <div class="todoComentarios">
            <div class="centradorComentarios">
              <div class="resultadoComentarios">
                ${comentariosConNombres.join('')} <!-- Comentarios con el nombre de usuario -->
              </div>
            </div>
          </div>
        </div>
      `;
    }

    // Insertamos el contenido generado en el HTML
    publicacion.innerHTML = contenidoPublicaciones;

    // Manejamos el evento de los comentarios
    var commentarios = document.querySelector("#vercommentarios");
    commentarios.addEventListener("click", abrircomentarios);

  } catch (error) {
    console.error('Hubo un problema con la solicitud:', error);
  }
}





var principal = document.querySelector("#principal");
principal.addEventListener("click", fetchData);






async function abrircomentarios() {

  try {
    const token = localStorage.getItem("token");
    var contenido = document.querySelector(".todoComentarios");


    const response = await fetch('http://localhost:3002/api/post', {
      method: "GET",
      headers: {
        "Authorization": `Bearer ${token}`,
        "Content-Type": "application/json"
      }
    });


    if (!response.ok) {
      throw new Error(`Error en la solicitud: ${response.status}`);
    }


    const data = await response.json();


    let contenidocomentarios = "";
    console.log(data)
    
    for (var first of data) {
      contenidocomentarios.innerHTML += `
            <div class="centradorComentarios">
              <div class="resultadoComentarios">
                <div class="etiquetasCommentario">
                  <h4>${first.idComment}</h4>
                  <p>${first.text}</p>
                </div>
              </div>
            </div>`
    }
    contenido.innerHTML = contenidocomentarios
  } catch (error) {
    console.error('Hubo un problema con la solicitud:', error);
  }
}