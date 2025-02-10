


const perfil = document.querySelector('#perfil');

perfil.addEventListener("click", mostrarPerfil);

async function mostrarPerfil() {

  var identifiacador = localStorage.getItem("email");
  try {
    const token = localStorage.getItem("token");
    const response = await fetch('http://localhost:8080/parchap-0.0.1-SNAPSHOT/api/users', {
      method: "GET",
      headers: {
        "Authorization": `Bearer ${token}`,
        "Content-Type": "application/json"
      }
    });

    const respuestaSeguidores = await fetch('http://localhost:8080/parchap-0.0.1-SNAPSHOT/api/users/' + localStorage.getItem("id") + '/followers', {
      method: "GET",
      headers: {
        "Authorization": `Bearer ${token}`,
        "Content-Type": "application/json"
      }
    });

    const respuestaSiguiendo = await fetch('http://localhost:8080/parchap-0.0.1-SNAPSHOT/api/users/' + localStorage.getItem("id") + '/followed', {
      method: "GET",
      headers: {
        "Authorization": `Bearer ${token}`,
        "Content-Type": "application/json"
      }
    });

    if (!respuestaSiguiendo.ok) {
      throw new Error(`Error en la solicitud: ${response.status}`);
    }

    if (!respuestaSeguidores.ok) {
      throw new Error(`Error en la solicitud: ${response.status}`);
    }

    if (!response.ok) {
      throw new Error(`Error en la solicitud: ${response.status}`);
    }

    const seguiendo = await respuestaSiguiendo.json();
    const seguidores = await respuestaSeguidores.json();
    const data = await response.json();



    for (const element of data) {
      if (element.email === identifiacador) {
        let encabezadoPublicacion = document.querySelector(".encabezado");
        encabezadoPublicacion.innerHTML = "";
        encabezadoPublicacion.innerHTML = `
        <i class='bx bx-search-alt-2'></i>
          <input type="text" placeholder="Buscar en ParchApp" aria-label="Buscar publicaciones">`;
        const publicacion = document.querySelector(".publicacion");
        publicacion.innerHTML = `
            <div class="contenedorUsuario">
              <div class="usuarioImagen">
                  <img src="${element.urlPhoto}" alt="Imagen de perfil del usuario">
              </div>
              <div class="usuarionombre">
                <div class="nombre">
                  <h2 >@${element.nameUser}</h2>
                <h2>${element.name}</h2>
                </div>
                
                <div class="seguidores">
                  <h2 class="todosSeguidores">Seguidores</h2>
                  <h2>Seguidos</h2>
                </div>
                <div class="numero">
                  <h2>${seguidores.length}</h2>
                  <h2>${seguiendo.length}</h2>
                </div>
                <div class="bio">
                  <h1>${element.biography}</h1>
                </div>
                <div class="editPerfil">
                  <button class="hacerPublicacion"> Hacer Publicacion </button>
                  <button class="botoneditar">Editar Perfil</button>
                  <button class="eliminaUsuario"> Eliminar Perfil </button>
                  <button class="cerrarSesion"> Cerrar sesion </button>
                </div>
              </div>
            </div>
            <div class="publicacion-item"></div>

      `
        publicacionUsuario();
      }
    }
    var editarRegistrar = document.querySelector(".botoneditar");
    editarRegistrar.addEventListener("click", editarUsuario);
    var cerrandoSesion = document.querySelector(".cerrarSesion");
    cerrandoSesion.addEventListener("click", cerrarSesion);
    var apartadohacerPublicacion = document.querySelector(".hacerPublicacion");
    apartadohacerPublicacion.addEventListener("click", apartadoPublicacion);
    var eliminaUsuario = document.querySelector(".eliminaUsuario");
    eliminaUsuario.addEventListener("click", eliminarUsuario);
    var todoSeguidos = document.querySelector(".todosSeguidores")
    todoSeguidos.addEventListener("click", function(){
      var seguidoreeees ="seguidores"
      verNotificacionesSeguidores(seguidoreeees)
    })

  } catch (error) {
    console.error('Hubo un problema con la solicitud:', error);
  }
}




async function editarUsuario() {
  try {
    const token = localStorage.getItem("token");
    const id = localStorage.getItem("id")
    const response = await fetch('http://localhost:8080/parchap-0.0.1-SNAPSHOT/api/users/' + id, {
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
    let encabezadoPublicacion = document.querySelector(".encabezado");
        encabezadoPublicacion.innerHTML = "";
        encabezadoPublicacion.innerHTML = "Editar Usuario";
    var aparecerEditar = document.querySelector(".publicacion");
    aparecerEditar.innerHTML = `
    <div class="datosRegistrar">
            <p>Nombre usuario: <input id="editnombreusuario" type="text" value="${data.name}" value="${data.name}"></p>
            <p>Usuario: <input id="editusuario" type="text" value="${data.nameUser}"></p>
            <p>Correo: <input id="editemail" type="email" value="${data.email}"></p>
            <p>Biografia: <input id="editBio" type="text" value="${data.biography}"></p>
            <p>Url foto: <input id="editUrlImagen" type="url" value="${data.urlPhoto}"></p>
            <p>Contraseña: <input id="editcontraseña" type="password" placeholder="**********"></p>
            <p>Confiramar Contraseña: <input id="editcontraseñaConfirmar" type="password" placeholder="confirmar contraseña"></p>
            <button class="enviarEditar"> Enviar </button>
          </div>
    
    `;
    var enviarDatosEditadosUser = document.querySelector(".enviarEditar");
    enviarDatosEditadosUser.addEventListener("click", enviareditarususario)

  } catch (error) {
    console.error('Hubo un problema con la solicitud:', error);
  }

}


async function enviareditarususario() {
  const editnombreusuario = document.querySelector("#editnombreusuario").value.trim();
  const editusuario = document.querySelector("#editusuario").value.trim();
  const editemail = document.querySelector("#editemail").value.trim();
  const editBio = document.querySelector("#editBio").value.trim();
  const editUrlImagen = document.querySelector("#editUrlImagen").value.trim();
  const editcontraseña = document.querySelector("#editcontraseña").value.trim();
  const editcontraseñaConfirmar = document.querySelector("#editcontraseñaConfirmar").value.trim();
  
  

  if (!editnombreusuario || !editusuario || !editemail || !editcontraseña || !editcontraseñaConfirmar || !editBio) {
    alert("Por favor, completa todos los campos obligatorios.");
  }
  const validaemail = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  const validaurl = /^(https?:\/\/.*\.(?:png|jpg|jpeg|gif|webp|svg))$/i;

  if (editemail && !validaemail.test(editemail)) {
    alert("El email no concide con los parametros necesario recuerda el formato hola@gmail.com");
    return;
  }
  if (editUrlImagen && !validaurl.test(editUrlImagen)) {
    alert("Recuerda que para la imagen debe ser una url.");
    return;
  }
  if (editcontraseña !== editcontraseñaConfirmar) {
    alert("La contraseña de confirmación no coincide con la contraseña ingresada.");
    return;
  }

  const url = 'http://localhost:8080/parchap-0.0.1-SNAPSHOT/api/users/' + localStorage.getItem("id");

  const datosActualizados = {
    name: editnombreusuario,
    nameUser: editusuario,
    urlPhoto: editUrlImagen,
    email: editemail,
    password: editcontraseña,
    biography: editBio,


  };

  try {
    let tooken = localStorage.getItem("token");
    const respuesta = await fetch(url, {
      method: 'PUT', // Método HTTP PUT
      headers: {
        "Authorization": `Bearer ${tooken}`,
        'Content-Type': 'application/json' // Indica que el cuerpo está en JSON
      },
      body: JSON.stringify(datosActualizados) // Convierte el objeto a JSON
    });

    if (respuesta.ok) {
      const datos = await respuesta.json();
    
      localStorage.setItem("email", editemail);
    } else {
      console.error('Error al actualizar:', respuesta.status);
    }


    mostrarPerfil();
  } catch (error) {
    console.error('Error de red:', error);
  }

}

async function publicacionUsuario(id) {
  try {
    const token = localStorage.getItem("token");
    const id = Number(localStorage.getItem("id"));
    const urluser = "http://localhost:8080/parchap-0.0.1-SNAPSHOT/api/users/";

    // Definir obteniendoModulo FUERA del if
    const obteniendoModulo = "perfil"; 

    // Obtener datos del usuario
    const response2 = await fetch(urluser + id, {
      method: "GET",
      headers: {
        "Authorization": `Bearer ${token}`,
        "Content-Type": "application/json",
      },
    });

    if (!window.eventListenerEliminarAgregado) {
      document.addEventListener("click", function (event) {
        if (event.target.classList.contains("eliminarComentarioPerfil")) {
          const commentId = event.target.getAttribute("data-commentid");
          eliminarComentario2(commentId, id);
        }
      });



      window.eventListenerEliminarAgregado = true;
    }

    if (!response2.ok) {
      throw new Error(`Error en la solicitud del usuario: ${response2.status}`);
    }

    const data2 = await response2.json();

    // Obtener publicaciones del usuario
    const response = await fetch(urluser + id + "/posts", {
      method: "GET",
      headers: {
        "Authorization": `Bearer ${token}`,
        "Content-Type": "application/json",
      },
    });

    if (!response.ok) {
      throw new Error(`Error en la solicitud de publicaciones: ${response.status}`);
    }

    const data = await response.json();
    const publicacionesUsuario = document.querySelector(".publicacion-item");

    // Limpiar el contenedor antes de agregar nuevas publicaciones
    publicacionesUsuario.innerHTML = "";

    for (const element of data.reverse()) {
      // Solo mostrar botones si el usuario es el dueño de la publicación
      const esPropietario = id === element.userId;
      const botonesEdicion = esPropietario
        ? `
        <div class="OrdenarEditarEliminarBotones">
          <button class="editarPublicacion"><i class='bx bx-edit' ></i></button>
          <button class="eliminarPublicacion"><i class='bx bx-comment-x' ></i></button>
        </div>
        `
        : "";

      // Agregar cada publicación sin borrar las anteriores
      publicacionesUsuario.insertAdjacentHTML(
        "beforeend",
        `
        <div class="publicacion">
          <header class="encabezadoPubli">
            <img src="${data2.urlPhoto}" alt="Imagen de perfil del usuario" id="imagen">
            <div>
              <h2 class="nombrePerfil" data-nameUser="${data2.nameUser}" >@${data2.nameUser}</h2>
              <time datetime="${element.publicationDate}">${new Date(element.publicationDate).toLocaleString()}</time>
            </div>
            ${botonesEdicion}
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
                <img src="/Images/Comentarios.png" alt="Icono de comentarios">
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
        // Agregar eventos para editar y eliminar si el usuario es el dueño
        let eventoEditar = nuevaPublicacion.querySelector(".editarPublicacion");
        let eventoEliminar = nuevaPublicacion.querySelector(".eliminarPublicacion");
        let eventoReaccion = nuevaPublicacion.querySelector(".like-img");
        let eventoComentario = nuevaPublicacion.querySelector(".enviarComentario")

        eventoEditar.addEventListener("click", function () {
          editarPublicacion(element.idPost, element.caption, element.imageUrl, element.publicationDate);
        });

        eventoEliminar.addEventListener("click", function () {
          eliminarPost(element.idPost, nuevaPublicacion);
        });
        eventoReaccion.addEventListener("click", function () {

          hacerLikePerfil(element.idPost, id);

        })
        eventoComentario.addEventListener("click", function () {
          hacerComentarioPerfil(element.idPost, id);
        })
      }
      var buscarUsuario =document.querySelector(".nombrePerfil")
      buscarUsuario.addEventListener("click", function(){
        let username2 = this.getAttribute("data-nameUser")
        buscarPublicaciones(username2)
      })

      const contenedorComentarios = nuevaPublicacion.querySelector(".resultadoComentarios");

      // Llamar a la función para insertar comentarios
      insertarComentarios(element.coments, contenedorComentarios, id);
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






function cerrarSesion() {
  localStorage.removeItem("email");
  localStorage.removeItem("id");
  localStorage.removeItem("token");
  window.location.href = "/index.html";
}


function apartadoPublicacion() {
  let encabezadoPublicacion = document.querySelector(".encabezado");
  encabezadoPublicacion.innerHTML = "";
  encabezadoPublicacion.innerHTML = "<h1>Publicacion</h1>";
  var aparecerEditar = document.querySelector(".publicacion");
  aparecerEditar.innerHTML = `
    <style>
        .publicacion {
            justify-content: space-around;
            text-align: center;
            align-items: center;
            height: 80%;
        }


    </style>
    <div class="datosPublicacion">
            <p>Descripcion: <input id="descripcion" type="text" placeholder="Descripcion"></p>
            <p>Url de la imagen: <input id="urlImagen" type="text" placeholder="https://www.ejemplo.com/imagen.jpg"></p>
            <button class="enviarPublicacion"> Hacer publicacion </button>
          </div>
    
    `;
  var envio = document.querySelector(".enviarPublicacion");
  envio.addEventListener("click", enviaPulicacion)

}

async function enviaPulicacion() {
  const envioDescripcion = document.querySelector("#descripcion").value.trim();
  const envioImagen = document.querySelector("#urlImagen").value.trim();


  if (!envioDescripcion || !envioImagen) {
    alert("Por favor, completa todos los campos obligatorios.");
  }

  const validaurl = /^(https?:\/\/.*\.(?:png|jpg|jpeg|gif|webp|svg))$/i;

  if (envioImagen && !validaurl.test(envioImagen)) {
    alert("Recuerda que para la imagen debe ser una url.");
    return;
  }

  const url = 'http://localhost:8080/parchap-0.0.1-SNAPSHOT/api/post';

  const datosEnvioPublicacion = {
    userId: localStorage.getItem("id"),
    caption: envioDescripcion,
    imageUrl: envioImagen,

  };

  try {
    let tooken = localStorage.getItem("token");
    const respuesta = await fetch(url, {
      method: 'POST', // Método HTTP PUT
      headers: {
        "Authorization": `Bearer ${tooken}`,
        'Content-Type': 'application/json' // Indica que el cuerpo está en JSON
      },
      body: JSON.stringify(datosEnvioPublicacion) // Convierte el objeto a JSON
    });

    if (respuesta.ok) {
      const datos = await respuesta.json();
 

    } else {
      console.error('Error al actualizar:', respuesta.status);
    }
    mostrarPerfil();

  } catch (error) {
    console.error('Error de red:', error);
  }

}

async function eliminarUsuario() {
  let url = 'http://localhost:8080/parchap-0.0.1-SNAPSHOT/api/users/' + localStorage.getItem("id");
  try {
    let token = localStorage.getItem("token");
    let response = await fetch(url, {
      method: "DELETE",
      headers: {
        "Authorization": `Bearer ${token}`,
        "Content-Type": "application/json"
      }
    });

    if (response.ok) {
      // Solo intentar hacer .json() si la respuesta tiene cuerpo
      if (response.status !== 204) {
        const datos = await response.json();
        console.log('Usuario eliminado:', datos);
      } else {
        console.log('Usuario eliminado exitosamente, sin contenido de respuesta.');
      }
    } else {
      console.error('Error al eliminar el usuario:', response.status);
    }
    cerrarSesion();
  } catch (error) {
    console.error('Error de red:', error);
  }
}

async function eliminarPost(obteniendoIdPost, publicacionElemento) {
  let url = 'http://localhost:8080/parchap-0.0.1-SNAPSHOT/api/post/' + obteniendoIdPost;
  try {
    let token = localStorage.getItem("token");
    let response = await fetch(url, {
      method: "DELETE",
      headers: {
        "Authorization": `Bearer ${token}`,
        "Content-Type": "application/json"
      }
    });

    if (response.ok) {
      // Verifica si hay contenido antes de intentar leer el JSON
      const contentType = response.headers.get("Content-Type");
      if (contentType && contentType.includes("application/json")) {
        const datos = await response.json();
        console.log('Publicación eliminada:', datos);
      } else {
        alert('Publicación eliminada exitosamente, sin contenido de respuesta.');
      }
    } else {
      console.error('Error al eliminar la publicación:', response.status);
    }
    publicacionElemento.remove();
  } catch (error) {
    console.error('Error de red:', error);
  }
}

function editarPublicacion(obteniendoIdPost, obteniendoCaption, obteniendoimagenurl, obteniendodate) {
  var encabezadoPublicacion = document.querySelector(".encabezado");
  encabezadoPublicacion.innerHTML = "";
  encabezadoPublicacion.innerHTML = "<h1>Editar Post</h1>";
  var aparecerEditar = document.querySelector(".publicacion");
  aparecerEditar.innerHTML = `
    <style>
        .publicacion {
            justify-content: space-around;
            text-align: center;
            align-items: center;
            height: 80%;
        }


    </style>
    <div class="datosPublicacion">
            <p>Descripcion: <input id="caption" type="text" placeholder="Descripcion" value="${obteniendoCaption}"></p>
            <button class="enviarPublicacion" id="envioPost"> Editar Publicacion </button>
          </div>
    
    `;
  var envio = document.querySelector("#envioPost");
  envio.addEventListener("click", function () {
    envioEditPost(obteniendoIdPost, obteniendoimagenurl);
  })

}
async function envioEditPost(obteniendoIdPost, obteniendoimagenurl, obteniendodate) {
  let nuevaCaption = document.querySelector("#caption").value.trim()

  const postActualizado = {
    imageUrl: obteniendoimagenurl,
    publicationDate: obteniendodate,
    caption: nuevaCaption,
    userId: Number(localStorage.getItem("id"))
  };
  
  
  try {
    let token = localStorage.getItem("token");
    
    const respuesta = await fetch('http://localhost:8080/parchap-0.0.1-SNAPSHOT/api/post/' + obteniendoIdPost, {
      method: 'PUT', // Método HTTP PUT
      headers: {
        "Authorization": `Bearer ${token}`,
        'Content-Type': 'application/json' // Indica que el cuerpo está en JSON
      },
      body: JSON.stringify(postActualizado) // Convierte el objeto a JSON
    });

    if (respuesta.ok) {
      const datos = await respuesta.json();
      alert('Datos actualizados',);
      mostrarPerfil();
    } else {
      console.error('Error al actualizar:', respuesta.status);
    }



  } catch (error) {

  }
}



function editarComentarioApartado(obteniendoIdPost, obteniendoModulo) {

  var encabezadoPublicacion = document.querySelector(".encabezado");
  encabezadoPublicacion.innerHTML = "";
  encabezadoPublicacion.innerHTML = "<h1>Comentario</h1>";
  var aparecerEditar = document.querySelector(".publicacion");
  aparecerEditar.innerHTML = `
    <style>
        .publicacion {
            justify-content: space-around;
            text-align: center;
            align-items: center;
            height: 80%;
        }


    </style>
    <div class="datosPublicacion">
            <p>Descripcion: <input id="ComentarioUsuario" type="text" placeholder="Descripcion"></p>
            <button class="envioEditComentario" id="envioEditComentario"> Editar comentario </button>
          </div>
    
    `;
  var envio = document.querySelector(".envioEditComentario");
  envio.addEventListener("click", function () {
    editarComentario(obteniendoIdPost, obteniendoModulo);
  })
}
