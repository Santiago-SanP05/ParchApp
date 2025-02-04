
const perfil = document.querySelector('#perfil'); 

perfil.addEventListener("click", mostrarPerfil);

async function mostrarPerfil(){
  var identifiacador = localStorage.getItem("email");
  try {
    const token = localStorage.getItem("token");
    const response = await fetch('http://localhost:3002/api/users',{
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
    console.log(data);
    console.log(identifiacador);
    for (const element of data) {
      if (element.email === identifiacador) {
        const publicacion = document.querySelector(".publicacion");
      publicacion.innerHTML=`
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
                  <h2>${"aqui va el numero de seguidores"}</h2>
                  <h2>${"aqui va el numero de seguidos"}</h2>
                </div>
                <div class="bio">
                  <h1>${element.biography}</h1>
                </div>
                <div class="editPerfil">
                  <button class="botoneditar">Editar Perfil</button>
                </div>
              </div>
            </div>
            <div class="publicacion-item">
              <header class="encabezadoPubli">
                <img src="/Images/burrita.jpg" alt="Imagen de perfil del usuario" id="imagen">
                <div>
                  <h2>${"aqui va el nombre del ususario"}</h2>
                  <time datetime="${"aqui va la fecha de publicacion"}">${new Date("aqui va la fecha de publicacion").toLocaleString()}</time>
                </div>
              </header>
  
              <section class="cuerpoPubli">
                <h2>${"aqui va el contenido o descripcion de la imagen"}</h2>
                <img id="imgPublicacion" src="${"aqui va la imagen"}" alt="Imagen de la publicación">
              </section>
  
              <footer class="contenedorComentarios">
                <div class="comentarios">
                  <div class="contenedorReaccion">
                    <a href=""><img src="/Images/Me encanta.png" alt="Reacción de me encanta"></a>
                    <p>123</p>
                  </div>
                  <div class="contenedorimgcoment">
                    <a href=""><img src="/Images/Comentarios.png" alt="Icono de comentarios"></a>
                  </div>
                </div>
              </footer>
            </div>
                  
  
      `;
      }
    }
    var editarRegistrar = document.querySelector(".botoneditar");

editarRegistrar.addEventListener("click",editarUsuario);
    
} catch (error) {
  console.error('Hubo un problema con la solicitud:', error);
}
}





async function editarUsuario(){
  try {
    const token = localStorage.getItem("token");
    const id = localStorage.getItem("id")
    const response = await fetch('http://localhost:3002/api/users/'+id,{
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
    aparecerEditar.innerHTML=`
    <div class="datosRegistrar">
            <p>Nombre usuario: <input id="editnombreusuario" type="text" placeholder="${data.name}"></p>
            <p>Usuario: <input id="editusuario" type="text" placeholder="${data.nameUser}"></p>
            <p>Correo: <input id="editemail" type="email" placeholder="${data.email}"></p>
            <p>Biografia: <input id="editBio" type="text" placeholder="${data.biography}"></p>
            <p>Url foto: <input id="editUrlImagen" type="url" placeholder="${data.urlPhoto}"> </p>
            <p>Contraseña: <input id="" type="password" placeholder="${data.pasword}"></p>
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


function enviareditarususario(){
  const editnombreusuario = document.querySelector("#editnombreusuario").value.trim();
  const editusuario = document.querySelector("#editusuario").value.trim();
  const editemail = document.querySelector("#editemail").value.trim();
  const editBio = document.querySelector("#editBio").value.trim();
  const editUrlImagen= document.querySelector("#editUrlImagen").value.trim();
  const editcontraseña = document.querySelector("#editusuario").value.trim();
  const editcontraseñaConfirmar = document.querySelector("#editcontraseñaConfirmar").value.trim();

  if (!editnombreusuario || !editusuario || !editemail || !editcontraseña || !editcontraseñaConfirmar || !editBio) {
    throw new Error("Por favor, completa todos los campos obligatorios.");
}
  const validaemail = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  const validaurl = /^(https?:\/\/.*\.(?:png|jpg|jpeg|gif|webp|svg))$/i;
  if (editcontraseña !== editcontraseñaConfirmar) {
    throw new Error("La contraseña de confirmación no coincide con la contraseña ingresada.");
  }
  if (editemail && !validaemail.test(validaemail)) {
    alert("La contraseña de confirmación no coincide con la contraseña ingresada.");
  }
  if (editUrlImagen  && !validaurl.test(validaurl)) {
    alert("La contraseña de confirmación no coincide con la contraseña ingresada.");
  }

  

}