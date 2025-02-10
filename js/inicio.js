function getCurrentDateTime() {
  return new Date().toISOString();
}

function getCurrentDateTime2() {
  const now = new Date();
  const year = now.getFullYear();
  const month = String(now.getMonth() + 1).padStart(2, '0');
  const day = String(now.getDate()).padStart(2, '0');
  const hours = String(now.getHours()).padStart(2, '0');
  const minutes = String(now.getMinutes()).padStart(2, '0');
  const seconds = String(now.getSeconds()).padStart(2, '0');

  return `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;
}

console.log("hola mundo")



var principal = document.querySelector("#principal");
principal.addEventListener("click", fetchData());

async function fetchData() {
  let encabezadoPublicacion = document.querySelector(".encabezado");
  encabezadoPublicacion.innerHTML = "";
  encabezadoPublicacion.innerHTML = `<i class='bx bx-search-alt-2'></i>
          <input type="text" placeholder="Buscar en ParchApp" aria-label="Buscar publicaciones">`;
  try {
    const token = localStorage.getItem("token");
    const urlPost = "http://localhost:8080/parchap-0.0.1-SNAPSHOT/api/post";
    const urlUser = "http://localhost:8080/parchap-0.0.1-SNAPSHOT/api/users/";

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
    for (const post of posts.reverse()) {
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

// 🔥 Agregar event listener solo una vez para eliminar comentarios
if (!window.eventListenerEliminarAgregado) {
  document.addEventListener("click", function(event) {
      if (event.target.classList.contains("eliminarComentario")) {
          const commentId = event.target.getAttribute("data-commentid");
          eliminarComentario(commentId);
      }
  });
  const obteniendoModulo = "inicio"
  document.addEventListener("click", function(event) {
    if (event.target.classList.contains("editarComentario")) {
        const commentId = event.target.getAttribute("data-commentid");
        editarComentarioApartado(commentId,obteniendoModulo);
    }
});


  // Marcamos que ya se agregó el evento para evitar duplicados
  window.eventListenerEliminarAgregado = true;
}

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
  const usuarioActualId = Number(localStorage.getItem("id"));
  const esPropietario = usuarioActualId === comment.idUser;

  return `
    <div class="etiquetasComentario" data-commentid="${comment.idComment}">
      <h4 >${userCommentData.nameUser}</h4>
      <p>${comment.text}</p>
      <p>${new Date(comment.publicationDate).toLocaleString()}</p>

      ${esPropietario ? `
      <div class="Btn-SaveDelete">
        <button class="editarComentario" data-commentid="${comment.idComment}">Editar</button>
        <button class="eliminarComentario" data-commentid="${comment.idComment}">Eliminar</button>
      </div>
      ` : ""}
    </div>
  `;
}));
      // Agregar la publicación al DOM
      publicacionContainer.insertAdjacentHTML('beforeend', `
        <div class="publicacion-item" data-postid="${post.idPost}">
          <header class="encabezadoPubli">
            <img src="${userData.urlPhoto}" alt="Imagen de perfil del usuario" id="imagen">
            <div>
              <h2 class="user" data-nameUser="${userData.nameUser}">@${userData.nameUser}</h2>
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
                <img src="/Images/Me encanta.png" alt="Reacción de me encanta" class="like-img" data-postid="${post.idPost}"> 
                <p>${post.reactions.length}</p>
              </div>
              <div class="contenedorimgcoment">
                <img src="/Images/Comentarios.png" alt="Icono de comentarios">
                <p>${post.coments.length}</p>
              </div>
            </div>
          </footer>

          <div class="hacerComentario">
            <div class="inputComentario">
              <input class="leerComentario" type="text" placeholder="Comentar">
              <button class="enviarComentario" data-postid="${post.idPost}"><i class='bx bx-send'></i></button>
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

    const Username = document.querySelectorAll(".user");
    Username.forEach((Username) => {
      Username.addEventListener("click", function () {
         this.classList.toggle("active-border");
         let username = this.getAttribute("data-nameUser");
            
         buscarPublicaciones(username);
          });
      });
    
    const likeImages = document.querySelectorAll(".like-img"); // Selecciona todas las imágenes con la clase "like-img"
   likeImages.forEach((likeImage) => {
   likeImage.addEventListener("click", function () {
      this.classList.toggle("active-border");
      let postId2 = this.getAttribute("data-postid");
         
        hacerLike(postId2);
       });
   });


  } catch (error) {
    console.error('Hubo un problema con la solicitud:', error);
  }
}






var principal = document.querySelector("#principal");
principal.addEventListener("click", fetchData);











var urlCommentario = 'http://localhost:8080/parchap-0.0.1-SNAPSHOT/api/comment/';
async function eliminarComentario(idComentario){
  try {
    let token = localStorage.getItem("token");
    let response = await fetch(urlCommentario + idComentario, {
      method: "DELETE",
      headers: {
        "Authorization": `Bearer ${token}`,
        "Content-Type": "application/json"
      }
    });

    if (response.ok) {
      if (response.status !== 204) {
        const datos = await response.json();
        console.log('Usuario eliminado:', datos);
      } else {
        alert('Comentario eliminado exitosamente.');
      }
    } else {
      console.error('Error al eliminar el usuario:', response.status);
    }
    fetchData();
  } catch (error) {
    console.error('Error de red:', error);
  }
}

async function editarComentario(idComentario,obteniendoModulo){


  let leercomentario = document.querySelector("#ComentarioUsuario").value
  const estructuraComentario = {
    text : leercomentario,
    idUser: localStorage.getItem("id")
 

  }
  try {
    let token = localStorage.getItem("token");
    const respuesta = await fetch(urlCommentario + idComentario, {
      method: 'PUT', // Método HTTP PUT
      headers: {
        "Authorization": `Bearer ${token}`,
        'Content-Type': 'application/json' // Indica que el cuerpo está en JSON
      },
      body: JSON.stringify(estructuraComentario) // Convierte el objeto a JSON
    });

    if (respuesta.ok) {

      alert('Datos actualizados');

    } else {
      console.error('Error al actualizar:', respuesta.status);
    }
    if (obteniendoModulo == "inicio") {
      fetchData();
    }
    if (obteniendoModulo == "perfil") {
      mostrarPerfil()
    }
    if (obteniendoModulo != "perfil" && obteniendoModulo != "inicio") {
      buscarPublicaciones(obteniendoModulo)
    }

  } catch (error) {
    console.error('Error de red:', error);
  }

}

