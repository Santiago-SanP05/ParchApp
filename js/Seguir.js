var urlUser3 = 'http://localhost:3002/api/users';
var token3 = localStorage.getItem("token");
var id = localStorage.getItem("id");


document.addEventListener("keypress", function (event) {
  let inputBusqueda = document.querySelector(".encabezado input");

  if (inputBusqueda && event.target === inputBusqueda && event.key === "Enter") {
    let textoBusqueda = inputBusqueda.value.trim();
    buscarPublicaciones(textoBusqueda);
  }
});




async function buscarPublicaciones(texto) {

  try {
    const response = await fetch(urlUser3 + "/user" + "/" + texto, {
      method: "GET",
      headers: {
        "Authorization": `Bearer ${token3}`,
        "Content-Type": "application/json"
      }
    });
    const busacrUser = await response.json();
    const respuestaSeguidores = await fetch(urlUser3 + '/' + busacrUser.idUser + '/followers', {
      method: "GET",
      headers: {
        "Authorization": `Bearer ${token3}`,
        "Content-Type": "application/json"
      }
    });

    const respuestaSeguidos = await fetch(urlUser3 + '/' + busacrUser.idUser + '/followed', {
      method: "GET",
      headers: {
        "Authorization": `Bearer ${token3}`,
        "Content-Type": "application/json"
      }
    });
    const respuestaBoton = await fetch(urlSeguimiento, {
      method: "GET",
      headers: {
        "Authorization": `Bearer ${token3}`,
        "Content-Type": "application/json",
      },
  
    });
    if (!respuestaBoton.ok) {
      console.error("Error al obtener las reacciones:", respuestaBoton.status);
      return;
    }
  
    const datosBoton = await respuestaBoton.json();

    // Verificar si el usuario ya reaccionó a esta publicación
    const SeguidorExistenteBoton = datosBoton.find((seguidor) => {
      if (seguidor.userFollowerId == id && seguidor.userFollowedId == busacrUser.idUser) {

        publicacionUsuarioSeguido(busacrUser.idUser, texto);

        return true; // Retorna `true` para que `find()` devuelva este objeto
      }
      return false;
    })

    if (!respuestaSeguidos.ok) {
      alert("Este usuario no existe");
      throw new Error(`Error en la solicitud: ${response.status}`);

    }

    if (!respuestaSeguidores.ok) {
      alert("Este usuario no existe");
      throw new Error(`Error en la solicitud: ${response.status}`);

    }

    if (!response.ok) {
      alert("Este usuario no existe");
      throw new Error(`Error en la solicitud: ${response.status}`);

    }
    const Buscaralgo = await respuestaSeguidores.json();
    const Buscaralgo2 = await respuestaSeguidos.json();


    let encabezadoPublicacion = document.querySelector(".encabezado");
    let cuerpo = document.querySelector(".publicacion");
    encabezadoPublicacion.innerHTML = "";
    cuerpo.innerHTML = "";
    encabezadoPublicacion.innerHTML = `
          <i class='bx bx-search-alt-2'></i>
            <input type="text" placeholder="Buscar en ParchApp" aria-label="Buscar publicaciones">`;
    cuerpo.innerHTML = `
              <div class="contenedorUsuario">
                <div class="usuarioImagen">
                    <img src="${busacrUser.urlPhoto}" alt="Imagen de perfil del usuario">
                </div>
                <div class="usuarionombre">
                  <div class="nombre">
                    <h2>@${busacrUser.nameUser}</h2>
                  <h2>${busacrUser.name}</h2>
                  </div>
                  
                  <div class="seguidores">
                    <h2>Seguidores:</h2>
                    <h2>Seguidos:</h2>
                  </div>
                  <div class="numero">
                    <h2>${Buscaralgo.length}</h2>
                    <h2>${Buscaralgo2.length}</h2>
                  </div>
                  <div class="bio">
                    <h1>${busacrUser.biography}</h1>
                  </div>
                  <div class="editPerfil">
                    <button class="hacerSeguir">${SeguidorExistenteBoton ? "Seguido" : "Seguir"}</button>
                  </div>
                </div>
              </div>
              <div class="publicacion-item"></div>

          `;
          let eventoSeguir = document.querySelector(".hacerSeguir");

          // Clonar el botón para eliminar todos los eventos previos y evitar acumulación
          let nuevoBoton = eventoSeguir.cloneNode(true);
          eventoSeguir.parentNode.replaceChild(nuevoBoton, eventoSeguir);
      
          // Agregar el nuevo evento sin acumular los anteriores
          nuevoBoton.addEventListener("click", async function () {
            await hacerSeguimiento(busacrUser.idUser);
            buscarPublicaciones(texto); // Vuelve a ejecutar la búsqueda para actualizar los datos
          });

  } catch (error) {
    console.error('Hubo un problema con la solicitud:', error);
  }

}

var urlSeguimiento = "http://localhost:3002/api/follower";


async function hacerSeguimiento(idUser) {
  console.log(idUser)


  try {
    // Obtener todas las reacciones
    const respuesta = await fetch(urlSeguimiento, {
      method: "GET",
      headers: {
        "Authorization": `Bearer ${token3}`,
        "Content-Type": "application/json",
      },
    });

    if (!respuesta.ok) {
      console.error("Error al obtener las reacciones:", respuesta.status);
      return;
    }

    const datos2 = await respuesta.json();
    // Verificar si el usuario ya reaccionó a esta publicación
    const SeguidorExistente = datos2.find((seguidor) => {
      if (seguidor.userFollowerId == id && seguidor.userFollowedId == idUser) {
        console.log("Seguidor encontrado con ID:", seguidor.idFollower);
        return true; // Retorna `true` para que `find()` devuelva este objeto
      }
      return false;
    });


    if (SeguidorExistente) {
      // Si la reacción existe, eliminarla
      console.log("Eliminando seguidor con ID:", SeguidorExistente.idFollower);
      const respuestaDelete = await fetch(`${urlSeguimiento}/${SeguidorExistente.idFollower}`, {
        method: "DELETE",
        headers: {
          "Authorization": `Bearer ${token3}`,
          "Content-Type": "application/json",
        },
      });

      if (respuestaDelete.ok) {
        alert("Seguidor eliminada");

      } else {
        console.error("Error al eliminar la reacción:", respuestaDelete.status);
      }
    } else {
      // Si la reacción no existe, agregarla
      console.log("Agregando nueva reacción");
      const datosEnvioSeguimiento = {
        userFollowedId: idUser,
        followDate: getCurrentDateTime(),
        userFollowerId: id,
      };

      const respuestaSeguidor = await fetch(urlSeguimiento, {
        method: "POST",
        headers: {
          "Authorization": `Bearer ${token3}`,
          "Content-Type": "application/json",
        },
        body: JSON.stringify(datosEnvioSeguimiento),
      });

      if (respuestaSeguidor.ok) {
        alert("Seguimiento agregada");
      } else {
        console.error("Error al agregar el seguidor:", respuestaSeguidor.status);
      }
    }
  } catch (error) {
    console.error("Error de red:", error);
  }
}



  // Obtener todas las reacciones


async function publicacionUsuarioSeguido(id, texto) {
  console.log(";DSFSFSD"+texto)
  console.log("assssssssssssssss")
  try {

    // Obtener datos del usuario
    const response2 = await fetch(urlUser3+ "/" + id, {
      method: "GET",
      headers: {
        "Authorization": `Bearer ${token}`,
        "Content-Type": "application/json",
      },
    });
    if (!response2.ok) {
      throw new Error(`Error en la solicitud del usuario: ${response2.status}`);
    }

    const data2 = await response2.json();
    if (!window.eventListenerEliminarAgregado) {
      document.body.addEventListener("click", (event) => {
        if (event.target.classList.contains("eliminarComentarioPerfil")) {
          const commentId = event.target.getAttribute("data-commentid");
          console.log("Se hizo clic en eliminar. ID comentario:", texto);
          eliminarComentario3(commentId, texto);
        }
      });
    
      window.eventListenerEliminarAgregado = true;
    }
    

    
    // Obtener publicaciones del usuario
    const response = await fetch(urlUser3+ "/" + id + "/posts", {
      method: "GET",
      headers: {
        "Authorization": `Bearer ${token3}`,
        "Content-Type": "application/json",
      },
    });

    if (!response.ok) {
      throw new Error(`Error en la solicitud de publicaciones: ${response.status}`);
    }

    const data = await response.json();
    const publicacionesUsuario = document.querySelector(".publicacion-item");

    if (!publicacionesUsuario) {
      console.error("⚠️ Error: No se encontró el contenedor .publicacion-item en el DOM.");
      return; // Salir de la función para evitar más errores
    }
    

    // Limpiar el contenedor antes de agregar nuevas publicaciones
    publicacionesUsuario.innerHTML = "";

    for (const element of data) {
      // Solo mostrar botones si el usuario es el dueño de la publicación
      const esPropietario = id === element.userId;



      // Agregar cada publicación sin borrar las anteriores
      publicacionesUsuario.insertAdjacentHTML(
        "beforeend",
        `
        <div class="publicacion">
          <header class="encabezadoPubli">
            <img src="${data2.urlPhoto}" alt="Imagen de perfil del usuario" id="imagen">
            <div>
              <h2>@${data2.nameUser}</h2>
              <time datetime="${element.publicationDate}">${new Date(element.publicationDate).toLocaleString()}</time>
            </div>

          </header>

          <section class="cuerpoPubli">
            <h2>${element.caption}</h2>
            <img id="imgPublicacion" src="${element.imageUrl}" alt="Imagen de la publicación">
          </section>

          <footer class="contenedorComentarios">
            <div class="comentarios">
              <div class="contenedorReaccion">
                <img src="/Images/Me encanta.png" alt="Reacción de me encanta" class="like-img">
                <p>${element.reactions.length}</p>
              </div>
              <div class="contenedorimgcoment">
                <a href="#"><img src="/Images/Comentarios.png" alt="Icono de comentarios"></a>
                <p>${element.coments.length}</p>
              </div>
            </div>
          </footer>

          <div class="hacerComentario">
            <div class="inputComentario">
              <input class="leerComentarioPerfil" type="text" placeholder="Comentar">
              <button class="enviarComentario" data-postid="${element.idPost}"><i class='bx bx-send'></i></button>
            </div>
          </div>

          <div class="todoComentarios">
            <div class="centradorComentarios">
              <div class="resultadoComentarios"></div>


            </div>
          </div>
        </div>
      `
      );

      let nuevaPublicacion = publicacionesUsuario.lastElementChild;

      if (esPropietario) {

        let eventoReaccion = nuevaPublicacion.querySelector(".like-img");
        let eventoComentario = nuevaPublicacion.querySelector(".enviarComentario")




        eventoReaccion.addEventListener("click", function () {

          hacerLikePerfil(element.idPost, texto);

        })
        eventoComentario.addEventListener("click", function () {
          hacerComentarioPerfil(element.idPost, texto);
        })
      }


      const contenedorComentarios = nuevaPublicacion.querySelector(".resultadoComentarios");

      // Llamar a la función para insertar comentarios
      insertarComentarios(element.coments, contenedorComentarios, texto);
    }
    const likeImages = document.querySelectorAll(".like-img"); // Selecciona todas las imágenes con la clase "like-img"
    likeImages.forEach((likeImage) => {
      likeImage.addEventListener("click", function () {
        this.classList.toggle("active-border");
      });
    });
  } catch (error) {
    console.error("Hubo un problema con la solicitud:", error);
  }
}

async function eliminarComentario3( idComentario, texto){
  console.log(texto)
  console.log("holaaaaaaaaaaaaaaaaaaaaa "+ idComentario)
  console.log(urlComentario+idComentario)
  try {
    let token = localStorage.getItem("token");
    let response = await fetch(urlCommentario+idComentario, {
      method: "DELETE",
      headers: {
        "Authorization": `Bearer ${token}`,
        "Content-Type": "application/json"
      }
    });

    if (response.ok) {
      if (response.status !== 204) {
        const datos = await response.json();
        console.log('comentario eliminado:', datos);

          

        
      } else {
        console.log('Comentario eliminado exitosamente.');
        buscarPublicaciones(texto);
      }
    } else {
      console.error('Error al eliminar el usuario:', response.status);
    }
  } catch (error) {
    console.error('Error de red:', error);
  }
}