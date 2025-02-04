
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
                  <button>Editar Perfil</button>
                </div>
              </div>
            </div>
              `+`
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
    
    
} catch (error) {
  console.error('Hubo un problema con la solicitud:', error);
}
}