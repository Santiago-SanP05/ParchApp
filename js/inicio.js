function getCurrentDateTime() {
  return new Date().toISOString();
}


console.log("hola mundo")



var principal = document.querySelector("#principal");
principal.addEventListener("click", fetchData());

async function fetchData() {
  try {
    const token = localStorage.getItem("token");
    const urlPost = "http://localhost:3002/api/post";
    const urlUser = "http://localhost:3002/api/users/";

    const publicacionContainer = document.querySelector(".publicacion");

    // Obtener todas las publicaciones
    const responsePosts = await fetch(urlPost, {
      method: "GET",
      headers: {
        "Authorization": `Bearer ${token}`,
        "Content-Type": "application/json"
      }
    });

    if (!responsePosts.ok) {
      throw new Error(`Error en la solicitud de publicaciones: ${responsePosts.status}`);
    }

    const posts = await responsePosts.json();

    // Limpiar el contenedor antes de agregar nuevas publicaciones
    publicacionContainer.innerHTML = "";

    for (const post of posts) {
      // Obtener información del usuario que hizo la publicación
      const responseUser = await fetch(urlUser + post.userId, {
        method: "GET",
        headers: {
          "Authorization": `Bearer ${token}`,
          "Content-Type": "application/json"
        }
      });

      if (!responseUser.ok) {
        console.warn(`No se pudo obtener el usuario para la publicación: ${post.idPost}`);
        continue;
      }

      const userData = await responseUser.json();

      // Obtener información de los usuarios que comentaron
      const comentariosConNombres = await Promise.all(post.coments.map(async (comment) => {
        const responseCommentUser = await fetch(urlUser + comment.idUser, {
          method: "GET",
          headers: {
            "Authorization": `Bearer ${token}`,
            "Content-Type": "application/json"
          }
        });

        const userCommentData = await responseCommentUser.json();

        return `
          <div class="etiquetasComentario">
            <h4>${userCommentData.nameUser}</h4>
            <p>${comment.text}</p>
            <p>${new Date(comment.publicationDate).toLocaleString()}</p>
          </div>
        `;
      }));

      // Agregar la publicación al DOM
      publicacionContainer.insertAdjacentHTML('beforeend', `
        <div class="publicacion-item" data-postid="${post.idPost}">
          <header class="encabezadoPubli">
            <img src="${userData.urlPhoto}" alt="Imagen de perfil del usuario" id="imagen">
            <div>
              <h2>@${userData.nameUser}</h2>
              <time datetime="${post.publicationDate}">${new Date(post.publicationDate).toLocaleString()}</time>
            </div>
          </header>

          <section class="cuerpoPubli">
            <h2>${post.caption}</h2>
            <img id="imgPublicacion" src="${post.imageUrl}" alt="Imagen de la publicación">
          </section>

          <footer class="contenedorComentarios">
            <div class="comentarios">
              <div class="contenedorReaccion">
                <a href="#"><img src="/Images/Me encanta.png" alt="Reacción de me encanta"></a>
                <p>${post.reactions.length}</p>
              </div>
              <div class="contenedorimgcoment">
                <a href="#"><img src="/Images/Comentarios.png" alt="Icono de comentarios"></a>
                <p>${post.coments.length}</p>
              </div>
            </div>
          </footer>

          <div class="hacerComentario">
            <div class="inputComentario">
              <input id="leerComentario" type="text" placeholder="Comentar">
              <button class="enviarComentario" data-postid="${post.idPost}">Enviar</button>
            </div>
          </div>

          <div class="todoComentarios">
            <div class="centradorComentarios">
              <div class="resultadoComentarios">
                ${comentariosConNombres.join('')}
              </div>
            </div>
          </div>
        </div>
      `);
    }

    // 🔥 Ahora agregamos los eventos de los botones después de renderizar todo
    document.querySelectorAll(".enviarComentario").forEach(boton => {
      boton.addEventListener("click", function() {
        const postId = this.getAttribute("data-postid"); // Obtener ID único del post
        hacerComentario(postId);
      });
    });
    

  } catch (error) {
    console.error('Hubo un problema con la solicitud:', error);
  }
}





var principal = document.querySelector("#principal");
principal.addEventListener("click", fetchData);








async function hacerComentario(postId){
  console.log(postId)
  let leercommentario = document.querySelector("#leerComentario").value; 
  const idUser = Number(localStorage.getItem("id"));
  const datosEnvioComentario = {
    text: leercommentario,
    publicationDate: new Date().toISOString(),
    idUser: idUser,
    idPost: postId,
  }

  try {
    let urlPost = "http://localhost:3002/api/comment"
    let token = localStorage.getItem("token");
    const respuesta = await fetch(urlPost, {
      method: 'POST', // Método HTTP PUT
      headers: {
        "Authorization": `Bearer ${token}`,
        'Content-Type': 'application/json' // Indica que el cuerpo está en JSON
      },
      body: JSON.stringify(datosEnvioComentario) // Convierte el objeto a JSON
    });

    if (respuesta.ok) {
      const datos = await respuesta.json();
      console.log('Datos actualizados:', datos);

    } else {
      console.error('Error al actualizar:', respuesta.status);
    }

    fetchData()
  } catch (error) {
    console.error('Error de red:', error);
  }

}
/*
async function hacerComentario(postId) {
  console.log("ID del post:", postId);

  // Obtener el comentario del input correctamente
  let leerComentario = document.querySelector("#leerComentario").value;

  // Verificar que el comentario no esté vacío
  if (!leerComentario.trim()) {
    console.error("El comentario está vacío");
    return;
  }

  // Obtener ID de usuario
  const idUser = Number(localStorage.getItem("id"));
  if (isNaN(idUser)) {
    console.error("ID de usuario no válido");
    return;
  }

  // Obtener token de autenticación
  let token = localStorage.getItem("token");
  if (!token) {
    console.error("No hay token en localStorage");
    return;
  }

  // Construir el objeto con los datos correctos
  const datosEnvioComentario = {
    text: leerComentario,
    publicationDate: new Date().toISOString(), // Formato ISO 8601
    idUser: idUser,
    idPost: postId,
  };

  try {
    let urlPost = "http://localhost:3002/api/comment";
    
    const respuesta = await fetch(urlPost, {
      method: "POST",
      headers: {
        "Authorization": `Bearer ${token}`,
        "Content-Type": "application/json",
      },
      body: JSON.stringify(datosEnvioComentario),
    });

    if (respuesta.ok) {
      const datos = await respuesta.json();
      console.log("Comentario enviado con éxito:", datos);
    } else {
      console.error("Error al enviar comentario:", respuesta.status, await respuesta.text());
    }
  } catch (error) {
    console.error("Error de red:", error);
  }
}
*/