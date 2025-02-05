function getCurrentDateTime() {
  const now = new Date();
  const year = now.getFullYear();
  const month = String(now.getMonth() + 1).padStart(2, '0');
  const day = String(now.getDate()).padStart(2, '0');
  const hours = String(now.getHours()).padStart(2, '0');
  const minutes = String(now.getMinutes()).padStart(2, '0');
  const seconds = String(now.getSeconds()).padStart(2, '0');

  return `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;
}


const perfil = document.querySelector('#perfil');

perfil.addEventListener("click", mostrarPerfil);

async function mostrarPerfil() {
  var identifiacador = localStorage.getItem("email");
  try {
    const token = localStorage.getItem("token");
    const response = await fetch('http://localhost:3002/api/users', {
      method: "GET",
      headers: {
        "Authorization": `Bearer ${token}`,
        "Content-Type": "application/json"
      }
    });

    const respuestaSeguidores = await fetch('http://localhost:3002/api/users/' + localStorage.getItem("id") + '/followers', {
      method: "GET",
      headers: {
        "Authorization": `Bearer ${token}`,
        "Content-Type": "application/json"
      }
    });

    const respuestaSiguiendo = await fetch('http://localhost:3002/api/users/' + localStorage.getItem("id") + '/followed', {
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
        const publicacion = document.querySelector(".publicacion");
        publicacion.innerHTML = `
            <div class="contenedorUsuario">
              <div class="usuarioImagen">
                  <img src="${element.urlPhoto}" alt="Imagen de perfil del usuario">
              </div>
              <div class="usuarionombre">
                <div class="nombre">
                  <h2>@${element.nameUser}</h2>
                <h2>${element.name}</h2>
                </div>
                
                <div class="seguidores">
                  <h2>Seguidores</h2>
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
        await publicacionUsuario();
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

  } catch (error) {
    console.error('Hubo un problema con la solicitud:', error);
  }
}





async function editarUsuario() {
  try {
    const token = localStorage.getItem("token");
    const id = localStorage.getItem("id")
    const response = await fetch('http://localhost:3002/api/users/' + id, {
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
    var aparecerEditar = document.querySelector(".publicacion");
    aparecerEditar.innerHTML = `
    <div class="datosRegistrar">
            <p>Nombre usuario: <input id="editnombreusuario" type="text" value="${data.name}" value="${data.name}"></p>
            <p>Usuario: <input id="editusuario" type="text" value="@${data.nameUser}"></p>
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
  console.log(editnombreusuario);
  console.log(editusuario);
  console.log(editemail);
  console.log(editBio);
  console.log(editUrlImagen);
  console.log(editcontraseña);
  console.log(editcontraseñaConfirmar);

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

  const url = 'http://localhost:3002/api/users/' + localStorage.getItem("id");

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
      console.log('Datos actualizados:', datos);
      localStorage.setItem("email", editemail);
    } else {
      console.error('Error al actualizar:', respuesta.status);
    }


    mostrarPerfil();
  } catch (error) {
    console.error('Error de red:', error);
  }

}

async function publicacionUsuario() {
  try {
    const token = localStorage.getItem("token");
    const id = localStorage.getItem("id");
    const urluser = 'http://localhost:3002/api/users/';

    // Obtener datos del usuario
    const response2 = await fetch(urluser + id, {
      method: "GET",
      headers: {
        "Authorization": `Bearer ${token}`,
        "Content-Type": "application/json"
      }
    });

    if (!response2.ok) {
      throw new Error(`Error en la solicitud del usuario: ${response2.status}`);
    }

    const data2 = await response2.json();

    // Obtener publicaciones del usuario
    const response = await fetch(urluser + id + '/posts', {
      method: "GET",
      headers: {
        "Authorization": `Bearer ${token}`,
        "Content-Type": "application/json"
      }
    });

    if (!response.ok) {
      throw new Error(`Error en la solicitud de publicaciones: ${response.status}`);
    }

    const data = await response.json();
    const publicacionesUsuario = document.querySelector(".publicacion-item");

    // Limpiar el contenedor antes de agregar nuevas publicaciones
    publicacionesUsuario.innerHTML = "";

    for (const element of data) {



      // Agregar cada publicación sin borrar las anteriores
      publicacionesUsuario.insertAdjacentHTML('beforeend', `
        <div class="publicacion">
          <header class="encabezadoPubli">
            <img src="${data2.urlPhoto}" alt="Imagen de perfil del usuario" id="imagen">
            <div>
              <h2>@${data2.nameUser}</h2>
              <time datetime="${element.publicationDate}">${new Date(element.publicationDate).toLocaleString()}</time>
            </div>
            <div class="OrdenarEditarEliminarBotones">
            <button class="editarPublicacion"> Editar </button>
            <button class="eliminarPublicacion"> Eliminar </button>
            </div>
          </header>

          <section class="cuerpoPubli">
            <h2>${element.caption}</h2>
            <img id="imgPublicacion" src="${element.imageUrl}" alt="Imagen de la publicación">
          </section>

          <footer class="contenedorComentarios">
            <div class="comentarios">
              <div class="contenedorReaccion">
                <a href="#"><img src="/Images/Me encanta.png" alt="Reacción de me encanta"></a>
                <p>${element.reactions.length}</p>
              </div>
              <div class="contenedorimgcoment">
                <a href="#"><img src="/Images/Comentarios.png" alt="Icono de comentarios"></a>
                <p>${element.coments.length}</p>
              </div>
            </div>
          </footer>

          <div class="todoComentarios">
            <div class="centradorComentarios">
              <div class="resultadoComentarios">
              </div>
            </div>
          </div>
          
        </div>
      `);

      const contenedorComentarios = publicacionesUsuario.lastElementChild.querySelector(".resultadoComentarios");

      // Llamar a la función para insertar comentarios, pasando los comentarios de la publicación actual
      insertarComentarios(element.coments, contenedorComentarios);
    }
  } catch (error) {
    console.error('Hubo un problema con la solicitud:', error);
  }
}


function insertarComentarios(comments, contenedor) {
  // Limpiar el contenedor de comentarios antes de agregar nuevos
  contenedor.innerHTML = "";

  // Iterar sobre los comentarios y agregarlos al contenedor
  for (const comment of comments) {
    console.log(comment.text);
    contenedor.innerHTML += `
        <div class="">
        <h4>${comment.idComment}</h4>
          <p>${comment.text}</p>
          <p>${comment.publicationDate}</p>
        </div>
          

    `;
  }
}



function cerrarSesion() {
  localStorage.removeItem("email");
  localStorage.removeItem("id");
  localStorage.removeItem("token");
  window.location.href = "/html/main.html";
}


function apartadoPublicacion() {
  var encabezadoPublicacion = document.querySelector(".encabezado");
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
  console.log(envioDescripcion);
  console.log(envioImagen);


  if (!envioDescripcion || !envioImagen) {
    alert("Por favor, completa todos los campos obligatorios.");
  }

  const validaurl = /^(https?:\/\/.*\.(?:png|jpg|jpeg|gif|webp|svg))$/i;

  if (envioImagen && !validaurl.test(envioImagen)) {
    alert("Recuerda que para la imagen debe ser una url.");
    return;
  }

  const url = 'http://localhost:3002/api/post';

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
      console.log('Datos actualizados:', datos);

    } else {
      console.error('Error al actualizar:', respuesta.status);
    }
    mostrarPerfil();

  } catch (error) {
    console.error('Error de red:', error);
  }

}

async function eliminarUsuario() {
  let url = 'http://localhost:3002/api/users/' + localStorage.getItem("id");
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