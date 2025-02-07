const urlComentario = "http://localhost:3002/api/comment";
const token = localStorage.getItem("token");
async function hacerComentarioPerfil(postId) {
    console.log("ID del post:", postId);
    
    // Buscar la publicación específica por su atributo data-postid
    const publicacion = document.querySelector(`[data-postid="${postId}"]`).closest(".publicacion");
    
    if (!publicacion) {
        console.error("No se encontró la publicación correspondiente.");
        return;
    }

    // Buscar el campo de comentario dentro de esta publicación
    const inputComentario = publicacion.querySelector(".leerComentarioPerfil");
    
    if (!inputComentario) {
        console.error("No se encontró el campo de comentario.");
        return;
    }

    let leercomentaroo = inputComentario.value.trim();
    console.log("Comentario a enviar:", leercomentaroo);

    if (!leercomentaroo) {
        console.error("El comentario está vacío");
        return;
    }

    const idUser = Number(localStorage.getItem("id"));
    const datosEnvioComentarioPerfil = {
        text: leercomentaroo,
        publicationDate: new Date().toISOString(),
        idUser: idUser,
        idPost: postId,
    };

    console.log("Enviando comentario:", datosEnvioComentarioPerfil);

    try {
        const token = localStorage.getItem("token");
        const respuesta = await fetch(urlComentario, {
            method: "POST",
            headers: {
                "Authorization": `Bearer ${token}`,
                "Content-Type": "application/json",
            },
            body: JSON.stringify(datosEnvioComentarioPerfil),
        });

        if (respuesta.ok) {
            console.log("Comentario enviado con éxito.");

            // Limpiar el campo de comentario
            inputComentario.value = "";

            // Recargar los comentarios solo de esta publicación
            publicacionUsuario();
        } else {
            console.error("Error al enviar comentario:", respuesta.status);
        }
    } catch (error) {
        console.error("Error de red:", error);
    }
}


  async function hacerComentario(postId) {
    console.log("ID del post:", postId);
  
    // Seleccionar el contenedor de la publicación específica
    let publicacionItem = document.querySelector(`[data-postid="${postId}"]`);
  
    if (!publicacionItem) {
      console.error("No se encontró la publicación correspondiente");
      return;
    }
  
    // Obtener el input dentro de esa publicación
    let leerComentarioInput = publicacionItem.querySelector(".leerComentario");
  
    if (!leerComentarioInput) {
      console.error("No se encontró el campo de comentario");
      return;
    }
  
    let leerComentario = leerComentarioInput.value.trim();
  
    // Verificar que el comentario no esté vacío
    if (!leerComentario) {
      console.error("El comentario está vacío");
      return;
    }
  
    const idUser = Number(localStorage.getItem("id"));
    const datosEnvioComentario = {
      text: leerComentario,
      publicationDate: new Date().toISOString(),
      idUser: idUser,
      idPost: postId,
    };
  
    console.log("Enviando comentario:", datosEnvioComentario);
  
    try {
      
      let token = localStorage.getItem("token");
  
      const respuesta = await fetch(urlComentario, {
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
        
        // Limpiar el campo de comentario después de enviar
        leerComentarioInput.value = "";
  
        // Recargar las publicaciones para mostrar el nuevo comentario
        fetchData();
      } else {
        console.error("Error al enviar comentario:", respuesta.status);
      }
    } catch (error) {
      console.error("Error de red:", error);
    }
  }


  function insertarComentarios(comments, contenedor) {
    // Limpiar el contenedor de comentarios antes de agregar nuevos
    contenedor.innerHTML = "";
  
    const usuarioActualId = Number(localStorage.getItem("id"));
  
    // Iterar sobre los comentarios y agregarlos al contenedor
    for (const comment of comments) {
      const esPropietario = usuarioActualId === comment.idUser;
      const botonesEdicion = esPropietario
        ? `
        <div class="Btn-SaveDelete">
          <button class="editarComentarioPerfil" data-commentid="${comment.idComment}">Editar</button>
          <button class="eliminarComentarioPerfil" data-commentid="${comment.idComment}">Eliminar</button>
        </div>
        `
        : "";
  
      // Crear el comentario como un elemento HTML para poder asignarle eventos
      const comentarioHTML = document.createElement("div");
      comentarioHTML.classList.add("etiquetasComentario");
      comentarioHTML.innerHTML = `
        <h4>${comment.idUser}</h4>
        <p>${comment.text}</p>
        <p>${new Date(comment.publicationDate).toLocaleString()}</p>
        ${botonesEdicion}
      `;
  
      contenedor.appendChild(comentarioHTML);
  
      if (esPropietario) {
        let btnEliminar = comentarioHTML.querySelector(".eliminarComentarioPerfil");
        let btnEditar = comentarioHTML.querySelector(".editarComentarioPerfil");
  
        if (btnEliminar) {
          btnEliminar.addEventListener("click", function () {
            eliminarComentario2(comment.idComment);
          });
        }
  
        if (btnEditar) {
          btnEditar.addEventListener("click", function () {
            editarComentario2(comment.idComment);
          });
        }
      }
    }
  }

  var urlCommentario = 'http://localhost:3002/api/comment/';

async function eliminarComentario2( idComentario){
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
        publicacionUsuario()
      } else {
        console.log('Comentario eliminado exitosamente.');
      }
    } else {
      console.error('Error al eliminar el usuario:', response.status);
    }
    publicacionUsuario()
  } catch (error) {
    console.error('Error de red:', error);
  }
}

async function editarComentario2(idComentario){
  console.log("holaaaaaaaaaaaaaaaaaaaaa");
  const estructuraComentario = {
    
    text : "HOLA MUNDO",
    idUser: localStorage.getItem("id")
    

  }
  try {
    let token = localStorage.getItem("token");
    const respuesta = await fetch(urlCommentario+idComentario, {
      method: 'PUT', // Método HTTP PUT
      headers: {
        "Authorization": `Bearer ${token}`,
        'Content-Type': 'application/json' // Indica que el cuerpo está en JSON
      },
      body: JSON.stringify(estructuraComentario) // Convierte el objeto a JSON
    });

    if (respuesta.ok) {
      const datos = await respuesta.json();
      console.log('Datos actualizados:', datos);
      publicacionUsuario()

    } else {
      console.error('Error al actualizar:', respuesta.status);
    }


  } catch (error) {
    console.error('Error de red:', error);
  }

}